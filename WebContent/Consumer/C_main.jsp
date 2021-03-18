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

	/* 메인페이지 */
	#mainBox{
		width: 1920px;
		height: auto;
	}
	/* 메인타이틀 */
	.mainTitle{
		width: 1920px;
		height: 200px;
		float: left;
	}


	#mainBox .mainTitle p{
		margin-top: 120px;
		font-size: 35px;
		margin-left: 60px;
		font-family: Arial, sans-serif;
		font-weight: 800;
		color: #283744;
		display: inline-block;
	}

	.mainTitle span{
		margin-left: 12px;
		background-color: red;
		border: 1px solid red;
		color: #ffffff;
		font-family: Arial, sans-serif;
		font-weight: 600;
		border-radius: 30%;
		padding: 10px;
		font-size: 20px;
	}
	/* 메인컨텐츠 */

	.mainContent1{
		width: 1920px;
		height: auto;
		display: inline-block;
	}

	.bestItem{
		
		margin-left: 60px;
		margin-top:  50px;
	}

	table{
		display: inline-block;
		float: left;
		margin-right: 110px;
		margin-bottom: 100px;
		width: 500px;
		height: 400px;
		border: 1px solid black;
		border-collapse: collapse;
	}
	table .bestImage{
		width: 300px;
	}

	table .bestName{
		vertical-align: top;
		padding-top: 10px;
		width: 300px;
		font-family: Arial, sans-serif;
		font-weight: 800;
		text-align: center;
		font-size: 25px;
	}

	table .bestLike{
		font-family: Arial, sans-serif;
		font-weight: 520;
		font-size: 23px;
		text-align: right;
		padding-right: 10px;
	}

	table .bestPrice{
		text-align: center;
		font-size: 25px;
		font-weight: 600;
		font-family: Arial, sans-serif;
	}
	
	.alink{
		text-decoration: none;
	}

</style>
</head>

<body>
	<div id="wrap">
		<c:choose>
    		<c:when test="${session.loginId eq null}">
			<jsp:include page="../Include/loginnavi.jsp"></jsp:include>
    		</c:when>
   
    		<c:when test="${session.loginId ne null}">
			<jsp:include page="../Include/navi.jsp"></jsp:include>
    		</c:when>
    	</c:choose>	
    	<!--  메인페이지 시작 -->
		<!-- 메인컨텐츠 박스 -->
		<div id="mainBox">
		<!-- 상단 타이틀 -->
			<div class="mainTitle">
				<p>인기상품!!</p>
				<span>HOT</span>
			</div>
			<div class="mainContent1">
			
				<div class="bestItem">
					<c:forEach items="${list1}" var="best">
					<a href="C_itemDetail?p_idx=${best.p_idx}" class="alink">
					<table>
						<tr>
							<td rowspan="3" class="bestImage" >
								<img src="Uploaded_Img/${best.newFileName}"  alt="${best.oriFileName}"width="300px" height="400px" />
							</td>
							<td class="bestName">
								${best.p_name}
							</td>
						</tr>
						<tr>
							<td class="bestLike">
								❤️${best.likes}
							</td>
						</tr>
						<tr>
							<td class="bestPrice">${best.p_price} 원</td>
						</tr>
					</table>
					</a>
					</c:forEach>
					
					<c:forEach items="${list2}" var="best2">
					<a href="C_itemDetail?p_idx=${best2.p_idx}" class="alink">
					<table>
						<tr>
							<td rowspan="3" class="bestImage">
								<img src="Uploaded_Img/${best2.newFileName}"  alt="${best2.oriFileName}"width="300px" height="400px"/>
							</td>
							<td class="bestName">
								${best2.p_name}
							</td>
						</tr>
						<tr>
							<td class="bestLike">
								❤️ ${best2.likes}
							</td>
						</tr>
						<tr>
							<td class="bestPrice">
								${best2.p_price} 원
							</td>
						</tr>
					</table>
					</a>
					</c:forEach>
					
					<c:forEach items="${list3}" var="best3">
					<a href="C_itemDetail?p_idx=${best3.p_idx}" class="alink">
					<table>
						<tr>
							<td rowspan="3" class="bestImage">
								<img src="Uploaded_Img/${best3.newFileName}"  alt="${best3.oriFileName}"width="300px" height="400px"/>
							</td>
							<td class="bestName">
								${best3.p_name}
							</td>
						</tr>
						<tr>
							<td class="bestLike">
								❤️ ${best3.likes}
							</td>
						</tr>
						<tr>
							<td class="bestPrice">${best3.p_price}원</td>
						</tr>
					</table>
					</a>
					</c:forEach>
					
					<c:forEach items="${list4}" var="best4">
					<a href="C_itemDetail?p_idx=${best4.p_idx}" class="alink">
					<table>
						<tr>
							<td rowspan="3" class="bestImage">
								<img src="Uploaded_Img/${best4.newFileName}"  alt="${best4.oriFileName}"width="300px" height="400px"/>
							</td>
							<td class="bestName">
									${best4.p_name}
							</td>
						</tr>
						<tr>
							<td class="bestLike">
								❤️ ${best4.likes}
							</td>
						</tr>
						<tr>
							<td class="bestPrice">${best4.p_price} 원</td>
						</tr>
					</table>
					</a>
					</c:forEach>
					
					<c:forEach items="${list5}" var="best5">
					<a href="C_itemDetail?p_idx=${best5.p_idx}" class="alink">
					<table>
						<tr>
							<td rowspan="3" class="bestImage">
								<img src="Uploaded_Img/${best5.newFileName}"  alt="${best5.oriFileName}"width="300px" height="400px"/>
							</td>
							<td class="bestName">
								${best5.p_name}
							</td>
						</tr>
						<tr>
							<td class="bestLike">
								❤️ ${best5.likes}
							</td>
						</tr>
						<tr>
							<td class="bestPrice">${best5.p_price} 원</td>
						</tr>
					</table>
					</a>
					</c:forEach>
					
					<c:forEach items="${list6}" var="best6">
					<a href="C_itemDetail?p_idx=${best6.p_idx}" class="alink">
					<table>
						<tr>
							<td rowspan="3" class="bestImage">
								<img src="Uploaded_Img/${best6.newFileName}"  alt="${best6.oriFileName}"width="300px" height="400px"/>
							</td>
							<td class="bestName">
								${best6.p_name}
							</td>
						</tr>
						<tr>
							<td class="bestLike">
								❤️ ${best6.likes}
							</td>
						</tr>
						<tr>
							<td class="bestPrice">${best6.p_price} 원</td>
						</tr>
					</table>
					</a>
					</c:forEach>
				</div>
				
			</div>
		</div>
	</div>

</body>
<script>
</script>
</html>


















