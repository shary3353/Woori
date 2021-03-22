<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
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

table, th, td {
	padding: 15px;
}

#content {
	width: 650px;
	height: 200px;
}

table, th {
	text-align: left;
	border: 2px solid gray;
	border-collapse: collapse;
}

#form {
	margin-left: 300px;
}

table {
	width: 1300px;
}

#button {
	text-align: center;
	width: 1298px;	
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
		<div style="">
			<div id="form">

				<h3>신고하기</h3>
					<table>
						<tr>
							<th>신고제목</th>
							<th><input id="subject" type="text" name="subject" style="width: 500px;"
								placeholder="신고제목을 입력해주세요."></th>
						</tr>
						<tr>
							<th>신고자</th>
							<td><input id="rId" type="text" name="userName"
								value="${sessionScope.loginID}" readonly/></td>
						
						</tr>
						<tr>
							<th>신고대상자</th>
							<th>
							<c:set var="targetId" value="<%=request.getParameter(\"sid\")%>"></c:set>
							<c:if test="${targetId == null }">
							<input id="tId" type="text" name="sellerId"
								value="">
								</c:if>
								<c:if test="${targetId != null}">
									<input id="tId" type="text" name="sellerId"
								value="<%=request.getParameter("sid")%>" readonly>
								</c:if>
								</th>
								
						</tr>
						<tr>
							<th>신고 카테고리</th>
							<th>카테고리 선택 <select name="categorie" id="cate">
									<option value="0" selected="selected">신고사유/카테고리</option>
									<option value="100">상품관련</option>
									<option value="200">판매자관련</option>
									<option value="300">기타</option>
							</select>
							</th>
						</tr>
						<tr>
							<th>신고내용</th>
							<th><textarea name="content" id="content" cols="30"
									rows="10" placeholder="신고내용을 입력해주세요."></textarea></th>
						</tr>
						<tr>

						</tr>
					</table>
					<div id="button">
						<button id="btn" style="width: 80px; padding:3px 7px;">신고하기</button>
					</div>
			</div>
		</div>
	</div>
</body>
<script>
	$("#btn").click(function(){
		var $subject = $("#subject").val();
		var $rId = $("#rId").val();
		var $tId = $("#tId").val();
		var $category = $("#cate").val();
		var $content = $("#content").val();
		
		if($subject == ''){
			alert('제목을 입력해주세요');
			$subject.focus();
		}else if($rId == ''){
			alert('로그인 하세요');
			$cId.focus();
		}else if($tId == ''){
			alert('신고자를 확인해주세요')
			$sId.focus();
		}else if($category == 0){
			alert('카테고리 선택해세주세요')
			$category.focus();
		}else if($content == ''){
			alert('내용을 입력해 주세요')
			$content.focus();
		}else{
			console.log('서버로 전송');
			var pa = {};
			pa.subject = $subject;
			pa.rId = $rId;
			pa.tId = $tId;
			pa.category = $category;
			pa.content = $content;
			$.ajax({
				type : 'post'
				,url : 'Report'
				,data : pa
				,dataType : 'json'
				,success : function(data){
					console.log(data);
					if(data.success == true){
						console.log(data.r_idx);
						alert('신고등록을 완료하였습니다.');
					location.href="ReportDetail?r_idx="+ data.r_idx;
					}else{
						alert('잠시 후 다시 시도해 주세요');
					}
				}, error : function(e){
					
				}
			})
		}
	})
	
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