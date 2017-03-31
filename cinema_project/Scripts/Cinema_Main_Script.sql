-- cinema
DROP SCHEMA IF EXISTS `cinema`;

-- cinema
CREATE SCHEMA `cinema`;

-- 공지사항
CREATE TABLE `cinema`.`board` (
	`board_no` INT         NOT NULL COMMENT '게시판번호', -- 게시판번호
	`category` VARCHAR(15) NOT NULL COMMENT '분류', -- 분류
	`title`    VARCHAR(30) NOT NULL COMMENT '제목', -- 제목
	`b_date`   DATETIME    NOT NULL COMMENT '등록날짜', -- 등록날짜
	`u_id`     VARCHAR(30) NOT NULL COMMENT '등록Id' -- 등록Id
)
COMMENT '공지사항';

-- 공지사항
ALTER TABLE `cinema`.`board`
	ADD CONSTRAINT `PK_board` -- 공지사항 기본키
		PRIMARY KEY (
			`board_no` -- 게시판번호
		);

ALTER TABLE `cinema`.`board`
	MODIFY COLUMN `board_no` INT NOT NULL AUTO_INCREMENT COMMENT '게시판번호';

-- 고객
CREATE TABLE `cinema`.`member` (
	`cus_no`        INT         NOT NULL COMMENT '고객번호', -- 고객번호
	`id`            VARCHAR(30) NOT NULL COMMENT '아이디', -- 아이디
	`pasword`       VARCHAR(30) NOT NULL COMMENT '패스워드', -- 패스워드
	`name`          VARCHAR(30) NOT NULL COMMENT '이름', -- 이름
	`phone`         VARCHAR(30) NOT NULL COMMENT '전화번호', -- 전화번호
	`mail`          VARCHAR(50) NULL     COMMENT '메일', -- 메일
	`date_of_birth` DATETIME    NOT NULL COMMENT '생년월일' -- 생년월일
)
COMMENT '고객';

-- 고객
ALTER TABLE `cinema`.`member`
	ADD CONSTRAINT `PK_member` -- 고객 기본키
		PRIMARY KEY (
			`cus_no` -- 고객번호
		);

ALTER TABLE `cinema`.`member`
	MODIFY COLUMN `cus_no` INT NOT NULL AUTO_INCREMENT COMMENT '고객번호';

-- 예약
CREATE TABLE `cinema`.`booking` (
	`booking_no`    INT         NOT NULL COMMENT '예약번호', -- 예약번호
	`cus_no`        INT         NULL     COMMENT '고객번호', -- 고객번호
	`price_no`      INT         NULL     COMMENT '가격번호', -- 가격번호
	`schedule_no`   INT         NOT NULL COMMENT '스케줄번호', -- 스케줄번호
	`book_seat_row` VARCHAR(20) NOT NULL COMMENT '좌석(행)', -- 좌석(행)
	`book_seat_col` INT         NOT NULL COMMENT '좌석(열)', -- 좌석(열)
	`accepted_date` DATETIME    NULL     COMMENT '수락된 날' -- 수락된 날
)
COMMENT '예약';

-- 예약
ALTER TABLE `cinema`.`booking`
	ADD CONSTRAINT `PK_booking` -- 예약 기본키
		PRIMARY KEY (
			`booking_no` -- 예약번호
		);

ALTER TABLE `cinema`.`booking`
	MODIFY COLUMN `booking_no` INT NOT NULL AUTO_INCREMENT COMMENT '예약번호';

-- 가격
CREATE TABLE `cinema`.`ticket_price` (
	`price_no`    INT         NOT NULL COMMENT '가격번호', -- 가격번호
	`cate_day`    VARCHAR(30) NOT NULL COMMENT '주중주말', -- 주중주말
	`cate_time`   VARCHAR(50) NOT NULL COMMENT '시간구분', -- 시간구분
	`cate_audi`   VARCHAR(50) NULL     COMMENT '상영관구분', -- 상영관구분
	`cate_screen` VARCHAR(50) NULL     COMMENT '스크린타입구분', -- 스크린타입구분
	`cate_seat`   VARCHAR(50) NULL     COMMENT '좌석구분', -- 좌석구분
	`cate_age`    VARCHAR(30) NOT NULL COMMENT '나이구분', -- 나이구분
	`price`       INT         NOT NULL COMMENT '가격' -- 가격
)
DEFAULT CHARACTER SET = 'utf8'
ENGINE = InnoDB
COMMENT '가격';

-- 가격
ALTER TABLE `cinema`.`ticket_price`
	ADD CONSTRAINT `PK_ticket_price` -- 가격 기본키
		PRIMARY KEY (
			`price_no` -- 가격번호
		);

ALTER TABLE `cinema`.`ticket_price`
	MODIFY COLUMN `price_no` INT NOT NULL AUTO_INCREMENT COMMENT '가격번호';

-- 좌석
CREATE TABLE `cinema`.`seating` (
	`seat_no`   INT         NOT NULL COMMENT '좌석번호', -- 좌석번호
	`row`       INT         NOT NULL COMMENT '행', -- 행
	`col`       INT         NOT NULL COMMENT '열', -- 열
	`seat_name` VARCHAR(20) NOT NULL COMMENT '좌석이름', -- 좌석이름
	`audi_no`   INT         NOT NULL COMMENT '상영관번호' -- 상영관번호
)
COMMENT '좌석';

-- 좌석
ALTER TABLE `cinema`.`seating`
	ADD CONSTRAINT `PK_seating` -- 좌석 기본키
		PRIMARY KEY (
			`seat_no` -- 좌석번호
		);

ALTER TABLE `cinema`.`seating`
	MODIFY COLUMN `seat_no` INT NOT NULL AUTO_INCREMENT COMMENT '좌석번호';

-- 상영스케줄
CREATE TABLE `cinema`.`schedule` (
	`schedule_no` INT  NOT NULL COMMENT '스케줄번호', -- 스케줄번호
	`audi_no`     INT  NOT NULL COMMENT '상영관번호', -- 상영관번호
	`screen_no`   INT  NOT NULL COMMENT '스크린번호', -- 스크린번호
	`start_time`  TIME NULL     COMMENT '시작', -- 시작
	`end_time`    TIME NULL     COMMENT '종료', -- 종료
	`show_date`   DATE NULL     COMMENT '상영 날짜' -- 상영 날짜
)
COMMENT '상영스케줄';

-- 상영스케줄
ALTER TABLE `cinema`.`schedule`
	ADD CONSTRAINT `PK_schedule` -- 상영스케줄 기본키
		PRIMARY KEY (
			`schedule_no` -- 스케줄번호
		);

ALTER TABLE `cinema`.`schedule`
	MODIFY COLUMN `schedule_no` INT NOT NULL AUTO_INCREMENT COMMENT '스케줄번호';

-- 상영관
CREATE TABLE `cinema`.`auditorium` (
	`audi_no`    INT         NOT NULL COMMENT '상영관번호', -- 상영관번호
	`audi_name`  VARCHAR(50) NOT NULL COMMENT '상영관이름', -- 상영관이름
	`audi_type`  VARCHAR(50) NOT NULL COMMENT '상영관종류', -- 상영관종류
	`floor`      VARCHAR(50) NULL     COMMENT '장소', -- 장소
	`theater_no` INT         NOT NULL COMMENT '영화관번호' -- 영화관번호
)
COMMENT '상영관';

-- 상영관
ALTER TABLE `cinema`.`auditorium`
	ADD CONSTRAINT `PK_auditorium` -- 상영관 기본키
		PRIMARY KEY (
			`audi_no` -- 상영관번호
		);

ALTER TABLE `cinema`.`auditorium`
	MODIFY COLUMN `audi_no` INT NOT NULL AUTO_INCREMENT COMMENT '상영관번호';

-- 스크린
CREATE TABLE `cinema`.`screen` (
	`screen_no`    INT         NOT NULL COMMENT '스크린번호', -- 스크린번호
	`movie_no`     INT         NOT NULL COMMENT '영화번호', -- 영화번호
	`screen_mode`  VARCHAR(20) NOT NULL COMMENT '스크린모드', -- 스크린모드
	`buy_date`     DATETIME    NOT NULL COMMENT '구입 날짜', -- 구입 날짜
	`screen_price` BIGINT      NOT NULL COMMENT '금액', -- 금액
	`supplier`     VARCHAR(30) NULL     COMMENT '공급자', -- 공급자
	`start_date`   DATETIME    NULL     COMMENT '상영 시작 날짜', -- 상영 시작 날짜
	`end_date`     DATETIME    NULL     COMMENT '상영 중단 날짜' -- 상영 중단 날짜
)
COMMENT '스크린';

-- 스크린
ALTER TABLE `cinema`.`screen`
	ADD CONSTRAINT `PK_screen` -- 스크린 기본키
		PRIMARY KEY (
			`screen_no` -- 스크린번호
		);

ALTER TABLE `cinema`.`screen`
	MODIFY COLUMN `screen_no` INT NOT NULL AUTO_INCREMENT COMMENT '스크린번호';

-- 영화
CREATE TABLE `cinema`.`movie` (
	`movie_no`         INT          NOT NULL COMMENT '영화번호', -- 영화번호
	`title_kor`        VARCHAR(50)  NOT NULL COMMENT '제목(한국어)', -- 제목(한국어)
	`title_eng`        VARCHAR(50)  NULL     COMMENT '제목(영어)', -- 제목(영어)
	`actors`           VARCHAR(150) NULL     COMMENT '출연배우', -- 출연배우
	`director`         VARCHAR(30)  NOT NULL COMMENT '감독', -- 감독
	`release_date`     DATETIME     NULL     COMMENT '개봉날짜', -- 개봉날짜
	`age_require`      TINYINT      NOT NULL COMMENT '상영등급', -- 상영등급
	`time_length`      INT          NOT NULL COMMENT '시간', -- 시간
	`country`          VARCHAR(50)  NULL     COMMENT '나라', -- 나라
	`ratings`          TINYINT      NULL     COMMENT '평점', -- 평점
	`total_attendance` INT          NULL     COMMENT '누적관객', -- 누적관객
	`genre`            VARCHAR(50)  NOT NULL COMMENT '장르', -- 장르
	`content`          TEXT         NULL     COMMENT '줄거리', -- 줄거리
	`small_pic_url`    VARCHAR(500) NULL     COMMENT '작은이미지', -- 작은이미지
	`big_pic_url`      VARCHAR(500) NULL     COMMENT '큰이미지' -- 큰이미지
)
COMMENT '영화';

-- 영화
ALTER TABLE `cinema`.`movie`
	ADD CONSTRAINT `PK_movie` -- 영화 기본키
		PRIMARY KEY (
			`movie_no` -- 영화번호
		);

ALTER TABLE `cinema`.`movie`
	MODIFY COLUMN `movie_no` INT NOT NULL AUTO_INCREMENT COMMENT '영화번호';

-- 공시사항내용
CREATE TABLE `cinema`.`board_content` (
	`bo_cont_no` INT  NOT NULL COMMENT '개시내용번호', -- 개시내용번호
	`board_no`   INT  NOT NULL COMMENT '게시판번호', -- 게시판번호
	`content`    TEXT NOT NULL COMMENT '내용' -- 내용
)
COMMENT '공시사항내용';

-- 공시사항내용
ALTER TABLE `cinema`.`board_content`
	ADD CONSTRAINT `PK_board_content` -- 공시사항내용 기본키
		PRIMARY KEY (
			`bo_cont_no` -- 개시내용번호
		);

ALTER TABLE `cinema`.`board_content`
	MODIFY COLUMN `bo_cont_no` INT NOT NULL AUTO_INCREMENT COMMENT '개시내용번호';

-- 댓글
CREATE TABLE `cinema`.`reply` (
	`reply_no` INT         NOT NULL COMMENT '댓글번호', -- 댓글번호
	`content`  TEXT        NOT NULL COMMENT '내용', -- 내용
	`user_id`  VARCHAR(30) NULL     COMMENT '유저', -- 유저
	`movie_no` INT         NULL     COMMENT '영화번호', -- 영화번호
	`cus_no`   INT         NULL     COMMENT '고객번호' -- 고객번호
)
COMMENT '댓글';

-- 댓글
ALTER TABLE `cinema`.`reply`
	ADD CONSTRAINT `PK_reply` -- 댓글 기본키
		PRIMARY KEY (
			`reply_no` -- 댓글번호
		);

ALTER TABLE `cinema`.`reply`
	MODIFY COLUMN `reply_no` INT NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

-- 영화관
CREATE TABLE `cinema`.`theater` (
	`theater_no`          INT          NOT NULL COMMENT '영화관번호', -- 영화관번호
	`theater_name`        VARCHAR(50)  NOT NULL COMMENT '지점', -- 지점
	`theater_addr_num`    VARCHAR(200) NOT NULL COMMENT '지번', -- 지번
	`theater_addr_street` VARCHAR(200) NULL     COMMENT '도로명', -- 도로명
	`theater_manager`     VARCHAR(50)  NULL     COMMENT '담당자', -- 담당자
	`theater_tel`         VARCHAR(30)  NULL     COMMENT '전화번호' -- 전화번호
)
DEFAULT CHARACTER SET = 'utf8'
ENGINE = InnoDB
COMMENT '영화관';

-- 영화관
ALTER TABLE `cinema`.`theater`
	ADD CONSTRAINT `PK_theater` -- 영화관 기본키
		PRIMARY KEY (
			`theater_no` -- 영화관번호
		);

ALTER TABLE `cinema`.`theater`
	MODIFY COLUMN `theater_no` INT NOT NULL AUTO_INCREMENT COMMENT '영화관번호';

-- 예약
ALTER TABLE `cinema`.`booking`
	ADD CONSTRAINT `FK_member_TO_booking` -- 고객 -> 예약
		FOREIGN KEY (
			`cus_no` -- 고객번호
		)
		REFERENCES `cinema`.`member` ( -- 고객
			`cus_no` -- 고객번호
		);

-- 예약
ALTER TABLE `cinema`.`booking`
	ADD CONSTRAINT `FK_ticket_price_TO_booking` -- 가격 -> 예약
		FOREIGN KEY (
			`price_no` -- 가격번호
		)
		REFERENCES `cinema`.`ticket_price` ( -- 가격
			`price_no` -- 가격번호
		);

-- 예약
ALTER TABLE `cinema`.`booking`
	ADD CONSTRAINT `FK_schedule_TO_booking` -- 상영스케줄 -> 예약
		FOREIGN KEY (
			`schedule_no` -- 스케줄번호
		)
		REFERENCES `cinema`.`schedule` ( -- 상영스케줄
			`schedule_no` -- 스케줄번호
		);

-- 좌석
ALTER TABLE `cinema`.`seating`
	ADD CONSTRAINT `FK_auditorium_TO_seating` -- 상영관 -> 좌석
		FOREIGN KEY (
			`audi_no` -- 상영관번호
		)
		REFERENCES `cinema`.`auditorium` ( -- 상영관
			`audi_no` -- 상영관번호
		)
		ON DELETE CASCADE;

-- 상영스케줄
ALTER TABLE `cinema`.`schedule`
	ADD CONSTRAINT `FK_auditorium_TO_schedule` -- 상영관 -> 상영스케줄
		FOREIGN KEY (
			`audi_no` -- 상영관번호
		)
		REFERENCES `cinema`.`auditorium` ( -- 상영관
			`audi_no` -- 상영관번호
		)
		ON DELETE CASCADE;

-- 상영스케줄
ALTER TABLE `cinema`.`schedule`
	ADD CONSTRAINT `FK_screen_TO_schedule` -- 스크린 -> 상영스케줄2
		FOREIGN KEY (
			`screen_no` -- 스크린번호
		)
		REFERENCES `cinema`.`screen` ( -- 스크린
			`screen_no` -- 스크린번호
		)
		ON DELETE CASCADE;

-- 상영관
ALTER TABLE `cinema`.`auditorium`
	ADD CONSTRAINT `FK_theater_TO_auditorium` -- 영화관 -> 상영관
		FOREIGN KEY (
			`theater_no` -- 영화관번호
		)
		REFERENCES `cinema`.`theater` ( -- 영화관
			`theater_no` -- 영화관번호
		)
		ON DELETE CASCADE;

-- 스크린
ALTER TABLE `cinema`.`screen`
	ADD CONSTRAINT `FK_movie_TO_screen` -- 영화 -> 스크린
		FOREIGN KEY (
			`movie_no` -- 영화번호
		)
		REFERENCES `cinema`.`movie` ( -- 영화
			`movie_no` -- 영화번호
		);

-- 공시사항내용
ALTER TABLE `cinema`.`board_content`
	ADD CONSTRAINT `FK_board_TO_board_content` -- 공지사항 -> 공시사항내용
		FOREIGN KEY (
			`board_no` -- 게시판번호
		)
		REFERENCES `cinema`.`board` ( -- 공지사항
			`board_no` -- 게시판번호
		)
		ON DELETE CASCADE;

-- 댓글
ALTER TABLE `cinema`.`reply`
	ADD CONSTRAINT `FK_movie_TO_reply` -- 영화 -> 댓글
		FOREIGN KEY (
			`movie_no` -- 영화번호
		)
		REFERENCES `cinema`.`movie` ( -- 영화
			`movie_no` -- 영화번호
		)
		ON DELETE CASCADE;

-- 댓글
ALTER TABLE `cinema`.`reply`
	ADD CONSTRAINT `FK_member_TO_reply` -- 고객 -> 댓글
		FOREIGN KEY (
			`cus_no` -- 고객번호
		)
		REFERENCES `cinema`.`member` ( -- 고객
			`cus_no` -- 고객번호
		);