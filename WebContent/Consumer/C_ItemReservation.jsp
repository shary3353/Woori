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
		<jsp:include page="../Include/navi.jsp"></jsp:include>
    	<div id="reservation">
    	
    	<form action="C_Reservation" method="post">
            <table>
                <tr>
                    <td rowspan="8" id="ReservationImg"><img src="${pageContext.request.contextPath}/Uploaded_Img/${dto.newFileName}" alt="${dto.oriFileName}" width="400px"  height="600px"/></td>
                    <td class="ReservationTitle" colspan="2">예약하기</td>
                </tr>

                <tr>
                    <td><li class="ReservationName">상품명 : </li></td>
                    <td class="ReservationContent">${dto.p_name}
                    </td>
                </tr>
                <tr>
                    <td><li class="ReservationName">상품번호 : </li></td>
                    <td class="ReservationContent">
                    <input type="text" name="p_idx" value="${dto.p_idx}" style="border:none;  font-size: 20px;  padding-top: 8px;"/>
                    </td>
                    
                </tr>

                <tr>
                    <td><li class="ReservationName">가격 : </li></td>
                    <td class="ReservationContent">${dto.p_price} 원</td>
                </tr>
                
                <tr>
                    <td><li class="ReservationName">판매자 : </li></td>
                    <td class="ReservationContent">
                    	<input type="text" name="sid"   value="${dto.sid}" readonly  style="border:none;  font-size: 20px;  padding-top: 8px;"/>
                    </td>
                    <td><li class="ReservationName">구매자 : </li></td>
                    <td class="ReservationContent">
                    	<!-- 로그인 세션처리 안해서 구매자 id 임시 지정 -->
                    	<input type="text" name="cid"   value="${sessionScope.cLoginID}" readonly  style="border:none;  font-size: 20px;  padding-top: 8px;"/>
                    </td>
                </tr>
                <tr>
                    <td><li class="ReservationName">방문날짜 : </li></td>
                    <td><input type="text" name="visitdate" id="ReservationDate" readonly 
                    style="background: url(${pageContext.request.contextPath}/img/calendar.png) no-repeat 2px 2px;
                    background-size:25px " /></td>
                </tr>
                </ol>
                <tr>
                    <td colspan="3">
                    		<button id="ReservationButton">예약하기</button>
                    </td>
                </tr>
            </table>
            </form>
            
        </div>

    </div>
</body>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script>
	var msg = "${msg}";
	
	if(msg!=""){
		alert(msg);
	}

	var $date = $("#ReservationDate");
	console.log($date);
	
	$(function() {
            //모든 datepicker에 대한 공통 옵션 설정
            $.datepicker.setDefaults({
                dateFormat: 'yy-mm-dd' //Input Display Format 변경
                ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
                ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
                ,changeYear: true //콤보박스에서 년 선택 가능
                ,changeMonth: true //콤보박스에서 월 선택 가능                
                ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
                ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
                ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
                ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
                ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
                ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
                ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
                ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
                ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
                ,minDate: "0M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
                ,maxDate: "+1Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)        
               	,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시
            });
	})
            //input을 datepicker로 선언
           $date.datepicker();                    
	
	
	var $ReservationButton = $("#ReservationButton");
	console.log($ReservationButton);
	
	$ReservationButton.click(function(){
		if($date.val() == ""){
			alert('날짜를 입력해 주세요!');
			return false;
		}
	});
	
</script>
</html>
