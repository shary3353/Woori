<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
    <meta charset="UTF-8">
    <title>구매자회원가입</title>
    <script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
    
 	 <style>
        body{
            position: relative;
        }
    
        tr,
        th {
            /* margin: 0 auto;삭제*/

            width: 150px;
        }

        th{
            text-align: right;
        }


        th,
        td {
            padding-top: 5;
        }

        input[type='submit'] {
            height: 30;
            background-color: rgb(94, 198, 240);
            color: white;
            font-weight: bold;
            padding: 3px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 50%;
            border-radius: 10px;
       

        }
    input[type=text], input[type=password],input[type=date],input[type=email],input[type=number] {
    padding: 5px 10px; /* 상하 우좌 */
    margin: 3px 0; /* 상하 우좌 */
    border: 1px solid #ccc;
    border-radius: 3px;
    font-family: inherit; /* 폰트 상속 */
    font-size:10pt;
    font-weight: bold;
    }

    table{
        margin-left: auto;
        margin-right: auto;
    }
  
    </style>

	
  
</head>

<body style="padding-top: 200;">

    <div style="text-align: center;">
        <img src="../img/Consumer_regist.PNG" alt="logo" class="logo">
    </div>

    <form action="C_login.jsp">
        <table>
            <tr>
                <th>이름</th>
                <td>
                    <input type="text" name="consumername" value="" placeholder="이름을 입력해주세요." />
                </td>

            </tr>
            <tr>
                <th>아이디</th>
                <td>
                    <input type="text" name="cId" value="" placeholder="아이디를 입력해주세요." />
                    <input type="button" id="overlay" value="중복확인" onclick="idCheck()" />
                </td>
            </tr>


            <tr>
                <th>비밀번호 </th>
                <td>
                    <input type="password" name="pw"  value="" placeholder="비밀번호를 입력해주세요." onchange="check_pw" />
                </td>
            </tr>
            <tr>
                <th>비밀번호 확인</th>
                <td>
                    <input type="password" name="PwChk" value="" placeholder="비밀번호를 입력해주세요." onchange="check_pw"/>
                    <font id="chkNotice" size="5"></font>
                </td>
            </tr>
            <tr>
                <th>생년월일</th>
                <td>
                    <form name="birthday" action="값을 보낼 주소" method="post">
                        <input type='date' name='userBirthday' />
          </form>
                </td>
            </tr>
            <tr>
                <th>성별</th>
                <td>
                    <input type="radio" name="gender" value="male" />남자
                    <input type="radio" name="gender" value="female" />여자
                </td>
            </tr>
            <tr>
                <th>이메일</th>
                <td>
                    <input type="email" name="email" placeholder="이메일을 입력해주세요." />
                </td>
            </tr>
            <tr>
                <th>전화번호</th>
                <td>
                    <input type="number" name="phone" placeholder="전화번호를 입력해주세요." />
                </td>
            </tr>
            <br />
        
           
           
           <td colspan="2" align="center">
                <input type="submit" style="border: rgb(94, 198, 240);  " value="회원가입완료">
       




        </table>
    </form>
</body>
 <script>
	
 	function check_pw(){//비밀번호 일치
		 var pw = document.getElementById('pw').value;
		 
		 if(document.getElementById('pw').value!=''&&document.getElementById('PwChk').value!=''){
			 if(document.getElementById('pw').value == document.getElementById('PwChk').value){
				 document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
				 document.getElemnetById('check').style.color='green';
			 }else{
				 document.getElementById('check'.innerHTML)='비밀번호가 일치하지 않습니다.';
				 document.getElemnetById('check').style.color='red';
			 }
		 };
	 };
	
	 var overChk = false;//중복체크 여부
		
		$("#overlay").click(function(){
			
			$.ajax({
				type:'get'
				,url:'overlay'
				,data:{"id":$("#CId").val()}
				,dataType:'JSON'
				,success:function(obj){
					console.log(obj);
					if(obj.use){
						alert('사용할 수 있는 아이디 입니다.');
						
						$("#CId").css({backgroundColor:'yellowgreen'});
						overChk=true; //사용할 수 있는 아이디로 판정 받는다면
					}else{
						alert('이미 사용중인 아이디 입니다.');
						$("#CId").val('');
					}
						
				}
				,error:function(e){
					console.log(e);
				}
				
			});
		});
		
		$('button').click(function(){
			
			var $id = $("#CId");
			var $pw = $("#Pw");
			var $name = $("#consumername");
			var $age = $("#age");
			var $gender = $("input[name='gender']:checked");
			var $email = $("#email");
			
			if(overChk){
				if($id.val()==''){
					alert('아이디를 입력해 주세요!');
					$id.focus();
				}else if($pw.val()==''){
					alert('비밀번호를 입력해 주세요!');
					$pw.focus();
				}else if($name.val()==''){
					alert('이름을 입력해 주세요!');
					$name.focus();
				}else if($age.val()==''){
					alert('나이를 입력해 주세요!');
					$age.focus();
				}else if($gender.val()==null){ 
					alert('성별을 체크해 주세요!');
					$gender.focus();
				}else if($email.val()==''){
					alert('이메일을 입력해 주세요!');
					$email.focus();
				}else{
					var params = {};
					params.id = $id.val();
					params.pw = $pw.val();
					params.name = $name.val();
					params.age = $age.val();
					params.gender = $gender.val();
					params.email = $email.val();
					
					$ajax({
						type:'POST'
						,url:'join'
						,data:params
						,dataType:'JSON'
						,success:function(data){
							console.log(data);
							if(data.success == true){
								alert('회원가입을 축하드립니다.');
								location.href="C_login.jsp";
							}else{
								alert('잠시 후 다시 시도해 주세요.');
							}
						},error:function(e){
							console.log(e);
						}
					});
				}
				
			}else{
				alert('중복 체크 해주세요!');
				$id.focus();
			}
			
			
		});
	
    
    </script>
   
  




</html>

