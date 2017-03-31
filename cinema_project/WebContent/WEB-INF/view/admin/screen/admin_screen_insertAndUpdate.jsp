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


<style type="text/css">
textarea {
	resize: none;
}

.submitBtn {
	background: blue;
	color: white;
	margin-top: 50px;
	margin-bottom: 150px;
}

#startDate, #endDate, #buyDate {
	background: white !important;
}
</style>
<title>Cinema_Admin_Version</title>
<script type="text/javascript">
	$(function() {
		$("#startDate").flatpickr();
		$("#endDate").flatpickr();
		$("#buyDate").flatpickr();

	});//시작함수 끝

	//숫자 콤마 방법1.
	function numberWithCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	//숫자 콤마 방법2.
	function AddComma(data_value) {
		return Number(data_value).toLocaleString('en');
	}
	//숫자 콤마 방법3.
	function cmaComma(obj) {
		var firstNum = obj.value.substring(0, 1); // 첫글자 확인 변수
		var strNum = /^[/,/,0,1,2,3,4,5,6,7,8,9,/]/; // 숫자와 , 만 가능
		var str = "" + obj.value.replace(/,/gi, ''); // 콤마 제거  
		var regx = new RegExp(/(-?\d+)(\d{3})/);
		var bExists = str.indexOf(".", 0);
		var strArr = str.split('.');

		if (!strNum.test(obj.value)) {
			alert("숫자만 입력하십시오.\n\n특수문자와 한글/영문은 사용할수 없습니다.");
			obj.value = 1;
			obj.focus();
			return false;
		}

		if ((firstNum < "0" || "9" < firstNum)) {
			alert("숫자만 입력하십시오.");
			obj.value = 1;
			obj.focus();
			return false;
		}

		while (regx.test(strArr[0])) {
			strArr[0] = strArr[0].replace(regx, "$1,$2");
		}
		if (bExists > -1) {
			obj.value = strArr[0] + "." + strArr[1];
		} else {
			obj.value = strArr[0];
		}
	}

	function commaSplit(n) {// 콤마 나누는 부분
		var txtNumber = '' + n;
		var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');
		var arrNumber = txtNumber.split('.');
		arrNumber[0] += '.';
		do {
			arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2');
		} while (rxSplit.test(arrNumber[0]));
		if (arrNumber.length > 1) {
			return arrNumber.join('');
		} else {
			return arrNumber[0].split('.')[0];
		}
	}

	function removeComma(n) { // 콤마제거
		if (typeof n == "undefined" || n == null || n == "") { return ""; }
		var txtNumber = '' + n;
		return txtNumber.replace(/(,)/g, "");
	}

	function getNumber(obj) {

		var num01;
		var num02;
		num01 = obj.value;
		num02 = num01.replace(/\D/g, ""); //숫자가 아닌것을 제거, 
		//즉 [0-9]를 제외한 문자 제거; /[^0-9]/g 와 같은 표현
		num01 = setComma(num02); //콤마 찍기
		obj.value = num01;

	}
	function setComma(n) {
		var reg = /(^[+-]?\d+)(\d{3})/; // 정규식
		n += ''; // 숫자를 문자열로 변환         
		while (reg.test(n)) {
			n = n.replace(reg, '$1' + ',' + '$2');
		}
		return n;
	}
</script>
</head>
<body>
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>


	<div class="container">
		<h2>스크린 등록</h2>
		<c:if test="${ empty screen }">
			<form class="form-horizontal" method="post" action="screenWrite.do">
		</c:if>

		<c:if test="${! empty screen }">
			<form class="form-horizontal" method="post" action="screenUpdate.do">
				<input type="hidden" name="screenNo" value="${screen.screenNo }">
		</c:if>


		<div class="form-group ">
			<label for="movieNo" class="control-label col-sm-2">영화선택</label>
			<div class="col-sm-10">
				<select class="form-control" id="movieNo" name="movieNo">

					<c:forEach var="movie" items="${movieList }">
						<option value="${movie.movieNo }" ${movie.movieNo == screen.movie.movieNo ? 'selected' : ''}>${movie.korTitle }</option>
					</c:forEach>
				</select>
			</div>
		</div>


		<div class="form-group ">
			<label for="screenMode" class="control-label col-sm-2">스크린 종류</label>
			<div class="col-sm-10">
				<label class="radio-inline">
					<input type="radio" name="screenMode" value="Digital 2D" ${screen.screenMode == '디지털 2D' ? 'checked' : ''} checked>
					Digital 2D
				</label>
				<label class="radio-inline">
					<input type="radio" name="screenMode" value="Digital 3D" ${screen.screenMode == '디지털 3D' ? 'checked' : ''}>
					Digital 3D
				</label>
				<label class="radio-inline">
					<input type="radio" name="screenMode" value="Atomos 2D" ${screen.screenMode == 'Atomos 2D' ? 'checked' : ''}>
					Atomos 2D
				</label>
				<label class="radio-inline">
					<input type="radio" name="screenMode" value="Atomos 3D" ${screen.screenMode == 'Atomos 3D' ? 'checked' : ''}>
					Atomos 3D
				</label>
			</div>
		</div>


		<div class="form-group">
			<label for="buyDate" class="control-label col-sm-2">구입 날짜</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="buyDate" id="buyDate" value="${screen.buyDate }" required placeholder="연도-월-일" />
			</div>
		</div>
		<div class="form-group">
			<label for="screenPrice" class="control-label col-sm-2">금액</label>
			<div class="col-sm-10">
				<input type=text class="form-control" name="screenPrice" id="screenPrice" value="${screen.screenPrice }" required onchange="getNumber(this);" onkeyup="getNumber(this);" />
			</div>
		</div>
		<div class="form-group">
			<label for="supplier" class="control-label col-sm-2">공급업자</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="supplier" id="supplier" value="${screen.supplier }" required />
			</div>
		</div>

		<div class="form-group">
			<label for="startDate" class="control-label col-sm-2">상영시작날짜</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="startDate" id="startDate" value="${screen.startDate }" required placeholder="연도-월-일" />
			</div>
		</div>

		<div class="form-group">
			<label for="endDate" class="control-label col-sm-2">끝날짜</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="endDate" id="endDate" value="${screen.endDate }" placeholder="연도-월-일" />
			</div>
		</div>



		<br> <br>
		<div class="btn-group col-md-offset-6">
			<input type="submit" value="저장" class="btn btn-default submitBtn btn-lg " />
			<input type="reset" value="취소" class="btn btn-default submitBtn btn-lg " />
		</div>
		</form>
	</div>
</body>
</html>