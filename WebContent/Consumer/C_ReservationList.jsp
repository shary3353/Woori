<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>예약내역</title>

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

#reservationListBox {
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

form {
	margin-bottom: 5px;
}

.reportBtn {
	background-color: white;
	color: gray;
	text-decoration: none;
	font-weight: 600;
	border: 1px solid lightgray;
	border-radius: 5px;
}

.reportBtn:hover {
	background-color: lightblue;
}

.reserProcess {
	color: crimson;
}

.headDESC {
	font-size: 25px;
	font-weight: 600;
	text-align: left;
	color: mediumslateblue;
	margin-left: 20px;
	margin-top: 30px;
}
img:hover{
	cursor: pointer;
}
a{
	text-decoration: none;
	color: black;
}
</style>
</head>

<body>
	<div style="min-width: 1920px">
		<jsp:include page="../Include/navi.html"></jsp:include>
		<jsp:include page="../Include/SideBar.html"></jsp:include>
		<div id="reservationListBox">
			<form action="" method="GET">
				<p class="headDESC">예약 내역</p>
				<table>
					<tr>
						<th>No</th>
						<th>상품번호</th>
						<th style="width: 550px;">상품정보</th>
						<th>판매자</th>
						<th>예약등록일</th>
						<th>방문예정일</th>
						<th>예약상황</th>
						<th>신고하기</th>
					</tr>
					<c:forEach items="${list}" var="list">
						<tr>
							<td style="width: auto;">${list.r_idx}</td>
							<td>${list.p_idx}</td>
							<td>
								<table class="p_PhoNDesc">
									<tr>
										<td class="p_PhoNDesc" rowspan="3" ><img
											src="../Uploaded_Img/${list.newFileName}" alt="${list.newFileName}"
											style="width: 125px; height: 125px;" onclick="location=href='../C_itemDetail?p_idx=${list.p_idx}'"/></td>
									</tr>
									<tr>
										<td class="p_PhoNDesc"><a href="../C_itemDetail?p_idx=${list.p_idx}">${list.p_name}</a></td>
									</tr>
								</table>
							</td>
							<td>${list.sid}</td>
							<td>${list.reg_date}</td>
							<td>${list.visit_date}</td>
							<td class="reserProcess">${list.status}</td>
							<td><a href="cReportForm?t_id=${list.sid}" class="reportBtn">신고하기</a></td>
						</tr>
					</c:forEach>
				</table>
			</form>
			<div>
				<c:if test="${currPage == 1}">
					<a href="#" class="pagingBtnDisable">처음</a>
				</c:if>
				<c:if test="${currPage > 1}">
					<a href="cReservationList?page=1" class="pagingBtn">처음</a>
				</c:if>
				<c:if test="${currPage == 1}">
					<a href="#" class="pagingBtnDisable">이전</a>
				</c:if>
				<c:if test="${currPage > 1}">
					<a href="cReservationList?page=${currPage-1}" class="pagingBtn">이전</a>
				</c:if>
				<a href="#" class="pagingBtnDisable"
					style="background-color: skyblue; color: white;">&nbsp;${currPage}&nbsp;</a>
				<c:if test="${currPage == maxPage}">
					<a href="#" class="pagingBtnDisable">다음</a>
				</c:if>
				<c:if test="${currPage < maxPage}">
					<a href="cReservationList?page=${currPage+1}" class="pagingBtn">다음</a>
				</c:if>
				<c:if test="${currPage == maxPage}">
					<a href="#" class="pagingBtnDisable">마지막</a>
				</c:if>
				<c:if test="${currPage < maxPage}">
					<a href="cReservationList?page=${maxPage}" class="pagingBtn">마지막</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
<script>

s</script>

</html>