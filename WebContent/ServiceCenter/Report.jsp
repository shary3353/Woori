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
	font-size:20px;
}

#content {
	width: 100%;
	height: 200px;
	font-size: 20px;
	resize:none;
}

table, th , td	{
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
#subject , #rId , #tId , #cate {
	height:20px;
	font-size:15px;
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
		<div style="">
			<div id="form">

				<h3>신고하기</h3>
					<table>
						<tr>
							<th>신고제목</th>
							<th><input id="subject" type="text" name="subject" style="width: 500px;"
								placeholder="신고제목을 입력해주세요." onKeyUp="fnChkByte(this,'50')"></th>
						</tr>
						<tr>
							<th>신고자</th>
							<td><input id="rId" type="text" name="userName" value="${sessionScope.cLoginID}" readonly/></td>
						
						</tr>
						<tr>
							<th>신고대상자</th>
							<th>
							<c:set var="targetId" value="<%=request.getParameter(\"sid\")%>"></c:set>
							<c:if test="${targetId == null }">
								<input id="tId" type="text" name="sellerId" value="" onKeyUp="fnChkByte(this,'50')"></c:if>
								<c:if test="${targetId != null}">
								<input id="tId" type="text" name="sellerId" value="<%=request.getParameter("sid")%>" readonly></c:if>
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
							<td><textarea name="content" id="content" cols="30" rows="10" placeholder="신고내용을 입력해주세요." onKeyUp="fnChkByte(this,'1000')"></textarea>
									<!-- <div id="content_cnt">(0 / 1000)</div> -->
							</td>
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
	 
	/*  $(document).ready(function(){
		 $('#content').on('keyup',function(){
			 $('#content_cnt').html("("+$(this).val().length+" / 1000)");
			 	
			 if($(this).val().length > 1000){
				 $(this).val($(this).val().substring(0,1000));
			 	$('#content_cnt').html("(1000 / 1000)");
			 }
		 });
	 }); */
	 
	    function fnChkByte(obj, maxByte){//글자 bytes 제한
	        var str = obj.value;
	        var str_len = str.length;

	        var rbyte = 0;
	        var rlen = 0;
	        var one_char = "";
	        var str2 = "";

	        for(var i=0; i<str_len; i++){
	            one_char = str.charAt(i);
	            if(escape(one_char).length > 4){
	             rbyte += 3;//한글3Byte
	          } else {
	              rbyte++;//영문 등 나머지 1Byte
	            }
	          if(rbyte <= maxByte){
	                rlen = i+1;//return할 문자열 갯수
	            }
	         }
	         if(rbyte > maxByte){
	            // alert("한글 "+(maxByte/2)+"자 / 영문 "+maxByte+"자를 초과 입력할 수 없습니다.");
	            alert("최대 " + maxByte + "byte를 초과할 수 없습니다.")
	            str2 = str.substr(0,rlen);//문자열 자르기
	            obj.value = str2;
	            fnChkByte(obj, maxByte);
	         }else{
	            //document.getElementById('byteInfo').innerText = rbyte;
	         }
	    }
	
	 </script>
</html>