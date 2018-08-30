//사이드바

var xhr=null;
function getView(){
	var id=document.getElementById("id");
	if(id.value=="null"){
		alert("로그인 후 사용가능한 서비스입니다.");
		return;
	}
	xhr=new XMLHttpRequest();
	xhr.onreadystatechange=getViewInfo;
	xhr.open('get','cart.do?cmd=view&id='+id.value,true);
	xhr.send();
}

function getViewInfo(){
	if(xhr.readyState==4 && xhr.status==200){
		removeView();
		var arr=xhr.responseText;
		var json=JSON.parse(arr);
		var view=document.getElementById("view");
		if(json!=null){
			for(var i=0;i<json.length;i++){
				var p_name=json[i].p_name;
				var p_image=json[i].p_image;
				var a=document.createElement("a");
				a.className="w3-bar-item w3-button";
				a.href="opainting.do?cmd=detail&p_num="+json[i].p_num;	
				var img=document.createElement("img");
				img.src="painting/o/"+p_image;
				img.width="370";
				img.height="370";
				a.appendChild(img);
				view.appendChild(a);
			}
		}
		w3_open();
	}
}

function removeView(){
	var view=document.getElementById("view");
	var nodes=view.childNodes;
	for(var i=nodes.length-1;i>=0;i--){
		var child=nodes.item(i);
		view.removeChild(child);
	}
}



function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("btn1").style.display = "none";
}
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("btn1").style.display = "block";
}




//------------------------------------------------------------------------

function getCheck(obj){
	var allcheck=document.getElementsByName("allcheck")[0];
	var obj=document.getElementsByName(obj);
	var size=obj.length;
	if(allcheck.checked){
		for(var i=0;i<size;i++){
			obj[i].checked=true;
		}
	}else{
		for(var i=0;i<size;i++){
			obj[i].checked=false;
		}
	}
}

function openCity(cityName) {
    var i;
    var x = document.getElementsByClassName("city");
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";  
    }
    document.getElementById(cityName).style.display = "block";  
}


function getBlank1() {
	setTimeout(function() {
		var title = document.getElementById("b_title");
		if(title.value=="20자 이내로 작성해주세요"){
			title.value = "";
			title.focus();
		}
			
	}, 10);
}
function getBlank() {
	setTimeout(function() {
		var content = document.getElementById("b_content");
		if(content.innerHTML=="200자 이내로 작성해주세요"){
			content.innerHTML = "";
			content.focus();
		}
	}, 10);
}
function getSubmit(){
	var title = document.getElementById("b_title");
	if(title.value=="20자 이내로 작성해주세요"){
		alert("제목을 입력해주세요");
		return false;
	}
	var content = document.getElementById("b_content");
	if(content.innerHTML=="200자 이내로 작성해주세요"){
		alert("내용을 입력해주세요");
		return false;
	}
}

//주문내역 조회 날짜 입력하기
function getDate(e){
	var btn=document.getElementById(e);
	var date1=document.getElementById("d1");
	var date2=document.getElementById("d2");
	var d=new Date();
	var z="";
	if(e=="btn1"){ //오늘
		if((d.getMonth()+1) < 10){
			z="0";
		}
		date1.value=d.getFullYear()+"-"+z+(d.getMonth()+1)+"-"+d.getDate();
		date2.value=d.getFullYear()+"-"+z+(d.getMonth()+1)+"-"+d.getDate();
	}else if(e=="btn2"){ //1주일
		if((d.getMonth()+1) <10) z="0";
		var d1=new Date(d.getFullYear(),d.getMonth()+1,d.getDate()-7);
		date1.value=d1.getFullYear()+"-"+z+(d1.getMonth())+"-"+(d1.getDate());
		date2.value=d.getFullYear()+"-"+z+(d.getMonth()+1)+"-"+d.getDate();
	}else if(e=="btn3"){ //1개월
		if((d.getMonth()+1) <10) z="0";
		var d1=new Date(d.getFullYear(),d.getMonth(),d.getDate());
		date1.value=d1.getFullYear()+"-"+z+d1.getMonth()+"-"+(d1.getDate());
		date2.value=d.getFullYear()+"-"+z+(d.getMonth()+1)+"-"+d.getDate();
	}else if(e=="btn4"){ //3개월
		if((d.getMonth()+1) <10) z="0";
		var d1=new Date(d.getFullYear(),d.getMonth()-2,d.getDate());
		date1.value=d1.getFullYear()+"-"+z+d1.getMonth()+"-"+(d1.getDate());
		date2.value=d.getFullYear()+"-"+z+(d.getMonth()+1)+"-"+d.getDate();
	}else if(e=="btn5"){ //6개월
		if((d.getMonth()+1) <10) z="0";
		var gd=d.getDate();
		var g=1;
		//윤년계산
		if((d.getFullYear()%4==0 && d.getFullYear()%100!=0) || d.getFullYear()%400==0){
			if(d.getMonth()-5==2){ //2월
				if(gd==30){ 
					gd=d.getDate()-1; //29일
				}else if(gd==31){
					gd=d.getDate()-2; //29일
				}
			} 
		}
		//윤년 아닐 때 
		if(d.getMonth()-5==2){ //2월
			if(gd==29){
				gd=d.getDate()-1; //28일
			}else if(gd==30){
				gd=d.getDate()-2; //28일
			}else if(gd==31){
				gd=d.getDate()-3; //28일
			}
		} 
			
		var d1=new Date(d.getFullYear(),d.getMonth()-5,gd);
		date1.value=d1.getFullYear()+"-"+z+(d1.getMonth())+"-"+(d1.getDate());
		date2.value=d.getFullYear()+"-"+z+(d.getMonth()+1)+"-"+d.getDate();
	}
}

function getDateReturn(){
	var date1=document.getElementById("d1");
	var date2=document.getElementById("d2");
	if(date1.value==""){
		alert("조회할 날짜를 선택해주세요");
		return false;
	}
	if(date2.value==""){
		alert("조회할 날짜를 선택해주세요");
		return false;
	}
	return true;
	
}


