<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>위시리스트</title>

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

#wishListBox {
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
a {
text-decoration: none;
color: black;
}
a:hover{
color: black;
}
img:hover{
cursor: pointer;
}
</style>
</head>

<body>
	<div style="min-width: 1920px">
	<%@include file="../Include/navi.jsp" %>
	<%@include file="../Include/SideBar.jsp" %>
		<div id="wishListBox">
			<form action="cReservation" method="GET">
				<p class="headDESC">위시리스트</p>
				<table>
					<tr>
						<th>No</th>
						<th>상품번호</th>
						<th style="width: 650px;">상품정보</th>
						<th>가격</th>
						<th>판매자</th>
						<th>예약</th>
						<th>삭제</th>
					</tr>
					<c:forEach items="${list}" var="list">
						<c:if test="${list.is_sold == 1}">
							<tr>
								<td style="width: auto;">${list.wish_idx}</td>
								<td>${list.p_idx}</td>
								<td>
									<table class="p_PhoNDesc">
										<tr>
											<td class="p_PhoNDesc" rowspan="3"><img
												src="${pageContext.request.contextPath}/Uploaded_Img/${list.photoPath}"
												alt="${list.photoPath}" style="width: 125px; height: 125px;"
												onclick="location=href='${pageContext.request.contextPath}/Consumer/C_itemDetail?p_idx=${list.p_idx}'" />
											</td>
										</tr>
										<tr>
											<td class="p_PhoNDesc"><a href="${pageContext.request.contextPath}/Consumer/C_itemDetail?p_idx=${list.p_idx}">${list.p_name}</a></td>
										</tr>
									</table>
								</td>
								<td>${list.p_price}원</td>
								<td>${list.sid}</td>
								<td><a href="C_ItemReservation?p_idx=${list.p_idx}"
									class="reserBtn">예약하기</a></td>
								<td><input class="delBtn" type="button" value="X"
									onclick="location.href='wishDel?wish_idx=${list.wish_idx}'" /></td>
							</tr>
						</c:if>
						<c:if test="${list.is_sold == 0}">
							<tr style="background-color: gray;">
								<td style="width: auto;">${list.wish_idx}</td>
								<td>${list.p_idx}</td>
								<td>
									<table class="p_PhoNDesc">
										<tr>
											<c:if test="${empty list.photoPath  }">
											<td class="p_PhoNDesc" rowspan="3"><img
												src="${pageContext.request.contextPath}/img/no-image.png"
												alt="${list.photoPath}" style="width: 125px; height: 125px;" />
											</td>
											</c:if>
											<c:if test="${!empty list.photoPath  }">
											<td class="p_PhoNDesc" rowspan="3"><img
												src="${pageContext.request.contextPath}/Uploaded_Img/${list.photoPath}"
												alt="${list.photoPath}" style="width: 125px; height: 125px;" />
											</td>
											</c:if>
										</tr>
										<tr>
											<td class="p_PhoNDesc" style="color: red;">현재 판매중인 상품이
												아닙니다, 삭제해주세요!</td>
										</tr>
									</table>
								</td>
								<td>${list.p_price}원</td>
								<td>${list.sid}</td>
								<td><a href="../C_ItemReservation?p_idx=${list.p_idx}"
									class="reserBtn" hidden="hidden">예약하기</a></td>
								<td><input class="delBtn" type="button" value="X"
									onclick="location.href='wishDel?wish_idx=${list.wish_idx}'" /></td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</form>
			<div>
				<c:if test="${currPage == 1}">
					<a href="#" class="pagingBtnDisable">처음</a>
				</c:if>
				<c:if test="${currPage > 1}">
					<a href="wishPaging?page=1" class="pagingBtn">처음</a>
				</c:if>
				<c:if test="${currPage == 1}">
					<a href="#" class="pagingBtnDisable">이전</a>
				</c:if>
				<c:if test="${currPage > 1}">
					<a href="wishPaging?page=${currPage-1}" class="pagingBtn">이전</a>
				</c:if>
				<a href="#" class="pagingBtnDisable"
					style="background-color: skyblue; color: white;">&nbsp;${currPage}&nbsp;</a>
				<c:if test="${currPage == maxPage}">
					<a href="#" class="pagingBtnDisable">다음</a>
				</c:if>
				<c:if test="${currPage < maxPage}">
					<a href="wishPaging?page=${currPage+1}" class="pagingBtn">다음</a>
				</c:if>
				<c:if test="${currPage == maxPage}">
					<a href="#" class="pagingBtnDisable">마지막</a>
				</c:if>
				<c:if test="${currPage < maxPage}">
					<a href="wishPaging?page=${maxPage}" class="pagingBtn">마지막</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
<script>
var msg = "${msg}";
if(msg != ""){
	alert(msg);
	msg="";
}
</script>

</html>