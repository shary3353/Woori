<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>회원정보수정</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
#wrapper {
	overflow: hidden;
}

#naviBox {
	float: left;
}

#myInfo {
	width: auto;
	margin-top: 25px;
	margin-left: 32%;
	position: absolute;
	text-align: center;
}

#infoBox {
	width: 550px;
	margin: 50px 50px;
	text-align: center;
}

table, td, th {
	border: 1px solid lightgray;
	border-collapse: collapse;
	padding: 10px 10px;
	margin: 10px;
	line-height: 100%;
}

td {
	text-align: center;
	width: 450px;
}

th {
	width: 160px;
	text-align: right;
}

.inputs {
	width: 350px;
	height: 35px;
	margin: 9px 0 0 9px;
	border: 2px solid lightslategrey;
	line-height: 21px;
	font-weight: bold;
	font-size: 16px;
	outline: none;
	padding-left: 15px;
}

#pwCfmMsg {
	color: red;
	font-size: 12px;
}

#modifyBtn {
	text-align: center;
}

.readonlyInputs {
	border: none;
	text-align: center;
	font-size: 15px;
	width: 350px;
	height: 35px;
	margin: 9px 0 0 9px;
	border: 2px solid white;
	line-height: 21px;
	font-weight: bold;
	font-size: 16px;
	outline: none;
	padding-left: 15px;
}

.headDESC {
	font-size: 25px;
	font-weight: 600;
	text-align: left;
	color: mediumslateblue;
	margin-left: 20px;
	margin-top: 30px;
}
</style>
</head>

<body>
	<%@include file="../Include/navi.jsp"%>
	<%@include file="../Include/SideBar.jsp"%>
	<div id="myInfo">
		<form action="cUpdateInfo" method="POST" name="updateForm">
			<div id="infoBox">
				<p class="headDESC">나의 정보 수정</p>
				<table>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="cid" class="readonlyInputs"
							value="${list.cid}" readonly></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="birthday" class="readonlyInputs"
							value="${list.name}" readonly></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pw" class="inputs" id="pw" onkeyup="fnChkByte(this, 200)"/></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" name="pwCfm" id="pwCfm"
							class="inputs" /><br> <span id="pwCfmMsg">비밀번호가 일치하지
								않습니다.</span></td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td><input type="text" name="birthday" class="readonlyInputs"
							value="${list.birthday}" readonly></td>
					</tr>
					<tr>
						<th>성별
						<td><input type="text" name="gender" class="readonlyInputs"
							value="${list.gender}" readonly></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="email" name="email" id="email"
							class="inputs" value="${list.email}" onkeyup="fnChkByte(this, 100)"/></td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td><input type="text" name="phone" id="phone" class="inputs"
							value="${list.phone}" /></td>
					</tr>
				</table>
				<span><input type="button" value="수정하기" id="updateBtn" /></span>
			</div>
		</form>
	</div>
</body>
<script>
// 비밀번호 실시간 확인
const pwBox = document.getElementById('pw');
const pwCheckBox = document.getElementById('pwCfm');
const pwCheckText = document.getElementById('pwCfmMsg');
const emailBox = document.getElementById('email');
const phoneBox = document.getElementById('phone');

var regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
var regPhone = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
var regPw = /^[A-Za-z\d$@$!%*_#?&]{3,}$/;

pwCheckBox.addEventListener('keyup', ()=>{
  if (pwBox.value == pwCheckBox.value) {
    pwCheckText.style.color = '#93c47d';
    pwCheckText.innerHTML = '비밀번호가 일치합니다.';
  } else {
    pwCheckText.style.color = 'red';
  }
});

pwBox.addEventListener('keyup', ()=>{
	if (pwBox.value == pwCheckBox.value) {
	    pwCheckText.style.color = '#93c47d';
	    pwCheckText.innerHTML = '비밀번호가 일치합니다.';
	  } else {
	    pwCheckText.style.color = 'red';
	  }
})

function fnChkByte(obj, maxByte){//글자 bytes 제한
    var str = obj.value;
    var str_len = str.length;

    var rbyte = 0;
    var rlen = 0;
    var one_char = "";
    var str2 = "";

    for(var i=0; i<str_len; i++){
        one_char = str.charAt(i);
        if(escape(one_char).length > 4){
			rbyte += 3;//한글3Byte
		} else {
		    rbyte++;//영문 등 나머지 1Byte
        }
		if(rbyte <= maxByte){
            rlen = i+1;//return할 문자열 갯수
        }
     }
     if(rbyte > maxByte){
  		// alert("한글 "+(maxByte/2)+"자 / 영문 "+maxByte+"자를 초과 입력할 수 없습니다.");
  		alert("최대 " + maxByte + "byte를 초과할 수 없습니다.")
  		str2 = str.substr(0,rlen);//문자열 자르기
  		obj.value = str2;
  		fnChkByte(obj, maxByte);
     }else{
        //document.getElementById('byteInfo').innerText = rbyte;
     }
}

$('#updateBtn').click(()=>{
	console.log(pwBox.value);
	if(pwBox.value == ""){
		alert("비밀번호를 입력해주세요.");
	} else if (pwCheckBox.value == ""){
		alert("비밀번호확인을 입력해주세요.");
	} else if (pwBox.value != pwCheckBox.value){
		alert("비밀번호를 다시 확인해주세요.");
	} else if(!regPw.test(pwBox.value)){
		alert('비밀번호는 영문,숫자,특수문자@$!%*_#?&만 허용됩니다.');
		$pw.focus();
	} else if (emailBox.value == ""){
		alert("이메일을 입력해주세요.");
	} else if(!regEmail.test(emailBox.value)){
		alert('이메일 주소가 유효하지 않습니다.');
		$email.focus();
	} else if (phoneBox.value == ""){
		alert("전화번호을 입력해주세요.");
	} else if(!regPhone.test(phoneBox.value)){
		alert('전화번호가 유효하지 않습니다.');
		$phone.focus();
	} else {
		document.updateForm.submit();
	}
});

</script>

</html>