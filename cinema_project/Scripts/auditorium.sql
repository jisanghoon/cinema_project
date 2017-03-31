-- 상영관
DROP TABLE IF EXISTS `auditorium` RESTRICT;

-- 상영관
CREATE TABLE `auditorium` (
	`audi_no`    INT         NOT NULL, -- 상영관번호
	`audi_name`  VARCHAR(50) NOT NULL, -- 상영관이름
	`audi_type`  VARCHAR(50) NOT NULL, -- 상영관종류
	`floor`      VARCHAR(50) NULL,     -- 장소
	`theater_no` INT         NOT NULL  -- 영화관번호
);

-- 상영관
ALTER TABLE `auditorium`
	ADD CONSTRAINT `PK_auditorium` -- 상영관 기본키
		PRIMARY KEY (
			`audi_no` -- 상영관번호
		);

ALTER TABLE `auditorium`
	MODIFY COLUMN `audi_no` INT NOT NULL AUTO_INCREMENT;

-- 상영관
ALTER TABLE `auditorium`
	ADD CONSTRAINT `FK_theater_TO_auditorium` -- 영화관 -> 상영관
		FOREIGN KEY (
			`theater_no` -- 영화관번호
		)
		REFERENCES `theater` ( -- 영화관
			`theater_no` -- 영화관번호
		)
		ON DELETE CASCADE;
/*---------------------------------------------------------*/

select * from auditorium;
delete from auditorium;
ALTER TABLE auditorium AUTO_INCREMENT=1;

/*----------------------------------------------------------*/



select a.*, t.theater_no,t.theater_name from auditorium as a left join theater as t on a.theater_no =t.theater_no order;

/*상영관 데이터 입력*/
load data infile 'C:/ProgramData/MySQL/MySQL Server 5.6/Uploads/auditorium.csv' into table auditorium
fields terminated by ';' 
lines terminated by '\r\n' 
(audi_name, `floor`, theater_no);



select a.*, t.theater_name from auditorium as a left join theater as t on a.theater_no =t.theater_no where audi_no=1;
select * count(if(seat_name='empty',null,1)) from auditorium as audi left join seating on audi.audi_no=seating.audi_no ;


select audi.*,th.theater_name, count(if(seat_name='empty',null,1)) as seat_cnt from auditorium as audi left join seating on audi.audi_no=seating.audi_no  
left join theater th on audi.theater_no=th.theater_no 
group by audi.audi_no;

select audi.*, th.theater_name,seating.* from auditorium as audi left join seating on audi.audi_no=seating.audi_no  
left join theater th on audi.theater_no=th.theater_no where audi.audi_no=1 ;










