<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	
	
	request.setCharacterEncoding("UTF-8");
	
	String cid = request.getParameter("CId"); 
	String pw = request.getParameter("Pw"); 
	String name = request.getParameter("name"); 
	String gender = request.getParameter("gender"); 
	String email = request.getParameter("email");
	String BIRTHDAY = request.getParameter("BirthDay"); 
	String PHONE = request.getParameter("Phone");
	
	System.out.println(cid+"/"+pw+"/"+name+"/"+gender+"/"+email+"/"+ BIRTHDAY +"/"+PHONE);
	
	//DB 접속
	
	Context ctx = new InitialContext();
	
	DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
	
	Connection conn = ds.getConnection();
	

	String sql = "INSERT INTO member(CID,PW,NAME,GENDER,EMAIL,BIRTHDAY,PHONE)VALUES(?,?,?,?,?,?)";
	
	PreparedStatement ps = conn.prepareStatement(sql);
	//?대응
	ps.setString(1, cid);
	ps.setString(2, pw);
	ps.setString(3, name);
	ps.setString(4, gender);
	ps.setString(5, email);
	ps.setString(6, BIRTHDAY);
	ps.setString(7, PHONE);
	
	
	//쿼리 실행
	int success = ps.executeUpdate();
	
	//자원반납
	conn.close();
	ps.close();
%>
<script>
	<%if(success==0){%>// 다시 회원가입 페이지로 이동
		alert('회원가입 실패');//alert 를 사용하기 위해 script로 내려서 사용
		location.href="C_regist.jsp";
	<%}else{%>// 로그인 페이지로 이동
		alert('회원가입 완료');
		location.href="C_Login.jsp";
	<%}%>
</script>