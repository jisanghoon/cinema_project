-- 좌석
DROP TABLE IF EXISTS `seating` RESTRICT;

-- 좌석
CREATE TABLE `seating` (
	`seat_no`   INT         NOT NULL, -- 좌석번호
	`row`       INT         NOT NULL, -- 행
	`col`       INT         NOT NULL, -- 열
	`seat_name` VARCHAR(20) NOT NULL, -- 좌석이름
	`audi_no`   INT         NOT NULL  -- 상영관번호
);

-- 좌석
ALTER TABLE `seating`
	ADD CONSTRAINT `PK_seating` -- 좌석 기본키
		PRIMARY KEY (
			`seat_no` -- 좌석번호
		);

ALTER TABLE `seating`
	MODIFY COLUMN `seat_no` INT NOT NULL AUTO_INCREMENT;

-- 좌석
ALTER TABLE `seating`
	ADD CONSTRAINT `FK_auditorium_TO_seating` -- 상영관 -> 좌석
		FOREIGN KEY (
			`audi_no` -- 상영관번호
		)
		REFERENCES `auditorium` ( -- 상영관
			`audi_no` -- 상영관번호
		)
		ON DELETE CASCADE;
		
/*---------------------------------------------------------*/

select * from seating;
delete from seating;
ALTER TABLE seating AUTO_INCREMENT=1;

/*----------------------------------------------------------*/
		
insert into seats(`row`,`col`,ready,audi_no) values('a',1,TRUE,1);
update seats set row='d', col=5, audi_no=11 where seat_no=5;




/*영화 데이터 입력*/
load data infile 'C:/ProgramData/MySQL/MySQL Server 5.6/Uploads/seat_data_1.csv' into table seats 
fields terminated by ',' 
lines terminated by '\r\n'
ignore 1 lines
(`row`, `col`, @ready, `audi_no`) SET ready = (@ready = 'TRUE'); 


select s.*, au.audi_name, au.theater_name, au.theater_no ,(select count(if(seat_name='empty',null,1)) from seating where seating.audi_no=au.audi_no ) as sum  
from seating s left join  (select a.*,t.theater_name from auditorium a left join theater t on a.theater_no=t.theater_no) as au 
on s.audi_no=au.audi_no group by audi_no;

select count(*) from seating;

select audi_no, count(if(seat_name='empty',null,1)) as sum from seating group by audi_no;

select s.*, `au`.audi_name,`au`.theater_name,`au`.theater_no from seating s 
left join  (select a.*, th.theater_name from auditorium a left join theater th on a.theater_no=th.theater_no) as `au`
on s.audi_no=`au`.audi_no where s.audi_no=1;



select s.*, au.audi_name, au.theater_no, au.theater_name  
from seating s left join	
(select a.*, t.theater_name from auditorium a left join theater t on a.theater_no=t.theater_no) as au
on s.audi_no=au.audi_no;

select s.*,a.theater_no  
from seating s left join	auditorium a 
on s.audi_no=a.audi_no;




		
select * from seating where audi_no = 1;


select * from auditorium ;
		
select * from ticket_price;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		