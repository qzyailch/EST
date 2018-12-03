package com.qian.ess.mvp.ui.adapter.problem;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.qian.ess.R;
import com.qian.ess.bean.config.ts.TSLabel;
import com.qian.ess.bean.ts.TSSolution;
import com.qian.ess.mvp.ui.adapter.listview.CommonAdapter;
import com.qian.ess.mvp.ui.adapter.listview.ViewHolder;

/**
 * Created by QianMoLi on 2018/11/3.
 * <p>
 * 每一个问题解决的步骤列表
 */

public class ProblemStepListAdapter extends CommonAdapter<TSSolution> {

    private TSLabel tsLabel;
    private OnSolutionClickListener onSolutionClickListener;

    public ProblemStepListAdapter(Context context, TSLabel tsLabel) {
        super(context, R.layout.item_problem_step_adapter);
        this.tsLabel = tsLabel;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final TSSolution tsSolution, int position) {
        viewHolder.setText(R.id.txt_number, tsSolution.getTsSolutionNumber())
                .setText(R.id.txt_possible_reason, tsSolution.getPossibleReason())
                .setText(R.id.txt_is_use, tsSolution.getIsUsed() ? "+" : "-")
                .setText(R.id.txt_is_work, tsSolution.getIsWork() ? "+" : "-")
                .setOnClickListener(R.id.txt_step, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != onSolutionClickListener) {
                            onSolutionClickListener.onSolutionClick(tsSolution);
                        }
                    }
                });
//                .setText(R.id.txt_pic, tsSolution.getPictureList().size())
//                .setText(R.id.txt_video, tsSolution.getVideoList().size())
//                .setText(R.id.txt_audio, tsSolution.getAudioList().size());

        //说明书
        TextView txtStep = viewHolder.getView(R.id.txt_step);
        switch (tsSolution.getTsSolutionNumber()) {
            case 1:
                txtStep.setText(tsLabel.getTroubleshootingWayEM());
                break;
            case 2:
                txtStep.setText(tsLabel.getTroubleshootingWayAEM());
                break;
            case 3:
                txtStep.setText(tsLabel.getTroubleshootingWayTSEM());
                break;
            default:
                txtStep.setText(tsSolution.getTsSolutionName());
                break;
        }
    }

    public void setOnSolutionClickListener(OnSolutionClickListener onSolutionClickListener) {
        this.onSolutionClickListener = onSolutionClickListener;
    }

    public interface OnSolutionClickListener {
        void onSolutionClick(TSSolution tsSolution);
    }
}
