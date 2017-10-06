<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 	<!-- 遍历Map集合 -->
	<c:forEach var="me" items="${fileNameMap}">
	<c:url value="/DownLoadServlet" var="downurl">
	<c:param name="filename" value="${me.key}"></c:param>
	</c:url>
	${me.value}<a href="${downurl}">下载</a>
	<br/>
	</c:forEach>
	<a href="/001.png" rel="nofollow">下载图片</a>
	<form action="DownLoadServlet" method="POST">
	图片名称：<input type="text" name="filename">
	<br />
	<input type="submit" value="提交" />
</form>
</body>
</html>