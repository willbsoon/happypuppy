<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>

<%@page import="java.util.ArrayList" %>
<%@page import="team.project.dto.User"%>
<%@page import="team.project.dto.Animal"%>
<%@page import="team.project.dto.Recommend"%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>

<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>HappyPuppy</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">
<!-- Mobile Specific Metas
        ================================================== -->
<meta name="format-detection" content="telephone=no">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Template CSS Files
        ================================================== -->
<!-- Twitter Bootstrs CSS -->

<link rel="stylesheet" href="../css/bootstrap.css">
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
<link rel="stylesheet" href="../css/main.css?ver=1">
<!-- responsive css -->
<link rel="stylesheet" href="../css/responsive.css">

<!-- 나만의 css -->
<link rel="stylesheet" href="../css/custom.css">
<!-- Template Javascript Files
        ================================================== -->
<!-- modernizr js -->
<script src="../js/vendor/modernizr-2.6.2.min.js"></script>
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
<script>
$(document).ready(function() {
	$("#myBtn").click(function() {
		$("#myModal").modal();
		$("#email").focusout(function() {
			if ($("#email").val() == "") {
				$("#emailnull").html("<h4>아이디를 입력하세요</h4>").css("color","red");
				event.preventDefault();
			}
		});
		$("#password").focusout(function() {
			if ($("#password").val() == "") {
				$("#passwordnull").html("<h4>비밀번호를 입력하세요</h4>").css("color","red");
				event.preventDefault();
			}
		});
		$("#form_login").submit(function(event) {
			if (($("#email").val() == "") || ($("#password").val() == "")) {
				event.preventDefault();
				location.reload();	
			} else {
				$("#form_login").submit();
			}
		});
	});
	$("#myBtnheader").click(function() {
		$("#myModal").modal();
		$("#email").focusout(function() {
			if ($("#email").val() == "") {
				$("#emailnull").html("<h4>아이디를 입력하세요</h4>").css("color","red");
				event.preventDefault();
			}
		});
		$("#password").focusout(function() {
			if ($("#password").val() == "") {
				$("#passwordnull").html("<h4>비밀번호를 입력하세요</h4>").css("color","red");
				event.preventDefault();
			}
		});
		$("#form_login").submit(function(event) {
			if (($("#email").val() == "") || ($("#password").val() == "")) {
				event.preventDefault();
				location.reload();	
			} else {
				$("#form_login").submit();
			}
		});
	});
});
</script>
<%
ArrayList<Animal> animalList = new ArrayList<>();
if ((request.getSession().getAttribute("animalListNew")==null)||
		(request.getSession().getAttribute("Recommend")==null)){%>
   <jsp:forward page="animalNew.puppy"/>
<%} else {
	animalList = (ArrayList<Animal>) request.getSession().getAttribute("animalListNew");
}

	Recommend rec = (Recommend) request.getSession().getAttribute("Recommend");
	String bgnde = rec.getBgnde();
	String endde = rec.getEndde();
	String upr_cd = rec.getUpr_cd();
	String numOfRows = rec.getNumOfRows();
%>

</head>

<body>
	<%@include file="./header.jsp"%>
	<section id="hero-area">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<div class="block wow fadeInUp" data-wow-delay=".3s">

						<!-- Slider -->
						<section class="cd-intro">
							<h1 class="wow fadeInUp animated cd-headline slide"
								data-wow-delay=".4s">
								<span>반갑습니다</span>
								<%if (loginedUser==null){ %> <span class="cd-words-wrapper"> <b
									class="is-visible">고양이 집사</b> <b>강아지 집사</b> <b>개성파 집사</b>
								</span> <span>여러분</span><br><%}else{ %>
								<%=loginedUser.getName() %>
								<span>님</span><br><%} %>
							</h1>
						</section>
						<!-- cd-intro -->
						<!-- /.slider -->

						<h2 class="wow fadeInUp animated" data-wow-delay=".6s">
							저희는 새로운 인생을 드리기 위해 반려 동물 친구들을 분양해줍니다.<br> 또한 분양은 동물에게도 새로운
							인생을 줍니다.<br> 유기된 우리 동물 친구들과 함께 새로운 인생을 살아보세요!
						</h2>
						<a class="btn-lines dark light wow fadeInUp animated smooth-scroll btn btn-default btn-green"
							data-wow-delay=".9s" href="#works" data-section="#works"> 새로운
							동물 보러가기!</a> 
						
						<a
							href="signup.jsp"
							data-wow-delay=".9s"
							class="btn-lines dark light wow fadeInUp btn btn-green"
							<%
							if (loginedUser == null) {//로그아웃상태
								out.print("style='display:inline-block;'");
							} else {//로그인상태
								out.print("style='display:none;'");
							}%>
							>가입하고 더 많은 동물 보러가기!</a>
						
						<a
							href="animalsearch.jsp"
							data-wow-delay=".9s"
							class="btn-lines dark light wow fadeInUp btn btn-green"
							<%
							if (loginedUser == null) {//로그아웃상태	
								out.print("style='display:none;'");
							} else {//로그인상태
								out.print("style='display:inline-block;'");
							}%>
							>더 많은 동물 보러가기!</a>
						
						<a
							href="animalDanger.puppy"
							data-wow-delay=".9s"
							class="btn-lines dark light wow fadeInUp btn btn-green">위급한 동물 보러가기!</a>
						
						<!--
						슬라이더 버튼 
						-->
						<!-- Trigger the modal with a button -->
						<button type="button" data-wow-delay=".9s"
							class="btn-lines dark light wow fadeInUp animated smooth-scroll btn btn-default btn-green"
							id="myBtn"
							<%if (loginedUser == null) {//로그아웃상태
								out.print("style='display:inline-block;'");
							} else {//로그인상태
								out.print("style='display:none;'");
							}%>
							>Login</button>

						<button type="button" data-wow-delay=".9s" id="logout"
								onclick="location.href='../userLogout.puppy'"
								class="btn-lines dark light wow fadeInUp animated smooth-scroll btn btn-default btn-green"
								<%
								if (loginedUser == null) {//로그아웃 상태
									out.print("style='display:none;'");
								} else {
								out.print("style='display:inline-block;'");
								}
								%>
								>Logout</button>
						<!-- Modal -->
						<div class="modal fade" id="myModal" role="dialog">
							<div class="modal-dialog">
								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header" style="padding: 0px">
										<button type="button" class="close" data-dismiss="modal"
											style="margin-top: 0px">&times;</button>
										<h4>
											<span class="glyphicon glyphicon-lock"></span> Login
										</h4>
									</div>
									<div class="modal-body" style="padding: 40px 50px;">
										<form role="form"
											action="login.puppy"
											method="post" 
											id="form_login">
											<div class="form-group">
												<input type="text" class="form-control" id="email"
													name="email" placeholder="email을 입력하세요">
											</div>
											<div id="emailnull" style="font-color:red"></div>
											<div class="form-group">
												<input type="password" class="form-control" id="password"
													name="password" placeholder="비밀번호를 입력하세요">
											</div>
											<div id="passwordnull" style="font-color:red"></div>
											<div></div>
											<button type="submit" class="btn btn-success btn-block">
												<span class="glyphicon glyphicon-off"></span> Login
											</button>
											<button type="submit" id="login_face"
												class="btn btn-success btn-block">
												<span class="glyphicon glyphicon-off"></span> Facebook Login
											</button>
										</form>
									</div>
									<div class="modal-footer">
										<p>
											아직 가입안하셨나요? <a
												href="signup.jsp">가입하기</a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--
            ==================================================
            Portfolio Section Start
            새로운 동물 소개 섹션
            ================================================== -->
    <section id="works" class="works">
		<div class="container">
			<div class="section-heading">
            <h1 class="title wow fadeInDown" data-wow-delay=".3s">New Face</h1>
            <%
            if (loginedUser == null) {//로그아웃상태	%>
								로그인하시면 품종별로 보실수있습니다
			<%				} else {//로그인상태
				}
				%>
            <p class="wow fadeInDown" data-wow-delay=".5s"></p>
            <br>
            <div class="list-group"
            <%
            if (loginedUser == null) {//로그아웃상태	
								out.print("style='display:none;'");
							} else {//로그인상태
								out.print("");
							}
							%>
            >
               <!-- 변경됨 링크를 서블릿으로 바꾸기 -->
               <a href="animalSearch.puppy?bgnde=<%=bgnde%>&endde=<%=endde%>&upkind=417000&upr_cd=<%=upr_cd%>&numOfRows=<%=numOfRows %>"
               class="list-group-item">강아지</a>
               <a href="animalSearch.puppy?bgnde=<%=bgnde%>&endde=<%=endde%>&upkind=422400&upr_cd=<%=upr_cd%>&numOfRows=<%=numOfRows %>"
               class="list-group-item">고양이</a>
               <a href="animalSearch.puppy?bgnde=<%=bgnde%>&endde=<%=endde%>&upkind=429900&upr_cd=<%=upr_cd%>&numOfRows=<%=numOfRows %>"
               class="list-group-item">개성동물</a>
            </div>
         </div>
			<div class="row" id="listmaker">
				
			</div>
		</div>
	</section>
	<section id="works" class="works">
		<div class="container">
			<div class="row" id="listmaker">
				<!-- id="listmaker"추가 -->
				<%
					for (int i = 0; i < animalList.size() ; i++) {
				%>
				<div class="col-sm-4 col-xs-12">
					<figure class="wow fadeInLeft animated portfolio-item"
						data-wow-duration="500ms" data-wow-delay="0ms">
						<div class="thumbnail-wrapper">
							<div class="thumbnail">
								<div class="centered">
									<img src="<%out.print(animalList.get(i).getPopfile());%>"
										class="img-responsive" alt="this is a title">

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
						</div>
					</figure>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</section>
	<!-- #works -->
	<!-- footer -->
	<%@ include file="./footer.jsp"%>
</body>
</html>