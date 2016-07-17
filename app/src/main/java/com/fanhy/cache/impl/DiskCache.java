package com.fanhy.cache.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.fanhy.cache.ImgCache;
import com.fanhy.util.CloseUtils;
import com.fanhy.util.MD5Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by huayong on 2016/6/15.
 * 外部存储中的缓存机制
 */
public class DiskCache implements ImgCache{
    // 缓存路径
    private String cacheDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DCIM).getPath()+"/ImgCache/";

    public DiskCache(){
        ensureDirExists();
    }

    public DiskCache(String cacheDir) {
        this.cacheDir = cacheDir;
        ensureDirExists();
    }

    /**
     * 设置外部存储缓存路径
     */
    public void setCacheDir(String cacheDir) {
        this.cacheDir = cacheDir;
        ensureDirExists();
    }

    // 确保缓存路径真实存在
    private void ensureDirExists() {
        File dir = new File(cacheDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    public void put(String url, Bitmap bmp) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(cacheDir + MD5Utils.GetMD5Code(url));
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeQuietly(out);
        }
    }

    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir + MD5Utils.GetMD5Code(url));
    }
}
