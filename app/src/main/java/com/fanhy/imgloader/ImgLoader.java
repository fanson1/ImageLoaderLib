package com.fanhy.imgloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.fanhy.cache.ImgCache;
import com.fanhy.cache.impl.MemeryCache;

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
        imageView.setTag(url);
        Future<?> submit = executorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp = downloadImg(url);
                if (bmp == null) {
                    return ;
                }
                if (imageView.getTag().equals(url)) {
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
