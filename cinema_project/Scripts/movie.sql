-- 영화
ALTER TABLE `cinema`.`movie`
	DROP PRIMARY KEY; -- 영화 기본키

-- 영화
DROP TABLE IF EXISTS `cinema`.`movie` RESTRICT;

-- 영화
CREATE TABLE `cinema`.`movie` (
	`movie_no`         INT          NOT NULL, -- 영화번호
	`title_kor`        VARCHAR(50)  NOT NULL, -- 제목(한국어)
	`title_eng`        VARCHAR(50)  NULL,     -- 제목(영어)
	`actors`           VARCHAR(150) NULL,     -- 출연배우
	`director`         VARCHAR(30)  NOT NULL, -- 감독
	`release_date`     DATETIME     NULL,     -- 개봉날짜
	`age_require`      VARCHAR(30)  NOT NULL, -- 상영등급
	`time_length`      INT          NOT NULL, -- 시간
	`country`          VARCHAR(50)  NULL,     -- 나라
	`ratings`          TINYINT      NULL,     -- 평점
	`total_attendance` INT          NULL,     -- 누적관객
	`genre`            VARCHAR(50)  NOT NULL, -- 장르
	`content`          TEXT         NULL,     -- 줄거리
	`small_pic_url`    VARCHAR(500) NULL,     -- 작은이미지
	`big_pic_url`      VARCHAR(500) NULL      -- 큰이미지
);

-- 영화
ALTER TABLE `cinema`.`movie`
	ADD CONSTRAINT `PK_movie` -- 영화 기본키
		PRIMARY KEY (
			`movie_no` -- 영화번호
		);

ALTER TABLE `cinema`.`movie`
	MODIFY COLUMN `movie_no` INT NOT NULL AUTO_INCREMENT;
	
	

/*---------------------------------------------------------*/

select * from movie;
delete from movie;
ALTER TABLE movie AUTO_INCREMENT=1;

/*----------------------------------------------------------*/


	
/*영화 데이터 입력*/
load data infile 'C:/ProgramData/MySQL/MySQL Server 5.6/Uploads/movie_info.txt' into table movie 
fields terminated by '\t' 
lines terminated by '\r\n' 
(movie_no, title_kor, title_eng, director, actors, release_date, age_require, time_length, country, genre, content);

select * from board;





	