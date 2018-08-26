

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
