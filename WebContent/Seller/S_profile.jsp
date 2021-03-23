<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <title>seller profile</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <style>
        *{margin: 0;padding: 0;}
        #content{/*본문*/
            width: 100%;
            margin-top: 40px;
        }
        table{
            border-top: 1px solid gray;
            margin: auto;
        }
        table,th,td{
            border-bottom: 1px solid gray;
            border-collapse: collapse;
            margin-top: 5px;
            padding: 10pX;
        }
        .column-name {
            background-color: rgb(231, 231, 231);
            width: 150px;
            text-align: right;
        }
        .info {
            width: 350px;
            text-align: center;
        }
        #chk-pw{/*비밀번호확인창*/
            display: none;
        }
        button{
            padding: 4px;
        }
        .title{/*타이틀스타일*/
            border-top: 1px solid white;
            font-size: 24px;
            font-weight: 600;
            text-align: left;
        }
    </style>
</head>
<body>
	<jsp:include page="S_navi.jsp"/>
    <div id="content"><!--본문 : 판매자 회원정보-->
        <table>
            <tr>
                <td colspan="2" class="title">나의 정보</td>
            </tr>
            <tr>
                <td class="column-name">아이디</td>
                <td class="info">${detail.sid}</td>
            </tr>
            <tr>
                <td class="column-name">이름</td>
                <td class="info">${detail.name}</td>
            </tr>
            <tr>
                <td class="column-name">생년월일</td>
                <td class="info">${detail.birthday}</td>
            </tr>
            <tr>
                <td class="column-name">성별</td>
                <td class="info">${detail.gender}</td>
            </tr>
            <tr>
                <td class="column-name">이메일</td>
                <td class="info">${detail.email}</td>
            </tr>
            <tr>
                <td class="column-name">전화번호</td>
                <td class="info">${detail.phone}</td>
            </tr>
            <tr>
                <td class="column-name">사업장전화번호</td>
                <td class="info">${detail.store_call}</td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: right; border-bottom:1px solid white;"> 
                    <span id="chk-pw"><!--수정하기버튼 눌렀을시에 보임-->
                        비밀번호 확인 :
                        <input type="password" id="chkPw"/>
                        <button onclick="chkPw()")>확인</button>
                    </span>
                    <button onclick="chkPwBox()">수정하기</button>
                </td>
            </tr>
        </table>
    </div>
</body>
<script>
    function chkPwBox(){
        $('#chk-pw').css('display','inline');
    };
    function chkPw(){
       var inPw = $('#chkPw').val();
       console.log(inPw);
       
       var regPw = /^[A-Za-z\d$@$!%*#?&]{3,}$/;
       
       if(!regPw.test(inPw)){
			alert('유효 하지 않은 값입니다.');
	   }else if(inPw === "${detail.pw}"){
    	   alert("비밀번호 일치");
    	   location.href='./sPfpUpdateForm?sid=${detail.sid}';
       } else{
    	   alert("비밀번호가 다릅니다.");
       }
       
    };
</script>
</html>