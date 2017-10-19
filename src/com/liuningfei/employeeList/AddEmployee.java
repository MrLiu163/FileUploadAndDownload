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
import com.liuningfei.tools.JsonHandleHelper;
import com.liuningfei.tools.JsonUtil;

/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("application/json;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		// 写入到数据库
		String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String sql = "insert into employee(name, gender, phone)values(\'"+ name + "\',\'" + gender + "\',\'" + phone + "\')";
        DatabaseConnectionHelper.executeSqliteOperationWithSqlString(sql);
        /*
        Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("statusCode", "0");
		jsonMap.put("message", "添加员工成功！");
		jsonMap.put("data", "{}");
		out.write(JsonUtil.map2json(jsonMap));	
		*/
        Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("name", name);
		jsonMap.put("gender", gender);
		jsonMap.put("phone", phone);
        out.write(JsonHandleHelper.getResponseJsonStr("0", "添加员工成功", jsonMap));
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
