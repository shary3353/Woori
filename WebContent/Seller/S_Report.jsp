<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        *{margin: 0; padding: 0;}
  
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
         .title{
            border-top: 1px solid white;
            font-size: 24px;
            font-weight: 600;
            text-align: left;
        }
        .column-name {
            background-color: rgb(231, 231, 231);
            width: 150px;
            text-align: center;
        }
        .btnArea{
            text-align: right;
            border-bottom: 1px solid white;
        }
        button{
            padding: 4px;
        }
    </style>
</head>
<body>
    <jsp:include page="S_navi.jsp"/>
    
    	<div>
		        <form action="sReport" method="get">
                <table>
                	<tr>
                		<th class="title" colspan="2">신고하기</th>
                	</tr>
                    <tr>
                        <th class="column-name">신고제목</th>
                        <td><input type="text" name="subject" placeholder="신고제목을 입력해주세요."></td>
                    </tr>
                    <tr>
                        <th class="column-name">신고자</th>
                        <td ><input type="text" name="reporter_id" value="${sessionScope.loginId}" readonly/></td> 
                    </tr>
                    <tr>
                        <th class="column-name">신고대상자</th>
                        <td><input type="text" name="target_id" value="${target_id}" readonly></td>
                    </tr>  
                    <tr>
                        <th class="column-name">신고 카테고리</th>
                        <td>
                            <select name="category">
                                <option value="100">상품관련</option>
                                <option value="200">판매자관련</option>
                                <option value="300">구매자관련</option>
                                <option value="400">기타</option>
                            </select>
                            <span style="color:lightgray;">해당 카테고리를 선택해주세요.</span>
                        </td>
                    </tr>
                    <tr>
                        <th class="column-name">신고내용</th>
                        <td><textarea name="content" cols="85" rows="10" placeholder="신고내용을 입력해주세요."></textarea></td>
                    </tr>
                    <tr>
                    	<td colspan="2" class="btnArea">
	                    	<button type="submit">신고하기</button>
                    	</td>
                    </tr>
                </table>
		        </form>
        </div>
    </body>
</html>