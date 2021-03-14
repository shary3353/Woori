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
	height: 500px;
	float: left;
	margin: 5px 150px;
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

.readonlyInputs{
	border: none;
	text-align: center;
	font-size: 15px;
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
	<jsp:include page="../Include/navi.html"></jsp:include>
	<jsp:include page="../Include/SideBar.html"></jsp:include>
	<div id="myInfo">
		<form action="cUpdateInfo" method="POST" name="updateForm">
			<div id="infoBox">
				<p class="headDESC">나의 정보 수정</p>
				<table>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="cid" class="readonlyInputs" value="${list.cid}"readonly></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pw" class="inputs"  id="pw" /></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" name="pwCfm" id="pwCfm" class="inputs" /><br>
							<span id="pwCfmMsg">비밀번호가 일치하지 않습니다.</span></td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td><input type="text" name="birthday" class="readonlyInputs" value="${list.birthday}" readonly></td>
					</tr>
					<tr>
						<th>성별
						<td><input type="text" name="gender" class="readonlyInputs" value="${list.gender}" readonly></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="email" name="email" id="email" class="inputs"
							value="${list.email}" /></td>
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
pwCheckBox.addEventListener('keyup', () => {
  if (pwBox.value == pwCheckBox.value) {
    pwCheckText.style.color = '#93c47d';
    pwCheckText.innerHTML = '비밀번호가 일치합니다.';
  } else {
    pwCheckText.style.color = 'red';
  }
});

$('#updateBtn').click(()=>{
	console.log(pwBox.value);
	if(pwBox.value == ""){
		alert("비밀번호를 입력해주세요.");
	} else if (pwCheckBox.value == ""){
		alert("비밀번호확인을 입력해주세요.");
	} else if (pwBox.value != pwCheckBox.value){
		alert("비밀번호를 다시 확인해주세요.");
	} else if (emailBox.value == ""){
		alert("이메일을 입력해주세요.");
	} else if (phoneBox.value == ""){
		alert("전화번호을 입력해주세요.");
	} else {
		document.updateForm.submit();
	}
});

</script>

</html>