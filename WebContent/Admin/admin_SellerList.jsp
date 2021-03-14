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

        <div id="Admin_Seller_Content"> <!--판매자관리컨텐츠-->
            <div id="Admin_Seller_List">    <!--판매자 리스트-->
                <table class="sellerlist" style="margin-left:auto; margin-right:auto;">
                    <tr>
                        <th colspan="6">판매 회원 목록</th>
                    </tr>
                    <tr>
                        <th>ID</th>
                        <th>신고 누적</th>
                        <th>블랙리스트 누적</th>
                        <th>블랙리스트 여부</th>
                        <th>가입날짜</th>
                        <th>블랙리스트 등록</th>
                    </tr>
                    <c:forEach items="${sList }" var="seller">
	                    <tr>
	                        <td>${seller.sid }</td>
	                        <td>${seller.cntReport }</td>
	                        <td>${seller.cntBlack}</td>
	                        <c:if test="${seller.isBlack == 1}">	<!-- 블랙리스트 true -->
	                        	<td>true</td>
	                        </c:if>
	                        <c:if test="${seller.isBlack == 0}">	<!-- 블랙리스트 false -->
	                        	<td>false</td>
	                        </c:if>
	                        <td>${seller.reg_date}</td>
	                        <c:if test="${seller.isBlack == 1}">	<!-- 블랙리스트 true -->
	                        	<td>이미등록된회원입니다.</td>
	                        </c:if>
	                        <c:if test="${seller.isBlack == 0}">	<!-- 블랙리스트 false -->
	                        	<td><button style="color:red;">등록</button></td>
	                        </c:if>
	                    </tr>
                    </c:forEach>
                </table>
            </div>  <!--리스트 닫음-->

            <div id="Admin_Searching">   <!--판매자리스트 검색 부분-->
                <input type="text" name="searchingSeller" id="searchingSellerInput" placeholder="ID검색란"><button>검색</button>
            </div>  <!--판매자리스트 검색 닫음-->

            <div id="Admin_Seller_List_Paging"> <!--판매자리스트 페이징부분-->
               <span class="Page_Content"><a href="./sList?page=1">처음</a></span>
                <span class="Page_Content"><a href="./sList?page=${currPage-1 }">이전</a></span>
                <span class="Page_Number">${currPage }</span>
                <span class="Page_Content"><a href="./sList?page=${currPage+1 }">다음</a></span>
                <span class="Page_Content"><a href="./sList?page=${maxSellerPage }">마지막</a></span>
            </div>  <!--페이징 닫음-->

        </div>  <!--판매자관리 컨텐츠 닫음-->

        <div id="Admin_Footer"> <!--관리자 푸터-->
            푸터
        </div>  <!--관리자 푸터 닫음-->
    </div>  <!--Wrapper 닫음-->
</body>
</html>