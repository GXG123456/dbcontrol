package com.jdbc.control;

import java.util.Date;

import java.util.concurrent.CountDownLatch;

/*
这个方法是只有一个线程执行整个任务，在oozie当中可以不断的循环调用程序，当数据库的状态为1的时候任务结束执行
 */

public class BeginControl {

    public static void main(String[] args) throws InterruptedException {
        int threadNumber = 1;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNumber);
        final String tablename = "loan_base";
        final long timeInterval = 1000;
        new Thread() {
            public void run() {

                while (true) {
                    System.out.println("方法：循环调用 !!  时间=" + new Date());

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
        }.start();
        countDownLatch.await();
        System.out.println("main thread finished!!");
    }

}