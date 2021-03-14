<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>구매회원 마이페이지</title>
<style>


.reservationBox {
	display: inline-block;
}

.reservationInnerBox {
	float: left;
	width: 250px;
	height: 200px;
	border: 1px solid black;
	border-radius: 25px;
	margin: 10px 50px;
}

.wishListBox {
	position: relative;
	display: inline-block;
}

.wishListInnerBox {
	float: left;
	width: 250px;
	height: 200px;
	border: 1px solid black;
	border-radius: 25px;
	margin: 10px 50px;
}

.pImg {
	width: 125px;
	height: 193px;
}

table, td, th {
	text-align: center;
}
</style>
</head>

<body>
	<jsp:include page="../Include/navi.html"></jsp:include>
	<div style="min-width: 1920px">
	<jsp:include page="../Include/SideBar.html"></jsp:include>
	
		<form action="#" method="GET">
			<div>
				<div class="reservationBox">
					<h3
						style="font-size: 25px; font-weight: 600; color: mediumslateblue">예약내역</h3>
					<div class="reservationInnerBox">
						<table>
							<tr>
								<td rowspan="3"><img src="../img/no-image.png" alt="상품사진"
									class="pImg" /></td>
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
								<td rowspan="3"><img src="../img/no-image.png" alt="상품사진"
									class="pImg" /></td>
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
								<td rowspan="3"><img src="../img/no-image.png" alt="상품사진"
									class="pImg" /></td>
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
			</div>
		</form>
		<form action="#" method="GET">
			<div>
				<div class="wishListBox">
					<h3
						style="font-size: 25px; font-weight: 600; color: mediumslateblue">위시리스트</h3>
					<div class="wishListInnerBox">
						<table>
							<tr>
								<td rowspan="3"><img src="../img/no-image.png" alt="상품사진"
									class="pImg" /></td>
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
								<td rowspan="3"><img src="../img/no-image.png" alt="상품사진"
									class="pImg" /></td>
								<td>상품제목</td>
							</tr>
							<tr>
								<td>가격</td>
							</tr>
						</table>
						>
					</div>
					<div class="wishListInnerBox">
						<table>
							<tr>
								<td rowspan="3"><img src="../img/no-image.png" alt="상품사진"
									class="pImg" /></td>
								<td>상품제목</td>
							</tr>
							<tr>
								<td>가격</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
<script></script>

</html>