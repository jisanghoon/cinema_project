<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/nomalize.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="./js/common.js"></script>
<script type="text/javascript">
	$(function() {
		$("form[name='f1']").submit(function() {
			if (!checkInputEmpty($("input[name]"))) { return false; }
		});
	});
</script>
</head>
<body>
	<div class="container" id="wrap">
		<div id="logo">
			<img src="image/megabox_logo.png">
		</div>

		<form action="login.do" method="post" name="f1">
			<div id="form1">
				<div class="form-group">
					<input type="text" class="form-control" name="id" placeholder="아이디">
					<span class="error">아이디를 입력하세요.</span>
				</div>
			</div>

			<div id="form2">
				<div class="form-group">
					<input type="password" class="form-control" name="password" placeholder="비밀번호">
					<span class="error">비밀번호를 입력하세요.</span>
				</div>
			</div>

			<div id="form3">
				<input type="submit" value="로그인">
			</div>

		</form>

		<div id="loginOption">
			<p>
				<a href="#">아이디 찾기</a>
				&nbsp; | &nbsp;
				<a href="#">비밀번호 찾기</a>
				&nbsp; | &nbsp;
				<a href="join.do">회원가입</a>
			</p>
		</div>

		<div id="banner">
			<img src="image/banner.jpg">
		</div>
		<div id="footer">

			<img src="image/megabox_logo.png"> <span>Copyright © MEGABOX Corp. All Rights Reserved.</span>
		</div>
	</div>
</body>
</html>