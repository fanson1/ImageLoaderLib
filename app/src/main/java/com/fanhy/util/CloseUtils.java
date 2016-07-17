package com.fanhy.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by huayong on 2016/6/20.
 * 关闭操作封装工具类
 */
public class CloseUtils {
    public static void closeQuietly(Closeable closeable){
        if (closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
