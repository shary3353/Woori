<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.adminLoginID ne null }">
	<script type="text/javascript">
		location.href = "../AdminMain";
	</script>
</c:if>
<!DOCTYPE html>
<html>

<head>
    <script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            padding-top: 160px
            
        }

        .img-container {
            text-align: center;
            margin: 0 0 12px 0;
        }

        .container-body {
            padding: 16px 16px 8px 16px;
        }

        .container {
            width: 500px;
        }

        .container-head {
            background-color: #ca4b42;
            min-height: 40px;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
            text-align: center;
            color: white;
            padding-top: 7px;
        }

        .container-body {
            background-color: rgb(240, 239, 236);
            border-bottom-left-radius: 10px;
            border-bottom-right-radius: 10px;
        }

        .row-text-top {
            font-size: small;
            font-weight: bold;
            color: rgb(196, 196, 196);
            font-weight: bold;
        }
    </style>
</head>

<body>
    <div class="container"><!--컨테이너 div-->
        <div class="img-container"><!--로고 부분-->
            <img src="${pageContext.request.contextPath}/img/main_logo.png" alt="logo" class="logo"><!--메인 이미지 변경-->
        </div><!--로고부분 닫음-->

        <div class="container-head"><!--컨테이너 헤더-->
            <b>관리자 로그인</b>
        </div>

        <div class="container-body"><!--컨테이너 바디-->
            <form action="${pageContext.request.contextPath}/AdminLogin" method="post">
                <div class="form-group row"><!--폼 밑부분-->
                    <label for="userId" class="col-sm-4 col-form-label"><b>아이디/ID</b></label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="userID" placeholder="아이디를 입력해주세요." name="userID" onkeyup="fnChkByte(this, '50')"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password" class="col-sm-4 col-form-label"><b>패스워드/PW</b></label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" id="userPW" placeholder="비밀번호를 입력해주세요." name="userPW" onkeyup="fnChkByte(this, '200')" />
                    </div>
                </div><!--폼 밑부분 닫음-->
                <br />
                <div class="form-group row"><!--로그인 버튼-->
                    <div class="col-sm-12" style="text-align: center;">
                        <button class="btn btn-danger" 
                            style="width: 200px; background-color: #ca4b42;" id="loginBtn"><b>로그인</b></button>
                    </div><!--로그인 버튼 닫음-->
                </div>
            </form><!--폼닫음-->
        </div>
        <br /><!--하단 문구-->
        <p class="row-text-top">아이디/비밀번호를 잊어버리셨나요? 관리자 이외에는 로그인 할 수 없습니다.</p>
        <p class="row-text-row" style="color: rgb(196, 196, 196); font-weight: bold;">고객센터| 1588-1588 </p>
        <p><a href="${pageContext.request.contextPath}/" style="font-size: small; color: rgb(196, 196, 196);">메인페이지로></a></p>
    </div><!--컨테이너 div닫음-->
</body>
<script type="text/javascript">
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}
	
	$('#loginBtn').click(function(){
		var inputID = $('#userID').val();
		var inputPW = $('#userPW').val();
		if(inputID == ""){
			alert('아이디를 입력하세요.');
			$('#userID').focus();
			return false;
		}else if (inputPW == ""){
			alert('비밀번호를 입력하세요.');
			$('#userPW').focus();
			return false;
		}else if(inputPW.indexOf('(') > 0){
			alert('알 수 없는 키워드가 입력되었습니다.');
			return false;
		}else if(inputID.indexOf('(') > 0){
			alert('알 수 없는 키워드가 입력되었습니다.');
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