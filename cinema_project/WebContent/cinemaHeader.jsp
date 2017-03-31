<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
/* 
::-webkit-scrollbar {display: none;} */
::-webkit-scrollbar {
	width: 8px;
	height: 8px;
	border: 3px solid #fff;
}

::-webkit-scrollbar-button:start:decrement, ::-webkit-scrollbar-button:end:increment {
	display: block;
	height: 10px;
	background: url(`./images/bg.png`) #efefef
}

::-webkit-scrollbar-track {
	background: #efefef;
	-webkit-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: inset 0 0 4px rgba(0, 0, 0, .2)
}

::-webkit-scrollbar-thumb {
	height: 50px;
	width: 50px;
	background: rgba(0, 0, 0, .2);
	-webkit-border-radius: 8px;
	border-radius: 8px;
	-webkit-box-shadow: inset 0 0 4px rgba(0, 0, 0, .1)
}

@media ( min-width : 767px) {
	.navbar-nav .dropdown-menu .caret {
		transform: rotate(-90deg);
	}
}

.menu_caret {
	margin-top: 8px;
}

.menu_caret_wrap {
	position: relative;
}
</style>
<script>
	$(function() {
		$('.navbar a.dropdown-toggle').on('click', function(e) {
			var $el = $(this);
			var $parent = $(this).offsetParent(".dropdown-menu");
			$(this).parent("li").toggleClass('open');

			if (!$parent.parent().hasClass('nav')) {
				$el.next().css({
					"top" : $el[0].offsetTop,
					"left" : $parent.outerWidth() - 4
				});
			}

			$('.nav li.open').not($(this).parents("li")).removeClass("open");

			return false;
		});
	})
</script>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">ADMIN</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
					Theater Info <b class="caret"></b>
				</a>

				<ul class="dropdown-menu">


					<li><a href="#" class="dropdown-toggle menu_caret_wrap" data-toggle="dropdown">
							영화관 <b class="caret pull-right menu_caret"></b>
						</a>

						<ul class="dropdown-menu">
							<li><a href="theaterWrite.do">영화관 지점 추가</a></li>
							<li><a href="theaterList.do">영화관 지점 리스트 </a>
						</ul></li>



					<li class="divider"></li>

					<li><a href="#" class="dropdown-toggle menu_caret_wrap" data-toggle="dropdown">
							상영관<b class="caret pull-right menu_caret"></b>
						</a>

						<ul class="dropdown-menu">
							<li><a href="auditoriumWrite.do">상영관 추가</a></li>
							<li><a href="auditoriumList.do"> 상영관 리스트 </a>
						</ul></li>



					<li class="divider"></li>


					<li><a href="#" class="dropdown-toggle menu_caret_wrap" data-toggle="dropdown">
							좌석 <b class="caret pull-right menu_caret"></b>
						</a>

						<ul class="dropdown-menu">
							<li><a href="seatWrite.do">좌석 추가</a></li>
							<li><a href="seatList.do"> 좌석 리스트 </a>
						</ul></li>

				</ul></li>


			<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
					Movie Info <b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#" class="dropdown-toggle menu_caret_wrap" data-toggle="dropdown">
							영화 <b class="caret pull-right menu_caret"></b>
						</a>

						<ul class="dropdown-menu">
							<li><a href="movieWrite.do">영화 추가</a></li>
							<li><a href="movieList.do"> 영화 리스트 </a>
						</ul></li>


					<li class="divider"></li>
					<li><a href="#" class="dropdown-toggle menu_caret_wrap" data-toggle="dropdown">
							스크린<b class="caret pull-right menu_caret"></b>
						</a>

						<ul class="dropdown-menu">
							<li><a href="screenWrite.do">스크린 추가</a></li>
							<li><a href="screenList.do"> 스크린 리스트 </a>
						</ul></li>

					<li class="divider"></li>



					<li class="dropdown-header"><sup>상영스케줄</sup></li>
					<li><a href="scheduleWrite.do"> 상영스케줄 추가</a></li>
					<li><a href="scheduleList.do"> 상영스케줄 리스트</a></li>

				</ul></li>
			<!-- end of Movie Info -->




			<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
					Business Info <b class="caret"></b>
				</a>

				<ul class="dropdown-menu">
					<li class="divider"></li>
					<li class="dropdown-header"><sup>예약 메뉴</sup></li>
					<li><a href="bookingList.do"> 예약 현황</a></li>



					<li class="divider"></li>

					<li><a href="#" class="dropdown-toggle menu_caret_wrap" data-toggle="dropdown">
							가격 <b class="caret pull-right menu_caret"></b>
						</a>

						<ul class="dropdown-menu">
							<li><a href="ticketPriceWrite.do"> 티켓 가격 추가</a></li>
							<li><a href="ticketPriceList.do"> 티켓 가격 리스트 </a>
						</ul></li>


					<li class="divider"></li>

					<li><a href="#" class="dropdown-toggle menu_caret_wrap" data-toggle="dropdown">
							판매 <b class="caret pull-right menu_caret"></b>
						</a>

						<ul class="dropdown-menu">
							<li><a href="screenWrite.do">매출 정보</a></li>
							<li><a href="screenList.do"> 매출 현황 그래프 </a>
						</ul></li>
				</ul></li>
			<!-- end of Business Info -->



			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
					Member Info <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="memberList.do">멤버 리스트</a></li>
					<li><a href="#">기타</a></li>
				</ul></li>

		</ul>
	</div>
</nav>

















<!-- 
			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 영화 <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li>
						<a href="movieWrite.do">추가</a>
					</li>
					<li>
						<a href="movieList.do">리스트</a>
					</li>

				</ul>
			</li>

			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 스크린 <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li>
						<a href="screenWrite.do">추가</a>
					</li>
					<li>
						<a href="screenList.do">리스트</a>
					</li>
				</ul>
			</li>


			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 상영스케줄 <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li>
						<a href="scheduleWrite.do">추가</a>
					</li>
					<li>
						<a href="scheduleList.do">리스트</a>
					</li>
				</ul>
			</li>
 -->
<!-- <li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 영화관 <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li>
						<a href="theaterWrite.do">추가</a>
					</li>
					<li>
						<a href="theaterList.do">리스트</a>
					</li>
				</ul>
			</li>


			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 상영관 <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li>
						<a href="auditoriumWrite.do">추가</a>
					</li>
					<li>
						<a href="auditoriumList.do">리스트</a>
					</li>
				</ul>
			</li>

			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 좌석 <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li>
						<a href="seatWrite.do">추가</a>
					</li>
					<li>
						<a href="seatList.do">리스트</a>
					</li>
				</ul>
			</li> -->

<!-- 			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 가격 <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li>
						<a href="ticketPriceWrite.do">추가</a>
					</li>
					<li>
						<a href="ticketPriceList.do">리스트</a>
					</li>
				</ul>
			</li>
 -->