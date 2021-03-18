<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                        <input type="text" class="form-control" id="userId" placeholder="아이디를 입력해주세요." name="userID">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password" class="col-sm-4 col-form-label"><b>패스워드/PW</b></label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" id="password" placeholder="비밀번호를 입력해주세요." name="userPW">
                    </div>
                </div><!--폼 밑부분 닫음-->
                <br />
                <div class="form-group row"><!--로그인 버튼-->
                    <div class="col-sm-12" style="text-align: center;">
                  		<div id="confirmID" style="margin-bottom:30px;">관리자 아이디는 'admin'이 포함되어야 합니다.</div>
                        <button type="submit" class="btn btn-danger" 
                            style="width: 200px; background-color: #ca4b42;"><b>로그인</b></button>
                    </div><!--로그인 버튼 닫음-->
                </div>
            </form><!--폼닫음-->
        </div>
        <br /><!--하단 문구-->
        <p class="row-text-top">아이디/비밀번호를 잊어버리셨나요? 관리자 이외에는 로그인 할 수 없습니다.</p>
        <p class="row-text-row" style="color: rgb(196, 196, 196); font-weight: bold;">고객센터| 1588-1588 </p>
        <p><a href="#" style="font-size: small; color: rgb(196, 196, 196);">메인페이지로></a></p>
    </div><!--컨테이너 div닫음-->
</body>
<script type="text/javascript">
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}
	$('#userId').keydown(function(){
		if($(this).val().includes('admin')){
			$('#confirmID').html("'admin'이 포함되어있습니다.");
		}else{
			$('#confirmID').html("관리자 아이디는 'admin'이 포함되어야 합니다.");
		}
	});
</script>
</html>