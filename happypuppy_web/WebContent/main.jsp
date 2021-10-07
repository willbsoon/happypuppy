<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%if (session.getAttribute("name")!=null){
	%>
<h1>로그인성공</h1>
<%=session.getAttribute("name") %>님 환영합니다.
<a href="logout.puppy">로그아웃</a>

<form action="recommend.puppy">
검색시작날짜 : <input type="date" value ="2017-07-18" name="bgnde">
검색종료날짜 : <input type="date" value ="2017-07-20" name="endde">
검색 동물 : <input type="text"  value ="417000" name="upkind">
검색 도시 : <input type="text" value="6110000" name="upr_cd">
<input type="submit">
</form>



<%	} %>

<%if (session.getAttribute("name")==null){
	%>
<a href="login.jsp">로그인창으로 이동</a>	
<%} %>
</body>
</html>