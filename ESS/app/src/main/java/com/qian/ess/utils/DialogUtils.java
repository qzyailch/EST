package com.qian.ess.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.widget.EditText;

import com.qian.ess.R;

/**
 * Created by Administrator on 2016/9/21 0021.
 */
public class DialogUtils {

    public static void showAlertDialog(String message, Context context, final IDialogCallBack callBack) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context)
                .setTitle(R.string.ess_tip)
                .setMessage(message)
                .setPositiveButton(R.string.ess_btn_sure,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                callBack.onConfirm("true");
                            }
                        })
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode,
                                         KeyEvent event) {
                        if (KeyEvent.KEYCODE_BACK == keyCode) {
                            callBack.onCancel();
                        }
                        return false;
                    }
                });
        alert.show();
    }

    public static void showConfirmDialog(String message, Context context, final IDialogCallBack callBack) {
        AlertDialog.Builder confirm = new AlertDialog.Builder(context)
                .setTitle(R.string.ess_please_sure)
                .setMessage(message)
                .setPositiveButton(R.string.ess_btn_sure,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                callBack.onConfirm("true");
                            }
                        })
                .setNegativeButton(R.string.ess_btn_cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                callBack.onCancel();
                            }
                        })
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode,
                                         KeyEvent event) {
                        if (KeyEvent.KEYCODE_BACK == keyCode) {
                            callBack.onCancel();
                        }
                        return false;
                    }
                });
        confirm.show();
    }

    public static void showPromptDialog(String title, Context context, final IDialogCallBack callBack) {
        final EditText et = new EditText(context);
        AlertDialog.Builder prompt = new AlertDialog.Builder(context)
                .setTitle(title)
                .setView(et)
                .setPositiveButton(R.string.ess_btn_sure,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                callBack.onConfirm(et.getText().toString());
                            }
                        })
                .setNeutralButton(R.string.ess_btn_cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                callBack.onCancel();
                            }
                        })
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode,
                                         KeyEvent event) {
                        if (KeyEvent.KEYCODE_BACK == keyCode) {
                            callBack.onCancel();
                        }
                        return false;
                    }
                });
        prompt.show();
    }

    public interface IDialogCallBack {
        void onConfirm(String data);

        void onCancel();
    }
}
