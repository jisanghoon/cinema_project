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
				<a href="scheduleWrite.do" class="btn btn-primary" role="button">상영스케줄 추가</a>
			</div>
		</div>
		<br> <br> <br>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>영화관 지점</th>
					<th>영화이름</th>
					<th>상영관이름</th>
					<th>영화시작 시간</th>
					<th>영화종료 시간</th>
					<th>시간대 구분</th>
					<th></th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${scheduleList}" var="schedule">
					<tr>
						<th>${schedule.auditorium.theater.theaterName }</th>
						<td>${schedule.screen.movie.korTitle }&nbsp&nbsp[${schedule.screen.screenMode}]</td>
						<td>${schedule.auditorium.audiName }</td>
						<td>${schedule.startTime }</td>
						<td>${schedule.endTime }</td>
						<td>${schedule.cateTime }</td>
						<td>
							<div class="btn-group btn-group-xs pull-right">
								<a href="scheduleUpdate.do?scheduleNo=${schedule.scheduleNo }" class="btn btn-primary ">수정</a>
								<a href="scheduleDelete.do?scheduleNo=${schedule.scheduleNo }" class="btn btn-primary ">삭제</a>
							</div>
						</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>