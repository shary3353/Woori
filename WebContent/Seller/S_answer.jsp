<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <title>seller answer detail</title>
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
    
    <div id="content"> <!--본문 : 문의상세보기 -->
        <table>
            <tr>
                <td colspan="6" class="title">문의 및 답변 보기</td>
            </tr>
            <tr>
                <td class="column-name">제목</td>
                <td colspan="5">${dto.subject}</td>
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
                <td colspan="5" style="white-space: pre-line;">${dto.content}</td>
            </tr>
            <tr>
                <td class="column-name">답변내용</td>
                <td colspan="5" style="white-space: pre-line;">
                	${dto.s_answer}
                    <div style="background:lightgray;margin-top: 5px;">*이외의 문의 있을시 새로운 문의작성 부탁드립니다.</div>
                </td>
            </tr>
            <tr>
                <td colspan="6" class="btnArea">
                    <button onclick="location.href='./sQAList'">목록으로가기</button>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>