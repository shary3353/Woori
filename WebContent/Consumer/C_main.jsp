<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	#mainBox{
	}

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




</style>
</head>
<body>
	<div id="wrap">
		<iframe src="../Include/navi.html" style="width: 100%" height="180px" scrolling="no" frameborder="none"></iframe>
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
					<table>
						<tr>
							<td rowspan="3" class="bestImage">
								<img src="../img/no-image.png" width="300px" height="400px"/>
							</td>
							<td class="bestName">
								베스트1 임시이름 입니다 임시이름
							</td>
						</tr>
						<tr>
							<td class="bestLike">
								❤️500
							</td>
						</tr>
						<tr>
							<td class="bestPrice">1.000.000 원</td>
						</tr>
					</table>
					<table>
						<tr>
							<td rowspan="3" class="bestImage">
								<img src="../img/no-image.png" width="300px" height="400px"/>
							</td>
							<td class="bestName">
								베스트1 임시이름 입니다 임시이름
							</td>
						</tr>
						<tr>
							<td class="bestLike">
								❤️500
							</td>
						</tr>
						<tr>
							<td class="bestPrice">1.000.000 원</td>
						</tr>
					</table>
					<table>
						<tr>
							<td rowspan="3" class="bestImage">
								<img src="../img/no-image.png" width="300px" height="400px"/>
							</td>
							<td class="bestName">
								베스트1 임시이름 입니다 임시이름
							</td>
						</tr>
						<tr>
							<td class="bestLike">
								❤️500
							</td>
						</tr>
						<tr>
							<td class="bestPrice">1.000.000 원</td>
						</tr>
					</table>
					<table>
						<tr>
							<td rowspan="3" class="bestImage">
								<img src="../img/no-image.png" width="300px" height="400px"/>
							</td>
							<td class="bestName">
								베스트1 임시이름 입니다 임시이름
							</td>
						</tr>
						<tr>
							<td class="bestLike">
								❤️500
							</td>
						</tr>
						<tr>
							<td class="bestPrice">1.000.000 원</td>
						</tr>
					</table>
					<table>
						<tr>
							<td rowspan="3" class="bestImage">
								<img src="../img/no-image.png" width="300px" height="400px"/>
							</td>
							<td class="bestName">
								베스트1 임시이름 입니다 임시이름
							</td>
						</tr>
						<tr>
							<td class="bestLike">
								❤️500
							</td>
						</tr>
						<tr>
							<td class="bestPrice">1.000.000 원</td>
						</tr>
					</table>
					<table>
						<tr>
							<td rowspan="3" class="bestImage">
								<img src="../img/no-image.png" width="300px" height="400px"/>
							</td>
							<td class="bestName">
								베스트1 임시이름 입니다 임시이름
							</td>
						</tr>
						<tr>
							<td class="bestLike">
								❤️500
							</td>
						</tr>
						<tr>
							<td class="bestPrice">1.000.000 원</td>
						</tr>
					</table>
				</div>
				
			</div>
		</div>
	</div>
    






</body>
<script>
</script>
</html>

















