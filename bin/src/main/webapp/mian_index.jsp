<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
    	<meta charset="UTF-8">
    	<title>首页</title>
	</head>
	<body>
		欢迎来到首页。。。。
	</body>
</html>
