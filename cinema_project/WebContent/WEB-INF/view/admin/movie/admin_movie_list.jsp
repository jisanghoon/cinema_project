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
.movie_story {
	overflow: hidden;
}
</style>
</head>
<body>
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>

	<div class="container ">
		<div class="row full-right">
			<div class="col-lg-1 pull-right">
				<a href="movieWrite.do" class="btn btn-primary" role="button">영화추가</a>
			</div>
		</div>
		<br> <br> <br>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>제목</th>
					<th>감독</th>
					<th>개봉날짜</th>
					<th>상영등급</th>
					<th>시간(단위:분)</th>
					<th>나라</th>
					<th>장르</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${movieList}" var="movie">
					<tr>
						<td><a href="movieDetail.do?movieNo=${movie.movieNo }"> ${movie.korTitle }(${movie.engTitle })</a></td>

						<td>${movie.director }</td>
						<td>${movie.releaseDate }</td>
						<td>${movie.ageRequire }</td>
						<td>${movie.timeLength }</td>
						<td>${movie.country }</td>
						<td>${movie.genre }</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>