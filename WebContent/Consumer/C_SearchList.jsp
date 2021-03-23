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
    .searchList table{
        border: 1px solid lightgray;
        border-collapse: collapse;
        width: 250px;
        height: 400px;
        margin-bottom: 70px;
        float: left;
        display: inline-block;
        margin-left: 60px;
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
		<c:choose>
    		<c:when test="${sessionScope.loginID eq null}">
			<jsp:include page="../Include/loginnavi.jsp"></jsp:include>
    		</c:when>
   
    		<c:when test="${sessionScope.loginID ne null}">
			<jsp:include page="../Include/navi.jsp"></jsp:include>
    		</c:when>
    	</c:choose>	
    	<!--  메인페이지 시작 -->
        <div id="searchBox">

            <div class="searchtitle">
                <span>"${msg}" </span>
            </div>
            <div class="searchList">
            
			<c:forEach items="${search}" var="search">
				<a href="C_itemDetail?p_idx=${search.p_idx}" class="alink">
                <table>
					<tr>
						<td class="listItemLike">❤️${search.likes}</td>
					</tr>
					<tr>
						<td class="listItemImg">
								<img id="itemImage" src="${pageContext.request.contextPath}/Uploaded_Img/${search.newFileName}" alt="${search.oriFileName}" width="200px"  height="300px"/>

						</td>
					</tr>
					<tr>
						<td class="listItemName">${search.p_name}</td>
					</tr>
                    <tr>
                        <td class="listItemPrice">${search.p_price} 원</td>
                    </tr>
				</table>
				</a>
            </c:forEach>
            </div>
        </div>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	
</script>
</html>

