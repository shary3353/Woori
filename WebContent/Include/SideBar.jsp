<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Side Bar</title>
<style>
    div.C_MyPageNavi {
        width: 150px;
        height: 100px;
        line-height: 100px;
        font-family: Arial, Helvetica, sans-serif;
        text-align: right;
        font-weight: 600;
        font-size: 18px;
        background-color: rgb(202, 202, 202);
    }
    
    #mainMyPage {
        width: 150px;
        height: 125px;
        line-height: 125px;
        font-size: 20px;
        font-weight: 600;
        text-align: right;
        background-color: rgb(202, 202, 202);
    }
    
    div.C_MyPageNavi:hover {
        background-color: rgb(138, 138, 138);
    }
    
    div.C_MyPageNavi>a {
        text-decoration: none;
        color: black;
    }
    
    #naviBox {
        float: left;
    }
</style>
</head>
<body>
<div id="naviBox">
    <div id="mainMyPage" class="C_MyPageNavi">
        <a href="cMyPageMain">마이페이지</a>
    </div>
    <div class="C_MyPageNavi">
        <a href="cDetail">회원정보&nbsp;</a>
    </div>
    <div class="C_MyPageNavi">
        <a href="wishPaging?page=1">위시리스트&nbsp;</a>
    </div>
    <div class="C_MyPageNavi">
        <a href="cQuestionList?page=1">문의내역&nbsp;</a>
    </div>
    <div class="C_MyPageNavi">
        <a href="cReservationList?page=1">예약내역&nbsp;</a>
    </div>
    <div class="C_MyPageNavi">
        <a href="cReportList?page=1">신고내역&nbsp;</a>
    </div>
</div>
</body>
</html>