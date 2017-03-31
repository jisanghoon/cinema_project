<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Booking_Seat</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/nomalize.css" rel="stylesheet">
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
#detail {
	width: 1000px;
	border: 1px solid #EAEAEA;
	margin-top: 100px;
	box-shadow: 2px 2px 20px gray;
	padding: 0;
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
/* 좌석 */
#seatBox {
	margin: 20px 30px 10px 50px;;
	width: 598px;
	display: inline-block;
	text-align: center;
}

#text {
	padding: 10px;
}

#screen {
	border: 1px solid #EAEAEA;
	text-align: center;
	padding: 8px;
	margin-bottom: 20px;
	background-color: #A6A6A6;
	color: white;
}

#screen p {
	margin: 0;
}

.seat {
	margin: 0;
	font-size: 10px;
}

.seat p {
	font-size: 10px;
}

#column {
	width: 30px;
	height: 34px;
	display: inline-block;
	text-align: center;
	border: 1px solid #EAEAEA;
	margin-right: 5px;
	padding: 6px;
}

#column p {
	margin: 0;
}

#void {
	width: 30px;
	display: inline-block;
	text-align: center;
	padding: 6px;
}

input[type=checkbox] {
	display: none;
	
}

input[type=checkbox]+label {
	border: 1px solid #EAEAEA;
	padding: 6px;
	background-color: #D5D5D5;
	width: 30px;
	text-align: center;
	color: white;
}

input[type=checkbox]:checked+label {
	border: 1px solid #3F0099;
	background-color: #3F0099;
	color: white;
}
/* 리스트 */
#list {
	display: inline-block;
	text-align: center;
	float: right;
	padding: 60px 50px 128px 50px;
	border-left: 1px solid #EAEAEA;
}

#list p {
	width: 200px;
	margin-bottom: 10px;
}

#list img {
	width: 200px;
}

#select {
	background-color: #F6F6F6;
	margin-top: 20px;
	padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		var seatList = $.parseJSON('${seatList}');
		var seatCnt = $.parseJSON('${seatCnt}');

		console.log(seatList);
		console.log(seatCnt);

		var $seat = $("#seatBox #seatWrap");

		for (var i = 0; i < seatCnt.rowCnt; i++) {
			var divSeat = $("<div>").attr("class", "seat");

			var txt = String.fromCharCode(65 + i);
			//abcd...
			var $rowTile = $("<input>").attr("type", "checkbox")
					.attr("id", txt).attr("onclick", "return false");

			$rowTile.val(txt);
			console.log(seatList, " <= seatList")
			divSeat.append($rowTile)
			// seatName

			divSeat.append("<label for='"+seatList[i*seatCnt.colCnt].seatName+"' style='margin-right:13px'>"
							+ seatList[i * seatCnt.colCnt].seatName.split('-')[0]
							+ "</label>")

			for (var j = 0; j < seatCnt.colCnt; j++) {
				if (seatList[i * seatCnt.colCnt + j].seatName != "empty") {
					divSeat.append("<input type='checkbox' value='"+seatList[i*seatCnt.colCnt + j].seatName+"' id='"+seatList[i*seatCnt.colCnt + j].seatName+"'>");
 
				}

				divSeat.append("<label for='"+seatList[i*seatCnt.colCnt + j].seatName+"'>"
								+ seatList[i * seatCnt.colCnt + j].seatName
										.split('-')[1] + "</label>")

			}

			$seat.append(divSeat);
			$('label[for="empty"]').css({
				'opacity' : '0',

			});
			console.log(txt);

		}

		$(document).on("click","#seatWrap input[type='checkbox']",function(){
			if($("#selAge").val() == "") {
				alert("선택");
				return false;				
				};
			var dataNo = 0;
			var price = 0;
			var persons = 0;
			var seatNum = "";
			$("#seatWrap input[type='checkbox']").each(function(i, obj) {

				//check된 녀석의 값만 들어감
				if($(obj).prop("checked") == true){				
						price += Number($("#selAge").val());
						persons++;
						seatNum += $(this).val() + " ";
				}
			});
			
			$("#totalPrice").html(price + " 원");
			$("#persons").html(persons + "명");
			$("#seatNum").html(seatNum);
			
		});

		//가격넘버보내기
		$(document).on("change", function() {
			var element = $(this).find('option:selected'); 
            var myTag = element.attr("data-no");
			console.log(myTag);
            $('#priceNo').val(myTag);

		});
		
		//좌석선택
		$.ajax({
			url : "selprice.do",
			type : "post",
			timeout : 30000,
			datatype : "json",
			data : {"cateTime" : $("#cateTime").val()},
			success : function(data) {
				console.log(data);
				var selPrice;

				$("#selAge").html("<option value=''>선택 </option>");
				for(var i = 0; i< data.length; i++) {
					selPrice += ("<option value='"+data[i].price+"' data-no='"+data[i].priceNo+"'>"+data[i].cateAge +"</option>");
					}
				$("#selAge").append(selPrice);				
			}
		});
		
	}); //end ready
</script>
</head>
<body>
	<div class="container-fluid">

		<!-- 네비게이션 바 -->
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="navbar-scroll">
			<div class="container" id="header">
				<a class="navbar-brand" href="#"><img src="image/logo.png"></a>
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
		<div id="titleBox">
			<h3>인원 / 좌석 선택</h3>
		</div>


		<div id="seatBox">
			<form action="">
				<select name="cateAge" id="selAge">
					
				</select>

				<!-- <p id="text"> -->
				※ 인원선택은 최대 8명까지 가능합니다.
				<!-- </p> -->


				<div id="screen">
					<p>SCREEN</p>
				</div>
				<div id="seatWrap"></div>

				<div id="select">
					<input type="reset" value="다시선택" />
					<p>좌석 : <span id="seatNum"></span> / 인원 : <span id="persons">0명</span></p>
				</div>
				
				
			</form>
		</div>
		<!-- end seat -->


		<div id="list">
			<p>
				<img src="image/${schedules.screen.movie.smallPicUrl}" />
			</p>
			<p class="text-left">${schedules.screen.movie.korTitle}</p>
			<p class="text-left">${schedules.auditorium.theater.theaterName}</p>
			<p class="text-left">${schedules.showDate} ${schedules.startTime}</p>
			<h1 class="text-right" id="totalPrice">원</h1>
			<form action="">
				<input type="submit" value="결제하기"/><br>
			
			
			<input type="text" value="${schedules.scheduleNo }" readonly="readonly"/><br><br>
			<input type="text" id="priceNo" value="" readonly="readonly"/><br><br>
			<input type="text" id="cateTime" value="${schedules.cateTime }" readonly="readonly"/>
			</form>
		</div>
	</div>
</body>
</html>