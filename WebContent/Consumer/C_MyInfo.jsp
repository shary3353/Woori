<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>회원정보 상세보기</title>

<style>
#wrapper {
	overflow: hidden;
}

#naviBox {
	float: left;
}

#myInfo {
	text-align: center;
}

#infoBox {
	margin-top: 75px;
	margin-left: 35%;
	position: absolute;
	width: 550px;
}

#memInfo {
	background-color: rgb(138, 138, 138);
}

table, td, th {
	border: 1px solid lightgray;
	border-collapse: collapse;
	padding: 10px 10px;
	margin: 10px;
}

td {
	text-align: center;
	width: 450px;
}

th {
	width: 125px;
	text-align: right;
}

#pwInputBox {
	text-align: right;
	margin-right: 10px;
}
</style>
</head>

<body>
	<%@include file="../Include/navi.jsp" %>
	<%@include file="../Include/SideBar.jsp" %>
	<div id="myInfo">
		<form action="cUpadateForm" method="POST">
			<div id="infoBox">
				<p
					style="font-size: 25px; font-weight: 600; text-align: left; color: mediumslateblue">나의
					정보</p>
				<input type="text" value="${list.cid}" name="cid"
					readonly="readonly" hidden="hidden" />
				<table>
					<tr>
						<th>아이디</th>
						<td>${list.cid}</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${list.name}</td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td>${list.birthday}</td>
					</tr>
					<tr>
						<th>성별
						<td>${list.gender}</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${list.email}</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${list.phone}</td>
					</tr>
				</table>
				<div id="pwInputBox">
					<span id="pwBox" style="visibility: hidden">비밀번호 : <input
						type="password" placeholder="비밀번호를 입력하세요." name="pw"
						style="width: 150px;"><input type="submit" value="확인" id="cfmBtn"></span>
					<span style="text-align: right"><input type="button"
						value="수정하기" onclick="modifyBtn()" /></span>
				</div>
			</div>
		</form>
	</div>
</body>
<script>
function modifyBtn() {
    document.getElementById('pwBox').style.visibility ='visible';
}
var msg = "${msg}";
if(msg != ""){
	alert(msg);
	msg="";
}
</script>

</html>