<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="./js/seatDesign.js"></script>
<style>
.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th,
	.table>thead>tr>td, .table>thead>tr>th {
	padding: 0;
	text-align: center;
	width: 42px;
	height: 36px;
	font-size: 11px;
}

.seatName {
	height: 90px;
	font-size: 5em;
	height: 90px;
}

.col_header, .row_header, .leftTop {
	
}

.table {
	margin: 0 auto;
	width: auto;
	max-width: auto;
}

.seat {
	cursor: pointer;
}

.btn-group {
	margin-top: 20px;
}

.audi_item {
	display: none;
}

.mytxtInput, .myselect {
	/* width: 200px !important; */
	
}
</style>

<script type="text/javascript">
	var storeData = new Array();
	var seatData;

	$(function() {

		initData();

		$('#theaterNo').change(function() {
			readyAudi()
		});

		$('.btnSendData').click(function() {
			sendPost();
		})

	});

	function readyBody() {
		readyAudi()
	}

	function readyAudi() {

		var param = $("#theaterNo").val();
		$('#audiNo').empty();

		$.ajax({
		type : "get",
		url : "ajaxProcess.do",
		dataType : "json",
		data : {
			"theaterNo" : param
		},
		success : function(data) {
			console.log(data);
			if (data.auditoriumList.length != 0) {
				for (var i = 0; i < data.auditoriumList.length; i++) {
					$('#audiNo').append($('<option/>', {
					value : data.auditoriumList[i].audiNo,
					text : data.auditoriumList[i].audiName
					}));

					if (i === 0) {
						$('#audiNo option').eq(0).attr('selected', true);
					}
				}
			} else {
				$('#audiNo').append($('<option/>', {
					text : '등록된 상영관이 없습니다.'
				}));
			}

		}
		});

	}

	var initData = function() {

		$('.btnMakeTable').click(function() {
			var rowCnt = $('#inputRow').val();
			var colCnt = $('#inputCol').val();

			seatData = new Array();

			for (var i = 0; i < rowCnt; i++) {
				seatData[i] = new Array();
				var alphabet = String.fromCharCode(65 + i);

				for (var j = 0; j < colCnt; j++) {
					var json = new Object();

					json.row = i + 1;
					json.col = j + 1;
					json.name = alphabet + '-' + (j + 1);

					seatData[i][j] = json;
				}

			}
			makeCopyData(seatData)
			drawTable(seatData)

		})// end of method ==> $('.btnTable').click

		$('.btnBackTable').click(function() {
			if (storeData.length !== 0) {
				seatData = storeData.pop();
				drawTable(seatData);
			}
		})// end of method ==> $('.btnBackTable').click

	}

	var makeCopyData = function(temp) {

		var copyData = new Array();
		for (var i = 0; i < temp.length; i++) {
			copyData[i] = new Array();
			for (var j = 0; j < temp[0].length; j++) {
				copyData[i][j] = $.extend({}, temp[i][j]);
			}
		}
		storeData.push(copyData);

	}// end of method  = > var makeCopyData = function(temp)

	function sendPost() {
		var form = document.form_write;
		var input = document.createElement("input");
		input.type = "hidden";
		input.name = 'data';
		input.value = JSON.stringify(seatData);
		form.append(input);
		form.submit();
	}
</script>

</head>
<body onload="readyBody()">
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>


	<div class="container">
		<h1>좌석 등록</h1>
		<sub>1. 체크박스 위치 조정 필요</sub> <br> <sub>2. 큰 좌석 명 글자 간격 조정 필요</sub> <br> <br>


		<!-- 		<form class="form-horizontal" method="post" action="seatWrite.do" name="form_write"> -->
		<form class="form-horizontal" method="post" action="seatWrite.do" name="form_write">

			<div class="form-inline">
				<div class="col-xs-3">
					<div class="form-group form-group-sm">
						<label for="theaterNo" class="control-label ">지점 </label>
						<select class="form-control myselect" id="theaterNo" name="theaterNo">
							<c:forEach var="theater" items="${theaterList }">
								<option value="${theater.theaterNo }" ${theater.theaterNo == seat.auditorium.theater.theaterNo ? 'selected' : ''}>${theater.theaterName }</option>
							</c:forEach>
						</select>
					</div>
				</div>


				<div class="col-xs-3">
					<div class="form-group form-group-sm">
						<label for="audiNo" class="control-label ">상영관 </label>
						<select class="form-control myselect" id="audiNo" name="audiNo">
						</select>

					</div>
				</div>

				<div class="col-xs-3">
					<div class="form-group form-group-sm">
						<label for="" class="control-label ">행 : </label>
						<input type="text" class="form-control  mytxtInput" id="inputRow">
					</div>
				</div>

				<div class="col-xs-3">
					<div class="form-group form-group-sm">
						<label for="" class="control-label">열 : </label>
						<input type="text" class="form-control  mytxtInput" id="inputCol">
					</div>
				</div>
			</div>
		</form>

		<div class="form-group pull-right btn-group">
			<button class="btn btn-default btn-md btnMakeTable">만들기</button>
			<button class="btn btn-default btn-md btnSendData">저장</button>
			<button class="btn btn-default btn-md btnBackTable">뒤로가기</button>
		</div>



		<h1 class="seatName">
			<span class="rowName"></span> <span class="colName"></span>
		</h1>
		<table class="table table-bordered">
			<tbody class="seat_table">
			</tbody>
		</table>


	</div>
</body>
</html>