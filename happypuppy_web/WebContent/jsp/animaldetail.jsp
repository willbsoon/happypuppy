<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<!-- Basic Page Needs
        ================================================== -->
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" type="image/png" href="images/favicon.png">
<title>동물상세정보</title>
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
<link rel="stylesheet" href="../css/bootstrap.min.css">
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
<!-- DataTables Responsive CSS -->
<link href="../vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">
<!-- MetisMenu CSS -->
<link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- template main js -->
<script src="../js/main.js"></script>
<%@page import="team.project.dto.Animal"%>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script>
$(document).ready(function() {
	var pie = $(this).find(".overlay").find(".panel-body").find("#curve_chart_member");
	
	var animalDeath = parseFloat($(this).find("#animalDeath").html());
	var animalKilled = parseFloat($(this).find("#animalKilled").html());
	var animalReturned = parseFloat($(this).find("#animalReturned").html());
	var animalBring = parseFloat($(this).find("#animalBring").html())
	
	var graph = $(".panel-body");
	var str = "<div id='curve_chart_member' style='min-height: 500px'></div>";
	graph.append(str);
	
	//삭제 성공후 그래프 div생성완료
	google.charts.load("current", {
		packages : [ "corechart" ]
	});
					
	function drawChart() {
		var data = google.visualization.arrayToDataTable([
			[ 'Language','Speakers (in millions)' ],
			[ '안락사', animalKilled ],
			[ '자연사', animalDeath ],
			[ '입양', animalBring ],
			[ '반환', animalReturned ] 
		]);
		var options = {
			//legend : 'none',
			pieSliceText : 'label',
			title : '위험도 상태 분포 그래프',
			pieStartAngle : 0
		};
		var chart = new google.visualization.PieChart(document.getElementById('curve_chart_member'));
		chart.draw(data, options);
		};
		google.charts.setOnLoadCallback(drawChart);
});
</script>
</head>
<body>
<%@ include file="./header.jsp" %>
<%
		Animal choicedAnimal = (Animal) session.getAttribute("choicedAnimal");
		String NeuterYn = "미상";
		String SexCd = "미상";
		if(choicedAnimal.getNeuterYn().equals("Y")) { NeuterYn = "했음";}
		else if(choicedAnimal.getNeuterYn().equals("N")) { NeuterYn = "안함";}
		
		if(choicedAnimal.getSexCd().equals("M")) { SexCd = "수컷";}
		else if(choicedAnimal.getSexCd().equals("F")) { SexCd = "암컷";}

%>
	<!--
        ==================================================
        Global Page Section Start
        ================================================== -->
	<section class="global-page-header">
		<span id="animalDeath" style="display: none;"><%=choicedAnimal.getDeath()%></span>
		<span id="animalKilled" style="display: none;"><%=choicedAnimal.getKilled()%></span>
		<span id="animalReturned" style="display: none;"><%=choicedAnimal.getReturned()%></span>
		<span id="animalBring" style="display: none;"><%=choicedAnimal.getBring()%></span>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="block">
						<h3>새로운 동물 친구를 소개합니다</h3>
						<div class="portfolio-meta">
							<span><h4>등록일 : <%=choicedAnimal.getNoticeSdt()%></h4>
							<h4>상태 : <%=choicedAnimal.getProcessState()%></h4>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--/#Page header-->

	<section class="portfolio-single">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="portfolio-single-img">
                        <p>
                        <div class="container"style="text-align: center;">
							<img  class="img-responsive center-block"
								src="<%=choicedAnimal.getPopfile()%>" >
						</div>
						</p>
					</div>
									
					<div>
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example"
                            style="text-align:center;">
                                <tbody id="list_tbody" >
                                		<tr>
										<td><h4>발견날짜</h4></td>
										<td><h4><%=choicedAnimal.getHappenDt()%></h4></td>
										</tr>
                                		<tr>
										<td><h4>발견장소</h4></td>
										<td><h4><%=choicedAnimal.getHappenPlace()%></h4></td>
										</tr>
										<tr>
										<td><h4>상태</h4></td>
										<td><h4><%=choicedAnimal.getProcessState()%></h4></td>
										</tr>
										<tr>
										<td><h4>공고시작일</h4></td>
										<td><h4><%=choicedAnimal.getNoticeSdt()%></h4></td>
										</tr>
										<tr>
										<td><h4>공고종료일</h4></td>
										<td><h4><%=choicedAnimal.getNoticeEdt()%></h4></td>
										</tr>
										<tr>
										<td><h4>성별</h4></td>
										<td><h4><%=SexCd%></h4></td>
										</tr>
										<tr>
										<td><h4>중성화</h4></td>
										<td><h4><%=NeuterYn%></h4></td>
										</tr>
										<tr>
										<td><h4>품종</h4></td>
										<td><h4><%=choicedAnimal.getKindCd()%></h4></td>
										</tr>
										<tr>
										<td><h4>나이</h4></td>
										<td><h4><%=choicedAnimal.getAge()%></h4></td>
										</tr>
										<tr>
										<td><h4>몸무게</h4></td>
										<td><h4><%=choicedAnimal.getWeight()%></h4></td>
										</tr>
										<tr>
										<td><h4>색깔</h4></td>
										<td><h4><%=choicedAnimal.getColorCd()%></h4></td>
										</tr>
										<tr>
										<td><h4>특징</h4></td>
										<td><h4><%=choicedAnimal.getSpecialMark()%></h4></td>
										</tr>
										<tr>
										<td><h4>입양소주소</h4></td>
										<td><h4><%=choicedAnimal.getCareAddr()%></h4></td>
										</tr>
										<tr>
										<td><h4>입양소이름</h4></td>
										<td><h4><%=choicedAnimal.getCareNm()%></h4></td>
										</tr>
										<tr>
										<td><h4>입양소번호</h4></td>
										<td><h4><%=choicedAnimal.getCareTel()%></h4></td>
										</tr>	
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                            
                        </div>
					
					
					
					
					<div class="portfolio-content"  style="text-align:center;">
							<%
								String beforeAddress = request.getHeader("referer");
								if(beforeAddress.contains("animaldangerlist.jsp")){ %>	
								<div class="overlay" id="overlay">
									<div id="graphPanel" class="panel-body"></div>
								</div>			
							<%}
								else { %>
									<br><br><br><br><br>
								<% } %>
							<div id="button_div" style="text-align: center;">
								<button type="button" class="btn btn-default" onclick="location.href='<%=beforeAddress%>'">뒤로가기</button>
							</div>
				</div>
			</div>
		</div>
	</section>

	<!-- 
        ================================================== 
            Contact Section Start
        ================================================== -->
	<section id="contact-section">
		<div class="container">
			<div class="row">
			<!-- 
				<div class="col-md-6">
					<div class="block">
						<h2 class="subtitle wow fadeInDown" data-wow-duration="500ms"
							data-wow-delay=".3s">메일 보내기</h2>
						<div class="contact-form">
							<form id="contact-form" method="post" action="sendmail.php"
								role="form">

								<div class="form-group wow fadeInDown" data-wow-duration="500ms"
									data-wow-delay=".6s">
									<input type="text" placeholder="이름을 입력하세요" class="form-control"
										name="name" id="name">
								</div>
								<div class="form-group wow fadeInDown" data-wow-duration="500ms"
									data-wow-delay=".8s">
									<input type="email" placeholder="이메일을 입력하세요"
										class="form-control" name="email" id="email">
								</div>
								<div class="form-group wow fadeInDown" data-wow-duration="500ms"
									data-wow-delay="1s">
									<input type="text" placeholder="제목을 입력하세요" class="form-control"
										name="subject" id="subject">
								</div>
								<div class="form-group wow fadeInDown" data-wow-duration="500ms"
									data-wow-delay="1.2s">
									<textarea rows="6" placeholder="내용을 기입하세요" class="form-control"
										name="message" id="message"></textarea>
								</div>
								<div id="submit" class="wow fadeInDown"
									data-wow-duration="500ms" data-wow-delay="1.4s">
									<input type="submit" id="mail-submit"
										class="btn btn-default btn-send" value="보내기">
								</div>

							</form>
						</div>
					</div>
				</div>
			 -->
				<!-- 
				
				분양소 위치 보여주기 
				
				 -->
				<div class="col-md-6" style="width:100%;">
					<div class="map-area">
						<h2 class="subtitle  wow fadeInDown" data-wow-duration="500ms"
							data-wow-delay=".3s">입양소 위치</h2>
						<div class="map">
							<!-- custom -->
							<div id="map" style="width: 100%; height: 400px; border: 0"></div>
							<script type="text/javascript"
								src="//dapi.kakao.com/v2/maps/sdk.js?appkey=37c148dab2cf986f4a6ce194449c733b&libraries=services"></script>
							<script>
								/* static var result_row;
								static var result_col; */

								var mapContainer = document
										.getElementById('map'), // 지도를 표시할 div 
								mapOption = {
									center : new daum.maps.LatLng(33.450701,
											126.570667), // 지도의 중심좌표
									level : 3
								// 지도의 확대 레벨
								};

								// 지도를 생성합니다    
								var map = new daum.maps.Map(mapContainer,
										mapOption);
								// 지도 타입 변경 컨트롤을 생성한다
								var mapTypeControl = new daum.maps.MapTypeControl();
								// 지도의 상단 우측에 지도 타입 변경 컨트롤을 추가한다
								map.addControl(mapTypeControl,
										daum.maps.ControlPosition.TOPRIGHT);
								// 지도에 확대 축소 컨트롤을 생성한다
								var zoomControl = new daum.maps.ZoomControl();
								// 지도의 우측에 확대 축소 컨트롤을 추가한다
								map.addControl(zoomControl,
										daum.maps.ControlPosition.RIGHT);
								
								<%-- 
								// 주소-좌표 변환 객체를 생성합니다
								var geocoder = new daum.maps.services.Geocoder();
								// 지도 중심 좌표 변화 이벤트를 등록한다
								daum.maps.event.addListener(map,
										'center_changed', function() {
											console.log('지도의 중심 좌표는 '
													+ map.getCenter()
															.toString()
													+ ' 입니다.');
										});

								///
								///////callback 객체 생성
								var callback = function(status, result) {
									// 정상적으로 검색이 완료됐으면 
									if (status === daum.maps.services.Status.OK) {
										var coords = new daum.maps.LatLng(
												result.addr[0].lat,
												result.addr[0].lng);
										// 결과값으로 받은 위치를 마커로 표시합니다
										var marker = new daum.maps.Marker({
											map : map,
											position : coords
										});

										// 인포윈도우로 장소에 대한 설명을 표시합니다
										// db에서 위치 이름 가져오기
										var infowindow = new daum.maps.InfoWindow(
												{
													content : '<div style="width:150px;text-align:center;padding:6px 0;"><%=choicedAnimal.getCareNm()%></div>'
												});
										infowindow.open(map, marker);

										// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
										map.setCenter(coords);
										// 지도 중심 좌표 변화 이벤트를 등록한다
										daum.maps.event.addListener(map,
												'center_changed', function() {
													console.log('지도의 중심 좌표는 '
															+ map.getCenter()
																	.toString()
															+ ' 입니다.');
													/* //result_row = map.getCenter().getLat().toString();
													//result_col = map.getCenter().getLng().toString();
													//document.write('<h1><a href=http://map.daum.net/link/to/대구시수의사회(더난),'+ result_row +','+ result_col +'>길찾기주소</h1>');
													document.write('<h1><a href=http://map.daum.net/link/to/대구시수의사회(더난),'+ map.getCenter().getLat() +','+ map.getCenter().getLng() +'>길찾기주소</h1>'); */
												});
									}
								};
								// 주소로 좌표를 검색합니다 //db에서 가져와 주소 붙여넣기
								geocoder.addr2coord({
									addr : '<%=choicedAnimal.getCareAddr()%>',
									callback : callback
								});
								 --%>
								// 주소-좌표 변환 객체를 생성합니다
								var geocoder = new daum.maps.services.Geocoder();

								// 주소로 좌표를 검색합니다
								geocoder.addressSearch('<%=choicedAnimal.getCareAddr()%>', function(result, status) {

								    // 정상적으로 검색이 완료됐으면 
								     if (status === daum.maps.services.Status.OK) {

								        var coords = new daum.maps.LatLng(result[0].y, result[0].x);

								        // 결과값으로 받은 위치를 마커로 표시합니다
								        var marker = new daum.maps.Marker({
								            map: map,
								            position: coords
								        });

								        // 인포윈도우로 장소에 대한 설명을 표시합니다
								        var infowindow = new daum.maps.InfoWindow({
								            content : '<div style="width:150px;text-align:center;padding:6px 0;"><%=choicedAnimal.getCareNm()%></div>'
								        });
								        infowindow.open(map, marker);

								        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
								        map.setCenter(coords);
								    }
								});
							</script>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</section>
	<!--
            ==================================================
            Call To Action Section Start
            입양받고 싶으면 전화해!
            ================================================== -->
	<section id="call-to-action">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="block">
						<h2 class="title wow fadeInDown" data-wow-delay=".3s"
							data-wow-duration="300ms">이 친구를 입양받고 싶나요?</h2>
						<p class="wow fadeInDown" data-wow-delay=".5s"
							data-wow-duration="300ms">
							아래의 번호로 전화해주세요.<br>
						</p>
						<a href="#" class="btn btn-default btn-contact wow fadeInDown"
							data-wow-delay=".7s" data-wow-duration="300ms"><%=choicedAnimal.getOfficetel()%></a>
					</div>
				</div>

			</div>
		</div>
	</section>
	<!-- 
			
			custom
			
			 -->
	<!-- 
	<section id="contact-section">
		<div class="container">
			<div class="row">
				<div class="row address-details" id="custom-container">
					<div class="col-md-3" id="custom-block">
						<div class="address wow fadeInLeft" data-wow-duration="500ms"
							data-wow-delay=".3s">
							<i class="ion-ios-location-outline"></i>
							<h5>
								홈페이지 사무소<br>충남대학교 <br>공학 4층건물
							</h5>
						</div>
					</div>
					<div class="col-md-3" id="custom-block">
						<div class="email wow fadeInLeft" data-wow-duration="500ms"
							data-wow-delay=".7s">
							<i class="ion-ios-email-outline"></i>
							<p>
								홈페이지 메일<br>tkdjun67@naver.com<br>52121813@dankook.ac.kr
							</p>
						</div>
					</div>
					<div class="col-md-3" id="custom-block">
						<div class="phone wow fadeInLeft" data-wow-duration="500ms"
							data-wow-delay=".9s">
							<i class="ion-ios-telephone-outline"></i>
							<p>
								홈페이지 전화번호<br>041 572 1234<br>010 9999 2222
							</p>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>
 -->
	<!-- footer -->
	<%@ include file="./footer.jsp"%>

</body>
</html>