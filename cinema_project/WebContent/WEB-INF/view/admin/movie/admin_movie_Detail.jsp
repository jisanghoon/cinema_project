<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>Cinema_Admin_Version</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
.movie_content {
	border: 0;
	background: #f9f9f9;
}
</style>
</head>

<body>
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>
	<div class="container">
		<br> <br> <br> <br>

		<table class="table table-striped center">
			<tbody>
				<tr>
					<th class="col-sm-2">이미지</th>
					<td class="col-sm-8">
						<div class="row">
							<div class="col-sm-5 col-sm-offset-1">
								<h3>
									<sub>Big-Image</sub>
								</h3>
								<c:if test="${empty movie.bigPicUrl}">
									<img src="./img/no_image_placeholder.png" class="img-thumbnail" width="200">
								</c:if>
								<c:if test="${!empty movie.bigPicUrl}">
									<img src="./upload/${movie.bigPicUrl }" class="img-thumbnail" width="200">
								</c:if>
							</div>
							<div class="col-sm-5 ">

								<h3>
									<sub>Small-Image</sub>
								</h3>
								<c:if test="${empty movie.smallPicUrl}">
									<img src="./img/no_image_placeholder.png" class="img-thumbnail" width="150">
								</c:if>
								<c:if test="${!empty movie.smallPicUrl}">
									<img src="./upload/${movie.smallPicUrl }" class="img-thumbnail" width="150">
								</c:if>
							</div>
						</div> <br> <br>
					</td>

				</tr>
				<tr>
					<th class="col-sm-2">Title(kor)</th>
					<td class="col-sm-8">${movie.korTitle}</td>
				</tr>
				<tr>
					<th class="col-sm-2">Title(eng)</th>
					<td class="col-sm-8">${movie.engTitle}</td>
				</tr>
				<tr>
					<th class="col-sm-2">출연배우</th>
					<td class="col-sm-8">${movie.actors}</td>
				</tr>

				<tr>
					<th class="col-sm-2">감독</th>
					<td class="col-sm-8">${movie.director}</td>
				</tr>

				<tr>
					<th class="col-sm-2">개봉날짜</th>
					<td class="col-sm-8">${movie.releaseDate}</td>
				</tr>

				<tr>
					<th class="col-sm-2">상영등급</th>
					<td class="col-sm-8">${movie.ageRequire}</td>
				</tr>
				<tr>
					<th class="col-sm-2">시간(단위:분)</th>
					<td class="col-sm-8">${movie.timeLength}</td>
				</tr>
				<tr>
					<th class="col-sm-2">국가</th>
					<td class="col-sm-8">${movie.country}</td>
				</tr>
				<tr>
					<th class="col-sm-2">장르</th>
					<td class="col-sm-8">${movie.genre}</td>
				</tr>
				<tr>
					<th class="col-sm-2">줄거리</th>
					<td class="col-sm-8"><pre class="movie_content">${movie.content}</pre></td>

				</tr>
			</tbody>
		</table>
		<br> <br> <br> <br>
		<div class="row ">
			<div class="btn-group pull-right btn-group-lg">
				<a type="button" class="btn btn-primary" href="movieUpdate.do?movieNo=${movie.movieNo}">수정</a>
				<a type="button" class="btn btn-primary" href="movieDelete.do?movieNo=${movie.movieNo}">삭제</a>
				<a href="movieList.do" class="btn btn-primary" role="button">돌아가기</a>
			</div>
		</div>
	</div>
</body>
</html>