<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>신고내역</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
#wrapper {
	overflow: hidden;
}

#naviBox {
	float: left;
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

div.C_MyPageNavi:hover {
	background-color: rgb(138, 138, 138);
}

div.C_MyPageNavi>a {
	text-decoration: none;
	color: black;
}

#memReprotList {
	background-color: rgb(138, 138, 138);
}

table, td, th {
	border: 1px solid lightgray;
	border-collapse: collapse;
	padding: 10px 10px;
	margin: 10px;
	line-height: 100%;
	text-align: center;
}

th {
	background-color: bisque;
}

.p_PhoNDesc {
	border: none;
	margin: 0px;
	padding: 0px;
	text-align: left;
}

#reportBox {
	position: relative;
	display: inline-block;
	text-align: center;
	margin: 15px 150px;
}

.pagingBtn {
	text-decoration: none;
	color: black;
	font-weight: 600;
	background-color: lightgray;
	margin: 1px 2px;
	margin-top: 0px;
	padding: 3px;
	border: 1px white;
}

.pagingBtn:hover {
	background-color: steelblue;
}

form {
	margin-bottom: 5px;
}

.cancelBtn {
	background-color: white;
	color: dodgerblue;
	text-decoration: none;
	font-weight: 600;
}

.cancelBtn:hover {
	background-color: lightblue;
}

.reportSubject {
	width: 450px;
	text-align: left;
}
</style>
</head>

<body>
	<div style="min-width: 1920px">
		<jsp:include page="../Include/navi.html"></jsp:include>
		<div id="wrapper">
			<div id="naviBox">
				<div id="mainMyPage " class="C_MyPageNavi ">
					<a href="# " style="text-decoration: none; color: black">마이페이지&nbsp;</a>
				</div>
				<div class="C_MyPageNavi ">
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
				<div id="memReprotList" class="C_MyPageNavi ">
					<a href="# ">신고내역&nbsp;</a>
				</div>
			</div>
			<div id="reportBox">
				<form action="" method="GET">
					<span><p
							style="font-size: 25px; font-weight: 600; text-align: left; color: mediumslateblue; margin-left: 20px; margin-top: 30px;">신고
							내역</p></span>
					<table>
						<tr>
							<th>No</th>
							<th>신고 분류</th>
							<th>신고 제목</th>
							<th>신고대상자</th>
							<th>신고 날짜</th>
							<th>신고 처리 상황</th>
						</tr>
						<tr>
							<td style="width: auto;">3</td>
							<td>상품 관련</td>
							<td class="reportSubject">신고 제목 가져오기</td>
							<td>신고대상자 가져오기</td>
							<td>2021.01.23</td>
							<td style="color: tomato;">처리중</td>
						</tr>
						<tr>
							<td style="width: auto;">2</td>
							<td>예약 관련</td>
							<td class="reportSubject">신고 제목 가져오기</td>
							<td>신고대상자 가져오기</td>
							<td>2021.01.23</td>
							<td style="color: 93c47d;">처리완료</td>
						</tr>
						<tr>
							<td style="width: auto;">1</td>
							<td>상품 관련</td>
							<td class="reportSubject">신고 제목 가져오기</td>
							<td>신고대상자 가져오기</td>
							<td>2021.01.23</td>
							<td style="color: #93c47d;">처리 완료</td>
						</tr>
					</table>
				</form>
				<span> <a href="#" class="pagingBtn">처음</a> <a href="#"
					class="pagingBtn">이전</a> <a href="#" class="pagingBtn"
					style="background-color: skyblue; color: white;">&nbsp;1&nbsp;</a>
					<a href="#" class="pagingBtn">다음</a> <a href="#" class="pagingBtn">마지막</a>
				</span>
			</div>
		</div>
	</div>
</body>
<script></script>

</html>