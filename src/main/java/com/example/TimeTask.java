package com.example;

import java.util.TimerTask;

public class TimeTask extends TimerTask {
    public void run() {
        Demo demo=new Demo();
        demo.groupMsg(0, 10006, 930684981L, 2387020215L, "", "定时任务", 0);
    }
}
