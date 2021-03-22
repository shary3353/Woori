<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
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
	color: rgb(43, 77, 109);;
	padding-top: 40px;
	font-size: 27px;
}

#content {
	width: 700px;
	height: 200px;
}

table, th, td {
	border: 2px solid gray;
	border-collapse: collapse;
	padding: 15px;
}

table {
	width: 950px;
}

th {
	width: 150px;
}

#form {
	margin-left: 300px;
}

#button {
	text-align: center;
	margin-right: 400px;
	margin-top: 30px;
}
</style>
</head>
<body>
	<div style="min-width: 1920px">

		<c:choose>
			<c:when test="${sessionScope.loginID eq null}">
				<jsp:include page="../Include/loginnavi.jsp"></jsp:include>
			</c:when>

			<c:when test="${sessionScope.loginID ne null}">
				<jsp:include page="../Include/navi.jsp"></jsp:include>
			</c:when>
		</c:choose>
		<%@include file="../Include/SideBar.jsp" %>
		<div id="form">
			<h3>신고 상세보기</h3>
			<table>
				<tr>
					<th>신고제목</th>
					<td>${dto.subject }</td>
				</tr>
				<tr>
					<th>신고자</th>
					<td>${dto.reporter_id }</td>
				</tr>
				<tr>
					<th>신고대상자</th>
					<td>${dto.target_id}</td>
				</tr>
				<tr>
					<th>신고 카테고리</th>
					<td>${dto.category }</td>
				</tr>
				<tr>
					<th>처리상태</th>
					<td><c:if test="${dto.status == 0}">처리중</c:if> <c:if
							test="${dto.status > 0}">완료됨</c:if></td>
				</tr>
				<tr>
					<td colspan="2" id="content">${dto.content }</td>
				</tr>
			</table>
		</div>
		<div id="button">
			<button onclick="location.href='../Consumer/cReportList'">내
				신고내역 목록 가기</button>
		</div>
	</div>
</body>
<script>
function idCheck(){ 
    var uid = '<%=(String)session.getAttribute("loginID")%>';
	console.log(uid);
     if(uid=="null"){ 
        alert("로그인이 필요한 항목입니다."); 
     }
     else{
        location.replace("./Q_write.jsp");
     }
}  

function idCheck2(){ 
    var uid = '<%=(String)session.getAttribute("loginID")%>';
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