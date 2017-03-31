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
<script src="./js/autoHypen.js"></script>
<script type="text/javascript">
	$(function() {
		$("#closeBtn").click(function() {
			$("#myModal").css("display", "none");

			console.log("test")
		})
	})
</script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>


	<div class="container">

		<c:if test="${ empty theater }">
			<h2>영화관 등록</h2>
			<form class="form-horizontal" method="post" action="theaterWrite.do">
		</c:if>

		<c:if test="${! empty theater }">

			<c:if test="${!empty duplication }">
				<h2>영화관 등록</h2>
				<form class="form-horizontal" method="post" action="theaterWrite.do">

					<div class="modal show" id="myModal" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Modal Header</h4>
								</div>
								<div class="modal-body">
									<p>Some text in the modal.</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal" id="closeBtn">Close</button>
								</div>
							</div>

						</div>
					</div>
			</c:if>
			<c:if test="${empty duplication }">
				<h2>영화관 수정</h2>
				<form class="form-horizontal" method="post" action="theaterUpdate.do">
					<input type="hidden" name="theaterNo" value="${theater.theaterNo }">
			</c:if>
		</c:if>

		<div class="form-group">
			<label for="theaterName" class="control-label col-sm-2">지점</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="theaterName" id="theaterName" value="${theater.theaterName }" required />
			</div>
		</div>

		<div class="form-group">
			<label for="theaterAddrNum" class="control-label col-sm-2">지번</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="theaterAddrNum" id="theaterAddrNum" value="${theater.theaterAddrNum }" required />
			</div>
		</div>
		<div class="form-group">
			<label for="theaterAddrStr" class="control-label col-sm-2">도로명</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="theaterAddrStr" id="theaterAddrStr" value="${theater.theaterAddrStr }" required />
			</div>
		</div>

		<div class="form-group">
			<label for="theaterManager" class="control-label col-sm-2">담당자</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="theaterManager" id="theaterManager" value="${theater.theaterManager }" />
			</div>
		</div>

		<div class="form-group">
			<label for="theaterTel" class="control-label col-sm-2">전화번호</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="theaterTel" id="theaterTel" value="${ theater.theaterTel }" onblur="chk_tel(this.value,this);" />
			</div>
		</div>

		<br> <br>
		<div class="btn-group col-md-offset-6">
			<input type="submit" value="저장" class="btn btn-default submitBtn btn-lg " /> <input type="reset" value="취소" class="btn btn-default submitBtn btn-lg " />
		</div>
		</form>
	</div>
</body>
</html>