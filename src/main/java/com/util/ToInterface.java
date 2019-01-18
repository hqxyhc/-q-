package com.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ToInterface {
    public static final String urlString = "https://c.m.163.com/nc/article/headline/T1348647853363/0-40.html";  //先登录保存cookie
    public static final String urlString2 = "https://v1.hitokoto.cn/?encode=json";
    public String sessionId = "";
    public StringBuilder doGet(String urlStr) throws IOException{
        String key = "";
        String cookieVal = "";
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        cookieVal = "RK=sW5tyeg0sm; ptcz=d57344780baff653b9916f2ab47faef001ccf0d5d4f08c94aad21e6d0d2b32ae; pgv_pvi=7021795328; tvfe_boss_uuid=80a47f42a4cc12b6; pgv_pvid=2654649660; o_cookie=2387020215; pgv_si=s688740352; _qpsvr_localtk=0.2798671852074053; uin=o2387020215; skey=@yg0Kcw2Vr; ptisp=cnc; p_uin=o2387020215; pt4_token=f5MJmvi8G502UYOYnpVRfmBhIoHK711C-Q9rBSjUmq4_; p_skey=waGGLRWxBFZXc*1Mij38DyvaS8O-QYSRJgzDIPJd9JE_";
//        connection.setRequestProperty("Cookie", cookieVal);
        connection.connect(); //到此步只是建立与服务器的tcp连接，并未发送http请求
        /**
         * 设置cookie
         */
        /*if(!sessionId.equals("")){
            connection.setRequestProperty("Cookie", sessionId);
        }
        for(int i=1;(key=connection.getHeaderField(i))!=null;i++){*/

       /* }*/

        //直到getInputStream()方法调用请求才真正发送出去
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line=br.readLine()) != null){
            sb.append(line);
            sb.append("\n");
        }


       /* for(int i=0,len=strs.length;i<len;i++){
            System.out.println(strs[i].toString());
        }*/

        br.close();
        connection.disconnect();
        return sb;
    }

    public void doPost() throws IOException{
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true); //设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
        connection.setDoInput(true); // 设置连接输入流为true
        connection.setRequestMethod("POST"); // 设置请求方式为post
        connection.setUseCaches(false); // post请求缓存设为false
        connection.setInstanceFollowRedirects(true); //// 设置该HttpURLConnection实例是否自动执行重定向
        // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
        // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.connect();

        // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
        DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
        String parm = "storeId=" + URLEncoder.encode("32", "utf-8"); //URLEncoder.encode()方法  为字符串进行编码           
        dataout.writeBytes(parm);
        dataout.flush();
        dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)           
        // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder(); // 用来存储响应数据           
        // 循环读取流,若不到结尾处
        while ((line = bf.readLine()) != null) {
            sb.append(bf.readLine());
        }
        bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
        connection.disconnect(); // 销毁连接
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        ToInterface hcu = new ToInterface();
//        hcu.doGet(urlString);
          JSONObject my= (JSONObject) JSONObject.parse(hcu.doGet(urlString2).toString());
        System.out.println(my.get("hitokoto")+"\n来源："+my.get("from")+"\n"+"时间");
    }
}
