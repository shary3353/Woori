<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>회원정보수정</title>

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
	margin: 50px 150px;
	text-align: center;
}

#infoBox {
	width: 550px;
	margin: 50px 50px;
	text-align: center;
}

div.C_MyPageNavi {
	width: 150px;
	height: 100px;
	line-height: 100px;
	font-family: Arial, Helvetica, sans-serif;
	text-align: right;
	font-weight: 600;
	font-size: 18px;
	background-color: rgb(202, 202, 202);
}

#mainMyPage {
	width: 150px;
	height: 125px;
	line-height: 125px;
	font-size: 25px;
	font-weight: 600;
	text-align: right;
	background-color: rgb(202, 202, 202);
}

div.C_MyPageNavi:hover {
	background-color: rgb(138, 138, 138);
}

div.C_MyPageNavi>a {
	text-decoration: none;
	color: black;
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
}

#pwCfmMsg {
	color: red;
	font-size: 12px;
}

#modifyBtn {
	text-align: center;
}
</style>
</head>

<body>
	<jsp:include page="../Include/navi.html"></jsp:include>
	<div id="wrapper">
		<div id="naviBox">
			<div id="mainMyPage " class="C_MyPageNavi ">
				<a href="# " style="text-decoration: none; color: black">마이페이지&nbsp;</a>
			</div>
			<div id="memInfo" class="C_MyPageNavi ">
				<a href="# ">회원정보&nbsp;</a>
			</div>
			<div class="C_MyPageNavi ">
				<a href="# ">위시리스트&nbsp;</a>
			</div>
			<div class="C_MyPageNavi ">
				<a href="# ">문의내역&nbsp;</a>
			</div>
			<div class="C_MyPageNavi ">
				<a href="# ">예약내역&nbsp;</a>
			</div>
			<div class="C_MyPageNavi ">
				<a href="# ">신고내역&nbsp;</a>
			</div>
		</div>

		<div id="myInfo">
			<form action="#" method="POST">
				<div id="infoBox">
					<p
						style="font-size: 25px; font-weight: 600; text-align: left; color: mediumslateblue">나의
						정보 수정</p>
					<table>
						<tr>
							<th>아이디</th>
							<td>아이디 가져오기</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" name="pw" class="inputs" /></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td><input type="password" name="pwCfm" class="inputs" /><br>
								<span id="pwCfmMsg">비밀번호가 일치하지 않습니다.</span></td>
						</tr>
						<tr>
							<th>생년월일</th>
							<td>생년월일 가져오기</td>
						</tr>
						<tr>
							<th>성별
							</td>
							<td>성별 가져오기</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><input type="email" name="email" class="inputs" /></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><input type="text" name="phone" class="inputs" /></td>
						</tr>
					</table>
					<span><input type="submit" value="수정하기" /></span>
				</div>
			</form>
		</div>
	</div>
</body>
<script>
	
</script>

</html>