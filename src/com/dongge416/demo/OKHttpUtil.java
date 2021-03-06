package com.dongge416.demo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpUtil {

	/** 
     * 发起get请求 
     *  
     * @param url 
     * @return 
     */  
    public static String httpGet(String url) {  
        String result = null;  
        OkHttpClient client = new OkHttpClient();  
        Request request = new Request.Builder().url(url).build();  
        try {  
            Response response = client.newCall(request).execute();  
            result = response.body().string();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
    
    /**
     * 下载图片流
     * @param url
     * @return
     */
    public static InputStream httpGetPicInputStream(String url) {  
    		InputStream is = null;
        OkHttpClient client = new OkHttpClient();  
        Request request = new Request.Builder().url(url).build();  
        try {  
        		
            Response response = client.newCall(request).execute();  
            int code = response.code();
            System.out.println("get pic response code:"+code);
            is = response.body().byteStream();
            System.out.println("download pic success");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return is;  
    }  
    
    /** 
     * 发送httppost请求 
     *  
     * @param url 
     * @param data  提交的参数为key=value&key1=value1的形式 
     * @return 
     */  
    public static String httpPost(String url, String data) {  
        String result = null;  
        OkHttpClient httpClient = new OkHttpClient();  
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/html;charset=utf-8"), data);  
        Request request = new Request.Builder().url(url).post(requestBody).build();  
        try {  
            Response response = httpClient.newCall(request).execute();  
            result = response.body().string();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
	
}
