<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<% String name = (String)request.getAttribute("name"); %>
</head>
<script src="../js/jquery.min.js"></script>
<script>

	$(function(){
		alert("환영합니다\n 로그인 해주세요");
		location.replace("./index.jsp"); 
	});
	
</script>
<body>

</body>
</html>