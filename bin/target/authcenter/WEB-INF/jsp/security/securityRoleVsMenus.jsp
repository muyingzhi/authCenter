<%@page import="com.tianjian.util.Converter"%>
<%@page import="com.tianjian.security.bean.MenuTreeNode"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	if (request.getServerPort() == 80) {
%>
<base
	href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
<%
	} else {
%>
<base
	href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<%
	}
%>
<title>权限管理
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="<%=request.getContextPath()%>/include/css/form_new.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/demo.css" type="text/css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/include/jquery-easyui-1.5.2/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/include/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/MzTreeView10_addradio_rmdaohang.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/loading.js" ></script>
	<script language="javascript" src="<%=request.getContextPath()%>/include/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/include/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/include/jquery-easyui-1.5.2/themes/default/easyui.css"/>
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="<%=request.getContextPath() %>/include/css/listFile_new.css" />
<script language="javascript">
var roles_setting = {
		async: {
			enable: true,
			url:"<%=request.getContextPath()%>/security/securityRoleVsMenusRoleInit.do"
		},
		view :{
			showIcon: showIconForRoleTree,
			expandSpeed: "",
			selectedMulti: false,
			fontCss : getFontCss
		},
		check : {
			enable : true,
			chkStyle : "radio",
			radioType: "all" 
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback: {
			beforeCheck: beforeRoleCheck,
			onCheck: onRoleCheck,
			onRightClick: zTreeOnRightClick
		}
	};
	
	function getFontCss(treeId, treeNode){
		return {};
	}

	//右键菜单
	function zTreeOnRightClick(event, treeId, treeNode) {
	    //alert(treeNode ? treeNode.tId + ", " + treeNode.name : "isRoot");
	    hideRMenu();
		$("#rMenu ul").hide();
		if(treeNode){
			<%--使用该方法代替zTree.selectNode，用于防止子节点过多超过屏幕高度时选中父节点后被置顶而发生位置变动。--%>
// 			$.fn.zTree._z.view.selectNode(zTree.setting, treeNode);
			roleTree.selectNode(treeNode);
			if(treeNode.type == "root"){
				$("#rootMenu").show();
            }else if(treeNode.type == "role"){
				$("#roleMenu").show();
            }
            var x = $(document).scrollLeft()+event.clientX;
            var y = $(document).scrollTop()+event.clientY;
            var height = $("#rMenu").height();
            var width = $("#rMenu").width();
            if(x + width > $(document).width()){
            	x -= width;
            }
            if(y + height > $(document).height()){
            	y -= height;
            }
            $("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});
            $("body").bind("mousedown", onBodyMouseDown);
            $("#rightClickRoleId").val(treeNode.id);
            $("#rightClickRoleName").val(treeNode.name);
        }
	};
	
	function refreshNode(){
		hideRMenu();
		var treeObj = $.fn.zTree.getZTreeObj("rolesTree");
		var nodes = treeObj.getNodesByParam("name", "角色", null);
		if (nodes.length>0) {
			treeObj.reAsyncChildNodes(nodes[0], "refresh");
		}
	}
	
	
	
	function dialogback_ndmb(){
		$("#mydialog_ndmb").dialog("close");
	}
	
	function hideRMenu() {
		if ($("#rMenu")) $("#rMenu").css({"visibility": "hidden"});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	
	function onBodyMouseDown(event){
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
			$("#rMenu").css({"visibility" : "hidden"});
		}
	}
	
	
	var roles_zNodes =[{ id:0, pId:-1, name:"角色", nocheck:true, isParent:true, type:"root"}]
	function showIconForRoleTree(treeId, treeNode){
		return treeNode.level == 0;
	}
	
	function beforeRoleCheck(treeId, treeNode){
		menuTree.checkAllNodes(false);
	}
	
	function onRoleCheck(e, treeId, treeNode){
		if(treeNode.checked){
			 $.ajax({
					type: "POST",
		        	processData: false,
		        	dataType: "text",
		        	url: "<%=request.getContextPath()%>/security/securityRoleVsMenusSelectRoleMenus.do",
		        	data: "roleId="+treeNode.id,
		        	error: function(a, b, c){
		        		pop_win_setTitle(a + "-" + b + "-" + c,null,"2",null);
		        		return;
		        	},
		        	success: function(json){
		        		var ids = eval(json);
		        		if(ids != null){
	        				var nodes = menuTree.getNodesByFilter(
	        					function(treeNode){return treeNode.type==NodeType.MENU && $.inArray(treeNode.id, ids)>=0 }, false);
	        				for(var i = 0; i < nodes.length; i++){
	        					menuTree.checkNode(nodes[i], true, true,false);
	        				}
		        		}
		        	}
		        });
		}else{
			menuTree.checkAllNodes(false);
		}
	}
	
	
	var NodeType = {ROOT:"0", PUBLIC_CLASS_1:"1", PUBLIC_CLASS_2:"2", PUBLIC:"3", MENU:"4"};
	var menus_setting = {
			async: {
				enable: true,
				url:getDataUrl,
				dataFilter: getDataFilter
			},
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeMenuCheck
			}
		};
	
	var menus_zNodes =[{id:0, pId:-1, name:"模块菜单", isParent:true, type:NodeType.ROOT}];
	function getDataUrl(treeId, treeNode){
		if(treeNode.type == NodeType.ROOT){
			return "<%=request.getContextPath()%>/security/securityRoleVsMenusMenusInit.do";
		}
		return null;
	}
	function getDataFilter(treeId, parentNode, responseData){
		if(responseData){
			for(var i =0; i < responseData.length; i++) {
				var treeNode = responseData[i];
				if(treeNode.type == NodeType.PUBLIC_CLASS_1 || treeNode.type == NodeType.PUBLIC_CLASS_2){
					treeNode.icon = "<%=request.getContextPath()%>/include/images/close_folder.png";
					treeNode.iconOpen = "<%=request.getContextPath()%>/include/images/open_folder.png";
					treeNode.iconClose = "<%=request.getContextPath()%>/include/images/close_folder.png";
				}else if(treeNode.type == NodeType.PUBLIC){
					treeNode.icon = "<%=request.getContextPath()%>/include/images/close_folder_yellow.png";
				}else if(treeNode.type == NodeType.MENU){
					treeNode.icon = "<%=request.getContextPath()%>/include/images/leaf.png";
				}
				
		      }
		}
		return responseData;
	}
	function beforeMenuCheck(treeId, treeNode){
		var selectedRoleNodes = menuTree.getSelectedNodes(true);
		if(selectedRoleNodes == null || selectedRoleNodes.length == 0){
			return false;
		}
	}
	function save(){
		var selectedRoleNodes = roleTree.getCheckedNodes(true);
		if(selectedRoleNodes == null || selectedRoleNodes.length == 0){
			pop_win_setTitle("！",null,"2",null);
			return;
		}
		pop_win_setTitle("确定要保存吗？",function(){
			lodingDivShow('<%=request.getContextPath()%>');
			var selectedMenuNodes = menuTree.getCheckedNodes(true);
			var menuIds = new Array();
			if(selectedMenuNodes != null){
				for(var i = 0; i < selectedMenuNodes.length; i++){
					var node = selectedMenuNodes[i];
					if(node.type == NodeType.MENU){
						menuIds.push(node.id);
					}
				}
			}
			 $.ajax({
		        	type: "POST",
		        	processData: false,
		        	dataType: "text",
		        	url: "<%=request.getContextPath()%>/security/securityRoleVsMenusSave.do",
		        	data: "roleId="+selectedRoleNodes[0].id+"&menuIds="+menuIds.join(","),
		        	error: function(a, b, c){
		        		loadingDivCloe();
		        		pop_win_setTitle(a + "-" + b + "-" + c,null,"2",null);
		        		return;
		        	},
		        	success: function(str){
		        		loadingDivCloe();
		        		pop_win_setTitle(str,null,"2",null);
		        		return;
		        	}
		        });
		},"0",null);
	}
	$(document).ready(function() {
		resize();
		$.fn.zTree.init($("#rolesTree"), roles_setting, roles_zNodes);
		$.fn.zTree.init($("#menusTree"), menus_setting, menus_zNodes);
		roleTree = $.fn.zTree.getZTreeObj("rolesTree");
		var roleRootNode = roleTree.getNodeByParam("level", "0");
		roleTree.expandNode(roleRootNode, true, false, false, false);
		menuTree = $.fn.zTree.getZTreeObj("menusTree");
		var menuRootNode = menuTree.getNodeByParam("level", "0");
		menuTree.expandNode(menuRootNode, true, false, false, false);
	});
	$(window).resize(function(){resize();});
	function resize(){
		var height = $(window).height() - 100;
		$("#rolesTree").height(height);
		$("#menusTree").height(height);
	}
	//-->
	</script>
	
	<style type="text/css">
	div#rMenu {
		position: absolute;
		visibility: hidden;
		top: 0;
		background-color: #afafaf;
		text-align: center;
	}
	
	div#rMenu ul li {
		margin: 1px 1px 1px 1px;
		padding: 3px 30px;
		cursor: pointer;
		list-style: none outside none;
		background-color: #fafafa;
	}
	
	table {
		font-family: Arial, Helvetica, sans-serif, 宋体;
		font-size: 12px;
		color: #000;
		margin: 0 auto;
	}
	
	.rMenuImg {
		vertical-align: middle;
		margin-right: 8px;
	}
	
	#query_div {
		top: 0; left 0;
		border: 1px solid #617775;
		text-align: left;
	}
	
	.ztree li span.button.switch.level0 {
		visibility: hidden;
		width: 1px;
	}
	
	.ztree li ul.level0 {
		padding-left: 17px;
		background: none;
	}
	
	
	div#rMenu {
		position: absolute;
		visibility: hidden;
		top: 0;
		background-color: #afafaf;
		text-align: center;
	}
	
	div#rMenu ul li {
		margin: 1px 1px 1px 1px;
		padding: 3px 30px;
		cursor: pointer;
		list-style: none outside none;
		background-color: #fafafa;
	}
	
	table {
		font-family: Arial, Helvetica, sans-serif, 宋体;
		font-size: 12px;
		color: #000;
		margin: 0 auto;
		overflow-y: hidden;
	}
	
	.rMenuImg {
		vertical-align: middle;
		margin-right: 8px;
	}
	.examTime{
		border:1px solid #c2c2c2;
		vertical-align: middle;
		}
	#mydialog_ndmb td{
		padding: 5px 0px;
	}
</style>
</head>
<body style="background-color: white" onload="window.parent.loadingDivCloe();loadingDivCloe();">
	<%@include file="/common/tanchukuang.jsp" %>
	<%@include file="/common/loadingDivPage.jsp" %>
	
	<div align="center">
	<div class="content_wrap"  style="width:90%;height:90%; overflow: no;padding-left: 5%; ">
		<div class="left" style="width: 30%;margin-left: 20px;"  align="center">
			<p class="title" style="width: 90%;text-align: left;"><img src="<%=request.getContextPath()%>/include/images/jiaose-list.jpg" /> 角色列表</p>
			<ul id="rolesTree" class="ztree" style="width: 90%;"></ul>
		</div>
		<div class="center" style="width: 5%; color: #2e9bed; font-weight: bold; font-size: 14px; height: 50%; padding-top: 150px;margin-left: 0px;float: left;text-align: center;">
		>>>>
		</div>
		<div class="left" style="width: 30%;" align="center">
			<p class="title" style="width: 90%;text-align: left;"><img src="<%=request.getContextPath()%>/include/images/menu-list.jpg" /> 模块菜单列表</p>
			<ul id="menusTree" class="ztree" style="width: 90%;"></ul>
		</div>
		<div class="left" align="left" style="width:25%; padding-left: 30px;">
			<p>使用说明：</p>
			<p>1.在左侧点击角色名称，在模块菜单列表中选择好角色对应的菜单后，点击保存按钮。</p>
			<p>2.在左侧选中角色名称，表示当前用户拥有的角色。</p>
			<table style="margin: 5px;text-align: left">
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/include/images/close_folder.png" />
					</td>
					<td>：模块类别</td>
				</tr>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/include/images/open_folder.png" />
					</td>
					<td>：功能名称</td>
				</tr>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/include/images/close_folder_yellow.png" />
					</td>
					<td style="text-align:left;">：一级菜单</td>
				</tr>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/include/images/leaf.png" />
					</td>
					<td style="text-align:left;">：二级菜单</td>
				</tr>
			</table>
			<div class="btnSave"  style="text-align: left;margin: 20px 5px 5px 5px">
				<img style="cursor: pointer;" onclick="save()" src="<%= request.getContextPath()%>/include/images/submit.jpg" />
			</div>
	</div>
	
	<div id="rMenu" style="white-space:nowrap" >
		
	</div>
	
	
		</div>
		</div>
</body>
</html>
