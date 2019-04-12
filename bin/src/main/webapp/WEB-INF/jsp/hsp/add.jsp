<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.tianjian.hsp.bean.CommConfigBean"%>
<jsp:useBean id="data" scope="request"
	class="com.tianjian.hsp.bean.HspConfigBaseinfoForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%if(request.getServerPort() == 80) {%>
<base
	href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
<%} else {%>
<base
	href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<%}%>
<title>机构添加</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />

	<script language="javascript" src="<%=request.getContextPath() %>/include/js/eventOnKeyPress.js"></script>
	<script language="javascript" src="<%=request.getContextPath()%>/include/js/utrim.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/include/js/checkbox_radio.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/jianbian.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/include/jquery-easyui-1.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/loading.js" defer="defer"></script>
 	<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/prompt.js"></script>
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="<%=request.getContextPath()%>/include/css/prompt.css" />
 

<script language="javascript">

function saveForm(){
	 var enDomain = document.form.domainName.value;
	var i;
	var ii;
	var aa;
	var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-0123456789|.!=+%/_: ";
	var allValid = true;
	for (i = 0;  i < enDomain.length;  i++){
	  ch = enDomain.charAt(i);
	  for (j = 0;  j < checkOK.length;  j++)
	  if (ch == checkOK.charAt(j))
	   break;
	  if (j == checkOK.length)
	  {
	   allValid = false;
	   break;
	  }
	 }
	
	    if(enDomain.length>0){
	       ii=enDomain.indexOf(".");
	        if(ii==-1){
			   pop_win_setTitle("网站域名不规范!",function(){document.form.domainName.value = "";
			   document.form.domainName.focus();},"2",null);
			   
			   return 
	        }
	    }
	 if(!(/^\s*[\u4e00-\u9fa5]+\s*$/.test(document.form.itemName.value))){
			 pop_win_setTitle("机构名称只能为汉字",function(){document.form.itemName.focus();},"2",null);
			 
			 return;
		}

	if(document.form.itemCode.value == ""){
	   pop_win_setTitle("机构代码不能为空!",null,"2",null);
	 	return ;
	}

	if(!CheckInputWord("1")) {
	   return;
	}
	pop_win_setTitle("是否确定添加",function(){
		
	<%
	if("1".equals(request.getParameter("useForTree"))){
	%>
	window.parent.lodingDivShow('<%=request.getContextPath()%>');
	var formData = $("#form").serialize();
	$.ajax({
		dataType: "text",
		type:"POST",
		url:"<%=request.getContextPath()%>/hsp/hspConfigBaseinfoAdd.do",
		processData:true,
		data:formData,
		error: function(a, b, c){
			pop_win_setTitle(a + "-" + b + "-" + c,null,"2",null);
		},
		success:function(data){
			if(data != null){
				try{
					var json = eval(data);
				}catch(e){
					window.parent.loadingDivCloe();
					pop_win_setTitle("请重新登录！",null,"2",null);
					return;
				}
				var message=json[0].msg;
				if(json[0].flag == '1'){
  				window.parent.loadingDivCloe();
	  				pop_win_setTitle(message,function(){
	  					window.parent.lodingDivShow('<%=request.getContextPath()%>');
	  					window.parent.location.reload();
// 	  					location.reload(false);
	  				},"2",null);
					
						
				}else{
					pop_win_setTitle(message,null,"2",null);
				}
			}
		}
	});	
	<%
		}else{
	%>
	window.parent.lodingDivShow('<%=request.getContextPath()%>');
	document.form.submit();
	<%
		}
	%>
	},"0",null);
}

function checkNum(s,name){
	
	if(!isInteger(s)){
	pop_win_setTitle(name+"!",null,"2",null);
	return false;
	}else{
	return true; 
	}
}


  function onlyNum()
  {
    if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
    if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
    event.returnValue=false;
   }
   

function newXMLHttpRequest() {
	var xmlreq = false;
	if (window.XMLHttpRequest) {
		xmlreq = new XMLHttpRequest();		
	} else if (window.ActiveXObject) {
	    try {
      		xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e1) {
			try {
        		xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e2) {
			xmlreq = false;
	    	}
    	}
	}	
	return xmlreq;
}

function getReadyStateHandler(req, responseXmlHandler) {
	return function () {
		if (req.readyState == 4) {
      		if (req.status == 200) {
      			//alert(req.responseText); 
			    responseXmlHandler(req.responseXML);
			} else {
			    pop_win_setTitle("HTTP error: " + req.status,null,"2",null);
      		}
    	}
  	}
}

function huiche(){
		if(event.keyCode==13){
			event.keyCode=9;
		}
		
	} 
var smsLength = parseInt('40');

  
   function CheckInputWord(needAlert)
   {
      //检查字数
    var word1 = document.getElementById("comments").value;
    var word = word1.replace(/(^\s*)|(\s*$)/g,"");
    var num = word.length;
    if (num <= smsLength)
    {
       return true;
    }
    else
    {
     //去掉该汉字
     //document.getElementById("comments").value = document.getElementById("comments").value.substring(0,smsLength);
     if (needAlert == 1)
     {
        //对象失去焦点，同时弹出
        //document.getElementById("comments").blur();
		pop_win_setTitle("备注字数过多!",function(){document.getElementById("comments").foucs();},"2",null);
        
        return false;
     }
    }
   }
			 function getReadyStateHandler(req, responseXmlHandler) {
				return function () {
					if (req.readyState == 4) {
			      		if (req.status == 200) {
			      			//alert(req.responseText); 
						    responseXmlHandler(req.responseXML);
						} else {
						    pop_win_setTitle("HTTP error: " + req.status,null,"2",null);
			      		}
			    	}
			  	}
			}  
			<!-- 处理市 -->
			function setCity(url){
				var province = document.getElementById("commConfigLocationId1").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?province=" + province;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateCity);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			function setCitys(url){
				var province = document.getElementById("hspConfigBaseInfoId1").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?province=" + province;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateCity1);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			<!-- 更新市 -->
			function updateCity1(cityXML) {
				var city = document.getElementById("hspConfigBaseInfoId2");
			    while (city.options.length) {
			        city.remove(0);
			    }				    
			    var country = document.getElementById("hspConfigBaseInfoId3");
			    while (country.options.length) {
			        country.remove(0);
			    }				    
			    var clt = document.getElementById("hspConfigBaseInfoId4");
			    while (clt.options.length) {
			        clt.remove(0);
			    }				    
			    var clv = document.getElementById("hspConfigBaseInfoId5");
			    while (clv.options.length) {
			       clv.remove(0);
			    }				    
				var indexObj = cityXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for(var i=0; i<index; i++){
					var keyObj = cityXML.getElementsByTagName("key")[i];
					var valueObj = cityXML.getElementsByTagName("value")[i];
					var newElem = document.createElement("option");
					if(valueObj.childNodes[0] == null){
						newElem.text = "" ;
					}else{
						newElem.text = valueObj.childNodes[0].nodeValue ;
					}
					if(keyObj.childNodes[0] == null){
						newElem.value = "" ;
					}else{
						newElem.value = keyObj.childNodes[0].nodeValue ;
					}
					city.add(newElem);
				}
			}	
			<!-- 更新市 -->
			function updateCity(cityXML) {
				var city = document.getElementById("commConfigLocationId2");
			    while (city.options.length) {
			        city.remove(0);
			    }				    
			    var country = document.getElementById("commConfigLocationId3");
			    while (country.options.length) {
			        country.remove(0);
			    }				    
			    var clt = document.getElementById("commConfigLocationTownId");
			    while (clt.options.length) {
			        clt.remove(0);
			    }				    
			    var clv = document.getElementById("commClvId");
			    while (clv.options.length) {
			       clv.remove(0);
			    }				    
				var indexObj = cityXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for(var i=0; i<index; i++){
					var keyObj = cityXML.getElementsByTagName("key")[i];
					var valueObj = cityXML.getElementsByTagName("value")[i];
					var newElem = document.createElement("option");
					if(valueObj.childNodes[0] == null){
						newElem.text = "" ;
					}else{
						newElem.text = valueObj.childNodes[0].nodeValue ;
					}
					if(keyObj.childNodes[0] == null){
						newElem.value = "" ;
					}else{
						newElem.value = keyObj.childNodes[0].nodeValue ;
					}
					city.add(newElem);
				}
			}	
			<!-- 设置县 -->
			function setCounty(url){
				var city = document.getElementById("commConfigLocationId2").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?city=" + city;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateCounty);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			<!-- 设置县 -->
			function setCountys(url){
				var city = document.getElementById("hspConfigBaseInfoId2").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?city=" + city;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateCountys);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			<!-- 更新县 -->
			function updateCountys(countyXML) {
				var country = document.getElementById("hspConfigBaseInfoId3");
			    while (country.options.length) {
			        country.remove(0);
			    }			    
			    var clt = document.getElementById("hspConfigBaseInfoId4");
			    while (clt.options.length) {
			        clt.remove(0);
			    }			    
			    var clv = document.getElementById("hspConfigBaseInfoId5");
			    while (clv.options.length) {
			       clv.remove(0);
			    }			    
				var indexObj = countyXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for(var i=0; i<index; i++){
					var keyObj = countyXML.getElementsByTagName("key")[i];
					var valueObj = countyXML.getElementsByTagName("value")[i];
					var newElem = document.createElement("option");
					if(valueObj.childNodes[0] == null){
						newElem.text = "" ;
					}else{
						newElem.text = valueObj.childNodes[0].nodeValue ;
					}
					if(keyObj.childNodes[0] == null){
						newElem.value = "" ;
					}else{
						newElem.value = keyObj.childNodes[0].nodeValue ;
					}
					country.add(newElem);
				}
			}
			<!-- 更新县 -->
			function updateCounty(countyXML) {
				var country = document.getElementById("commConfigLocationId3");
			    while (country.options.length) {
			        country.remove(0);
			    }			    
			    var clt = document.getElementById("commConfigLocationTownId");
			    while (clt.options.length) {
			        clt.remove(0);
			    }			    
			    var clv = document.getElementById("commClvId");
			    while (clv.options.length) {
			       clv.remove(0);
			    }			    
				var indexObj = countyXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for(var i=0; i<index; i++){
					var keyObj = countyXML.getElementsByTagName("key")[i];
					var valueObj = countyXML.getElementsByTagName("value")[i];
					var newElem = document.createElement("option");
					if(valueObj.childNodes[0] == null){
						newElem.text = "" ;
					}else{
						newElem.text = valueObj.childNodes[0].nodeValue ;
					}
					if(keyObj.childNodes[0] == null){
						newElem.value = "" ;
					}else{
						newElem.value = keyObj.childNodes[0].nodeValue ;
					}
					country.add(newElem);
				}
			}			
			<!-- 设置乡镇 -->
			function setTown(url){
				var town = document.getElementById("commConfigLocationId3").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?town=" + town;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateTown);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			<!-- 设置乡镇 -->
			function setTown1(url){
				var town = document.getElementById("hspConfigBaseInfoId4").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?town=" + town;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateTowns);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			<!-- 更新乡镇 -->
			function updateTowns(countyXML) {				   
			    var clt = document.getElementById("hspConfigBaseInfoId4");
			    while (clt.options.length) {
			        clt.remove(0);
			    }			    
			    var clv = document.getElementById("hspConfigBaseInfoId5");
			    while (clv.options.length) {
			       clv.remove(0);
			    }			    
				var indexObj = countyXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for(var i=0; i<index; i++){
					var keyObj = countyXML.getElementsByTagName("key")[i];
					var valueObj = countyXML.getElementsByTagName("value")[i];
					var newElem = document.createElement("option");
					if(valueObj.childNodes[0] == null){
						newElem.text = "" ;
					}else{
						newElem.text = valueObj.childNodes[0].nodeValue ;
					}
					if(keyObj.childNodes[0] == null){
						newElem.value = "" ;
					}else{
						newElem.value = keyObj.childNodes[0].nodeValue ;
					}
					clt.add(newElem);
				}
			}
			<!-- 更新乡镇 -->
			function updateTown(countyXML) {				   
			    var clt = document.getElementById("commConfigLocationTownId");
			    while (clt.options.length) {
			        clt.remove(0);
			    }			    
			    var clv = document.getElementById("commClvId");
			    while (clv.options.length) {
			       clv.remove(0);
			    }			    
				var indexObj = countyXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for(var i=0; i<index; i++){
					var keyObj = countyXML.getElementsByTagName("key")[i];
					var valueObj = countyXML.getElementsByTagName("value")[i];
					var newElem = document.createElement("option");
					if(valueObj.childNodes[0] == null){
						newElem.text = "" ;
					}else{
						newElem.text = valueObj.childNodes[0].nodeValue ;
					}
					if(keyObj.childNodes[0] == null){
						newElem.value = "" ;
					}else{
						newElem.value = keyObj.childNodes[0].nodeValue ;
					}
					clt.add(newElem);
				}
			}
			<!-- 设置村 -->
			function setVillage(url){
				var village = document.getElementById("commConfigLocationTownId").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?village=" + village;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateVillage);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			function setVillage1(url){
				var village = document.getElementById("commConfigLocationTownId").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?village=" + village;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateVillages);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			<!-- 更新村 -->
			function updateVillages(countyXML) {
			    var clv = document.getElementById("hspConfigBaseInfoId5");
			    while (clv.options.length) {
			       clv.remove(0);
			    }			    
				var indexObj = countyXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for(var i=0; i<index; i++){
					var keyObj = countyXML.getElementsByTagName("key")[i];
					var valueObj = countyXML.getElementsByTagName("value")[i];
					var newElem = document.createElement("option");
					if(valueObj.childNodes[0] == null){
						newElem.text = "" ;
					}else{
						newElem.text = valueObj.childNodes[0].nodeValue ;
					}
					if(keyObj.childNodes[0] == null){
						newElem.value = "" ;
					}else{
						newElem.value = keyObj.childNodes[0].nodeValue ;
					}
					clv.add(newElem);
				}
			}
			<!-- 更新村 -->
			function updateVillage(countyXML) {
			    var clv = document.getElementById("commClvId");
			    while (clv.options.length) {
			       clv.remove(0);
			    }			    
				var indexObj = countyXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for(var i=0; i<index; i++){
					var keyObj = countyXML.getElementsByTagName("key")[i];
					var valueObj = countyXML.getElementsByTagName("value")[i];
					var newElem = document.createElement("option");
					if(valueObj.childNodes[0] == null){
						newElem.text = "" ;
					}else{
						newElem.text = valueObj.childNodes[0].nodeValue ;
					}
					if(keyObj.childNodes[0] == null){
						newElem.value = "" ;
					}else{
						newElem.value = keyObj.childNodes[0].nodeValue ;
					}
					clv.add(newElem);
				}
			}
			function back(){
				window.parent.lodingDivShow('<%=request.getContextPath()%>');
				history.go(-1);
			}
			
			//检查电话格式
			function checkPhone(){
				var tel= document.form.phone.value;
				var mobilePhoneReg = /^1[0-9][0-9]\d{8}$/;
				var tellphoneReg = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
				if(tel!=null&&tel!=""){
					if(mobilePhoneReg.test(tel) || tellphoneReg.test(tel)){
						//alert("正确的电话号码");
						return true;
					}else{
						//alert("请输入正确的手机号码或固定电话号码");
						//document.form.phone.focus();
						pop_win_setTitle("请输入正确的手机号码或固定电话号码",function(){document.form.phone.focus();},"2",null);		
						return false;
					}
				}
			}
</script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/open.css" />
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="<%=request.getContextPath()%>/include/css/form_new.css" />
	<style type="text/css">
		h2{line-height: 35px;font-size: 14px; color: #333; border-bottom: 1px solid #cccccc; width: 90%; margin: 10px auto 0; font-weight: bold;}
		.tblFill td.tblLable{width: 15%;}
		.tblFill select{width: 100%;}
		input[type='text']{
			width: 98%;
		}
	</style>
	<%--<style>
	    #xx td{
	       text-align:left;
	    }
	</style>
--%></head>
	<body onload="window.parent.loadingDivCloe();">
	<%@include file="/common/tanchukuang.jsp" %>
	<form id="form" name="form" method="post" action="hsp/hspConfigBaseinfoAdd.do">
		
		<input type="hidden" name="useForTree" value="<%=request.getParameter("useForTree") %>"/>
		<input type="hidden" name="id" value="<%=data.getId() %>" />
		<input type="hidden" id="oldParentItemCode" value="<%=data.getParentItemCode() %>"/>
		<span id="spanOutput" style="display: none;"></span>
		<h2><img src="<%=request.getContextPath()%>/include/images/list.jpg" /><span style="vertical-align: middle;padding-left: 6px;">添加卫生机构</span></h2>
		<table  align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" id="xx">
			<tr>
				<td class="tblLable">
					<span style="color:red">*</span>上级机构：
				</td>
				<td  colspan="3" style="text-align: left;padding-left: 5px;font-size: 13px;">
			<input style="text-align: left;" type="hidden" id="displayInputId_1" name="parentItemCode_name"  value="<%=data.getParentItemName() %>" readonly="true"  onkeydown="huiche()" />
					<%=data.getParentItemName() %>		
	 	   <!--这是准备存储到数据的字段-->
			<input type="hidden" id="hiddenInputId_1" value="<%=data.getParentItemCode() %>" name="parentItemCode" />
			
				</td>
			</tr>	
			<tr>
				
				<td class="tblLable">
					<span>*</span>机构名称：
				</td>
				<td >
					<input type="text" name="itemName"
						maxlength="50" onkeydown="huiche()"
						value="<%=data.getItemName() %>" />
				</td>
			</tr>
			<tr>
				<td class="tblLable">
					<span>*</span>机构代码：
				</td>
				<td>
					<input type="text" name="itemCode" maxlength="50"
						onkeydown="huiche()"
						value="<%=data.getItemCode() %>"  />	
				</td>
				<div style="display: none">
					序号：
						<input type="text" name="seqNo"  onblur=""
							   id="seqNo" maxlength="25" value="<%=data.getSeqNo()%>" onkeydown="onlyNum();"/>

				</div>
			</tr>
			
			<tr>
				<td class="tblLable" >
					机构类别：
				</td>
				<td>				
				<!--以下是显示基本录入框的input-->
					<input type="text" class="input" id="displayInputId_2" name="cchtName" value="<%=data.getCchtName() %>" 
					/>
		 	   </td>	

			</tr>

			<tr>
				<td class="tblLable">
					地址：
				</td>
				<td>
					<input type="text" name="address" onkeydown="huiche()"
						id="address" maxlength="50"
						value="<%=data.getAddress() %>" />
				</td>

				<td class="tblLable">
					邮政编码：
				</td>
				<td>
					<input type="text" onkeydown="huiche()" name="zipcode" id="zipcode"  onblur="checkPostCode('zipcode')" 
						maxlength="6" 
						value="<%=data.getZipcode() %>" />
				</td>
			</tr>

			<tr>
				<td class="tblLable">
					联系人姓名：
				</td>
				<td>
					<input type="text" onkeydown="huiche()" name="contactPersonName"
						id="contactPersonName" maxlength="50"
						value="<%=data.getContactPersonName() %>" />
				</td>
				<td class="tblLable">
					电&emsp;话：
				</td>
				<td>
					<input type="text" onkeydown="huiche()" name="phone" id="tel"  onblur="checkPhone()"
						maxlength="15" 
						value="<%=data.getPhone() %>" />
				</td>
			</tr>
			
			<tr>
				<td class="tblLable">
					电子邮件：
				</td>
				<td>
					<input type="text" id="email"onkeydown="huiche()" name="EMail" onblur="checkemail()" maxlength="25" value="<%=data.getEMail()%>" />
				</td>
				<td class="tblLable" >
					网站域名：
				</td>
				<td>
				<input type="text" id="domainName" onkeydown="huiche()" name="domainName" maxlength="50" value="<%=data.getDomainName()%>" />
				</td>
			</tr>
			<tr>

					<td class="tblLable">
						编制床位数：
					</td>
					<td>
						<input type="text" name="authorizedBedNum" onkeydown="huiche()" onblur=""
							id="authorizedBedNum" maxlength="25" onkeyup="this.value=this.value.replace(/\D/gi,'')" value="<%=data.getAuthorizedBedNum()%>" />
					</td>
					<td class="tblLable">实有床位数：</td>
					<td >
						<input type="text" name="outspreadBedNum" onkeydown="huiche()" onblur=""
							id="outspreadBedNum" maxlength="25" onkeyup="this.value=this.value.replace(/\D/gi,'')" value="<%=data.getOutspreadBedNum()%>" />
					</td>
				</tr>
				<tr>
			   	 	<td class="tblLable" style="padding-bottom:50px">
			   	 		备注：
			   	 	</td>
			   	 	<td class="textareaTd" colspan="3" style="padding-bottom:10px">
						<textarea name="comments" id="comments" 
						onkeydown="huiche()" rows="3" ><%=data.getComments()%></textarea>
					</td>
			    </tr>
		</table>
			<!-- Sheet operation button area -->
              <div class="btnSave">
						<img src="<%= request.getContextPath()%>/include/images/submit.jpg" name="btnSave" id="btnSave" style="cursor: pointer;" onclick="saveForm();" />
						 <span style="margin-left: 20px"></span>
					
               </div>
		</form>
	</body>
</html>
