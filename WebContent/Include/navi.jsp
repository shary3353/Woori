<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.cLoginID eq null }">
	<script type="text/javascript">
			location.href = "./C_login.jsp";
	</script>
</c:if>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>네비게이션을 만들어보자</title>
<style>
* {
	margin: 0;
	padding: 0;
}

body {
	min-width: 1920px;
}
/* 상단메뉴 */
#topmenu {
	width: 1920px;
	height: 110px;
}

#topmenu div {
	float: left;
}

#topmenu #mainlogo {
	margin-left: 50px;
	margin-bottom: 20px;
}

#topmenu #searchicon {
	margin-left: 350px;
	margin-top: 22px;
	margin-right: 20px;
}

#topmenu #searchmenu {
	margin-top: 23px;
	width: 630px;
	height: 55px;
}

#topmenu #search_border {
	display: inline-block;
	width: 500px;
	height: 50px;
	border: 5px solid #283744;
	background: white;
}

#topmenu #searchbar {
	width: 480px;
	height: 35px;
	margin: 9px 0 0 9px;
	border: 0;
	line-height: 21px;
	font-weight: bold;
	font-size: 16px;
	outline: none;
}

#search {
	width: 70px;
	height: 60px;
	margin: 0;
	border: 0;
	vertical-align: top;
	background: #283744;
	color: white;
	font-weight: bold;
	font-size: 15px;
	border-radius: 1px;
	cursor: pointer;
}

#toplistBox {
}

#toplistBox .toplist {
	padding-left: 30px;
	padding-top: 25px;
	padding-right: 30px;
}

.toplist a {
	display: inline-block;
	color: #283744;
	text-decoration: none;
	font-size: 12pt;
	font-family: Arial, sans-serif;
	font-weight: bold;
}
/* 네비게이션 */
nav {
	height: 70px;
	width: 1920px;
	background: #34495e;
	border-bottom: 2px solid #283744;
}

nav div {
	float: left;
}

nav .navilist {
	margin-left: 20px;
	display: inline;
	width: 200px;
	height: 72px;
	text-align: center;
}

nav .navilist a {
	display: inline-block;
	color: #ffffff;
	text-decoration: none;
	font-size: 18pt;
	font-family: Arial, sans-serif;
	font-weight: bold;
	line-height: 72px;
}

nav .navilist:hover {
	background-color: #2c3e50;
}
</style>
</head>

<body>
	<div id="topmenu">
		<div id="mainlogo">
			<a href="${pageContext.request.contextPath}/Consumer/C_main"><img src="${pageContext.request.contextPath}/img/mainlogo.png" width="200"
				height="100"></a>
		</div>

		<div id="searchicon">
			<img src="${pageContext.request.contextPath}/img/search.png" alt="검색" width="55" height="55">
		</div>

		<div id="searchmenu">
		<form action="../Consumer/C_SearchList" method="post">
			<span id='search_border'> <input type='text' id='searchbar' name='searchname'
				placeholder="상품이름을 입력하세요"/>
			</span>
			<button id='search'>검색</button>
		</form>
		</div>

		<div id="toplistBox">
			<div class="toplist">
				<img src="${pageContext.request.contextPath}/img/mypage.png" width="40" height="40"> <a
					href="${pageContext.request.contextPath}/Consumer/cMyPageMain">마이페이지</a>
			</div>

			<div class="toplist">
				<img src="${pageContext.request.contextPath}/img/wishlist.png" width="40" height="40"> <a
					href="${pageContext.request.contextPath}/Consumer/wishPaging?page=1">위시리스트</a>
			</div>
			
			<div class="toplist">
				<img src="${pageContext.request.contextPath}/img/logout.png" width="40" height="40"> <a
					href="${pageContext.request.contextPath}/cLogout">로그아웃</a>
			</div>
			

		</div>

	</div>


	<nav class="navi">
		<div class="navilist">
			<a href="${pageContext.request.contextPath}/Consumer/NaviCategory?c_idx=1">와인</a>
		</div>
		<div class="navilist">
			<a href="${pageContext.request.contextPath}/Consumer/NaviCategory?c_idx=2">위스키</a>
		</div>
		<div class="navilist">
			<a href="${pageContext.request.contextPath}/Consumer/NaviCategory?c_idx=3">꼬냑/브랜디</a>
		</div>
		<div class="navilist">
			<a href="${pageContext.request.contextPath}/Consumer/NaviCategory?c_idx=4">보드카</a>
		</div>
		<div class="navilist">
			<a href="${pageContext.request.contextPath}/Consumer/NaviCategory?c_idx=5">샴페인</a>
		</div>
		<div class="navilist">
			<a href="${pageContext.request.contextPath}/ServiceCenter/qList">고객센터</a>
		</div>
	</nav>
</body>
<script>
</script>

</html>