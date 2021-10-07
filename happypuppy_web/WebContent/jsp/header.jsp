<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	$(document).ready(
			function() {

				$("#myBtnheader").click(
						function() {
							$("#myModal").modal();
							$("#email").focusout(
									function() {
										if ($("#email").val() == "") {
											$("#emailnull").html(
													"<h4>아이디를 입력하세요</h4>").css(
													"color", "red");
											event.preventDefault();
										}
									});
							$("#password").focusout(
									function() {
										if ($("#password").val() == "") {
											$("#passwordnull").html(
													"<h4>비밀번호를 입력하세요</h4>")
													.css("color", "red");
											event.preventDefault();
										}
									});
							$("#form_login")
									.submit(
											function(event) {
												if (($("#email").val() == "")
														|| ($("#password")
																.val() == "")) {
													event.preventDefault();
													location.reload();
												} else {
													$("#form_login").submit();
												}
											});
						});
			});
</script>
</head>
<body>
	<%@page import="team.project.dto.User"%>
	<%
		User loginedUser = (User) request.getSession().getAttribute("loginedUser");
	%>
	<!-- Modal 로그인!!!!!! -->
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
					<form role="form" action="userLogin.puppy" method="post"
						id="form_login">
						<div class="form-group">
							<input type="text" class="form-control" id="email" name="email"
								placeholder="email을 입력하세요">
						</div>
						<div id="emailnull" style="font-color: red"></div>
						<div class="form-group">
							<input type="password" class="form-control" id="password"
								name="password" placeholder="비밀번호를 입력하세요">
						</div>
						<div id="passwordnull" style="font-color: red"></div>
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
						아직 가입안하셨나요? <a href="signup.jsp">가입하기</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<!--
        ==================================================
        Header Section Start
        ================================================== -->

	<header id="top-bar" class="navbar-fixed-top animated-header">
	<div class="container">
		<div class="navbar-header">
			<!-- responsive nav button -->
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- /responsive nav button -->

			<!-- logo -->
			<div class="navbar-brand">
				<a href="../index.html"> <img src="../images/logo2.png" alt="">
				</a>
			</div>
			<!-- /logo -->
		</div>
		<!-- main menu -->
		<nav class="collapse navbar-collapse navbar-right" role="navigation">
		<div class="main-menu">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="../index.html">Home</a></li>
				<li><a href="userIntroduce.puppy">소개</a></li>
				<li><a href="animalimagesearch.jsp">사진검색</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">동물보기 <span class="caret"></span></a>
					<div class="dropdown-menu">
						<ul>
							<li><a href="animalsearch.jsp" <%
							if (loginedUser == null) {//로그아웃상태	
								out.print("style='display:none;'");
							} else {//로그인상태
								out.print("style='display:inline-block;'");
							}%>>검색하여 보러가기</a></li>
							<li><a href="signup.jsp" <%
							if (loginedUser == null) {//로그아웃상태	
								out.print("style='display:inline-block;'");
							} else {//로그인상태
								out.print("style='display:none;'");
							}%>>가입하고 더 많은 동물 보러가기</a></li>
							<li><a href="animalDanger.puppy">위급한 동물 보러가기</a></li>
							<li><a href="index.jsp#works" data-section="#works">새로운 동물 보러가기</a></li>
						</ul>
					</div>
				</li>
				<li><a href="postList.puppy">커뮤니티</a></li>
				<li><a href="adminIndex.puppy"
					<%if (loginedUser == null) {//로그아웃상태
				out.print("style='display:none;'");
			} else if (loginedUser != null) {//로그인상태
				if (loginedUser.getName().equals("admin")) {
					out.print("style='display:inline-block;'");
				} else {
					out.print("style='display:none;'");
				}
			}%>>관리자페이지</a></li>
				<li><a href="signup.jsp"
					<%if (loginedUser == null) {//로그아웃상태
				out.print("style='display:inline-block;'");
			} else {//로그인상태
				out.print("style='display:none;'");
			}%>>가입하기</a></li>
				<li><a href="#" id="myBtnheader"
					<%if (loginedUser == null) {//로그아웃상태
				out.print("style='display:inline-block;'");
			} else {//로그인상태 !안보이기
				out.print("style='display:none;'hover='';");
			}%>>로그인
				</a></li>
				<li><a href="userLogout.puppy"
					<%if (loginedUser != null) {//로그인상태!
				out.print("style='display:inline-block;'");
			} else {//로그아웃상태 ! 안보이기
				out.print("style='display:none;'");
			}%>>로그아웃
				</a></li>
				<li><a href="usermypage.jsp"
					<%if (loginedUser != null) {//로그인상태!
				out.print("style='display:inline-block;'");
			} else {//로그아웃상태 ! 안보이기
				out.print("style='display:none;'");
			}%>>마이페이지
				</a></li>
			</ul>
		</div>
		</nav>
		<!-- /main nav -->

	</div>
	</header>
</body>
</html>