<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>q.write</title>
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
            resize: none;
          }
          #form table, tr, td{
            padding: 15px 10px;
              border:  2px solid gray;
            border-collapse: collapse;
          }
          #form table td{
          }
          #form table th{
              
          }
          #setpass{
              text-align: left;
              font-size: 12px;
          }
          .column1{
              text-align: center;
              padding: 10px 5px;
          }
          .column2{
            padding: 20px 10px;
          }
          #form{
              margin-left: 300px;
          }
          table{
              width: 60%;
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
        <div>
        </div>
        <div id="form">
        <form action="qWrite" method="post">
            <h3>1:1문의 작성</h3>
            <table id="table">
                <tr>
                    <td class="column1">작성자</td>
                    <td class="column2"><input type="text" name="cId" value="${sessionScope.loginId}" readonly/></td>
                    <td>
                        카테고리
                        <select name="category" value="문의/카테고리">
                            <option value="100" selected="selected">상품관련</option>
                            <option value="200">예약관련</option>
                            <option value="300" >매장관련</option>
                            <option value="400">교환/반품/반납</option>
                        </select>
                    </td>
                    <td class="column1" >문의상품</td>
                    <td class="culumn1"  name="productName">
                    
                    <c:set var="p_name" value="<%=request.getParameter(\"p_name\") %>"></c:set>
               		<c:if test="${p_name != null}">
                    <%=request.getParameter("p_name") %>
               		</c:if>
               		<c:if test="${p_name == null }">
               			<input type="text" name=p_name>
               		</c:if>
                      
                    </td>
                </tr>
                <tr>
                    <td class="column1">제목</td>
                    <td colspan="2"><input type="text" placeholder="제목을 입력해주세요" style="width: 380px" maxlength='30' name="subject"></td>
                    <td class="column1">판매자 </td>
                    <td name="sId" >
                    <c:set var ="sId" value="<%=request.getParameter(\"sId\") %>"></c:set>
                    <c:if test="${sId != null }">
                    <%=request.getParameter("sId") %>
                    </c:if>
                    <c:if test="${sId == null}">
                    	<input type=text name="sId">
                    </c:if>
                    </td>
                </tr>
                <tr>
                    <td class="column1">내용</td>
                    <td colspan="4">
                        <textarea name="content" id="content" cols="30" rows="10"></textarea>
                    </td>
                </tr>
            <tr>
                <td class="column1">
                    확인 비빌번호
                </td>
                <td colspan="4" id="setpass">
                    <input type="password" id="pass" placeholder="비밀번호4자리 입력" style="text-align: left;" maxlength='4' name="passWord"/>
                    <button style="display: inline;">저장</button>
                    <div style="display: inline; color:gray">비밀번호는 작성하신 글 확인시 사용됩니다.</div>
                    
                </td>
                
            </tr>
        </table>
        </form>
    </div>
</div>
</body>
</html>