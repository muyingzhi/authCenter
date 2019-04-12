
function  lodingDivShow(url){
	var loadHtml = "<div  id='iframe_span' style='width:100%;top:120px;position:absolute;" +
		"text-align:center;'> " +
		"</div>";

	document.getElementById('iframLoadDiv').style.display='';
	//document.getElementById('iframLoadDiv').style.width=window.screen.width;
	document.getElementById('iframLoadDiv').style.visibility='visible';	
	var h1=document.body.scrollTop;
	if(document.body.scrollTop==0){
		h1=document.documentElement.scrollTop;
	}
	document.getElementById('iframLoadDiv').style.height=document.body.scrollHeight;
	document.getElementById('iframLoadtd').style.top=h1+"px";
	document.getElementById('iframLoadtd').innerHTML =loadHtml;
	var   msgw,msgh,bordercolor;   
	var loding_str="请稍侯......";
	msgw=280;//提示窗口的宽度    
	msgh=100;//提示窗口的高度 
	bordercolor="#336699";//提示窗口的边框颜色 
	var imgUrl=url+"/include/images/progress.gif";
	var   msgObj=document.createElement("div");//创建一个div对象（提示框层）
	msgObj.setAttribute("id","msgDiv");    
	msgObj.setAttribute("align","center");    
	msgObj.style.background="white";    
	msgObj.style.border="1px   solid  "   +   bordercolor;    
	msgObj.style.position   =  "absolute";    
	msgObj.style.left   =  "50%";    
	msgObj.style.top   =  "50%";    
	msgObj.style.font="13px/1.6em   Verdana,   Geneva,   Arial,   Helvetica,   sans-serif";    
	msgObj.style.marginLeft   =  "-140px"   ;    
	msgObj.style.marginTop   =   -50+document.documentElement.scrollTop+"px";    
	msgObj.style.width   =   msgw   +  "px";    
	msgObj.style.height   =msgh   +  "px";    
	msgObj.style.textAlign   =  "center";    
	msgObj.style.lineHeight   ="25px";    
	msgObj.style.zIndex   =  "10001";    
	document.getElementById("iframe_span").appendChild(msgObj);//在body内添加提示框div对象msgObj 
	
	var   txt=document.createElement("p");//创建一个p对象（提示框提示信息）    
	//定义p的属性，即相当于    
	txt.style.margin="1em   0";    
	txt.setAttribute("id","msgTxt");    
	txt.innerHTML="<font color='green' style=''>"+loding_str+"</font>";//来源于函数调用时的参数值    
	//创建一个IMG
	var   img=document.createElement("img");
	img.setAttribute("src",imgUrl);
	document.body.appendChild(msgObj);//在body内添加提示框div对象msgObj 
	document.getElementById("msgDiv").appendChild(txt);//在提示框div中添加提示信息对象txt    
	document.getElementById("msgDiv").appendChild(img);//在提示框div中添加按钮对象button  
	
	
	//setTimeout(function(){loadingDivCloe();},10000);
}

function loadingDivCloe(){
	if(document.getElementById('iframLoadDiv') !=null){
		document.getElementById('iframLoadDiv').style.display='none';
		document.getElementById('iframLoadDiv').style.visibility='hidden';
	}
	if(document.getElementById('bgDiv') !=null){
        document.body.removeChild(document.getElementById("bgDiv"));
	}
	if(document.getElementById('msgDiv') !=null){
		document.body.removeChild(document.getElementById("msgDiv"));
	}
}




