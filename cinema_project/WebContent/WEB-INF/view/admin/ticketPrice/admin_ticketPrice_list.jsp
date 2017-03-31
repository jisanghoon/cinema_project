<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>Cinema_Admin_Version</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
td, th {
	text-align: center;
}
</style>

</head>
<body>
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>

	<div class="container ">
		<div class="row full-right">
			<div class="col-lg-1 pull-right">
				<a href="auditoriumWrite.do" class="btn btn-primary" role="button">상영관 추가</a>
			</div>
		</div>
		<br> <br> <br>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>주중/주말 구분</th>
					<th>TimeZon</th>
					<th>상영관 구분</th>
					<th>영상 구분</th>
					<th>좌석 구분</th>
					<th>나이 구분</th>
					<th>가격</th>
					<th></th>
				</tr>
			</thead>


			<tbody>
				<c:forEach items="${ticketPriceList}" var="ticketPrice">
					<tr>
						<td><c:choose>
								<c:when test="${ticketPrice.cateDay=='weekday' }">
									<span>주중</span>
								</c:when>
								<c:when test="${ticketPrice.cateDay=='weekend' }">
									<span>주말</span>
								</c:when>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${ticketPrice.cateTime=='early morning' }">
									<span>조조</span>
								</c:when>
								<c:when test="${ticketPrice.cateTime=='normal' }">
									<span>일반</span>
								</c:when>
								<c:when test="${ticketPrice.cateTime=='midnight' }">
									<span>심야</span>
								</c:when>
							</c:choose></td>





						<td><c:choose>
								<c:when test="${ticketPrice.cateAudi=='normal' }">
									<span>일반</span>
								</c:when>
								<c:when test="${ticketPrice.cateAudi=='premium' }">
									<span>PREMIUM</span>
								</c:when>
								<c:when test="${ticketPrice.cateAudi=='table zone' }">
									<span>Table Zone</span>
								</c:when>
								<c:when test="${ticketPrice.cateAudi=='m' }">
									<span>M</span>
								</c:when>
								<c:when test="${ticketPrice.cateAudi=='m2' }">
									<span>M2</span>
								</c:when>
								<c:when test="${ticketPrice.cateAudi=='mx' }">
									<span>MX</span>
								</c:when>
								<c:when test="${ticketPrice.cateAudi=='boutique' }">
									<span>THE BOUTIQUE</span>
								</c:when>
								<c:when test="${ticketPrice.cateAudi=='balcony deluxe m' }">
									<span>BALCONY deluxe M</span>
								</c:when>
								<c:when test="${ticketPrice.cateAudi=='balcony m' }">
									<span>BALCONY M </span>
								</c:when>
								<c:when test="${ticketPrice.cateAudi=='kids box' }">
									<span>KIDS BOX</span>
								</c:when>
							</c:choose></td>





						<td><c:choose>
								<c:when test="${ticketPrice.cateScreen=='digital 2d' }">
									<span>디지털 2D</span>
								</c:when>
								<c:when test="${ticketPrice.cateScreen=='digital 3d' }">
									<span>디지털 3D</span>
								</c:when>
								<c:when test="${ticketPrice.cateScreen=='atmos 2d' }">
									<span>ATMOS 2D</span>
								</c:when>
								<c:when test="${ticketPrice.cateScreen=='atmos 3d' }">
									<span>ATMOS 3D</span>
								</c:when>
							</c:choose></td>





						<td><c:choose>
								<c:when test="${ticketPrice.cateSeat=='normal' }">
									<span>일반 좌석</span>
								</c:when>
								<c:when test="${ticketPrice.cateSeat=='normal couple' }">
									<span>일반 커플 좌석</span>
								</c:when>
								<c:when test="${ticketPrice.cateSeat=='premium 2' }">
									<span>프리미엄 2인 소파</span>
								</c:when>
								<c:when test="${ticketPrice.cateSeat=='premium 4' }">
									<span>프리미엄 4인 소파</span>
								</c:when>
								<c:when test="${ticketPrice.cateSeat=='premium suite' }">
									<span>프리미엄 SUITE 좌석</span>
								</c:when>
								<c:when test="${ticketPrice.cateSeat=='premium comfort' }">
									<span>프리미엄 COMFORT 좌석</span>
								</c:when>

							</c:choose></td>




						<td><c:choose>
								<c:when test="${ticketPrice.cateAge=='adult' }">
									<span>성인</span>
								</c:when>
								<c:when test="${ticketPrice.cateAge=='teenager' }">
									<span>청소년</span>
								</c:when>
								<c:when test="${ticketPrice.cateAge=='kid' }">
									<span>어린이</span>
								</c:when>
								<c:when test="${ticketPrice.cateAge=='favor' }">
									<span>우대</span>
								</c:when>
							</c:choose></td>




						<td>${ticketPrice.price }</td>
						<td>
							<div class="btn-group btn-group-xs pull-right">
								<a href="ticketPriceUpdate.do?priceNo=${ticketPrice.priceNo }" class="btn btn-primary ">수정</a>
								<a href="ticketPriceDelete.do?priceNo=${ticketPrice.priceNo }" class="btn btn-primary ">삭제</a>
							</div>
						</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>