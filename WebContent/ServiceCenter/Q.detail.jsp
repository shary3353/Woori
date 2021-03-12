<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
            body{
                min-width: 1920;
            }
            a{
                text-decoration: none;
                color:  rgb(239, 239, 245);
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
            .Question {
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
          #form table,tr,th,td{
              border:  2px solid gray;
              padding: 5px;
              border-collapse: collapse;
          }
          #form th{
              text-align: center;
          }
          #table{
              width: 1000px;
          }
          #form{
              margin-left: 300px;
              
          }
    </style>
</head>
<body>
    <div style="min-width: 1920px">
        <div>
            <iframe src="../Include/navi.html" style="width: 100%" height="180px" scrolling="no" frameborder="none"></iframe>
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
            <h3>문의 내용</h3>
            <table id="table">
                <tr>
                    <th>제목</th>
                    <td colspan="5">혹시 발렌타인 재고 있나요?</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>shary3057</td>
                    <th>카테고리</th>
                    <td>상품관련</td>
                    <th>작성일</th>
                    <td>2021-03-08</td>
                </tr>
                <tr>
                    <th>판매자</th>
                    <td>123-456-78910</td>
                    <th>문의상품</th>
                    <td colspan="3">Ballantine’s</td>
                </tr>
                <tr>
                    <th colspan="6">문의내용</th>
                </tr>
                <tr>
                    <td colspan="6" style="height: 150px;">혹시 발렌타인 재고 있나요?</td>
                </tr>
                <tr>
                    <th colspan="6" style="height: 70px;" >등록된 답변이 없습니다.</th>
                </tr>
                <tr>
                    <th colspan="6"><button>목록으로 돌아가기</button></th>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>