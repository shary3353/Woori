<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>디테일</title>
<style>

	*   {
        margin: 0;
        padding: 0;
    }

	body{
		min-width: 1920px;
	}
    #reservation{
        width: 1920px;
        height: auto;
    }

    table{
        margin-top: 120px;
        margin-left: 60px;
    }

    table td{
        padding: 10px;
    }
    
    table #ReservationImg{
        padding: 5px;
    }

    table .ReservationTitle{
        padding-left: 150px;
        font-size: 40px;
        font-family: Arial, sans-serif;
        text-align: left;
        font-weight: 550;
    }

    .ReservationName{
        padding-left: 150px;
        font-size: 25px;
        font-family: Arial, sans-serif;
        font-weight: 550;
    }

    td.ReservationContent{
        text-align: left;
        padding-left: 20px;
        padding-top: 8px;
        font-size: 20px;
        font-family: Arial, sans-serif;
        font-weight: 480;
    }
    #ReservationCount{
        margin-left: 20px;
        width: 150px;
        height: 30px;
        font-size: 20px;
        text-align: center;
        border: 2px solid black;
    }
    #ReservationDate{
        margin-left: 20px;
        width: 150px;
        height: 30px;
        font-size: 15px;
        font-weight: 480;
        text-align: center;
        border: 2px solid black;
    }

    #ReservationButton{
        background-color:#283744;
        border: 3px solid #283744;
        color: white;
        width: 150px;
        height: 40px;
        font-size: 20px;
        font-weight: 600;
        cursor: pointer;
        margin-left: 500px;
    }
    #ReservationButton:hover{
        background-color: #2c3e50;
    }



    
</style>
</head>
<body>
	<div id="wrap">
		<iframe src="Include/navi.html" style="width: 100%" height="180px" scrolling="no" frameborder="none"></iframe>
    	<div id="reservation">
            <table>
                <tr>
                    <td rowspan="8" id="ReservationImg"><img src="img/${dto.newFileName}" alt="${dto.oriFileName} width="400px" height="600px"/></td>
                    <td class="ReservationTitle" colspan="2">예약하기</td>
                </tr>
                <ol>
                <tr>
                    <td><li class="ReservationName">상품명 : </li></td>
                    <td class="ReservationContent">${dto.p_name}</td>
                </tr>
                <tr>
                    <td><li class="ReservationName">상품번호 : </li></td>
                    <td class="ReservationContent">${dto.p_idx}</td>
                </tr>

                <tr>
                    <td><li class="ReservationName">가격 : </li></td>
                    <td class="ReservationContent">${dto.p_price} 원</td>
                </tr>
                
                <tr>
                    <td><li class="ReservationName">판매자 : </li></td>
                    <td class="ReservationContent">${dto.sid}</td>
                    <td><li class="ReservationName">구매자 : </li></td>
                    <td class="ReservationContent">byebyebye</td>
                </tr>
                <tr>
                    <td><li class="ReservationName">수량선택 : </li></td>
                    <td><input type="number" min="1" max"1000" step="1" id="ReservationCount"></td>

                    <td><li class="ReservationName">예약날짜 : </li></td>
                    <td><input type="date" id="ReservationDate"></td>
                </tr>
                </ol>
                <tr>
                    <td colspan="3"><button id="ReservationButton">예약하기</button></td>
                </tr>
            </table>
        </div>

    </div>
</body>
<script>
</script>
</html>
