<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입 페이지</h1>
	<form action="register.puppy">
		<ul>
			<li>아이디 <input type="text" name="userId">
			<li>이메일 <input type="email" name="email">
			<li>비밀번호<input type="password" name="password">
			<li>이름 <input type="text" name="name">
			<li>성별 <input type="text" name="gender">
			<li>생년월일 <input type="date" name="born">
			<li>프로필사진 <input type="file" name="profile">
		</ul>
		<input type="submit" value="가입하기">

	</form>
</body>
</html>