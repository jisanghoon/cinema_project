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


<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>


	<div class="container">
		<h2>상영관 등록</h2>
		<c:if test="${ empty auditorium }">
			<form class="form-horizontal" method="post" action="auditoriumWrite.do">
		</c:if>

		<c:if test="${! empty auditorium }">
			<form class="form-horizontal" method="post" action="auditoriumUpdate.do">
				<input type="hidden" name="audiNo" value="${auditorium.audiNo }">
		</c:if>



		<div class="form-group ">
			<label for="theaterNo" class="control-label col-sm-2">지점</label>
			<div class="col-sm-10">
				<select class="form-control" id="theaterNo" name="theaterNo">
					<c:forEach var="theater" items="${theaterList}">
						<option value="${theater.theaterNo }" ${theater.theaterNo ==auditorium.theater.theaterNo  ? 'selected' : ''}>${theater.theaterName }</option>
					</c:forEach>
				</select>
			</div>
		</div>


		<div class="form-group">
			<label for="audiName" class="control-label col-sm-2">상영관 이름</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="audiName" id="audiName" value="${auditorium.audiName}" required />
			</div>
		</div>

		<div class="form-group">
			<label for="audiType" class="control-label col-sm-2">상영관 타입 구분</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="audiType" id="audiType" value="${auditorium.audiType}" required />
			</div>
		</div>


		<div class="form-group">
			<label for="floor" class="control-label col-sm-2">상영관 장소</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="floor" id="floor" value="${auditorium.floor }" required />
			</div>
		</div>

		<br> <br>
		<div class="btn-group col-md-offset-6">
			<input type="submit" value="저장" class="btn btn-default submitBtn btn-lg " />
			<input type="reset" value="취소" class="btn btn-default submitBtn btn-lg " />
		</div>
		</form>
	</div>
</body>
</html>