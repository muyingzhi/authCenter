<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta charset="UTF-8">
  <title></title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <link rel="stylesheet" href="include/css/common2.css" />
  <style type="text/css">
    .not-found{text-align: center;margin-top: 160px;}
    .not-found p{font-size: 18px;margin-top: 18px;margin-bottom: 20px;color: #1f232c;}
    .not-found a{display: inline-block;width: 150px;height: 35px;text-align: center;line-height: 35px;background: #03a9f5;color: #fff;border-radius: 5px;text-decoration: none;}
  </style>
</head>
<body>

<div class="not-found">
  <img src="${ctx}/include/images/404/relogin.jpg" alt="" />
  <p>网络超时，请重新登录</p>
  <a href="javascript:reLogin()">重新登录</a>
</div>
<script type="text/javascript">
  function reLogin(){
    window.top.location = "${ctx}/"
  }
</script>
</body>
</html>