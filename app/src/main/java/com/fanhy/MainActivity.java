package com.fanhy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.fanhy.cache.impl.DoubleCache;
import com.fanhy.imgloader.ImgLoader;
import com.fanhy.imgloader.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        ImgLoader loader = new ImgLoader();
        loader.setImgCache(new DoubleCache());
        loader.loadImg("http://123.56.145.151:8080/JiuPinHui/Image/10.jpg", iv);
    }
}
