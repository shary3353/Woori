<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네비게이션을 만들어보자</title>
<style>

	*   {
        margin: 0;
        padding: 0;
    }

	body{
		min-width: 1920px;
	}
    #searchBox{
		width: 1920px;
		height: auto;
	}
    #searchBox .searchtitle{
        width: 1920px;
		height: 200px;
		float: left;
    }
    #searchBox .searchtitle span{
        margin-top: 120px;
		font-size: 25px;
		margin-left: 60px;
		font-family: Arial, sans-serif;
		font-weight: 800;
		color: #283744;
		display: inline-block;
	}
    .searchList{
        display: inline-block;
        margin-left: 60px;
    }
    .searchList table{
        border: 1px solid lightgray;
        border-collapse: collapse;
        width: 250px;
        height: 400px;
        margin-bottom: 70px;
    }
    .searchList table .listItemLike{
        font-family: Arial, sans-serif;
		font-weight: 520;
		font-size: 18px;
		text-align: right;
		padding-right: 10px;
    }

    .searchList table .listItemImg{
        text-align: center;
    }

    .searchList table .listItemName{
        font-family: Arial, sans-serif;
		font-weight: 520;
		font-size: 20px;
		text-align: center;
		padding-right: 10px;
        font-weight: 800;
    }

    .searchList table .listItemPrice{
        font-family: Arial, sans-serif;
		font-weight: 520;
		font-size: 18px;
		text-align: center;
		padding-right: 10px;
        font-weight: 700;
    }






</style>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../Include/navi.jsp"></jsp:include>
    	<!--  메인페이지 시작 -->
        <div id="searchBox">

            <div class="searchtitle">
                <span>...에 대한 검색 상품입니다.</span>
            </div>

            <div class="searchList">
                <table>
					<tr>
						<td class="listItemLike">❤️50</td>
					</tr>
					<tr>
						<td class="listItemImg"><img src="../img/no-image.png" width="200" height="300"/></td>
					</tr>
					<tr>
						<td class="listItemName">임시 검색상품 이름</td>
					</tr>
                    <tr>
                        <td class="listItemPrice">1.000.000 원</td>
                    </tr>
				</table>
            </div>

            <div class="searchList">
                <table>
					<tr>
						<td class="listItemLike">❤️50</td>
					</tr>
					<tr>
						<td class="listItemImg"><img src="../img/no-image.png" width="200" height="300"/></td>
					</tr>
					<tr>
						<td class="listItemName">임시 검색상품 이름</td>
					</tr>
                    <tr>
                        <td class="listItemPrice">1.000.000 원</td>
                    </tr>
				</table>
            </div>

            <div class="searchList">
                <table>
					<tr>
						<td class="listItemLike">❤️50</td>
					</tr>
					<tr>
						<td class="listItemImg"><img src="../img/no-image.png" width="200" height="300"/></td>
					</tr>
					<tr>
						<td class="listItemName">임시 검색상품 이름</td>
					</tr>
                    <tr>
                        <td class="listItemPrice">1.000.000 원</td>
                    </tr>
				</table>
            </div>

            <div class="searchList">
                <table>
					<tr>
						<td class="listItemLike">❤️50</td>
					</tr>
					<tr>
						<td class="listItemImg"><img src="../img/no-image.png" width="200" height="300"/></td>
					</tr>
					<tr>
						<td class="listItemName">임시 검색상품 이름</td>
					</tr>
                    <tr>
                        <td class="listItemPrice">1.000.000 원</td>
                    </tr>
				</table>
            </div>

            <div class="searchList">
                <table>
					<tr>
						<td class="listItemLike">❤️50</td>
					</tr>
					<tr>
						<td class="listItemImg"><img src="../img/no-image.png" width="200" height="300"/></td>
					</tr>
					<tr>
						<td class="listItemName">임시 검색상품 이름</td>
					</tr>
                    <tr>
                        <td class="listItemPrice">1.000.000 원</td>
                    </tr>
				</table>
            </div>

            <div class="searchList">
                <table>
					<tr>
						<td class="listItemLike">❤️50</td>
					</tr>
					<tr>
						<td class="listItemImg"><img src="../img/no-image.png" width="200" height="300"/></td>
					</tr>
					<tr>
						<td class="listItemName">임시 검색상품 이름</td>
					</tr>
                    <tr>
                        <td class="listItemPrice">1.000.000 원</td>
                    </tr>
				</table>
            </div>

            <div class="searchList">
                <table>
					<tr>
						<td class="listItemLike">❤️50</td>
					</tr>
					<tr>
						<td class="listItemImg"><img src="../img/no-image.png" width="200" height="300"/></td>
					</tr>
					<tr>
						<td class="listItemName">임시 검색상품 이름</td>
					</tr>
                    <tr>
                        <td class="listItemPrice">1.000.000 원</td>
                    </tr>
				</table>
            </div>
        </div>
    </div>
</body>
<script>
</script>
</html>
