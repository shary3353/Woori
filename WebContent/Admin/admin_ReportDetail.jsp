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
                        <th>ì ê³ ì ëª©</th>
                        <td>ì ê³ ê¸ ì ëª©</td>
                    </tr>
                    <tr>
                        <th>ì ê³ ì</th>
                        <td>ì ê³ ì ID</td>
                    </tr>
                    <tr>
                        <th>ì ê³ ëìì</th>
                        <td>ì ê³  ëìì ID</td>
                    </tr>
                    <tr>
                        <th>ì ê³ ì¹´íê³ ë¦¬</th>
                        <td>ì¹´íê³ ë¦¬ ë´ì©</td>
                    </tr>
                    <tr id="content">
                        <td colspan="2"> ì ê³ ê¸ ë´ì©</td>
                    </tr>
                </table>
                <div id="listbutton">
                    <button style="width: 200px; height: 30px;">ì ê³ ê´ë¦¬ ë¦¬ì¤í¸</button>
                </div>
                
            </div>
        </div>
</body>
</html>