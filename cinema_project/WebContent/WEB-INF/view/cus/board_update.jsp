<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MyPage</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/nomalize.css" rel="stylesheet">
<style type="text/css">
/* 네비게이션 바 */
#header {width: 1000px; padding:0px;}
.navbar-brand {padding-top: 10px; padding-right: 30px;}
.navbar-default {background-color: white;}
#menu li {border-left: 1px solid #EAEAEA; width: 112px; height: 49px; text-align: center;}
#liLogin {border-right: 1px solid #EAEAEA; width: 158px !important;}
#menu li a {font-weight: bold;}
#top { background-color: #f2f2f2; height: 150px; border-bottom: 4px solid #3F0099;}
#topTextBox { width: 1000px; margin:0 auto; padding-top: 110px; font-size: 1.2em; font-weight: bold; color: #3F0099;}
/* 회원페이지 */
#myPage {width: 1000px; padding: 0px; padding-top: 50px; margin: 0 auto;}
</style>
</head>
<body>
	<div class="container-fluid" id="top">
	
		<!-- 네비게이션 바 -->
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="navbar-scroll">
			<div class="container" id="header">
				<a class="navbar-brand" href="#"><img src="image/logo.png"></a>
				<ul class="nav navbar-nav" id="menu">
					<li><a href="#">영화</a></li>
					<li><a href="#">큐레이션</a></li>
					<li><a href="#">영화관</a></li>
					<li><a href="#">특별관</a></li>
					<li><a href="#">스토어</a></li>
					<li><a href="#">이벤트</a></li>
					<li id="liLogin">
						<c:if test="${!empty admin }">
							<p>${admin.name }님./<a href="logout.do">로그아웃</a></p>
							<p><a href="#">예매확인/취소</a></p>
						</c:if>
						<c:if test="${empty admin }">
							<a href="login.do">로그인</a>
						</c:if>
					</li>
				</ul>
			</div>		
		</nav>
		
		<div id="topTextBox">
			<p>공지사항</p>
		</div>		
	</div>
	
	<div class="container" id="myPage">
		<div class="row">
			<div class="col-md-12" id="titleBox">
				<div id="contentBox">
				
					<form action="boardupdate.do?no=${board.boardNo }" method="post">
						<table class="table table-bordered">
							<tr>
								<td class="active">제목</td>
								<td colspan="3"><input type="text" name="title" value="${board.title }" /></td>							
							</tr>
							
							<tr>
								<td class="active">영화관</td>
								<td>
									<select name="category" id="category">
										<option value="전체">전체</option>
										<option value="서울">서울</option>
										<option value="경기">경기</option>
										<option value="대구">대구</option>
										<option value="경북">경북</option>
									</select>
								</td>							
							</tr>
							
							<tr>
								<td colspan="4">
									<textarea name="content" id="content" cols="136" rows="25">${content.content }</textarea>
								</td>
							</tr>
							
						</table>
						
						
							<input type="submit" value="수정"/>
						
						
					</form>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>