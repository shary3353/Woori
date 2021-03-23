<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
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
		        <form action="sReport" method="post" onsubmit="return check()">
                <table>
                	<tr>
                		<th class="title" colspan="2">신고하기</th>
                	</tr>
                    <tr>
                        <th class="column-name">신고제목</th>
                        <td><input type="text" name="subject" placeholder="신고제목을 입력해주세요." id="subject" onKeyUp="fnChkByte(this,'50')"></td>
                    </tr>
                    <tr>
                        <th class="column-name">신고자</th>
                        <td ><input type="text" name="reporter_id" value="${sessionScope.loginID}" readonly/></td> 
                    </tr>
                    <tr>
                        <th class="column-name">신고대상자</th>
                        <td><input type="text" name="target_id" value="${target_id}" readonly></td>
                    </tr>  
                    <tr>
                        <th class="column-name">신고 카테고리</th>
                        <td>
                            <select name="category" id="category">
                                <option value="">신고카테고리</option>
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
                        <td><textarea name="content" cols="85" rows="10" placeholder="신고내용을 입력해주세요." id="content" onKeyUp="fnChkByte(this,'1000')" style="resize: none;"></textarea></td>
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
    <script>
        function check(){
            if($('#subject').val()==""){
                console.log($('#subject').val());
                alert("제목을 입력하세요!");
                return false;
            } else if($('#category').val()==""){
                console.log($('#category').val());
                alert("카테고리를 선택해주세요!");
                return false;
            } else if($('#content').val()==""){
                console.log($('#content').val());
                alert("내용을 입력하세요!");
                return false;
            }
            return true;
        }
        
        function fnChkByte(obj, maxByte){//글자 bytes 제한
            var str = obj.value;
            var str_len = str.length;

            var rbyte = 0;
            var rlen = 0;
            var one_char = "";
            var str2 = "";

            for(var i=0; i<str_len; i++){
                one_char = str.charAt(i);
                if(escape(one_char).length > 4){
        			rbyte += 3;//한글3Byte
        		} else {
        		    rbyte++;//영문 등 나머지 1Byte
                }
        		if(rbyte <= maxByte){
                    rlen = i+1;//return할 문자열 갯수
                }
             }
             if(rbyte > maxByte){
          		// alert("한글 "+(maxByte/2)+"자 / 영문 "+maxByte+"자를 초과 입력할 수 없습니다.");
          		alert("최대 " + maxByte + "byte를 초과할 수 없습니다.")
          		str2 = str.substr(0,rlen);//문자열 자르기
          		obj.value = str2;
          		fnChkByte(obj, maxByte);
             }else{
                //document.getElementById('byteInfo').innerText = rbyte;
             }
        }
    </script>
</html>