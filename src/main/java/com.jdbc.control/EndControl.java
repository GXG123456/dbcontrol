package com.jdbc.control;

/*
通过jdbc的方式将数据库当中指定的记录做更新操作
 */


public class EndControl {

    public static void main(String[] args) throws ClassNotFoundException {

        MysqlControl mc =  new MysqlControl();
        mc.modifyInsertTableInfo();
        System.out.println("状态更新成功");

    }
}
