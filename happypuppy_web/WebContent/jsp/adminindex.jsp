<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<%@page import="java.util.Calendar"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="team.project.dto.Admin"%>

<%
/* request에 사용자의 정보가 담겨서 오면
유저 id가 admin인지 확인후 맞으면 페이지 열고
아니면 뒤로 보냄
request에 저장되어 있어야 할 값은 유저 아이디 이름 
*/
Admin admin = (Admin) request.getAttribute("admin_data");
%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Happy Puppy Admin 1.0 - Index</title>

<!-- Bootstrap Core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="../vendor/metisMenu/metisMenu.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../vendor/font-awesome/css/font-awesome.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- jQuery -->
<script src="../vendor/jquery/jquery.js"></script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    /* 페이지 실행시 대시보드 밑에 뜨는 데이터 */
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
     <%Calendar cal = Calendar.getInstance();%>
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Month', 'Members', 'Visitors'],
          [<%=cal.get(Calendar.MONTH) - 4%>+'월',  <%=admin.getMonth5User()%>,      <%=admin.getMonth5Visit()%>],
          [<%=cal.get(Calendar.MONTH) - 3%>+'월',  <%=admin.getMonth4User()%>,      <%=admin.getMonth4Visit()%>],
          [<%=cal.get(Calendar.MONTH) - 2%>+'월',  <%=admin.getMonth3User()%>,      <%=admin.getMonth3Visit()%>],
          [<%=cal.get(Calendar.MONTH) - 1%>+'월',  <%=admin.getMonth2User()%>,      <%=admin.getMonth2Visit()%>],
          [<%=cal.get(Calendar.MONTH)%>+'월',  <%=admin.getMonth1User()%>,       <%=admin.getMonth1Visit()%>],
          [<%=cal.get(Calendar.MONTH) + 1%>+'월',  <%=admin.getMonthUser()%>,      <%=admin.getMonthVisit()%>]
        ]);

        var options = {
          title: 'Member Changes',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart_member'));

        chart.draw(data, options);
      }
</script>


<script>
    jQuery(function(){
    	$("#graph1").click(function(){
    		var graph = $("#graphPanel");
    		$.ajax({
  	 			url:"adminIndexGraph.puppy",
  	 			data:{"a":"1"},
  	 			dataType: "json",//가지고 오는 데이터의 타입//"json", "xml" 도 가능
  	 			success:function(jsondata){
  	 				console.log("1 성공");
  	 				$("#curve_chart_member").remove();
  	 				var str =  "<div id='curve_chart_member' style='min-height: 300px'></div>";
  	 				graph.append(str);
  	 				//삭제 성공후 그래프 div생성완료
  	 				google.charts.load('current', {'packages':['corechart']});
				    $.each(jsondata, function(index, item){
				    	function drawChart() {
				    		var data = google.visualization.arrayToDataTable([
				    			['Month', 'Members', 'Visitors'],
				    			[parseInt(item.month5) + '월',  item.month5User,      item.month5Visit],
				    			[parseInt(item.month4) + '월',  item.month4User,      item.month4Visit],
				    			[parseInt(item.month3) + '월',  item.month3User,      item.month3Visit],
				    			[parseInt(item.month2) + '월',  item.month2User,      item.month2Visit],
				    			[parseInt(item.month1) + '월',  item.month1User,       item.month1Visit],
				    			[parseInt(item.month) + '월',  item.monthUser,      item.monthVisit]
				    		]);
				    		
				    		 var options = {
				    				 title: 'Member Changes',
				    				 curveType: 'function',
				    				 legend: { position: 'bottom' }
				    		 };
				    		 
				    		 var chart = new google.visualization.LineChart(document.getElementById('curve_chart_member'));
				    		 chart.draw(data, options);	 
				    	}
				    	google.charts.setOnLoadCallback(drawChart);
				    });
				},
  	 			
  	 			error:function(error){
  	 				console.log(error);
  	 			}	
    		});
    	});
    	
    	$("#graph2").click(function(){
    		var graph = $("#graphPanel");
    		$.ajax({
    			url:"adminIndexGraph.puppy",
    			data:{"a":"2"},
    			dataType: "json",//가지고 오는 데이터의 타입//"json", "xml" 도 가능
    			success:function(jsondata){
    				console.log("2 성공");
    				$("#curve_chart_member").remove();
    				var str =  "<div id='curve_chart_member' style='min-height: 300px'></div>";
  	 				graph.append(str);
  	 				//삭제 성공후 그래프 div생성완료
  	 				google.charts.load("current", {packages:["corechart"]});
  	 				$.each(jsondata, function(index, item){
  	 					function drawChart() {
  	 						var data = google.visualization.arrayToDataTable([
  	 							['Language', 'Speakers (in millions)'],
  	 							['보호중',  parseInt(item.live)],
  	 							['안락사',  parseInt(item.kill)],
  	 							['자연사',  parseInt(item.dead)],
  	 							['입양', parseInt(item.getted)],
  	 							['반환', parseInt(item.returned)]
  	 						]);
  	 						var options = {
  	  	 					        pieSliceText: 'label',
  	  	 					        title: '유기동물 상태 분포 그래프',
  	  	 					        pieStartAngle: 0
  	  	 					};
  	  	 					
  	  	 					var chart = new google.visualization.PieChart(document.getElementById('curve_chart_member'));
  	  	 					chart.draw(data, options);		
  	 					}
  	 					google.charts.setOnLoadCallback(drawChart);
  	 				});
	
    			},
    			error:function(error){
  	 				console.log(error);
  	 			}
    		});
    	});
    });   	
    
</script>
</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
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
		
		<!-- 대시보드 -->
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Dashboard</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<!-- 대시보드 1번 코멘트 개수 -->
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-comments fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge"><%=admin.getTodayVisit()%></div>
									<div>New Visitors!</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">Total Visitors</span> <span
									class="pull-right"><%=admin.getAllVisit()%></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<!-- 대시보드 2번 뉴 멤버 수 -->
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-green">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-tasks fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge"><%=admin.getTodayUser()%></div>
									<div>New Members!</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">Total Members</span> <span
									class="pull-right"><%=admin.getAllUser()%></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<!-- 대시보드 3번 새로운 동물수 -->
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-yellow">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-shopping-cart fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge"><%=admin.getTodayPuppy()%></div>
									<div>New Animals!</div>
								</div>
							</div>
						</div>
						
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left"></span>Number of animals a month ago<span
									class="pull-right"><%=admin.getMonth1Puppy()%></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<!-- 대시보드4번 새로운 게시글 수 -->
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-red">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-support fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge"><%=admin.getTodayPost()%></div>
									<div>New Post!</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">Total Post</span> <span
									class="pull-right"><%=admin.getAllPost()%></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-8">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i> Data Chart
							<div class="pull-right">
								<div class="btn-group">
									<button type="button"
										class="btn btn-default btn-xs dropdown-toggle"
										data-toggle="dropdown">
										주제별그래프 <span class="caret"></span>
									</button>
									<ul class="dropdown-menu pull-right" role="menu">
										<li><a id="graph1" href="#">월별 멤버 수 그래프</a></li>
										<li><a id="graph2" href="#">유기견 등록 그래프</a></li>
										<!-- 
										<li><a id="graph3" href="#">월별 게시글 그래프</a></li>
										 -->
										<li class="divider"></li>
										<li><a href="#"> </a></li>
									</ul>
								</div>
							</div>
						</div>
						<!-- /.panel-heading -->
						<div id="graphPanel" class="panel-body">
							<div id="curve_chart_member" style="min-height: 300px"></div>

						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	<!-- jQuery -->
	<!--	<script src="../vendor/jquery/jquery.js"></script> -->

	<!-- Bootstrap Core JavaScript -->
	<script src="../vendor/bootstrap/js/bootstrap.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="../vendor/metisMenu/metisMenu.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>

</body>

</html>
