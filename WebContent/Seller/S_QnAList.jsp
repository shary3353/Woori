<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <title>seller Q&A</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <style>
        *{margin: 0; padding: 0;}
        #content{
            width: 100%;
            margin-top: 40px;
        }
        table{
            width: 900px;
            margin: auto;
        }
        table,th,td{
            border-bottom: 1px solid gray;
            border-collapse: collapse;
            margin-top: 5px;
            padding: 10pX;
        }
        #List_Paging{
            margin: 80px;
            text-align: center;
        }
        .Page_Content{
            padding: 5px 5px;
            background-color: #F6F6F6 ;
        }
        .Page_Number{
            padding: 5px 10px;
            background-color: #c0e1eb;
        }
        .title{
            border-top: 1px solid white;
            font-size: 24px;
            font-weight: 600;
            text-align: left;
        }
        th{
            text-align: left;
            background-color: rgb(231, 231, 231);
        }
        td>a{
            color: black;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <jsp:include page="S_navi.jsp"/>

    <div id="content"><!--본문 : 문의내역 - 리스트 -->
        <table>
            <tr>
                <td colspan="6" class="title">문의내역</td>
            </tr>
            <tr>
                <th>No</th>
                <th>문의분류</th>
                <th>문의제목</th>
                <th>문의자</th>
                <th>문의날짜</th>
                <th>상태</th>
            </tr>
            <c:forEach items="${list}" var="qlist">
            <tr>
                <td>${qlist.q_idx}</td>
                <td>${qlist.category}</td>
                <td>
                	<c:if test="${empty qlist.s_answer}"><a href="./sAnswerForm?q_idx=${qlist.q_idx}">${qlist.subject}</a></c:if>
					<c:if test="${not empty qlist.s_answer}"><a href="./sAnswerDetail?q_idx=${qlist.q_idx}">${qlist.subject}</a></c:if>
                </td>
                <td>${qlist.cid}</td>
                <td>${qlist.q_reg_date}</td>
                <td class="chkAnswer">
	                <c:if test="${empty qlist.s_answer}"><span style="color:red;">진행중</span></c:if>
					<c:if test="${not empty qlist.s_answer}"><span style="color:green;">답변완료</span></c:if>
                </td>
            </tr>
            </c:forEach>
        </table>

        <div id="List_Paging"> <!--페이징부분-->
            <span class="Page_Content">처음</span>
            <span class="Page_Content">이전</span>
            <span class="Page_Number">1</span>
            <span class="Page_Content">다음</span>
            <span class="Page_Content">마지막</span>
        </div>
    </div>
</body>

</html>