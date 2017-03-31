<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Booking_Date</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="./font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="./css/nomalize.css" rel="stylesheet">
<link type="text/css" href="./css/sel_date.css" rel="stylesheet">
<style type="text/css">
/* 네비게이션 바 */
#header {
	width: 1000px;
	padding: 0px;
}

.navbar-brand {
	padding-top: 10px;
	padding-right: 30px;
}

.navbar-default {
	background-color: white;
}

#menu li {
	border-left: 1px solid #EAEAEA;
	width: 112px;
	height: 49px;
	text-align: center;
}

#liLogin {
	border-right: 1px solid #EAEAEA;
	width: 158px !important;
}

#menu li a {
	font-weight: bold;
}
/* 예매페이지 */
.col-md-6 {
	padding: 0;
}

#line {
	border-right: 1px solid #EAEAEA;
}

#detail {
	width: 1000px;
	height: 700px;
	border: 1px solid #EAEAEA;
	margin-top: 100px;
	box-shadow: 2px 2px 20px gray;
}

#detail h3 {
	color: #3F0099;
	margin: 0;
	font-weight: bold;
}

#titleBox {
	background-color: #F6F6F6;
	padding: 20px 15px 20px 15px;
	border-bottom: 1px solid #EAEAEA;
}

#titleBox h3 {
	padding-bottom: 14px;
}
/* 선택창 */
#date {
	overflow-y: scroll;
	height: 582px;
	margin: 0 auto;
}

#time {
	overflow-y: scroll;
	height: 500px;
	margin: 0 auto;
}

#next {
	text-align: center;
	padding: 28px 10px;
	border-top: 1px solid #EAEAEA;
}

#dateOl {
	width: 2040px !important;
	left: 0px;
}

/* #nextHidden { display: none}; */
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/sel_date.js"></script>
<script src="js/sel_time.js"></script>
<script type="text/javascript">
	// 시간 선택
	 function ajaxcall(where) {

		var temp = $(where).parent().attr("data-item");/* yyyy-mm-dd */

		//console.log($("#theater option:selected").val());
		//console.log($("#movie option:selected").val());
		$.ajax({
			url : "seltime.do",
			type : "post",
			timeout : 30000,
			datatype : "json",
			data : {
					"theaterNo" : $("#theater option:selected").val(),
					"movieNo" : $("#movie option:selected").val(),
					"dateNo" : temp
					},
			success : function(data) {
				console.log(data);
				
				var movieTimes;
				var movieNo;
				var dayno;
				var scheduleNo;
				var audiNo;
				if (data == "") {
					movieTimes += " 조회된 영화가 없습니다. ";
				}
				$("#showTimes").html(movieTimes);
				for (var i = 0; i < data.length; i++) {
					console.log(data[i].schedule.startTime.hour)
					movieTimes += "<tr><td>"
								+ data[i].schedule.startTime.hour
								+ ":"
								+ data[i].schedule.startTime.minute
								+ " ~ "
								+ data[i].schedule.endTime.hour
								+ ":"
								+ data[i].schedule.endTime.minute
								+ "</td><td><a href='#' data-scheduleNo="+data[i].schedule.scheduleNo+" onclick='scheduleNocall(this)' >"
								+ data[i].schedule.screen.movie.korTitle
								+ "</a></td><td>"
								+ data[i].theater.theaterName + "<br>"
								+ data[i].auditorium.audiName
								+ "</td></tr>";
					movieNo = data[i].schedule.screen.movie.movieNo
					scheduleNo = data[i].schedule.scheduleNo
					audiNo = data[i].auditorium.audiNo
					}
				$("#showTimes").html(movieTimes);
				$('input[name=audiNo]').attr('value', audiNo);
				/* $('input[name=dayno]').attr('value', temp);
				$('input[name=scheduleNo]').attr('value', scheduleNo); */
				}
					
			});
	}
	
	function scheduleNocall(where) {
		var temp = $(where).attr("data-scheduleNo");
		$("input[name=scheduleNo]").attr('value', temp);
		}
	

	$(function() {
		//극장선택
		$("#theater").change(function() {
			$.ajax({
				url : "selmovie.do",
				type : "post",
				timeout : 30000,
				datatype : "json",
				data : {
						"theaterNo" : $("#theater option:selected").val()},
				success : function(data) {
					console.log(data);
					var korTitle = data[0].schedule.screen.movie.korTitle;
					var theaterNo;
					//alert(korTitle);				
					for (i = 0; i < data.length; i++) {
						if (data == "") {
							korTitle = "";
						} else {
							korTitle += "<option value='"+data[i].movie.movieNo+"'>"
									+ data[i].schedule.screen.movie.korTitle
									+ "</option>";
							theaterNo = data[i].theater.theaterNo
						}
					}
					$("#movie").html(korTitle);
					$('input[name=theaterNo]').attr('value', theaterNo);
				}
			});
		});

		var t = new Date();
		var nowYear = t.getFullYear();
		var nowMonth = t.getMonth() + 1;
		var nowDate = t.getDate();
		var nowDay = t.getDay();
		var date = t.setDate(t.getDate() + 0);
		//console.log();		
		//console.log(t.getDate());

		var selDate = "";
		for (i = 0; i < 31; i++) {
			t.setDate(t.getDate());
			selDate += "<li data-item='" + t.getFullYear() + "-"
					+ (t.getMonth() + 1) + "-" + t.getDate()
					+ "'><a href='#' class='active' onclick='ajaxcall(this)'>"
					+ t.getDate() + "</a></li>";
			t.setDate(t.getDate() + 1);
			//console.log(t.getDate());
		}
		$("#dateOl").html(selDate); 

	}); // end ready
</script>
</head>
<body data-spy="scroll">
	<div class="container-fluid">

		<!-- 네비게이션 바 -->
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="navbar-scroll">
			<div class="container" id="header">
				<a class="navbar-brand" href="#">
					<img src="image/logo.png">
				</a>
				<ul class="nav navbar-nav" id="menu">
					<li><a href="#">영화</a></li>
					<li><a href="#">큐레이션</a></li>
					<li><a href="#">영화관</a></li>
					<li><a href="#">특별관</a></li>
					<li><a href="#">스토어</a></li>
					<li><a href="#">이벤트</a></li>
					<li id="liLogin"><a href="#">로그인</a></li>
				</ul>
			</div>
		</nav>

	</div>


	<div class="container" id="detail">
		<div class="row">
			<div class="col-md-6" id="line">
				<div id="titleBox">
					<a href=""></a>
					<h3>날짜</h3>
					<!-- select -->
					<div class="slidebar" id="sel_date">
						<ul class="slidebar_control">
							<li><i class="fa fa-caret-left prev"><span class="blind">이전날짜보기</span></i></li>
							<li><i class="fa fa-caret-right next"><span class="blind">다음날짜보기</span></i></li>
						</ul>

						<div class="slidebar_item" tabindex="0">
							<!-- <ol style="width: 2040px; left: 0px;" id="dateOl"> -->
							<ol style="width: 2040px; left: 0px;" id="dateOl">
								<!-- <li data-item="2017-03-13"><a href="#" class="active" onclick="return false;" title="선택됨"> 오늘 </a></li>
								<li data-item="2017-03-14"><a href="#" class="" onclick="return false;" title=""> 14(화) </a></li>
								<li data-item="2017-03-15"><a href="#" class="" onclick="return false;" title=""> 15(수) </a></li>
								<li data-item="2017-03-16"><a href="#" class="" onclick="return false;" title=""> 16(목) </a></li>
								<li data-item="2017-03-17"><a href="#" class="" onclick="return false;" title=""> 17(금) </a></li>
								<li data-item="2017-03-18"><a href="#" class="" onclick="return false;" title=""> 18(토) </a></li>
								<li data-item="2017-03-19"><a href="#" class="" onclick="return false;" title=""> 19(일) </a></li>
								<li data-item="2017-03-20"><a href="#" class="" onclick="return false;" title=""> 20(월) </a></li>
								<li data-item="2017-03-21"><a href="#" class="" onclick="return false;" title=""> 21(화) </a></li>
								<li data-item="2017-03-22"><a href="#" class="" onclick="return false;" title=""> 22(수) </a></li>	 -->
							</ol>
						</div>
					</div>
					<!-- end sel_date -->
				</div>


				<div id="date">
					<table class="table table-hover">
						<tr>
							<select name="theater" id="theater">
								<option value="">극장 선택</option>
								<c:forEach var="selTheater" items="${selTheater }">
									<option value="${selTheater.theaterNo }">${selTheater.theaterName }</option>
								</c:forEach>
							</select>

						</tr>

					</table>
					<table class="table table-hover">
						<tr>
							<select name="movie" id="movie">
								<option value="">영화 선택</option>
							</select>
						</tr>

					</table>
				</div>
			</div>


			<div class="col-md-6">
				<div id="titleBox">
					<h3>시간</h3>
					<!-- select -->
					<div class="slidebar" id="sel_time">
						<ul class="slidebar_control">
							<li><i class="fa fa-caret-left prev"><span class="blind">이전날짜보기</span></i></li>
							<li><i class="fa fa-caret-right next"><span class="blind">다음날짜보기</span></i></li>
						</ul>

						<div class="slidebar_item" tabindex="0">
							<ol style="width: 2040px; left: 0px;">
								<li data-item="2017-03-13"><a href="#" class="active" onclick="return false;" title="선택됨"> 0 </a></li>
								<li data-item="2017-03-14"><a href="#" class="" onclick="return false;" title=""> 1 </a></li>
								<li data-item="2017-03-15"><a href="#" class="" onclick="return false;" title=""> 2 </a></li>
								<li data-item="2017-03-16"><a href="#" class="" onclick="return false;" title=""> 3 </a></li>
								<li data-item="2017-03-17"><a href="#" class="" onclick="return false;" title=""> 4 </a></li>
								<li data-item="2017-03-18"><a href="#" class="" onclick="return false;" title=""> 5 </a></li>
								<li data-item="2017-03-19"><a href="#" class="" onclick="return false;" title=""> 6 </a></li>
								<li data-item="2017-03-20"><a href="#" class="" onclick="return false;" title=""> 7 </a></li>
								<li data-item="2017-03-21"><a href="#" class="" onclick="return false;" title=""> 8 </a></li>
								<li data-item="2017-03-22"><a href="#" class="" onclick="return false;" title=""> 9 </a></li>
								<li data-item="2017-03-23"><a href="#" class="no_click " onclick="return false;" title=""> 10 </a></li>
								<li data-item="2017-03-24"><a href="#" class="no_click " onclick="return false;" title=""> 11 </a></li>
								<li data-item="2017-03-25"><a href="#" class="" onclick="return false;" title=""> 12 </a></li>
								<li data-item="2017-03-26"><a href="#" class="" onclick="return false;" title=""> 13 </a></li>
								<li data-item="2017-03-27"><a href="#" class="no_click " onclick="return false;" title=""> 14 </a></li>
								<li data-item="2017-03-28"><a href="#" class="" onclick="return false;" title=""> 15 </a></li>
								<li data-item="2017-03-29"><a href="#" class="" onclick="return false;" title=""> 16 </a></li>
								<li data-item="2017-03-30"><a href="#" class="no_click " onclick="return false;" title=""> 17 </a></li>
								<li data-item="2017-03-31"><a href="#" class="no_click " onclick="return false;" title=""> 18 </a></li>
								<li data-item="2017-04-01"><a href="#" class="no_click " onclick="return false;" title=""> 19 </a></li>
								<li data-item="2017-04-02"><a href="#" class="" onclick="return false;" title=""> 20 </a></li>
								<li data-item="2017-04-03"><a href="#" class="no_click " onclick="return false;" title=""> 21 </a></li>
								<li data-item="2017-04-04"><a href="#" class="no_click " onclick="return false;" title=""> 22 </a></li>
								<li data-item="2017-04-05"><a href="#" class="" onclick="return false;" title=""> 23 </a></li>
								<li data-item="2017-04-06"><a href="#" class="no_click " onclick="return false;" title=""> 24 </a></li>
							</ol>
						</div>
					</div>
					<!-- end sel_date -->
				</div>

				<div id="time">
					<table class="table table-hover" id="showTimes">
						<!-- <tr >
							<td>07:40 ~ 10:08</td>
							<td>로건</td>
							<td>메가박스 동대구</td>
						</tr> -->
					</table>
				</div>
				<div id="next">

					<form action="selseat.do" method="post">
						<input type="text" name="audiNo" /> <input type="text" name="scheduleNo" /> <input type="reset" value="취소" /> <input type="submit" value="다음" />
					</form>

				</div>
			</div>
		</div>

	</div>

	<!-- 	<form action="" name="info1">
		<input type="text" name="theaterNo" />
		<input type="text" name="movieNo" /> 
		<input type="text" name="dayno" class="hidden_date" />		
	</form> -->
</body>
</html>