//반품처리
var xhr1=null;
function returnPduct(e){
	var conf=confirm("반품하시려면 확인을 눌러주세요");
	if(conf==true){
		xhr1=new XMLHttpRequest();
		xhr1.onreadystatechange=returnPductOk;
		xhr1.open('get','mypage.do?cmd=returnPdut&o_num='+e,'true');
		xhr1.send();
	}else{
		return;
	}
}
function returnPductOk(){
	
	if(xhr1.readyState==4 && xhr1.status==200){
		alert("성공");
		var id=document.getElementById("idd").value; //id이름 idd맞음
		var data=xhr1.responseText;
		var json=JSON.parse(data);
		if(json.n>0){
			alert("반품신청성공");
		}else{
			alert("반품신청실패");
		}
	}
}

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
				img.width="300";
				img.height="300";
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
	var x="";
	if(e=="btn1"){ //오늘
		var nowmonth=d.getMonth()+1;
		if(nowmonth <10) z="0";
		var gd=d.getDate();	
		if(gd<10){
			x="0";
		}
		date1.value=d.getFullYear()+"-"+z+(d.getMonth()+1)+"-"+x+d.getDate();
		date2.value=d.getFullYear()+"-"+z+(d.getMonth()+1)+"-"+x+d.getDate();
	}else if(e=="btn2"){ //1주일
		var nowmonth=(d.getMonth()+1);
		if(nowmonth <10) z="0";
		var gd=d.getDate();	
		if(gd<10){
			x="0";
		}
		var d1=new Date(d.getFullYear(),d.getMonth()+1,d.getDate()-7);
		date1.value=d1.getFullYear()+"-"+z+(d1.getMonth())+"-"+(d1.getDate());
		date2.value=d.getFullYear()+"-"+z+(d.getMonth()+1)+"-"+x+d.getDate();
	}else if(e=="btn3"){ //1개월
		var nowmonth=(d.getMonth()+1);
		if(nowmonth <10) z="0";
		var gd=d.getDate();	
		//윤년 상관없는 달
		if(nowmonth == 5 || nowmonth == 7 || nowmonth == 10 ||nowmonth == 12 ){
			if(gd==31)gd=d.getDate()-1;
		}
		//윤년인데 3월
		if(((d.getFullYear()%4==0 && d.getFullYear()%100!=0) || d.getFullYear()%400==0) && nowmonth==3){
			switch(gd){
				case 30: gd=d.getDate()-1;break;
				case 31: gd=d.getDate()-2;break;
			}
		//윤년아닌데 3월
		}else if(!((d.getFullYear()%4==0 && d.getFullYear()%100!=0) || d.getFullYear()%400==0) && nowmonth==3){
			switch(gd){
				case 29: gd=d.getDate()-1;break;
				case 30: gd=d.getDate()-2;break;
				case 31: gd=d.getDate()-3;break;
			}
		}
		if(gd <10) x="0";
		var d1=new Date(d.getFullYear(),d.getMonth(),gd);
		date1.value=d1.getFullYear()+"-"+z+d1.getMonth()+"-"+x+(d1.getDate());
		date2.value=d.getFullYear()+"-"+z+(d.getMonth()+1)+"-"+x+d.getDate();
	}else if(e=="btn4"){ //3개월
		var nowmonth=(d.getMonth()+1);
		if(nowmonth <10) z="0";
		var gd=d.getDate();
		if(nowmonth==7 || nowmonth==12){
			if(gd==31)gd=d.getDate()-1;
		}
		//윤년인데 5월
		if(((d.getFullYear()%4==0 && d.getFullYear()%100!=0) || d.getFullYear()%400==0) && nowmonth==5){
			switch(gd){
				case 30: gd=d.getDate()-1;break;
				case 31: gd=d.getDate()-2;break;
			}
		//윤년아닌데 5월
		}else if(!((d.getFullYear()%4==0 && d.getFullYear()%100!=0) || d.getFullYear()%400==0) && nowmonth==5){
			switch(gd){
				case 29: gd=d.getDate()-1;break;
				case 30: gd=d.getDate()-2;break;
				case 31: gd=d.getDate()-3;break;
			}
		}
		if(gd <10) x="0";
		var d1=new Date("\""+d.getFullYear()+"-"+(d.getMonth()-2)+"-"+d.getDate()+"\"");
		//var d1=new Date(d.getFullYear(),d.getMonth()-2,gd); (X)
		date1.value=d1.getFullYear()+"-"+z+(d1.getMonth()+1)+"-"+x+d1.getDate();
		date2.value=d.getFullYear()+"-"+z+(d.getMonth()+1)+"-"+x+d.getDate();
		
	}else if(e=="btn5"){ //6개월
		
		var nowmonth=(d.getMonth()+1);
		if(nowmonth <10) z="0";
		var gd=d.getDate();
		if(gd <10) x="0";
		//윤년 상관없는 달
		if(nowmonth == 3 || nowmonth == 5 ||nowmonth == 10 ||nowmonth == 12 ){
			switch(nowmonth){
				case 3: if(gd==31)gd=d.getDate()-1;break;
				case 5:	if(gd==31)gd=d.getDate()-1;break;
				case 10: if(gd==31)gd=d.getDate()-1;break;
				case 12: if(gd==31)gd=d.getDate()-1;break;
			}
		}
		//윤년인데 8월
		if(((d.getFullYear()%4==0 && d.getFullYear()%100!=0) || d.getFullYear()%400==0)&&nowmonth==8){
				switch(gd){
					case 30: gd=d.getDate()-1; break;
					case 31: gd=d.getDate()-2;break;
				}
		//윤년아닌데 8월
		}else if(!((d.getFullYear()%4==0 && d.getFullYear()%100!=0) || d.getFullYear()%400==0)&&nowmonth==8 ){ 
				switch(gd){
					case 29: gd=d.getDate()-1;break;
					case 30: gd=d.getDate()-2; break;
					case 31: gd=d.getDate()-3;break;
				}
		}
		
		var d1=new Date(d.getFullYear(),d.getMonth()-5,gd);
		date1.value=d1.getFullYear()+"-"+z+(d1.getMonth())+"-"+x+(d1.getDate());
		date2.value=d.getFullYear()+"-"+z+(d.getMonth()+1)+"-"+x+d.getDate();
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


