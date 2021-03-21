<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.loginID eq null }">
	<script type="text/javascript">
	location.href = "./admin_Login.jsp";
</script>
</c:if>
<meta charset='utf-8'>
<span id="Mark"><a href="${pageContext.request.contextPath}/Admin/admin_Main.jsp"><img id="Admin_Login_Notice" src="${pageContext.request.contextPath}/img/admin_login_notice.png" alt="로그인알림" ></a></span>
<span id="Inform_Text">현재 로그인중인 <b>관리자</b> 아이디 : ${sessionScope.loginID}</span>
<span id="Logout_Link">
	<c:if test="${sessionScope.loginID ne null}">
		<span id="Logout_Link"><a href="${pageContext.request.contextPath}/logout">로그아웃 ></a></span>
	</c:if>
</span>
<div id="Header_Logo"><img id="Main_Logo" src="${pageContext.request.contextPath}/img/main_logo.png" alt="우리양주로고"></div>
