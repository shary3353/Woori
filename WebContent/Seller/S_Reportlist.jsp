<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <title>seller report</title>
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

    <div id="content"><!--본문 : 문의내역 - 리스트 -->
        <table>
            <tr>
                <td colspan="6" class="title">신고내역</td>
            </tr>
            <tr>
                <th>No</th>
                <th>신고분류</th>
                <th>신고제목</th>
                <th>신고자</th>
                <th>신고날짜</th>
                <th>진행상태</th>
            </tr>
            <tr>
                <td>1</td>
                <td>응답</td>
                <td><a href="#신고상세보기">고객이 잠수함에 갇힌 것 같습니다.</a></td>
                <td>USER158</td>
                <td>21.03.05</td>
                <td id="chkAnswer">진행중</td>
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
<script>
    var chktext = $('#chkAnswer').text();
    console.log(chktext);
    if(chktext=="진행중"){
        $('#chkAnswer').css("color","red");
    } else if(chktext=="답변완료"){
        $('#chkAnswer').css("color","green");
    }
</script>
</html>