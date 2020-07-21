package com.nebula.nebulautils.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 工具类
 * </p>
 * @author: zhu.chen
 * @date: 2019-12-20
 */
public class NebulaUtils {

    /**
     * <h>
     * 将文件转换为十六进制的md5值
     * </h>
     * @param filePath
     * @return String
     */
    public static String calculateMd5(String filePath) {
        try {
            return DigestUtils.md5Hex(new FileInputStream(filePath));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * <h>
     * 获取文件的最后修改时间
     * </h>
     * @param filePath
     * @return
     */
    public static String acquireFileLastModifiedTime(String filePath) {
        BasicFileAttributes attributes = null;
        try {
            attributes = Files.readAttributes(new File(filePath).toPath(), BasicFileAttributes.class);
        } catch (Exception e) {
            return null;
        }
        // 获取文件的创建时间
        //attributes.creationTime().toMillis()
        return convertTime(new Date(attributes.lastModifiedTime().toMillis()));
    }

    protected static String convertTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {

    }

}
