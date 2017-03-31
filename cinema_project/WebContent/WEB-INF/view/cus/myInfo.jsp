<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="container" id="wrap">
		<div id="logo">
			<img src="image/megabox_logo.png">
		</div>
		
		<form action="memberinfo.do" method="post">
			<div id="form1">				
				<div class="form-group">
					<input type="text" class="form-control" name="id" placeholder="아이디" id="checkId" disabled="disabled" value="${member.id }">
					
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="password" placeholder="비밀번호" value="${member.password }">
					
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="confirmPassword" placeholder="비밀번호 재확인" value="${member.password }">
					
				</div>				
			</div>
			
			<div id="form2">				
				<div class="form-group">
					<input type="text" class="form-control" name="name" placeholder="이름" disabled="disabled" value="${member.name }">
				</div>
				<div class="form-group">
					<c:choose>
						<c:when test="${member.gender == '남자' }">
							<input type="radio" id="1" class="rad" name="gender" value="남자" disabled="disabled" checked="checked"/>
							<label for="1">남자</label>
						</c:when>
						<c:otherwise>
							<input type="radio" id="1" class="rad" name="gender" value="남자" disabled="disabled"/>
							<label for="1">남자</label>
						</c:otherwise>						
					</c:choose>
					<c:choose>
						<c:when test="${member.gender == '여자' }">
							<input type="radio" id="2" class="rad" name="gender" value="여자" disabled="disabled" checked="checked"/>
							<label for="2">여자</label>
						</c:when>
						<c:otherwise>
							<input type="radio" id="2" class="rad" name="gender" value="여자" disabled="disabled"/>
							<label for="2">여자</label>
						</c:otherwise>
					</c:choose>					
				</div>
				<div class="form-group">
					<input type="date" class="form-control" name="birth" placeholder="생년월일" disabled="disabled" value="${member.birth }">
					
				</div>
				<div class="form-group">
					<input type="email" class="form-control" name="email" placeholder="이메일" value="${member.email }">
					
				</div>
				<div class="form-group">
					<input type="tel" class="form-control" name="mobile" placeholder="휴대전화번호 '-없이 입력'" value="${member.mobile }">
					
				</div>	
			</div>
			
			<div id="form3">
				<input type="submit" value="수정하기">
			</div>
		</form>
		
		<div id="footer">
			
		</div>
	</div>