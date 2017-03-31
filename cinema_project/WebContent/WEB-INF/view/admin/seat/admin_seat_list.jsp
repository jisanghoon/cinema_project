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
th, td {
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>

	<div class="container ">
		<h3>상세 페이지 필요 / 수정 없음 / 삭제만!</h3>
		<div class="row full-right">
			<div class="col-lg-1 pull-right">
				<a href="seatWrite.do" class="btn btn-primary" role="button">좌석 추가</a>
			</div>
		</div>
		<br> <br> <br>
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<table class="table table-striped">
					<thead>
						<tr>
							<th class="col-md-4">지점</th>
							<th class="col-md-3">상영관</th>
							<th class="col-md-3">좌석 개수</th>
							<!-- <th>좌석 총 개수</th> -->
							<th></th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${auditoriumList}" var="auditorium">
							<tr>
								<th>${auditorium.theater.theaterName }</th>
								<td>${auditorium.audiName }</td>
								<td>${auditorium.seatTotalCnt } 개</td>
								<%-- <td>${seat.auditorium.seatCnt }</td> --%>
								<td>
									<div class="btn-group btn-group-xs pull-right">
										<a href="seatDetail.do?audiNo=${auditorium.audiNo }" class="btn btn-primary ">상세보기</a>
										<a href="seatDelete.do?audiNo=${auditorium.audiNo }" class="btn btn-primary ">삭제</a>
									</div>
								</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>