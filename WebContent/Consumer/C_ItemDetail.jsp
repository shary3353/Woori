<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네비게이션을 만들어보자</title>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<style>

	*   {
        margin: 0;
        padding: 0;
    }

	body{
		min-width: 1920px;
	}

    #detail{
        width: 1920px;
        height: auto;
    }

    table{
        margin-top: 120px;
        margin-left: 60px;

    }
    table td{
        padding-left: 150px;
    }

    table #detailImg{
        padding: 5px;
    }

    .detailName{
        font-size: 50px;
        font-family: Arial, sans-serif;
        text-align: left;
        font-weight: 550;
    }

    .detailPrice{
        font-size: 37px;
        font-family: Arial, sans-serif;
        font-weight: 500;
        text-align: left;
        color: gray;
    }

    .detailContent{
        font-size: 23px;
        font-family: Arial, sans-serif;
    }

    .detailButtons{
        float: left;
    }
    .detailButtons button{
        background-color: #283744;
        color: white;
        width: 130px;
        height: 40px;
        font-size: 20px;
        font-weight: bold;
        margin-right: 25px;
        font-family: Arial, sans-serif;
    }

    
</style>
</head>
<body>
	<div id="wrap">
		<c:choose>
    		<c:when test="${sessionScope.cLoginID eq null}">
			<jsp:include page="../Include/loginnavi.jsp"></jsp:include>
    		</c:when>
   
    		<c:when test="${sessionScope.cLoginID ne null}">
			<jsp:include page="../Include/navi.jsp"></jsp:include>
    		</c:when>
    	</c:choose>	
    	<div id="detail">
    	<!-- 로그인 세션처리 아직 안해서 임시값 대체함-->
    	<!-- 좋아요 , 물품 상세보기 , 물품예약하기 세션처리해서 id값 넣어야함 -->
            <table>
                <tr>
                    <td rowspan="6" id="detailImg">
                    		<img id="itemImage" src="../Uploaded_Img/${dto.newFileName}"  alt="${dto.oriFileName}" width="400px" height="600px"/>
                    </td>
                    <td class="detailName" colspan="2">${dto.p_name}</td>
                </tr>
                <tr>
                    <td colspan="2" class="detailPrice">${dto.p_price} 원</td>
                </tr>
                <tr>
                    <td colspan="2" class="detailContent">
                        ${dto.p_content }
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="detailButtons">
                        <button id="questionButton" onclick="Question()">문의하기</button>
                        <a href="C_ItemReservation?p_idx=${dto.p_idx}" class="alink">
                        <button id="reservationButton">예약하기</button>
                        </a>
                        <!-- 로그인 세션처리 아직 안해서 임시값 대체함-->
                        <button id="likeButton"> &nbsp;좋아요</button>
                        <button id="dislikeButton" style="display: none">❤️ &nbsp;좋아요 취소</button>
                        <button id="wishlistButton" onclick="location.href='${pageContext.request.contextPath}/Consumer/addWishList?p_idx=${dto.p_idx}'">위시리스트</button>
                        <button id="reportButton" onclick="location.href='${pageContext.request.contextPath}/ServiceCenter/cReportForm?sid=${dto.sid}'">신고하기</button>
                    </td>
                </tr>
            </table>
        </div>

    </div>
</body>
<script>
	var msg = "${msg}";
		if(msg!=""){
			alert(msg);
		}
		//이미지 없을 때 이미지 대체
		var $itemImage = $("#itemImage");
		if($itemImage.attr('src') == "../Uploaded_Img/"){
			$itemImage.attr("src", "${pageContext.request.contextPath}/img/no-image.png");
		}
		
		
	 var loginid = '<%=(String)session.getAttribute("cLoginID")%>';	
	//좋아요 검사	
	function likeConfirm(){
		if(loginid != 'null'){
		$.ajax({
			type:'GET'
			,url:'likeConfirm'
			,data:{
				cid: loginid
				,pidx: '${dto.p_idx}'
			}
			,dataType:'JSON'
			,success:function(data){
					console.log(data.use);
					if(data.use){
						$("#likeButton").css("display","");
						$("#dislikeButton").css("display","none");
					}
					else{
						$("#likeButton").css("display","none");
						$("#dislikeButton").css("display","");
					}
			}
			,error:function(e){
				console.log(e);
			}
		})
		}else{
			console.log("아이디가 없습니다");
			$("#likeButton").css("display","none");
			$("#dislikeButton").css("display","none");
			$("#wishlistButton").css("display","none");
		}
	};

	likeConfirm();// 시작하자마자 like여부 검사

	var $reservationButton = $("#reservationButton");
	$reservationButton.click(function(){
		if(loginid.val() == null)
		alert("로그인이 필요한 서비스 입니다")
	});
	var $likeButton = $("#likeButton");
	var $dislikeButton = $("#dislikeButton");
	
	$likeButton.click(function(){
		
		var likeContent ={}
		likeContent.pidx = '${dto.p_idx}';
		likeContent.cid = loginid;
		console.log(likeContent.pidx,likeContent.cid);
		
		$.ajax({
			type:'GET'
			,url:'C_LikePlus'
			,data:likeContent
			,dataType:'JSON'
			,success:function(data){
					if(data.plus){
						$("#likeButton").css("display","none");
						$("#dislikeButton").css("display","");
					}else{
						console.log("좋아요 추가 실패입니다")
					}
			}
			,error:function(e){
				console.log(e);
			}
		})
	})

	$dislikeButton.click(function(){
		
		var likeContent ={}
		likeContent.pidx = '${dto.p_idx}';
		likeContent.cid = loginid;
		console.log(likeContent.pidx,likeContent.cid);
		
		$.ajax({
			type:'GET'
			,url:'C_LikeMinus'
			,data:likeContent
			,dataType:'JSON'
			,success:function(data){
					if(data.minus){
						$("#likeButton").css("display","");
						$("#dislikeButton").css("display","none");
					}else{
						console.log("좋아요 삭제 실패입니다")
					}
			}
			,error:function(e){
				console.log(e);
			}
		})
	})

	 function Question(){ 
         var uid = '<%=(String)session.getAttribute("cLoginID")%>';
		console.log(uid);
          if(uid=="null"){ 
             alert("로그인이 필요한 항목입니다.");
             location.replace("./C_login.jsp");
          }
          else{
             location.replace("../ServiceCenter/Q_write.jsp?p_name=${dto.p_name}&sId=${dto.sid}");
          }
    }  
	
</script>
</html>
