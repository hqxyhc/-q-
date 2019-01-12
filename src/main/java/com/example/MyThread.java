package com.example;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyThread extends TimerTask {
    public static void main(String[] args) {
        Timer t = new Timer();
       /* circle(t);*/
        return;
    }

    public   void circle(Timer t) throws ParseException {
        boolean flag=true;
        while (flag) {
                t.schedule(new MyThread(), 1000*30);
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    public void run() {
        Demo demo=new Demo();
        demo.groupMsg(1,2,930684981,2387020215L,null,"定时",0);

    }
}