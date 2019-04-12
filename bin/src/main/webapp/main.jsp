<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.tianjian.login.bean.LoginSecondForm" %>
<%@page import="com.tianjian.login.bean.SessionForm" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <%
        SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");

        String staffId = sessionForm.getStaffId();
        String roleId = "";
        LoginSecondForm loginSecondForm = (LoginSecondForm) request.getAttribute("loginSecondForm");
        String roleIds[] = loginSecondForm.getRolesId();
        if (roleIds != null && roleIds.length > 0) {
            for (String ro : roleIds) {
                roleId += "'" + ro + "',";
            }
        }

        if (roleId.length() > 1) {
            roleId = "(" + roleId.substring(0, roleId.length() - 1) + ")";
        } else {
            roleId = "('" + "')";
        }
    %>

    <meta charset="UTF-8">
    <title>决策分析系统首页</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/include/jquery-easyui-1.5.2/themes/default/easyui.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/jquery.mCustomScrollbar.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/common.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/include/jquery-easyui-1.5.2/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/include/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/include/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/include/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/include/js/loading.js" defer="defer"></script>

</head>


<body>

<%@include file="/common/loadingDivPage.jsp" %>
<div class="left-content">
    <div class="logo">
        <img src="<%=request.getContextPath()%>/include/images/logo.png" alt=""/>

        <p>宝石花医疗集团决策分析系统</p>

    </div>
    <div class="nav2">
        <ul class="accordion">
            <li class="level curr">
                <a id="homePage" class="today-work">首页</a>
            </li>
        </ul>
        
      
   		<ul class="accordion" id="systemSetting">

  
            <li class="level">
                <a href="#" class="goods-manage">系统设置</a>
            </li>
            <ul id="menulist" class="submenu"  style="display: block"></ul>


        </ul>
    </div>
</div>
<div class="right-container">
    <div class="header clear">
        <strong id="navigation">首页</strong>
        <ul class="fr">
            <li class="fl" style="width: auto;">
                <span class="name"><%= request.getAttribute("staffName") %></span>
            </li>
            <li class="fl">

                <a href="javascript:void(0)"
                   onclick="$('#changePassword').dialog('open')">
                    <span class="msg">修改密码</span>
                </a>
            </li>

            <li class="fl">
                <a href="javascript:void(0)"
                   onclick="$('#logout').dialog('open')" class="exit">
                    <img src="<%=request.getContextPath()%>/include/images/loginout.png"/>
                    <br>退出系统
                </a>
            </li>
        </ul>
    </div>
    <div class="multi_iframe_div">
        <iframe src="<%=request.getContextPath() %>/login/homeData.do" width="100%" height="100%" frameborder="0"
                height="100%" style="position: relative;" name="main"></iframe>
    </div>
    <div class="footer" style="position:absolute;bottom: 0px;width:100%">版权所有：宝石花医疗集团</div>
</div>

<!--确认退出登录弹出框-->
<div id="logout" title="确认退出登录" modal="true" style="width: 400px; height: 200px;display: none;">
    <div style="padding: 27px 0 40px 27px;font-size: 16px;">
        您确定要退出登录吗？
    </div>
    <div>
        <span class="graybtn fr cancel mr30">取消</span>
        <a class="lightbluebtn fr sure mr30" style="width:90px" href="<%=request.getContextPath() %>/login/loginOut.do">确定</a>
    </div>
</div>

<input value="<%=staffId %>"/>

<!--修改密码弹出框  changePassword-->
<div id="changePassword" title="修改密码" modal="true" style="width: 400px; height: 300px;display: none;">
    <div style="padding: 27px 0 40px 27px;font-size: 16px;">
        <table>
            <tr>
                <td>当前密码：</td>
                <td><input id="initPassword" type="password" style="width:200px"/></td>

            </tr>
            <tr>
                <td>输入密码：</td>
                <td><input id="firstPassword" type="password" style="width:200px"/></td>
            </tr>

            <tr>
                <td>确认密码：</td>
                <td><input id="secondPassword" type="password" style="width:200px"/></td>

            </tr>

        </table>

    </div>


    <div>
        <span class="graybtn fr cancel mr30" onclick="reset();">重置</span>
        <a class="lightbluebtn fr sure mr30" style="width:90px" onclick="changePassword()">确定</a>
    </div>
</div>


<script type="text/javascript">

    function reset() {
        $("#changePassword input").val("");
    }

    function changePassword() {
        var staffId = "<%=staffId%>";
        var initPassword = document.getElementById('initPassword').value.trim();
        var firstPassword = document.getElementById('firstPassword').value;
        var secondPassword = document.getElementById('secondPassword').value;

        var arr = new Array();
        arr = firstPassword.split(" ");
        if (arr.length != 1) {
            alert("密码不能含有空格！");
            return;
        }

        var arr1 = new Array();
        arr1 = secondPassword.split(" ");
        if (arr1.length != 1) {
            alert("确认密码不能含有空格！");
            return;
        }

        if (initPassword == null || initPassword == '') {
            alert("请输入当前密码！");
            return;
        }
        if (firstPassword == null || firstPassword == '') {
            alert("密码为空！请输入密码！");
            return;
        }
        if (secondPassword == null || secondPassword == '') {
            alert("密码为空！请再次密码！");
            return;
        }

        if (firstPassword != secondPassword) {
            alert("两次输入的密码不一致！");
            return;
        }

        $.ajax({
            url: "<%=request.getContextPath() %>/security/securityStaffPasswordChangeUpdate.do?staffId=" + staffId + "&firstPassword=" + firstPassword
            + "&initPassword=" + initPassword,
            cache: false,
            async: false,
            success: function (data) {

                if (data == "修改密码成功！") {
                    $("#changePassword input").val("");
                    //$(".panel window").css("display","none");
                    $(".window").css("display", "none");
                    $(".window-shadow").css("display", "none");
                    $(".window-mask").css("display", "none");
                }
                alert(data);
            }
        });
    }

    $(window).resize(function () {
        $(".multi_iframe_div").height($(window).height() - 97);
    });

    $(function () {
        $('#changePassword').css({display: 'block'}).dialog({
            closed: true
        });
        $('#logout').css({display: 'block'}).dialog({
            closed: true
        });
        var roleId = "<%=roleId %>";

        $(".nav2").mCustomScrollbar({
            axis: "yx"
        });
     
            
            
        //--获取权限菜单
        $.ajax({
            url: "<%=request.getContextPath() %>/login/api/menulist.do?roleId=" + roleId,
            cache: false,
            async: false,

            success: function (list) {
                let $ul = $("#menulist");
                let $ulChild;
                let groupCode = "";
                
                
                var url = "<%=request.getContextPath()%>/";
                for (let i = 0; i < list.length; i++) {
                    let one = list[i];

                    if(one.type==1){
                    	let $li = $("<li/>");
                        $li.addClass("level");
                        $li.append("<a hrefUrl = '" + url + one.pageUrl + "' > " + one.name + "</a>");
                        $ul.append($li);
                        $ulChild = $("<ul/>");
                    }

                }
                //最后一组加入
                $ul.append($ulChild);
                setNav2Click();

            }
        });

        
        
        

        $('#homePage').on("click", function () {
            let rightURL = "<%=request.getContextPath() %>/login/homeData.do";
            $(".multi_iframe_div iframe").attr("src", rightURL);

            $("#navigation").text("首页");
        });
        function setNav2Click() {
            $('.nav2').on("click", "li", function () {
                $(this).parents(".accordion").siblings().find("li").removeClass("curr");
                $(this).parents(".accordion").find("li").removeClass("curr");
                $(this).addClass("curr").siblings().removeClass("curr");
                $(this).next(".submenu").slideToggle();
                $(this).next(".submenu3").slideToggle();
                if ($(this).hasClass("unfold")) {
                    $(this).removeClass("unfold").addClass("fold");
                } else if ($(this).find("a").length > 0) {
                    if ($(this).parent().hasClass("submenu3") || $(this).parent().hasClass("submenu")) {
                        //----指标
                        let title = $(this).find("a").text();
                       

                        $("#navigation").text(title);
                        let targetCode = $(this).find("a").attr("data");
                        let hrefUrl = $(this).find("a").attr("hrefUrl");
                       
                        $(".multi_iframe_div iframe").attr("src", hrefUrl);
                    }
                }


            });
        }

        $(".multi_iframe_div").height($(window).height() - 97);


        $("#logout").on("click", ".cancel", function () {
            $("#logout").dialog('close');
        });
    });

    /**
     * 切换导航名称
     * @param id 标签id
     */
    function getInnerTEXT(id) {
        var a = document.getElementById(id);
        var b = a.innerText;
        var c = document.getElementById("navigation");
        c.innerText = b;
    }
</script>
</body>
</html>
