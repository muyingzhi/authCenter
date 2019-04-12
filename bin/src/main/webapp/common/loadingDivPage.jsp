<%@ page language="java" pageEncoding="utf-8"%>
<div id="iframLoadDiv" style="display:none; z-index:10000;left:0px;
			   visibility: hidden;background-color:white;  position: absolute;top:0px;width: 100%;height: 100% ;filter:alpha(opacity=45);opacity:0.5;" align="center">
	<table style="width: 100%;" cellpadding="0" border="0">
		<tr>
			<td id="iframLoadtd" style='position:relative'></td>
		</tr>
	</table>
</div>
<script type="text/javascript">
	Date.prototype.Format = function (fmt) { //author: meizz
		var o = {
			"M+": this.getMonth() + 1, //月份
			"d+": this.getDate(), //日
			"h+": this.getHours(), //小时
			"m+": this.getMinutes(), //分
			"s+": this.getSeconds(), //秒
			"q+": Math.floor((this.getMonth() + 3) / 3), //季度
			"S": this.getMilliseconds() //毫秒
		};
		if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for (var k in o)
			if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
	$(function(){
		$(document).ajaxStart(function(){
			lodingDivShow('<%=request.getContextPath()%>');
		});
		$(document).ajaxStop(function(){
			loadingDivCloe();
		})
		$(document).ajaxError(function(event, jqxhr, settings, thrownError) {
			loadingDivCloe();
			/** **/
			var resp = jqxhr.responseText;
			if(jqxhr.status===0){
//				alert("异常，服务器未返回内容");
				return;
			}

			//当ajax请求返回的是登录页面,则表示会话超时或没有权限操作.页面直接转到登录页面.
			if(resp && resp.indexOf("site-login-verification")>=0){
				alert("会话过期或者无此权限,返回登录页面!");
				top.location = "${ctx}/";
				return;
			}

			if(jqxhr.status===200){
				alert("异常，请求重定向，返回内容如下:status="+jqxhr.status+"\n"+resp);
				return;
			}
			if(jqxhr.status==404){
				alert("404，请求无效地址:status="+jqxhr.status+"\n"+settings.url);
				return;
			}
			if(jqxhr.status==500){
				alert("status="+jqxhr.status+"\n"+jqxhr.responseText);
				return;
			}
			try{
				var result = $.parseJSON(resp);
			}catch(er){
				if(er.name === "SyntaxError"){
					alert("异常内容不是JSON：\n"+resp);
				}else{
					alert("异常内容：\n"+resp);
				}
				return;
			}
			//----可处理的异常
			if(result.code===888){
				location.href="<%=request.getContextPath()%>";
				alert("请重新登录");
			}else if(result.code===403){
//				alert("没有权限完成此操作:\n"+settings.url);
				top.location = "<%=request.getContextPath()%>";
			}else{
				alert(result.msg+"("+jqxhr.status + ")");
			}
		});
	})
</script>