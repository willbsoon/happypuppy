<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>

<!DOCTYPE html>
<html class="no-js">
<head>
<!-- Basic Page Needs
        ================================================== -->
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" type="image/png" href="../images/favicon.png">
<title>검색하여 보기</title>
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
<!-- 나만의 css -->
<link rel="stylesheet" href="../css/custom.css">
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
<!-- jquery -->
<script src="../js/jquery.min.js"></script>
<!-- bootstrap js -->
<script src="../js/bootstrap.min.js"></script>
<!--================================================== -->					
<%
Calendar cal = Calendar.getInstance();
String bgnde = String.format("%02d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH));
String endde = String.format("%02d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
		cal.get(Calendar.DAY_OF_MONTH));
String bgnde_3 = String.format("%02d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 2,
		cal.get(Calendar.DAY_OF_MONTH));
%>
<script type="text/javascript">
function checkValue() {
	var year_bg = document.puppy.bgnde.value.split('-')[0];
	var month_bg = document.puppy.bgnde.value.split('-')[1];
	var day_bg = document.puppy.bgnde.value.split('-')[2];
	
	var year_en = document.puppy.endde.value.split('-')[0];
	var month_en = document.puppy.endde.value.split('-')[1];
	var day_en = document.puppy.endde.value.split('-')[2];
	
	if (year_bg > year_en) {
		alert("시작날짜가 종료날짜보다 앞섭니다!");
		return false;
	}
	else {
		if(month_bg > month_en) {
			alert("시작날짜가 종료날짜보다 앞섭니다!");
			return false;
		}
		else if(month_bg == month_en) {
			if(day_bg > day_en) {
				alert("시작날짜가 종료날짜보다 앞섭니다!");
				return false;
			}
		}
	}
}
</script>
<script type="text/javascript">
function button_click() {
	document.getElementById("bgnde").value = "<%=bgnde_3%>";
}
</script>
<!--================================================== -->
</head>
<body>
<%@ include file="./header.jsp"%>
<br><br><br>
	<div class="container">
		<form class="form-horizontal" id="survey" name="puppy" action="animalSearch.puppy" onsubmit="return checkValue()">
		<!-- method="post" -->
		<fieldset>
				<!-- Form Name -->
				<br><br><br><br><br><br>		
				<!-- Select Basic -->
				<!-- <div class="form-group">
					<label class="col-md-4 control-label" for="address">
					무슨 일로 오셨나요?</label>
					<div class="col-md-4">
						<select id="typesite" name="state" class="form-control">
							<option value="notice">잃어버린 동물을 찾기 위해서</option>
							<option value="protect">동물을 입양을 하고 싶어서</option>		
						</select>
					</div>
				</div> -->
				<!-- Select Basic -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="typesite">
						날짜 입력</label>
					<div class="col-md-4">
				<fieldset>
					<div>
					시작날짜 : <input type="date" name="bgnde" id="bgnde"
							maxlength="10" value=<%=bgnde%>><br>
					종료날짜 : <input type="date" name="endde" id="endde"
							maxlength="10" value=<%=endde%>>
					</div>
					<div>
					<button type="button" class="btn btn-info" onclick="button_click();" style="position:relative; left:70px">
						 기간을 3개월로!<i class="fa fa-check spaceLeft"></i>
					</button>
					</div>
				</fieldset>
				</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label" for="typesite">
						거주지 입력</label>
					<div class="col-md-4">
						<select id="typesite" name="upr_cd" class="form-control">
							<option value="6110000">서울특별시</option>
							<option value="6260000">부산광역시</option>
							<option value="6270000">대구광역시</option>
							<option value="6280000">인천광역시</option>
							<option value="6290000">광주광역시</option>
							<option value="5690000">세종특별자치시</option>
							<option value="6300000">대전광역시</option>
							<option value="6310000">울산광역시</option>
							<option value="6410000">경기도</option>
							<option value="6420000">강원도</option>
							<option value="6430000">충청북도</option>
							<option value="6440000">충청남도</option>
							<option value="6450000">전라북도</option>
							<option value="6460000">전라남도</option>
							<option value="6470000">경상북도</option>
							<option value="6480000">경상남도</option>
							<option value="6500000">제주특별자치도</option>
						</select>
					</div>
				</div>
				
				<!-- Multiple Checkboxes -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="useageboxes">
					입양받기 원하시는 동물</label>
					<div class="col-md-4">
						<div class="checkbox">
							<label for="useageboxes-0"> <input type="radio"
								name="upkind" id="useageboxes-0" value="417000" checked>
								강아지
							</label>
						</div>
						<div class="checkbox">
							<label for="useageboxes-1"> <input type="radio"
								name="upkind" id="useageboxes-1" value="422400">
								고양이
							</label>
						</div>
						<div class="checkbox">
							<label for="useageboxes-2"> <input type="radio"
								name="upkind" id="useageboxes-2" value="429900">
								개성강한동물(예:돼지,토끼 등등)
							</label>
						</div>
					</div>
				</div>

				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="submitfull"><span
						style="font-size: 110%; color: blue;">
						</span></label>
					<div class="col-md-6">
						<button type="submit" id="submitfull" class="btn btn-primary"
							style="width: 360px;">찾기!</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</body>