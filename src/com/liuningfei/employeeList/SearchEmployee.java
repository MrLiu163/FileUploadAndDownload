package com.liuningfei.employeeList;

import java.io.IOException;
import java.io.PrintWriter;


import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class SearchEmployee
 */
@WebServlet("/SearchEmployee")
public class SearchEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";
    static final String DB_URL = "jdbc:mysql://192.168.1.88:3306/RUNOOB?useUnicode=true&characterEncoding=utf-8";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
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
//            sql = "SELECT id, name, url FROM websites";
            sql = "SELECT id, name, gender, phone FROM employee";
            ResultSet rs = stmt.executeQuery(sql);
        
            String finalResponseString = "{\"list\":" + "[";
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
//                String url = rs.getString("url");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
//                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
//                finalResponseString = finalResponseString + "{\"id\":\"" + id + "\",\n" + 
//                "\"name\":\"" + name + "\",\n" + "\"url\":\"" + url + "\"\n},";
                finalResponseString = finalResponseString + "{\"id\":\"" + id + "\",\n" + 
                      "\"name\":\"" + name + "\",\n" + "\"gender\":\"" + gender + "\",\n" + "\"phone\":\"" + phone + "\"\n},";
            }
            finalResponseString = finalResponseString + "]}";
            System.out.println(finalResponseString);
            out.write(finalResponseString);
//            out.write("{\"name\":\"菜鸟教程标识\"}");
            // 完成后关闭
            rs.close();
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
		
//		out.println("{\"name\" : \"This is test signal\"}");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
