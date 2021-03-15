<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <title>seller noAnswer</title>
    <style>
        *{margin: 0; padding: 0;}
        #content{
            width: 100%;
            margin-top: 40px;
        }
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
        .btnArea{
            text-align: right;
            border-bottom: 1px solid white;
        }
        button{
            padding: 4px;
        }
        .title{
            border-top: 1px solid white;
            font-size: 24px;
            font-weight: 600;
            text-align: left;
        }
        .column-name {
            background-color: rgb(231, 231, 231);
            width: 100px;
            text-align: center;
        }
    </style>
</head>
<body>
    <jsp:include page="S_navi.jsp"/>
    
    <div id="content"> <!--본문 : 문의답변하기 -->
        <table>
            <tr>
                <td colspan="6" class="title">문의 답변하기</td>
            </tr>
            <tr>
                <td class="column-name">제목</td>
                <td colspan="5">문의드립니다</td>
            </tr>
            <tr>
                <td class="column-name" >질문자</td>
                <td>${dto.cid}</td>
                <td class="column-name" >질문분류</td>
                <td>${dto.category}</td>
                <td class="column-name" >문의날짜</td>
                <td>${dto.q_reg_date}</td>
            </tr>
            <tr>
                <td class="column-name">내용</td>
                <td colspan="5">${dto.content}</td>
            </tr>
            <form action="./sAnswer" method="post">
	           <tr>
	               <td class="column-name">답변하기</td>
	               <td colspan="5" style="text-align: center;">
	               		<input type="hidden" name="q_idx" value="${dto.q_idx}"/>
	                   <textarea name="s_answer" id="" cols="95" rows="10">${dto.s_answer}</textarea>
	               </td>
	           </tr>
	           <tr>
	               <td colspan="6" class="btnArea">
	                   <button type="submit">답변하기</button>
            </form>
	                   <button onclick="location.href='./sQAList'">목록으로가기</button>
	               </td>
	           </tr>
        </table>
    </div>
</body>
</html>