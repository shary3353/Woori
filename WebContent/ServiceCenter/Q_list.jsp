<!DOCTYPE html>
<html>
    <head>
        <title>1:1문의 게시판</title>
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
            color : rgb(43, 77, 109);
              padding-top: 40px;
              font-size: 27px;
          }     
          #Admin_Seller_List_Paging{
            text-align: center;
            margin-right: 300px;
            margin-top: 20px;
          }
          #list{
              margin-left: 50px;
          }
          #list table,th,td{
              border-bottom:  2px solid gray;
              border-right: 1px solid grey;;
              border-collapse: collapse;
              padding : 20px 20px;
          }
          .num{
              width: 7%;
          }
          .subject{
              width: 60%;
              text-align: left;
          }
          .date{
              width: 10%;
          }
          .pass{
              display: none;
          }
          #list_div{
            margin-left: 300px;
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
           <div id="list_div">
               <h3>1:1 문의내역</h3>
               <table>
                    <div id="list">
                        <tr>
                            <th class="num">번호</th>
                            <th class="subject">제목</th>
                            <th class="date">날짜</th>
                        </tr>
                        <tr>
                            <th class="num">5</th>
                            <th class="subject"><a href="#">발렌타인 재고 있나요?</a></th>
                            <th class="date">2021.02.01</th>
                        </tr>
                        <tr class="pass">
                            <td colspan="3"><input type="password" maxlength="4" id="p5"><button>확인</button></td>
                        </tr>
                        <tr>
                            <th class="num">4</th>
                            <th class="subject"><a href="#">발렌타인 재고 있나요?</a></th>
                            <th class="date">2021.02.01</th>
                        </tr>
                        <tr class="pass">
                            <td colspan="3"><input type="password" maxlength="4" id="p4"><button>확인</button></td>
                        </tr>
                        <tr>
                            <th class="num">3</th>
                            <th class="subject"><a href="#">발렌타인 재고 있나요?</a></th>
                            <th class="date">2021.02.01</th>
                        </tr>
                        <tr class="pass">
                            <td colspan="3"><input type="password" maxlength="4" id="p3"><button>확인</button></td>
                        </tr>
                        <tr>
                            <th class="num">2</th>
                            <th class="subject"><a href="#">발렌타인 재고 있나요?</a></th>
                            <th class="date">2021.02.01</th>
                        </tr>
                        <tr class="pass">
                            <td colspan="3"><input type="password" maxlength="4" id="p2"><button>확인</button></td>
                        </tr>
                        <tr>
                            <th class="num">1</th>
                            <th class="subject"><a href="#">발렌타인 재고 있나요?</a></th>
                            <th class="date">2021.02.01</th>
                        </tr>
                        <tr class="pass">
                            <td colspan="3"><input type="password" maxlength="4" id="p1" ><button>확인</button></td>
                        </tr>
                    </div>
                    
                    </table>
                    <div id="Admin_Seller_List_Paging"> <!--판매자리스트 페이징부분-->
                        <span class="Page_Content"><a href="#">처음</a></span>
                        <span class="Page_Content"><a href="#">이전</a></span>
                        <span class="Page_Number"><a>1</a></span>
                        <span class="Page_Content"><a href="#">다음</a></span>
                        <span class="Page_Content"><a href="#">마지막</a></span>
                    </div>
                </div>
                
                
                
                
            </body>
            <script>
    
    
</script>
</html>