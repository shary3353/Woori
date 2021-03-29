<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>1:1문의 게시판</title>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<style>
a {
	text-decoration: none;
	color: inherit;
}

.sideMenu {
	width: 180px;
	height: 600px;
	background-color: rgb(43, 77, 109);
	font-weight: bold;
	color: rgb(239, 239, 245);
}

.Service {
	font-size: 25px;
	text-align: right;
	padding: 50px 20px;
}

.One {
	font-size: 15px;
	text-align: right;
	margin: 10px;
	padding: 10px;
}

.Question {
	font-size: 15px;
	text-align: right;
	margin: 10px;
	padding: 10px;
}

.Report {
	font-size: 15px;
	text-align: right;
	margin: 10px;
	padding: 10px;
}

.One:hover, .Question:hover, .Report:hover {
	text-decoration: none;
	background-color: lightgray;
}

.seMain {
	float: left;
}

h3 {
	color: rgb(43, 77, 109);
	padding-top: 40px;
	font-size: 27px;
}

#Admin_Seller_List_Paging {
	text-align: center;
	margin-right: 300px;
	margin-top: 20px;
}

#list {
	margin-left: 50px;
}

#list table, th, td {
	border-bottom: 2px solid gray;
	border-right: 1px solid grey;;
	border-collapse: collapse;
	padding: 20px 20px;
}

.num {
	width: 50px;
}

.subject {
	width: 1000px;
	text-align: left;
}

.date {
	width: 150px;
}

#list_div {
	margin-left: 300px;
}
input[type="number"]::-webkit-inner-spin-button {-webkit-appearance: none;margin: 0;}

.viewBtn{
padding:2px 7px;
}
</style>
</head>
<body>
	<div style="min-width: 1920px">
		<c:choose>
    		<c:when test="${sessionScope.cLoginID eq null}">
			<jsp:include page="../Include/loginnavi.jsp"></jsp:include>
    		</c:when>
   
    		<c:when test="${sessionScope.cLoginID ne null}">
			<jsp:include page="../Include/navi.jsp"></jsp:include>
    		</c:when>
    	</c:choose>
		<div class="seMain">
			<div class="sideMenu">
				<div class="Service">고객센터</div>
				<div class="One">
					<a href="qList">1:1 문의하기</a>
				 
				</div>
				<div class="Question">
					<a href="Question.jsp">자주묻는 질문</a>
				</div>
				<div class="Report">
					<a href="#" onclick="idCheck2();">신고하기</a>
				</div>
			</div>
		</div>
		<div id="list_div">
			<h3>1:1 문의내역</h3>
				<div id="list">
					<!-- <form action="qDetail" method="post"> -->
		
					<table>
						<tr>
							<th class="num">번호</th>
							<th class="subject">제목</th>
							<th class="date">날짜</th>
						</tr>
						<c:forEach items="${list}" var="list" varStatus="status">
							<form id="mainF" action="qDetail" method ="post">
							<tr>
								<th id="line${status.index }" class="num">
								<input  type="text" name="q_idx"
									value="${list.q_idx }" readonly style="display: none;" />
									${list.q_idx }
									</th>
								<th class="subject"><a href="#" id="subjectDetail"
									onclick="has('${list.q_idx}');">${list.subject }</a></th>
								<th class="date">${list.q_reg_date }</th>
							</tr>
							<tr>
								<td colspan="3" id="${list.q_idx }" style="display: none;">
									<input
									 id ="pw${status.index }" type="text"  style="-webkit-text-security: disc;" 
									 maxlength="4"  name="password" 
									 oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
									<button  class="viewBtn" name="viewButton" >확인</button>
								</td>
							</tr>
								</form>
						</c:forEach>
					</table>
				
			
				</div>
			<div id="Admin_Seller_List_Paging">
				<!--판매자리스트 페이징부분-->
				<span class="Page_Content"> <c:if test="${currnPage == 1}">처음</c:if>
					<c:if test="${currPage > 1 }">
						<a href="./qList?page=${1 }">처음</a>
					</c:if>
				</span> 
				<span class="Page_Content"> <c:if test="${currPage == 1 }">이전</c:if>
					<c:if test="${currPage > 1}">
						<a href="./qList?page=${currPage-1 }">이전</a>
					</c:if>
				</span>
				<span id="Page_Number"><a>${currPage}</a></span>
				 <span class="Page_Content"> <c:if test="${currPage == maxPage }">다음</c:if>
					<c:if test="${currPage < maxPage }">
						<a href="./qList?page=${currPage + 1}">다음</a>
					</c:if>
				</span>
				 <span class="Page_Content"> <c:if
						test="${currPage < maxPage }">
						<a href="./qList?page=${maxPage}">마지막</a>
					</c:if>	
				</span>
				
				<div style=" float: right;">
					<button style="padding:7px 7px;" onclick="idCheck();"> 문의글 작성</button>
				</div>
			</div>
		</div>
		</div>
</body>

<script>
	
	function has(id) {
		var idx = document.getElementById(id);
		console.log(idx)
		if (idx.style.display == "none") {
			idx.style.display = '';

		} else {
			idx.style.display = "none";
		}
	}

	var msg = "${msg}";
	if (msg != "") {
		alert(msg);
	}
    function idCheck(){ 
        var uid = '<%=(String)session.getAttribute("cLoginID")%>';
		console.log(uid);
         if(uid=="null"){ 
            alert("로그인이 필요한 항목입니다."); 
         }
         else{
            location.replace("./Q_write.jsp");
         }
   }  
	
	 function idCheck2(){ 
        var uid = '<%=(String)session.getAttribute("cLoginID")%>';
		console.log(uid);
         if(uid=="null"){ 
            alert("로그인이 필요한 항목입니다."); 
         }
         else{
            location.replace("./Report.jsp");
         }
   }   

	function cc(){
		var pwpw = document.getElementByClass('viewBtn');
		console.log(pwpw.val());
		
	}
	function maxLengthCheck(object){
	    if (object.value.length > object.maxLength){
	        object.value = object.value.slice(0, object.maxLength);
	    }    
	}


</script>
</html>