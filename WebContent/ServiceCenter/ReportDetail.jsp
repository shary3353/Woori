<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
         
            a{
                text-decoration: none;
                color: inherit;
            }
            .sideMenu{
                width: 180px;
                height: 600px;
                background-color: rgb(43, 77, 109);
                font-weight: bold;
                color : rgb(239, 239, 245);
            }
            .Service{
                font-size: 25px;
                text-align: right;
                padding: 50px 20px;
            }
            .One{
                font-size: 15px;
                text-align: right;
                margin: 10px;
                padding: 10px;

            }
            .Question{
                font-size: 15px;
                text-align: right;
                margin: 10px;
                padding: 10px;

            }
            .Report{
                font-size: 15px;
                text-align: right;
                margin: 10px;
                padding: 10px;
            }
            .One:hover,.Question:hover,.Report:hover{
                text-decoration: none;
                background-color: lightgray;
                
            }
            .seMain{
                float:left;
                
            }
          h3{
              color: rgb(43, 77, 109);;
              padding-top: 40px;
              font-size: 27px;
          }
          #content{
            width: 700px;
            height: 200px;
          }
          table ,th,td{
              border:  2px solid gray;
              border-collapse: collapse;
              padding : 15px;
          }
          table{
              width: 950px;
          }
          th{
              width: 150px;
          }
          #form{
              margin-left: 300px;
          }
          #button{
              text-align: center;
              margin-right: 400px;
              margin-top: 30px;
          }
    </style>
</head>
<body>
    <div style="min-width: 1920px">
    
    <div>
        <iframe src="./img/navi/navi.html" style="width: 100%" height="180px" scrolling="no" frameborder="none"></iframe>
    </div>
    <div class="seMain">
        <div class="sideMenu">
            <div class="Service">고객센터</div>
            <div class="One"><a href="#">1:1 문의내역</a></div>
            <div class="Question"><a href="#">자주묻는 질문</a></div>
            <div class="Report"><a href="#">신고하기</a></div>
        </div> 
    </div>
    <div id="form">
        <h3>신고 상세보기</h3>
        <table>
            <tr>
                <th>신고제목</th>
                <td>신고제목 내용</td>
            </tr>
            <tr>
                <th>신고자</th>
                <td>신고자 이름</td>
            </tr>
            <tr>
                <th>신고대상자</th>
                <td>신고대상자 ID</td>
            </tr>
            <tr>
                <th>신고 카테고리</th>
                <td>상품 관련</td>
            </tr>
            <tr>
                <th>처리상태</th>
                <td>처리중</td>
            </tr>
            <tr>
                <td colspan="2" id="content">분명 그저께 A 21 OpusOne 2016년산 800,000원으로 예약을 했는데, 오늘 보니까 390,000원으로 바껴있네요? 판매자가 너무 양심 없는 것 아닙니까???????? 신고합니다.</td>
            </tr>
        </table>
    </div>
    <div id="button"><button >내 신고내역 목록 가기</button></div>
    </div>
</body>
</html>