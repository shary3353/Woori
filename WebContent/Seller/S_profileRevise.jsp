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
		 <table>
	            <tr>
	                <td colspan="2" class="title">나의정보 수정</td>
	            </tr>
	            <tr>
	                <td class="column-name">아이디</td>
	                <td class="info">${detail.sid}
	                	<input type="hidden" name="sid" id="sid" value="${detail.sid}"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="column-name">이름</td>
	                <td class="info"><input type="text" value="${detail.name}" class="newProfile" name="name" id="name"></td>
            	</tr>
	            <tr>   
	                <td class="column-name">새로운 비밀번호</td>
	                <td>
	                	<input type="password" placeholder="새로운 비밀번호를 입력하세요" class="newPassword" id="pw" 
	                		onkeyup="chkPw(), fnChkByte(this,'200')" name="pw"/><br/>
	                </td>
	            </tr>
	            <tr>   
	                <td class="column-name">비밀번호 확인</td>
	                <td>
	                	<input type="password" placeholder="새로운 비밀번호를 확인하세요" class="newPassword" id="pw_ck"  
	                		onkeyup="chkPw()"/>
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
	                <td class="info"><input type="email" value="${detail.email}" class="newProfile" name="email" id="email" onkeyup="fnChkByte(this,'200')"></td>
	            </tr>
	            <tr>
	                <td class="column-name">전화번호</td>
	                <td class="info"><input type="text" value="${detail.phone}" class="newProfile" name="phone" id="phone" onkeyup="fnChkByte(this,'20')"></td>
	            </tr>
	            <tr>
	                <td class="column-name">사업장전화번호</td>
	                <td class="info"><input type="text" value="${detail.store_call}" class="newProfile" name="storeCall" id="storeCall" onkeyup="fnChkByte(this,'20')"></td>
	            </tr>
	            <tr>
	                <td colspan="2" style="text-align: right; border-bottom:1px solid white;"> 
	                    <button>수정사항 저장</button>
	                </td>
	            </tr>
	        </table>
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
			rbyte += 2;//한글2Byte
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


$('button').click(function(){
	var $sid = $("#sid");//selector 값을 변수에 담는다.(나중에 간편하게 사용하기 위해)
	var $name = $("#name");
	var $pw = $("#pw");
	var $pw_ck = $("#pw_ck");
	var $email = $("#email");
	var $phone = $("#phone");
	var $storeCall = $("#storeCall");
	
	var regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	var regPhone = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
	var regPw = /^[A-Za-z\d$@$!%*#?&]{3,}$/;
	
		if($name.val()==''){
			alert('이름을 입력해!');
			$id.focus();
		}else if($pw.val()==''){
			alert('비밀번호를 입력해 주세요!');
			$pw.focus();
		}else if(!regPw.test($pw.val())){
			alert('비밀번호는 영문,숫자,특수문자(@$!%*#?&)만 허용됩니다.');
			$pw.focus();
		}else if($email.val()==''){
			alert('이메일를 입력해 주세요!');
			$email.focus();
		}else if(!regEmail.test($email.val())){
			alert('이메일 주소가 유효하지 않습니다.');
			$email.focus();
		} else if($phone.val()==''){
			alert('전화번호를 입력해 주세요!');
			$phone.focus();
		} else if(!regPhone.test($phone.val())){
			alert('전화번호가 유효하지 않습니다.');
			$phone.focus();
		} else if($storeCall.val()==''){
			alert('사업장전화번호를 입력해 주세요!');
			$storeCall.focus();
		}  else if(!regPhone.test($storeCall.val())){
			alert('사업장전화번호가 유효하지 않습니다.');
			$storeCall.focus();
		} else if($pw.val() != $pw_ck.val()){
			alert('비밀번호가 일치 하지 않아요!');
			$pw_ck.focus();
		} else {
			console.log('서버로 전송');
			
			//data
			var params = {};
			params.sid = $sid.val();
			params.pw = $pw.val();
			params.name = $name.val();
			params.email = $email.val();
			params.phone = $phone.val();
			params.storeCall = $storeCall.val();
			
			$.ajax({
				type:'POST'
				,url:'./sPfpUpdate'
				,data:params
				,dataType:'JSON'
				,success:function(obj){
					console.log(obj.update);
					if(obj.update == true){
						alert('회원정보가 수정되었습니다.');
						window.location.href = "./sPfpDetail";
					} else{
						alert('잠시 후 다시 시도해 주세요.');
					}
				}
				,error:function(e){
					console.log(e);
				}
			});
			
		}
	
});

</script>
</html>