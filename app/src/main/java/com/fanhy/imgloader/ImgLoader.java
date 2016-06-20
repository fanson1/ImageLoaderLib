package com.fanhy.imgloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.fanhy.cache.ImgCache;
import com.fanhy.cache.impl.MemeryCache;
import com.fanhy.util.MD5Utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by huayong on 2016/6/15.
 */
public class ImgLoader {
    // 默认使用内部缓存
    private ImgCache mCache = new MemeryCache();
    // 线程池，将cpu数量作为线程数量
    private ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());
    /**
     * 单例模式
     */
    // 声明ImgLoader对象
    private static ImgLoader imgLoader;

    // 私有化构造器，防止外部创建对象
    private ImgLoader() {
    }

    // 提供访问ImgLoader对象的方式
    public static ImgLoader getImgLoader() {
        if (imgLoader == null) {
            synchronized (ImgLoader.class) {
                if (imgLoader == null) {
                    imgLoader = new ImgLoader();
                }
            }
        }
        return imgLoader;
    }

    /**
     * 设置缓存方式
     */
    public void setImgCache(ImgCache imgCache){
        mCache = imgCache;
    }

    public void loadImg(String url, ImageView imageView){
        Bitmap bmp = mCache.get(url);
        if(bmp != null){
            imageView.setImageBitmap(bmp);
            return ;
        }

        // 如果没有图片缓存，就提交线程池下载
        submitLoadRequest(url, imageView);
    }

    private void submitLoadRequest(final String url, final ImageView imageView) {
        imageView.setTag(MD5Utils.GetMD5Code(url));
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp = downloadImg(url);
                if (bmp == null) {
                    return ;
                }
                if (imageView.getTag().equals(MD5Utils.GetMD5Code(url))) {
                    imageView.setImageBitmap(bmp);
                }
                mCache.put(url, bmp);// 存入缓存
            }

        });
    }
    private  Bitmap downloadImg(String url) {
        Bitmap bmp = null;
        try {
            URL urls = new URL(url);
            final HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
            bmp = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }
}
