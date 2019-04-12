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
	
	</head>
	<body onload="window.parent.loadingDivCloe();loadingDivCloe();">
		<%@include file="/common/tanchukuang.jsp" %>
		<%@include file="/common/loadingDivPage.jsp" %>
		<div style="height: 100%;width: 100%;">
			<div class="container" style="width: 249px; border-right:1px solid #c2c2c2;float: left;height: 100%;">
				<div style="border-right:1px solid #c2c2c2;width: 243px;margin-left: 2px;" id="leftDiv">
		  			<div class="nav2" style="margin: 0px 0px 0px 3px;width: 98%;height: 100%;">
		  				<ul class="accordion">
		  					<li class="level"  id="${staffHspId}" onclick="hspOperation1(this.id,'update')" name="hospital"><a>${staffHspName}</a></li>
					        <ul class="submenu">
					        	
					        	<c:forEach items="${dataList}" var="dataArray">
					        		<c:choose>
					        			<c:when test="${not empty dataArray[2]}">
					          				<li class="level"  id="${dataArray[0]}" name="hospital"><a href="javascript:hspOperation1('${dataArray[0]}')">${dataArray[1]}</a></li>
					          				<ul class="submenu3">
					          					<c:forTokens items="${dataArray[2]}" delims="," var="depts">
					          						<c:forTokens items="${depts}" delims="#" var="dept" varStatus="status">
					          						<c:if test="${status.index eq '0'}">
					          								<li id="${dept}" name="dept">
					          								<a href="javascript:hspOperation1('${dept}')"> 
					          							</c:if>
					          						<c:if test="${status.index eq '1'}">
					          								  ${dept}</a></li>
					          						</c:if>
					          						
					          						</c:forTokens>
					          					</c:forTokens>
					          				</ul>
					          				
					          				
					          			</c:when>
					        			<c:when test="${empty dataArray[2]}">
					          				<li  id="${dataArray[0]}" name="hospital"><a href="javascript:hspOperation1('${dataArray[0]}')">${dataArray[1]}</a></li>
					          			</c:when>
					          		</c:choose>
					          	</c:forEach>
					        </ul>
		  				</ul>
		  			</div>
		  		</div>
	  		</div>
			 <div class="multi_page_container" style="margin-left:250px;display: black;height: 99%;">
                <iframe src="<%=request.getContextPath()%>/hspStaffInfo/hspStaffList.do?hspConfigBaseinfoId=${staffHspId}"
                        width="100%" height="100%" frameborder="0" id="rightIframe" scrolling="atuo"></iframe>
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
			
			
			
			 function hspOperation1(hspId) {
				
	             window.lodingDivShow('<%=request.getContextPath()%>');
	                var url = "<%=request.getContextPath()%>/hspStaffInfo/hspStaffList.do?hspConfigBaseinfoId="+hspId;
	                $("#rightIframe").attr("src",url);
	            }
				var staffHspId = ${staffHspId};
	            function hspClickByHspId(hspId) {
	                $("li[id='"+staffHspId+"']").removeClass("curr");
	                $("li[id='" + hspId + "']")[0].click();
	            }
			
		
		</script>
	</body>
</html>
