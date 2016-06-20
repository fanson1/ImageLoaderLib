package com.fanhy.cache.impl;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.fanhy.cache.ImgCache;
import com.fanhy.util.MD5Utils;

/**
 * Created by huayong on 2016/6/15.
 */
public class MemeryCache implements ImgCache {
    private LruCache<String, Bitmap> mCache;

    public MemeryCache(){
        initCache();
    }

    private void initCache() {
        final int maxMemery = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemery / 4;
        mCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bmp) {
        mCache.put(MD5Utils.GetMD5Code(url), bmp);
    }

    @Override
    public Bitmap get(String url) {
        return mCache.get(MD5Utils.GetMD5Code(url));
    }
}
