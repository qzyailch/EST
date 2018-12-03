package com.qian.ess.utils;

import android.graphics.Bitmap;

import com.qian.ess.common.Constants;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Administrator on 2018/8/6 0006.
 */

public class FileUtils {
    //文件大小
    private static final long KB = 2 << 9;
    private static final long MB = 2 << 19;
    private static final long GB = 2 << 29;

    //日期格式化
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    //当前路径
    private static String mNowPath;
    private static Stack<String> mNowPathStack;

    /**
     * 获得指定文件的大小
     *
     * @param file
     * @return
     */
    public static String getFileSize(File file) {
        if (file.isFile()) {
            long fileLength = file.length();
            if (fileLength < KB) {
                return fileLength + "B";
            } else if (fileLength < MB) {
                return String.format(Locale.getDefault(), "%.2fKB", fileLength / (double) KB);
            } else if (fileLength < GB) {
                return String.format(Locale.getDefault(), "%.2fMB", fileLength / (double) MB);
            } else {
                return String.format(Locale.getDefault(), "%.2fGB", fileLength / (double) GB);
            }
        }
        return null;
    }

    /**
     * 获得文件最近修改的时间
     *
     * @param file
     * @return
     */
    public static String getFileDate(File file) {
        return dateFormat.format(new Date(file.lastModified()));
    }

    /**
     * 获得当前所在的路径栈的字符串表示
     *
     * @return
     */
    public static String getNowStackPathString(Stack<String> nowPathStack) {
        mNowPathStack = nowPathStack;
        String result = "";
        Stack<String> temp = new Stack<>();
        temp.addAll(nowPathStack);
        while (temp.size() != 0) {
            result = temp.pop() + result;
        }
        mNowPath = result;
        return result;
    }

    /**
     * 获得当前路径
     *
     * @return
     */
    public static String getNowPath() {
        return mNowPath;
    }

    /**
     * 返回上级目录
     *
     * @return
     */
    public static String returnToParentDir() {
        mNowPathStack.pop();
        return mNowPath;
    }

    /**
     * 过滤隐藏文件,并按字母序排序文件目录和文件
     *
     * @param path
     * @return
     */
    public static File[] filterSortFileByName(String path, boolean isHideFile) {

        File[] fileArray = null;
        if (isHideFile) {
            //不显示隐藏文件
            fileArray = new File(path).listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    //过滤掉隐藏文件
                    return !file.getName().startsWith(".");
                }
            });
        } else {
            //显示隐藏文件
            fileArray = new File(path).listFiles();
        }

        //文件排序
        Arrays.sort(fileArray, new Comparator<File>() {
            @Override
            public int compare(File file, File t1) {
                if (file.isDirectory() && t1.isFile()) {
                    return -1;
                } else if (file.isFile() && t1.isDirectory()) {
                    return 1;
                }
                return file.getName().compareToIgnoreCase(t1.getName());
            }
        });
        return fileArray;
    }


    /**
     * 过滤隐藏文件,并按文件大小排序
     *
     * @param path
     * @param desc
     * @return
     */
    public static File[] filterSortFileBySize(String path, final boolean desc) {

        //不显示隐藏文件
        File[] fileArray = new File(path).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                //过滤掉隐藏文件
                return !file.getName().startsWith(".");
            }
        });
        //文件排序
        Arrays.sort(fileArray, new Comparator<File>() {
            @Override
            public int compare(File file, File t1) {
                if (file.isDirectory() && t1.isFile()) {
                    return -1;
                } else if (file.isFile() && t1.isDirectory()) {
                    return 1;
                } else if (file.isFile() && t1.isFile()) {
                    if (desc) {
                        return file.length() - t1.length() > 0 ? -1 : 1;
                    } else {
                        return file.length() - t1.length() > 0 ? 1 : -1;
                    }
                }
                return file.getName().compareToIgnoreCase(t1.getName());
            }
        });
        return fileArray;
    }

    /**
     * 按文件修改时间排序
     *
     * @param path
     * @return
     */
    public static File[] filterSortFileByLastModifiedTime(String path) {

        //不显示隐藏文件
        File[] fileArray = new File(path).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                //过滤掉隐藏文件
                return !file.getName().startsWith(".");
            }
        });
        //文件排序
        Arrays.sort(fileArray, new Comparator<File>() {
            @Override
            public int compare(File file, File t1) {
                if (file.isDirectory() && t1.isFile()) {
                    return -1;
                } else if (file.isFile() && t1.isDirectory()) {
                    return 1;
                } else if (file.isFile() && t1.isFile() || (file.isDirectory() && t1.isDirectory())) {
                    //最新的文件排在最前面
                    return file.lastModified() - t1.lastModified() > 0 ? -1 : 1;
                }
                return file.getName().compareToIgnoreCase(t1.getName());
            }
        });
        return fileArray;
    }

    /**
     * 获取指定文件大小(单位：字节)
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static int getFileSizeForInt(File file) {
        if (file == null) {
            return 0;
        }
        int size = 0;
        try {
            if (file.exists()) {
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                size = fis.available();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 获取文件大小
     */
    public static long getFileSizeForLong(File file) {
        FileChannel fc = null;
        try {
            if (file.exists() && file.isFile()) {
                FileInputStream fis = new FileInputStream(file);
                fc = fis.getChannel();

                return fc.size();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fc) {
                try {
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return 0;
    }

    /**
     * 文件转byte[]
     */
    public static byte[] File2byte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * byte[]转文件
     */
    public static void byte2File(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据路径获取文件名
     */
    public static String getFileName(String filePath) {
        int start = filePath.lastIndexOf("/");
        //int end = filePath.lastIndexOf(".");
        if (start != -1) {
            return filePath.substring(start + 1, filePath.length());
        } else {
            return "";
        }
    }

    //保存文件
    public static boolean saveImage(Bitmap bitmap, String filePath) {
        try {
            File f = new File(filePath);
            if (!f.getParentFile().getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            if (f.exists()) {
                f.delete();
            }

            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Logger.i("mImageUrl===", e.toString());
            return false;
        }
    }

    /**
     * 获取扫描文件类型
     */
    public static Map<String, Set<String>> getCategorySuffix(List<String> types) {
        //每种类型的文件包含的后缀
        Map<String, Set<String>> CATEGORY_SUFFIX = new HashMap<>();

        Set<String> set;
        if (types.contains(Constants.FILE_CONSTANTS.FILE_TYPE_VIDEO)) {
            set = new HashSet<>();
            set.add("mp4");
            set.add("avi");
            set.add("wmv");
            set.add("flv");
            CATEGORY_SUFFIX.put(Constants.FILE_CONSTANTS.FILE_TYPE_VIDEO, set);
        }

        if (types.contains(Constants.FILE_CONSTANTS.FILE_TYPE_DOCUMENT)) {
            set = new HashSet<>();
            set.add("txt");
            set.add("pdf");
            set.add("doc");
            set.add("docx");
            set.add("xls");
            set.add("xlsx");
            CATEGORY_SUFFIX.put(Constants.FILE_CONSTANTS.FILE_TYPE_DOCUMENT, set);
        }

        if (types.contains(Constants.FILE_CONSTANTS.FILE_TYPE_PICTURE)) {
            set = new HashSet<>();
            set.add("jpg");
            set.add("jpeg");
            set.add("png");
            set.add("bmp");
            set.add("gif");
            CATEGORY_SUFFIX.put(Constants.FILE_CONSTANTS.FILE_TYPE_PICTURE, set);
        }

        if (types.contains(Constants.FILE_CONSTANTS.FILE_TYPE_MUSIC)) {
            set = new HashSet<>();
            set.add("mp3");
            set.add("ogg");
            CATEGORY_SUFFIX.put(Constants.FILE_CONSTANTS.FILE_TYPE_MUSIC, set);
        }

        if (types.contains(Constants.FILE_CONSTANTS.FILE_TYPE_APK)) {
            set = new HashSet<>();
            set.add("apk");
            CATEGORY_SUFFIX.put(Constants.FILE_CONSTANTS.FILE_TYPE_APK, set);
        }

        if (types.contains(Constants.FILE_CONSTANTS.FILE_TYPE_ZIP)) {
            set = new HashSet<>();
            set.add("zip");
            set.add("rar");
            set.add("7z");
            CATEGORY_SUFFIX.put(Constants.FILE_CONSTANTS.FILE_TYPE_ZIP, set);
        }

        return CATEGORY_SUFFIX;
    }
}
