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
.theaterName {
	padding-left: 50px;
	font-size: 80px;
}

.audiName {
	padding-left: 50px;
	font-size: 40px;
}
</style>

<script type="text/javascript">
	
</script>

</head>
<body>
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>


	<div class="container ">

		<div class="row ">
			<div class="col-lg-12 ">
				<h1 class="theaterName">${seatList[0].auditorium.theater.theaterName }지점</h1>
			</div>
		</div>

		<br>
		<div class="row ">
			<div class="col-lg-8 ">
				<h2 class="audiName">${seatList[0].auditorium.audiName }</h2>
			</div>
			<div class="col-lg-1 pull-right">
				<a href="seatWrite.do" class="btn btn-primary" role="button">좌석 추가</a>
			</div>
		</div>
		<div class="row "></div>

		<br> <br>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>번호</th>
					<th>행 번호</th>
					<th>열 번호</th>
					<th>좌석 명</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${seatList}" var="seat" varStatus="status">
					<tr>
						<th>${status.count}</th>
						<td>${seat.row }</td>
						<td>${seat.col }</td>
						<td>${seat.seatName }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- <h3>상세 페이지 필요 / 수정 없음 / 삭제만!</h3> -->
</body>
</html>