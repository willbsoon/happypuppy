<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" type="image/png" href="../images/favicon.png">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
  <!-- jQuery -->
    <script src="../vendor/jquery/jquery.js"></script>
    <script>
$(document).ready(function() {
	
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
<style>
/* 게시글 서치바 여백 */
#dataTables-example_filter{
    margin-left: 50%;
} 
/* 페이지 번호 구역 여백 */
#dataTables-example_paginate{
    margin-left: 30%;
}
</style>
<title>게시판</title>

<!-- Bootstrap Core CSS -->
<link href="../css/bootstrap.css" rel="stylesheet">
<!-- Ionicons Fonts Css -->
<!-- <link rel="stylesheet" href="../css/ionicons.css"> -->
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
<!-- <script src="../js/vendor/modernizr-2.6.2.js"></script> -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- jquery -->
<!-- <script src="../js/jquery.js"></script> -->
<!-- owl carouserl js -->
<script src="../js/owl.carousel.min.js"></script> 
<!-- bootstrap js -->

<script src="../js/bootstrap.js"></script>
<!-- wow js -->
<script src="../js/wow.min.js"></script>
<!-- slider js -->
<script src="../js/slider.js"></script>
<script src="../js/jquery.fancybox.js"></script>
<!-- template main js -->
<script src="../js/main.js"></script>

</head>
<body>
<%@ include file="./header.jsp" %>
<%@page import="java.util.ArrayList" %>
<%@page import="team.project.dto.Post"%>
	<!--
        ==================================================
        가운데 상단 카테고리
        ================================================== -->
	<!-- Page Content 
		<div class="container">
		<div class="row">
			<div class="section-heading">
				<br> <br> <br> <br> <br> <br>
				<h2 class="title wow fadeInDown" data-wow-delay=".3s">동물 종류</h2>
				<p class="wow fadeInDown" data-wow-delay=".5s"></p>
				<br>
				<div class="list-group">
					 변경됨 링크를 서블릿으로 바꾸기 
					<a href="index.jsp?upkind=417000" class="list-group-item">강아지</a> <a
						href="?upkind=422400" class="list-group-item">고양이</a> <a
						href="?upkind=429900" class="list-group-item">개성동물</a>
				</div>
			</div>
		</div>
	</div>-->




	    <div class="container" id="wrapper">


        <div id="page-wrapper"style="min-height: 209px;margin-top: 100px;">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">게시판</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           	당신의 이야기를 들려주세요
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>제목</th>
                                        <th>작성일</th>
                                        <th>작성자</th>
                                        <th>조회수</th>
                                    </tr>
                                </thead>
                                <% ArrayList<Post> postList=(ArrayList<Post>)request.getSession().getAttribute("postList");
                                request.getSession().removeAttribute("postList");
                                %>
                                <tbody>
                                    <%for(int i=0; i<postList.size();i++){ %>
                                   <tr class="even gradeC">
                                        <td><%=postList.get(i).getPostNo() %></td>
                                        <td><a href="contentShow.puppy?postNo=<%=postList.get(i).getPostNo()%>"><%=postList.get(i).getTitle()%></a></td>
                                        <td><%=postList.get(i).getWriteDt() %></td>
                                        <td class="center"><%=postList.get(i).getUserId() %></td>
                                        <td class="center"><%=postList.get(i).getHitCt() %></td>
                                    </tr>
                                    <%} %>
                                    
                                </tbody>
                            </table><%if(session.getAttribute("name")!=null){ %>
                            <p align="center">
                            <button type="button" class="btn btn-info" onClick="self.location='./boardwrite.jsp';">글쓰기</button>
							</p>
							<%} %>
                            <!-- /.table-responsive -->
                            <!-- <div class="well">
                                <h4>DataTables Usage Information</h4>
                                <p>DataTables is a very flexible, advanced tables plugin for jQuery. In SB Admin, we are using a specialized version of DataTables built for Bootstrap 3. We have also customized the table headings to use Font Awesome icons in place of images. For complete documentation on DataTables, visit their website at <a target="_blank" href="https://datatables.net/">https://datatables.net/</a>.</p>
                                <a class="btn btn-default btn-lg btn-block" target="_blank" href="https://datatables.net/">View DataTables Documentation</a>
                            </div> -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
	  

    <!-- Bootstrap Core JavaScript -->
    <script src="../vendor/bootstrap/js/bootstrap.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../vendor/metisMenu/metisMenu.js"></script>

    <!-- DataTables JavaScript -->
    <script src="../vendor/datatables/js/jquery.dataTables.js"></script>
    <script src="../vendor/datatables-plugins/dataTables.bootstrap.js"></script>
    <script src="../vendor/datatables-responsive/dataTables.responsive.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
    </script>
	<!-- footer -->
	<%@ include file="./footer.jsp"%>

	
</body>
</html>