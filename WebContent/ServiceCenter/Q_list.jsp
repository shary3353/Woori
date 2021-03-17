<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
          #pass{
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
                <iframe src="../Include/navi.html" style="width: 100%" height="180px" scrolling="no" frameborder="none"></iframe>
            </div>
            <div class="seMain">
                <div class="sideMenu">
                    <div class="Service"><a href=qList>고객센터</a></div>
                    <div class="One"><a href="qWrite">1:1 문의하기</a></div>
                    <div class="Question"><a href="Question.jsp">자주묻는 질문</a></div>
                    <div class="Report"><a href="Report.jsp">신고하기</a></div>
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
               <c:forEach items="${list }" var="list">
                        <tr>
                            <th class="num">${list.q_idx }</th>
                            <th class="subject" id="subjectDetail"><a href="#" onclick="showPw('pass')">${list.subject }</a></th>
                            <th class="date">${list.q_reg_date }</th>
                        </tr>
                        <tr>
                            <td colspan="3" id="pass"><input type="password" maxlength="4" id="p5"><button>확인</button></td>
                        </tr>
               </c:forEach>
                    </div>
                    
                    </table>
                    <div id="Admin_Seller_List_Paging"> <!--판매자리스트 페이징부분-->
                        <span class="Page_Content">
                        <c:if test="${currnPage == 1}">처음</c:if>
                        <c:if test="${currPage > 1 }">
                        <a href="./qList?page=${1 }">처음</a>
                        </c:if>
                        </span>
                        <span class="Page_Content">
                       		<c:if test="${currPage == 1 }">이전</c:if>
                        	<c:if test="${currPage > 1}">
                        	<a href="./qList?page=${currPage-1 }">이전</a>
                        	</c:if>
                        </span>
                        <span id="Page_Number"><a>${currPage}</a></span>
                        <span class="Page_Content">
                        <c:if test="${currPage == maxPage }">다음</c:if>
                        <c:if test="${currPage < maxPage }">
                        <a href="./qList?page=${currPage + 1}">다음</a>
                        </c:if>
                        </span>
                        <span class="Page_Content">
						<c:if test="${currPage < maxPage }">
                        <a href="./qList?page=${maxPage}">마지막</a>
						</c:if>                        
                        </span>
                    </div>
                </div>
  	
       </body>
       <script>
       function showPw(id){
   	    obj = document.getElementById(id);
   	    if(obj.style.display == "none"){
   	        obj.style.display = "";
   	    }else{
   	        obj.style.display = "none";
   	    }
   	  }
    
</script>
</html>