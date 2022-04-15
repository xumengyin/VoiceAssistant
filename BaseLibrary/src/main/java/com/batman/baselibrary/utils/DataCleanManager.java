package com.batman.baselibrary.utils;

import android.content.Context;
import android.os.Environment;

import com.batman.utils.Utils;

import java.io.File;
import java.math.BigDecimal;

/**
 * 数据删除工具类
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @version 创建时间：2014年10月27日 上午10:18:22
 */
public class DataCleanManager {

    /**
     * 清除本应用内部缓存
     * (/data/data/com.xxx.xxx/cache)
     *
     * @param context
     */
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
        //deleteFilesByDirectory(context.getFilesDir());
    }

    /**
     * 清楚本应用所有数据库
     * (/data/data/com.xxx.xxx/databases)
     *
     * @param context
     */
    public static void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/databases"));
    }

    /**
     * 清除本应用SharedPreference
     * (/data/data/com.xxx.xxx/shared_prefs)
     *
     * @param context
     */
    public static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/shared_prefs"));
    }

    /**
     * 按名字清除本应用数据库
     *
     * @param context
     * @param dbName
     */
    public static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    /**
     * 清除/data/data/com.xxx.xxx/files下的内容
     *
     * @param context
     */
    public static void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }

    /**
     * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     *
     * @param context
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /**
     * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
     *
     * @param filePath
     */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /**
     * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
     *
     * @param filePath
     */
    public static void cleanCustomCache(File file) {
        deleteFilesByDirectory(file);
    }

    /**
     * 清除本应用所有的数据
     *
     * @param context
     * @param filepath
     */
    public static void cleanApplicationData(Context context, String... filepath) {
        cleanInternalCache(context);
        cleanExternalCache(context);
//        cleanDatabases(context);
//        cleanSharedPreference(context);
//        cleanFiles(context);
//        for (String filePath : filepath) {
//            cleanCustomCache(filePath);
//        }
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     *
     * @param directory
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File child : directory.listFiles()) {
                if (child.isDirectory()) {
                    deleteFilesByDirectory(child);
                }
                child.delete();
            }
        }
    }

    /**
     * 获取缓存值
     */
    public static String getTotalCacheSize(Context context) {

        try {
            long cacheSize = getFolderSize(Utils.getApp().getCacheDir());
//            cacheSize += getFolderSize(Utils.getApp().getFilesDir());

            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                cacheSize += getFolderSize(Utils.getApp().getExternalCacheDir());
            }
            return getFormatSize(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
            return "0MB";
        }
    }

    /**
     * 获取文件
     */
    public static long getFolderSize(File file) {
        long size = 0;
        if (file != null) {
            File[] fileList = file.listFiles();
            if (fileList != null && fileList.length > 0) {
                for (int i = 0; i < fileList.length; i++) {
                    // 如果下面还有文件
                    if (fileList[i].isDirectory()) {
                        size = size + getFolderSize(fileList[i]);
                    } else {
                        size = size + fileList[i].length();
                    }
                }
            }
        }
        return size;
    }

    /**
     * 格式化单位
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        double megaByte = kiloByte / 1024;
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = BigDecimal.valueOf(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
}
