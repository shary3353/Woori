<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>S_ReportDetail</title>
    <style>
       	*{margin: 0; padding: 0;}
  
         table{
            width: 800px;
            margin: auto;
         }
         table,th,td{
            border-bottom: 1px solid gray;
            border-collapse: collapse;
            margin-top: 5px;
            padding: 10pX;
         }
         .title{
            border-top: 1px solid white;
            font-size: 24px;
            font-weight: 600;
            text-align: left;
        }
        .column-name {
            background-color: rgb(231, 231, 231);
            width: 120px;
            text-align: center;
        }
        .btnArea{
            text-align: right;
            border-bottom: 1px solid white;
        }
        button{
            padding: 4px;
        }
    </style>
</head>
<body>   
     <jsp:include page="S_navi.jsp"/>
    <div id="content">
        <table>
        	<tr>
        		 <td colspan="2" class="title">신고내용 상세보기</td>
        	</tr>
            <tr>
                <th class="column-name">신고제목</th>
                <td>${dto.subject }</td>
            </tr>
            <tr>
                <th class="column-name">신고자</th>
                <td>${dto.reporter_id }</td>
            </tr>
            <tr>
                <th class="column-name">신고대상자</th>
                <td>${dto.target_id}</td>
            </tr>
            <tr>
                <th class="column-name">신고 카테고리</th>
                <td>${dto.category }</td>
            </tr>
            <tr>
                <th class="column-name">처리상태</th>
                <td>
                <c:if test="${dto.status == 0}"><span style="color:red;">처리중</span></c:if>
                <c:if test="${dto.status > 0}"><span style="color:green">처리완료</span></c:if>
                </td>
                
            </tr>
            <tr>
                <th class="column-name">신고내용</th>
                <td id="content">${dto.content }</td>
            </tr>
            <tr>
	            <td colspan="2" class="btnArea">
			    	<button onclick="location.href='./sReportList'">신고내역 목록 가기</button>
	            </td>
            </tr>
        </table>
    </div>
</body>
</html>