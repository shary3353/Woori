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
</head>
<body>
    <div id="Wrapper">
        <div id="Admin_Header">
            <%@include file="admin_Header.jsp" %>
        </div>

        <nav>  
            <%@include file="admin_Nav.jsp" %>
        </nav>  
          <div id="Admin_Seller_List">
                <table class="sellerlist" style="margin-left:auto; margin-right:auto;">
                    <tr>
                        <th>신고제목</th>
                        <td>${selectedReport.subject }</td>
                    </tr>
                    <tr>
                        <th>신고자</th>
                        <td>${selectedReport.reporter_id }</td>
                    </tr>
                    <tr>
                        <th>신고대상자</th>
                        <td>${selectedReport.target_id }</td>
                    </tr>
                    <tr>
                        <th>신고카테고리</th>
                        <td>${selectedReport.category }</td>
                    </tr>
                    <tr>
                        <th>신고 처리상태</th>
                        <td>
                        	<c:if test="${selectedReport.status  == 0}">처리중</c:if>
                        	<c:if test="${selectedReport.status  == 1}">처리완료</c:if>
                        </td>
                    </tr>
                    <tr id="content">
                        <td colspan="2">${selectedReport.content }</td>
                    </tr>
                </table>
                <div id="listbutton" style="text-align: center;">
                    <button style="width: 200px; height: 30px;" onclick="location.href='./rList'">신고관리 리스트</button>
                </div>
                
            </div>
        </div>
</body>
</html>