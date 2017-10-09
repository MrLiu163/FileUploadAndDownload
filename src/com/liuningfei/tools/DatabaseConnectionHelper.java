package com.liuningfei.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.Statement;

public class DatabaseConnectionHelper {
	// JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useUnicode=true&characterEncoding=utf-8";
//    static final String DB_URL = "jdbc:mysql://192.168.1.28:3306/RUNOOB?useUnicode=true&characterEncoding=utf-8";
//    static final String DB_URL = "jdbc:mysql://192.168.0.103:3306/RUNOOB?useUnicode=true&characterEncoding=utf-8";
    static final String DB_URL = "jdbc:mysql://192.168.1.88:3306/RUNOOB?useUnicode=true&characterEncoding=utf-8";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useUnicode=true&amp;characterEncoding=UTF-8"; // 192.168.1.28
    // 数据库的用户名与密码，需要根据自己的设置 //amp;
    static final String USER = "root";
    static final String PASS = "123456";  
    
    // 执行sql语句 
    static public void executeSqliteOperationWithSqlString(String sqlString) {
    		Connection conn = null;
		Statement stmt = null;
		try {
			// 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对...");
            stmt = conn.createStatement();
            String sql;
            sql = sqlString;
            System.out.println(sql);
            stmt.execute(sql);
//            ResultSet rs = stmt.executeQuery(sql);
//            out.write("{\"statusCode\" : \"0\"}");
            System.out.println(" --------signal-------");
            // 完成后关闭
//            rs.close();
            stmt.close();
            conn.close();
		}catch (SQLException se) {
			// TODO: handle exception
			se.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			
		}	
    }
    // 执行executeQuery查询语句
    static public ResultSet executeQueryOperationWithSqlString(String sqlString) {
    		Connection conn = null;
		Statement stmt = null;
		try {
			// 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对...");
            stmt = conn.createStatement();
            String sql;
            sql = sqlString;
            ResultSet rs = stmt.executeQuery(sql);
            // 完成后关闭
//            stmt.close();
//            conn.close();
            return rs;
		}catch (SQLException se) {
			// TODO: handle exception
			se.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			
		}
		return null;	
    }
}
