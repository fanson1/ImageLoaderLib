package com.fanhy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.fanhy.cache.impl.DoubleCache;
import com.fanhy.imgloader.ImgLoader;
import com.fanhy.imgloader.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> datas = new ArrayList<>();
    private String imgBaseUrl = "http://123.56.145.151:8080/JiuPinHui/Image/";
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        for (int i = 1; i < 30; i++) {
            datas.add(imgBaseUrl + i + ".jpg");
        }
        myAdapter = new MyAdapter(datas, MainActivity.this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(myAdapter);
    }

}
