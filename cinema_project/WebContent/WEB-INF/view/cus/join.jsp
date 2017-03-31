<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/nomalize.css" rel="stylesheet">
<link href="css/join.css" rel="stylesheet">
<style type="text/css">
.errorId {
	display: none;
	color: red;
	padding: 0;
	margin: 0 0 0 0;
	font-size: 12px;
}

.error {
	display: none;
	color: red;
	padding: 0;
	margin: 0 0 0 0;
	font-size: 12px;
	padding-top: 8px;
}

.errorRad {
	display: none;
	color: red;
	font-size: 12px;
	padding-top: 8px;
}

.error2 {
	color: red;
	padding: 0;
	margin: 0 0 0 90px;
	font-size: 12px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="js/common.js"></script>

<script type="text/javascript">
	$(function() {

		// 아이디 중복 검사
		$("input[name='id']").on("blur", function() {
			$.ajax({
			url : "checkid.do",
			type : "post",
			timeout : 30000,
			datatype : "json",
			data : {
				"checkId" : $("input[name='id']").val()
			},
			success : function(data) {

				if (data == false) {
					$(".errorId").css("display", "block");
					return false;
				} else {
					$(".errorId").css("display", "none");
				}

			}
			});
		});

		// 회원가입 검사
		$("form[name='f1']")
				.submit(function() {

					// 회원가입 폼창 정보가 빈칸일때 가입 false
					if (!checkInputEmpty($("input[name]"))) {
						if (!($('#radGroup').checked)) {
							$(".errorRad").css("display", "block");
						}
						return false;
					}

					/* // 성별만 선택하지 않았을때 false
					if(!($('#radGroup').checked)){
						alert("성별을 체크해 주세요");
						return false;
					} */

					// password == confirmpassword
					if ($("input[name='password']").val() != $("input[name='confirmPassword']")
							.val()) {
						var $next = $("input[name='confirmPassword']")
								.nextAll(".error").eq(1);
						$next.css("display", "block");
						return false;
					}

				});

	});
</script>
</head>
<body>
	<div class="container" id="wrap">
		<div id="logo">
			<img src="image/megabox_logo.png">
		</div>

		<form action="join.do" method="post" name="f1">
			<div id="form1">
				<div class="form-group">
					<input type="text" class="form-control" name="id" placeholder="아이디" id="checkId">
					<span class="errorId">이미 사용중이거나 탈퇴한 아이디입니다.</span> <span class="error">필수 정보입니다.</span>
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="password" placeholder="비밀번호">
					<span class="error">필수 정보입니다.</span>
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="confirmPassword" placeholder="비밀번호 재확인">
					<span class="error">필수 정보입니다.</span> <span class="error">비밀번호가 일치하지 않습니다.</span>
				</div>
			</div>

			<div id="form2">
				<div class="form-group">
					<input type="text" class="form-control" name="name" placeholder="이름">
					<span class="error">필수 정보입니다.</span>
				</div>
				<div class="form-group" id="radGroup">

					<input type="radio" id="1" class="rad" name="gender" value="남자" />
					<label for="1">남자</label>
					<input type="radio" id="2" class="rad" name="gender" value="여자" />
					<label for="2">여자</label>
					<span class="errorRad">성별을 선택하세요.</span>
				</div>

				<div class="form-group">
					<input type="date" class="form-control birth" name="birth" placeholder="생년월일">
					<span class="error">생년월일을 정확하게 입력하세요.</span>
				</div>
				<div class="form-group">
					<input type="email" class="form-control" name="email" placeholder="이메일">
					<span class="error">필수 정보입니다.</span>
				</div>
				<div class="form-group">
					<input type="tel" class="form-control" name="mobile" placeholder="휴대전화번호 '-없이 입력'">
					<span class="error">필수 정보입니다.</span>
				</div>
			</div>

			<div id="form3">
				<input type="submit" value="가입하기">
			</div>
		</form>

		<div id="footer">
			<img src="image/megabox_logo.png"> <span>Copyright © MEGABOX Corp. All Rights Reserved.</span>
		</div>
	</div>
</body>
</html>