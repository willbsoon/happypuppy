<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<%@page import="java.util.ArrayList"%>
<%@page import="team.project.dto.Post"%>
<%@page import="team.project.dto.User"%>
<%@page import="team.project.dto.Comment"%>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- <link rel="icon" type="image/png" href="../images/favicon.png"> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

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
<!-- jQuery -->
<script src="../vendor/jquery/jquery.js"></script>
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

<!-- Custom Fonts -->
<link href="../vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript">
$(function(){
	// console.log("jQuery"); 
	// 댓글쓰기-------------------------------
 	$("#add_comment").click(function(){
		$.ajax({
			url : "commentWrite.puppy",// 여기에 코멘트 저장하는 데이터 집어넣고 
 			data : {
 				"name":$("#name").val(),
 				"userId":$("#userId").val(),
 				"postNo":$("#postNo").val(),
 				"content":$("#content").val()
 				},//보내는 데이터 
				dataType: "json",//가지고 오는 데이터의 타입//"json", "xml" 도 가능 
				success : function(data){
					console.log("success")
					var str ="<tr>"
					$.each(data, function(index, item){
						//console.log(index)
						//console.log(item)
						str = str + "<td id='commentId' style='display:none;'>" + item.commentId + "</td>"
						str = str + "<td>" + item.name + "</td>"
						str = str + "<td>" + item.content +"<button type='button' id='del_comment' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button></td>"
						str = str + "<td>" + item.commentDt + "</td>"
						str = str + "</tr>";
						// console.log(item.id);
				}); 
				$("#comment").append(str);
			},
			error : function(error){
				console.log("안됨");
			}
		});
		$(this).parent().find("input").val("");
 	});
	$("#comment").on("click","#del_comment",function(){/* 상위 객체에 on click 이벤트를 주기 */
		//alert("ddd");
		var num = $(this).parent().parent().find("td:first-child");//.html();
		var num1 = $(this).parent().parent();
		$.ajax({
			url : "commentDelete.puppy",//삭제하는 url주소로 이동해야함.
			data : {
				"commentId":num.html()
			},
			success : function(){
				var a = $("#commentId").html();
				num1.remove();
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
	<%@ include file="./header.jsp"%>

	<div class="container" id="wrapper">

		<div id="page-wrapper" style="min-height: 300px; margin-top: 100px;">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">게시판</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- 그리드 옵션 가져올꺼임 -->
			<div class="table-responsive">
				<table class="table table-bordered table-striped">
					<tbody id="board_table_body">
						<tr>
							<th style="text-align: center;">제목</th>
							<td colspan="3">${post.getTitle()}</td>
						</tr>
						<tr>
							<th style="text-align: center;">작성자</th>
							<td>${post.getName()}</td>
							<th style="text-align: center;">작성일</th>
							<td>${post.getWriteDt()}</td>
						</tr>
						<tr height="200px">
							<!-- 행 높이 설정, th 상단에 글씨 위치하게-->
							<th style="text-align: center;">내용</th>
							<td colspan="3">${post.getContent()}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="button_div" style="text-align: center; margin-bottom: 15px">

				<%
					/* 세션에 사용자의 정보가 담겨서 오면 세션에 담긴 유저 아이디와 게시글 작성할때 작성자의 아이디를 게시판에 저장해서 
					비교해보고 맞다면 저렇게 이동하고 아니면 뒤로 가는거
					세션에 저장되어 있어야 할 값은 유저 아이디 이름 
					게시글 페이지를 열었을때는 세션 혹은 리퀘스트에 맵으로 저장해서 가져오기.
					그리고 게시글을 나갈때는 반드시 세션에 담겨있는 게시글데이터 지워주기 리퀘스트라면 상관없고
					*/
					Post post = (Post) request.getSession().getAttribute("post");
					Comment comment = (Comment) request.getSession().getAttribute("comment"); 
					String postUserId = null;
					String loginedUserId = null;

					// postUserId, loginedUserId 설정
					if (loginedUser == null) {
						loginedUserId = "NULL";
						postUserId = post.getUserId();
					} else {
						loginedUserId = loginedUser.getUserId();
						postUserId = post.getUserId();
					}

					if (loginedUserId.equals(postUserId)) {
				%>
				<button type="button" class="btn btn-default"
					onclick="location.href='postupdate.jsp?postNo=${post.getPostNo()}&update=true'">수정하기</button>
				<button type="button" class="btn btn-default" id="del_post"
					onclick="location.href='postDelete.puppy?postNo=${post.getPostNo()}'">삭제하기</button>
				<%
					}
				%>
				
				<button type="button" class="btn btn-default"
					onclick="location.href='postList.puppy'">뒤로가기</button>
			</div>
			<div></div>
			<!-- 댓글창 만들기 시작 -->
			<div class="col-lg-12">
				<div class="panel panel-default">
					<!-- /.panel-heading -->
					<div class="panel-heading">댓글</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>User</th>
										<th>Comments</th>
										<th>Date</th>
									</tr>
								</thead>
								<tbody id="comment">
									<tr>
										<%if (loginedUser == null) {%>
										<td>Guest</td>
										<td><input class="form-control" id="disabledInput"
											name="comment" type="text" value="로그인해주세요" disabled></td>
										<td>-</td>
										<%} else {%>
										<input type=hidden name="name" id="name" value="<%=loginedUser.getName()%>">
										<input type=hidden name="userId" id="userId" value="<%=loginedUser.getUserId()%>">
										<input type=hidden name="postNo" id="postNo" value="<%=post.getPostNo()%>">
										<td><%=loginedUser.getName()%></td>
										<td><input class="form-control" name="content" id="content" style="display:inline-block;width:85%;">
											<button type="button" id="add_comment" class="btn btn-info btn-circle">
											<i class="fa fa-check"></i>
											</button>
										</td>
										<td>-</td>
										<%}%>
									</tr>
									<!-- 처음 들어오면 게시판 번호로 댓글목록 셀렉트해오기  -->
									<%ArrayList<Comment> commentList = (ArrayList<Comment>) request.getSession().getAttribute("commentList");%>
		 							<%for (int i = 0; i < commentList.size(); i++) {%>
									<tr>
										<td id="commentId" style="display: none"><%=commentList.get(i).getCommentId()%></td>
										<td><%=commentList.get(i).getName()%></td>
										<td><%=commentList.get(i).getContent()%>
									 	<%if(loginedUserId.equals(commentList.get(i).getUserId())){ %> 
											<button type='button' id='del_comment' class='btn btn-warning btn-circle'>
											<i class='fa fa-times'></i>
											</button></td>
									<%} %> 
										<td><%=commentList.get(i).getCommentDt()%></td>
										<%if (loginedUserId.equals(postUserId)) {%>
										<!-- <td>
											<button type="button" id="del_comment"
												class="btn btn-warning btn-circle">
												<i class="fa fa-times"></i>
											</button>삭제버튼
										</td>
										<td>날짜 반복문으로 돌리기</td> -->
									</tr>
									<%}}%> 
								</tbody>
							</table>
							<%
								//post객체를 모두 사용후 세션영역에서 post변수 제거
								//request.getSession().removeAttribute("post");
							%>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-6 -->
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
				responsive : true
			});
		});
	</script>
	<!-- footer -->
	<%@ include file="./footer.jsp"%>


</body>
</html>