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
/* th:NTH-OF-TYPE(2n) {text-align: center;} */
th, td {
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
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<table class="table table-striped">
					<thead>
						<tr>
							<th class="col-md-2">상영관번호</th>
							<th class="col-md-1">지점</th>
							<th class="col-md-3">상영관이름</th>
							<th class="col-md-3">상영관 타입 구분</th>
							<th class="col-md-1">장소</th>
							<th class="col-md-2"></th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${auditoriumList}" var="auditorium">
							<tr>
								<td>${auditorium.audiNo }</td>
								<td>${auditorium.theater.theaterName }</td>
								<td>${auditorium.audiName }</td>
								<td>${auditorium.audiType }</td>
								<td>${auditorium.floor }</td>
								<td>
									<div class="btn-group btn-group-xs">
										<a href="auditoriumUpdate.do?audiNo=${auditorium.audiNo }" class="btn btn-primary ">수정</a>
										<a href="auditoriumDelete.do?audiNo=${auditorium.audiNo }" class="btn btn-primary ">삭제</a>
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