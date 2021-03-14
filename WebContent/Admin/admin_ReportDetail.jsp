<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminStyle.css">
    
    <title>ReportDetail</title>
    <style>
      #wrap{
          
          width: 1920px;
          height: 600px;
          display: table-cell;
          vertical-align: middle;
      }
      #ReportDetail{
          width: 70%;
          margin-right: auto;
          margin-left: auto;
      }
      table{
          width: 100%;
      }
        table,th,td{           
            border: 1px solid gray;
            border-collapse: collapse;
            padding: 10px 5px;
        }
        #content{
            height: 250px;
        }
      table th{
          width: 20%;
      }
      #listbutton{
        text-align: center;
        padding: 25px;
      }
    </style>
</head>
<body>
    <div id="wrap">
        <div id="Admin_Header">  <!-- í¤ë ìì­ -->
            <%@include file="admin_Header.jsp" %>
        </div>  <!--í¤ë ë«ì-->

        <nav>    <!-- ê´ë¦¬ì ë¤ë¹ê²ì´ì-->
            <%@include file="admin_Nav.jsp" %>
        </nav>  <!--ë¤ë¹ê²ì´ì ë«ì-->
          <div id="ReportDetail">
                <table>
                    <tr>
                        <th>신고제목</th>
                        <td>신고글 제목</td>
                    </tr>
                    <tr>
                        <th>신고자</th>
                        <td>신고자 ID</td>
                    </tr>
                    <tr>
                        <th>신고대상자</th>
                        <td>신고 대상자 ID</td>
                    </tr>
                    <tr>
                        <th>신고카테고리</th>
                        <td>카테고리 내용</td>
                    </tr>
                    <tr id="content">
                        <td colspan="2"> 신고글 내용</td>
                    </tr>
                </table>
                <div id="listbutton">
                    <button style="width: 200px; height: 30px;">신고관리 리스트</button>
                </div>
                
            </div>
        </div>
</body>
</html>