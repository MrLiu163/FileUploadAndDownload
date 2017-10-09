package com.liuningfei.uploadFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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

import com.liuningfei.tools.DatabaseConnectionHelper;

/**
 * Servlet implementation class ListFileServlet
 */
@WebServlet("/ListFileServlet")
public class ListFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
		
		// 通过servelet将数据库中存储的文件 展示到界面上进行下载
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		try {
            String sql = "SELECT id, fileName, fileUrl FROM uploadFiles";
            ResultSet rs = DatabaseConnectionHelper.executeQueryOperationWithSqlString(sql);
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
                fileUrl = URLEncoder.encode(fileUrl, "UTF-8");
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

            // 完成后关闭
            Connection conn = rs.getStatement().getConnection();
			Statement stmt = rs.getStatement();
			rs.close();
			conn.close();
			stmt.close();
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
