<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
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
            text-align: right;
            border-bottom: 1px solid white;
        }
        button{
            padding: 4px;
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

    <div id="content"><!--본문 : 물품등록하기-->
        <form action="registItem" method="post" enctype="multipart/form-data" onsubmit="return check()">
        <table>
            <tr>
                <td colspan="3" class="title">물품등록하기</td>
            </tr>
            <tr>
                <td rowspan="5">
                    <input type="file" name="photo" id="imageFile" accept="image/*">
                </td>
                <td class="column-name" >물품이름</td>
                <td><input type="text" placeholder="물품이름을 입력해주세요." name="p_name" id="p_name" onKeyUp="fnChkByte(this,'100')"/></td>
            </tr>
            <tr>
                <td class="column-name" >카테고리</td>
                <td>
                    <select name="c_idx" id="c_idx">
                        <option value="">카테고리선택</option>
                        <option value="1">와인</option>
                        <option value="2">위스키</option>
                        <option value="3">꼬냑/브랜디</option>
                        <option value="4">보드카</option>
                        <option value="5">샴페인</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="column-name">가격</td>
                <td>
                    <input type="text" placeholder="가격(원)을 입력해주세요." name="p_price" id="p_price"
                    onKeyup="this.value=this.value.replace(/[^0-9]/g,''); onPriceChk(this);"/>원
                </td>
            </tr>
            <tr>
                <td class="column-name" >판매자</td>
                <td><%= session.getAttribute("loginID")%>
                <input type="hidden" value ="<%= session.getAttribute("loginID")%>" name="sid"/>
                </td>
            </tr>
            <tr>
                <td class="column-name" >상품설명</td>
                <td><textarea name="p_content" id="p_content" cols="50" rows="10" 
                onKeyUp="fnChkByte(this,'2000')"
                style="resize: none;"></textarea></td>
            </tr>
            <tr>
                <td colspan="3" class="btnArea">
                    <button>등록</button>
                </td>
            </tr>
        </table>
         </form>
    </div>
</body>
<script>
    function check(){
        if($('#p_name').val()==""){
            console.log($('#p_name').val());
            alert("물품이름을 입력하세요!");
            return false;
        } else if($('#c_idx').val()==""){
            console.log($('#c_idx').val());
            alert("카테고리를 선택해주세요!");
            return false;
        } else if($('#p_price').val()==""){
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
    /*
    var newValue;
    $("#p_content").on("propertychange change keyup paste input", function() {
        newValue = $(this).val();// 현재 변경된 데이터 셋팅
        //alert("텍스트 :: " + newValue);// 현재 실시간 데이터 표츌
        fnChkByte(this, '1000');
    });
    */
    
    function onPriceChk(obj){
    	var str = obj.value;
    	var str2 = "";
        if(str>=2100000000){
    		alert("21억이상 입력불가");
    		str2 =str.substr(0,9);
    		obj.value = str2;
    	}
    }
    
</script>
</html>