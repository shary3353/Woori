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
	<div style="min-width: 1920px">
		<jsp:include page="../Include/navi.html"></jsp:include>
		<jsp:include page="../Include/SideBar.html"></jsp:include>
		<div id="reservationBox">
			<form action="" method="GET">
				<p class="headDESC">문의 내역</p>
				<table>
					<tr>
						<th>No│</th>
						<th style="width: 800px; text-align: left;">문의 제목</th>
						<th style="width: 150px;">│진행 상태</th>
						<th style="width: 200px;">│등록 날짜</th>
					</tr>
					<c:forEach items="${list}" var="list">
						<tr>
							<td>${list.q_idx}</td>
							<td><a href="cQuestionDetail?q_idx=${list.q_idx}" style="text-decoration: none; color: black;">${list.subject}</a></td>
							<c:if test="${list.s_answer != null}">
								<td style="color: steelblue;">&nbsp;&nbsp;답변 완료</td>
							</c:if>
							<c:if test="${list.s_answer == null}">
								<td style="color: tomato;">&nbsp;&nbsp;답변 대기중</td>
							</c:if>
							<td>&nbsp;&nbsp;${list.q_reg_date}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
			<div>
				<c:if test="${currPage == 1}">
					<a href="#" class="pagingBtnDisable">처음</a>
				</c:if>
				<c:if test="${currPage > 1}">
					<a href="cQuestionList?page=1" class="pagingBtn">처음</a>
				</c:if>
				<c:if test="${currPage == 1}">
					<a href="#" class="pagingBtnDisable">이전</a>
				</c:if>
				<c:if test="${currPage > 1}">
					<a href="cQuestionList?page=${currPage-1}" class="pagingBtn">이전</a>
				</c:if>
				<a href="#" class="pagingBtnDisable"
					style="background-color: skyblue; color: white;">&nbsp;${currPage}&nbsp;</a>
				<c:if test="${currPage == maxPage}">
					<a href="#" class="pagingBtnDisable">다음</a>
				</c:if>
				<c:if test="${currPage < maxPage}">
					<a href="cQuestionList?page=${currPage+1}" class="pagingBtn">다음</a>
				</c:if>
				<c:if test="${currPage == maxPage}">
					<a href="#" class="pagingBtnDisable">마지막</a>
				</c:if>
				<c:if test="${currPage < maxPage}">
					<a href="cQuestionList?page=${maxPage}" class="pagingBtn">마지막</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
<script></script>

</html>