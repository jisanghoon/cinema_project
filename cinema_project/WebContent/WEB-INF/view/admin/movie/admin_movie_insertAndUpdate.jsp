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
<link rel="stylesheet" href="./css/flatpickr.min.css">
<script src="./js/flatpickr.min.js"></script>


<style type="text/css">
textarea {
	resize: none;
}

.submitBtn {
	background: blue;
	color: white;
	margin-top: 50px;
	margin-bottom: 150px;
}

.img_box {
	text-align: center;
}

.img_box .big_pre {
	width: 300px;
}

.img_box .small_pre {
	width: 200px;
}

.preText {
	display: inline;
	vertical-align: top;
}

.small_pre_text {
	margin-left: 50px;
}

#releaseDate {
	background: white !important;
}

.control-label {
	text-align: left !important;
	padding-left: 60px;
}

.country {
	padding-left: 120px;
}
</style>
<title>Insert title here</title>

<script type="text/javascript">
	function previewBig(html, $target) {
		if (html.files && html.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$target.attr('src', e.target.result);
			}
			reader.readAsDataURL(html.files[0]);
		}
	}
	
	function previewSmall(html, $target) {
		if (html.files && html.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$target.attr('src', e.target.result);
			}
			reader.readAsDataURL(html.files[0]);
		}
	}

	$(function() {

		$("#releaseDate").flatpickr();
		$('[data-toggle="tooltip"]').tooltip();

		if ($('a').is('.hidenGerne')) {
			$('.hidenGerne').each(function(i, element) {
				var arr_gerne = $('.opt_gerne')

				for (var i = 0; i < arr_gerne.length; i++) {

					if ($(this).text().trim() == arr_gerne.eq(i).val()) {
						arr_gerne.eq(i).prop("selected", true);
					}

				}

			});

		}

	});
</script>
</head>
<body>
	<jsp:include page="/cinemaHeader.jsp"></jsp:include>


	<div class="container">
		<h2>영화 등록</h2>
		<c:if test="${ empty movie }">
			<form class="form-horizontal" method="post" action="movieWrite.do" enctype="multipart/form-data">
		</c:if>

		<c:if test="${! empty movie }">
			<form class="form-horizontal" method="post" action="movieUpdate.do" enctype="multipart/form-data">
				<input type="hidden" name="movieNo" value="${movie.movieNo }">
		</c:if>
		<br> <br>
		<!-- 		<div class="form-group img_box">
			<h3 class="preText">Big_Image</h3>
			<img class="img-thumbnail big_pre" src="./img/no_image_placeholder.png" alt="NO IMAGE" width="350" height="350" id="image_big">
			<h3 class="preText small_pre_text">Small_Image</h3>
			<img class="img-thumbnail small_pre" src="./img/no_image_placeholder.png" alt="NO IMAGE" width="200" height="200" id="image_small">
		</div> -->
		<div class="form-group">
			<label for="korTitle" class="control-label col-sm-2">Big_Image</label>
			<div class="col-sm-4 ">
				<c:if test="${ empty movie }">
					<img class="img-thumbnail big_pre" src="./img/no_image_placeholder.png" alt="NO IMAGE" width="350" height="350" id="image_big">
				</c:if>
				<c:if test="${! empty movie }">
					<img class="img-thumbnail big_pre" src="./upload/${movie.bigPicUrl }" alt="NO IMAGE" width="350" height="350" id="image_big">
				</c:if>
			</div>
			<label for="engTitle" class=" control-label col-sm-2">Small_Image</label>
			<div class="col-sm-4 ">
				<c:if test="${ empty movie }">
					<img class="img-thumbnail small_pre" src="./img/no_image_placeholder.png" alt="NO IMAGE" width="200" height="200" id="image_small">
				</c:if>
				<c:if test="${! empty movie }">
					<img class="img-thumbnail big_pre" src="./upload/${movie.smallPicUrl}" alt="NO IMAGE" width="350" height="350" id="image_small">
				</c:if>
			</div>
		</div>
		<br>


		<%-- 	<div class="form-group">
			<label for="korTitle" class="control-label col-sm-2">Title(kor)</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="korTitle" id="korTitle" required value="${movie.korTitle }" />
			</div>
		</div>
		<div class="form-group">
			<label for="engTitle" class="control-label col-sm-2">Title(eng)</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="engTitle" id="engTitle" required value="${movie.engTitle }" />
			</div>
		</div>
 --%>


		<div class="form-group">
			<label for="korTitle" class="control-label col-sm-2">Title (Kor)</label>
			<div class="col-sm-4 ">
				<input type="text" class="form-control" name="korTitle" id="korTitle" required value="${movie.korTitle }" />
			</div>
			<label for="engTitle" class=" control-label col-sm-2">Title (Eng)</label>
			<div class="col-sm-4 ">
				<input type="text" class="form-control" name="engTitle" id="engTitle" required value="${movie.engTitle }" />
			</div>

		</div>





		<div class="form-group">
			<label for="director" class="control-label col-sm-2 ">감독</label>
			<div class="col-sm-4 ">
				<input type="text" name="director" class="form-control" id="director" required value="${movie.director }" />
			</div>
			<label for="actors" class="control-label col-sm-2">출연배우</label>
			<div class="col-sm-4">
				<input type="text" name="actors" class="form-control" id="actors" required value="${movie.actors }" />
			</div>
		</div>



		<%-- 		<div class="form-group">
			<label for="director" class="control-label col-sm-2">감독</label>
			<div class="col-sm-10">
				<input type="text" name="director" class="form-control" id="director" required value="${movie.director }" />
			</div>
		</div>

		<div class="form-group">
			<label for="releaseDate" class="control-label col-sm-2">개봉날짜</label>
			<div class="col-sm-10 ">
				<input type="text" name="releaseDate" class="form-control" id="releaseDate" required value="${movie.releaseDate }" />
			</div>
		</div> --%>



		<div class="form-group">
			<label for="ageRequire" class="control-label col-sm-2">상영등급</label>
			<div class="col-sm-10">
				<label class="radio-inline">
					<input type="radio" name="ageRequire" value="전체 관람가" ${movie.ageRequire == '전체 관람가' ? 'checked' : ''} checked>
					전체 관람가
				</label>
				<label class="radio-inline">
					<input type="radio" name="ageRequire" value="12세 관람가" ${movie.ageRequire == '12세 관람가' ? 'checked' : ''}>
					12세 관람가
				</label>
				<label class="radio-inline">
					<input type="radio" name="ageRequire" value="15세 관람가" ${movie.ageRequire == '15세 관람가' ? 'checked' : ''}>
					15세 관람가
				</label>
				<label class="radio-inline">
					<input type="radio" name="ageRequire" value="청소년 관람불가" ${movie.ageRequire == '청소년 관람불가' ? 'checked' : ''}>
					청소년 관람불가
				</label>
			</div>
			<%-- 	<select class="form-control " id="ageRequire" name="ageRequire" value="${movie.ageRequire }" required>
					<option value="전체 관람가">전체 관람가</option>
					<option value="12세 관람가">12세 관람가</option>
					<option value="15세 관람가">15세 관람가</option>
					<option value="청소년 관람불가">청소년 관람불가</option>
				</select> --%>
		</div>

		<div class="form-group">

			<label for="releaseDate" class=" control-label col-sm-2">개봉날짜</label>
			<div class="col-sm-2 ">
				<input type="text" name="releaseDate" class="form-control" id="releaseDate" required value="${movie.releaseDate }" placeholder="연도-월-일" />
			</div>
			<label for="timeLegth" class="control-label col-sm-2 timeLegth">시간(단위:분)</label>
			<div class="col-sm-2 ">
				<input type="text" name="timeLegth" class="form-control" id="timeLegth" required value="${movie.timeLength }" />
			</div>
			<label for="country" class=" control-label col-sm-2 country">국가</label>
			<div class="col-sm-2 ">
				<input type="text" name="country" class="form-control" id="country" required value="${movie.country }" />
			</div>
		</div>







		<%-- 

		<div class="form-group">
			<label for="timeLegth" class="control-label col-sm-2">시간(단위:분)</label>
			<div class="col-sm-10">
				<input type="text" name="timeLegth" class="form-control" id="timeLegth" required value="${movie.timeLength }" />
			</div>
		</div>
		<div class="form-group">
			<label for="country" class="control-label col-sm-2">국가</label>
			<div class="col-sm-10">
				<input type="text" name="country" class="form-control" id="country" required value="${movie.country }" />
			</div>
		</div>
 --%>








		<div class="form-group">
			<label for="genre" class="control-label col-sm-2">장르</label>
			<div class="col-sm-10">
				<c:forEach var="item" items="${gerneList }">
					<a hidden class="hidenGerne">${item }</a>
				</c:forEach>
				<select multiple class="form-control" size="7" data-placement="left" data-toggle="tooltip" id="genre" name="genre" title="중복 선택 : ctrl+click" required>

					<option class="opt_gerne" value="액션">액션</option>
					<option class="opt_gerne" value="SF">SF</option>
					<option class="opt_gerne" value="코미디">코미디</option>
					<option class="opt_gerne" value="판타지">판타지</option>
					<option class="opt_gerne" value="스릴러">스릴러</option>
					<option class="opt_gerne" value="전쟁">전쟁</option>
					<option class="opt_gerne" value="가족">가족</option>
					<option class="opt_gerne" value="멜로">멜로</option>
					<option class="opt_gerne" value="스포츠">스포츠</option>
					<option class="opt_gerne" value="애니메이션">애니메이션</option>
					<option class="opt_gerne" value="재난">재난</option>
					<option class="opt_gerne" value="다큐멘터리">다큐멘터리</option>
					<option class="opt_gerne" value="음악">음악</option>
				</select>

			</div>
		</div>

		<div class="form-group">
			<label for="content" class="control-label col-sm-2">줄거리</label>
			<div class="col-sm-10">
				<textarea rows="10" cols="0" name="content" class="form-control" id="content">${movie.content }</textarea>
			</div>
		</div>
		<br>
		<div class="form-group">
			<label for="bigPic" class="control-label col-sm-2">큰 이미지</label>
			<div class="col-sm-10">
				<input type="file" name="bigPic" class="form-control" id="bigPic" accept="image/*" onchange="previewBig(this,$('#image_big'))" value="${movie.bigPicUrl }" />
			</div>

		</div>
		<div class="form-group">
			<label for="smallPic" class="control-label col-sm-2">작은이미지</label>
			<div class="col-sm-10">
				<input type="file" name="smallPic" class="form-control" id="smallPic" accept="image/*" onchange="previewSmall(this,$('#image_small'))" value="${movie.smallPicUrl }" />
			</div>
		</div>


		<br>

		<div class="btn-group col-md-offset-6">
			<input type="submit" value="저장" class="btn btn-default submitBtn btn-lg " />
			<input type="reset" value="취소" class="btn btn-default submitBtn btn-lg " />
		</div>
		</form>
	</div>
</body>
</html>