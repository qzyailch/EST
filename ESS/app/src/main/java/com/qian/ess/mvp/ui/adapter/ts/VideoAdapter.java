package com.qian.ess.mvp.ui.adapter.ts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.qian.ess.R;
import com.qian.ess.mvp.ui.adapter.listview.CommonAdapter;
import com.qian.ess.mvp.ui.adapter.listview.ViewHolder;
import com.qian.ess.utils.InitFileUtils;

import java.io.File;
import java.util.List;

/**
 * Created by QianMoLi on 2018/9/24.
 * <p>
 * 视频列表适配器
 */

public class VideoAdapter extends CommonAdapter<String> {

    private OnPicClickListener mOnPicClickListener;
    private String fileName;
    private String objectNumber;
    private String partNumber;

    public VideoAdapter(Context context, String objectNumber, String partNumber, String fileName) {
        super(context, R.layout.item_video);
        this.fileName = fileName;
        this.objectNumber = objectNumber;
        this.partNumber = partNumber;
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, final int position) {
        viewHolder.setVisible(R.id.img_delete, true).setOnClickListener(R.id.img_delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnPicClickListener) {
                    mOnPicClickListener.onDelete(position);
                }
            }
        });
        //通过调用execute方法开始处理异步任务.相当于线程中的start方法.
        new MyAsyncTask((ImageView) viewHolder.getView(R.id.img_media)).execute(InitFileUtils.getTSFilePath(objectNumber, partNumber) + "/" + fileName + item);
    }

    //获取所有视频路径
    public String getMediaPaths() {
        List<String> picList = getArray();
        StringBuffer stringBuffer = new StringBuffer();
        if (null != picList && picList.size() > 0) {
            for (int i = 0, len = picList.size(); i < len; i++) {
                stringBuffer.append(",").append(picList.get(i));
            }
        }

        if (TextUtils.isEmpty(stringBuffer.toString())) {
            return null;
        } else {
            return stringBuffer.toString().substring(1);
        }
    }

    //获取视频的缩略图
    private Bitmap getMediaThumb(String path) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();//实例化MediaMetadataRetriever对象
        File file = new File(path);//实例化File对象，文件路径为/storage/sdcard/Movies/music1.mp4
        if (file.exists()) {
            mmr.setDataSource(file.getAbsolutePath());//设置数据源为该文件对象指定的绝对路径
            return mmr.getFrameAtTime();//获得视频第一帧的Bitmap对象
        }
        return null;
    }

    public void setOnPicClickListener(OnPicClickListener onPicClickListener) {
        mOnPicClickListener = onPicClickListener;
    }

    public interface OnPicClickListener {
        void onDelete(int position);
    }

    @SuppressLint("StaticFieldLeak")
    class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {

        ImageView imageView;

        public MyAsyncTask(ImageView imageView) {
            this.imageView = imageView;
        }

        //onPreExecute用于异步处理前的操作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //在doInBackground方法中进行异步任务的处理.
        @Override
        protected Bitmap doInBackground(String... params) {
            //获取传进来的参数
            String path = params[0];
            return getMediaThumb(path);
        }

        //onPostExecute用于UI的更新.此方法的参数为doInBackground方法返回的值.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //更新imageView
            if (null != bitmap) {
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setImageResource(R.mipmap.batch_add_07);
            }
        }
    }
}
