<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                        <th colspan="7">판매 회원 목록</th>
                    </tr>
                    <tr>
                        <th>ID</th>
                        <th>신고 누적</th>
                        <th>블랙리스트 누적</th>
                        <th>블랙리스트 여부</th>
                        <th>가입날짜</th>
                        <th>블랙리스트 사유</th>
                        <th>블랙리스트 등록</th>
                    </tr>
                    <c:forEach items="${sList }" var="seller" varStatus="status">
	                    <tr>
	                        <td>${seller.sid }</td>
	                        <td>${seller.cntReport }</td>
	                        <td>${seller.stack}</td>
	                        <c:if test="${seller.isBlack == 1}">	<!-- 블랙리스트 true -->
	                        	<td>true</td>
	                        </c:if>
	                        <c:if test="${seller.isBlack == 0}">	<!-- 블랙리스트 false -->
	                        	<td>false</td>
	                        </c:if>
	                        <td>${seller.reg_date}</td>
	                        <c:if test="${seller.isBlack == 1}">	<!-- 블랙리스트 true -->
	                        	<td colspan="2">이미등록된회원입니다.</td>
	                        </c:if>
	                        <c:if test="${seller.isBlack == 0}">	<!-- 블랙리스트 false -->
	                        	<td id="inputReason${status.count }"><input type="text" id="bReason${status.count }" placeholder="블락사유를 적어주세요."></td>
		                        <td id="blackBtn${status.count }"><button style="color:red;" value="${seller.sid }" id="bRegistBtn${status.count }">등록</button></td>
	                        </c:if>
	                    </tr>
                    </c:forEach>
                </table>
            </div>  <!--리스트 닫음-->

            <div id="Admin_Searching">   <!--판매자리스트 검색 부분-->
				<form>
                    <input type="text" name="sSearch" id="searchingSellerInput" placeholder="ID검색란"><button type="submit" formaction="sSearch">검색</button>
                </form>
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
<script type="text/javascript">
	$("body").on("click", "[id^=bRegistBtn]", function(event) {
	var sid = $('#'+this.id).val();
	var number = this.id.slice(-1);
	var bReason = $('#bReason'+number).val();
	console.log(sid+' / '+bReason);
	
	$.ajax({
		type:"GET"
		,url:"sBlackRegist"
		,data:{"id":sid, "bReason":bReason}
		,dataType:"JSON"
		,success:function(obj){
			console.log(obj);
			if(obj.addBlack){
				$('td').remove('#blackBtn'+number);
				$('#inputReason'+number).attr('colspan', '2');
				$('#inputReason'+number).html('이미등록된회원입니다.');
			}else{
				alert('블랙리스트 등록에 실패하였습니다.');
			}
		}
		,error:function(e){
			console.log(e);
		}
	});
}); 
	
	
</script>
</html>