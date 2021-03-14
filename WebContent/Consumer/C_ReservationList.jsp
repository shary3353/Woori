<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8" />
        <title>예약내역</title>

        <style>
            #wrapper {
                overflow: hidden;
            }
            
            #naviBox {
                float: left;
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
            
            div.C_MyPageNavi:hover {
                background-color: rgb(138, 138, 138);
            }
            
            div.C_MyPageNavi>a {
                text-decoration: none;
                color: black;
            }
            
            #memReservationList {
                background-color: rgb(138, 138, 138);
            }
            
            table,
            td,
            th {
                border: 1px solid lightgray;
                border-collapse: collapse;
                padding: 10px 10px;
                margin: 10px;
                line-height: 100%;
                text-align: center;
            }
            
            th {
                background-color: bisque;
            }
            
            .p_PhoNDesc {
                border: none;
                margin: 0px;
                padding: 0px;
                text-align: left;
            }
            
            #wishListBox {
                position: relative;
                display: inline-block;
                text-align: center;
                margin: 15px 150px;
            }
            
            .delBtn {
                border: 1px solid lightpink;
                width: 30px;
                height: 30px;
                color: lightpink;
                background-color: crimson;
            }
            
            .delBtn:hover {
                cursor: pointer;
            }
            
            .pagingBtn {
                text-decoration: none;
                color: black;
                font-weight: 600;
                background-color: lightgray;
                margin: 1px 2px;
                margin-top: 0px;
                padding: 3px;
                border: 1px white;
            }
            
            .pagingBtn:hover {
                background-color: steelblue;
            }
            
            form {
                margin-bottom: 5px;
            }
            
            .reportBtn {
                background-color: white;
                color: gray;
                text-decoration: none;
                font-weight: 600;
                border: 1px solid lightgray;
                border-radius: 5px;
            }
            
            .reportBtn:hover {
                background-color: lightblue;
            }
            
            .reserProcess {
                color: crimson;
            }
        </style>
    </head>

    <body>
        <div style="min-width: 1920px">
            <iframe src="../include/navi.html" style="width: 100%" height="180px" scrolling="no" frameborder="none"></iframe>
            <div id="wrapper">
                <div id="naviBox">
                    <div id="mainMyPage " class="C_MyPageNavi "><a href="# " style="text-decoration: none; color: black ">마이페이지&nbsp;</a></div>
                    <div class="C_MyPageNavi "><a href="# ">회원정보&nbsp;</a></div>
                    <div class="C_MyPageNavi "><a href="# ">위시리스트&nbsp;</a></div>
                    <div class="C_MyPageNavi "><a href="# ">문의내역&nbsp;</a></div>
                    <div id="memReservationList" class="C_MyPageNavi "><a href="# ">예약내역&nbsp;</a></div>
                    <div class="C_MyPageNavi "><a href="# ">신고내역&nbsp;</a></div>
                </div>
                <div id="wishListBox">
                    <form action="" method="GET">
                        <span><p style="font-size: 25px; font-weight: 600; text-align: left; color: mediumslateblue; margin-left: 20px; margin-top: 30px;" >예약 내역</p></span>
                        <table>
                            <tr>
                                <th>No</th>
                                <th>상품번호</th>
                                <th>상품정보</th>
                                <th>판매자</th>
                                <th>예약등록일</th>
                                <th>방문예정일</th>
                                <th>예약상황</th>
                                <th>신고하기</th>
                            </tr>
                            <tr>
                                <td style="width: auto;">3</td>
                                <td>상품번호 가져오기</td>
                                <td>
                                    <table class="p_PhoNDesc">
                                        <tr>
                                            <td class="p_PhoNDesc" rowspan="3" style="width: 550px;"><img src="../img/no-image.png" alt="상품사진" style="width: 125px; height: 125px;" /></td>
                                        </tr>
                                        <tr>
                                            <td class="p_PhoNDesc">상품이름 가져오기</td>
                                        </tr>
                                    </table>
                                </td>
                                <td>판매자 가져오기</td>
                                <td>2021-03-01</td>
                                <td>2021-03-23</td>
                                <td class="reserProcess">신청중</td>
                                <td><a href="#" class="reportBtn">신고하기</a></td>
                            </tr>
                            <tr>
                                <td style="width: auto;">2</td>
                                <td>상품번호 가져오기</td>
                                <td>
                                    <table class="p_PhoNDesc">
                                        <tr>
                                            <td class="p_PhoNDesc" rowspan="3" style="width: 550px;"><img src="../img/no-image.png" alt="상품사진" style="width: 125px; height: 125px;" /></td>
                                        </tr>
                                        <tr>
                                            <td class="p_PhoNDesc">상품이름 가져오기</td>
                                        </tr>
                                    </table>
                                </td>
                                <td>판매자 가져오기</td>
                                <td>2021-03-01</td>
                                <td>2021-03-23</td>
                                <td class="reserProcess">신청중</td>
                                <td><a href="#" class="reportBtn">신고하기</a></td>
                            </tr>
                            <tr>
                                <td style="width: auto;">1</td>
                                <td>상품번호 가져오기</td>
                                <td>
                                    <table class="p_PhoNDesc">
                                        <tr>
                                            <td class="p_PhoNDesc" rowspan="3" style="width: 550px;"><img src="../img/no-image.png" alt="상품사진" style="width: 125px; height: 125px;" /></td>
                                        </tr>
                                        <tr>
                                            <td class="p_PhoNDesc">상품이름 가져오기</td>
                                        </tr>
                                    </table>
                                </td>
                                <td>판매자 가져오기</td>
                                <td>2021-03-01</td>
                                <td>2021-03-23</td>
                                <td class="reserProcess">신청중</td>
                                <td><a href="#" class="reportBtn">신고하기</a></td>
                            </tr>
                        </table>
                    </form>
                    <span>
          <a href="#" class="pagingBtn">처음</a>
          <a href="#" class="pagingBtn">이전</a>
          <a href="#" class="pagingBtn" style="background-color: skyblue; color: white;">&nbsp;1&nbsp;</a>
          <a href="#" class="pagingBtn">다음</a>
          <a href="#" class="pagingBtn">마지막</a>
        </span>
                </div>
            </div>
    </body>
    <script></script>

    </html>