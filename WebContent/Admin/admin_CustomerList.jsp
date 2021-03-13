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

        <div id="Admin_Seller_Content"> <!--구매자관리컨텐츠-->
            <div id="Admin_Seller_List">    <!--구매자 리스트-->
                <table class="sellerlist" style="margin-left:auto; margin-right:auto;">
                    <tr>
                        <th colspan="6">구매 회원 목록</th>
                    </tr>
                    <tr>
                        <th>ID</th>
                        <th>신고 누적</th>
                        <th>블랙리스트 누적</th>
                        <th>블랙리스트 여부</th>
                        <th>가입날짜</th>
                        <th>블랙리스트 등록</th>
                    </tr>
                    <c:forEach items="${cList }" var="customer">
	                    <tr>
	                        <td>${customer.cid }</td>
	                        <td>${customer.cntReport }</td>
	                        <td>${customer.cntBlock }</td>
	                        <td>${customer.isBlack }</td>
	                        <td>${customer.reg_date }</td>
	                        <td>이미등록된회원입니다.</td>
	                    </tr>
                    </c:forEach>
                </table>
            </div>  <!--리스트 닫음-->

            <div id="Admin_Searching">   <!--구매자자리스트 검색 부분-->
                <input type="text" name="searchingSeller" id="searchingSellerInput" placeholder="ID검색란"><button>검색</button>
            </div>  <!--구매자리스트 검색 닫음-->

            <div id="Admin_Seller_List_Paging"> <!--구매자리스트 페이징부분-->
                <span class="Page_Content">처음</span>
                <span class="Page_Content">이전</span>
                <span class="Page_Number">1</span>
                <span class="Page_Content">다음</span>
                <span class="Page_Content">마지막</span>
            </div>  <!--페이징 닫음-->

        </div>  <!--구매자관리 컨텐츠 닫음-->

        <div id="Admin_Footer"> <!--관리자 푸터-->
            푸터
        </div>  <!--관리자 푸터 닫음-->
    </div>  <!--Wrapper 닫음-->
</body>
</html>