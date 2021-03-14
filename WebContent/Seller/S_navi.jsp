<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <title>seller navi</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <style>
        *{ margin: 0; padding: 0; }
        body{
            min-width: 900px;
        }
        #seller-login{
            display: flex;
            align-items: center;
            padding: 20px;        
        }
        #notice-img{
            width: 16px;
        }
        #logo-img{
            float: right;
            margin-left: auto;
            width: 100px;
            margin-right: 0;
        }
        #seller-navi {
            display: flex;
            flex-direction: row;
            justify-content: center;
            width: 100%;
            background-color: lightblue;
            list-style-type: none;
            height: 72px;
        }
        .seller-navi-menu{
            display: block;
		    text-align: center;
        }
        .seller-navi-menu a {
            display:inline-block ;
            background-color: lightblue;
            color: black;
            padding : 10px 40px;
            text-decoration: none;
            text-align: center;
            font-weight: 600;
            font-size: 18pt;
		    line-height: 51px;
        }
        .seller-navi-menu a:hover{
            background-color: cadetblue;
            color: white;
        }
        .clicked a{
            background-color: cadetblue;
            color: white;
        }
    </style>
</head>
<body>
    <div id="seller-login">
        <img id="notice-img" src="../img/seller_login_notice.png" alt="seller-login-notice">
        <span>&nbsp;현재 판매자 아이디로 로그인 중입니다.&nbsp;</span>
        <span><a href="#">로그아웃></a></span>
        <img id="logo-img" src="../img/small_logo.png" alt="logo">
    </div>
    <nav>
        <ul id="seller-navi"><!--판매자 네이게이션-->
            <li class="seller-navi-menu"><a href="./sPfpDetail">회원정보</a></li>
            <li class="seller-navi-menu"><a href="./sItemList">물품관리</a></li>
            <li class="seller-navi-menu"><a href="./sQAList">문의내역</a></li>
            <li class="seller-navi-menu"><a href="./sReservationList">예약내역</a></li>
            <li class="seller-navi-menu"><a href="#신고내역">신고내역</a></li>
        </ul>
    </nav>
</body>
</html>