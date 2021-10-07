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

<title>동물리스트</title>

<!-- Bootstrap Core CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<!-- Ionicons Fonts Css -->
<link rel="stylesheet" href="../css/ionicons.min.css">
<!-- animate css -->
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
	ArrayList<Animal> animalList = (ArrayList<Animal>) request.getSession().getAttribute("animalList");
	Recommend rec = (Recommend) request.getSession().getAttribute("Recommend");
	String bgnde = rec.getBgnde();
	String endde = rec.getEndde();
	String upr_cd = rec.getUpr_cd();
	String numOfRows = rec.getNumOfRows();
%>
</head>
<body>
	<%@ include file="./header.jsp"%>
	
	<!--
        ==================================================
        가운데 상단 카테고리
        ================================================== -->
	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="section-heading">
				<br> <br> <br> <br> <br> <br>
				<h2 class="title wow fadeInDown" data-wow-delay=".3s">동물 종류</h2>
				<p class="wow fadeInDown" data-wow-delay=".5s"></p>
				<br>
				<div class="list-group">
					<!-- 변경됨 링크를 서블릿으로 바꾸기 -->
					<a href="animalSearch.puppy?bgnde=<%=bgnde%>&endde=<%=endde%>&upkind=417000&upr_cd=<%=upr_cd%>&numOfRows=<%=numOfRows %>"
					class="list-group-item">강아지</a>
					<a href="animalSearch.puppy?bgnde=<%=bgnde%>&endde=<%=endde%>&upkind=422400&upr_cd=<%=upr_cd%>&numOfRows=<%=numOfRows %>"
					class="list-group-item">고양이</a>
					<a href="animalSearch.puppy?bgnde=<%=bgnde%>&endde=<%=endde%>&upkind=429900&upr_cd=<%=upr_cd%>&numOfRows=<%=numOfRows %>"
					class="list-group-item">개성동물</a>
				</div>
			</div>
		</div>
	</div>

	<section id="works" class="works">
		<div class="container">
			<div class="row" id="listmaker">
				<!-- id="listmaker"추가 -->
				<%
					for (int i = 0; i < animalList.size(); i++) {

				%>
				<div class="col-sm-4 col-xs-12">
					<figure class="wow fadeInLeft animated portfolio-item"
						data-wow-duration="500ms" data-wow-delay="0ms">
						<div class="thumbnail-wrapper">
							<div class="thumbnail">
								<div class="centered">
									<%-- 	<%db에서 받은 이미지 경로추가%> --%>

									<img src="<%out.print(animalList.get(i).getPopfile());%>"
										class="img-responsive" alt="this is a title">

								</div>
								<figcaption>
									<%-- <% if(로그인이 되있으면 <a href="portfolio.jsp">
										아니라면 가입 하는 페이지로 보내기%>  --%>
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
						</div>
					</figure>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</section>

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
	<!-- /.container -->
	<!-- footer -->
	<%@ include file="./footer.jsp"%>


</body>

</html>
