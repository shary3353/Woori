<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            text-align: center;
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
        .newPassword{
            width: 300px;
            height: 35px;
            border: 0px;
            border-bottom: 1px solid black;
            line-height: 5px;
            padding-left: 20px;
            padding-top: 10px;
        }
        
        .newProfile{
        	width: 300px;
            height: 35px;
            border: 0px;
            border-bottom: 1px solid black;
            line-height: 5px;
            padding-left: 20px;
            padding-top: 10px
        }
    </style>
</head>
<body>
    <jsp:include page="S_navi.jsp"/>
    
    <div id="content"><!--본문 : 판매자 회원정보수정폼-->
    	<form action="sPfpUpdate" method="post">
	        <table>
	            <tr>
	                <td colspan="2" class="title">나의정보 수정</td>
	            </tr>
	            <tr>
	                <td class="column-name">아이디</td>
	                <td class="info">${detail.sid}
	                	<input type="hidden" name="sid" value="${detail.sid}"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="column-name">이름</td>
	                <td class="info"><input type="text" value="${detail.name}" class="newProfile" name="name"></td>
            	</tr>
	            <tr>   
	                <td class="column-name">새로운 비밀번호</td>
	                <td>
	                	<input type="password" placeholder="새로운 비밀번호를 입력하세요" class="newPassword" id="pw" 
	                		onkeyup="chkPw()">
	                </td>
	            </tr>
	            <tr>   
	                <td class="column-name">비밀번호 확인</td>
	                <td>
	                	<input type="password" placeholder="새로운 비밀번호를 확인하세요" class="newPassword" id="pw_ck"  
	                		onkeyup="chkPw()" name="pw">
	                	<br/>
	                	<span id="chkPw"></span>
	                </td>
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
	                <td class="info"><input type="text" value="${detail.email}" class="newProfile" name="email"></td>
	            </tr>
	            <tr>
	                <td class="column-name">전화번호</td>
	                <td class="info"><input type="text" value="${detail.phone}" class="newProfile" name="phone"></td>
	            </tr>
	            <tr>
	                <td class="column-name">사업장전화번호</td>
	                <td class="info"><input type="text" value="${detail.store_call}" class="newProfile" name="storeCall"></td>
	            </tr>
	            <tr>
	                <td colspan="2" style="text-align: right; border-bottom:1px solid white;"> 
	                    <button>수정사항 저장</button>
	                </td>
	            </tr>
	        </table>
        </form>
    </div>
</body>
<script>
function chkPw() { //비밀번호 일치여부 확인
    var pw = document.getElementById('pw').value; 
    var pw_ck = document.getElementById('pw_ck').value; 
    if (pw!=pw_ck) { 
    document.getElementById('chkPw').innerHTML = "비밀번호가 다릅니다."; 
    } else { 
        document.getElementById('chkPw').innerHTML = "비밀번호가 일치합니다.";
    }
}
</script>
</html>