<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>우리 양주</title>
    <link rel="stylesheet" type="text/css" href="../css/AdminStyle.css">
</head>
<body>
    <div id="Wrapper">
        <div id="Admin_Header">  <!-- 헤더 영역 -->
            <span id="Mark"><img id="Admin_Login_Notice" src="../img/admin_login_notice.png" alt="로그인알림"></span>
            <span id="Inform_Text">현재 <b>관리자</b> 아이디로 로그인중입니다.</span>
            <span id="Logout_Link"><a href="">로그아웃 ></a></span>
            <div id="Header_Logo"><img id="Main_Logo" src="../img/main_logo.png" alt="우리양주로고"></div>
        </div>  <!--헤더 닫음-->

        <nav>    <!-- 관리자 네비게이션-->
            <div class="Admin_Navlist">구매자회원관리</div>
            <div class="Admin_Navlist">판매자회원관리</div>
            <div class="Admin_Navlist">블랙리스트관리</div>
            <div class="Admin_Navlist">신고관리</div>

            <!-- <div class="Admin_Navlist"><a href="#">구매자회원관리</a></div>
        	<div class="Admin_Navlist"><a href="#">구매자회원관리</a></div>
       		<div class="Admin_Navlist"><a href="#">블랙리스트관리</a></div>
        	<div class="Admin_Navlist"><a href="#">신고관리</a></div> -->
        </nav>  <!--네비게이션 닫음-->

        <div id="Admin_Seller_Content"> <!--구매자관리컨텐츠-->
            <div id="Admin_Seller_List">    <!--구매자 리스트-->
                <table class="sellerlist" style="margin-left:auto; margin-right:auto;">
                    <tr>
                        <th colspan="6">구매 회원 목록</th>
                    </tr>
                    <tr>
                        <th>ID</th>
                        <th>신고 누적</th>
                        <th>블랙리스트 누적</th>
                        <th>블랙리스트 여부</th>
                        <th>가입날짜</th>
                        <th>블랙리스트 등록</th>
                    </tr>
                    <tr>
                        <td>testID01</td>
                        <td>0</td>
                        <td>6</td>
                        <td>true</td>
                        <td>2021.03.02</td>
                        <td>이미등록된회원입니다.</td>
                    </tr>
                    <tr>
                        <td>testID03</td>
                        <td>2</td>
                        <td>1</td>
                        <td>false</td>
                        <td>2021.03.02</td>
                        <td><button style="color: red;">등록</button></td>
                    </tr>
                </table>
            </div>  <!--리스트 닫음-->

            <div id="Admin_Searching">   <!--구매자자리스트 검색 부분-->
                <input type="text" name="searchingSeller" id="searchingSellerInput" placeholder="ID검색란"><button>검색</button>
            </div>  <!--구매자리스트 검색 닫음-->

            <div id="Admin_Seller_List_Paging"> <!--구매자리스트 페이징부분-->
                <span class="Page_Content">처음</span>
                <span class="Page_Content">이전</span>
                <span class="Page_Number">1</span>
                <span class="Page_Content">다음</span>
                <span class="Page_Content">마지막</span>
            </div>  <!--페이징 닫음-->

        </div>  <!--구매자관리 컨텐츠 닫음-->

        <div id="Admin_Footer"> <!--관리자 푸터-->
            푸터
        </div>  <!--관리자 푸터 닫음-->
    </div>  <!--Wrapper 닫음-->
</body>
</html>