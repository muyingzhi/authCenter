<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>机构管理菜单</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/css/newTopNav.css" />
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/jquery.mThumbnailScroller.min.css" />
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/jquery.mCustomScrollbar.css" />
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/common2.css" />
    	<script type="text/javascript" src="<%=request.getContextPath()%>/include/jquery-easyui-1.5.2/jquery.min.js"></script>
	    <script src="<%=request.getContextPath()%>/include/js/jquery.mThumbnailScroller.min.js"></script>
	    <script src="<%=request.getContextPath()%>/include/js/jquery.mCustomScrollbar.concat.min.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/include/js/loading.js" defer="defer"></script>
		<style>
		</style>
	</head>
	<body onload="window.parent.loadingDivCloe();loadingDivCloe();">
		<%@include file="/common/tanchukuang.jsp" %>
		<%@include file="/common/loadingDivPage.jsp" %>
		<div style="height: 100%;width: 100%;">
			<div class="container" style="width: 249px; border-right:1px solid #c2c2c2;float: left;height: 100%;">
				<div style="border-right:1px solid #c2c2c2;width: 243px;margin-left: 2px;" id="leftDiv">
					<img src="<%=request.getContextPath()%>/include/images/addHsp.gif" style="margin: 0px 1px 5px 0px;cursor: pointer;" onclick="hspOperation('${staffHspId}','add');" />
					<img src="<%=request.getContextPath()%>/include/images/delete.jpg" style="margin: 0px 1px 5px 0px;cursor: pointer;" onclick="deleteOperation();"  />
		  			<div class="nav2" style="margin: 35px 0px 0px 3px;width: 98%;height: 95%;">
		  				<ul class="accordion">
		  					<li class="level"  id="${staffHspId}" onclick="hspOperation(this.id,'update')" name="hospital"><a>${staffHspName}</a></li>
					        <ul class="submenu">
					        	<c:if test="${not empty deptList}">
					        		<li class="level" id="${staffHspId}">科室列表</li>
					        		<ul class="submenu3">
					        			<c:forEach items="${deptList}" var="deptArray">
					        				<li onclick="hspDeptOperation(this.id,'${staffHspId}','update')" id="${deptArray[0]}" name="dept"><a>${deptArray[1]}</a></li>
					        			</c:forEach>
					        		</ul>
					        	</c:if>
					        	<c:forEach items="${dataList}" var="dataArray">
					        		<c:choose>
					        			<c:when test="${not empty dataArray[2]}">
					        			
					          				<li class="level" onclick="hspOperation(this.id,'update')" id="${dataArray[0]}" name="hospital"><a>${dataArray[1]}</a></li>
					          				
					          				
					          				
					          				<ul class="submenu3">
					          				
					          				
					          					<c:forTokens items="${dataArray[2]}" delims="," var="depts">
					          						<c:forTokens items="${depts}" delims="#" var="dept" varStatus="status">
					          						
					          							<c:if test="${status.index eq '0'}">
					          								<li onclick="hspOperation('${dept}','update')" id="${dept}" name="dept">
					          							</c:if>
					          							<c:if test="${status.index eq '1'}">
					          								<a >${dept}</a></li>
					          							</c:if>
					          							
					          							
					          						</c:forTokens>
					          					</c:forTokens>
					          				</ul>
					          				
					          				
					          			</c:when>
					        			<c:when test="${empty dataArray[2]}">
					          				<li onclick="hspOperation(this.id,'update')" id="${dataArray[0]}" name="hospital"><a  >${dataArray[1]}</a></li>
					          			</c:when>
					          		</c:choose>
					          	</c:forEach>
					        </ul>
		  				</ul>
		  			</div>
		  		</div>
	  		</div>
			 <div class="multi_page_container" style="margin-left:250px;display: black;height: 99%;" >
		        <iframe src="<%=request.getContextPath()%>/hsp/hspConfigBaseinfoUpdateInit.do?useForTree=1&idHidden=${staffHspId}" width="100%" height="100%" frameborder="0" id="rightIframe" scrolling="atuo"></iframe>
		 	</div>
	 	</div>
  	<script>
	(function($){
        $(window).load(function(){
            $(".nav2").mCustomScrollbar({
            	axis:"yx",
				scrollButtons:{enable:true}
            });
        });
    })(jQuery);
			$(function(){
				$(window).resize(function(){
					size();
				});
				function size(){
// 					中间部分高度
					$(".container").height("100%");				
					//嵌套区域宽度
// 					$(".multi_page_container").width($(window).width()-350);
// 					$(".multi_page_container").height($(".container").height());
				}
				size();
				//二级侧边导航
				$('.nav2').on("click", ".accordion li", function(){
					$(this).parents(".accordion").siblings().find("li").removeClass("curr");
					$(this).parents(".accordion").find("li").removeClass("curr");
					$(this).addClass("curr").siblings().removeClass("curr");
					$(this).next(".submenu").slideToggle();
					$(this).next(".submenu3").slideToggle();
					$(this).toggleClass("arrow");
				});
				$("li").eq(0).click();
			});
			
			function hspOperation(hspId,type){
				
				if(type == "update"){
					//alert(hspId+"========hspId");
  					lodingDivShow('<%=request.getContextPath()%>');
					 var url = "<%=request.getContextPath()%>/hsp/hspConfigBaseinfoUpdateInit.do?useForTree=1&idHidden="+hspId;
					$("#rightIframe").attr("src",url);
				}
				if(type == "add"){
					var hspId = $(".curr[name='hospital']").attr("id");
					var url = "<%=request.getContextPath()%>/hsp/hspConfigBaseinfoAddInit.do?useForTree=1&tree_hspId="+hspId;
  					lodingDivShow('<%=request.getContextPath()%>');
					$("#rightIframe").attr("src",url);
				}
				if(type == "delete"){
					if($(".curr[name='hospital']").length == 0){
						pop_win_setTitle("请选择机构",null,"2",null);
						return false;
					}
					pop_win_setTitle("确定要删除机构："+$(".curr[name='hospital']>a").text()+"",function(){
						lodingDivShow('<%=request.getContextPath()%>');
						hspId = $(".curr[name='hospital']").attr("id");
						var url = "<%=request.getContextPath()%>/hsp/hspConfigBaseinfoDelete.do?useForTree=1";
						$.post(url,
							   {"idHidden":hspId},
							   function(data){
								   var json = eval(data);
								   loadingDivCloe();
								   if(data){
									   if(json[0].flag == '1'){
										   pop_win_setTitle(json[0].msg,function(){
											   document.location.reload();
										   },"2",null);				   
									   }
									   if(json[0].flag == '0'){
										   pop_win_setTitle(json[0].msg,null,"2",null);				   
									   }
								   }
							   },
							   "text"
						);
					},"1",null);
				}
			}
			
			function hspDeptOperation(deptCode,hspId,type){
				if(type == "update"){
					lodingDivShow('<%=request.getContextPath()%>');
					var url = "<%=request.getContextPath()%>/hsp/healthRegisterTreeUpdateInit.do?type=dept&hspId="+hspId+"&deptCode="+deptCode;
					$("#rightIframe").attr("src",url);
				}
				if(type == "add"){
					var hspId = $(".curr[name='hospital']").attr("id");
					if(hspId == null || hspId == ""){
						pop_win_setTitle("请选择要添加科室的机构",null,"2",null);
						return false;
					}
					lodingDivShow('<%=request.getContextPath()%>');
					var url = "<%=request.getContextPath()%>/hsp/healthRegisterTreeAddInit.do?type=dept&hspId="+hspId+"&deptCode="+deptCode;
					$("#rightIframe").attr("src",url);
				}
				if(type == "delete"){
					if($(".curr[name='dept']").length == 0){
						pop_win_setTitle("请要删除的科室",null,"2",null);
						return false;
					}
					pop_win_setTitle("确定要删除科室："+$(".curr[name='dept']>a").text()+"",function(){
 	  					lodingDivShow('<%=request.getContextPath()%>');
						hspId = $(".curr[name='dept']").parent().prev().attr("id");
						deptCode = $(".curr[name='dept']").attr("id");
						var url = "<%=request.getContextPath()%>/hsp/hspConfigBaseinfoDelete.do?type=dept&useForTree=1";
						$.post(url,
							   {"hspId":hspId,"deptCode":deptCode},
							   function(data){
								   var json = eval(data);
								   loadingDivCloe();
								   if(data){
									   if(json[0].flag == '1'){
										   pop_win_setTitle(json[0].msg,function(){
											   document.location.reload();
										   },"2",null);				   
									   }
									   if(json[0].flag == '0'){
										   pop_win_setTitle(json[0].msg,null,"2",null);				   
									   }
								   }
							   },
							   "text"
						);
					},"1",null);
				}
			}
			
			function deleteOperation(){
				if($(".curr").attr("name") == "hospital"){
					pop_win_setTitle("确定要删除机构："+$(".curr[name='hospital']>a").text()+"",function(){
						lodingDivShow('<%=request.getContextPath()%>');
						hspId = $(".curr[name='hospital']").attr("id");
						var url = "<%=request.getContextPath()%>/hsp/hspConfigBaseinfoDelete.do?useForTree=1";
						$.post(url,
							   {"idHidden":hspId},
							   function(data){
								   var json = eval(data);
								   loadingDivCloe();
								   if(data){
									   if(json[0].flag == '1'){
										   pop_win_setTitle(json[0].msg,function(){
											   document.location.reload();
										   },"2",null);				   
									   }
									   if(json[0].flag == '0'){
										   pop_win_setTitle(json[0].msg,null,"2",null);				   
									   }
								   }
							   },
							   "text"
						);
					},"1",null);
				}
				
				if($(".curr").attr("name") == "dept"){
					pop_win_setTitle("确定要删除科室："+$(".curr[name='dept']>a").text()+"",function(){
						lodingDivShow('<%=request.getContextPath()%>');
						hspId = $(".curr[name='dept']").parent().prev().attr("id");
						deptCode = $(".curr[name='dept']").attr("id");
						var url = "<%=request.getContextPath()%>/hsp/hspConfigBaseinfoDelete.do?type=dept&useForTree=1";
						$.post(url,
							   {"hspId":hspId,"deptCode":deptCode},
							   function(data){
								   var json = eval(data);
								   loadingDivCloe();
								   if(data){
									   if(json[0].flag == '1'){
										   pop_win_setTitle(json[0].msg,function(){
											   document.location.reload();
										   },"2",null);				   
									   }
									   if(json[0].flag == '0'){
										   pop_win_setTitle(json[0].msg,null,"2",null);				   
									   }
								   }
							   },
							   "text"
						);
					},"1",null);
				}
				pop_win_setTitle("请选择要删除的机构或者科室",null,"2",null);
			}
		</script>
	</body>
</html>
