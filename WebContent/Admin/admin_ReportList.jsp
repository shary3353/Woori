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
            <%@include file="admin_Header.jsp" %>
        </div>  <!--헤더 닫음-->

        <nav>    <!-- 관리자 네비게이션-->
            <%@include file="admin_Nav.jsp" %>
        </nav>  <!--네비게이션 닫음-->

        <div id="Admin_Seller_Content"> <!--판매자관리컨텐츠-->
            <div id="Admin_Seller_List">    <!--판매자 리스트-->
                <table class="sellerlist" style="margin-left:auto; margin-right:auto;">
                    <tr>
                        <th colspan="7">블랙리스트 목록</th>
                    </tr>
                    <tr>
                        <th>번호</th>
                        <th>신고분류</th>
                        <th>신고제목</th>
                        <th>신고자</th>
                        <th>신고대상자</th>
                        <th>신고날짜</th>
                        <th>신고상태</th>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>기타</td>
                        <td>억울해요</td>
                        <td>판매자 123546789</td>
                        <td>구매자 zz123</td>
                        <td>2021.03.07</td>
                        <td><select name="reportstate" id="">
                            <option value="100">처리중</option>
                            <option value="200">처리완료</option>
                        </select><br><button>신고상태저장</button></td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>기타</td>
                        <td>신고합니다.</td>
                        <td>구매자 123546789</td>
                        <td>판매자 zz123</td>
                        <td>2021.03.07</td>
                        <td><select name="reportstate" id="">
                            <option value="100">처리중</option>
                            <option value="200">처리완료</option>
                        </select><br><button>신고상태저장</button></td>
                    </tr>
                </table>
            </div>  <!--리스트 닫음-->

            <div id="Admin_Searching">   <!--판매자리스트 검색 부분-->
                <input type="text" name="searchingSeller" id="searchingSellerInput" placeholder="ID검색란"><button>검색</button>
            </div>  <!--판매자리스트 검색 닫음-->

            <div id="Admin_Seller_List_Paging"> <!--판매자리스트 페이징부분-->
                <span class="Page_Content">처음</span>
                <span class="Page_Content">이전</span>
                <span class="Page_Number">1</span>
                <span class="Page_Content">다음</span>
                <span class="Page_Content">마지막</span>
            </div>  <!--페이징 닫음-->

        </div>  <!--판매자관리 컨텐츠 닫음-->

        <div id="Admin_Footer"> <!--관리자 푸터-->
            푸터
        </div>  <!--관리자 푸터 닫음-->
    </div>  <!--Wrapper 닫음-->
</body>
</html>