package com.liuningfei.employeeList;

import java.io.IOException;
import java.io.PrintWriter;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuningfei.tools.DatabaseConnectionHelper;
import com.liuningfei.tools.JsonHandleHelper;
import com.liuningfei.tools.JsonUtil;
import com.liuningfei.tools.JsonHandleHelper;
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
		/*
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
            */
		
		String finalResponseString = ""; 
		ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		try {
			// 展开结果集数据库
			while(rs.next()){
				// 通过字段检索
				int id  = rs.getInt("id");
				String name = rs.getString("name");
//               String url = rs.getString("url");
				String gender = rs.getString("gender");
				String phone = rs.getString("phone");
				Map<String, Object> tempMap = new HashMap<String, Object>(); 
				tempMap.put("id", id);
				tempMap.put("name", name);
				tempMap.put("gender", gender);
				tempMap.put("phone", phone);
//				String tempStr = JsonUtil.map2json(tempMap);
//				mapList.add(tempStr);
				mapList.add(tempMap);
			}
			
			/*
			String listStr = JsonUtil.list2json(mapList);
			Map<String, String> finalMap = new HashMap<String, String>(); 
			finalMap.put("statusCode", "0");
			finalMap.put("message", "查询成功");
			finalMap.put("data", listStr);
			finalResponseString = JsonUtil.map2json(finalMap);
			*/
			finalResponseString = JsonHandleHelper.getResponseJsonStr("0", "查询所有员工成功", mapList);
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
