-- 스크린
ALTER TABLE `screen`
	DROP FOREIGN KEY `FK_movie_TO_screen`; -- 영화 -> 스크린

-- 스크린
ALTER TABLE `screen`
	DROP PRIMARY KEY; -- 스크린 기본키

-- 스크린
DROP TABLE IF EXISTS `screen` RESTRICT;

-- 스크린
CREATE TABLE `screen` (
	`screen_no`    INT         NOT NULL, -- 스크린번호
	`movie_no`     INT         NOT NULL, -- 영화번호
	`screen_mode`  VARCHAR(20) NOT NULL, -- 스크린모드
	`buy_date`     DATETIME    NOT NULL, -- 구입 날짜
	`screen_price` BIGINT      NOT NULL, -- 금액
	`supplier`     VARCHAR(30) NULL,     -- 공급자
	`start_date`   DATETIME    NULL,     -- 상영 시작 날짜
	`end_date`     DATETIME    NULL      -- 상영 중단 날짜
);

-- 스크린
ALTER TABLE `screen`
	ADD CONSTRAINT `PK_screen` -- 스크린 기본키
		PRIMARY KEY (
			`screen_no` -- 스크린번호
		);

ALTER TABLE `screen`
	MODIFY COLUMN `screen_no` INT NOT NULL AUTO_INCREMENT;

-- 스크린
ALTER TABLE `screen`
	ADD CONSTRAINT `FK_movie_TO_screen` -- 영화 -> 스크린
		FOREIGN KEY (
			`movie_no` -- 영화번호
		)
		REFERENCES `movie` ( -- 영화
			`movie_no` -- 영화번호
		);

/*---------------------------------------------------------*/

select * from screen;
delete from screen;
ALTER TABLE screen AUTO_INCREMENT=1;

/*----------------------------------------------------------*/





select screen.*,movie.title_kor from screen left join movie on screen.movie_no=movie.movie_no;
/*스크린 데이터 입력*/
load data infile 'C:/ProgramData/MySQL/MySQL Server 5.6/Uploads/screen.csv' into table screen
fields terminated by ';' 
lines terminated by '\r\n' 
ignore 1 lines
(theater_no, movie_no, start_date );



select screen.*, movie.title_kor, theater.theater_name  
from screen 
left join movie on screen.movie_no=movie.movie_no  
left join theater on screen.theater_no=theater.theater_no
order by movie_no, screen_no asc;




select sm.*
from (select s.*, m.title_kor from screen s left join movie m on s.movie_no=m.movie_no) as sm 
 left join  theater t               on sm.theater_no=t.theater_no where t.theater_no=1;
 
 
 select sm.*
from (select s.*, m.title_kor from screen s left join movie m on s.movie_no=m.movie_no) as sm 
 left join  theater t               on sm.theater_no=t.theater_no where t.theater_no=1;

 


select screen.*, movie.title_kor, theater.theater_name from screen left join movie 
on screen.movie_no=movie.movie_no left join theater 
on screen.theater_no=theater.theater_no 
where screen_no=1; 
 

 




















