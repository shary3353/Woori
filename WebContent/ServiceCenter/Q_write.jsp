<%@page import="com.sun.jdi.Location"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>q.write</title>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
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

#content {
	width: 1000px;
	height: 200px;
	resize: none;
}

#form table, tr, td {
	padding: 15px 10px;
	border: 2px solid gray;
	border-collapse: collapse;
}

#form table td {
	
}

#form table th {
	
}

#setpass {
	text-align: left;
	font-size: 12px;
}

.column1 {
	text-align: center;
	padding: 10px 5px;
}

.column2 {
	padding: 20px 10px;
}

#form {
	margin-left: 300px;
}

table {
	width: 1300px;
}

#cId, #subject, #category, #productName, .sId, .productName {
	height: 30px;
	font-size: 15px;
}

#content {
	font-size: 20px;
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
				<div class="Service">
					<a href="qList">고객센터</a>
				</div>
				<div class="One">
					<a href="#" onclick="idCheck();">1:1 문의하기</a>
				 
				</div>
				<div class="Question">
					<a href="Question.jsp">자주묻는 질문</a>
				</div>
				<div class="Report">
					<a href="#" onclick="idCheck2();">신고하기</a>
				</div>
			</div>
		</div>
		<div></div>
		<div id="form">
			<h3>1:1문의 작성</h3>
			<table id="table">
				<tr>
					<td class="column1">작성자</td>
					<td class="column2"><input type="text" id="cId"
						name="consumerId" value="${sessionScope.loginID}" readonly/>
                      </td>
					<td>카테고리 <select id="category" name="category" value="문의/카테고리">
							<option value="100" selected="selected">상품관련</option>
							<option value="200">예약관련</option>
							<option value="300">매장관련</option>
							<option value="400">교환/반품/반납</option>
					</select>
					</td>
					<td class="column1">문의상품</td>
					<td class="culumn1" name="productName">
					<c:set var="p_name"
							value="<%=request.getParameter(\"p_name\")%>">
					</c:set> 
					<c:if test="${p_name != null}">
							<input class="productName" name="p_name" maxlength='50'
								value="<%=request.getParameter("p_name")%>" readonly>
						</c:if> 
						<c:if test="${p_name == null }">
							<input class="productName" type="text" name="p_name">
						</c:if>
						</td>
				</tr>
				<tr>
					<td class="column1">제목</td>
					<td colspan="2"><input type="text" id="subject"
						placeholder="제목을 입력해주세요" style="width: 380px" maxlength='50'
						name="subject"></td>
					<td class="column1">판매자</td>

					<td class="culumn1" name="sellerId"><c:set var="sId"
							value="<%=request.getParameter(\"sId\")%>"></c:set> <c:if
							test="${sId != null }">
							<input type="text" class="sId"
								value="<%=request.getParameter("sId")%>" readonly>
						</c:if> <c:if test="${sId == null}">
							<input type=text class="sId" name="sId">
						</c:if></td>
				</tr>
				<tr>
					<td class="column1">내용</td>
					<td colspan="4"><textarea id="content" name="content"
							id="content" cols="30" rows="10"></textarea></td>
				</tr>
				<tr>
					<td class="column1">확인 비빌번호</td>
					<td colspan="4" id="setpass"><input type="password" id="pass"
						placeholder="숫자4자리 입력" style="text-align: left;" maxlength='4'
						minlengt='4' name="passWord" />
						<button id="submit" style="display: inline;">저장</button>
						<div style="display: inline; color: gray">비밀번호는 작성하신 글 확인시
							사용됩니다.</div></td>
				</tr>
			</table>
		</div>
	</div>
</body>
<script>
	$("#submit").click(function() {
		var $cid = $("#cId");
		var $category = $("#category");
		var $product = $(".productName");
		var $subject = $("#subject");
		var $sid = $(".sId");
		var $content = $("#content");
		var $pass = $("#pass")

		if ($product.val() == '') {
			alert('상품이름을 입력해주세요');
			$product.focus();
		} else if ($subject.val() == '') {
			alert('제목을 입력해주세요');
			$subject.focus();
		} else if ($sid.val() == '') {
			alert('판매자를 입력해주세요');
			$sid.focus();
		} else if ($content.val() == '') {
			alert('내용을 입력해주세요');
			$content.focus();
		} else if ($pass.val() == '') {
			alert('비밀번호를 입력해주세요');
			$pass.focus();
		} else if ($pass.val().length != 4) {
			alert('비밀번호를 입력해주세요');
			$pass.focus();
		} else if(isNaN($pass.val()) == true){
			alert('비밀번호를 숫자로 입력하세요');
			$pass.focus();
		}else{		
			console.log('서버로전송');
			var pa = {};
			pa.cid = $cid.val();
			pa.category = $category.val();
			pa.product = $product.val();
			pa.subject = $subject.val();
			pa.sid = $sid.val();
			pa.content = $content.val();
			pa.pass = $pass.val();
			$.ajax({
				type : 'post',
				url : 'qWrite',
				data : pa,
				dataType : 'json',
				success : function(data) {
					console.log(data)
					console.log(data.q_idx)
					if (data.success == true) {
						alert('문의등록에  성공하였습니다.');
						location.href = "qList";
					} else {
						alert('판매자 ID를 확인해주세요');
					}
				},
				error : function(e) {

				}
			});
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