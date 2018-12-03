package com.qian.ess.bean.socket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by AA on 2017/3/23.
 */
public class FileInfo implements Serializable {

    /**
     * 文件传输结果：1 成功  -1 失败
     */
    public static final byte FLAG_SUCCESS = (byte) 0x01;
    public static final byte FLAG_FAILURE = (byte) 0x00;

    /**
     * 分包大小
     */
    public static final int SUB_PACKAGES_LENGTH = 1024 * 1024;

    /**
     * 文件路径
     */
    private String filePath;

    /***
     * 文件名
     */
    private String fileName;

    /**
     * 文件大小
     */
    private long size;

    /**
     * 传输进度
     */
    private long progress;

    /**
     * 正在接收的包序号
     */
    private int packagesNo;

    /**
     * 分包个数
     */
    private int subPackagesTotal;

    /**
     * 识别码 ：yyyyMMddHHmmssSSS
     */
    private long identifier;

    /**
     * 每个包的大小
     * <p>
     * 默认的 1024 * 1024
     */
    private int subPackagesLength = SUB_PACKAGES_LENGTH;

    /**
     * 当前接收到的包的大小
     */
    private int receiveSubPackagesLength = 0;

    /**
     * 文件传送结果
     */
    private byte result;

    //正在接收的文件输出流
    FileOutputStream saveFileOutputStream;

    public FileInfo(String filePath, long size) {
        this.filePath = filePath;
        this.size = size;
    }

    public FileInfo() {

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public static String toJsonStr(FileInfo fileInfo) {
        return new Gson().toJson(fileInfo);
    }

    public static String toJsonStr(List<FileInfo> fileInfoList) {
        return new Gson().toJson(fileInfoList);
    }

    public static FileInfo toObject(String jsonStr) {
        return new Gson().fromJson(jsonStr, FileInfo.class);
    }

    public static List<FileInfo> toObjectList(String jsonStr) {
        return new Gson().fromJson(jsonStr, new TypeToken<List<FileInfo>>() {
        }.getType());
    }

    public int getPackagesNo() {
        return packagesNo;
    }

    public void setPackagesNo(int packagesNo) {
        this.packagesNo = packagesNo;
    }

    public int getSubPackagesTotal() {
        return subPackagesTotal;
    }

    public void setSubPackagesTotal(int subPackagesTotal) {
        this.subPackagesTotal = subPackagesTotal;
    }

    public int getSubPackagesLength() {
        return subPackagesLength;
    }

    public void setSubPackagesLength(int subPackagesLength) {
        this.subPackagesLength = subPackagesLength;
    }

    public int getReceiveSubPackagesLength() {
        return receiveSubPackagesLength;
    }

    public void setReceiveSubPackagesLength(int receiveSubPackagesLength) {
        this.receiveSubPackagesLength = receiveSubPackagesLength;
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public FileOutputStream getSaveFileOutputStream() {
        return saveFileOutputStream;
    }

    public void setSaveFileOutputStream(FileOutputStream saveFileOutputStream) {
        this.saveFileOutputStream = saveFileOutputStream;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", size=" + size +
                ", progress=" + progress +
                ", packagesNo=" + packagesNo +
                ", subPackagesTotal=" + subPackagesTotal +
                ", identifier='" + identifier + '\'' +
                ", result=" + result +
                '}';
    }
}
