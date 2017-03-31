<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<div class="row ">
			<h1>예약 현황 리스트</h1>
		</div>
		<br> <br> <br>

		<table class="table table-striped">
			<thead>
				<tr>

					<th>예약번호</th>
					<th>고객이름</th>
					<th>고객아이디</th>
					<th>전화번호</th>
					<th>신청한 날짜</th>
					<th>영화이름</th>
					<th>스크린 종류</th>


					<th>상영날짜</th>
					<th>시작 종료 시간</th>
					<th>영화관 지점</th>
					<th>상영관</th>
					<th>좌석</th>
					<th></th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="booking" items="${bookingList}">
					<tr>
						<td>${booking.bookingNo }</td>
						<td>${booking.member.userName }</td>
						<td>${booking.member.userId }</td>
						<td>${booking.member.userPhone}</td>
						<td>${booking.acceptedDate }</td>
						<td>${booking.schedule.screen.movie.korTitle }</td>
						<td><c:choose>
								<c:when test="${booking.ticketPrice.cateScreen=='digital 2d' }">
									<span>디지털 2D</span>
								</c:when>
								<c:when test="${booking.ticketPrice.cateScreen=='digital 3d' }">
									<span>디지털 3D</span>
								</c:when>
								<c:when test="${booking.ticketPrice.cateScreen=='atmos 2d' }">
									<span>ATMOS 2D</span>
								</c:when>
								<c:when test="${booking.ticketPrice.cateScreen=='atmos 3d' }">
									<span>ATMOS 3D</span>
								</c:when>
							</c:choose></td>

						<td>${booking.schedule.showDate }</td>
						<td>${booking.schedule.startTime }/${booking.schedule.endTime }</td>
						<td>${booking.schedule.auditorium.theater.theaterName }</td>
						<td>${booking.schedule.auditorium.audiName }</td>
						<td>${booking.seatRow }-${booking.seatCol }</td>


						<td>
							<div class="btn-group btn-group-xs pull-right">
								<%-- <a href="bookingUpdate.do?bookingNo=${booking.bookingNo }" class="btn btn-primary ">수정</a> --%>
								<a href="bookingDelete.do?bookingNo=${booking.bookingNo }" class="btn btn-primary ">삭제</a>
							</div>
						</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>