<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <title>seller Reservationlist</title>
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
       	.red{ color: red;}
        .green{ color: green;}
        .blue{ color: blue;}
    </style>
</head>
<body>
    <jsp:include page="S_navi.jsp"/>
    
    <div id="content"><!--본문 : 예약내역 - 리스트 -->
        <table>
            <tr>
                <td colspan="9" class="title">예약내역</td>
            </tr>
            <tr>
                <th>No</th>
                <th>상품번호</th>
                <th colspan="2">상품정보</th>
                <th>구매자</th>
                <th>예약일</th>
                <th>신청한 날짜</th>
                <th>예약현황</th>
                <th>신고하기</th>
            </tr>
            <c:forEach items="${list}" var="rlist">
            <tr>
                <td>${rlist.r_idx }</td>
                <td>${rlist.p_idx }</td>
                <td>
                <a href="sItemDetail?p_idx=${rlist.p_idx}">
               		<c:if test="${empty rlist.oriFileName}">
                    	<img src="../img/no-image.png" width="100px" height="100px">
                    </c:if>
					<c:if test="${not empty rlist.oriFileName}">
						<img src ="<%=request.getContextPath()%>/img/${rlist.oriFileName}" alt="${rlist.oriFileName}"
						 style="width:100px;"/>
	                </c:if>
	            </a>
	            </td>
                <td><a href="sItemDetail?p_idx=${rlist.p_idx}">${rlist.p_name }</a></td>
                <td>${rlist.cid }</td>
                <td>${rlist.visit_date }</td>
                <td>${rlist.reg_date }</td>
                <td>
                	<form action="updateResevationStatus">
                		<input type="hidden" name="r_idx" value="${rlist.r_idx }"/>
		                <span  class="chk">${rlist.status}</span><br/>
		                <select name="reservationState" id="">
		                    <option value="0">신청중</option>
		                    <option value="1">예약완료</option>
		                    <option value="2">상담완료</option>
		                </select><br/>
		                <button>예약현황변경</button>
	                </form>
	            </td>
                <td><button>신고하기</button></td>
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
<script>
	var chktext = $('.chk'); //진행중, 답변완료 색표시
	for (var i = 0; i < chktext.length; i++) {
	    if(chktext[i].textContent ==="신청중"){
	        chktext[i].classList.add('red');
	    } else if(chktext[i].textContent === "상담완료"){
	        chktext[i].classList.add('green');
	    } else if (chktext[i].textContent === "예약완료"){
	        chktext[i].classList.add('blue');
		}
	}
</script>
</html>