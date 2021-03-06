<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>
body {
	min-width: 1920;
}

a {
	text-decoration: none;
	color: rgb(239, 239, 245);
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
	color: rgb(43, 77, 109);;
	padding-top: 40px;
	font-size: 27px;
}

#form table, tr, th, td {
	border: 2px solid gray;
	padding: 5px;
	border-collapse: collapse;
}

#form th {
	text-align: center;
}

#table {
	width: 1000px;
}

#form {
	margin-left: 300px;
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

		<%@include file="../Include/SideBar.jsp"%>

		<div id="form">
			<h3>문의 내용</h3>
			<table id="table">
				<tr>
					<th>제목</th>
					<td colspan="5">${list.subject}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${list.cid}</td>
					<th>카테고리</th>
					<td>${list.category}</td>
					<th>작성일</th>
					<td>${list.q_reg_date}</td>
				</tr>
				<tr>
					<th>판매자</th>
					<td>${list.sid}</td>
					<th>문의상품</th>
					<td colspan="3">${list.p_name }</td>
				</tr>
				<tr>
					<th colspan="6">문의내용</th>
				</tr>
				<tr>
					<td colspan="6" style="height: 150px;">${list.content}</td>
				</tr>
				<c:if test="${list.s_answer == null}">
					<tr>
						<th colspan="6" style="height: 70px;">등록된 답변이 없습니다.</th>
					</tr>
				</c:if>
				<c:if test="${list.s_answer != null}">
					<tr>
						<th colspan="6" style="height: 70px;">${list.s_answer}</th>
					</tr>
				</c:if>
				<tr>
					<th colspan="6"><button onclick="location.href='cQuestionList'">목록으로
							돌아가기</button></th>
				</tr>
			</table>
		</div>
	</div>
</body>
<script>
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
</script>
</html>