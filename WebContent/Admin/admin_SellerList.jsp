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
	                        <td><a href="sDetail?id=${seller.sid }"> ${seller.sid }</a></td>
	                        <td>${seller.cntReport }</td>
	                        <td id="stack${status.index }">${seller.stack}</td>
	                        <c:if test="${seller.isBlack == 1}">	<!-- 블랙리스트 true -->
	                        	<td>true</td>
	                        </c:if>
	                        <c:if test="${seller.isBlack == 0}">	<!-- 블랙리스트 false -->
	                        	<td id="false${status.index }">false</td>
	                        </c:if>
	                        <td>${seller.reg_date}</td>
	                        <c:if test="${seller.isBlack == 1}">	<!-- 블랙리스트 true -->
	                        	<td colspan="2">이미등록된회원입니다.</td>
	                        </c:if>
	                        <c:if test="${seller.isBlack == 0}">	<!-- 블랙리스트 false -->
	                        	<td id="inputReason${status.index }"><input type="text" id="bReason${status.index }" placeholder="블락사유를 적어주세요."></td>
		                        <td id="blackBtn${status.index }"><button style="color:red;" value="${seller.sid }" id="bRegistBtn${status.index }">등록</button></td>
	                        </c:if>
	                    </tr>
                    </c:forEach>
                </table>
            </div>  <!--리스트 닫음-->

            <div id="Admin_Searching">   <!--판매자리스트 검색 부분-->
				<form>
                    <input type="text" name="sSearch" id="searchingSellerInput" placeholder="ID검색란" onkeyup="fnChkByte(this, '50')"><button type="submit" formaction="sSearch">검색</button>
                </form>
            </div>  <!--판매자리스트 검색 닫음-->

            <div id="Admin_Seller_List_Paging"> <!--판매자리스트 페이징부분-->
	            <c:if test="${inputS eq null }">
	               <c:if test="${currPage == 1}">
	                	<span class="Page_Content"><a href="./sList?page=1">처음</a></span>
	                </c:if>
	                <c:if test="${currPage > 1}">
	                	<span class="Page_Content"><a href="./sList?page=1">처음</a></span>
	                </c:if>
	                <c:if test="${currPage == 1}">
						<span class="Page_Content"><a href="./sList?page=1">이전</a></span>
					</c:if>
					<c:if test="${currPage > 1}">
						<span class="Page_Content"><a href="./sList?page=${currPage-1 }">이전</a></span>
					</c:if>
					
	                <span class="Page_Number">${currPage }</span>
	                
	                <c:if test="${currPage == maxSellerPage}">
						 <span class="Page_Content"><a href="./sList?page=${maxSellerPage }">다음</a></span>
					</c:if>
					<c:if test="${currPage < maxSellerPage}">
						 <span class="Page_Content"><a href="./sList?page=${currPage+1 }">다음</a></span>
					</c:if>
					<c:if test="${currPage == maxSellerPage}">
						 <span class="Page_Content"><a href="./sList?page=${maxSellerPage }">마지막</a></span>
					</c:if>
					<c:if test="${currPage < maxSellerPage}">
						 <span class="Page_Content"><a href="./sList?page=${maxSellerPage }">마지막</a></span>
					</c:if>
	            </c:if>
            </div>  <!--페이징 닫음-->

        </div>  <!--판매자관리 컨텐츠 닫음-->

        <div id="Admin_Footer"> <!--관리자 푸터-->
            
        </div>  <!--관리자 푸터 닫음-->
    </div>  <!--Wrapper 닫음-->
</body>
<script type="text/javascript">
	$("body").on("click", "[id^=bRegistBtn]", function(event) {
		var sid = $('#'+this.id).val();
		var number = this.id.slice(-1);
		var bReason = $('#bReason'+number).val();
		console.log(sid+' / '+bReason);
		
		if(bReason == ""){
			alert("블락 사유를 적어주세요.");
		}else{
		$.ajax({
			type:"GET"
			,url:"sBlackRegist"
			,data:{"id":sid, "bReason":bReason}
			,dataType:"JSON"
			,success:function(obj){
				console.log(obj);
				if(obj.addBlack){
					alert('해당 판매회원을 블랙리스트에 추가하였습니다.');
					$('td').remove('#blackBtn'+number);
					$('#inputReason'+number).attr('colspan', '2');
					$('#inputReason'+number).html('이미등록된회원입니다.');
					$('#stack'+number).html(obj.newStack);
					$('#false'+number).html('true');
				}else{
					alert('블랙리스트 등록에 실패하였습니다.');
				}
			}
			,error:function(e){
				console.log(e);
			}
		});
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