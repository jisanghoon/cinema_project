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
<style type="text/css">
td,th {
	text-align: center;	
}
</style>
</head>

<body>
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>

	<div class="container ">
		<div class="row ">
			<h1>회원 리스트</h1>
		</div>
		<br> <br> <br>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>회원 번호</th>
					<th>아아디</th>
					<th>이름</th>
					<th>휴대폰 번호</th>
					<th>이메일</th>
					<th>생년월일</th>
					<th>성별</th>
					<th>등록날짜</th>
					<th>가입 상태</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="member" items="${memberList}">
					<tr>
						<td>${member.userNo }</td>
						<td>${member.userId }</td>
						<td>${member.userName}</td>
						<td>${member.userPhone}</td>
						<td>${member.userEmail}</td>
						<td>${member.dateOfBirth}</td>
						<td>${member.userGender}</td>
						<td>${member.regDate}</td>
						<td><input type="checkbox" value="${member.stateOfJoin}" ${member.stateOfJoin ==true?'checked':''} onclick="return false;"></td>



						<td>
							<div class="btn-group btn-group-xs pull-right">
								<a href="memberDelete.do?userNo=${member.userNo }" class="btn btn-primary ">삭제</a>
							</div>
						</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>