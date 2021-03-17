<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <span class="dataname">아이디</span><span class="readdata">${Admin_CDetailData['Admin_selectedCData'].cid }</span>
                <span class="dataname">생년월일</span><span class="readdata">${Admin_CDetailData['Admin_selectedCData'].birthday }</span><br>
                <span class="dataname">이름</span><span class="readdata">${Admin_CDetailData['Admin_selectedCData'].name }</span>
                <span class="dataname">이메일</span><span class="readdata">${Admin_CDetailData['Admin_selectedCData'].email }</span><br>
                <span class="dataname">성별</span><span class="readdata">${Admin_CDetailData['Admin_selectedCData'].gender }</span>
                <span class="dataname">개인번호</span><span class="readdata">${Admin_CDetailData['Admin_selectedCData'].phone }</span><br>
            </div>

            <div id="CustomerBlacklistState">
                <table id="BlacklistTable">
                    <tr>
                        <td style="width:315px;">블랙리스트여부</td>
                        <td style="color: red; width: 315px;">
                        	<c:if test="${Admin_CDetailData['Admin_selectedCIsBlack']  == 0}">false</c:if>
                        	<c:if test="${Admin_CDetailData['Admin_selectedCIsBlack'] == 1}">true</c:if>
                        </td>
                    </tr>
                </table>
            </div>

            <div id="Admin_Customer_ReportList">    <!--구매자 상세보기 신고리스트-->
                <table class="CustomerReportList">
                    <tr>
                        <th>신고자</th>
                        <th>신고제목</th>
                    </tr>
                    <c:forEach items="${Admin_CDetailData['Admin_selectedCRList']}" var="report">
                    	<tr>
	                        <td>${report.reporter_id }</td>
	                        <td>${report.subject }</td>
                    </tr>
                    </c:forEach>
                </table>
            </div>  <!--리스트 닫음-->

            <!-- <div id="Admin_Customer_ReportList_Paging"> 구매자 신고리스트 페이징부분
                <span class="Page_Content">처음</span>
                <span class="Page_Content">이전</span>
                <span class="Page_Number">1</span>
                <span class="Page_Content">다음</span>
                <span class="Page_Content">마지막</span>
            </div>  페이징 닫음 -->
            <div>
				<c:if test="${Admin_CDetailData['Admin_selectedCRListCurrPage'] == 1}">
					<a href="#" class="pagingBtnDisable">처음</a>
				</c:if>
				<c:if test="${Admin_CDetailData['Admin_selectedCRListCurrPage'] > 1}">
					<a href="cReservationList?page=1" class="pagingBtn">처음</a>
				</c:if>
				<c:if test="${Admin_CDetailData['Admin_selectedCRListCurrPage'] == 1}">
					<a href="#" class="pagingBtnDisable">이전</a>
				</c:if>
				<c:if test="${Admin_CDetailData['Admin_selectedCRListCurrPage'] > 1}">
					<a href="cReservationList?page=${Admin_CDetailData['Admin_selectedCRListCurrPage']-1}" class="pagingBtn">이전</a>
				</c:if>
				<a href="#" class="pagingBtnDisable"
					style="background-color: skyblue; color: white;">&nbsp;${Admin_CDetailData['Admin_selectedCRListCurrPage']}&nbsp;</a>
				<c:if test="${Admin_CDetailData['Admin_selectedCRListCurrPage'] == maxPage}">
					<a href="#" class="pagingBtnDisable">다음</a>
				</c:if>
				<c:if test="${Admin_CDetailData['Admin_selectedCRListCurrPage'] <= 1}">
					<a href="cReservationList?page=${Admin_CDetailData['Admin_selectedCRListCurrPage']+1}" class="pagingBtn">다음</a>
				</c:if>
				<c:if test="${Admin_CDetailData['Admin_selectedCRListCurrPage'] == Admin_CDetailData['Admin_maxRPage']}">
					<a href="#" class="pagingBtnDisable">마지막</a>
				</c:if>
				<c:if test="${Admin_CDetailData['Admin_selectedCRListCurrPage'] < Admin_CDetailData['Admin_maxRPage']}">
					<a href="cReservationList?page=${Admin_CDetailData['Admin_maxRPage']}" class="pagingBtn">마지막</a>
				</c:if>
		</div>

        </div>  <!--구매자 상세보기 컨텐츠 닫음-->

        <div id="Admin_Footer"> <!--관리자 푸터-->
            푸터
        </div>  <!--관리자 푸터 닫음-->
    </div>  <!--Wrapper 닫음-->
</body>
</html>