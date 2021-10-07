<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%@page import="team.project.dto.Animal"%>
<body>
	<%
		Animal choicedAnimal = (Animal) session.getAttribute("choicedAnimal");
	%>
	<ul>

		<li><img src="<%=choicedAnimal.getPopfile()%>">
		<li>견종(묘종) : <%=choicedAnimal.getKindCd()%><br>
		<li>성별: <%=choicedAnimal.getSexCd()%><br>
		<li>출생일추정 : <%=choicedAnimal.getAge()%><br>
		<li>색상 : <%=choicedAnimal.getColorCd()%><br>
		<li>특징 : <%=choicedAnimal.getSpecialMark()%><br>
		<br>
		<br>
		<li>실종일 : <%=choicedAnimal.getNoticeSdt()%><br>
		<li>실종장소 : <%=choicedAnimal.getOrgNm() + " " + choicedAnimal.getHappenPlace()%><br>
		<li>현재상태 : <%=choicedAnimal.getProcessState()%><br>
		<li>보호장소 : <%=choicedAnimal.getCareAddr()%><br>
		<li>연락처 : <%=choicedAnimal.getCareTel()%><br>
		<br>
		<br>
		<li><%=choicedAnimal.toString()%>
	</ul>

</body>
</html>