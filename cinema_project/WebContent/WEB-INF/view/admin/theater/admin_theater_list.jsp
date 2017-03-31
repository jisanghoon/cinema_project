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
.col_Header th {
	text-align: center;
}

td.col_1 {
	padding-left: 40px !important;
}

td.col_1, td.col_2 {
	vertical-align: middle !important;
}

td#col_3 {
	padding-top: 20px;
}

.col_2 {
	text-align: center;
}

/* .col_1 span {
	padding-left: 20px;
} */

/* #table_box {
	padding-left: 50px;
} */
sub {
	padding: 5px 10px 5px 0px;
	bottom: 0em;
	margin-right: 10px;
	/* border: 1px solid #4D00FF; */
	border-right: 5px solid #304D99;
}

.addr_num {
	padding-right: 9px;
}
</style>
</head>
<body>
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>

	<div class="container ">
		<div class="row full-right">
			<div class="col-lg-1 pull-right">
				<a href="theaterWrite.do" class="btn btn-primary" role="button">영화관 추가</a>
			</div>
		</div>
		<br> <br> <br>
		<div class="row">
			<div class="col-md-12" id="table_box">
				<table class="table table-striped">
					<thead>
						<tr class='col_Header'>
							<th class="col-md-2">지점</th>
							<th class="col-md-6">지번 / 도로명</th>

							<th class="col-md-1">담당자</th>
							<th class="col-md-1">전화번호</th>
							<th class="col-md-1"></th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${theaterList}" var="theater">
							<tr>
								<td class="col_1"><span>${theater.theaterName }</span></td>
								<td class="col_3" id="col_3"><p>
										<sub class="addr_num">지&nbsp;&nbsp;&nbsp; 번</sub> &nbsp;${theater.theaterAddrNum }
									</p>
									<p>
										<sub>도로명</sub> &nbsp;${theater.theaterAddrStr }</td>
								<td class="col_2">${theater.theaterManager }</td>
								<td class="col_2">${theater.theaterTel }</td>
								<td>
									<div class="btn-group btn-group-xs pull-right">
										<a href="theaterUpdate.do?theaterNo=${theater.theaterNo }" class="btn btn-primary ">수정</a>
										<a href="theaterDelete.do?theaterNo=${theater.theaterNo }" class="btn btn-primary ">삭제</a>
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