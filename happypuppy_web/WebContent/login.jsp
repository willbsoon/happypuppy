<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <%@include file="" %> --%>
	<h1>로그인 페이지</h1>
	<form action="login.puppy" method="post">
		<ul>
			<li>이메일 :<input type="text" name="email">
			<li>비밀번호 :<input type="password" name="password">
		</ul>
		<input type="submit" value="로그인">
	</form>
</body>
</html>