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
	display: inline-block;
}

.reservationInnerBox {
	float: left;
	width: 250px;
	height: 200px;
	border: 5px solid lightgray;
	border-radius: 25px;
	margin: 0px 50px;
	margin-bottom: 50px;
}

.wishListBox {
	position: relative;
	display: inline-block;
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
</style>
</head>

<body>
	<jsp:include page="../Include/navi.html"></jsp:include>
	<jsp:include page="../Include/SideBar.html"></jsp:include>
	<div class="wrapper">
		<div class="reservationBox">
			<div class="more">
				<p style="font-size: 25px; font-weight: 600; text-align: left; color:mediumslateblue">예약 내역</p>
				<a href="#" class="moreBtn"><img src="../img/plus_Btn.png" style="width: 25px; height: 25px"/></a>
			</div>
			<div class="reservationInnerBox">
				<table>
					<tr>
						<th rowspan="3"><img src="../img/no-image.png" alt="상품사진"
							class="pImg" /></th>
						<td>상품제목</td>
					</tr>
					<tr>
						<td>가격</td>
					</tr>
					<tr>
						<td>방문예정일</td>
					</tr>
				</table>
			</div>
			<div class="reservationInnerBox">
				<table>
					<tr>
						<th rowspan="3"><img src="../img/no-image.png" alt="상품사진"
							class="pImg" /></th>
						<td>상품제목</td>
					</tr>
					<tr>
						<td>가격</td>
					</tr>
					<tr>
						<td>방문예정일</td>
					</tr>
				</table>
			</div>
			<div class="reservationInnerBox">
				<table>
					<tr>
						<th rowspan="3"><img src="../img/no-image.png" alt="상품사진"
							class="pImg" /></th>
						<td>상품제목</td>
					</tr>
					<tr>
						<td>가격</td>
					</tr>
					<tr>
						<td>방문예정일</td>
					</tr>
				</table>
			</div>
		</div>

		<div class="wishListBox">
			<div class="more">
				<p style="font-size: 25px; font-weight: 600; text-align: left; color:mediumslateblue">위시리스트</p>
				<a href="wishPaging?page=1" class="moreBtn"><img src="../img/plus_Btn.png" style="width: 25px; height: 25px"/></a>
			</div>
			<div class="wishListInnerBox">
				<table>
					<tr>
						<th rowspan="3"><img src="../img/no-image.png" alt="상품사진"
							class="pImg" /></th>
						<td>상품제목</td>
					</tr>
					<tr>
						<td>가격</td>
					</tr>
				</table>
			</div>
			<div class="wishListInnerBox">
				<table>
					<tr>
						<th rowspan="3"><img src="../img/no-image.png" alt="상품사진"
							class="pImg" /></th>
						<td>상품제목</td>
					</tr>
					<tr>
						<td>가격</td>
					</tr>
				</table>
			</div>
			<div class="wishListInnerBox">
				<table>
					<tr>
						<th rowspan="3"><img src="../img/no-image.png" alt="상품사진"
							class="pImg" /></th>
						<td>상품제목</td>
					</tr>
					<tr>
						<td>가격</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
<script></script>

</html>