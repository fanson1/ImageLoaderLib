package com.fanhy.cache;

import android.graphics.Bitmap;

/**
 * Created by huayong on 2016/6/15.
 * 图片缓存根接口
 */
public interface ImgCache {
    /**
     * 存图片
     *
     * @param url 把url作为key
     * @param bmp 把图片作为value
     */
    void put(String url, Bitmap bmp);

    /**
     * 根据url取图片
     * @param url 根据指定的url这个取图片
     * */
    Bitmap get(String url);
}
