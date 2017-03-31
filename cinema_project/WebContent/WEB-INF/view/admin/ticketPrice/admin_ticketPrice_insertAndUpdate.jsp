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
<link rel="stylesheet" href="./css/font-awesome.min.css">
<style type="text/css">
.control-label {
	text-align: left !important;
	padding-left: 60px;
}
.plus_icon{

}
</style>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>



	<div class="container">
		<!-- <sub>요금을 추가로 등록할 때, ticketPrice데이터가 존재하는지 체크해줘서 존재하면 추가 안되게 처리해야됨.</sub> --> <br> <br>


		<c:if test="${ empty ticketPrice }">
			<h2>요금 등록</h2>
			<br>
			<form class="form-horizontal" method="post" action="ticketPriceWrite.do">
		</c:if>

		<c:if test="${! empty ticketPrice }">
			<h2>요금 수정</h2>
			<br>
			<form class="form-horizontal" method="post" action="ticketPriceUpdate.do">
				<input type="hidden" name="priceNo" value="${ticketPrice.priceNo }">
		</c:if>





		<div class="form-group ">
			<label for="cateDay" class="control-label col-sm-2">주중 / 주말구분</label>
			<div class="col-sm-10 item_line">
				<label class="radio-inline">
					<input type="radio" name="cateDay" value="weekday" ${ticketPrice.cateDay == 'weekday' ? 'checked' : ''} checked> 주중 - Weekday
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateDay" value="weekend" ${ticketPrice.cateDay == 'weekend' ? 'checked' : ''}> 주말 - Weekend (금요일 포함)
				</label>
			</div>
		</div>

		<br>




		<div class="form-group ">
			<label for="cateTime" class="control-label col-sm-2">Time Zon</label>
			<div class="col-sm-10 item_line">
				<label class="radio-inline">
					<input type="radio" name="cateTime" value="early morning" ${ticketPrice.cateTime == 'early morning' ? 'checked' : ''} checked> 조조
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateTime" value="normal" ${ticketPrice.cateTime == 'normal' ? 'checked' : ''}> 일반
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateTime" value="midnight" ${ticketPrice.cateTime == 'midnight' ? 'checked' : ''}> 심야
				</label>
			</div>
		</div>


		<br>

		<div class="form-group ">
			<label for="cateAudi" class="control-label col-sm-2">상영관 구분</label>
			<div class="col-sm-10 item_line">
				<label class="radio-inline">
					<input type="radio" name="cateAudi" value="normal" ${ticketPrice.cateAudi == 'normal' ? 'checked' : ''} checked> 일반
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateAudi" value="premium" ${ticketPrice.cateAudi == 'premium' ? 'checked' : ''}> 프리미움
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateAudi" value="table zone" ${ticketPrice.cateAudi == 'table zone' ? 'checked' : ''}> Table Zone
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateAudi" value="m" ${ticketPrice.cateAudi == 'm' ? 'checked' : ''}> M
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateAudi" value="m2" ${ticketPrice.cateAudi == 'm2' ? 'checked' : ''}> M2
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateAudi" value="mx" ${ticketPrice.cateAudi == 'mx' ? 'checked' : ''}> MX
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateAudi" value="boutique" ${ticketPrice.cateAudi == 'boutique' ? 'checked' : ''}> THE BOUTIQUE
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateAudi" value="balcony deluxe m" ${ticketPrice.cateAudi == 'balcony deluxe m' ? 'checked' : ''}> BALCONY deluxe M
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateAudi" value="balcony m" ${ticketPrice.cateAudi == 'balcony m' ? 'checked' : ''}> BALCONY M
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateAudi" value="kids box" ${ticketPrice.cateAudi == 'kids box' ? 'checked' : ''}> KIDS BOX
				</label>
			</div>

		</div>



		<br>



		<div class="form-group ">
			<label for="cateScreen" class="control-label col-sm-2">영상 구분</label>
			<div class="col-sm-10 item_line">
				<label class="radio-inline">
					<input type="radio" name="cateScreen" value="digital 2d" ${ticketPrice.cateScreen == '2D' ? 'checked' : ''} checked> 디지털 2D 
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateScreen" value="digital 3d" ${ticketPrice.cateScreen == '3D' ? 'checked' : ''}> 디지털 3D 
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateScreen" value="atmos 2d" ${ticketPrice.cateScreen == 'atmos' ? 'checked' : ''}> ATMOS 2D 
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateScreen" value="atmos 3d" ${ticketPrice.cateScreen == 'atmos 3d' ? 'checked' : ''}> ATMOS 3D 
				</label>
	<!-- 			<a href="#" class="btn  btn-sm plus_icon"> <span class="glyphicon glyphicon-plus"></span>항목추가 -->
				</a>
			</div>

		</div>

		<br>

		<div class="form-group ">
			<label for="cateSeat" class="control-label col-sm-2">좌석 구분</label>
			<div class="col-sm-10 item_line">

				<label class="radio-inline">
					<input type="radio" name="cateSeat" value="normal" ${ticketPrice.cateSeat == 'normal' ? 'checked' : ''} checked> 일반 좌석
				</label>

				<label class="radio-inline">
					<input type="radio" name="cateSeat" value="normal couple" ${ticketPrice.cateSeat == 'normal couple' ? 'checked' : ''}> 일반 커플 좌석
				</label>

				<label class="radio-inline">
					<input type="radio" name="cateSeat" value="premium 2" ${ticketPrice.cateSeat == 'premium 2' ? 'checked' : ''}> 프리미엄 2인 소파
				</label>

				<label class="radio-inline">
					<input type="radio" name="cateSeat" value="premium 4" ${ticketPrice.cateSeat == 'premium 4' ? 'checked' : ''}> 프리미엄 4인 소파
				</label>

				<label class="radio-inline">
					<input type="radio" name="cateSeat" value="premium suite" ${ticketPrice.cateSeat == 'premium suite' ? 'checked' : ''}> 프리미엄 SUITE 좌석
				</label>

				<label class="radio-inline">
					<input type="radio" name="cateSeat" value="premium comfort" ${ticketPrice.cateSeat == 'premium comfort' ? 'checked' : ''}> 프리미엄 COMFORT 좌석
				</label>

			</div>

			<!-- <a href="#" class="btn  btn-sm col-sm-1"> <span class="glyphicon glyphicon-plus"></span> Plus
			</a> -->

		</div>


		<div class="form-group ">
			<label for="cateAge" class="control-label col-sm-2">나이 구분</label>
			<div class="col-sm-10 item_line">
				<label class="radio-inline">
					<input type="radio" name="cateAge" value="adult" ${ticketPrice.cateAge == 'adult' ? 'checked' : ''} checked> 어른
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateAge" value="teenager" ${ticketPrice.cateAge == 'teenager' ? 'checked' : ''}> 청소년
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateAge" value="kid" ${ticketPrice.cateAge == 'kid' ? 'checked' : ''}> 어린이
				</label>
				<label class="radio-inline">
					<input type="radio" name="cateAge" value="favor" ${ticketPrice.cateAge == 'favor' ? 'checked' : ''}> 우대
				</label>
			</div>
		</div>

		<br> <br> <br>
<!-- 		<div class="form-group">
			<div class="col-sm-10 col-sm-offset-2">
				<h1>저장 버튼 클릭 시 데이터 존재 확인</h1>
			</div>
		</div> -->
		<div class="form-group">
			<label for="price" class="control-label col-sm-2">요금</label>
			<div class="col-sm-10 ">
				<input type="text" class="form-control" name="price" id="price" value="${ticketPrice.price }" required />
			</div>
		</div>

		<br> <br>
		<div class="btn-group col-md-offset-6">
			<input type="submit" value="저장" class="btn btn-default submitBtn btn-lg " /> <input type="reset" value="취소" class="btn btn-default submitBtn btn-lg " />
		</div>
		</form>
	</div>
</body>
</html>