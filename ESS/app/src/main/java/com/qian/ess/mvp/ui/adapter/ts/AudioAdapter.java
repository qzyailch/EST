package com.qian.ess.mvp.ui.adapter.ts;

import android.content.Context;
import android.view.View;

import com.qian.ess.R;
import com.qian.ess.mvp.ui.adapter.listview.CommonAdapter;
import com.qian.ess.mvp.ui.adapter.listview.ViewHolder;
import com.qian.ess.widget.VoisePlayingIcon;

/**
 * Created by QianMoLi on 2018/11/23.
 * <p>
 * 音频文件列表适配器
 */

public class AudioAdapter extends CommonAdapter<String> {

    private int mPlayPosition = -1;

    public AudioAdapter(Context context) {
        super(context, R.layout.item_audio);
    }

    public void setPlayPosition(int position) {
        mPlayPosition = position;
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        viewHolder.setText(R.id.txt_audio_name, item);

        VoisePlayingIcon icon = viewHolder.getView(R.id.voise_playint_icon);
        if (position == mPlayPosition) {
            icon.setVisibility(View.VISIBLE);
            icon.start();
        } else {
            icon.stop();
            icon.setVisibility(View.GONE);
        }
    }
}
