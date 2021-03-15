<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
                        <th colspan="7">블랙리스트 목록</th>
                    </tr>
                    <tr>
                        <th>ID</th>
                        <th>구매/판매 회원</th>
                        <th>블랙리스트 누적</th>
                        <th>블랙리스트 사유</th>
                        <th>블랙리스트 등록 날짜</th>
                        <th>블랙리스트 취소</th>
                        <th>블랙리스트 등록 관리자</th>
                    </tr>
                    <c:forEach items="${bList }" var="black">
	                    <tr>
	                        <td>${black.id }</td>
	                        <td>판매자</td>
	                        <td>${black.stack }</td>
	                        <td>${black.reason }</td>
	                        <td>${black.reg_date }</td>
	                        <td><button>취소</button></td>
	                        <td>${black.admin_id }</td>
	                    </tr>
                    </c:forEach>
                </table>
            </div>  <!--리스트 닫음-->

            <div id="Admin_Searching">   <!--판매자리스트 검색 부분-->
	            <form action="bSearch">
	                    <select name="bSearchOption" id="">
	                        <option value="100"  selected="selected">판매자</option>
	                        <option value="200">구매자</option>
	                    </select>
	                    <input type="text" name="bSearch" id="searchingSellerInput" placeholder="ID검색란"><button>검색</button>
	                </form>
            </div>  <!--판매자리스트 검색 닫음-->

            <div id="Admin_Seller_List_Paging"> <!--판매자리스트 페이징부분-->
                <span class="Page_Content"><a href="./bList?page=1">처음</a></span>
                <span class="Page_Content"><a href="./bList?page=${currPage-1 }">이전</a></span>
                <span class="Page_Number">${currPage }</span>
                <span class="Page_Content"><a href="./bList?page=${currPage+1 }">다음</a></span>
                <span class="Page_Content"><a href="./bList?page=${maxBlackPage }">마지막</a></span>
            </div>  <!--페이징 닫음-->

        </div>  <!--판매자관리 컨텐츠 닫음-->

        <div id="Admin_Footer"> <!--관리자 푸터-->
            푸터
        </div>  <!--관리자 푸터 닫음-->
    </div>  <!--Wrapper 닫음-->
</body>
</html>