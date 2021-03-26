<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>구매자회원가입</title>
			<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

			<style>
				body {
					position: relative;
				}

				tr,
				th {
					/* margin: 0 auto;삭제*/
					width: 150px;
				}

				th {
					text-align: right;
				}

				th,
				td {
					padding-top: 5;
				}

				/** input[type='button'] {
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
       

        }*/
				input[type=text],
				input[type=password],
				input[type=date],
				input[type=email],
				input[type=number] {
					padding: 5px 10px;
					/* 상하 우좌 */
					margin: 3px 0;
					/* 상하 우좌 */
					border: 1px solid #ccc;
					border-radius: 3px;
					font-family: inherit;
					/* 폰트 상속 */
					font-size: 10pt;
					font-weight: bold;
				}

				table {
					margin-left: auto;
					margin-right: auto;
				}

				#button {
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
			</style>



		</head>

		<body style="padding-top: 200;">

			<div style="text-align: center;">
				<img src="../img/Consumer_regist.PNG" alt="logo" class="logo">
			</div>

				<table>
					<tr>
						<th>이름</th>
						<td><input type="text" name="cunsumername" id="cunsumername" value="" placeholder="이름을 입력해주세요." /></td>

					</tr>
					<tr>
						<th>아이디</th>
						<td><input type="text" id="cid" name="cId" value="" placeholder="아이디를 입력해주세요." /> 
						<input type="button" id="overlay" value="중복확인" /></td>
					</tr>


					<tr>
						<th>비밀번호</th>
						<td><input type="password" id="Pw" name="pw" value="" placeholder="비밀번호를 입력해주세요."/></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" id="PwChk" name="PwChk" value="" placeholder="비밀번호를 입력해주세요." />
							<font id="check" size="1"></font>
						</td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td>
							<!-- form name="birthday" id="birth" action="값을 보낼 주소" method="post"-->
							<input type='date' id="birth" name='userBirthday' />
							

						</td>
					</tr>
					<tr>
						<th>성별</th>
						<td><input type="radio" name="gender" value="남" />남자 
							<input type="radio" name="gender" value="여" />여자</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="email" id="email" name="email" placeholder="이메일을 입력해주세요."required/></td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td><input type="text" id="phone" name="phone" placeholder="전화번호를 입력해주세요." /> </td>
					</tr>

					<tr>
						<td colspan="2" style="text-align:center;">
						<button id="button">회원가입</button>
						</td>
					</tr>
				</table>
		</body>
		<script>
			$("#Pw").change(function(){
				checkPassword($('#Pw').val());
			});
			function checkPassword(Pw){
				if(!/^[A-Za-z\d$@$!%*_#?&]{3,}$/.test(Pw)){
					alert('비밀번호는 영문,숫자,특수문자@$!%*_#?&만 허용됩니다.');
					$('#Pw').val('').focus();
					return false;
				}
				return true;
			}

		
		
		
		
		
		
			$(function () {
				$('#Pw').keyup(function () {
					$('#check').html('');
					
				});

				$('#PwChk').keyup(function () {
					
					if ($('#Pw').val() != $('#PwChk').val()) {
						$('#check').html('비밀번호 일치하지 않음');
						$('#check').attr('color', '#f82a2aa3');
						}
								else {
						$('#check').html('비밀번호 일치');
						$('#check').attr('color', '#199894b3');
					}
				});
			});
			$("#birth").change(()=>{
				
				var today = new Date();
			    var curYear = today.getFullYear();
			    var birthDay = ($('#birth').val());
			    var birthYear = parseInt(birthDay.substr(0,4));
			   
			  	 if(curYear - birthYear > 19){
			    	
			    		
			     alert('확인되었습니다.');
			   
			    }else{
			    	
			    	alert('미성년자는 가입할 수 없습니다.');
				}
			});
			 
		



				var overChk = false;//중복체크 여부

				$("#overlay").click(function () {
					var inputCid = $('#cid').val();
					var Exp =/^[a-zA-z0-9]{4,12}$/;
					var text = $('#cid').val();
					var find ="admin";
					
					if (inputCid == "") {
						alert('아이디를 입력하세요.');
					} else if(text.indexOf(find)!=-1){
						alert("admin을 포함할 수 없습니다.");
					} else if(!Exp.test(inputCid)){
						alert('id는 영문 대소문자와 숫자로만 입력해주세요.');
						$("#cid").val("");
						$("#cid").focus();
						return false;
						
					}
					else {
						
					

						$.ajax({
							type: 'get'
							, url: 'overlay'
							, data: { "cid": $("#cid").val()}
							, dataType: 'JSON'
							, success: function (data) {
								console.log(data);
								if (data.use) {
									alert('사용할 수 있는 아이디 입니다.');
									$("#cid").css({ backgroundColor: 'yellowgreen' });
									overChk = true; //사용할 수 있는 아이디로 판정 받는다면
								}
								else{
									
									console.log(data.use);
									alert('이미 사용중인 아이디 입니다.');
									$("#cid").val('');
								}
							}
							, error: function (e) {
								console.log(e);
							}
						});
					};
				});
				$('#button').click(function () {//submit

					var $id = $("#cid");
					var $pw = $("#Pw");
					var $name = $("#cunsumername");
					var $birth = $("#birth");
					var $gender = $("input[name='gender']:checked");
					var $email = $("#email");
					var $phone = $("#phone");
					
					var regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
					var regPhone = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
					
					if (overChk) {
						if ($id.val() == '') {
							alert('아이디를 입력해 주세요!');
							$id.focus();
						}else if ($pw.val() == '') {
							alert('비밀번호를 입력해 주세요!');
							$pw.focus();
						} 
						else if ($name.val() == '') {
							alert('이름을 입력해 주세요!');
							$name.focus();
						} else if ($birth.val() == '') {
							alert('생년월일을 입력해 주세요!');
							$birth.focus();
						} else if ($gender.val() == null) {
							alert('성별을 체크해 주세요!');
							$gender.focus();
						} else if ($email.val() == '') {
							alert('이메일을 입력해 주세요!');
							$email.focus();
						} else if ($email.val().indexOf('@') < 0) {
							alert('@를 입력해 주세요!');
							$email.focus();
						}else if(!regEmail.test($email.val())){
							alert('이메일 주소가 유효하지 않습니다.');
							$email.focus();
							
						}else if ($phone.val() == '') {
							alert('핸드폰 번호를 입력해 주세요!');
							$phone.focus();

						} else if(!regPhone.test($phone.val())){
							alert('핸드폰번호가 유효하지 않습니다.');
							$phone.focus(); 
						}else {
							var params = {};
							params.id = $id.val();
							params.pw = $pw.val();
							params.name = $name.val();
							params.birth = $birth.val();
							params.gender = $gender.val();
							params.email = $email.val();
							params.phone = $phone.val();
							
							$.ajax({
								type: 'POST'
								, url: 'cJoin'
								, data: params
								, dataType: 'JSON'
								, success: function (data) {
									console.log(data);
									if (data.cJoinSuccess == true) {
										alert('회원가입을 축하드립니다.');
										location.href = "C_login.jsp";
									} else {
										alert('잠시 후 다시 시도해 주세요.');
									}
								}, error: function (e) {
									console.log(e);
								}
							});
						}

					} else {
						alert('형식에 맞는지 체크 해주세요!');
						$id.focus();
					}

				});


		</script>
		</html>
					





