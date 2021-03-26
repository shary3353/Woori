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
                        <th colspan="7">신고 목록</th>
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
                    <c:forEach items="${rList }" var="report" varStatus="status">
                    	<tr>
                    	<c:set var="reporter_id" value="${report.reporter_id }"></c:set>
                    	<c:set var="target_id" value="${report.target_id }"></c:set>
                    		<td>${report.r_idx }</td>
                    		<td>
                    				<c:if test="${report.rc_code == 100}">상품관련</c:if>
                    				<c:if test="${report.rc_code == 200}">판매자관련</c:if>
                    				<c:if test="${report.rc_code == 300}">구매자관련</c:if>
                    				<c:if test="${report.rc_code == 400}">기타</c:if>
                    		</td>
                    		<td><a href="${pageContext.request.contextPath}/Admin/rDetail?r_idx=${report.r_idx }">${report.subject }</a></td>
                    		<td>
                    			<c:if test="${fn:indexOf(reporter_id, '-') == -1 }"><a href="cDetail?id=${report.reporter_id }">${report.reporter_id }</a></c:if>	<!-- 구매자 -->
                    			<c:if test="${fn:indexOf(reporter_id, '-') > -1 }"><a href="sDetail?id=${report.reporter_id }">${report.reporter_id }</a></c:if>		<!-- 판매자 -->
                    		</td>
                    		<td>
                    			<c:if test="${fn:indexOf(target_id, '-') == -1 }"><a href="cDetail?id=${report.target_id }">${report.target_id  }</a></c:if>	<!-- 구매자 -->
                    			<c:if test="${fn:indexOf(target_id, '-') > -1 }"><a href="sDetail?id=${report.target_id  }">${report.target_id  }</a></c:if>		<!-- 판매자 -->
                    		</td>
                    		<td>${report.r_date }</td>
                            <td>
								<select name="reportstatus" id="reportStatus${status.count }">
		                    		<c:if test="${report.status == 0}">
										<option value="0" selected="selected">처리중</option>
										<option value="1">처리완료</option>
		                             </c:if>
		                             <c:if test="${report.status == 1}">
										<option value="0">처리중</option>
										<option value="1" selected="selected">처리완료</option>
		                              </c:if>
								</select>
	                              <br><button id="rStatusUpdateBtn${status.count }" value="${report.r_idx }">신고상태저장</button>
							</td>
                    	</tr>
                    </c:forEach>
                </table>
            </div>  <!--리스트 닫음-->

            <div id="Admin_Searching">   <!--판매자리스트 검색 부분-->
				<form action="rSearch">
                    <select name="rSearchOption" id="">
                        <option value="100"  selected="selected">신고자ID</option>
                        <option value="200">신고대상자ID</option>
                    </select><input type="text" name="inputR" id="searchingSellerInput" placeholder="ID검색란" onkeyup="fnChkByte(this, '50')"><button id="searchBtn">검색</button>
                </form>
            </div>  <!--판매자리스트 검색 닫음-->

            <div id="Admin_Seller_List_Paging"> <!--판매자리스트 페이징부분-->
	            <c:if test="${inputR eq null }">	<!-- 신고리스트페이징 -->
	            	<c:if test="${currPage == 1}">
	            		<span class="Page_Content"><a href="./rList?page=1">처음</a></span>
	            	</c:if>
	            	<c:if test="${currPage > 1}">
	            		<span class="Page_Content"><a href="./rList?page=1">처음</a></span>
	            	</c:if>
	            	<c:if test="${currPage == 1}">
						<span class="Page_Content"><a href="./rList?page=1">이전</a></span>
					</c:if>
					<c:if test="${currPage > 1}">
						<span class="Page_Content"><a href="./rList?page=${currPage-1 }">이전</a></span>
					</c:if>
					
	                <span class="Page_Number">${currPage }</span>
	                
	                <c:if test="${currPage == maxReportPage}">
							<span class="Page_Content"><a href="./rList?page=${maxReportPage}">다음</a></span>
						</c:if>
						<c:if test="${currPage < maxReportPage}">
							 <span class="Page_Content"><a href="./rList?page=${currPage+1 }">다음</a></span>
						</c:if>
						<c:if test="${currPage == maxReportPage}">
							 <span class="Page_Content"><a href="./rList?page=${maxReportPage }">마지막</a></span>
						</c:if>
						<c:if test="${currPage < maxReportPage}">
							 <span class="Page_Content"><a href="./rList?page=${maxReportPage }">마지막</a></span>
						</c:if>
	            </c:if>
	            
	            <c:if test="${inputR ne null }">	<!-- 신고검색페이징 -->
	            	<c:if test="${currPage == 1}">
	            		<span class="Page_Content"><a href="./rSearch?inputR=${inputR }&rSearchOption=${rSearchOption}&page=1">처음</a></span>
	            	</c:if>
	            	<c:if test="${currPage > 1}">
	            		<span class="Page_Content"><a href="./rSearch?inputR=${inputR }&rSearchOption=${rSearchOption}&page=1">처음</a></span>
	            	</c:if>
	            	<c:if test="${currPage == 1}">
						<span class="Page_Content"><a href="./rSearch?inputR=${inputR }&rSearchOption=${rSearchOption}&page=1">이전</a></span>
					</c:if>
					<c:if test="${currPage > 1}">
						<span class="Page_Content"><a href="./rSearch?inputR=${inputR }&rSearchOption=${rSearchOption}&page=${currPage-1 }">이전</a></span>
					</c:if>
					
	                <span class="Page_Number">${currPage }</span>
	                
	                <c:if test="${currPage == maxReportPage}">
							<span class="Page_Content"><a href="./rSearch?inputR=${inputR }&rSearchOption=${rSearchOption}&page=${maxReportPage}">다음</a></span>
						</c:if>
						<c:if test="${currPage < maxReportPage}">
							 <span class="Page_Content"><a href="./rSearch?inputR=${inputR }&rSearchOption=${rSearchOption}&page=${currPage+1 }">다음</a></span>
						</c:if>
						<c:if test="${currPage == maxReportPage}">
							 <span class="Page_Content"><a href="./rSearch?inputR=${inputR }&rSearchOption=${rSearchOption}&page=${maxReportPage }">마지막</a></span>
						</c:if>
						<c:if test="${currPage < maxReportPage}">
							 <span class="Page_Content"><a href="./rSearch?inputR=${inputR }&rSearchOption=${rSearchOption}&page=${maxReportPage }">마지막</a></span>
						</c:if>
	            </c:if>
				
            </div>  <!--페이징 닫음-->

        </div>  <!--판매자관리 컨텐츠 닫음-->

        <div id="Admin_Footer"> <!--관리자 푸터-->
            푸터
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

	$("body").on("click", "[id^=rStatusUpdateBtn]", function(event) {
		var r_idx = $('#'+this.id).val();
		var number = this.id.slice(-1);
		console.log(number);
		var rStatus = $('#reportStatus'+number).val();
		console.log(r_idx+' / '+rStatus);
		
 		$.ajax({
			type:"GET"
			,url:"rStatusUpdate"
			,data:{"r_idx":r_idx, "rStatus":rStatus}
			,dataType:"JSON"
			,success:function(obj){
				if(obj.updateReport){
					alert('신고 상태를 수정하였습니다.')
				}else{
					alert('신고 상태를 수정하지못했습니다.');
				}
			}
			,error:function(e){
				console.log(e);
			}
		}); 
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