<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8" />
        <title>회원정보 상세보기</title>

        <style>
            #wrapper {
                overflow: hidden;
            }
            
            #naviBox {
                float: left;
            }
            
            #myInfo {
                width: 1000px;
                height: 500px;
                float: left;
                margin: 50px 150px;
                text-align: center;
            }
            
            #infoBox {
                width: 550px;
                margin: 50px 50px;
            }
            
            div.C_MyPageNavi {
                width: 150px;
                height: 100px;
                line-height: 100px;
                font-family: Arial, Helvetica, sans-serif;
                text-align: right;
                font-weight: 600;
                font-size: 18px;
                background-color: rgb(202, 202, 202);
            }
            
            #mainMyPage {
                width: 150px;
                height: 125px;
                line-height: 125px;
                font-size: 25px;
                font-weight: 600;
                text-align: right;
                background-color: rgb(202, 202, 202);
            }
            
            div.C_MyPageNavi:hover {
                background-color: rgb(138, 138, 138);
            }
            
            div.C_MyPageNavi>a {
                text-decoration: none;
                color: black;
            }
            
            #memInfo {
                background-color: rgb(138, 138, 138);
            }
            
            table,
            td,
            th {
                border: 1px solid lightgray;
                border-collapse: collapse;
                padding: 10px 10px;
                margin: 10px;
            }
            
            td {
                text-align: center;
                width: 450px;
            }
            
            th {
                width: 125px;
                text-align: right;
            }
        </style>
    </head>

    <body>
        <iframe src="../include/navi.html" style="width: 100%" height="180px" scrolling="no" frameborder="none"></iframe>
        <div id="wrapper">
            <div id="naviBox">
                <div id="mainMyPage " class="C_MyPageNavi "><a href="# " style="text-decoration: none; color: black ">마이페이지&nbsp;</a></div>
                <div id="memInfo" class="C_MyPageNavi "><a href="# ">회원정보&nbsp;</a></div>
                <div class="C_MyPageNavi "><a href="# ">위시리스트&nbsp;</a></div>
                <div class="C_MyPageNavi "><a href="# ">문의내역&nbsp;</a></div>
                <div class="C_MyPageNavi "><a href="# ">예약내역&nbsp;</a></div>
                <div class="C_MyPageNavi "><a href="# ">신고내역&nbsp;</a></div>
            </div>

            <div id="myInfo">
                <form action="# " method="GET ">
                    <div id="infoBox">
                        <p style="font-size: 25px; font-weight: 600; text-align: left; color: mediumslateblue ">나의 정보</p>
                        <table>
                            <tr>
                                <th>아이디</th>
                                <td>아이디 가져오기</td>
                            </tr>
                            <tr>
                                <th>생년월일</th>
                                <td>생년월일 가져오기</td>
                            </tr>
                            <tr>
                                <th>성별</td>
                                    <td>성별 가져오기</td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td>이메일 가져오기</td>
                            </tr>
                            <tr>
                                <th>전화번호</th>
                                <td>전화번호 가져오기</td>
                            </tr>
                        </table>
                        <div id="pwInputBox">
                            <span>비밀번호: <input id="pwBox"type="password" placeholder="수정하려면 비밀번호를 입력하세요." name="pw" display="hidden" style="width: 225px;"></span>
                            <span><input type="button" value="수정하기" onclick="modifyBtn()" /></span>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <script>
    </script>

    </html>