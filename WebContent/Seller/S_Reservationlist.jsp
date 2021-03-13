<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <title>seller Reservationlist</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <style>
        *{margin: 0; padding: 0;}
        #content{
            width: 100%;
            margin-top: 40px;
        }
        table{
            width: 900px;
            margin: auto;
        }
        table,th,td{
            border-bottom: 1px solid gray;
            border-collapse: collapse;
            margin-top: 5px;
            padding: 10pX;
        }
        #List_Paging{
            margin: 80px;
            text-align: center;
        }
        .Page_Content{
            padding: 5px 5px;
            background-color: #F6F6F6 ;
        }
        .Page_Number{
            padding: 5px 10px;
            background-color: #c0e1eb;
        }
        .title{
            border-top: 1px solid white;
            font-size: 24px;
            font-weight: 600;
            text-align: left;
        }
        th{
            text-align: left;
            background-color: rgb(231, 231, 231);
        }
        td>a{
            color: black;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <iframe src="seller_navi.html" width=100% scrolling="no" frameborder="0"></iframe>
    <div id="content"><!--본문 : 예약내역 - 리스트 -->
        <table>
            <tr>
                <td colspan="6" class="title">예약내역</td>
            </tr>
            <tr>
                <th>No</th>
                <th>상품번호</th>
                <th>상품명</th>
                <th>구매자</th>
                <th>예약일</th>
                <th>신청한 날짜</th>
                <th>예약현황</th>
                <th>신고하기</th>
            </tr>
            <tr>
                <td>1</td>
                <td>12345</td>
                <td><a href="#상품상세보기"><img src="이미지.png">Ballantine's</a></td>
                <td>김**</td>
                <td>21.03.04</td>
                <td>21.03.04</td>
                <td><select name="reportstate" id="">
                    <option value="100">신청중</option>
                    <option value="200">예약완료</option>
                    <option value="300">상담완료</option>
                </select><br/><br/><button>예약현황저장</button></td>
                <td><button>신고하기</button></td>
                
            </tr>
        </table>

        <div id="List_Paging"> <!--페이징부분-->
            <span class="Page_Content">처음</span>
            <span class="Page_Content">이전</span>
            <span class="Page_Number">1</span>
            <span class="Page_Content">다음</span>
            <span class="Page_Content">마지막</span>
        </div>
    </div>
</body>
</html>