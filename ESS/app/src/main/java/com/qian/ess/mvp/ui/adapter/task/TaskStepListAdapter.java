package com.qian.ess.mvp.ui.adapter.task;

import android.content.Context;
import android.view.View;

import com.qian.ess.R;
import com.qian.ess.bean.tc.TechninalCard;
import com.qian.ess.mvp.ui.adapter.listview.CommonAdapter;
import com.qian.ess.mvp.ui.adapter.listview.ViewHolder;

/**
 * Created by QianMoLi on 2018/11/3.
 * <p>
 * 每一个任务的步骤列表
 */

public class TaskStepListAdapter extends CommonAdapter<TechninalCard> {

    private OnItemClickListenet onItemClickListenet;

    public TaskStepListAdapter(Context context) {
        super(context, R.layout.item_task_step_adapter);
    }

    @Override
    protected void convert(ViewHolder viewHolder, final TechninalCard techninalCard, int position) {

        int pictureListSize = techninalCard.getPhotoPathList().size(); //图片个数
        int videoListSize = techninalCard.getVideoPathList().size(); //视频个数
        int audioListSize = techninalCard.getAudioPathList().size(); //音频个数

        viewHolder.setText(R.id.txt_number, techninalCard.getNumber()) //编号
                .setText(R.id.txt_work_content, techninalCard.getWorkerName()) //工作内容
                .setText(R.id.txt_completion, techninalCard.getHours()) //完成情况
                .setText(R.id.txt_worker_name, techninalCard.getWorkerName()) //操作者
                .setText(R.id.txt_reviewer_name, techninalCard.getReviewerName()) //审核者
                .setText(R.id.txt_result_num, techninalCard.getResultValue()) //结果数量
                .setText(R.id.txt_remark, techninalCard.getRemark()) //备注
                .setText(R.id.txt_picture_size, pictureListSize == 0 ? "-" : pictureListSize + "") //照片数量
                .setText(R.id.txt_video_size, videoListSize == 0 ? "-" : videoListSize + "") //视频数量
                .setText(R.id.txt_audio_size, audioListSize == 0 ? "-" : audioListSize + "") //音频数量
                .setOnClickListener(R.id.txt_work_content, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(null != onItemClickListenet){
                            onItemClickListenet.onTCClick(techninalCard);
                        }
                    }
                })
                .setOnClickListener(R.id.txt_worker_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(null != onItemClickListenet){
                            onItemClickListenet.onWorkerNameClick(techninalCard);
                        }
                    }
                })
                .setOnClickListener(R.id.txt_reviewer_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(null != onItemClickListenet){
                            onItemClickListenet.onReviewerNameClick(techninalCard);
                        }
                    }
                });
    }

    public void setOnItemClickListenet(OnItemClickListenet onItemClickListenet) {
        this.onItemClickListenet = onItemClickListenet;
    }

    public interface OnItemClickListenet {
        void onTCClick(TechninalCard techninalCard);

        void onWorkerNameClick(TechninalCard techninalCard);

        void onReviewerNameClick(TechninalCard techninalCard);
    }
}
