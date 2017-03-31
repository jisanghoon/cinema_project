<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>DetailPage</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/nomalize.css" rel="stylesheet">
<link href="css/detailPage.css" rel="stylesheet" type="text/css">
<style type="text/css">
#detail {
	height: 100%;
}

#pasingBox {
	text-align: center;
	padding: 20px 0px 0px 0px;
}

#pasingBox input[type=button] {
	background-color: #3F0099;
	color: white;
	border: none;
	width: 30px;
	height: 30px;
}

#pasingBox input[type=button]:checked {
	border: 1px solid #3F0099;
}

.summary {
	margin-bottom: 60px;
}
/* 리뷰 아이콘 */
#iconMargin {
	margin-top: 12px;
}

#iconMargin span {
	font-size: 24px;
}

#btnReview {
	margin: 0;
	margin-top: 14px;
}

#btnReview:hover {
	background-color: white;
	border: 2px solid #3F0099;
	color: #3F0099;
	font-weight: bold;
	font-size: 16px;
}
/* 삭제 버튼 */
#review input[type=button] {
	background-color: white;
	border: 1px solid #EAEAEA;
	font-size: 10px;
	color: #3F0099;
	margin-left: 6px;
	padding: 4px 6px;
}

#review input[type=button]:hover {
	background-color: #3F0099;
	color: white;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){				
		//ajax-리뷰등록버튼
		$("#btnReview").on("click", function() {
			$.ajax({
				url : "reviewinsert.do",
				type : "post",
				timeout : 30000,
				datatype : "json",
				data : {"content" : $("#content").val(), "no" : ${viewMovieRead.movieNo} },
				success : function(res){
					console.log(res)
					var html = "<div id='review'><p>" + res.writer.id + " " + res.regFormatDate + "</p><p>" + res.content + "</p></div>" ; 
					$("#reviewBox").append(html);
					alert("리뷰를 등록하였습니다.");
					location.href="read.do?no=${viewMovieRead.movieNo }";
					}				
				});
			});

		
		/* $("#delete").click(function(){
			var tf =  confirm("의견을 삭제하시겠습니까?");
			if(tf){
				location.replace('reviewdelete.do?reviewNo=${reviewList.reviewNo}&no=${viewMovieRead.movieNo }');
				return true;
				}else{
					
				return false;
					}

			}); */
		
		});
</script>
</head>
<body>
	<div class="container-fluid">

		<!-- 네비게이션 바 -->
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="navbar-scroll">
			<div class="container" id="header">
				<a class="navbar-brand" href="list.do" onclick="location.replace('list.do')">
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
							<p>${auth.name }님./<a href="logout.do">로그아웃</a>
							</p>
							<p>
								<a href="#">예매확인/취소</a>
							</p>
						</c:if> <c:if test="${empty auth }">
							<a href="login.do">로그인</a>
						</c:if></li>
				</ul>
			</div>
		</nav>

	</div>

	<div class="container" id="detail">
		<div class="left_wrap">
			 <img src="./upload/${viewMovieRead.smallPicUrl }">
		</div>

		<div id="wrap">
			<div class="right_wrap">
				<div class="title">
					<h2>
						<strong>${viewMovieRead.korTitle }</strong>
					</h2>
					<p>
						<span id="text_small"> - ${viewMovieRead.engTitle }.</span>
					</p>
				</div>
				<div class="text">
					<p>
						<strong>장르</strong> : ${viewMovieRead.genre } / ${viewMovieRead.timeLength }분
					</p>
					<p>
						<strong>개봉일</strong> : ${viewMovieRead.releaseDate }
					</p>
					<p>
						<strong>감독</strong> : ${viewMovieRead.director }
					</p>
					<p>
						<strong>출연진</strong> : ${viewMovieRead.actors }
					</p>
					<p>
						<strong>등급</strong> : ${viewMovieRead.ageRequire }
					</p>
				</div>
				<div>
					<input type="submit" value="예매하기" data-toggle="modal" data-target="#myModal" />
					<input type="submit" value="상영시간표" />
				</div>
			</div>
		</div>

		<div class="summary">
			<h3>
				<strong>줄거리</strong>
			</h3>
			<p>${viewMovieRead.content }</p>
		</div>


		<!-- 리뷰 -->
		<div class="row" id="rivewInsert">
			<div class="col-md-2 box">
				<p id="iconMargin">
					<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
				</p>
				<p>기대평</p>
			</div>
			<div class="col-md-8 box">
				<textarea style="resize: none;" class="form-control" rows="4" name="content" id="content"></textarea>
			</div>
			<div class="col-md-2 box">
				<input type="submit" value="등록" id="btnReview" />
			</div>


			<div class="col-md-12" id="reviewBox">
			${viewReviewList.content.size() }
				<c:forEach var="reviewList" items="${viewReviewList.content }">
					<%-- <form action="reviewdelete.do?reviewNo=${reviewList.reviewNo}" method="post"> --%>
					<div id="review">
						<p>${reviewList.writer.id }${reviewList.regDate }</p>
						<p>
							-
							<c:out value=" ${reviewList.content }"></c:out>

							<!-- <input type="button" value="수정" id="update"/> -->
							<%-- onclick="location.href='reviewupdate.do?reviewNo=${reviewList.reviewNo}&no=${viewMovieRead.movieNo }'" --%>

							<c:if test="${reviewList.writer.id == user.id}">
								<input type="button" value="X" id="delete" onclick="location.replace('reviewdelete.do?reviewNo=${reviewList.reviewNo}&no=${viewMovieRead.movieNo }')" />
							</c:if>

						</p>

					</div>
					<!-- </form>	 -->
				</c:forEach>

				<div id="pasingBox">
					<c:if test="${viewReviewList.hasReviews() }">
						<c:if test="${viewReviewList.startPage > 5 }">
							<a href="read.do?no=${viewMovieRead.movieNo }&pageNo=${viewReviewList.startPage - 5 }">이전</a>
						</c:if>
						<c:forEach var="pNo" begin="${viewReviewList.startPage }" end="${viewReviewList.endPage }">
							<input type="button" id="pNo" value="${pNo }" onclick="location.href='read.do?no=${viewMovieRead.movieNo }&pageNo=${pNo }'" />
						</c:forEach>
						<c:if test="${viewReviewList.endPage < viewReviewList.totalPages }">
							<a href="read.do?no=${viewMovieRead.movieNo }&pageNo=${viewReviewList.startpage + 5 }">다음</a>
						</c:if>
					</c:if>
				</div>
			</div>
			<!-- <div id="test"></div> -->
		</div>

	</div>
</body>
</html>