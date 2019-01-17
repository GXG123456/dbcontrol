package com.jdbc.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlControl {

	private static String url = "jdbc:mysql://10.100.200.18:3306/report?useUnicode=true&amp;characterEncoding=utf-8";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String username = "u_report";
	private static String password = "report123456";
	private static Connection con = null;


	public  void insertTableInfo() throws ClassNotFoundException {

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			String sql = "insert into time_info (flag,overDate) values(0,curdate())";
			PreparedStatement ps = con.prepareStatement(sql);
		     ps.executeUpdate();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}


	public  void modifyInsertTableInfo() throws ClassNotFoundException {

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			String sql = " UPDATE time_info SET `flag` = 1  WHERE overDate =curdate()";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
