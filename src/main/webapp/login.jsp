<%--
  Created by IntelliJ IDEA.
  User: wub
  Date: 2018/4/13
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/common.css" />
        <!--[if IE 8]>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/ods/css/none-eye.css" />
        <![endif]-->
        <script src="<%=request.getContextPath()%>/include/jquery-easyui-1.5.2/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/include/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
        <script src="<%=request.getContextPath()%>/include/js/jquery.placeholder.min.js"></script>
        <script src="<%=request.getContextPath()%>/include/js/loading.js" defer="defer"></script>
        <style>
            .content .head{height: 110px;color: #00707b;padding: 27px 0 0 73px;}
            .content .head img{margin-right: 8px;}
            .content .head div div{font-size: 21px;font-weight: bold;padding-top:6px;}
            .content .head div p{font-size: 13px;}
            body{font-size: 16px;}
            .content{width: 100%;}
            .img-cont{position: relative;margin: 0 auto;}
            .img-cont>img{width: 100%;height: 80%;}
            .img-cont .center{position: absolute;width: 100%;top:50%;margin-top: -210px;padding: 0 10% 0 5%;}
            .img-cont .center>img{margin-top: 45px;}
            /*登录框*/
            .login-h{font-size: 26px;color: #333;margin-bottom: 28px;}
            .login-h b{width: 4px;background: #00a0e4;height: 40px;border-radius: 2px;margin-right: 16px;}
            .login-h span{color: #999;font-size: 20px;margin-left: 15px;}
            .login-box{width: 355px;height: 100%;background: #fff;padding:27px 36px 34px 36px;border-radius: 5px;}
            .login-box .user,.login-box .pwd,.login-box .veri{border: 1px solid #ccc;width:100%;padding-left:38px;border-radius: 3px;height:40px;font-size: 16px;line-height:36px;}
            .login-box .veri{width: 178px;}
            .login-box .pwd-d,.login-box .veri-d,.login-box .user-d{position: relative;}
            .login-box .user-bg,.login-box .pwd-bg,.login-box .veri-bg{height:38px;position: absolute;width:38px;background:#fff url(<%=request.getContextPath()%>/include/images/user.png) 11px center no-repeat;left:1px;top:1px;border-radius: 3px;}
            .login-box .pwd-bg{background:#fff url(<%=request.getContextPath()%>/include/images/pwd.png) 11px center no-repeat;}
            .login-box .pwd-d{margin-top: 20px;margin-bottom: 20px;position: relative;}
            .login-box .pwd-d i.pwd-contr{position: absolute;right:1px;width:38px;height:38px;top:1px;background:#fff url(<%=request.getContextPath()%>/include/images/visible.png) center no-repeat;cursor: pointer;}
            .login-box .pwd-d i.pwd-contr.hide{background:#fff url(<%=request.getContextPath()%>/include/images/invisible.png) center no-repeat;border-radius: 3px;}
            .user::-webkit-input-placeholder,.pwd::-webkit-input-placeholder,.veri::-webkit-input-placeholder{color: #b3b3b3;font-size: 16px;}
            .login-box .veri-bg{background:#fff url(<%=request.getContextPath()%>/include/images/veri.png) 11px center no-repeat;}
            .login-box .veri-code{width:93px;height:40px;border: 1px solid #ccc;border-radius: 3px;margin-left: 12px;}
            .rem-psd{font-size: 14px;margin-bottom:15px;color: #666;margin-top:15px;}
            .rem-psd input[type="checkbox"]{width: 14px; margin-right: 6px; width: 18px\9;vertical-align: sub;}
            input[type="button"]{width: 100%;height: 40px;background: #01a0e4;color: #fff;font-size: 20px;}
            a.forget-psd{color: #666;text-decoration: underline;}
            a.register{color: #00A0E4;}
            .bottom{text-align: center;position:relative;width: 100%;top: 10px;color: #01636e;}
            /*获取焦点*/
            .user-d.focus .user-bg{background:#fff url(<%=request.getContextPath()%>/include/images/user-b.png) 11px center no-repeat;}
            .pwd-d.focus .pwd-bg{background:#fff url(<%=request.getContextPath()%>/include/images/pwd-b.png) 11px center no-repeat;}
            .veri-d.focus .veri-bg{background:#fff url(<%=request.getContextPath()%>/include/images/veri-b.png) 11px center no-repeat;}
            .user-d.focus .user{border: 1px solid #01a0e4;}
            .pwd-d.focus .pwd{border: 1px solid #01a0e4;}
            .veri-d.focus .veri{border: 1px solid #01a0e4;}
        </style>
    </head>
    <body>
        <%@ include file="/common/tanchukuang.jsp" %>
        <%@include file="/common/loadingDivPage.jsp" %>
        <form name="form" method="post" id="form">
            <input type="hidden"  name="mode" id="mode" value=""/>
            <div class="content">
                <div class="head clear">
                    <img src="<%=request.getContextPath()%>/include/images/logo-b.png" alt="" class="fl"/>
                    <div class="fl">
                        <div>宝石花医疗集团决策分析系统</div>
                        <p>GEM FLOWER HEALTHCARE Decision Analysis System</p>
                    </div>
                </div>
                <div class="inner">
                    <div class="img-cont">
                        <img src="<%=request.getContextPath()%>/include/images/login-bg.png" alt="">
                        <div class="clear center">
                            <img src="<%=request.getContextPath()%>/include/images/chart.png" alt="" class="fl"/>
                            <div class="login-box fr">
                                <div class="login-h clear">
                                    <b class="fl"></b>用户登录<span>User Login</span>
                                </div>
                                <div class="user-d"><input type="text" class="user" placeholder="请输入6~13位字母或数字" id="userId" name="staffCode"><i class="user-bg"></i></div>
                                <div class="pwd-d">
                                    <i class="pwd-bg"></i>
                                    <input type="password" class="pwd" placeholder="请输入密码" id="passwd" name="password">
                                    <i class="pwd-contr hide">

                                    </i>
                                </div>
                               <%-- <div class="clear">
                                    <div class="veri-d fl"><input type="text" class="veri fl" placeholder="请输入验证码" ><i class="veri-bg"></i></div>
                                    <div class="veri-code fl">

                                    </div>
                                </div>--%>
                                <div class="rem-psd clear">
                                    <label><input type="checkbox" id="saveCookie"><span>记住密码</span></label>
                                </div>
                                <input type="button" value="登录" onclick="login();" >
                               <%-- <div class="clear" style="margin-top: 22px;">
                                    <a href="" class="fl forget-psd">忘记密码</a>
                                    <a href="" class="fr register">新用户注册</a>
                                </div>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="bottom">
                Ⓒ 宝石花医疗集团
            </div>
            <script type="text/javascript">
                $(function(){
                    $('input[placeholder]').placeholder();
                });
                $('input[type="text"],input[type="password"]').focus(function(){
                    $(this).closest("div").addClass("focus");
                });
                $('input[type="text"],input[type="password"]').blur(function(){
                    $(this).closest("div").removeClass("focus");
                });

                $(".pwd-contr").click(function(){
                    var pass=$("#passwd");
                    if(pass.attr("type")=="password"){
                        pass.attr("type","text");
                        $(this).removeClass("hide");

                    }else if(pass.attr("type")=="text"){
                        pass.attr("type","password");
                        $(this).addClass("hide");
                    }
                });
                var _TheIdsArray = new Array();
                $(function(){
                    var  date = new Date();
                    var  deft = date.getFullYear();
                    /* document.getElementById("teamId_ndmb").value="";
                    document.getElementById("planYear_ndmb").value=deft; */
                    var saveCookie=getCookieValue("phcis_saveCookie");
                    document.getElementById("userId").value=getCookieValue("phcis_username");
                    if(saveCookie==1){
                        document.getElementById("passwd").value=unescape(getCookieValue("phcis_password"));
                        document.getElementById("saveCookie").checked=true;
                    }else{
                        //document.getElementById("userId").value="";
                        document.getElementById("passwd").value="";
                        document.getElementById("saveCookie").checked=false;
                    }
                });
                /**登录方法*/
                function login(){
                    $(".login-mask").hide();
                    /**获取表单账号和密码*/
                    var staffCode=document.getElementById("userId");
                    var password=document.getElementById("passwd");
                    /**记住密码*/
                    var saveCookie=document.getElementById("saveCookie");

                    setCookie("phcis_username",staffCode.value,1000,"");
                    if(saveCookie.checked){
                        setCookie("phcis_password",password.value,1000,"");
                        setCookie("phcis_saveCookie",1,1000,"");
                    }else{
                        deleteCookie("phcis_password","");
                        setCookie("phcis_saveCookie",0,1000,"");
                    }
                    <%--lodingDivShow('<%=request.getContextPath()%>');--%>

                    //var obj = document.form;
                    $.post(
                        "<%=request.getContextPath()%>/login/loginExecute.do?",
                        {"staffCode":staffCode.value,"password":password.value},
                        function(data){
                            if(data[0].result=='0'){
                                pop_win_setTitle(data[0].message,null,"2",null); //0 为登录失败，弹出失败信息
//                                loadingDivCloe();
                            }else if(data[0].result=='1'){
                                //1 为登录成功
                                if(data[0].resultBean!=null){
                                    for(var i=0;i<data[0].resultBean.length;i++){
                                        _TheIdsArray[i]=data[0].resultBean[i].id;  //将登录过后的角色菜单放入_TheIdsArray数组中
                                    }
                                    //dialogback_ndmb();
                                    //loadingDivCloe();
                                    setTimeout(function () {okToNav('QYFW','0012200','1','');}, 500);
                                }
                            } else if (data[0].result == '2') {//账户没有医生信息，弹出医生信息填写框
                                pop_win_setTitle(data[0].message, function () {
                                    $("#userId").focus();
                                }, "0", null);
//                                loadingDivCloe();
                            }
                        },
                        'json'
                    );
                }
                //新建cookie。
                //hours为空字符串时,cookie的生存期至浏览器会话结束。hours为数字0时,建立的是一个失效的cookie,这个cookie会覆盖已经建立过的同名、同path的cookie（如果这个cookie存在）。
                function setCookie(name,value,hours,path){
                    var name = escape(name);
                    var value = escape(value);
                    var expires = new Date();
                    expires.setTime(expires.getTime() + hours*3600000);
                    path = path == "" ? "" : ";path=" + path;
                    _expires = (typeof hours) == "string" ? "" : ";expires=" + expires.toUTCString();
                    document.cookie = name + "=" + value + _expires + path;
                }
                //获取cookie值
                function getCookieValue(name){
                    var name = escape(name);
                    //读cookie属性，这将返回文档的所有cookie
                    var allcookies = document.cookie;
                    //查找名为name的cookie的开始位置
                    name += "=";
                    var pos = allcookies.indexOf(name);
                    //如果找到了具有该名字的cookie，那么提取并使用它的值
                    if (pos != -1){                                  //如果pos值为-1则说明搜索"version="失败
                        var start = pos + name.length;               //cookie值开始的位置
                        var end = allcookies.indexOf(";",start);     //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置
                        if (end == -1) end = allcookies.length;      //如果end值为-1说明cookie列表里只有一个cookie
                        var value = allcookies.substring(start,end); //提取cookie的值
                        return (value);                              //对它解码
                    }
                    else return "";                                  //搜索失败，返回空字符串
                }
                //删除cookie
                function deleteCookie(name,path){
                    var name = escape(name);
                    var expires = new Date(0);
                    path = path == "" ? "" : ";path=" + path;
                    document.cookie = name + "="+ ";expires=" + expires.toUTCString() + path;
                }
                function okToNav(mode,publicId,flag,localPath){
//                    if(_TheIdsArray.length==0){
//                        //alert("未登录，请先登录");
//                        login();
//                        return;
//                    }
                    if(flag=='0'){ //系统外菜单
                        if(localPath.substring(0,4)=='http'){ // 系统外网站访问  直接  wind.open
                            window.open(localPath);
                        }else{ //系统外.exe 访问
                            //runEXE("exepath","params");
                        }
                    }else if(flag=='1'){
                        var classFlag=0;
                        for(var i=0;i<_TheIdsArray.length;i++){
                            if(publicId!=_TheIdsArray[i]){
                                classFlag++;
                            }
                        }
                        document.getElementById("mode").value=mode;
                        document.form.action="<%=request.getContextPath() %>/login/okTotopNav.do";
                        document.form.submit();
                    }
                }
            </script>
        </form>
    </body>

</html>
