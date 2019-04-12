<%--
  User: wub
  Date: 2018/4/7
  Time: 17:19template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.tianjian.util.Converter"%>
<%@page import="java.lang.String"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<jsp:useBean id="data" scope="request"
	class="com.tianjian.hsp.bean.HspStaffBaseinfoForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

<title>人员信息维护</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/include/css/common1.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/include/css/ZhuanJiaZhuChe.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/loading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/prompt.js"></script>
<link type="text/css" href="<%=request.getContextPath()%>/include/css/prompt.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/validateIdCard.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/jquery.form.js"></script>
<script type="text/javascript">

        $(function(){
            //添加人员
            $(".add_doc").click(function(){
            	var h1=document.documentElement.scrollTop+50;
                
                $(".doc_info_cont").css("top",h1+"px");
            	
                $("input[name='verbId']").val("hspStaffSave");
                $("input[name='hspStaffId']").val("");
                //document.getElementById("photo").innerHTML="<img id='scalpture' style='width: 100%;height: 160px' src='<%=request.getContextPath()%>/include/images/scalpture.jpg'/>";
                $("#name").val("");
                $("#idNo").val("");
                $("#commConfigSexId").val("");
                $("#birthday").val("");
                $("input[name='securityStaffBaseinfoId']").val("");
                $("#userName").val("");
                $("#hspConfigBaseinfoId").val("${dataForm.hspConfigBaseinfoId}");
                $("#hspConfigBaseinfoName").val("${dataForm.hspConfigBaseinfoName}");
                $("#deptCode").val("");
                $("#commConfigStaffTypeId").val("");
                $("#mobilecode").val("");
              //  $("#major").val("");
              //  $("#comments").text("");
                $("input[name='roleId']:checkbox").attr("checked",false);
                $(".mask").show();
                document.getElementById("zhegaicheng").style.display = "block";
            });
            $(".pop_close").click(function(){
                $(this).closest(".mask").hide();
                document.getElementById("zhegaicheng").style.display = "none";
            });
           
        });

        
        
        
        function openHspStaffUpdate(hspStaffId,staffCode){

        	var inputs=document.getElementsByName("roleId");     
        	for(var i=0;i<inputs.length;i++){
        		if(inputs[i].type=="checkbox"){
        			inputs[i].checked=false;
        		}
        	}
        	
       
            //设置弹出框的位置
            var y = getMousePos(event);
           // var h = $(document.body).height();//浏览器当前窗口文档body的高度
           
            var h1=document.documentElement.scrollTop+50;
            
            $(".doc_info_cont").css("top",h1+"px");

            <%--window.parent.lodingDivShow('<%=request.getContextPath()%>');--%>
            $.getJSON("<%=request.getContextPath()%>/hspStaffInfo/updateStaffInit.do",
                {"hspStaffId":hspStaffId},
                function(data){
//                    window.parent.loadingDivCloe();
                    if(data){
           
                    	
                        var json = data[0];
                        $("input[name='hspStaffId']").val(json.hspStaffId);
                        $("#name").val(json.name);
                        $("#idNo").val(json.idNo);
                        $("#commConfigSexId").val(json.commConfigSexId);
                        $("#birthday").val(json.birthday);
                        $("input[name='securityStaffBaseinfoId']").val(json.securityStaffBaseinfoId);
                        $("#userName").val(json.userName);
                        $("#hspConfigBaseinfoId").val(json.hspConfigBaseinfoId);
                        $("#hspConfigBaseinfoName").val(json.hspConfigBaseinfoName);
                        $("#commConfigStaffTypeId").val(json.commConfigStaffTypeId);
                        $("#deptCode").val(json.deptCode);
                        $("#mobilecode").val(json.mobilecode);
                        var roleIds = json.roleIds;
                        if(roleIds != null && roleIds != ""){
                            var roleIdArray = roleIds.split(",");
                            for(var i=0;i<roleIdArray.length;i++){
                                $("input[id='"+roleIdArray[i]+"']:checkbox").attr("checked",true);
                            }
                        }
                        $("input[name='verbId']").val("updateHspStaff");
                        document.getElementById("zhegaicheng").style.display = "block";
                        $(".mask").show();
                    }
                });
        }

        function idCardCheck(idCard){
            if(idCard.value == null || idCard.value == ""){
                return false;
            }
            if(!IdCardValidate(idCard.value)) {
                pop_win_setTitle("请输入正确的身份证号码！",function(){$(idCard).focus();},"2",null);
                return false;
            }
            //验证身份证的重复性
           $.post("<%=request.getContextPath()%>/hspStaffInfo/idNoCheck.do",
                {"idCard":idCard.value},
                function(data){
                    if(data.flag == "exist" && data.securityStaffBaseinfoId != $("input[name='securityStaffBaseinfoId']").val()){
                        pop_win_setTitle("身份证已经被用户名为"+data.name+"登陆",function(){
                            $(idCard).val("");
                            $(idCard).focus();
                        },"2",null);

                        return false;
                    }
                },
                'json'
            );
        }

        function setTime(){
            if(document.getElementById("idNo").value.length==18){
                var time=document.getElementById("idNo").value;
                var year=time.substring(6,10);
                var month=time.substring(10,12);
                var day=time.substring(12,14);
                document.form.birthday.value=year+"-"+month+"-"+day;

            }
        }
        function checkGender(){
            var idNumber= $("#idNo").val();
            if(idNumber.length == 18){
                var strsex=idNumber.substr(16, 1);
                if(idNumber!=null){
                    if(strsex%2==0){
                        $("#commConfigSexId").val("3");
                    }else{
                        $("#commConfigSexId").val("2");
                    }
                }
            }
        }

        function huiche(){
            if(event.keyCode==13){
                event.keyCode=9;
            }
        }

		function checkTel(tel) {
			  var message="";
		      var regTel1 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(tel);//带区号的固定电话
		      var regTel2 = /^(\d{7,8})(-(\d{3,}))?$/.test(tel);//不带区号的固定电话
		      var pattern=/^1[3|4|5|8|7][0-9]\d{4,8}$/.test(tel); 
		      if (tel != "") {
		        if (!regTel1 && !regTel2&&!pattern) { 
		          message="手机号码格式输入有误！请重新输入手机号码！";
		          return message;
		        }
		      }else {
		        message="请输入电话号码！";
		        return message;
		      }
		      return true;
		    }
        
        
        function saveStaffInformation(){
        	var checkbox = document.getElementsByName("roleId");
			var count = 0;
			for(i=0; i < checkbox.length; i++){
				if(checkbox[i].checked){
					count++;
				}
			}
		        
			if(count == 0){
				alert("请选择角色！");
				return;
			}
      
         	var mobileCode=$("#mobilecode").val();
            var result = checkTel(mobileCode);
			if(result !=true){
                pop_win_setTitle(result ,function(){$("#mobilecode")[0].focus();},"2",null);
                return;
			}

         	var name=$("#name").val().trim();
            if(name == null || name == ""){
                pop_win_setTitle("姓名不能为空！",function(){$("#name")[0].focus();},"2",null);
                return;
            }
            var idNo=$("#idNo").val();
//            if(idNo == null || idNo == ""){
//                pop_win_setTitle("证件号码不能为空！",function(){$("#idNo")[0].focus();},"2",null);
//                return;
//            }
            var userName=$("#userName").val();
            if(userName == null || userName == ""){
                pop_win_setTitle("用户名不能为空！",function(){$("#userName")[0].focus();},"2",null);
                return;
            }
            var hspConfigBaseinfoId=$("#hspConfigBaseinfoId").val();
            if(hspConfigBaseinfoId == null || hspConfigBaseinfoId == ""){
                pop_win_setTitle("机构不能为空！",function(){$("#hspConfigBaseinfoId")[0].focus();},"2",null);
                return;
            }
            
            pop_win_setTitle("确定保存？",function(){
                window.parent.lodingDivShow('<%=request.getContextPath()%>');
                var verbId = $("#verbId").val();
                if ("hspStaffSave"==verbId) {
					document.getElementById("form").action="<%=request.getContextPath()%>/hspStaffInfo/hspStaffSave.do";
				}else if ("updateHspStaff"==verbId){
					document.getElementById("form").action="<%=request.getContextPath()%>/hspStaffInfo/updateHspStaff.do";
				}
                $("#form").submit();
            },"1",null);
        }
		/**
		*删除方法
		*/
        function deleteStaff(hspStaffId,staffName){
			
			
        	  pop_win_setTitle("确定删除"+staffName+"？",function(){
                  window.parent.lodingDivShow('<%=request.getContextPath()%>');
      				document.getElementById("form").action="<%=request.getContextPath()%>/hspStaffInfo/deleteStaff.do?hspStaffId="+hspStaffId;

                  $("#form").submit();
              },"1",null);
			
			return;
            
        }

        function showMessage(){
        	var message = document.getElementById("message").value;
            if(message != null && message != ""){
                pop_win_setTitle(message,function(){
                    window.parent.hspClickByHspId('${dataForm.hspConfigBaseinfoId}');
                },"2",null);
            }
        	document.getElementById("message").value = "";
        }

        function getMousePos(event) {
            var e = event || window.event;
            var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
            var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
            var x = e.pageX || e.clientX + scrollX;
            var y = e.pageY || e.clientY + scrollY;
            return y;
        }
        
        
    </script>
<style type="text/css">
.del span {
	position: absolute;
	right: 4px;
	top: 5px;
	width: 12px;
	height: 12px;
	display: block;
	cursor: pointer;
	background:
		url("<%=request.getContextPath()%>/include/images/close.png")
		no-repeat;
}

.del {
	display: inline-block;
	border: 1px solid #ccc;
	padding: 5px 18px 5px 5px;
	position: relative;
	margin-right: 5px;
	border-radius: 3px;
}

.add {
	background-color: #ecf6f8;
	border: 1px solid #abdfee;
	border-radius: 3px;
	color: #3db6f5;
	padding: 5px;
	cursor: pointer;
}

.delete {
	width: 70px;
	height: 28px;
	margin-top: 4.5px;
	display: inline-block;
	margin-left: 20px;
	background:
		url("<%=request.getContextPath()%>/include/images/delete.jpg");
}

.doc_info span {
	color: #f47b77;
	font-size: 18px;
}

.doc_info_cont input {
	line-height: 25px;
	vertical-align: middle;
}

#zhegaicheng {
	width: 100%;
	height: 100%;
	background-color: #fff;
	position: absolute;
	top: 0;
	left: 0;
	z-index: 99;
	opacity: 0.7;
	/*兼容IE8及以下版本浏览器*/
	filter: alpha(opacity = 70);
	display: none;
}

input[type="checkbox"] {
	-webkit-appearance: checkbox
}
</style>
</head>

<body
	onload="window.parent.loadingDivCloe();loadingDivCloe();showMessage()"
	style="width: 100%;">
	<%@ include file="/common/tanchukuang.jsp"%>
	<%@include file="/common/loadingDivPage.jsp"%>
	<form name="form" id="form"
		action="<%=request.getContextPath()%>/hspStaffInfo/hspStaffSave.do"
		method="post">
		<input type="hidden" id="message" value="${dataForm.message}" /> <input
			type="hidden" name="verbId" id="verbId" value="" /> <input
			type="hidden" name="securityStaffBaseinfoId" value="" /> <input
			type="hidden" name="hspStaffId" value="" /> <input type="hidden"
			name="userPassword" value="123456" />
		<!-- <input type="hidden" name="photoPath" value=""/>-->
		<span id="spanOutput" style="display: none;"></span>
		<div class="clear zhuanjia_cont">
			<!-- 添加机构人员 -->
			<div class="add_doc lf" id="${staffHspId}"></div>
			<!-- 人员详细列表-->
			<c:forEach items="${hspStaffDataList}" var="data">
				<div class="doc_detail_cont lf">
					<p class="doc_detail_title">
						${data[1]}
						<%--（${data[2]}）--%>
					</p>
					<div class="doc_detail">
						<ul>
							<li>用户名：${data[10]}</li>
							<li>科室：${data[8]}</li>
							<li>电话：${data[4]}</li>
							
						<div style="display: inline-block; width: 100%;">
							<div style="float: left; width: 60px; margin: 10px auto;">角色标签:</div>
							<div>
								<table style="border-collapse: separate; border-spacing: 2px;"
									id="roleTable">
									<c:forTokens items="${data[9]}" delims="," var="roleData">


										<c:forTokens items="${roleData}" delims="#" var="role"
											varStatus="status">
											<c:if test="${status.first}">
												<tr>
													<td><a class="del" id="${role}" name="${data[5]}">
											</c:if>
											<c:if test="${status.last}">
                                                ${role}
                                            </c:if>
										</c:forTokens>

										<span></span>
										</a>
										</td>
										</tr>
									</c:forTokens>
								</table>
							</div>
						</div>
					</div>
					<div class="bianji_cont" style="text-align: center;">
						<div class="bianji pointer"
							style="background: url('<%=request.getContextPath()%>/include/images/mod.png');margin-left: 0px;"
							onclick="openHspStaffUpdate('${data[0]}','${data[10]}');">
						</div>
						<div class="delete pointer"
							onclick="deleteStaff('${data[0]}','${data[1]}')"></div>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- 人员修改页面弹出框 -->
		<div id="zhegaicheng"></div>
		<div class="mask_doc mask">
			<div class="pop_up doc_info_cont"
				style="top: 50px; position: absolute; z-index: 100;">
				<p class="pop_up_title">
					人员编辑<i class="pop_close rt"></i>
				</p>
				<div class="doc_info_d">
					<div class="doc_info" style="padding: 20px 15px 0px 15px">
						<table border="0" style="width: 100%;">


							<tr>

								<td style="width: 15%;"><span>*</span>用户名：</td>
								<td><input type="text" name="userName" id="userName"
									value="" style="width: 98%;"
									onkeyup="value=value.replace(/[\W]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
									onkeydown="huiche()" maxlength="18" /></td>
								<td style="width: 15%;"><span>*</span>姓名：</td>
								<td><input type="text" name="name" id="name" value=""
									maxlength="13" style="width: 98%;" /></td>
							</tr>
							<tr>
								<td>证件号码：</td>
								<td colspan="3"><input type="text" id="idNo" name="idNo"
									value="" style="width: 99%;" onkeydown="huiche()"
									onblur="setTime();checkGender();idCardCheck(this);"
									onkeypress="eventOnKeyPress('empNo')" maxlength="18" /></td>
							</tr>
							<tr>
								<td><span>*</span>性别：</td>
								<td><select id="commConfigSexId" name="commConfigSexId"
									onkeydown="huiche()" style="width: 98%;">
										<c:forEach items="${dataForm.commConfigSexIdList}"
											var="sexDataBean">
											<option value="${sexDataBean.itemCode}">${sexDataBean.itemName}</option>
										</c:forEach>
								</select></td>
								<td>出生日期：</td>
								<td><input type="text" name="birthday" readonly="readonly"
									id="birthday" value=""
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
									onkeydown="huiche()" maxlength="10" style="width: 98%;" /></td>
							</tr>
							<tr>
								<td><span>*</span>机构：</td>
								<td><input type="hidden" id="hspConfigBaseinfoId"
									value="${dataForm.hspConfigBaseinfoId}"
									name="hspConfigBaseinfoId" /> <select id="hspConfigBaseinfoId"
									name="hspConfigBaseinfoName" onkeydown="huiche()"
									style="width: 98%;">
										<c:forEach items="${dataForm.hspConfigBaseinfoIdList}"
											var="hspConfigBaseinfo">
											<option value="${hspConfigBaseinfo.itemCode}">${hspConfigBaseinfo.itemName}</option>
										</c:forEach>
								</select></td>
								<td class="tblLable">科室：</td>
								<td><select id="deptCode" name="deptCode"
									onkeydown="huiche()" style="width: 98%;">
										<c:forEach items="${deptList}" var="deptArray">
											<option value=""></option>
											<option value="${deptArray[0]}">${deptArray[1] }</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td>人员类型：</td>
								<td><select id="commConfigStaffTypeId"
									name="commConfigStaffTypeId" onkeydown="huiche()"
									style="width: 98%;">
										<c:forEach items="${dataForm.commConfigStaffTypeIdList}"
											var="staffTypeDataBean">
											<option value="${staffTypeDataBean.itemCode}">${staffTypeDataBean.itemName}</option>
										</c:forEach>
								</select></td>
								<td><span>*</span>手机号码：</td>
								<td><input type="text" name="mobileTel" style="width: 98%;"
									value="" id="mobilecode" onkeydown="huiche()" maxlength="11"
									onkeyup="this.value=this.value.replace(/\D/gi,'')" /></td>
							</tr>


							
							</div>

						</table>
						<div style="margin-top: 10px; overflow: auto;">
							<p>
								<span>*</span>角色选择：
							</p>
							<table class="chuzhen_tab" style="white-space: nowrap;">
								<tr>
									<c:forEach items="${roleDataList}" var="role">
										<td style="padding: 0px 5px;"><label id="${role[0]}">${role[1]}</label></td>
									</c:forEach>
								</tr>
								<tr class="checkbox">
									<c:forEach items="${roleDataList}" var="role">
										<td><input style="width: 25px" type="checkbox"
											name="roleId" id="${role[0]}" value="${role[0]}" /></td>
									</c:forEach>
								</tr>
							</table>
						</div>
					</div>
					<div class="btn_cont">
						<div class="sav_btn_b" onclick="saveStaffInformation();"></div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>