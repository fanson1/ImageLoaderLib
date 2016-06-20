package com.fanhy.cache;

import android.graphics.Bitmap;

/**
 * Created by huayong on 2016/6/15.
 */
public interface ImgCache {
    void put(String url, Bitmap bmp);
    Bitmap get(String url);
}
