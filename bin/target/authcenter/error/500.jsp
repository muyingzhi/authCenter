<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
    <H1>错误：</H1><%= request.getAttribute("javax.servlet.error.status_code")%>
    <H2>错误内容：</H2>

    <%= request.getAttribute("javax.servlet.error.exception")%><br/>
    <%= request.getAttribute("javax.servlet.error.message")%><br/>
    <%=request.getAttribute("javax.servlet.error.request_uri")%>

  </body>
</html>
