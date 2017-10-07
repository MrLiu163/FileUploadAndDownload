package com.liuningfei.employeeList;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveEmployee
 */
@WebServlet("/RemoveEmployee")
public class RemoveEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
	// JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useUnicode=true&characterEncoding=utf-8";
    static final String DB_URL = "jdbc:mysql://192.168.1.88:3306/RUNOOB?useUnicode=true&characterEncoding=utf-8";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useUnicode=true&amp;characterEncoding=UTF-8";
    // 数据库的用户名与密码，需要根据自己的设置 //amp;
    static final String USER = "root";
    static final String PASS = "123456";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveEmployee() {
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
		request.setCharacterEncoding("UTF-8"); 
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
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
//            sql = "SELECT id, name, gender, phone FROM employee";
//            sql = "insert into employee(name, gender)values('1234test12', 'www.360.cn12')";
            sql = "delete from employee where name = \'" + name + "\'";
            System.out.println(sql);
            stmt.execute(sql);
//            ResultSet rs = stmt.executeQuery(sql);
        
            out.write("{\"statusCode\" : \"0\"}");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
