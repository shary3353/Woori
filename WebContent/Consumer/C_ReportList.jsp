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

.headDESC {
	font-size: 25px;
	font-weight: 600;
	text-align: left;
	color: mediumslateblue;
	margin-left: 20px;
	margin-top: 30px;
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
	width: 50px;
}

.pagingBtnDisable {
	text-decoration: none;
	color: gray;
	font-weight: 600;
	background-color: lightgray;
	margin: 1px 2px;
	margin-top: 0px;
	padding: 3px;
	border: 1px white;
	width: 50px;
}

.pagingBtnDisable:hover {
	cursor: default;
	text-decoration: none;
}

.pagingBtn:hover {
	background-color: steelblue;
}
</style>
</head>

<body>
	<div style="min-width: 1920px">
		<jsp:include page="../Include/navi.html"></jsp:include>
		<jsp:include page="../Include/SideBar.html"></jsp:include>
		<div id="reportBox">
			<form action="" method="GET">
				<p class="headDESC">신고 내역</p>
				<table>
					<tr>
						<th>No</th>
						<th>신고 분류</th>
						<th>신고 제목</th>
						<th>신고대상자</th>
						<th>신고 날짜</th>
						<th>신고 처리 상황</th>
					</tr>
					<c:forEach items="${list}" var="list">
						<tr>
							<td style="width: auto;">${list.r_idx}</td>
							<td>${list.category}</td>
							<td class="reportSubject"><a href="cReportDetail?r_idx=${list.r_idx}">${list.subject}</a></td>
							<td>${list.target_id}</td>
							<td>${list.r_date}</td>
							<c:if test="${list.status==0}">
								<td style="color: tomato; font-weight: 600;">처리중</td>
							</c:if>
							<c:if test="${list.status==1}">
								<td style="color: #01DF74; font-weight: 600;">처리완료</td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</form>
			<div>
				<c:if test="${currPage == 1}">
					<a href="#" class="pagingBtnDisable">처음</a>
				</c:if>
				<c:if test="${currPage > 1}">
					<a href="cReportList?page=1" class="pagingBtn">처음</a>
				</c:if>
				<c:if test="${currPage == 1}">
					<a href="#" class="pagingBtnDisable">이전</a>
				</c:if>
				<c:if test="${currPage > 1}">
					<a href="cReportList?page=${currPage-1}" class="pagingBtn">이전</a>
				</c:if>
				<a href="#" class="pagingBtnDisable"
					style="background-color: skyblue; color: white;">&nbsp;${currPage}&nbsp;</a>
				<c:if test="${currPage == maxPage}">
					<a href="#" class="pagingBtnDisable">다음</a>
				</c:if>
				<c:if test="${currPage < maxPage}">
					<a href="cReportList?page=${currPage+1}" class="pagingBtn">다음</a>
				</c:if>
				<c:if test="${currPage == maxPage}">
					<a href="#" class="pagingBtnDisable">마지막</a>
				</c:if>
				<c:if test="${currPage < maxPage}">
					<a href="cReportList?page=${maxPage}" class="pagingBtn">마지막</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
<script></script>

</html>