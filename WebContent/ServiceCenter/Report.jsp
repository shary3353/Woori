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
          table, th, td{
              padding: 15px;
          }
          #content{
            width: 650px;
            height: 200px;
          }
          table,th{
              text-align: left;
              border: 2px solid gray;
              border-collapse: collapse;
          }
          #form{
              margin-left: 300px;
          }
          table{
              width: 60%;
          }
          #button{
              text-align: center;
              width: 650px;
              margin-left: 120px;
              margin-top: 30px;
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
    <div style="">
        <div id="form">

            <h3>신고하기</h3>
            <form>
                <table>
                    <tr>
                        <th>신고제목</th>
                        <th><input type="text" name="subject" style="width: 500px;" placeholder="신고제목을 입력해주세요."></th>
                    </tr>
                    <tr>
                        <th>신고자</th>
                        <th>testCustomer</th>
                    </tr>
                    <tr>
                        <th>신고대상자</th>
                        <th>testSeller</th>
                    </tr>  
                    <tr>
                        <th>신고 카테고리</th>
                        <th>
                            카테고리 선택
                            <select name="categorie" id="cate">
                                <option value="신고사유/카테고리" selected="selected">신고사유/카테고리</option>
                                <option value="상품관련">상품관련</option>
                                <option value="판매자관련">판매자관련</option>
                                <option value="기타">기타</option>
                            </select>
                        </th>
                    </tr>
                    <tr>
                        <th>신고내용</th>
                        <th><textarea name="content" id="content" cols="30" rows="10" placeholder="신고내용을 입력해주세요."></textarea></th>
                    </tr>
                    <tr>
                        
                    </tr>
                </table>
                <div id="button">
                    <button style="width: 80px;">신고하기</button>
                </div>
            </form>
        </div>
    </div>
    </div>
    </body>
</html>