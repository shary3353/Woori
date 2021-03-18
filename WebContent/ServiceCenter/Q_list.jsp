<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <title>1:1문의 게시판</title>
        <script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
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
              width: 50px;
          }
          .subject{
              width: 1000px;
              text-align: left;
          }
          .date{
              width: 150px;
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
                    <div class="One"><a href="Q_write.jsp">1:1 문의하기</a></div>
                    <div class="Question"><a href="Question.jsp">자주묻는 질문</a></div>
                    <div class="Report"><a href="Report.jsp">신고하기</a></div>
                </div> 
            </div>
           <div id="list_div">
               <h3>1:1 문의내역</h3>
               <form action="qDetail" method="post">
                <div id="list">
               <table>
                        <tr>
                            <th class="num">번호</th>
                            <th class="subject">제목</th>
                            <th class="date">날짜</th>
                        </tr>
               <c:forEach items="${list}" var="list">
                        <tr>
                            <th class="num"><input type="text" name="q_idx"value="${list.q_idx }" readonly style="display:none;"/>${list.q_idx }</th>
                            <th class="subject" ><a href="#" id="subjectDetail" onclick="has('${list.q_idx}');">${list.subject }</a></th>
                            <th class="date">${list.q_reg_date }</th>
                        </tr>
                        <tr>   
                            <td colspan="3" id="${list.q_idx }" style="display:none;">
                            <input type="password" maxlength="4" id="p5" name="password">
                            <button id="sbmBtn">확인</button>
                            </td>
                        </tr>
               </c:forEach>
                    </table>
                    </div>
               </form>
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
    /*   function doDisplay(){
    	  var con = document.getElementById("pass");
    	  if(con.style.display == 'none'){
    		  	con.style.display='inline';
    	  }else{
    		  con.style.display ='none';
    	  }
      }
     */
    	 /* $('#subjectDetail').click(function(){
    		 $('#pass').toggle();
    		 
    	 }); */
	var idx = "";    	 
     function has(id){
    	 var idx = document.getElementById(id);
    	 console.log(idx)
    	 if(idx.style.display =="none"){
    		 idx.style.display = '';
    		 
    	 }else{
    		 idx.style.display = "none";
    	 }
     }
</script>
</html>