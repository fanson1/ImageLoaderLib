package com.fanhy.cache.impl;

import android.graphics.Bitmap;

import com.fanhy.cache.ImgCache;

/**
 * Created by huayong on 2016/6/20.
 */
public class DoubleCache implements ImgCache{
    ImgCache memeryCache = new MemeryCache();
    ImgCache diskCache = new DiskCache();
    @Override
    public void put(String url, Bitmap bmp) {
        memeryCache.put(url, bmp);
        diskCache.put(url, bmp);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bmp = memeryCache.get(url);
        if(bmp == null){
            bmp = diskCache.get(url);
        }
        return bmp;
    }
}
