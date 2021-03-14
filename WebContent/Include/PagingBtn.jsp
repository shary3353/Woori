<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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
<!-- url부분만 해당 url로 고쳐서 사용하면 됩니다. -->
	<div>
		<c:if test="${currPage == 1}">
			<a href="#" class="pagingBtnDisable">처음</a>
		</c:if>
		<c:if test="${currPage > 1}">
			<a href="url?page=1" class="pagingBtn">처음</a>
		</c:if>
		<c:if test="${currPage == 1}">
			<a href="#" class="pagingBtnDisable">이전</a>
		</c:if>
		<c:if test="${currPage > 1}">
			<a href="url?page=${currPage-1}" class="pagingBtn">이전</a>
		</c:if>
		<a href="#" class="pagingBtnDisable"
			style="background-color: skyblue; color: white;">&nbsp;${currPage}&nbsp;</a>
		<c:if test="${currPage == maxPage}">
			<a href="#" class="pagingBtnDisable">다음</a>
		</c:if>
		<c:if test="${currPage <= 1}">
			<a href="url?page=${currPage+1}" class="pagingBtn">다음</a>
		</c:if>
		<c:if test="${currPage == maxPage}">
			<a href="#" class="pagingBtnDisable">마지막</a>
		</c:if>
		<c:if test="${currPage < maxPage}">
			<a href="url?page=${maxPage}" class="pagingBtn">마지막</a>
		</c:if>
	</div>
</body>
</html>