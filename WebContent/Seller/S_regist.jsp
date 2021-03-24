<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<meta charset="UTF-8">
<title>판매자회원가입</title>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<style>
body {
	position: relative;
}

tr, th {
	width: 150px;
}

th {
	/* margin: 0 auto;삭제*/
	text-align: right;
}

td, th {
	padding-top: 5;
}

input[type=text], input[type=password], input[type=date], input[type=email]
	{
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

#button {
text-align:center;
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

table{
	margin-left: auto;
	margin-right: auto;
}


</style>
</head>
<body style="padding-top: 200;">


	<div style=" text-align:center;">
		<img src="../img/Sel_regist.PNG" alt="logo" class="logo">
	</div>

	
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" id="sid" name="SellerId" value=""
					placeholder="***-**-*****" /> <input type="button" id="overlay"
					value="중복확인"></td>
			</tr>

			<tr>
				<th>비밀번호</th>
				<td><input type="password" id="Pw" name="Pw" value=""
					placeholder="비밀번호를 입력해주세요." /></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" id="PwChk" name="PwChk" value=""
					placeholder="비밀번호를 입력해주세요." /> <font id="check" size="1"></font></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" id="sellername" name="sellername" value=""
					placeholder="이름을 입력해주세요." /></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type='date' id="birth" name='userBirthday' /></td>
			</tr>
			<tr>
				<th>개인전화번호</th>
				<td><input type="text" id="phone" name="phoneNumber"
					placeholder="개인 전화번호를 입력해주세요." /></td>
			</tr>
			<tr>
				<th>매장전화번호</th>
				<td><input type="text" id="store_call" name="phoneNumber"
					placeholder="매장 전화번호를 입력해주세요." /></td>
			</tr>

			<tr>
				<th>성별</th>
				<td><input type="radio" id="gender" name="gender" value="남" />남자
				 <input type="radio" name="gender" value="여" />여자</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" id="email" name="email" placeholder="이메일을 입력해주세요." />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;"><input type="button" id="button" value="회원가입완료">
				</td>
			</tr>




		</table>



</body>
<script>
$("#Pw").change(function(){
	checkPassword($('#Pw').val());
});
function checkPassword(Pw){
	if(!/^(?=.*[a-zA-Z])(?=.*[!@#$%^])(?=.[0-9]).{3,10}$/.test(Pw)){
		alert('숫자+영문자+특수문자 조합으로 3자리 이상 사용해야 합니다.()미포함');
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
						$('#check').html('비밀번호 일치하지 않음<br><br>');
						$('#check').attr('color', '#f82a2aa3');
					} else {
						$('#check').html('비밀번호 일치<br><br>');
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
					var inputCid = $('#sid').val();
					var Exp =/^[a-zA-Z0-9]{4,12}$/;
					var text = $('#sid').val();
					var find ="admin";
					var sidArr = new Array();
					if (inputCid == "") {
						alert('아이디를 입력하세요.');
					} else if(text.indexOf(find)!=-1){
						alert("admin을 포함할 수 없습니다.");
					} else if(sidArr.length < 12){
						alert("사업자 등록번호 형식에 맞게 입력해주세요.");
						return false;
					}
					
					else if(Exp.test(sid.value)){
						alert('id는 사업자등록번호로만 입력할 수 있습니다');
					}
					else {

						$.ajax({
							type: 'get'
							, url: 'soverlay'
							, data: { "sid": $("#sid").val() }
							, dataType: 'JSON'
							, success: function (data) {
								console.log(data);
								if (data.use) {
									alert('사용할 수 있는 아이디 입니다.');
									$("#sid").css({ backgroundColor: 'yellowgreen' });
									overChk = true; //사용할 수 있는 아이디로 판정 받는다면
								} else {
									console.log(data.use);
									alert('이미 사용중인 아이디 입니다.');
									$("#sid").val('');
								}
							}
							, error: function (e) {
								console.log(e);
							}
						});
					};
				});
				$('#button').click(function () {//submit

					var $id = $("#sid");
					var $pw = $("#Pw");
					var $name = $("#sellername");
					var $birth = $("#birth");
					var $phone = $("#phone");
					var $store_call = $("#store_call");
					var $gender = $("input[name='gender']:checked");
					var $email = $("#email");

					if (overChk) {
						if ($id.val() == '') {
							alert('아이디를 입력해 주세요!');
							$id.focus();
						} else if ($pw.val() == '') {
							alert('비밀번호를 입력해 주세요!');
							$pw.focus();
						} else if ($name.val() == '') {
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
						} else if ($phone.val() == '') {
							alert('핸드폰 번호를 입력해 주세요!');
							$phone.focus();
						} else if($store_call.val() == ''){
							 alert('사업장 번호를 입력해 주세요!');
							 $store_call.focus();
							
						

						} else {
							var params = {};
							params.id = $id.val();
							params.pw = $pw.val();
							params.name = $name.val();
							params.birth = $birth.val();
							params.gender = $gender.val();
							params.email = $email.val();
							params.phone = $phone.val();
							params.store_call =$store_call.val();
							$.ajax({
								type: 'POST'
								, url: 'sJoin'
								, data: params
								, dataType: 'JSON'
								, success: function (data) {
									console.log(data);
									if (data.sJoinSuccess == true) {
										alert('회원가입을 축하드립니다.');
										location.href = "${pageContext.request.contextPath}/Consumer/C_login.jsp";
									} else {
										alert('잠시 후 다시 시도해 주세요.');
									}
								}, error: function (e) {
									console.log(e);
								}
							});
						}

					} else {
						alert('중복 체크 해주세요!');
						$id.focus();
					}

				});


		</script>




</html>