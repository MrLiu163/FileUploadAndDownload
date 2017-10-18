package com.liuningfei.employeeList;

import java.io.IOException;
import java.io.PrintWriter;


import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuningfei.tools.DatabaseConnectionHelper;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class SearchEmployee
 */
@WebServlet("/SearchEmployee")
public class SearchEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
		
		String sql = "SELECT id, name, gender, phone FROM employee";
		ResultSet rs = DatabaseConnectionHelper.executeQueryOperationWithSqlString(sql);
		String finalResponseString = "{\"list\":" + "[";
        try {
        	// 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
//                String url = rs.getString("url");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                // 输出数据
//                finalResponseString = finalResponseString + "{\"id\":\"" + id + "\",\n" + 
//                "\"name\":\"" + name + "\",\n" + "\"url\":\"" + url + "\"\n},";
                finalResponseString = finalResponseString + "{\"id\":" + id + ",\n" + 
                      "\"name\":\"" + name + "\",\n" + "\"gender\":\"" + gender + "\",\n" + "\"phone\":\"" + phone + "\"\n},";
            }
            finalResponseString = finalResponseString + "]}";
            out.write(finalResponseString);            
            // 完成后关闭
            Connection conn = rs.getStatement().getConnection();
			Statement stmt = rs.getStatement();
			rs.close();
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
