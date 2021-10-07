<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Happy Puppy Admin 1.0 - memberlist</title>
<!-- Bootstrap Core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- MetisMenu CSS -->
<link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
<!-- DataTables CSS -->
<link href="../vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
<!-- DataTables Responsive CSS -->
<link href="../vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<!-- jQuery -->
<script src="../vendor/jquery/jquery.js"></script>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="team.project.dto.User"%>
<%@page import="java.util.ArrayList"%>
<%
//세션에 회원들의 정보가 담겨서 온다
ArrayList<User> list_user = (ArrayList<User>) request.getAttribute("list_user");
%>

<script>
$(function(){
	//alert("멤버리스트");
	//delete하는 경우
	$("#list_tbody").on("click","#delete_user",function(){
		console.log($(this));
		console.log($(this).parent());
		var email = $(this).parent().parent().find("td:nth-child(3)");//email부분
		var email_parent = $(this).parent().parent();
		console.log(email.html());
		$.ajax({
			url : "adminMemberDelete.puppy",//삭제하는 url주소로 이동해야함.
			data : {"email":email.html()},//보내는 데이터 유저아이디
			dataType: "json",//가지고 오는 데이터의 타입//"json", "xml" 도 가능
			success : function(response){
				email_parent.remove();
				console.log("삭제성공");
			},
			error : function(){
				console.log("삭제 실패");
			}
		})
	});
});
</script>

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand">Happy Puppy v1.0</a>
            </div>
            <!-- /.navbar-header -->

			<!-- 사이드바 메뉴 -->
           <div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="adminIndex.puppy"><i class="fa fa-dashboard fa-fw"></i>
						Dashboard</a></li>
						<li><a href="adminMemberList.puppy"><i class="fa fa-table fa-fw"></i>
						Member List</a></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">회원목록</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            최근 가입순 회원목록
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                    	<th>regDt</th>
                                        <th>userId</th>
                                        <th>email</th>
                                        <th>name</th>
                                        <th>gender</th>
                                        <th>born</th>
                                        <th>delete</th>
                                    </tr>
                                </thead>
                                <tbody id="list_tbody">
                                
                                	<%for(int i=0; i<list_user.size();i++) {%>
                                		<tr>
                                			<td><%=list_user.get(i).getRegDt()%></td>
                                			<td><%=list_user.get(i).getUserId()%></td>
                                			<td><%=list_user.get(i).getEmail()%></td>
                                			<td><%=list_user.get(i).getName()%></td>
                                			<td><%=list_user.get(i).getGender()%></td>
                                			<td><%=list_user.get(i).getBorn()%></td>
                                			<td><button type="button" id="delete_user" class="btn btn-warning btn-circle"><i class="fa fa-times"></i></button></td>
										</tr>
                                	<%}%>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                            
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

</body>

</html>
