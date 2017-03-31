-- 상영스케줄
DROP TABLE IF EXISTS `schedule` RESTRICT;

-- 상영스케줄
CREATE TABLE `schedule` (
	`schedule_no`  INT  NOT NULL, -- 스케줄번호
	`audi_no`      INT  NOT NULL, -- 상영관번호
	`screen_no`    INT  NOT NULL, -- 스크린번호
	`start_time`   TIME NULL,     -- 시작
	`end_time`     TIME NULL,     -- 종료
	`showing_date` DATE NULL      -- 상영 날짜
);

-- 상영스케줄
ALTER TABLE `schedule`
	ADD CONSTRAINT `PK_schedule` -- 상영스케줄 기본키
		PRIMARY KEY (
			`schedule_no` -- 스케줄번호
		);

ALTER TABLE `schedule`
	MODIFY COLUMN `schedule_no` INT NOT NULL AUTO_INCREMENT;

-- 상영스케줄
ALTER TABLE `schedule`
	ADD CONSTRAINT `FK_auditorium_TO_schedule` -- 상영관 -> 상영스케줄
		FOREIGN KEY (
			`audi_no` -- 상영관번호
		)
		REFERENCES `auditorium` ( -- 상영관
			`audi_no` -- 상영관번호
		)
		ON DELETE CASCADE;

-- 상영스케줄
ALTER TABLE `schedule`
	ADD CONSTRAINT `FK_screen_TO_schedule` -- 스크린 -> 상영스케줄2
		FOREIGN KEY (
			`screen_no` -- 스크린번호
		)
		REFERENCES `screen` ( -- 스크린
			`screen_no` -- 스크린번호
		)
		ON DELETE CASCADE;

/*---------------------------------------------------------*/

select * from schedule;
delete from schedule;
ALTER TABLE schedule AUTO_INCREMENT=1;

/*----------------------------------------------------------*/






insert into schedule(audi_no,screen_no,start_time,end_time) values(1,1,'15:00','17:00');

 /*
	Select * From A left JOIN B
	ON A.name = B.name
	left JOIN C
	ON A.name = C.name
*/

 select s.*,m.title_kor from screen s left join movie m on s.movie_no=m.movie_no; 
 select a.*,t.theater_name from auditorium a left join theater t on a.theater_no=t.theater_no;

 
 
 select * 
 from schedule sch 
 left join (select s.*,m.title_kor from screen s left join movie m on s.movie_no=m.movie_no) sr on sch.screen_no=sr.screen_no
 left join (select a.*,t.theater_name from auditorium a left join theater t on a.theater_no=t.theater_no) audi on sch.audi_no=audi.audi_no where ; 

