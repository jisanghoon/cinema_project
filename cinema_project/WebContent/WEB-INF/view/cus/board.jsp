<%@page import="cinema.model.User"%>
<%@page import="review.model.Writer"%>
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

#pasingBox {text-align: center; padding: 20px 0px 0px 0px;}
#pasingBox input[type=button] { background-color: #EAEAEA; color: #3F0099; border: 1px solid #3F0099; width: 30px; height: 30px;}
#pasingBox input[type=button]:checked {border: 1px solid #3F0099;}
</style>
</head>
<body>
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
						<c:if test="${!empty auth }">
							<p>${auth.name }님./<a href="logout.do">로그아웃</a></p>
							<p><a href="#">예매확인/취소</a></p>
						</c:if>
						<c:if test="${empty auth }">
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
				<table class="table table-hover">
					<thead>
						<tr class="active">
							<td>NO</td>
							<td>지역/영화관</td>
							<td>제목</td>
							<td>등록일</td>
						</tr>
					</thead>					
					<c:forEach var="boardList" items="${boardList.content }">
 					 <tr>
 					 	<td>${boardList.boardNo }</td>
 					 	<td>${boardList.category }</td>
 					 	<td><a href="boardcontent.do?no=${boardList.boardNo }">${boardList.title }</a></td>
 					 	<td>${boardList.date }<%-- ${boardList.writer.id } --%></td>
 					 </tr>
 					</c:forEach>
				</table>
				<!-- 페이징 -->
				<div id="pasingBox">
					<c:if test="${boardList.hasArticles() }">
					
						<c:if test="${boardList.startPage > 5 }">
							<input type="button" value="이전" onclick="location.href='boardlist.do?pageNo=${boardList.startPage - 5}'"/>
						</c:if>
						
						<c:forEach var="pNo" begin="${boardList.startPage }" end="${boardList.endPage }">
							<input type="button" id="pNo" value="${pNo }" onclick="location.href='boardlist.do?pageNo=${pNo }'"/>
						</c:forEach>
						
						<c:if test="${boardList.endPage < boardList.totalPages }">
							<input type="button" value="다음" onclick="location.href='boardlist.do?pageNo=${boardList.startPage + 5}'"/>
						</c:if>
						
					</c:if>
				</div>
					
					<!-- 관리자 버튼 -->
					<c:if test="${admin.id == 'test' }">
						<input type="button" value="등록" onclick="location.href='boardinsert.do'"/>
					</c:if>
						
								
				
								
			</div>
			
		</div>
	</div>
	
</body>
</body>
</html>