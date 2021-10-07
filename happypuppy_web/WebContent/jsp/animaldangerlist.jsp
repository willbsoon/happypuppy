<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" type="image/png" href="../images/favicon.png">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>위급한 강아지 리스트</title>

<!-- Bootstrap Core CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<!-- Ionicons Fonts Css -->
<link rel="stylesheet" href="../css/ionicons.min.css">
<!-- animate css -->
<!-- MetisMenu CSS -->
<link href="../vendor/metisMenu/metisMenu.css" rel="stylesheet">

<link rel="stylesheet" href="../css/animate.css">
<!-- Hero area slider css-->
<link rel="stylesheet" href="../css/slider.css">
<!-- owl craousel css -->
<link rel="stylesheet" href="../css/owl.carousel.css">
<link rel="stylesheet" href="../css/owl.theme.css">
<link rel="stylesheet" href="../css/jquery.fancybox.css">
<!-- template main css file -->
<link rel="stylesheet" href="../css/main.css">
<!-- responsive css -->
<link rel="stylesheet" href="../css/responsive.css">
<!-- custom.css -->
<link rel="stylesheet" href="../css/custom.css">
<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Template Javascript Files
        ================================================== -->
<!-- modernizr js -->
<script src="../js/vendor/modernizr-2.6.2.min.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- jquery -->
<script src="../js/jquery.min.js"></script>
<!-- owl carouserl js -->
<script src="../js/owl.carousel.min.js"></script>
<!-- bootstrap js -->

<script src="../js/bootstrap.min.js"></script>
<!-- wow js -->
<script src="../js/wow.min.js"></script>
<!-- slider js -->
<script src="../js/slider.js"></script>
<script src="../js/jquery.fancybox.js"></script>
<!-- template main js -->
<script src="../js/main.js"></script>

<%@page import="java.util.ArrayList"%>
<%@page import="team.project.dto.Animal"%>
<%@page import="team.project.dto.Recommend"%>

<%
	ArrayList<Animal> animalList = (ArrayList<Animal>) request.getSession().getAttribute("animalListDanger");
%>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<script>
$(document).ready(function() {	

	var animalTable = $(this).find("#animalTable");
	var animalTableSize = $("#animalTable tr").length;
	var arrAnimalKilled = new Array();
	var arrAnimalReturned = new Array();
	var arrAnimalDeath = new Array();
	var arrAnimalBring = new Array();
	var arrGraph = new Array();
	var arrStr = new Array();
	var arrData = new Array();
	var arrChart = new Array();
	var arrCurve_chart_member = new Array();
	var arrCHART = new Array();
	
	for (var i=1; i<animalTableSize+1; i++){
		arrAnimalKilled.push(parseFloat(animalTable.find("tr:nth-child("+i+")").find("td:nth-child(1)").html()));
	    arrAnimalReturned.push(parseFloat(animalTable.find("tr:nth-child("+i+")").find("td:nth-child(2)").html()))
	    arrAnimalDeath.push(parseFloat(animalTable.find("tr:nth-child("+i+")").find("td:nth-child(3)").html()))
	    arrAnimalBring.push(parseFloat(animalTable.find("tr:nth-child("+i+")").find("td:nth-child(4)").html()))
	    arrGraph.push("#graphPanel_"+ i);
	    arrStr.push("<div id='curve_chart_member_"+ i +"' style='min-height: 300px'></div>");
	    $(arrGraph[i-1]).append(arrStr[i-1]);
	}
	
	//삭제 성공후 그래프 div생성완료
	google.charts.load("current", {
		packages : [ "corechart" ]
	});
	function drawChart() {
		var options = {
				pieSliceText : 'label',
				title : '위험도 상태 분포 그래프',
				pieStartAngle : 0
			};
		for (var i=1; i<animalTableSize+1; i++){ 
			arrData.push("data_"+i);
			arrData[i-1] = google.visualization.arrayToDataTable([
				[ 'Language','Speakers (in millions)' ],
				[ '안락사', arrAnimalKilled[i-1] ],
				[ '자연사', arrAnimalDeath[i-1] ],
				[ '입양', arrAnimalBring[i-1] ],
				[ '반환', arrAnimalReturned[i-1] ] 
			]);
			arrChart.push("chart_" + i);
			arrCurve_chart_member.push("curve_chart_member_" + i)
			arrCHART.push(arrChart[i-1] = new google.visualization.PieChart(document.getElementById(arrCurve_chart_member[i-1])));
			arrCHART[i-1].draw(arrData[i-1],options)
		};
		
	};
	
	google.charts.setOnLoadCallback(drawChart);

});
</script>

<script type="text/javascript">
	function view(opt) {
		if (opt) {
			spec1.style.display = "block";
		} else {
			spec1.style.displayspec1.style.display = "none";
		}
	}
</script>
</head>
<body>
	<%@ include file="./header.jsp"%>

	<!--
        ==================================================
        가운데 상단 카테고리
        ================================================== -->
	<!-- Page Content -->
	<div class="container">
		<table id="animalTable" style="display:none;">
		<%for (int i = 0; i < animalList.size(); i++) { %>
			<tr>
				<td><%=animalList.get(i).getKilled()%></td>
				<td><%=animalList.get(i).getReturned()%></td>
				<td><%=animalList.get(i).getDeath()%></td>
				<td><%=animalList.get(i).getBring()%></td>
			</tr>
		<% } %>
		</table>
		<div class="row">
			<div class="section-heading">
				<br> <br> <br> <br> <br> <br>
				<h2 class="title wow fadeInDown" data-wow-delay=".3s">위급한 강아지
					리스트</h2>
			</div>
		</div>
	</div>

	<section id="works" class="works">
		<div class="container">
			<div class="row" id="listmaker">
				<!-- id="listmaker"추가 -->
				<%
					int i = 0;
					for (i = 0; i < 24 ; i++) {
				%>

				<div class="col-sm-4 col-xs-12">
					<figure class="wow fadeInLeft animated portfolio-item"
						data-wow-duration="500ms" data-wow-delay="0ms">
						<div class="thumbnail-wrapper">
							<div class="thumbnail" id="thumbnail">
								<div class="centered">
									<img src="<%out.print(animalList.get(i).getPopfile());%>" 
										class="img-responsive" alt="this is a title">
								</div>
								<div class="overlay" id="overlay">
									<div id="graphPanel_<%=i+1%>" class="panel-body"></div>
								</div>
							</div>
							<figcaption>

								<br> <br> <br> <br> <br> <br> <br>
								<br> <br> <br>
								<h4>
									<a
										href="animalDetail.puppy?popfile=<%out.print(animalList.get(i).getPopfile());%>">
										<%
											out.print(animalList.get(i).getProcessState());
										%>
									</a>						
								</h4>
								<br>
								<p>
									<%
										out.print(animalList.get(i).getCareAddr());
									%>
								</p>
								<%
									if (i % 3 == 2) {
											out.print("<br><br>");
										}
								%>

							</figcaption>

						</div>
					</figure>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</section>
<!-- 
	<section>
		<div class="container">
			<ul class="pagination">
				<%
					/* j는 데이터의 양에 따라서 j < data.size 사용 */
					int j = 0;
					for (j = 1; j < 6; j++) {

						out.print("<li><a href='123'>" + j + "</a></li>");
					}
				%>
			</ul>
		</div>
	</section>
 -->
	<!-- /.container -->
	<!-- footer -->
	<%@ include file="./footer.jsp"%>
</body>

</html>
