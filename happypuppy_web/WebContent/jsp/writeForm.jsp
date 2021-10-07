<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BBS Write</title>
</head>
<body>
<%@page import="java.util.ArrayList"%>
<%@page import="team.project.dto.Comment"%>
<%@page import="team.project.dto.User"%>
	<!-- enctype="application/x-www-form-urlencoded" 속성이 default로 잡혀있음 -->
	<!-- <form action="/bbs/write.bbs" method="post" enctype="application/x-www-form-urlencoded"> -->
	<form action="boardWrite.puppy?pageNum=${pageNum}" method="post">
	<%-- 	<input type="hidden" name="pageNum" value="${pageNum}">${pageNum}
		<%ArrayList<Comment> commentList =(ArrayList<Comment>)request.getAttribute("commentList");
		User loginedUser = (User)request.getSession().getAttribute("loginedUser");
		%>
		<input type="hidden" name="depth" value="${comment.depth}"> <input
			type="hidden" name="pos" value="${comment.pos}"> <input
			type="hidden" name="groupId" value="${comment.groupId}"> --%>

		<div align="center">
			<!-- HTML5에서는 태그 속성을 바로 명시하지 않고, CSS를 작성하여 붙여준다. -->
			<table border="2" width="200">
       
				<tr>
					<td>글쓴이 :${sessionScope.loginedUser.getName()}</td>
					<td>${sessionScope.loginedUser.getUserId()}</td>
				</tr>
				<tr>
					<td>제목 :</td>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<td colspan="2"><textarea cols="50" rows="20" name="comment"></textarea>
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="글쓰기"></td>
					<td><input type="reset" value="글쓰기취소"></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
