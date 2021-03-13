<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            position: relative;

        }

        .tabs {
            /*탭 전체 스타일*/
            margin-top: 50px;
            padding-bottom: 40px;
            background-color: rgb(240, 239, 236);


            margin: 0 auto;
        }

        .tab_item {
            /*탭 스타일*/
            width: calc(100%/2);
            height: 50px;
            border-bottom: 3px solid rgb(240, 239, 236);

            background-color: rgb(240, 239, 236);
            line-height: 50px;
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

       

        #Pur_tab:checked~#tab_content,
        #sell_tab:checked~#tab_content {
            /* 클릭되었을 때 보여질 컨텐츠*/
            display: block;
        }

        /* 선택된 탭 스타일 */

        .img-container {
            text-align: center;
            margin: 24px 0 12px 0;



        }

        .container-body {
            padding: 16px;
            border-radius: 10px;


        }

        .container {
            position: absolute;
            height: 300px;
            left: 40%;
            top: 50%;
            margin-left: 100px;
            margin-top: 150px;


        }

        input[type=text],
        input[type=password] {
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
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
            <input id="Pur_tab" type="radio" name="tab_item" checked>
            <lablel class="tab_item" for="Pur_tab">구매자 로그인</lablel>
            <input id="sell_tab" type="radio" name="tab_item">
            <label class="tab_item" for="sell_tab">판매자 로그인</label>
        </div>
        <!--탭부분 닫음-->

        <div class="container-body" style="background-color:  rgb(240, 239, 236);">
            <!--컨테이너 바디-->
            <div class="tab_content" id="Pur_tab">
                <!--구매자 로그인 탭열기-->
                <form action="#" method="">
                    <div>
                        <table>
                            <tr>
                                <th>

                                    <label><b>아이디/ID</b></label>
                                </th>
                                <td>

                                    <input type="text" id="userId" placeholder="아이디를 입력해주세요." name="name"
                                        style="width: 300px;" />
                                </td>

                            </tr>
                            <tr>
                                <th>
                                    <label><b>패스워드/PW</b></label>

                                </th>
                                <td>
                                    <input type="password" placeholder="비밀번호를 입력해주세요" name="paswword"
                                        style="width: 300px;">

                                </td>
                            </tr>
                        </table>
                    </div>
                    <br />


                    <div style="text-align: center;">
                        <button type="submit" class="btn"
                            style="border-radius: 10px; background-color: rgb(81, 167, 201);"><b>로그인</b></button>
                    </div>
                    <div style="text-align: center;">
                        <button type="submit" class="btn"
                            style="border-radius: 10px; border:2px solid rgb(81, 167, 201); background-color:rgb(240, 239, 236); color:rgb(81, 167, 201);"><b>회원가입</b></button>
                    </div>
            </div>
            <!--구매자 로그인 탭 닫음-->

        </div>
        <!--컨테이너 바디 닫음-->
        </form>
        <br /><!--하단 문구-->
        <p class="row-text-top">아이디/비밀번호를 잊어버리셨나요? 관리자 이외에는 로그인 할 수 없습니다.</p>
        <p class="row-text-row" style="color: rgb(196, 196, 196); font-weight: bold;">고객센터| 1588-1588 </p>
        <p><a href="#" style="font-size: small; color: rgb(196, 196, 196);">메인페이지로></a></p>
    </div>
    <!--컨테이너 닫음-->
</body>

</html>