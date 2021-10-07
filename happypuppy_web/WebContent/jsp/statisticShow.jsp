<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@page import="java.util.ArrayList"%>
	<%@page import="team.project.dto.Animal"%>
	<%@page import="java.util.HashSet"%>
	<%@page import="java.util.Map"%>

	<%
		ArrayList<Animal> animalList = (ArrayList<Animal>) request.getSession().getAttribute("animalList");
		HashSet<String> kindCdSet = (HashSet<String>) request.getSession().getAttribute("kindCdSet");
		ArrayList<Integer> ageList = (ArrayList<Integer>) request.getSession().getAttribute("ageList");
		ArrayList<Double> weightList = (ArrayList<Double>) request.getSession().getAttribute("weightList");
		ArrayList<Map<String, String>> processStateList = (ArrayList<Map<String, String>>) request.getSession()
				.getAttribute("processStateList");
		ArrayList<Map<String, String>> specialMark = (ArrayList<Map<String, String>>) request.getSession()
				.getAttribute("specialMark");
	%>

	kindCdSet정리 : <%=kindCdSet%><br>
	<br>

	<%
		double ageSum = 0;
		double ageAvg = 0;
		for (int i = 0; i < ageList.size(); i++) {
			ageSum = ageSum + ageList.get(i).doubleValue();
		}
		ageAvg = ageSum / ageList.size();
	%>
	출생년도 리스트 정리 : <%=ageList%><br>
	평균 출생년도 : <%=ageAvg%><br>
	<br>

	<%
		double weightSum = 0;
		double weightAvg = 0;
		for (int i = 0; i < weightList.size(); i++) {
			weightSum = weightSum + weightList.get(i).doubleValue();
		}
		weightAvg = weightSum / weightList.size();
	%>
	몸무게 리스트 정리 : <%=weightList%><br>
	평균 몸무게 : <%=weightAvg%><br>
	<br>
	
	동물 상태 정리 :<%=processStateList%><br>
	<br>
	
	<br> 특징 정리 : <%
	for(int i=0; i<specialMark.size();i++){
		out.print(specialMark.get(i)+"<br>"); }%><br>
	
	<%-- 특징 : '불량'단어 포함 : <%
	int check=0;
	for(int i=0; i<specialMark.size();i++){
		String a = specialMark.get(i).get(key);
		if(a.contains("불량")){
			check = check+1;
		}
	}
	%> --%>
	<br>동물종류
	<br>
	<%
		HashSet<String> kindCdSet2 = new HashSet();
		for (int i = 0; i < animalList.size(); i++) {
			kindCdSet2.add(animalList.get(i).getKindCd());
			out.print((String) animalList.get(i).getKindCd() + "<br>");
		}
	%>
	정리 :
	<%=kindCdSet%>

	<br>
	<br>나이
	<br>
	<%
		ArrayList<String> ageList2 = new ArrayList<String>();
		for (int i = 0; i < animalList.size(); i++) {
			ageList2.add(animalList.get(i).getAge());
			out.print((String) animalList.get(i).getAge() + "<br>");
		}
	%>
	정리 :
	<%=ageList%>

	<br>
	<br>몸무게
	<br>
	<%
		ArrayList<String> weightList2 = new ArrayList<String>();
		for (int i = 0; i < animalList.size(); i++) {
			weightList2.add(animalList.get(i).getWeight());
			out.print((String) animalList.get(i).getWeight() + "<br>");
		}
	%>
	정리 :
	<%=weightList%>

	<br>
	<br>현재보호상태
	<br>
	<%
		ArrayList<String> processStateList2 = new ArrayList<String>();
		for (int i = 0; i < animalList.size(); i++) {
			processStateList2.add(animalList.get(i).getProcessState());
			out.print((String) animalList.get(i).getProcessState() + "<br>");
		}
	%>
	정리 :
	<%=processStateList%>
</body>
</html>