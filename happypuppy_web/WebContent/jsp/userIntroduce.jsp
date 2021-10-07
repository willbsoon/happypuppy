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
<title>HappyPuppy 소개</title>
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
<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
<script src="http://d3js.org/topojson.v2.min.js"></script>
<style id="style-1-cropbar-clipper">/* Copyright 2014 Evernote Corporation. All rights reserved. */
.en-markup-crop-options {
    top: 18px !important;
    left: 50% !important;
    margin-left: -100px !important;
    width: 200px !important;
    border: 2px rgba(255,255,255,.38) solid !important;
    border-radius: 4px !important;
}
.en-markup-crop-options div div:first-of-type {
    margin-left: 0px !important;
}
</style>
<style>
svg circle {
	fill: orange;
	opacity: .5;
	stroke: white;
}

svg circle:hover {
	fill: red;
	stroke: #333;
}

svg text {
	pointer-events: none;
}

svg .municipality {
	fill: #efefef;
	stroke: #fff;
}

svg .municipality-label {
	fill: #bbb;
	font-size: 12px;
	font-weight: 300;
	text-anchor: middle;
}

svg #map text {
	color: #333;
	font-size: 10px;
	text-anchor: middle;
}

svg #places text {
	color: #777;
	font: 10px sans-serif;
	text-anchor: start;
}
</style>
<script>
$(document).ready(function() {
var animalTable = $(this).find("#animalTable");
var animalTableSize = $("#animalTable tr").length;
console.log(parseInt(animalTable.find("tr:nth-child(1)").find("td").html()));

d3.json("../skorea-provinces-topo2.json", function(error, data) {
	var state_features = topojson.feature(data, data.objects["skorea-provinces-geo"]).features;
	var w = 120;// 100
    var h = 150;// 100
    var barPadding = 1;// 1
    var width = 300, height = 100;
    
    var bar_section = d3
    .select("#chart")
    .append("section")
    .attr("id", "bar_section")
    .attr("height",height)
    
    
    var bar_section_div_div = d3
    .select("#chart")       
    .select("#bar_section")
    .append("div")
    .selectAll("path").data(state_features).enter()
    .append("span")
	.attr("id", function (data) { return "bar_" + data.properties["NAME_1"];})
	.text(function(data) {
        return " " + data.properties["NAME_1"]+" ";
		})
	.append("svg")
    .attr("id", function (data) { return "svg_" + data.properties["NAME_1"];})
    .attr("width", w)
    .attr("height", h)
    
    // /
    var bar_select = d3
	.select("#chart")       
    .select("#bar_section");
    ///

    
	var arrDataIn = new Array();
	
	
	var dataset_list = new Array();
	var m = 0;
	for (var i= 0; i<animalTableSize; i=i+2){
		var j = i + 1 ;
		var k = i + 2 ;
		arrDataIn[i] = parseInt(animalTable.find("tr:nth-child("+j+")").find("td").html());
		arrDataIn[j] = parseInt(animalTable.find("tr:nth-child("+k+")").find("td").html());
		dataset_list[m]=[arrDataIn[i],arrDataIn[j]];
		m++;
	}	
    
    var bar_id = ["#bar_부산광역시","#bar_충청북도","#bar_충청남도","#bar_대구광역시","#bar_대전광역시",
    	"#bar_강원도","#bar_광주광역시","#bar_경기도","#bar_경상북도","#bar_경상남도",
    	"#bar_인천광역시","#bar_제주특별자치도","#bar_전라북도","#bar_전라남도","#bar_서울특별시",
    	"#bar_울산광역시"];
    
    for (i = 0; i < bar_id.length; i++) { 
    	bar_select
        .select(bar_id[i])
        .select("svg")
		.selectAll("rect")
		.data(dataset_list[i]).enter()
		.append("rect")
		.attr("x", function(data, j) {
			return j * (w / dataset_list[i].length);
		})
		.attr("y", function(data) {
			return h - (data/50);
                   })
        .attr("width", w / dataset_list[i].length - barPadding)
        .attr("height", function(data) {return 500;})
        .attr("fill", function(data) {
        	   if(data>=0&&data<1200) {
        		   r = 0;
        		   g = 255;
        		   b = 0;
        	   }
        	   else if(data>=1200&&data<1700) {
        		   r = 50;
        		   g = 150;
        		   b = 0;
        	   }
        	   else if(data>=1700&&data<2200) {
        		   r = 100;
        		   g = 150;
        		   b = 0;
        	   }
        	   else if(data>=2200&&data<3100) {
        		   r = 200;
        		   g = 150;
        		   b = 0;
        	   }
        	   else if(data>=3100&&data<4200) {
        		   r = 200;
        		   g = 100;
        		   b = 0;
        	   }
        	   else if(data>=4200&&data<4700) {
        		   r = 200;
        		   g = 75;
        		   b = 0;
        	   }
        	   else if(data>=4700&&data<5200) {
        		   r = 255;
        		   g = 60;
        		   b = 0;
        	   }
        	   else if(data>=5200&&data<5700) {
        		   r = 255;
        		   g = 40;
        		   b = 0;
        	   }
        	   else if(data>=5700&&data<6200) {
        		   r = 255;
        		   g = 25;
        		   b = 0;
        	   }
        	   else if(data>=6200) {
        		   r = 255;
        		   g = 0;
        		   b = 0;
        	   }   
                return "rgb("+ r +","+ g +"," + b +")";
           });
    	
    	bar_select
        .select(bar_id[i])
        .select("svg")
        .selectAll("text")
           .data(dataset_list[i])
           .enter()
           .append("text")
           .text(function(data) {
                   return data;
           })
           .attr("text-anchor", "middle")
           .attr("x", function(data, j) {
        	   return j * (w / dataset_list[i].length) + (w / dataset_list[i].length - barPadding) / 2;
           })
           .attr("y", function(data) {
        	   return h - 10 ;
           })
           .attr("font-family", "sans-serif")
           .attr("font-size", "15px")
           .attr("fill", "black");

    }
});
});


</script>



</head>

<body>
	<%@include file="./header.jsp"%>
	<%ArrayList introdata = new ArrayList();
	if (request.getSession().getAttribute("introdata")==null){%>
   <jsp:forward page="userIntroduce.puppy"/>
<%} else {
	introdata = (ArrayList) request.getSession().getAttribute("introdata");
}
%>
	<!-- 
        ================================================== 
            Global Page Section Start
        ================================================== -->
        <section class="global-page-header">
            <div class="container">
            <table id="animalTable" style="display:none;">

			<%for (int i = 0; i < introdata.size(); i++) { %>
			<tr>
				<td><%=introdata.get(i).toString()%></td>
			</tr>
		<% } %>

		</table>
                <div class="row">
                    <div class="col-md-12">
                        <div class="block">
                            <h2>소개</h2>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- 
        ================================================== 
            Company Description Section Start
        ================================================== -->
        <section class="company-description">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 wow fadeInLeft" data-wow-delay=".3s" >
                        <img src="../images/dog.png" alt="" class="img-responsive">
                    </div>
                    <div class="col-md-6">
                        <div class="block">
                            <h3 class="subtitle wow fadeInUp" data-wow-delay=".3s" data-wow-duration="500ms">제작 의도</h3>
                            <p  class="wow fadeInUp" data-wow-delay=".5s" data-wow-duration="500ms">
                                &nbsp;현재 개와 고양이는 우리나라 국민 5인당 1인의 반려동물이 될 만큼 우리 삶의 가장 가까운 동반자로 다가와 있습니다. 하지만 이와 함께 엄청난 수로 번식 되고 관리되지 못한 동물들 또한 방치되고 다시 버려져 거리로 쏟아져 나오게 되었습니다. 유기되거나 학대 받는 동물에 대한 사회적 관심이 높아지면서 유기동물 발생 문제는 그 어느 때보다 사회의 중요한 쟁점으로 떠오르고 있습니다.
                            </p>
                            <p  class="wow fadeInUp" data-wow-delay=".7s" data-wow-duration="500ms">
                                &nbsp;그래서 저희는 사회에 도움이 되는 유기동물의 안락사를 줄여보고자 이 서비스를 계획하고 실행하였습니다.
                                <br />&nbsp;공공데이터포털에서 유기동물관련API를 이용하여 유기 동물의 데이터를 가져옵니다.
                               그리고 데이터를 이용하여 유기 동물을 추천하는 서비스를 제공합니다.
                                또한 공공데이터 기계학습을 활용하여 유기견 공고 종료 결과를 예측하여 안락사될 확률이 높은 강아지를 알려주는 서비스를 제공합니다.
                            </p> 
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <!-- 
        ================================================== 
            Company Feature Section Start
        ================================================== -->
        <section class="about-feature clearfix">
        <h2 class="subtitle text-center wow fadeInDown" data-wow-duration="500ms" data-wow-delay=".3s">관련 기사들</h2>
            <div class="container-fluid">
                <div class="row">
                    <div class="block about-feature-1 wow fadeInDown" data-wow-duration="500ms" data-wow-delay=".3s">
                        <h3 class="item_title">
                        <a href="http://mnews.joins.com/article/21855222#home">유기견 보호센터에 해마다 찾아오는 ‘여름의 비극'</a>
                        </h3>
                        <p>
                        	<h4>중앙일보</h4><br/>
                           &nbsp;여름철에는 통상 20일 뒤쯤 안락사를 당한다고 한다. 수용 공간과 예산에 한계가 있어 장기 보호가 어렵기 때문이다. 지난해 6~8월 사이 버려진 동물은 총 2729 마리로 그해 2월까지의 3개월간 유기 동물 수(1408 마리)의 두 배에 달했다.
                        </p>
                    </div>
                    <div class="block about-feature-2 wow fadeInDown" data-wow-duration="500ms" data-wow-delay=".5s">
                        <h3 class="item_title">
                        <a href="http://fromcare.org/archives/20668">[보고서] 전국 지자체 유기동물 보호소 현황 및 실태</a>
                        </h3>
                        <p>
                            <h4>동물권단체 케어</h4><br/>
                            &nbsp;운영과 예산 부분이 동물 복지를 실현하기에는 너무나도 부족하다는 것과, 상당 부분은 운영자와 관리자의 교육의 중요성도 정부정책에 반영되어야 한다는 것이었습니다. 또한 시설뿐만 아니라 운영기준이 법적으로 뒷받침되어야 한다는 것입니다.
                        </p>
                    </div>
                    <div class="block about-feature-3 wow fadeInDown" data-wow-duration="500ms" data-wow-delay=".7s">
                        <h3 class="item_title">
                        <a href="https://www.youtube.com/watch?v=d0zDABC5kMU">어렵게 구조돼도 '안락사'.. 매년 천마리 넘어</a>
                        </h3>
                        <p>
                            <h4>TJB뉴스</h4><br/>
                            &nbsp;거리를 떠도는 개나 고양이들을 구조해 동물보호센터로 보내게 되는데. 포화상태이다 보니 그중 1/3 은 결국 안락사를 당하게 됩니다. 병에 걸리면 귀찮다고 버리는 실태에 대전서만 매년 천마리 넘게 안락사를 당하고 있습니다.
                        </p>
                    </div>
                </div>
            </div>
        </section>

        <!--
        ==================================================
        Clients Section Start
        ================================================== -->
        <section id="clients">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="subtitle text-center wow fadeInDown" data-wow-duration="500ms" data-wow-delay=".3s">국내 6개월 동안의 유기동물 발생지도</h2>
                        <p class="subtitle-des text-center wow fadeInDown" data-wow-duration="500ms" data-wow-delay=".5s">왼쪽 : 유기동물 발생수 , 오른쪽 : 안락사수</p>
                        <div id="chart" style="text-align: center;"></div>
                        <script>
                     	//MAP만드는 스크립트
                     	$(document).ready(function() { 
                        var width = 600, height = 600;

                        var svg = d3.select("#chart")
                        .append("svg")
                        .attr("align", "center")
                        .attr("width", width)
                        .attr("height",height);

                        var projection = d3.geo.mercator().center(
                        		[ 126.08180236816418, 33.11208343505865 ])//좌표 중앙지정
                        .scale(5000).translate([ width / 4, height ]);

                        var path = d3.geo.path().projection(projection);

                        d3.json("../skorea-provinces-topo2.json", function(error, data) {
                        	var state_features = topojson.feature(data,
                        			data.objects["skorea-provinces-geo"]).features;

                        	var map = svg.selectAll("path").data(state_features).enter()
                        	.append("path")
                        	.attr('id', function(data) {
                        		return "sec_" + data.properties["NAME_1"];
                        	}).attr("d", path)
                        	.attr("fill", function(data) {
                        		var svgid = document.getElementById("svg_"+ data.properties["NAME_1"]);
                        		var first = svgid.children[0].getAttribute("fill");//바차트의 안락사색깔가져옴
                        		return first;
                        		})
                        	//.attr("fill", function(d) {return Math.random().toString(16).replace(/.*(\w{3})/, '#$1');})//랜덤색깔지정
                        });
                     	});
                        </script>
                    </div>
                </div>
            </div>
        </section>
        
        <!--
        ==================================================
        Call To Action Section Start
        ================================================== -->
        <section id="call-to-action">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block">
                            <h2 class="title wow fadeInDown" data-wow-delay=".3s" data-wow-duration="500ms">무슨 생각이 드시나요?</h1>
                            <p class="wow fadeInDown" data-wow-delay=".5s" data-wow-duration="500ms">유기 동물이 헌 동물을 뜻하는 것이 아닙니다. 사람의 손을 그리워하는 동물일 뿐입니다.</br>우리 유기동물들에게 다가가고 싶다면 아래의 버튼을 눌러주세요.</p>
                            <a href="animalDanger.puppy" class="btn btn-default btn-contact wow fadeInDown" data-wow-delay=".7s" data-wow-duration="500ms">위급한 동물 보기</a>
                        </div>
                    </div>
                    
                </div>
            </div>
        </section>
	<!-- #works -->
	<!-- footer -->
	<%@ include file="./footer.jsp"%>
</body>
</html>