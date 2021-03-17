<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>우리 양주</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminStyle.css">
</head>
<body>
    <div id="Wrapper">
        <div id="Admin_Header">  <!-- 헤더 영역 -->
            <%@include file="admin_Header.jsp" %>
        </div>  <!--헤더 닫음-->

        <nav>    <!-- 관리자 네비게이션-->
            <%@include file="admin_Nav.jsp" %>
        </nav>  <!--네비게이션 닫음-->

        <div id="Admin_CustomerDetail_Content"> <!--판매자 상세보기 컨텐츠-->
            <div id="CustomerDetailInform">
                <h1>구매회원정보 상세보기페이지</h1>
            </div>
            <div id="CustomerDetailData">
                <span class="dataname">아이디</span><span class="readdata">testid</span>
                <span class="dataname">생년월일</span><span class="readdata">1990.01.01</span><br>
                <span class="dataname">이름</span><span class="readdata">고길동</span>
                <span class="dataname">이메일</span><span class="readdata">test@naver.com</span><br>
                <span class="dataname">성별</span><span class="readdata">남</span>
                <span class="dataname">매장번호</span><span class="readdata">010-1111-0000</span><br>
            </div>

            <div id="CustomerBlacklistState">
                <table id="BlacklistTable">
                    <tr>
                        <td style="width:315px;">블랙리스트여부</td>
                        <td style="color: red; width: 315px;">FALSE</td>
                    </tr>
                </table>
            </div>

            <div id="Admin_Customer_ReportList">    <!--구매자 상세보기 신고리스트-->
                <table class="CustomerReportList">
                    <tr>
                        <th>신고자</th>
                        <th>신고제목</th>
                    </tr>
                    <tr>
                        <td>123-45-67890</td>
                        <td>구매자가 악의적인 댓글을 남겨요.</td>
                    </tr>
                </table>
            </div>  <!--리스트 닫음-->

            <div id="Admin_Customer_ReportList_Paging"> <!--구매자 신고리스트 페이징부분-->
                <span class="Page_Content">처음</span>
                <span class="Page_Content">이전</span>
                <span class="Page_Number">1</span>
                <span class="Page_Content">다음</span>
                <span class="Page_Content">마지막</span>
            </div>  <!--페이징 닫음-->

        </div>  <!--구매자 상세보기 컨텐츠 닫음-->

        <div id="Admin_Footer"> <!--관리자 푸터-->
            푸터
        </div>  <!--관리자 푸터 닫음-->
    </div>  <!--Wrapper 닫음-->
</body>
</html>