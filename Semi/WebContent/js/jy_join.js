/**
 * 
 */

var regxId = /^[a-zA-Z0-9]{8,12}$/;
var regxPwd = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$/;
var regxPhone = /^[0-9]{10,11}$/; //핸드폰번호 정규식
var regxName = /^[가-힣]{2,4}$/; // 이름 정규식 (한글,2~4글자)
var regxEmail = /^[a-zA-Z0-9]{2,10}$/; //이메일 아이디부분

 
// --- 유효성 검사 메소드 --

function idLiveCheck() { //아이디 유효성 실시간 체크
	var img = document.getElementById('img_id');
	var ide = document.getElementById('ide').value; //입력된 아이디 값
	var bool = regxId.test(ide);
	if(ide == ""){
		img.style.display = "none";
		$('#idcheck_btn').attr('disabled',true);
		return;
	}
	if(bool === true){
		img.src = "images/success.png";
		img.style.display = "block";
		$('.form-group-custom').css('height','70px');
		$('#idcheck_btn').attr('disabled',false).attr('class','btn btn-success');
		return true;
	}else if(bool === false){
		img.src = "images/cancel.png";
		img.style.display = "block";
		$('.form-group-custom').css('height','70px');
		$('#idcheck_btn').attr('disabled',true).attr('class','btn btn-secondary');
		
		return false;
	}
}

function idCheck(chkId) { //중복화면창 입력된 아이디 값
	var bool = regxId.test(chkId);
	if(bool === true){
		return true;
	}else if(bool === false){
		return false;
	}
}

function pwd1LiveCheck() { //비밀번호 유효성 검사
	var img = document.getElementById('img_pwd1');
	var pwd = document.getElementById('password1').value; 
	var bool = regxPwd.test(pwd);
	
	if(pwd == ""){
		img.style.display = "none";
		return;
	}
	if(bool === true){
		img.src = "images/success.png";
		img.style.display = "block";
		$('.form-group-custom').css('height','70px');
		return true;
	}else if(bool === false){
		img.src = "images/cancel.png";
		img.style.display = "block";
		$('.form-group-custom').css('height','70px');
		return false;
	}
}

function pwd2LiveCheck() { //비밀번호 재확인
	var pwd1 = document.getElementById('password1').value; 
	var pwd2 = document.getElementById('password2').value; 
	
	if(pwd2 == ""){
		$('#pwd_msg').css('color','green').text('비밀번호를 다시 입력해주세요!');
		return;
	}
	if(pwd1 === pwd2){
		$('#pwd_msg').css('color','blue').text('비밀번호가 일치합니다!');
		return true;
	}else{
		$('#pwd_msg').css('color','red').text('비밀번호가 일치하지않습니다!');
		return false;
	}
}


function phoneChk() {
	var phone = document.getElementById('phone').value; //입력된 아이디 값
	var img = document.getElementById('img_phone');
	var bool = regxPhone.test(phone);
	var first = phone.substring(0, 3);
	if(phone == ""){
		img.style.display = "none";
		return false;
	}
	if(bool === true){
		if(first != '010'){return;}
		img.src = "images/success.png";
		img.style.display = "block";
		return true;
	}else if(bool === false){
		img.src = "images/cancel.png";
		img.style.display = "block";
		return false;
	}
}

function nameChk() {
	var name = $('#name').val();
	var bool = regxName.test(name);
	if(bool){
		return true;
	}else{
		return false;
	}
}

function emailChk() {
	var email = document.getElementById('email1').value;
	var bool = regxEmail.test(email);
	if(bool){
		if($('#email_select option:selected').val() != ""){
			return true;
		}
		else{
			return false;
		}
	}else{
		return false;
	}
}

function qnaChk() {
	var i = $('#q_sel option:selected').val();
	var a = $('input[name=answer]').val();
	if($('#q_sel option:selected').val() != "" && $('input[name=answer]').val() != ""){
		return true;
	}else{
		return false;
	}
}

function addrChk() {
	if($('#postcode').val() != "" && $('#address1').val() != "" && $('#address2').val() != ""){
		return true;
	}else{
		return false;
	}
}
/*function openIdChk() { //중복확인 클릭시 뜨는 화면
	var id = document.getElementById('id').value;
	window.name = "parentForm";
	openWin = window.open("openIdChk.jsp?id="+id+"",'_blank',"resizable=no,top=200,left=100,width=600,height=200");
}

function checkId() {
	var chkId = document.getElementById('chkId').value;
	var chkDiv = document.getElementById('chkDiv');
	var bool = idCheck(chkId);
	if(bool){
		location.href = "checkId.do?id="+chkId+"";
	}else{
		chkDiv.innerHTML = "(8~12자 영대소문자와 숫자)";
		$(document).ready(function() {
			$('#chkDiv').css('color','red');
		});
	}
}*/



function finalCheck() {
			if(!(idLiveCheck())){
				alert('아이디를 올바르게 입력해주세요.');
				return false;
			};
			if(!(pwd1LiveCheck())){
				alert('비밀번호를 올바르게 입력해주세요.');
				return false;
			};
			if(!(pwd2LiveCheck())){
				alert('비밀번호가 일치하지 않습니다.');
				return false;
			};
			if(!(nameChk())){
				alert('이름을 올바르게 입력해주세요.');
				return false;
			};
			if(!(emailChk())){
				alert('이메일을 올바르게 입력해주세요.');
				return false;
			};
			if(!(phoneChk())){
				alert('번호를 올바르게 입력해주세요.');
				return false;
			};
			if(!(qnaChk())){
				alert('질문/답변을 올바르게 입력해주세요.');
				return false;
			};
			if(!(addrChk())){
				alert('주소를 올바르게 입력해주세요.');
				return false;
			};
			return true;
}


function dupliChk() {
	var id = $('#ide').val();
	$.ajax({
		url : "members.do?number=2&id="+id,
		dataType : "json",
		Type : 'get',
		success : function(data) {
			$('#dupliChkSpan').text(data.chk);
			if(data.num == 1){
				$('#dupliChkSpan').css('color','blue');
			}else{
				$('#dupliChkSpan').css('color','red');
			}
		}
	})
}

// --- 우편번호 관련 메소드 ---

function execDaumPostcode() { 
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = ''; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                fullAddr = data.roadAddress;

            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if(data.userSelectedType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('address1').value = fullAddr;

            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('address2').focus();
        }
    }).open();
}

function checkemailaddy(){
        if (form.email_select.value == '1') {
        	$('#email2').attr('readonly',false);
     	
            form.email2.focus();
            form.email2.value = '';
        }
        else {
            $('#email2').attr('readonly',true);
            $('#email2').attr('disabled',false);
            form.email2.value = form.email_select.value;
        }
    }



/////////////마이페이지 수정할 때 비밀번호 유효성 검사/////////////////

function remove(){
	var pwd1=document.getElementById("pwd1");
	pwd1.value="";
	pwd1.style.color="black";
	pwd1.focus();
}

function remove2(){
	var pwdck=document.getElementById("pwdck");
	pwdck.value="";
	pwdck.style.color="black";
	pwdck.focus();
}

function getPwdOk1(){
	var pwd1=document.getElementById("pwd1");
	var sp1=document.getElementById("sp1");
	var bool=regxPwd.test(pwd1.value);
	var img1=document.getElementById("img1");
	if(bool){
		img1.src="images/success.png";
		return;
	}
	img1.src="images/cancel.png";	
	pwd1.focus();
}

function getPwdOk2(){
	var pwd1=document.getElementById("pwd1");
	var pwdck=document.getElementById("pwdck");
	var sp2=document.getElementById("sp2");
	var img2=document.getElementById("img2");
	if(!(pwd1.value==pwdck.value)){
		img2.src="images/cancel.png";	
		return;
	}
	img2.src="images/success.png";
}

function remove3(){
	var phone=document.getElementById("phone");
	phone.value="";
	phone.focus();
}

function remove4(){
	var email1=document.getElementById("email1");
	email1.value="";
	email1.focus();
}


function getPhone(){
	var phone=document.getElementById("phone");
	var bool=regxPhone.test(phone.value);
	var img3=document.getElementById("img3");
	if(bool){
		img3.src="images/success.png";
		return;
	}
	img3.src="images/cancel.png";
	phone.focus();
}









