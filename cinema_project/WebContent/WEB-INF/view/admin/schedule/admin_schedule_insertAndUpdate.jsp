<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/flatpickr.min.css">
<script src="./js/flatpickr.min.js"></script>
<link rel="stylesheet" href="./css/jquery.timepicker.css">
<script src="./js/jquery.timepicker.min.js"></script>
<style type="text/css">
.audi_item {
	display: none;
}

#showDate {
	background: white !important;
}
</style>

<script type="text/javascript">
	var dateSetting = new function() {

		this.time = new Date();

		this.getStartTime = function() {
			this.time.setHours(this.time.getHours() - 3);
			this.time.setMinutes(0);
			return this.time;
		};
		this.getEndTime = function() {
			this.time.setHours(this.time.getHours() + 6);
			this.time.setMinutes(0);
			return this.time;
		};
	};

	$(function() {

		$("#showDate").flatpickr();
		$("#theaterNo").change(function() {
			readyAudiAndScreen();
		});

		$('.selTime').timepicker({

			'minTime' : '10:00am',
			'maxTime' : '03:00am',
			'step' : 10,
			'timeFormat' : 'H:i',

		});

		$('.startTime').timepicker('setTime', dateSetting.getStartTime());
		$('.endTime').timepicker('setTime', dateSetting.getEndTime());

	});

	//전역변수
	/* var scheduleData = ; */

	function readyAudiAndScreen() {
		/* 
		 if (scheduleData !== null) {

		 var selector = '#theaterNo option[value="'
		 + scheduleData.auditorium.theater.theaterNo + '"]';
		 $(selector).attr('selected', true);

		 } */

		var param = $("#theaterNo").val();
		$('#audiNo').empty();
		$('#screenNo').empty();

		$.ajax({
			type : "get",
			url : "ajaxProcess.do",
			dataType : "json",
			data : {
				"theaterNo" : param
			},
			success : function(data) {
				console.log(data);

				for (var i = 0; i < data.auditoriumList.length; i++) {
					$('#audiNo').append($('<option/>', {
						value : data.auditoriumList[i].audiNo,
						text : data.auditoriumList[i].audiName
					}));

					if (i === 0) {
						$('#audiNo option').eq(0).attr('selected', true);
					}
				}

				for (var i = 0; i < data.screenList.length; i++) {
					$('#screenNo').append(
							$('<option/>', {
								value : data.screenList[i].screenNo,
								text : data.screenList[i].movie.korTitle
										+ ' [ ' + data.screenList[i].screenMode
										+ ' ]'
							}));

					if (i === 0) {
						$('#screenNo option').eq(0).attr('selected', true);
					}
				}

				if ('${schedule}' == '' ? false : true) {
					$('#audiNo option[value="${schedule.auditorium.audiNo}"]')
							.attr('selected', true);
					$('#screenNo option[value="${schedule.screen.screenNo}"]')
							.attr('selected', true);
				}

			}
		});
	}
</script>
<title>Insert title here</title>
</head>
<body onload="readyAudiAndScreen()">
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>


	<div class="container">

		<c:if test="${ empty schedule }">
			<h2>상영스케줄 등록</h2>
			<form class="form-horizontal" method="post" action="scheduleWrite.do">
		</c:if>

		<c:if test="${! empty schedule }">
			<h2>상영스케줄 수정</h2>
			<form class="form-horizontal" method="post" action="scheduleUpdate.do">
				<input type="hidden" name="scheduleNo" value="${schedule.scheduleNo }">
		</c:if>


		<div class="form-group ">
			<label for="theaterNo" class="control-label col-sm-2">지점</label>
			<div class="col-sm-10">
				<select class="form-control" id="theaterNo" name="theaterNo">
					<c:forEach var="theater" items="${theaterList }">
						<option value="${theater.theaterNo }" ${theater.theaterNo == schedule.auditorium.theater.theaterNo ? 'selected' : ''}>${theater.theaterName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group ">
			<label for="audiNo" class="control-label col-sm-2">상영관</label>
			<div class="col-sm-10">
				<select class="form-control" id="audiNo" name="audiNo">
				</select>
			</div>
		</div>

		<div class="form-group ">
			<label for="screenNo" class="control-label col-sm-2">스크린</label>
			<div class="col-sm-10">
				<select class="form-control" id=screenNo name="screenNo">

				</select> <sub>스크린의 수가 많아지면 찾기 어려워짐 / 콤버와 검색 혼합 필요</sub>
			</div>
		</div>

		<div class="form-group">
			<label for="showDate" class="control-label col-sm-2">상영 날짜</label>
			<div class="col-sm-10">
				<input type="date" class="form-control" name="showDate" id="showDate" required value="${schedule.showDate }" placeholder="연도-월-일" /> <sub>중복 선택 할 수 있게 변경 필요 / 한 번에 많이 입력 할 수 있게 ...</sub>
			</div>

		</div>
		<%-- 		<div class="form-group">
			<label for="startTime" class="control-label col-sm-2">상영시작 시간</label>
			<div class="col-sm-10">
				<input type="text" class="form-control selTime" name="startTime" id="startTime" required value="${schedule.startTime }" />
				중복 선택 할 수 있게 변경 필요 / 한 번에 많이 입력 할 수 있게 ...
			</div>

		</div>

		<div class="form-group">
			<label for="endTime" class="control-label col-sm-2">상영종료 시간</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="endTime" id="endTime" value="${schedule.endTime }" />
				중복 선택 할 수 있게 변경 필요 / 한 번에 많이 입력 할 수 있게 ...
			</div>

		</div> --%>


		<div class="form-group">
			<label for="startTime" class="control-label col-sm-2">상영시작 시간</label>
			<div class="col-sm-2 ">
				<input type="text" class="form-control selTime startTime" name="startTime" id="startTime" required value="${schedule.startTime }" />
			</div>
			<label for="endTime" class="control-label col-sm-2">상영종료 시간</label>
			<div class="col-sm-2">
				<input type="text" class="form-control selTime endTime" name="endTime" id="endTime" value="${schedule.endTime }" />

			</div>
			<sub>중복 선택 할 수 있게 변경 필요 / 한 번에 많이 입력 할 수 있게 ...</sub>
		</div>



		<div class="form-group ">
			<label for="cateTime" class="control-label col-sm-2">시간대</label>
			<div class="col-sm-10 item_line">
				<label class="radio-inline">
					<input type="radio" name="cateTime" value="조조" ${schedule.cateTime == '조조' ? 'checked' : ''} checked> 조조
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateTime" value="일반" ${schedule.cateTime == '일반' ? 'checked' : ''}> 일반
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateTime" value="심야" ${schedule.cateTime == '심야' ? 'checked' : ''}> 심야
				</label>
			</div>
		</div>
	</div>

	<br>
	<br>
	<div class="btn-group col-md-offset-6">
		<input type="submit" value="저장" class="btn btn-default submitBtn btn-lg " /> <input type="reset" value="취소" class="btn btn-default submitBtn btn-lg " />
	</div>
	</form>
	</div>
</body>
</html>



