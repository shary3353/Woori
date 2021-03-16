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
            text-align: center;
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
                #List_Paging{
            margin: 80px;
            text-align: center;
        }
        .Page_Content{
            padding: 5px 5px;
            background-color: #F6F6F6 ;
        }
        .Page_Content a{
        	color: black; text-decoration: none;
            padding: 5px 5px;
            background-color: #F6F6F6 ;
        }
        .Page_Number{
            padding: 5px 10px;
            background-color: #c0e1eb;
        }
    </style>
</head>
<body>
    <jsp:include page="S_navi.jsp"/>

    <div id="content"><!--본문 : 등록 물품 리스트 -->
        <table>
            <tr>
                <td colspan="6" class="title">등록 물품 리스트</td>
                <td><button onclick = "location.href='S_registGoods.jsp'">물품등록</button></td>
            </tr>
            <tr>
                <th>물품 번호</th>
                <th>카테고리</th>
                <th>물품명</th>
                <th>물품사진</th>
                <th>가격</th>
                <th>수정</th>
                <th>삭제</th>
            </tr>
            
            <c:forEach items="${list}" var="item">
            <tr>
                <td>${item.p_idx}</td>
                <td>${item.category}</td>
                <td id="registContent">
                    <p><a href="sItemDetail?p_idx=${item.p_idx}">${item.p_name}</a></p>
                </td>
                <td>
                    <c:if test="${empty item.newFileName}">
                    	<img src="../img/no-image.png" width="100px" height="100px">
                    </c:if>
					<c:if test="${not empty item.newFileName}">
						<img src ="photo/${item.newFileName}" alt="${item.oriFileName}"
						style="width:100px;"/>
					</c:if>
                    <!-- <img src ="photo/${dto.newFileName}" alt="${dto.oriFileName}"/> -->
                    <!--	server.xml에 추가 할 것 
			      			<Context docBase="D:\MVC\Woori\WebContent\Uploaded_Img" path="/Woori/Seller/photo"/>
			         -->
                </td>
                <td>${item.p_price} 원</td>
                <td><button id="reviseButton" onclick="location.href='./sUpdateItemForm?p_idx=${item.p_idx}'">수정하기</button></td>
                <td><button id="delButton" onclick="location.href='./sDeleteItem?p_idx=${item.p_idx}'">❌</button></td>
            </tr>
            </c:forEach>
        </table>
        
        <div id="List_Paging"> <!--페이징부분-->
            <span class="Page_Content">
            	<a href="./sItemList?page=1">처음</a>
            </span>
            <span class="Page_Content">
            	<c:if test="${currPage == 1}">이전</c:if>
				<c:if test="${currPage > 1}">
					<a href="./sItemList?page=${currPage-1}">이전</a>
				</c:if>	
            </span>
            <span class="Page_Number">1</span>
            <span class="Page_Content">
           		<c:if test="${currPage == maxPage}">다음</c:if>
				<c:if test="${currPage < maxPage}">
					<a href="./sItemList?page=${currPage+1}">다음</a>	
				</c:if>
            </span>
            <span class="Page_Content">
            	<a href="./sItemList?page=${maxPage}">마지막</a>
            </span>
        </div>
    </div>  
</body>
</html>