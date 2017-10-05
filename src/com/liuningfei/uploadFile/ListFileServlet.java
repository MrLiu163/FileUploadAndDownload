package com.liuningfei.uploadFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListFileServlet
 */
@WebServlet("/ListFileServlet")
public class ListFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useUnicode=true&characterEncoding=utf-8";
//    static final String DB_URL = "jdbc:mysql://192.168.1.28:3306/RUNOOB?useUnicode=true&characterEncoding=utf-8";
    static final String DB_URL = "jdbc:mysql://192.168.1.88:3306/RUNOOB?useUnicode=true&characterEncoding=utf-8";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		/*
		 * 
		//获取上传文件的目录
		String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		//存储要下载的文件名
		Map<String,String> fileNameMap = new HashMap<String,String>();
		//递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
		listfile(new File(uploadFilePath),fileNameMap);//File既可以代表一个文件也可以代表一个目录
		//将Map集合发送到listfile.jsp页面进行显示
		request.setAttribute("fileNameMap", fileNameMap);
		request.getRequestDispatcher("/listfile.jsp").forward(request, response);
		*/
		
		// 通过servelet将数据库中存储的文件 展示到界面上进行下载
		response.setContentType("text/html;charset=UTF-8");
		
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
            sql = "SELECT id, fileName, fileUrl FROM uploadFiles";
            ResultSet rs = stmt.executeQuery(sql);
        
            String docType = "<!DOCTYPE html> \n";
            String helperStr = "";
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String fileName = rs.getString("fileName");
                String fileUrl = rs.getString("fileUrl");
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print("，文件名称：" + fileName);
                System.out.print("，文件路径：" + fileUrl);
                System.out.print("\n");
//                helperStr = helperStr + "\n" + "<form action=\"/FileUploadAndDownloadPro0911/DownLoadServlet\" method=\"POST\">网址名：<br/>网址：<input type=\"submit\" value=\"" + fileUrl + "\" name=\"filename\""  + "\n" + 
//                		"/></form>";
//                helperStr = helperStr + "\n" + "<form action=\"/FileUploadAndDownloadPro0911/DownLoadServlet\" method=\"POST\">文件名称：<input type=\"text\" value=\"" + fileUrl + "\" name=\"filename\""  + "\n" + 
//                		"/><br/>网址：<input type=\"submit\" value=\"下载\""  + "\n" + 
//                		"/></form>";
                helperStr = helperStr  + "<span>" + fileName + "</span>" + "<a href=\"/DownLoadServlet?filename=" + fileUrl + "\">下载</a>";
                		// disabled=\"disabled\"
            }
            /*
            <!DOCTYPE html><html><head><meta charset="utf-8"><title>菜鸟教程(runoob.com)</title></head><body><form action="HelloForm" method="GET">网址名：<input type="text" name="name"><br />网址：<input type="text" name="url" /><input type="submit" value="提交" /></form></body></html>
            */
            out.println(docType +
                          "<html>\n" +
                          "<head><title>" + "所有可下载文件列表" + "</title></head>\n" +
                          "<body bgcolor=\"#f0f0f0\">\n" +
                          helperStr +
                          "</body></html>");

            /*
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
 */
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
	
	/**
	 * @Method: listfile
	 * @Description: 递归遍历指定目录下的所有文件
	 * @Anthor:孤傲苍狼
	 * @param file 即代表一个文件，也代表一个文件目录
	 * @param map 存储文件名的Map集合
	 */ 
	public void listfile(File file,Map<String,String> map){
		//如果file代表的不是一个文件，而是一个目录
		if(!file.isFile()){
				//列出该目录下的所有文件和目录
				File files[] = file.listFiles();
				//遍历files[]数组
				for(File f : files){
					//递归
					listfile(f,map);
				}
		}else{
					/**
	 				* 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
						file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
						那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
	 				*/
		String realName = file.getName().substring(file.getName().indexOf("_")+1);
		//file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
		map.put(file.getName(), realName);
		}
	}

}
