<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>우리 양주</title>
    <script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
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
                    <c:forEach items="${bList }" var="black" varStatus="status">
	                    <tr>
	                        <td>${black.id }</td> <c:set var="id" value="${black.id }"></c:set>
	                        <td><c:if test="${fn:indexOf(id, '-') == -1 }">구매자</c:if><c:if test="${fn:indexOf(id, '-') > -1 }">판매자</c:if></td>
	                        <td>${black.stack }</td>
	                        <td>${black.reason }</td>
	                        <td>${black.reg_date }</td>
	                        <td><a href="${pageContext.request.contextPath}/Admin/blackUpdate?id=${black.id }">취소</a></td>
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
	                    <input type="text" name="bSearch" id="searchingSellerInput" placeholder="ID검색란" onkeyup="fnChkByte(this, '50')"><button id="searchBtn">검색</button>
	                </form>
            </div>  <!--판매자리스트 검색 닫음-->

            <div id="Admin_Seller_List_Paging"> <!--판매자리스트 페이징부분-->
            	<c:if test="${inputB eq null }">
	            	<c:if test="${currPage == 1}">
	                	<span class="Page_Content"><a href="./bList?page=1">처음</a></span>
	                </c:if>
	                <c:if test="${currPage > 1}">
	                	<span class="Page_Content"><a href="./bList?page=1">처음</a></span>
	                </c:if>
	                <c:if test="${currPage == 1}">
						<span class="Page_Content"><a href="./bList?page=1">이전</a></span>
					</c:if>
					<c:if test="${currPage > 1}">
						<span class="Page_Content"><a href="./bList?page=${currPage-1 }">이전</a></span>
					</c:if>
					
	                <span class="Page_Number">${currPage }</span>
	                
	                <c:if test="${currPage == maxBlackPage}">
						 <span class="Page_Content"><a href="./bList?page=${maxBlackPage }">다음</a></span>
					</c:if>
					<c:if test="${currPage < maxBlackPage}">
						 <span class="Page_Content"><a href="./bList?page=${currPage+1 }">다음</a></span>
					</c:if>
					<c:if test="${currPage == maxBlackPage}">
						<span class="Page_Content"><a href="./bList?page=${maxBlackPage }">마지막</a></span>
					</c:if>
					<c:if test="${currPage < maxBlackPage}">
						<span class="Page_Content"><a href="./bList?page=${maxBlackPage }">마지막</a></span>
					</c:if>
            	</c:if>
            </div>  <!--페이징 닫음-->

        </div>  <!--판매자관리 컨텐츠 닫음-->

        <div id="Admin_Footer"> <!--관리자 푸터-->
          
        </div>  <!--관리자 푸터 닫음-->
    </div>  <!--Wrapper 닫음-->
</body>
<script type="text/javascript">
$('#searchBtn').click(function(){
	var inputID = $('#searchingSellerInput').val();
	if(inputID == ""){
		alert("검색할 아이디를 입력하세요.");
		return false;
	}
});

function fnChkByte(obj, maxByte){
	var str = obj.value;
	var str_len = str.length;
	
	var rByte = 0;		//카운팅 할 변수
	var rLen = 0;		//잘라낼 글자수
	var one_char = "";	//하나씩 카운팅하기 위한 변수
	var str2 = "";		//잘라낸 문자열을 담을 변수
	
	for(var i=0; i<str_len; i++){
		one_char = str.charAt(i);
		if(escape(one_char).length > 4){
			rByte += 3;	//한글은 3byte
		}else{
			rByte ++;	//나머지 1byte
		}
		
		if(rByte <= maxByte){	//맥스바이트에 도달하면
			rLen = i+1;	//잘라낼 위치 지정
		}
	}
	
	
	if(rByte > maxByte){
		alert(Math.ceil((maxByte/3))+"자를 초과할 수 없습니다.");
		str2 = str.substr(0, rLen);
		obj.value = str2;
		fnChkByte(obj, maxByte);
	}
	
}
</script>
</html>