package top.suvvm.nilmusic.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @ClassName: DattaUtils
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/31 21:02
 */
public class DataUtils {

    public static StringBuilder stringBuilder;

    // 读取资源文件中的json
    @Deprecated
    public static String getJsonFromAssets(Context context, String fileName) {
        // 用StringBuilder存放读取出的数据
        stringBuilder = new StringBuilder();

        // AssetManager Open指定资源文件位InputStream
        AssetManager assetManager = context.getAssets();



        try {
            InputStream inputStream = assetManager.open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            // 用bufferedReader速度比Scanner快些
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            // 循环读取行
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String getJsonFromUrl(final String urlPath) {
        stringBuilder = new StringBuilder();
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {

                URL url = null;

                try {
                    url = new URL(urlPath);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = conn.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    // 用bufferedReader速度比Scanner快些
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line;
                    // 循环读取行
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
