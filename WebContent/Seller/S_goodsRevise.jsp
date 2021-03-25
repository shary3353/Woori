<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <title>seller regist goods</title>
    <style>
        *{margin: 0; padding: 0;}
        #content{
            width: 100%;
            margin-top: 40px;
        }
        table{
            margin: auto;
        }
        table,th,td{
            border-bottom: 1px solid gray;
            border-collapse: collapse;
            margin-top: 5px;
            padding: 10pX;
        }
        .btnArea{
           
            text-align: center;
            border-bottom: 1px solid white;
        }
        button{
            margin-top: 30px;
            padding: 4px;
            width: 80px;
            height: 40px;
        }
        .title{
            border-top: 1px solid white;
            font-size: 24px;
            font-weight: 600;
            text-align: left;
        }
        .column-name {
            background-color: rgb(231, 231, 231);
            text-align: center;
        }
    </style>
</head>
<body>
    <jsp:include page="S_navi.jsp"/>

    <div id="content"><!--본문 : 등록 물품 수정하기-->
        <table>
            <tr>
                <td colspan="3" class="title">등록 물품 수정</td>
            </tr>
            <form action="sUpdateItem" method="post" enctype="multipart/form-data" onsubmit="return check()">
            <input type="hidden" value="${dto.p_idx}" name="p_idx"/>
	            <tr>
	                <td rowspan="5">
	                    <input type="file" name="photo" id="imageFile" accept="image/*"/>
	                </td>
	                <td class="column-name" >물품이름</td>
	                <td><input type="text" value="${dto.p_name}" name="p_name" id="p_name" onKeyUp="fnChkByte(this,'100')"/></td>
	            </tr>
	            <tr>
	                <td class="column-name" >카테고리</td>
	                <td>
	                    <select name="c_idx" id="c_idx">
	                        <option value="1" <c:if test="${dto.c_idx == 1}">selected</c:if>>와인</option>
	                        <option value="2" <c:if test="${dto.c_idx == 2}">selected</c:if>>위스키</option>
	                        <option value="3" <c:if test="${dto.c_idx == 3}">selected</c:if>>꼬냑/브랜디</option>
	                        <option value="4" <c:if test="${dto.c_idx == 4}">selected</c:if>>보드카</option>
	                        <option value="5" <c:if test="${dto.c_idx == 5}">selected</c:if>>샴페인</option>
	                    </select>
	                </td>
	            </tr>
	            <tr>
	                <td class="column-name" >가격</td>
	                <td>
	                    <input type="text" placeholder="가격(원)을 입력해주세요." value="${dto.p_price}" name ="p_price" id="p_price"
	                    onKeyup="this.value=this.value.replace(/[^0-9]/g,''); onPriceChk(this);"/>원
	                </td>
	            </tr>
	            <tr>
	                <td class="column-name" >판매자</td>
	                <td>${sessionScope.loginID}
	                	<input type="hidden" name="sid" value="${sessionScope.loginID}" />
	                </td>
	            </tr>
	            <tr>
	                <td class="column-name" >상품설명</td>
	                <td><textarea name="p_content" id="p_content" cols="50" rows="10" onKeyUp="fnChkByte(this,'2000')" style="resize: none;">${dto.p_content}</textarea></td>
	            </tr>
	            <tr>
	                <td colspan="3" class="btnArea">
	                    <button type="submit">저장</button>
	                </td>
	            </tr>
            </form>
        </table>
    </div>
</body>
<script>
    function check(){
        if($('#p_name').val()==""){
            console.log($('#p_name').val());
            alert("물품이름을 입력하세요!");
            return false;
        }else if($('#p_price').val()==""){
            console.log($('#p_price').val());
            alert("가격을 입력하세요!");
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
    
    function onPriceChk(obj){
    	var str = obj.value;
    	var str2 = "";
        if(str>=10000000){
    		alert("1천만원이상 입력불가");
    		str2 =str.substr(0,9);
    		obj.value = str2;
    	}
    }
</script>
</html>