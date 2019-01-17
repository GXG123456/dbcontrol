package com.jdbc.control;

import java.util.Date;

/*
这个是多线程的执行定时任务的方式去执行代码，当数据库的状态为1的时候停止执行，但是在oozie里面执行的时候，设计主线程和分线程的概念
所以主线程结束了分线程还没开始执行。任务失败
 */

public class Helloworld {

    public static void main(String[] args) {
        final String tablename = "loan_base";
        final long timeInterval = 1000;
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    System.out.println("方法一：循环调用 !!  时间=" + new Date());
                    TimedLoadData tdl = new TimedLoadData();
                    int flag = 0;
                    try {
                        flag = tdl.getDateAndFlag(tablename);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println(flag);
                    if (flag!=0){
                        MysqlControl mc =  new MysqlControl();
                        try {
                            mc.insertTableInfo();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                    }
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

}
