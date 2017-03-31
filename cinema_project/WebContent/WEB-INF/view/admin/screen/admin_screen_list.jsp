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
</head>
<body>
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>

	<div class="container ">
		<div class="row full-right">
			<div class="col-lg-1 pull-right">
				<a href="screenWrite.do" class="btn btn-primary" role="button">스크린 추가</a>
			</div>
		</div>
		<br> <br> <br>

		<table class="table table-striped">
			<thead>
				<tr>

					<th>영화제목</th>
					<th>스크린모드</th>
					<th>구매 날짜</th>
					<th>금액</th>
					<th>공급자</th>
					<th>상영시작날짜</th>
					<th>끝날짜</th>
					<th></th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="screen" items="${screenList}">
					<tr>
						<td>${screen.movie.korTitle }</td>
						<td>${screen.screenMode }</td>
						<td>${screen.buyDate }</td>
						<td>￦ <fmt:formatNumber value="${screen.screenPrice}" groupingUsed="true" /></td>
						<td>${screen.supplier }</td>
						<td>${screen.startDate }</td>
						<td>${!empty screen.endDate?screen.endDate:'현재 상영 중' }</td>
						<td>
							<div class="btn-group btn-group-xs pull-right">
								<a href="screenUpdate.do?screenNo=${screen.screenNo }" class="btn btn-primary ">수정</a> <a href="screenDelete.do?screenNo=${screen.screenNo }" class="btn btn-primary ">삭제</a>
							</div>
						</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>