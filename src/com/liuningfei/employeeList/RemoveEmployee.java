package com.liuningfei.employeeList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuningfei.tools.DatabaseConnectionHelper;
import com.liuningfei.tools.JsonUtil;

/**
 * Servlet implementation class RemoveEmployee
 */
@WebServlet("/RemoveEmployee")
public class RemoveEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
	
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
		 
		// 从数据库移除
		String sql;
        String name = request.getParameter("name");
        sql = "delete from employee where name = \'" + name + "\'";
		DatabaseConnectionHelper.executeSqliteOperationWithSqlString(sql);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("statusCode", "0");
		jsonMap.put("message", "移除员工成功！");
		jsonMap.put("data", "{}");
		out.write(JsonUtil.map2json(jsonMap));		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
