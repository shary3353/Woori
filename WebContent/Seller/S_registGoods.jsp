<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <title>seller regist goods</title>
    <style>
        *{margin: 0; padding: 0;}
        #content{
            width: 100%;
            margin-top: 40px;
        }
        table{
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
            text-align: center;
        }
    </style>
</head>
<body>
    <jsp:include page="S_navi.jsp"/>

    <div id="content"><!--본문 : 물품등록하기-->
        <table>
            <tr>
                <td colspan="3" class="title">물품등록하기</td>
            </tr>
            
            <form action="registItem" method="post" enctype="multipart/form-data" onsubmit="return check()">
            <tr>
                <td rowspan="5">
                    <input type="file" name="photo" id="imageFile" accept="image/*">
                </td>
                <td class="column-name" >물품이름</td>
                <td><input type="text" placeholder="물품이름을 입력해주세요." name="p_name" id="p_name"/></td>
            </tr>
            <tr>
                <td class="column-name" >카테고리</td>
                <td>
                    <select name="c_idx" id="c_idx">
                        <option value="">카테고리선택</option>
                        <option value="1">와인</option>
                        <option value="2">위스키</option>
                        <option value="3">꼬냑/브랜디</option>
                        <option value="4">보드카</option>
                        <option value="5">샴페인</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="column-name">가격</td>
                <td>
                    <input type="text" placeholder="가격(원)을 입력해주세요." name="p_price" id="p_price"/>원
                </td>
            </tr>
            <tr>
                <td class="column-name" >판매자</td>
                <td><%= session.getAttribute("loginID")%>
                <input type="hidden" value ="<%= session.getAttribute("loginID")%>" name="sid"/>
                </td>
            </tr>
            <tr>
                <td class="column-name" >상품설명</td>
                <td><textarea name="p_content" id="p_content" cols="50" rows="10"></textarea></td>
            </tr>
            <tr>
                <td colspan="3" class="btnArea">
                    <button>등록</button>
                </td>
            </tr>
            </form>
        </table>
    </div>
</body>
<script>
    function check(){
        if($('#p_name').val()==""){
            console.log($('#p_name').val());
            alert("물품이름을 입력하세요!");
            return false;
        } else if($('#c_idx').val()==""){
            console.log($('#c_idx').val());
            alert("카테고리를 선택해주세요!");
            return false;
        } else if($('#p_price').val()==""){
            console.log($('#p_price').val());
            alert("가격을 입력하세요!");
            return false;
        }
        return true;
    }
</script>
</html>