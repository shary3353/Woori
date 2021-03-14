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
                        <th colspan="7">블랙리스트 목록</th>
                    </tr>
                    <tr>
                        <th>번호</th>
                        <th>신고분류</th>
                        <th>신고제목</th>
                        <th>신고자</th>
                        <th>신고대상자</th>
                        <th>신고날짜</th>
                        <th>신고상태</th>
                    </tr>
                    <c:forEach items="${rList }" var="report">
                    	<tr>
                    		<td>${report.r_idx }</td>
                    		<td>${report.rc_code }</td>
                    		<td>${report.subject }</td>
                    		<td>${report.reporter_id }</td>
                    		<td>${report.target_id }</td>
                    		<td>${report.r_date }</td>
                    		<c:if test="${report.status == 0}">
                    			<td>
	                    			<select name="reportstate" id="">
		                            	<option value="100" selected="selected">처리중</option>
		                            	<option value="200">처리완료</option>
                        			</select><br><button>신고상태저장</button>
                        		</td>
                    		</c:if>
                    		<c:if test="${report.status == 1}">
                    			<td>
	                    			<select name="reportstate" id="">
		                            	<option value="100">처리중</option>
		                            	<option value="200" selected="selected">처리완료</option>
                        			</select><br><button>신고상태저장</button>
                        		</td>
                    		</c:if>
                    	</tr>
                    </c:forEach>
                </table>
            </div>  <!--리스트 닫음-->

            <div id="Admin_Searching">   <!--판매자리스트 검색 부분-->
            	<form action="rSearch">
	                <select name="rSearchOption" id="">
						<option value="100"  selected="selected">신고자ID</option>
						<option value="200">신고대상자ID</option>
					</select><input type="text" name="rSearch" id="searchingSellerInput" placeholder="ID검색란"><button>검색</button>
            	</form>
            </div>  <!--판매자리스트 검색 닫음-->

            <div id="Admin_Seller_List_Paging"> <!--판매자리스트 페이징부분-->
                <span class="Page_Content"><a href="./rList?page=1">처음</a></span>
                <span class="Page_Content"><a href="./rList?page=${currPage-1 }">이전</a></span>
                <span class="Page_Number">${currPage }</span>
                <span class="Page_Content"><a href="./rList?page=${currPage+1 }">다음</a></span>
                <span class="Page_Content"><a href="./rList?page=${maxReportPage }">마지막</a></span>
            </div>  <!--페이징 닫음-->

        </div>  <!--판매자관리 컨텐츠 닫음-->

        <div id="Admin_Footer"> <!--관리자 푸터-->
            푸터
        </div>  <!--관리자 푸터 닫음-->
    </div>  <!--Wrapper 닫음-->
</body>
</html>