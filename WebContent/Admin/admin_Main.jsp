<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>우리 양주</title>   
    <script src='main.js'></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminStyle.css">
</head>
<body>
    <div id="Wrapper">
        <div id="Admin_Header">  <!-- 로그인 알림 영역 -->
            <span id="Inform_Text"><img id="Admin_Login_Notice" src="${pageContext.request.contextPath}/img/admin_login_notice.png" alt="/로그인알림로고/" />&nbsp;현재 <b>관리자</b> 아이디로 로그인중입니다.</span>
            <span id="Logout_Link"><a href="">로그아웃 ></a></span>
        </div>

        <div id="Admin_Content"> <!-- 관리자 메인 컨텐츠 영역 -->
            <div id="Hello_Admin"> <!-- 인사글 -->
                <h3>관리자 <span>'test'</span>님 반갑습니다.</h3>
            </div>
            <div id="Admin_Menu">  <!-- 메인메뉴 -->
                <div id="customer" class="Menu_Btn" style="background-color: #1ABC9C;"><a href="${pageContext.request.contextPath}/Admin/cList">구매회원 관리</a></div>
                <div id="seller" class="Menu_Btn" style="background-color: #3498DB;"> <a href="${pageContext.request.contextPath}/Admin/sList"">판매회원 관리</a></div>
                <div id="blacklist" class="Menu_Btn" style="background-color: #EE8888;"><a href="${pageContext.request.contextPath}/Admin/bList">블랙리스트 관리</a></div>
                <div id="record" class="Menu_Btn" style="background-color: #9B59B6;"><a href="${pageContext.request.contextPath}/Admin/rList">신고 관리</a></div>
            </div>
        </div>

        <div id="Logo_Content">  <!-- 페이지 하단 로고이미지 영역 -->
            <img id="Main_Logo2" src="${pageContext.request.contextPath}/img/main_logo.png" alt="/우리양주로고/" />
        </div>
    </div>
</body>
</html>