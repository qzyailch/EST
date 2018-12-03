package com.qian.ess.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Base64;
import android.util.DisplayMetrics;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class BitmapUtils {
    private static final String dirName = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator;

    /**
     * 图片压缩
     */
    public static Bitmap compress(Activity context, String srcPath) {
        Bitmap bitmap = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            DisplayMetrics dm = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(dm);

            float hh = dm.heightPixels;
            float ww = dm.widthPixels;
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            bitmap = BitmapFactory.decodeFile(srcPath, opts);
            opts.inJustDecodeBounds = false;
            int w = opts.outWidth;
            int h = opts.outHeight;
            int size = 0;
            if (w <= ww && h <= hh) {
                size = 1;
            } else {
                double scale = w >= h ? w / ww : h / hh;
                double log = Math.log(scale) / Math.log(2);
                double logCeil = Math.ceil(log);
                size = (int) Math.pow(2, logCeil);
            }
            opts.inSampleSize = size;
            bitmap = BitmapFactory.decodeFile(srcPath, opts);
            int quality = 100;
            bitmap.compress(srcPath.endsWith(".jpg") ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, quality, baos);
            System.out.println(baos.toByteArray().length);
            while (baos.toByteArray().length > 45 * 1024) {
                baos.reset();
                bitmap.compress(srcPath.endsWith(".jpg") ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, quality, baos);
                quality -= 20;
                if (quality == 0) {
                    break;
                }
                System.out.println(baos.toByteArray().length);
            }
            baos.writeTo(new FileOutputStream(srcPath));
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.show("图片处理出错，请重试");
        } finally {
            try {
                baos.flush();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }

    /**
     * 图片压缩
     *
     * @param srcPath 原图片路径
     */
    public static Bitmap compressReturnBitmap(Activity context, String srcPath) {
        Bitmap bitmap = null;
        try {
            DisplayMetrics dm = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(dm);

            float hh = dm.heightPixels;
            float ww = dm.widthPixels;
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            bitmap = BitmapFactory.decodeFile(srcPath, opts);
            opts.inJustDecodeBounds = false;
            int w = opts.outWidth;
            int h = opts.outHeight;
            int size = 0;
            if (w <= ww && h <= hh) {
                size = 1;
            } else {
                double scale = w >= h ? w / ww : h / hh;
                double log = Math.log(scale) / Math.log(2);
                double logCeil = Math.ceil(log);
                size = (int) Math.pow(2, logCeil);
            }
            opts.inSampleSize = size;
            bitmap = BitmapFactory.decodeFile(srcPath, opts);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int quality = 100;
            bitmap.compress(srcPath.endsWith(".jpg") ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, quality, baos);
            Logger.i("compress length:", "==============" + baos.toByteArray().length);
            while (baos.toByteArray().length > 45 * 1024) {
                baos.reset();
                bitmap.compress(srcPath.endsWith(".jpg") ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, quality, baos);
                quality -= 20;
                if (quality == 0) {
                    break;
                }
                Logger.i("compress length:", "==============" + baos.toByteArray().length);
            }
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * 图片压缩
     */
    public static Bitmap compressToPath(Activity context, String srcPath, String toPath) {
        Bitmap bitmap = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            DisplayMetrics dm = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(dm);

            float hh = dm.heightPixels;
            float ww = dm.widthPixels;
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            bitmap = BitmapFactory.decodeFile(srcPath, opts);
            opts.inJustDecodeBounds = false;
            int w = opts.outWidth;
            int h = opts.outHeight;
            int size = 0;
            if (w <= ww && h <= hh) {
                size = 1;
            } else {
                double scale = w >= h ? w / ww : h / hh;
                double log = Math.log(scale) / Math.log(2);
                double logCeil = Math.ceil(log);
                size = (int) Math.pow(2, logCeil);
            }
            opts.inSampleSize = size;
            bitmap = BitmapFactory.decodeFile(srcPath, opts);
            int quality = 100;
            bitmap.compress(srcPath.endsWith(".jpg") ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, quality, baos);
            System.out.println(baos.toByteArray().length);
            while (baos.toByteArray().length > 45 * 1024) {
                baos.reset();
                bitmap.compress(srcPath.endsWith(".jpg") ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, quality, baos);
                quality -= 20;
                if (quality == 0) {
                    break;
                }
                System.out.println(baos.toByteArray().length);
            }
            baos.writeTo(new FileOutputStream(toPath));
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.show("图片处理出错，请重试");
        } finally {
            try {
                baos.flush();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }

    /**
     * 图片转Base64
     *
     * @param imgPath 图片路径
     * @return
     */
    public static String imgPathToBase64(String imgPath) {
        Bitmap bitmap = null;
        if (imgPath != null && imgPath.length() > 0) {
            bitmap = readBitmap(imgPath);
        } else {
            ToastUtils.show("图片不存在");
            return null;
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(imgPath.endsWith(".jpg") ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, 100, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 图片转Base64
     *
     * @param bitmap 图片bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        if (bitmap == null) {
            ToastUtils.show("图片不存在");
            return null;
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param base64Data
     * @param imgName
     */
    public static void base64ToBitmap(String base64Data, String imgName) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        File myCaptureFile = new File(dirName + imgName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myCaptureFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean isTu = bitmap.compress(imgName.endsWith(".jpg") ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, 100, fos);
        if (isTu) {
            // fos.notifyAll();
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param base64Data
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes;
        //将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            bytes = Base64.decode(base64Data, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static Bitmap readBitmap(String imgPath) {
        try {
            return BitmapFactory.decodeFile(imgPath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }

    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return degree;
        }
        return degree;
    }

    /**
     * 旋转图片，使图片保持正确的方向。
     *
     * @param path 图片绝对路径
     * @return Bitmap 旋转后的图片
     */
    public static Bitmap rotateBitmap(String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        int degrees = readPictureDegree(path);
        if (null == path || bitmap == null || degrees == 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return bmp;
    }

}
