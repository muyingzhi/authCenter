<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- include/tanchukuang.jsp -->
		<%
		if (request.getServerPort() == 80) {
		%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%
		} else {
		%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%
		}
		%>
		<title></title>
		
<link href="<%=request.getContextPath() %>/include/css/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" >

	 var pop_callback;
	 var pop_callback1=null;
	 var pop_cancle_flag;
	 var pop_cancle_method;
	 var pop_cancle_method1=null;
	 //提示框value=需要提示的信息，callback=确认的方法，cancle_flag根据不同的值走不同的方法(“0”,点取消无方法;"1",点取消有方法;"2",alert样式(callback需要获取焦点时，传入ID，不能传方法))，cancle_method=点取消的方法可以取null
	function  pop_win_setTitle(value,callback,cancle_flag,cancle_method){
		var pop_all=document.getElementById("pop_all");
		 var popalldis=pop_all.style.display;
		 if(popalldis!="none"){
			 return;
		 } 
		document.documentElement.style.overflow='hidden';
		var backcolor = "#EEEEEE";
		var   sWidth,sHeight;    
		/* sWidth=window.screen.width;//浏览器工作区域内页面宽度    
		sHeight=window.screen.height;//屏幕高度（垂直分辨率）    */
 		sWidth=document.body.offsetWidth;//浏览器工作区域内页面宽度    
 		sHeight=window.screen.height;//屏幕高度（垂直分辨率） 
		//sHeight=document.body.offsetHeight;//浏览器工作区域内页面高度
		//alert("sWidth="+sWidth+";sHeight="+sHeight);
		//背景层（大小与窗口有效区域相同，即当弹出对话框时，背景显示为放射状透明灰色）    
		var   bgObj=document.createElement("div");//创建一个div对象（背景层） 
		//定义div属性，即相当于    
		bgObj.setAttribute("id","bgDiv");   
		//alert("***********")   
		bgObj.style.position="absolute";    
		bgObj.style.top="0";    
		bgObj.style.background=backcolor;    
	//	bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";    
		 bgObj.style.filter="alpha(Opacity=60);-moz-opacity:0.6;opacity: 0.6";    
		bgObj.style.opacity="0.6";    
		bgObj.style.left="0";    
		bgObj.style.width=sWidth   +  "px";    
		bgObj.style.height=sHeight +  "px";    
		bgObj.style.zIndex   =  "10000";    
	//	document.createElement('div');(bgObj);//在body内添加该div对象    
		document.body.appendChild(bgObj);//在body内添加该div对象    
	
		 pop_all.style.position="absolute";		//
		 pop_all.style.zIndex="9998";				//最高层
		 var h1=document.body.scrollTop;					//网页被卷去的高
			if(document.body.scrollTop==0){
				h1=document.documentElement.scrollTop;
			}
		 var h2=document.body.scrollLeft;
		 if(h2==0){
			 h2=document.documentElement.scrollLeft;
		 }
		
		 //pop_all.style.width=document.body.offsetWidth;
		 pop_all.style.height=h1+document.documentElement.scrollHeight+"px"; //正文高度
		 pop_all.style.width=h2+document.documentElement.scrollWidth+"px";	//正文宽度
		 //alert(pop_all.style.width+","+pop_all.style.height+","+h1+","+h2);
		 //pop_all.style.height=window.screen.height;
		// pop_all.style.height=document.body.scrollHeight;
		 pop_all.style.background="#EEEEEE";
		 pop_all.style.filter="alpha(Opacity=0);-moz-opacity:0;opacity:0";		//透明度
		 pop_all.style.opacity="0";					//透明度
		 pop_all.style.display="";
		 var pop_window=document.getElementById("pop_window");
		pop_window.style.position="absolute";		//
		pop_window.style.zIndex="9999";				//最高层
		pop_window.style.display="";				//显示DIV
		var popWidth= document.body.clientWidth;		//网页可见区域宽
		var popHeight=document.documentElement.clientHeight;		//网页正文全文高
		var popWindowHeight=pop_window.offsetHeight;	//DIV的高度
		var popWindowWidth=pop_window.offsetWidth;		//DIV的宽度
		if(popWidth==0){
			popWidth=document.documentElement.clientWidth;
		}
		if(popHeight==0){
			popHeight=document.body.clientHeight;
		}
					
		//pop_window.style.top=h1+(popHeight-h1-popWindowHeight)/2-35+"px";	//高度居中
		
		pop_window.style.top=h1+(popHeight - popWindowHeight) / 2+ "px"; //页面居中
		
		//pop_window.style.left=popWidth1/2+"px";			//宽度居中
		pop_window.style.left=h2+(popWidth - popWindowWidth-h2)/ 2 + "px"; //页面居中
		//alert(h1+","+h2+","+document.documentElement.clientHeight+","+h3+","+pop_window.style.top+","+popHeight+","+(popHeight - popWindowHeight));
		document.getElementById("tdtext").innerHTML=value;			//替换显示的内容
		
		pop_window.style.zIndex   =  "10001";    
		document.body.appendChild(pop_window);//在body内添加提示框div对象pop_window 
		
		pop_callback=callback;
		//确定要走的方法，走之前先清除前一个添加的点击事件
		if(pop_callback1==null&&typeof callback=='function'){		
			pop_callback1=callback;
		}else if(typeof pop_callback1=='function'){
			if(window.attachEvent){	
				document.getElementById("pop_confirm").detachEvent("onclick",pop_callback1);
			}else{
				document.getElementById("pop_confirm").removeEventListener("click",pop_callback1);
			}
			pop_callback1=callback;
		}
		//取消要走的方法，走之前先清除前一个添加的点击事件
		if(pop_cancle_method1==null&&typeof cancle_method=='function'){
			pop_cancle_method1=cancle_method;
		}else if(typeof pop_cancle_method1=='function'){
			if(window.attachEvent){	
				document.getElementById("pop_cancel").detachEvent("onclick",pop_cancle_method1);
			}else{
				document.getElementById("pop_cancel").removeEventListener("click",pop_cancle_method1);
			}
			pop_cancle_method1=cancle_method;
		}
		pop_cancle_flag=cancle_flag;
		pop_cancle_method=cancle_method;
		document.getElementById("td_cancel").style.display="";		//显示取消按钮
		//callback不为空，添加点击事件
		if(callback!=null&&typeof callback=='function'){
		document.getElementById("pop_confirm").parentNode.style.width="50%";
		if(window.attachEvent){	
			document.getElementById("pop_confirm").attachEvent("onclick",callback);			
		}else{
			document.getElementById("pop_confirm").addEventListener("click",callback,false);
			
		}
			}
		//cancle_flag为1，添加取消的点击事件
		if(cancle_flag=="1"){
		document.getElementById("pop_confirm").parentNode.style.width="50%";
			if(window.attachEvent){
				document.getElementById("pop_cancel").attachEvent("onclick",cancle_method);				
			}else{
				document.getElementById("pop_cancel").addEventListener("click",cancle_method,false);
				
			}
		}
		//cancle_flag为2，是alert样式，隐藏取消按钮
		if(cancle_flag=="2"){
			//alert("2");
			
			document.getElementById("td_cancel").style.display="none";
			document.getElementById("pop_confirm").parentNode.style.width="100%";
		}
	}
	//取消点击事件
	function pop_cancel_fun(){
		//清除确定，添加的点击事件的方法
		if(window.attachEvent){	
			if(pop_callback!=null&&typeof pop_callback=='function'){
				document.getElementById("pop_confirm").detachEvent("onclick",pop_callback);
			}
	
		}else{
			if(pop_callback!=null&&typeof pop_callback=='function'){
				document.getElementById("pop_confirm").removeEventListener("click",pop_callback);
			}			
		}
		//隐藏整个DIV
		document.getElementById("pop_window").style.display="none";
		//隐藏遮蔽层
		document.getElementById("pop_all").style.display="none";
		if(document.getElementById('bgDiv') !=null){
        	document.body.removeChild(document.getElementById("bgDiv"));
		}
		document.documentElement.style.overflowY='inherit';
	}
	//确定点击事件
	function pop_ok_fun(){
		//隐藏整个DIV
		document.getElementById("pop_window").style.display="none";	
		//隐藏遮蔽层
		document.getElementById("pop_all").style.display="none";
		//pop_cancle_flag为2，获取所传值的焦点
		if(pop_cancle_flag=="2"&&pop_callback!=null&&typeof pop_callback!='function'){
			document.getElementById(pop_callback).focus();
		}
		if(document.getElementById('bgDiv') !=null){
        	document.body.removeChild(document.getElementById("bgDiv"));
		}
		document.documentElement.style.overflowY='inherit';
	}
</script>
<div id="pop_all" style="display:none"></div>
<div class="pop_window" style="display:none" id="pop_window">
	 <div class="pop_title">
		<h1 >提示</h1>
	 </div>
	 <div class="pop_content">
		<table style="background-color:#FFFFFF;border: 0px solid" id="pop_content_id_1">
			<tr><td colspan="2" class="info" id="tdtext" >是否确认提交</td></tr>
			<tr class="operation"><td><button class="confirm" style="cursor: hand;" id="pop_confirm" onclick="pop_ok_fun();">确认</button></td><td id="td_cancel"><button class="cancel" id="pop_cancel" style="cursor: hand;" onclick="pop_cancel_fun()">取消</button></td></tr>
		</table>
	 </div>
</div>