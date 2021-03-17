<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Question</title>
    <style>

            body{
                min-width: 1920;
            }
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
          #image{
              margin-left: 300px ;
              padding-top: 100px;
          }
          
    </style>
</head>
<body>
    <div style="min-width: 1920px">
        <div>
            <iframe src="../Include/navi.html" style="width: 100%" height="180px" scrolling="no" frameborder="none"></iframe>
        </div>
        <div class="wrap">
            
            <div class="seMain">
                <div class="sideMenu">
                        <div class="Service"><a href=qList>고객센터</a></div>
                    <div class="One"><a href="Q_write.jsp">1:1 문의하기</a></div>
                    <div class="Question"><a href="Question.jsp">자주묻는 질문</a></div>
                    <div class="Report"><a href="Report.jsp">신고하기</a></div>
                </div> 
            </div>
            <div id="image">
                <img src="../img/FQA.PNG" width="1000" height="400"/>
            </div>
        </div>
    </div>
</body>
</html>