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

       
        th {
            /* margin: 0 auto;삭제*/

            width: 150px;
            text-align: right ;
            
        }

      

        th,
        td {
            padding-top: 5;
        }

        input[type=text],
        input[type=password],
        input[type=date],
        input[type=email] {
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
        #Wrapper{
            margin-top: 150px;
        }
        table{
           margin-left: 750;
        }
    </style>
</head>
<body >
    <div id="Wrapper">
        <div style="text-align: center;">
        <img src="../img/Sel_regist.PNG" alt="logo" class="logo">
            </div>
            <br/>
        <form>
            <table>
                <th>아이디</th>
                <td>
                    <input type="text" name="PurchaserId" value="" placeholder="아이디를 입력해주세요." />
                    <input type="button" value="중복확인" onclick="idCheck()">
    
                </td>
                </tr>
                <tr>
                    <th>비밀번호 </th>
                    <td>
                        <input type="password" name="PurchaerPw" value="" placeholder="비밀번호를 입력해주세요." />
                    </td>
                </tr>
                <tr>
                    <th>비밀번호 확인</th>
                    <td>
                        <input type="password" name="PwChk" value="" placeholder="비밀번호를 입력해주세요." />
                    </td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td>
                        <input type="text" name="Purchasername" value="" placeholder="이름을 입력해주세요." />
                    </td>
                </tr>
                <tr>
                <tr>
                    <th>생년월일</th>
                    <td>
                        <form name="birth" action="값을 보낼 주소" method="post">
                            <input type='date' name='userBirthday' />
                        </form>
                    </td>
                </tr>
                <tr>
                    <th>개인전화번호</th>
                    <td>
                        <input type="text" name="phoneNumber" placeholder="개인 전화번호를 입력해주세요." />
                    </td>
                </tr>
                <tr>
                    <th>매장전화번호</th>
                    <td>
                        <input type="text" name="phoneNumber" placeholder="매장 전화번호를 입력해주세요." />
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
                <td colspan="2" align="center">
                    <input type="submit" value="회원가입완료">
    
    
    
    
            </table>
        </form>

    </div>
</body>

</html>