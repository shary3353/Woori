<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>문의내역</title>

<style>

table, td, th {
	border: 0px 1px;
	border-collapse: collapse;
	padding: 10px 10px;
	margin: 10px;
	line-height: 100%;
	text-align: left;
}

th {
	border-bottom: 2px solid black;
}

.p_PhoNDesc {
	border: none;
	margin: 0px;
	padding: 0px;
	text-align: left;
}

#reservationBox {
	position: relative;
	display: inline-block;
	text-align: center;
	margin: 15px 150px;
}

.delBtn {
	border: 1px solid lightpink;
	width: 30px;
	height: 30px;
	color: lightpink;
	background-color: crimson;
}

.delBtn:hover {
	cursor: pointer;
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

.reserBtn {
	background-color: white;
	color: dodgerblue;
	text-decoration: none;
	font-weight: 600;
}

.reserBtn:hover {
	background-color: lightblue;
}
</style>
</head>

<body>
	<div style="min-width: 1920px">
		<jsp:include page="../Include/navi.html"></jsp:include>
		<jsp:include page="../Include/SideBar.html"></jsp:include>
			<div id="reservationBox">
				<form action="" method="GET">
					<span><p style="font-size: 25px; font-weight: 600; text-align: left; color: mediumslateblue; margin-left: 20px; margin-top: 30px;">문의
							내역</p></span>
					<table>
						<tr>
							<th>No│</th>
							<th style="width: 800px; text-align: left;">문의 제목</th>
							<th style="width: 150px;">│진행 상태</th>
							<th style="width: 200px;">│등록 날짜</th>
						</tr>
						<tr>
							<td>4</td>
							<td><a href="#" style="text-decoration: none; color: black;">제목
									가져오기</a></td>
							<td style="color: steelblue;">&nbsp;&nbsp;처리완료</td>
							<td>&nbsp;&nbsp;2021-03-22</td>
						</tr>
						<tr>
							<td>3</td>
							<td><a href="#" style="text-decoration: none; color: black;">제목
									가져오기</a></td>
							<td style="color: steelblue;">&nbsp;&nbsp;처리완료</td>
							<td>&nbsp;&nbsp;2021-03-22</td>
						</tr>
						<tr>
							<td>2</td>
							<td><a href="#" style="text-decoration: none; color: black;">제목
									가져오기</a></td>
							<td style="color: steelblue;">&nbsp;&nbsp;처리완료</td>
							<td>&nbsp;&nbsp;2021-03-22</td>
						</tr>
						<tr>
							<td>1</td>
							<td><a href="#" style="text-decoration: none; color: black;">제목
									가져오기</a></td>
							<td style="color: crimson;">&nbsp;&nbsp;처리중</td>
							<td>&nbsp;&nbsp;2021-03-22</td>
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