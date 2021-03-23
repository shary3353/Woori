<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <title>seller noAnswer</title>
    <style>
        *{margin: 0; padding: 0;}
        #content{
            width: 100%;
            margin-top: 40px;
        }
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
            width: 100px;
            text-align: center;
        }
    </style>
</head>
<body>
    <jsp:include page="S_navi.jsp"/>
    
    <div id="content"> <!--본문 : 문의답변하기 -->
        <table>
            <tr>
                <td colspan="6" class="title">문의 답변하기</td>
            </tr>
            <tr>
                <td class="column-name">제목</td>
                <td colspan="5">문의드립니다</td>
            </tr>
            <tr>
                <td class="column-name" >질문자</td>
                <td>${dto.cid}</td>
                <td class="column-name" >질문분류</td>
                <td>${dto.category}</td>
                <td class="column-name" >문의날짜</td>
                <td>${dto.q_reg_date}</td>
            </tr>
            <tr>
                <td class="column-name">내용</td>
                <td colspan="5" style="white-space: pre-line;">${dto.content}</td>
            </tr>
            <form action="./sAnswer" method="post" onsubmit="return check()">
	           <tr>
	               <td class="column-name">답변하기</td>
	               <td colspan="5" style="text-align: center;">
	               		<input type="hidden" name="q_idx" value="${dto.q_idx}"/>
	                   <textarea name="s_answer" id="s_answer" cols="95" rows="10" id="s_answer" onKeyUp="fnChkByte(this,'1000')" style="resize: none;">${dto.s_answer}</textarea>
	               </td>
	           </tr>
	           <tr>
	               <td colspan="6" class="btnArea">
	                   <button type="submit">답변하기</button>
            </form>
	                   <button onclick="location.href='./sQAList'">목록으로가기</button>
	               </td>
	           </tr>
        </table>
    </div>
</body>
<script>
    function check(){
        if($('#s_answer').val()==""){
            console.log($('#s_answer').val());
            alert("답변을 입력하세요!");
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
    			rbyte += 2;//한글2Byte
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