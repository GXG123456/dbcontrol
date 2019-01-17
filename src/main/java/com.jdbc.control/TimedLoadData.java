package com.jdbc.control;

import java.sql.*;

public class TimedLoadData {

	private static String url = "jdbc:oracle:thin:@172.16.230.11:1521:zdxdb";
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String username = "frontbank";
	private static String password = "sdff23s";
	private static Connection con = null;


	public  int getDateAndFlag(String tablename) throws ClassNotFoundException {
		int flag = 0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			String sql = "select flag from JOB_TABLE_RECORD where table_name='"+tablename+"' and end_time  BETWEEN  trunc(sysdate) and sysdate ";
		//	String sql = "select is_exe from operation_record where id=1 ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){

				flag  =	Integer.parseInt(rs.getString("flag"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return flag ;
	}
}
