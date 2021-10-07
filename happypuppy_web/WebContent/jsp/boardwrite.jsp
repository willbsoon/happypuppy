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
</style>
<title>게시판</title>

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
</head>
<body>
<%@ include file="./header.jsp" %>
<%@page import="team.project.dto.User"%>


	    <div class="container" id="wrapper">


        <div id="page-wrapper"style="min-height: 209px;margin-top: 100px;">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">글쓰기</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           	회원들에게 하고싶은 말을 전해주세요
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                             <div class="row" >
                                <div class="writeform">
                                    <form role="form" method="post" action="postWrite.puppy">
                                        <div class="form-group">
                                            <label>제목</label>
                                            <input type="text" class="form-control" name="title" placeholder="제목을 입력해주세요">
                                        </div>
                                        <div class="form-group">
                                            <label>이름</label>
                                            <%if(request.getAttribute("update")==null) {/* 업데이트 싸인이 없다면 새로운 글쓰기 */
                                            	if(loginedUser==null){ %>
                                            <input type="text" name="name" class="form-control" placeholder="이름을 입력해주세요">
                                            	<%} else{ %>
                                             <input class="form-control" id="disabledInput" name="name"type="text" value=<%=loginedUser.getName() %> disabled>
                                            <%}} else{/* 업데이트 사인이 있다면 이렇게 보여주기 */  %>
                                            <input class="form-control" id="disabledInput" name="name"type="text" value="게시글에 저장되어있던 작성자의 이름" disabled>
                                            <%} %>
                                        </div>
                                        <div class="form-group">
                                            <label>Text area</label>
                                            <textarea class="form-control" rows="8" name="content"></textarea>
                                        </div>
                                        
                                        <%/* 만약 수정하기 버튼을 누른다면 비즈니스 로직에서 리퀘스트에 업데이트라는 단어를 저장해서 보내주게 설정  
                                        	업데이트라는게 있으면 버튼을 수정하기로 보여주고 없으면 글쓰기 버튼을 보여주는거고	
                                        */ 
                                        if(request.getAttribute("update")==null) {%>
                                        <button type="submit" class="btn btn-default">글쓰기</button>
                                        <%}else{ %>
                                        <button type="button" onclick="location.href='수정'" class="btn btn-default">수정하기</button>
                                        <%} %>
                                        <button type="reset" class="btn btn-default">다시 쓰기</button>
                                    </form>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
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
	    <!-- jQuery -->
    <script src="../vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../vendor/metisMenu/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="../vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="../vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
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