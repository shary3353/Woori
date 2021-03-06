<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:if test="${sessionScope.cLoginID ne null }">
	<script type="text/javascript">
			location.href = "./C_main";
		</script>
</c:if>
<c:if test="${sessionScope.sLoginID ne null }">
	<script type="text/javascript">
			location.href = "../Seller/sItemList";
		</script>
</c:if>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }
        .tabs {
            /*탭 전체 스타일*/
            margin-top: 50px;
            padding-bottom: 40px;
            margin: 0 auto;
        }
        .tab_item {
            /*탭 스타일*/
            width: calc(100%/2);
            height: 50px;
            background-color: rgb(240, 239, 236);
            font-size: 15px;
            text-align: center;
            color: #333333;
            display: block;
            float: left;
            text-align: center;
            font-weight: bold;
            transition: all 0.2s ease;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
        }
        .tab_item:hover {
            opacity: 0.75;
        }
        input[name="tab_item"] {
            /*라디오 버튼 UI삭제*/
            display: none;
        }
        .tabs input:checked+.tab_item {
            /*선택 되어있는 버튼 스타일*/
            background-color: #333333;
            color: #fff;
        }
        
        /*탭*/
        #c_login_tab{
        	display: block;
        }
        #s_login_tab{
     		display: none;
        }
        /* 선택된 탭 스타일 */
        .img-container {
            text-align: center;
            margin: 24px 0 12px 0;
        }

        .container-body {
            padding: 16px;
        }

        .container {
            position: absolute;
            left: 50%;
            top: 50%;
			transform: translate(-50%, -50%);
        }

        input[type=text],
        input[type=password] {
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
        }
		input:focus{
			background-color:  rgb(226, 226, 226);
		}
        button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 50%;

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
    <div class="container">
        <!--컨테이너 div-->
        <div class="img-container">
            <!--로고 부분-->
            <img src="../img/main_logo.png" alt="logo" class="logo">
            <!--메인 이미지-->
        </div>

        <div class="tabs">
            <!--tab 부분-->
            <!--수정 style="background-color: #b44e46; min-height: 30px; border-top-left-radius: 4px; border-top-right-radius: 4px; text-align:center; color: white; padding-top:5px "-->
            <button class="tab_item" id="c_btn" onclick="lookCLogin()" style="background-color: gray;">구매자 로그인</button>
            <button class="tab_item" id="s_btn" onclick="lookSLogin()">판매자 로그인</button>
        </div>
        <!--탭부분 닫음-->


        <!--컨테이너 바디-->
        <div class="container-body" style="background-color:  rgb(240, 239, 236);" id="c_login_tab" >
                <!--구매자 로그인 탭열기-->
        		<div class="tab_content" >
      	<form name="cform" action="../Consumer/cLogin" method="POST">
                    <div>
                        <table>
                            <tr>
                                <th>
                                    <label><b>구매자아이디/ID</b></label>
                                </th>
                                <td>

                                    <input type="text" id="cid" placeholder="아이디를 입력해주세요." name="cid"
                                        style="width: 300px;" onkeyup="fnChkByte(this, '50')"/>
                                </td>

                            </tr>
                            <tr>
                                <th>
                                    <label><b>패스워드/PW</b></label>
                                </th>
                                <td>
                                    <input type="password" id="pw" placeholder="비밀번호를 입력해주세요" name="pw"
                                        style="width: 300px;" onkeyup="fnChkByte(this, '200')">
                                </td>
                            </tr>
                        </table>
                    </div><br />
                    <div style="text-align: center;">
                        <button type="submit" class="btn" value="login"
                            style="border-radius: 10px; background-color: rgb(81, 167, 201);"><b>로그인</b></button>
                    </div>
       </form>
                    <div style="text-align: center;">
                        <button type="button" class="btn" onclick="location.href='C_regist.jsp'" value="회원가입"
                            style="border-radius: 10px; border:2px solid rgb(81, 167, 201); background-color:rgb(240, 239, 236); color:rgb(81, 167, 201);"><b>회원가입</b></button>
                    </div>
            	</div>
        </div>
        <!--컨테이너 바디 닫음-->
        
         <!--컨테이너 바디-->
         <div class="container-body" style="background-color:  rgb(240, 239, 236);" id="s_login_tab" >
                <!--판매자 로그인 탭열기-->
        		<div class="tab_content">
      <form action="../Seller/sLogin" method="POST">
                    <div>
                        <table>
                            <tr>
                                <th>
                                    <label><b>판매자아이디/ID</b></label>
                                </th>
                                <td>
                                    <input type="text" id="sid" placeholder="아이디를 입력해주세요." name="sid"
                                        style="width: 300px;" onkeyup="fnChkByte(this, '50')"/>
                                </td>

                            </tr>
                            <tr>
                                <th>
                                    <label><b>패스워드/PW</b></label>
                                </th>
                                <td>
                                    <input type="password" id="pw" placeholder="비밀번호를 입력해주세요" name="pw"
                                        style="width: 300px;" onkeyup="fnChkByte(this, '200')">
                                </td>
                            </tr>
                        </table>
                    </div><br />
                    <div style="text-align: center;">
                        <button type="submit" class="btn" value="login"
                            style="border-radius: 10px; background-color: rgb(81, 167, 201);"><b>로그인</b></button>
                    </div>
       </form>
                    <div style="text-align: center;">
                        <button type="button" class="btn" onclick="location.href='../Seller/S_regist.jsp'" value="회원가입"
                            style="border-radius: 10px; border:2px solid rgb(81, 167, 201); background-color:rgb(240, 239, 236); color:rgb(81, 167, 201);"><b>회원가입</b></button>
                    </div>
            	</div>
        </div>
        <!--컨테이너 바디 닫음-->
        
        <br /><!--하단 문구-->
        <p class="row-text-top">아이디/비밀번호를 잊어버리셨나요? 고객센터로 문의바랍니다.</p>
        <p class="row-text-row" style="color: rgb(196, 196, 196); font-weight: bold;">고객센터| 1588-1588 </p>
        <p><a href="../Consumer/C_main" style="font-size: small; color: rgb(196, 196, 196);">메인페이지로></a></p>
        <p><a href="../Admin/admin_Login.jsp" style="font-size: small; color: rgb(196, 196, 196);">관리자로 로그인하기></a></p>
    </div>
    <!--컨테이너 닫음-->
</body>
<script>
/*
	var loginID = "${sessionScope.loginID}";
	if(loginID !=""){
	var content ="안녕하세요"+loginId+"님,<a href='logout'>[로그아웃]</a>";
	document.getElementById("login").innerHTML = content;
	}else{
		location.href="#";
	}
*/
    function lookCLogin(){
        console.log("구매자로그인");
        document.getElementById('s_login_tab').style.display = 'none';
        document.getElementById('c_login_tab').style.display = 'block';
        document.getElementById('c_btn').style.backgroundColor = 'gray';
        document.getElementById('s_btn').style.backgroundColor = 'rgb(240, 239, 236)';
    }
    function lookSLogin(){
        console.log("판매자로그인");
        document.getElementById('s_login_tab').style.display = 'block';
        document.getElementById('c_login_tab').style.display = 'none';
        document.getElementById('c_btn').style.backgroundColor = 'rgb(240, 239, 236)';
        document.getElementById('s_btn').style.backgroundColor = 'gray';
    }
    
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}
	
	
	
	
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