var arrOptions = new Array();//弹出层的行数据
var ids = new Array();
var selectColumnNames = new Array();
var column_name = "";
var select_content = "";
var sql_content = "";
var sql_value = "";
var winHeight = 50;
var l_height = "";
var bottom_height = "";
var on_focus_id = "";
var when_null_is_clear = "";
var event_content = "";
var is_public = "";
var strLastValue = "";
var theTextBox; //输入字符的控件
var values = {};
var currentValueSelected = -1; //当前选择弹出层的行数
var mouseFlag = 0; //标识鼠标是否点击弹出层上的行，用来防止控件失去焦点时未将值放入控件就隐藏了弹出层
var width="";
//e:值为event
//url:执行数据查询的action访问路径
//arrayIds:需要赋值的页面标签id集合
//selectColumnName:在查询列表中显示的列名
//selectContent:在查询列表中显示的内容对应的表的字段名,需和selectColumnNames一一对应
//columnName:查询的字段名,先保证和arrayIds中的字段一一对应，顺序一致，再包含selectContent中没有的字段
//sqlContent:查询语句,中间条件用?占位
//sqlValue:sqlContent中?代表的值(除输入值) 第一个位置为输入值,之后和sqlContent中的?一一对应
//onFocusId:选择查询项后焦点所在标签的id,如果为空则焦点回到填写项
//whenNullIsClear:0,表示可以随意进行填写；1,当通过填写查询无结果时将值清空(即必须选择存在的)
//isPublic:是否公用  0:不公用(查询本租户下的数据) 1:查询为本租户或租户字段为空的数据   2:公用(查询所有数据) 需要在sqlContent中加上tenant_id作为查询条件
function GiveOptions(e, url,arrayIds,selectColumnName,selectContent,columnName,sqlContent,sqlValue,onFocusId,whenNullIsClear,isPublic) {
	winHeight = 50;
	l_height = "";
	bottom_height = "";	
	var eve = window.event ? window.event : (e ? e : null);
	var intKey = eve.keyCode || eve.which;
	theTextBox = eve.srcElement ? eve.srcElement : eve.target;
	if(arrayIds.indexOf('#')!=-1){
		ids = arrayIds.split("#");
	}else{
		ids = new Array();
		ids[0]=arrayIds;
	}
	if(selectColumnName.indexOf('#')!=-1){
		selectColumnNames = selectColumnName.split("#");
	}else{
		selectColumnNames = new Array();
		selectColumnNames[0]=selectColumnName;
	}
	event_content = eve;
	select_content = selectContent;
	column_name=columnName;
	sql_content = sqlContent;
	sql_value = sqlValue;
	on_focus_id = onFocusId;
	when_null_is_clear = whenNullIsClear;
	is_public = isPublic;
	if (window.attachEvent) {
		theTextBox.attachEvent("onblur", checkMouse);
	} else {
		theTextBox.addEventListener("blur", checkMouse, false);
	}
	//if (theTextBox.value.length == 0) {
	//	clearText();
	//	HideTheBox();
	//	strLastValue = "";
	//	return false;
	//}
	if (intKey == 13) {
		if (arrOptions.length != 0) {
			GrabHighlighted();
			return false;
		}
	} else {
		if (intKey == 38) {
			MoveHighlight(-1);
			return false;
		} else {
			if (intKey == 40) {
				MoveHighlight(1);
				return false;
			}
		}
	}
	if (values[theTextBox.id] == null) {
		values[theTextBox.id] == theTextBox.value;
	}
	if (theTextBox.value.indexOf(strLastValue) != 0 || arrOptions.length == 0 || (strLastValue.length == 0 && theTextBox.value.length > 0) 
			|| (theTextBox.value.length != strLastValue.length)||(strLastValue.length == 0 && theTextBox.value.length == 0)) {
		if(theTextBox.value.indexOf(strLastValue) == 0&&arrOptions.length == 0&&theTextBox.value.length > 0&&when_null_is_clear=="0"){
			
		}else{
			if (values[theTextBox.id] != theTextBox.value) {
				values[theTextBox.id] = theTextBox.value;
			}
			arrOptions = new Array();
			SetElementPosition();
			document.getElementById("spanOutput").innerHTML = "<font style='color:#a0a0a0'>\u6b63\u5728\u7528\u5173\u952e\u8bcd\u641c\u7d22\uff0c\u8bf7\u7a0d\u7b49...</font>";
			strLastValue = theTextBox.value;			
			TypeAhead(url, trimAll(theTextBox.value,'g'));
		}
	} else {
		SetElementPosition();
		BuildList(theTextBox.value);
		strLastValue = theTextBox.value;
	}
}
//width:自定义展示表格宽度
//其他参数参照上面
function GiveOptions(e, url,arrayIds,selectColumnName,selectContent,columnName,sqlContent,sqlValue,onFocusId,whenNullIsClear,isPublic,width2) {
	/*if(arrOptions.length == 0){
		alert(1);
		for(var j = 1 ; j < ids.length ; j++){
			document.getElementById(ids[j]).value = "";
		}
	}*/
	winHeight = 50;
	l_height = "";
	bottom_height = "";
	width=width2;
	var eve = window.event ? window.event : (e ? e : null);
	var intKey = eve.keyCode || eve.which;
	theTextBox = eve.srcElement ? eve.srcElement : eve.target;
	
	if(arrayIds.indexOf('#')!=-1){
		ids = arrayIds.split("#");
//		alert();
	}else{
		ids = new Array();
		ids[0]=arrayIds;
	}
	if(selectColumnName.indexOf('#')!=-1){
		selectColumnNames = selectColumnName.split("#");
	}else{
		selectColumnNames = new Array();
		selectColumnNames[0]=selectColumnName;
	}
	event_content = eve;
	select_content = selectContent;
	column_name=columnName;
	sql_content = sqlContent;
	sql_value = sqlValue;
	on_focus_id = onFocusId;
	when_null_is_clear = whenNullIsClear;
	is_public = isPublic;
	if (window.attachEvent) {
		theTextBox.attachEvent("onblur", checkMouse);
	} else {
		theTextBox.addEventListener("blur", checkMouse, false);
	}
	//if (theTextBox.value.length == 0) {
	//	clearText();
	//	HideTheBox();
	//	strLastValue = "";
	//	return false;
	//}
	if (intKey == 13) {
		if (arrOptions.length != 0) {
			GrabHighlighted();
			return false;
		}
	} else {
		if (intKey == 38) {
			MoveHighlight(-1);
			return false;
		} else {
			if (intKey == 40) {
				MoveHighlight(1);
				return false;
			}
		}
	}
	if (values[theTextBox.id] == null) {
		values[theTextBox.id] == theTextBox.value;
	}
	
	if (theTextBox.value.indexOf(strLastValue) != 0 || arrOptions.length == 0 || (strLastValue.length == 0 && theTextBox.value.length > 0) 
			|| (theTextBox.value.length != strLastValue.length)||(strLastValue.length == 0 && theTextBox.value.length == 0)) {
		if(theTextBox.value.indexOf(strLastValue) == 0&&arrOptions.length == 0&&theTextBox.value.length > 0&&when_null_is_clear=="0"){
			
		}else{
			if (values[theTextBox.id] != theTextBox.value) {
				values[theTextBox.id] = theTextBox.value;
			}
			arrOptions = new Array();
			SetElementPosition();
			document.getElementById("spanOutput").innerHTML = "<font style='color:#a0a0a0'>\u6b63\u5728\u7528\u5173\u952e\u8bcd\u641c\u7d22\uff0c\u8bf7\u7a0d\u7b49...</font>";
			strLastValue = theTextBox.value;
			TypeAhead(url, trimAll(theTextBox.value,'g'));
		}
	} else {
		SetElementPosition();
		BuildList(theTextBox.value,width);
		strLastValue = theTextBox.value;
	}

}

function TypeAhead(url, xStrText) {
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	if (req) {
		//
	    //req.setRequestHeader("Cache-Control","no-cache");
		req.onreadystatechange = callback;
		req.open("Post", url, true);
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		if(sql_value!=""&&sql_value!=null){
			sql_value = xStrText+"#"+sql_value;
		}else{
			sql_value = xStrText;
		}
		var param = "column_name="+column_name+"&sql_content="+sql_content+
		"&sql_value="+sql_value+"&select_content="+select_content+"&is_public="+is_public;
		req.send(param);
	}
}


function callback() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			parseMessage();
                 // update the HTML DOM based on whether or not message is valid
		} else {
			alert("\u670d\u52a1\u8c03\u7528\u5931\u8d25" + req.statusText);
		}
	} else {
	}
}


//后台回来的数据
function parseMessage() {
	var xmlDoc = req.responseXML.documentElement;
	if (xmlDoc) {
		var node = xmlDoc.getElementsByTagName("info");
		arrOptions = new Array();
		for (var i = 0; i < node.length; i++) {
			var temparr = new Array();
			for (var j = 0; j < node[i].childNodes.length; j++) {
				if ("hidden" == node[i].childNodes[j].nodeName) {
					temparr[j] = "<td style='display:none'>" + node[i].childNodes[j].firstChild.nodeValue + "</td>";
				} else {
					temparr[j] = node[i].childNodes[j].firstChild.nodeValue;
				}
			}
			arrOptions[i] = temparr;
		}
		BuildList(theTextBox.value);
		strLastValue = theTextBox.value;
	} else {
		document.getElementById("spanOutput").innerHTML = "<font style='color:#a0a0a0'>\u5bf9\u4e0d\u8d77\uff0c\u670d\u52a1\u8c03\u7528\u5931\u8d25\uff01</font>";
	}
}

function SetElementPosition() {
	var selectedPosX = 0;
	var selectedPosY = 0;
	var theElement = theTextBox;
	if (!theElement) {
		return;
	}
	var theElemHeight = theElement.offsetHeight;
	var theElemWidth = theElement.offsetWidth;
	bottom_height = document.body.clientHeight;
	while (theElement != null) {
		selectedPosX += theElement.offsetLeft;
		selectedPosY += theElement.offsetTop-theElement.scrollTop;
		theElement = theElement.offsetParent;
	}
	xPosElement = document.getElementById("spanOutput");
	xPosElement.style.left = selectedPosX + "px";
	xPosElement.style.width = theElemWidth + "px";
	xPosElement.style.top = selectedPosY + theElemHeight + "px";
	l_height = selectedPosY;
	bottom_height = bottom_height - selectedPosY - theElemHeight;
	xPosElement.style.display = "block";
}

function BuildList(theText) {
	var inner;
	if(width== undefined || width==""){
		inner = "<table id='buildTable' cellspacing='0' style='border-collapse: collapse;width:100%;white-space:nowrap;' >";
	}else{
		inner = "<table id='buildTable' cellspacing='0' style='border-collapse: collapse;width:"+width+";white-space:nowrap;' >";
	}
	if(selectColumnNames!=null&&selectColumnNames.length>0&&selectColumnNames[0]!=""){
		inner += "<tr>";
		for(var i=0;i<selectColumnNames.length;i++){
			inner += "<th style='height: 14px;padding-top:5px;padding-bottom:5px;'>"+selectColumnNames[i]+"</th>";
		}
		inner += "</tr>";
	}
	
	inner += "<tbody>";
	var theMatches = MakeMatches(theText);
	
	for (var i = 0; i < theMatches.length; i++) {
		inner += theMatches[i];
	}
	inner += "</tbody></table>";
	if (theMatches.length > 0) {
		document.getElementById("spanOutput").innerHTML = inner;
		document.getElementById("OptionsList_0").style.backgroundColor = "#e6e6e6";
		currentValueSelected = 0;
	} else {
		//未查询到结果，清空所以arrayIds数据
		if(arrOptions.length == 0){
			for(var j = 1 ; j < ids.length ; j++){
				$("#"+ids[j]).val("");
			}
		}
		if(when_null_is_clear=="1"){
			theTextBox.value = "";
		}
		if(when_null_is_clear=="1"||(when_null_is_clear=="0"&&theTextBox.value=="")){
			document.getElementById("spanOutput").innerHTML = "<font style='color:#a0a0a0'>\u672a\u627e\u5230\u76f8\u5e94\u641c\u7d22\u7ed3\u679c\uff01\u8bf7\u91cd\u65b0\u8f93\u5165</font>";
		}else{
			document.getElementById("spanOutput").innerHTML = "";
			if (window.attachEvent) {
				theTextBox.attachEvent("onkeydown", huiche);
			} else {
				theTextBox.addEventListener("onkeydown", huiche);
			}
		}
	}
}


function MakeMatches(xCompareStr) {
	countForId = 0;
	var matchArray = new Array();
	for (var i = 0; i < arrOptions.length; i++) {
		matchArray[matchArray.length] = CreateUnderline(arrOptions[i], xCompareStr, i);
		winHeight+=28;
	}
	if(winHeight>bottom_height){
	   document.getElementById("spanOutput").style.top=l_height-winHeight+"px";
	}
	return matchArray;
}


var redStart = "<font style='color:red'>";
var redEnd   = "</font>";
var selectSpanStart = "<tr onmouseover='SetHighColor(this); mouseFlag=1' onmouseout='mouseFlag=0' ";
var selectSpanEnd = "</tr>";
function CreateUnderline(strArr, xTextMatch, xVal) {
	selectSpanMid = "onclick='SetText(" + xVal + ")'" + " id='OptionsList_" + countForId + "' theArrayNumber='" + xVal + "'>";
	countForId++;
	var regExp = new RegExp(xTextMatch, "ig");
	var Replacestr = "";
	for (var i = 0; i < strArr.length; i++) {
		var value = strArr[i];
		if(value==null||value=="null")
			value = "";
		if (value.substring(0, 25) == "<td style='display:none'>") {
			Replacestr += value;
		} else {
			var start = value.search(regExp);
			var matchedText = value.substring(start, start + xTextMatch.length);
			Replacestr += "<td>" + value.replace(regExp, redStart + matchedText + redEnd) + "</td>";
		}
	}
	return selectSpanStart + selectSpanMid + Replacestr + selectSpanEnd;
}

function SetHighColor(theTr) {
	for (var i = 0; i < countForId; i++) {
		document.getElementById("OptionsList_" + i).style.backgroundColor = "";
	}
	if (theTr) {
		currentValueSelected = theTr.id.slice(theTr.id.indexOf("_") + 1, theTr.id.length);
	}
	//document.getElementById("OptionsList_2").style.backgroundColor = "fdf5cf";
	document.getElementById("OptionsList_" + currentValueSelected).style.backgroundColor = "#e6e6e6";
	document.getElementById("OptionsList_" + currentValueSelected).style.cursor = "hand";
}

function SetText(xVal) {
	//clearTextPrompt();
	mouseFlag=1;
	for (var i = 0; i < ids.length; i++) {
		if (ids[i] != null && ids[i] != "" && document.getElementById(ids[i])!=null) {
			var element = document.getElementById(ids[i]);
			var tableObject = arrOptions[xVal][i];
			var tableDispaly = tableObject.substring(25, arrOptions[xVal][i].length - 5);
			if (tableObject.length > 25 && tableObject.substring(0, 25) == "<td style='display:none'>") {
				if(tableDispaly == null||tableDispaly=="null"){
					tableDispaly = "";
				}
				if (element.tagName == "SPAN") {
					element.innerText = tableDispaly;
				}else if(element.tagName == "SELECT") {
					for(var j=0;j<element.options.length;j++){
						if(element.options[j].value==tableDispaly){
							element.options[j].selected = true;
						}
					}
				}else {
					element.value = tableDispaly;
				}
			} else {
				var value = "";
				if (tableObject != null && tableObject != "null") {
					value = tableObject;
				}
				if (element.tagName == "SPAN") {
					element.innerText = value;
				}else if(element.tagName == "SELECT") {
					for(var j=0;j<element.options.length;j++){
						if(element.options[j].value==value){
							element.options[j].selected = true;
						}
					}
				}else {
					element.value = value;
				}
			}
		}
	}
	document.getElementById("spanOutput").style.display = "none";
	currentValueSelected = -1; //remove the selected index
	values[theTextBox.id] = theTextBox.value;
	if(on_focus_id!=null&&on_focus_id!=""&&document.getElementById(on_focus_id)!=null){
		document.getElementById(on_focus_id).focus();
	}else{
		theTextBox.focus();
		if (window.attachEvent) {
			theTextBox.attachEvent("onkeydown", huiche);
		} else {
			theTextBox.addEventListener("onkeydown", huiche);
		}
	}
}



function GrabHighlighted() {
	if (currentValueSelected >= 0) {
		clearTextPrompt();
		xVal = document.getElementById("OptionsList_" + currentValueSelected).getAttribute("theArrayNumber");
		SetText(xVal);
		HideTheBox();
		if(on_focus_id==""||on_focus_id==null){
			event_content.keyCode=9;
		}
	}
}


function clearTextPrompt() {
	for (var i = 0; i < ids.length; i++) {
		if (ids[i] != null && ids[i] != "" && ids[i] != theTextBox.id) {
			if(document.getElementById(ids[i])!=null)
				document.getElementById(ids[i]).value = "";
		}
	}
}
function checkMouse() {
	if (mouseFlag == 1) {
		return false;
	} else {
		if(when_null_is_clear=="1"){
			theTextBox.value = "";
			clearTextPrompt();
		}
		HideTheBox();
	}
}
function HideTheBox() {
	document.getElementById("spanOutput").style.display = "none";
	initDataPrompt();
	currentValueSelected = -1;
}

function initDataPrompt(){
	arrOptions = new Array();
	ids = new Array();
	selectColumnNames = new Array();
}


function MoveHighlight(xDir) {
	if (currentValueSelected >= 0) {
		newValue = parseInt(currentValueSelected) + parseInt(xDir);
		if (newValue > -1 && newValue < countForId) {
			currentValueSelected = newValue;
			SetHighColor(null);
		} else {
			if (newValue == -1) {
				currentValueSelected = countForId - 1;
				SetHighColor(null);
			} else {
				if (newValue == countForId) {
					currentValueSelected = 0;
					SetHighColor(null);
				}
			}
		}
	}
}

function trimAll(str,is_global) { 
	var result; 
	result = str.replace(/(^\s*)|(\s*$)/g, ""); 
	if(is_global.toLowerCase()=="g") 
		result = result.replace(/\s/g,""); 
	return result; 
} 
