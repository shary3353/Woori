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

    #detail{
        width: 1920px;
        height: auto;
    }

    table{
        margin-top: 120px;
        margin-left: 60px;

    }
    table td{
        padding-left: 150px;
    }

    table #detailImg{
        padding: 5px;
    }

    .detailName{
        font-size: 50px;
        font-family: Arial, sans-serif;
        text-align: left;
        font-weight: 550;
    }

    .detailPrice{
        font-size: 37px;
        font-family: Arial, sans-serif;
        font-weight: 500;
        text-align: left;
        color: gray;
    }

    .detailContent{
        font-size: 23px;
        font-family: Arial, sans-serif;
    }

    .detailButtons{
        float: left;
    }
    .detailButtons button{
        background-color: #283744;
        color: white;
        width: 130px;
        height: 40px;
        font-size: 20px;
        font-weight: bold;
        margin-right: 25px;
        font-family: Arial, sans-serif;
    }

    
</style>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../Include/navi.jsp"></jsp:include>
    	<div id="detail">
            <table>
                <tr>
                    <td rowspan="6" id="detailImg"><img src="img/${dto.newFileName}"  alt="${dto.oriFileName} width="400px" height="600px"/></td>
                    <td class="detailName" colspan="2">${dto.p_name}</td>
                </tr>
                <tr>
                    <td colspan="2" class="detailPrice">${dto.p_price} 원</td>
                </tr>
                <tr>
                    <td colspan="2" class="detailContent">
                        ${dto.p_content }
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="detailButtons">
                        <button id="questionButton">문의하기</button>
                        <a href="C_ItemReservation?p_idx=${dto.p_idx}" class="alink">
                        <button id="reservationButton">예약하기</button>
                        </a>
                        <button id="likeButton">🤍&nbsp;좋아요</button>
                        <button id="wishlistButton">위시리스트</button>
                        <button id="reportButton">신고하기</button>
                    </td>
                </tr>
            </table>
        </div>

    </div>
</body>
<script>
var msg = "${msg}";
if(msg!=""){
	alert(msg);
}	
</script>
</html>
