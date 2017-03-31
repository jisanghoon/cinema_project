<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MegaBox</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/nomalize.css" rel="stylesheet">
<link href="css/mainPage.css" rel="stylesheet">
<style type="text/css">
#main {
	background-image: url("image/mainpng.png");
	background-size: cover;
}
/* 로그인 상태 */
#stateText p {
	font-size: 12px;
}

#stateText input[type=button] {
	background-color: white;
	border: 1px solid #EAEAEA;
	margin-top: 2px;
}

#stateText input[type=button]:hover {
	background-color: white;
	border: 1px solid #EAEAEA;
	margin-top: 2px;
	font-weight: bold;
}
/* 공지사항 */
#board {
	position: absolute;
	top: 14%;
	left: 70.6%;
	font-size: 20px;
}

#board p a {
	color: white;
	font-weight: bold;
}
/* 영화목록 */
#moviePadding {
	margin: 0 auto;
}

#list {
	margin: 0px 20px 20px 0px;
}

#list p {
	font-size: 20px;
	margin-top: 16px;
	margin-bottom: 14px;
}

#list input[type=button] {
	width: 100px;
	height: 50px;
	background-color: #EAEAEA;
	border: none;
	font-weight: bold;
}

#list input[type=button]:hover {
	background-color: #3F0099;
	color: white;
}
/* 메뉴 버튼 */
#bookPadding button[type=button] {
	background-color: #3F0099;
	color: white;
	border: 1px solid white;
	border-radius: 0px !important;
	width: 150px;
	height: 50px;
	font-size: 15px;
	font-weight: bold;
}

#bookPadding button[type=button]:hover {
	background-color: white;
	color: #3F0099;
	border: 2.5px solid #3F0099;
}

#bookPadding button[type=button]:checked {
	border: 1px solid #3F0099;
}
/* 푸터 */
#footer {
	background-image: url("image/footer.png");
	width: 100%;
	height: 2650px;
	margin-top: 50px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#moviePadding div").eq(3).css("margin", "0px");
		$("#moviePadding div").eq(7).css("margin", "0px");
	});
</script>
</head>
<body>
	<div class="container-fluid">

		<!-- 네비게이션 바 -->
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="navbar-scroll">
			<div class="container" id="header">
				<a class="navbar-brand" href="#">
					<img src="image/logo.png">
				</a>
				<ul class="nav navbar-nav" id="menu">
					<li><a href="#">영화</a></li>
					<li><a href="#">큐레이션</a></li>
					<li><a href="#">영화관</a></li>
					<li><a href="#">특별관</a></li>
					<li><a href="#">스토어</a></li>
					<li><a href="#">이벤트</a></li>
					<li id="liLogin"><c:if test="${!empty auth }">
							<div id="stateText">
								<p>${auth.name }님
									<input type="button" value="로그아웃" onclick="location.replace('logout.do')" />
									<!-- <a href="logout.do">로그아웃</a> -->
								</p>
								<c:choose>
									<c:when test="${auth.id == 'test' }">
										<p>
											<a href="movieList.do">관리자 모드</a>
										</p>
									</c:when>
									<c:otherwise>
										<p>
											<a href="memberinfo.do">예매확인/취소</a>
										</p>
									</c:otherwise>
								</c:choose>
							</div>
						</c:if> <c:if test="${empty auth }">
							<a href="login.do">로그인</a>
						</c:if></li>
				</ul>
			</div>
		</nav>

		<!-- 메인 비주얼 -->
		<div class="row" id="mainMargin">
			<div class="col-md-12" id="main">

				<div id="board">
					<p>
						<a href="boardlist.do"> + 공지사항</a>
					</p>
				</div>

			</div>
		</div>

	</div>

	<!-- 메뉴 -->
	<div class="container" id="book">
		<div class="row">
			<div class="col-md-12" id="bookPadding">
				<div class="form-group">
					<div class="btn-group" role="group" aria-label="...">
						<button type="button" class="btn btn-default">박스오피스</button>
						<button type="button" class="btn btn-default">최신개봉작</button>
						<button type="button" class="btn btn-default">상영예정작</button>
						<button type="button" class="btn btn-default">큐레이션</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 영화 -->
	<div class="container" id="movie">
		<div class="row">
			<div class="col-md-12" id="moviePadding">
				<c:forEach var="schedule" items="${scheduleList }">
					<div id="list">
						<img src="./upload/${schedule.screen.movie.smallPicUrl }">
						<p>${schedule.screen.movie.korTitle }</p>

						<input type="button" value="상세정보" onclick="location.href='read.do?no=${schedule.screen.movie.movieNo }'" />

						<input type="button" value="예매하기" onclick="location.href='bookone.do?scheduleNo=${schedule.scheduleNo}'" />

					</div>
				</c:forEach>
			</div>
		</div>
	</div>

	<!-- 푸터 -->
	<div class="container-fluid" id="footer"></div>
</body>
</html>