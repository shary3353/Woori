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

        <div id="Admin_SellerDetail_Content"> <!--판매자 상세보기 컨텐츠-->
            <div id="SellerDetailInform">
                <h1>판매회원정보 상세보기페이지</h1>
            </div>
            <div id="SellerDetailData">
                <span class="dataname">아이디</span><span class="readdata">123-45-67890</span>
                <span class="dataname">이메일</span><span class="readdata">test@naver.com</span><br>
                <span class="dataname">생년월일</span><span class="readdata">1990.01.01</span>
                <span class="dataname">개인번호</span><span class="readdata">010-1234-5678</span><br>
                <span class="dataname">성별</span><span class="readdata">남</span>
                <span class="dataname">매장번호</span><span class="readdata">02-1111-0000</span><br>
            </div>

            <div id="SellerBlacklistState">
                <table id="BlacklistTable">
                    <tr>
                        <td style="width: 315px;">블랙리스트여부</td>
                        <td style="color: red; width: 315px;">FALSE</td>
                    </tr>
                </table>
            </div>

            <div id="Admin_Seller_ReportList">    <!--판매자 상세보기 신고리스트-->
                <table class="SellerReportList">
                    <tr>
                        <th>신고자</th>
                        <th>신고제목</th>
                    </tr>
                    <tr>
                        <td>조길동</td>
                        <td>판매자가 불친절해요.</td>
                    </tr>
                </table>
            </div>  <!--리스트 닫음-->

            <div id="Admin_Seller_ReportList_Paging"> <!--판매자 신고리스트 페이징부분-->
                <span class="Page_Content">처음</span>
                <span class="Page_Content">이전</span>
                <span class="Page_Number">1</span>
                <span class="Page_Content">다음</span>
                <span class="Page_Content">마지막</span>
            </div>  <!--페이징 닫음-->

        </div>  <!--판매자 상세보기 컨텐츠 닫음-->

        <div id="Admin_Footer"> <!--관리자 푸터-->
            푸터
        </div>  <!--관리자 푸터 닫음-->
    </div>  <!--Wrapper 닫음-->
</body>
</html>