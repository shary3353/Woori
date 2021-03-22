<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>구매회원 마이페이지</title>
<style>
.wrapper {
	position: absolute;
	margin-top: 25px;
	margin-left: 200px;
	min-width: 1100px;
}

.reservationBox {
	
}

.reservationInnerBox {
	float: left;
	width: 250px;
	height: 200px;
	border: 5px solid lightgray;
	border-radius: 25px;
	margin: 10px 50px;
	margin-bottom: 50px;
}

.wishListBox {
	
}

.wishListInnerBox {
	float: left;
	width: 250px;
	height: 200px;
	border: 5px solid lightgray;
	border-radius: 25px;
	margin: 10px 50px;
}

.pImg {
	width: 125px;
	height: 192px;
}

table, td, th {
	text-align: center;
	padding-left: 5px;
	padding-top: 2px;
}

td {
	width: 100%;
}

.more {
	text-align: right;
}

.moreBtn {
	text-decoration: none;
}

.ListDetail:hover{
	cursor: pointer;
}
</style>
</head>

<body>
	<%@include file="../Include/navi.jsp" %>
	<%@include file="../Include/SideBar.jsp" %>
	<div class="wrapper">
		<div class="reservationBox">
			<div class="more">
				<p
					style="font-size: 25px; font-weight: 600; text-align: left; color: mediumslateblue">
					예약 내역 <a href="cReservationList?page=1" class="moreBtn"><img
						src="../img/plus_Btn.png" style="width: 25px; height: 25px" /></a>
				</p>

			</div>
			<c:forEach items="${rList}" var="rList">
				<div class="reservationInnerBox">
					<table class="ListDetail" onclick="location=href='${pageContext.request.contextPath}/Consumer/C_itemDetail?p_idx=${rList.p_idx}'">
						<tr>
							<th rowspan="3"><img src="${pageContext.request.contextPath}/Uploaded_Img/${rList.newFileName}"
								alt="${rList.newFileName}" class="pImg" /></th>
							<td>${rList.p_name}</td>
						</tr>
						<tr>
							<td>${rList.p_price}원</td>
						</tr>
						<tr>
							<td>방문 예정일 ${rList.visit_date}</td>
						</tr>
					</table>
				</div>
			</c:forEach>
		</div>
		<div class="wishListBox">
			<div class="more">
				<p
					style="font-size: 25px; font-weight: 600; text-align: left; color: mediumslateblue">
					위시리스트 <a href="wishPaging?page=1" class="moreBtn"><img
						src="../img/plus_Btn.png" style="width: 25px; height: 25px" /></a>
				</p>
			</div>
			<c:forEach items="${wList}" var="wList">
				<div class="wishListInnerBox">
					<table class="ListDetail" onclick="location=href='${pageContext.request.contextPath}/Consumer/C_itemDetail?p_idx=${wList.p_idx}'">
						<tr>
							<th rowspan="3"><img src="${pageContext.request.contextPath}/Uploaded_Img/${wList.newFileName}"
								alt="${wList.newFileName}" class="pImg" /></th>
							<td>${wList.p_name}</td>
						</tr>
						<tr>
							<td>${wList.p_price}원</td>
						</tr>
					</table>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
<script></script>

</html>