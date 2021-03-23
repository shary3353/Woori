<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Goods Info</title>
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
            padding: 5px;
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

    <div id="content"><!--본문 : 등록된물품상세보기 -->
        <table>
            <tr>
                <td colspan="3" class="title">등록된 물품 상세보기</td>
            </tr>
            <tr>
                <td rowspan="5" style="width: 300px;">
                    <c:if test="${empty dto.newFileName}">
                    	<img src="../img/no-image.png" width="300px">
                    </c:if>
					<c:if test="${not empty dto.newFileName}">
						<!-- 
						<img src ="photo/${dto.newFileName}" alt="${dto.oriFileName}"
						style="width:300px;"/>
						-->
						<img src ="../Uploaded_Img/${dto.newFileName}" alt="${dto.oriFileName}"
						style="width:300px;"/>
					</c:if>
                    <!-- <img src ="photo/${dto.newFileName}" alt="${dto.oriFileName}"/> -->
                    <!--	server.xml에 추가 할 것 
			      			<Context docBase="D:\MVC\Woori\WebContent\Uploaded_Img" path="/Woori/Seller/photo"/>
			         -->
                </td>
                <td class="column-name" >물품명</td>
                <td>${dto.p_name}</td>
            </tr>
            <tr>
                <td class="column-name">상품분류</td>
                <td>${dto.category}</td>
            </tr>
            <tr>
                <td class="column-name">가격</td>
                <td>${dto.p_price}원</td>
            </tr>
            <tr>
                <td class="column-name">예약수</td>
                <td style="color: red;">${r_count}건</td>
            </tr>
            <tr>
                <td class="column-name">내용</td>
                <td style="white-space: pre-line;">${dto.p_content}</td>
            </tr>
            <tr>
                <td colspan="3" class="btnArea">
                    <button onclick="location.href='./sItemList'">물품목록으로가기</button>
                    <form action="sUpdateItemForm" method="post" style='display:inline;'>
                		<input type="hidden" name="p_idx" value="${dto.p_idx }"/>
	                	<button id="reviseButton">수정하기</button>
                	</form>
                </td>
            </tr>
        </table>
    </div>  
</body>
</html>