-- 예약
ALTER TABLE `cinema`.`booking`
	DROP FOREIGN KEY `FK_member_TO_booking`; -- 고객 -> 예약

-- 예약
ALTER TABLE `cinema`.`booking`
	DROP FOREIGN KEY `FK_ticket_price_TO_booking`; -- 가격 -> 예약

-- 예약
ALTER TABLE `cinema`.`booking`
	DROP FOREIGN KEY `FK_schedule_TO_booking`; -- 상영스케줄 -> 예약

-- 예약
ALTER TABLE `cinema`.`booking`
	DROP PRIMARY KEY; -- 예약 기본키

-- 예약
DROP TABLE IF EXISTS `cinema`.`booking` RESTRICT;

-- 예약
CREATE TABLE `cinema`.`booking` (
	`booking_no`    INT         NOT NULL, -- 예약번호
	`cus_no`        INT         NULL,     -- 고객번호
	`price_no`      INT         NULL,     -- 가격번호
	`schedule_no`   INT         NOT NULL, -- 스케줄번호
	`book_seat_row` VARCHAR(20) NOT NULL, -- 좌석(행)
	`book_seat_col` INT         NOT NULL, -- 좌석(열)
	`accepted_date` DATETIME    NULL      -- 수락된 날
);

-- 예약
ALTER TABLE `cinema`.`booking`
	ADD CONSTRAINT `PK_booking` -- 예약 기본키
		PRIMARY KEY (
			`booking_no` -- 예약번호
		);

ALTER TABLE `cinema`.`booking`
	MODIFY COLUMN `booking_no` INT NOT NULL AUTO_INCREMENT;

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
		
		
/*---------------------------------------------------------*/

select * from booking;
delete from booking;
ALTER TABLE booking AUTO_INCREMENT=1;

/*----------------------------------------------------------*/
insert into booking(user_no,price_no,schedule_no,book_seat_row,book_seat_col,accepted_date) values
(1, 1, 1,'A', 10, '2017-03-30'),
(2, 1, 1,'B', 11, '2017-03-30'),
(3, 1, 1,'C', 12, '2017-03-30'),
(4, 1, 1,'D', 13, '2017-03-30');

/*----------------------------------------------------------*/

select * from booking left join 



/*스크린 영화 조인*/
(select * from screen scr left join movie mo on scr.movie_no=mo.movie_no);

/*상영관 영화관 좌석 조인*/
select * from auditorium audi left join theater th on audi.theater_no=th.theater_no inner join seating st on audi.audi_no=st.audi_no;
/*select * from auditorium audi inner join seating st on audi.audi_no=st.audi_no;*/

select sch.*, one.* from schedule sch 
left join (select scr.*, mo.title_kor, mo.title_eng, mo.age_require from screen scr left join movie mo on scr.movie_no=mo.movie_no) as one  
on sch.screen_no=one.screen_no;

select * from schedule sch 
left join (select scr.*,mo.title_kor,mo.title_eng,mo.actors,mo.director, mo.age_require, mo.time_length, mo.genre from screen scr left join movie mo on scr.movie_no=mo.movie_no) as one  
on sch.screen_no=one.screen_no 
left join (select audi.*,th.theater_name from auditorium audi left join theater th on audi.theater_no=th.theater_no inner join seating st on audi.audi_no=st.audi_no) as two 
on sch.audi_no=two.audi_no;

select * from schedule sch 
left join (select scr.*,mo.title_kor,mo.title_eng,mo.actors,mo.director, mo.age_require, mo.time_length, mo.genre from screen scr left join movie mo on scr.movie_no=mo.movie_no) as one  
on sch.screen_no=one.screen_no 
left join (select audi.*,th.theater_name from auditorium audi left join theater th on audi.theater_no=th.theater_no) as two 
on sch.audi_no=two.audi_no ;



/*--- 스케줄 & 고객 & 가격 테이블 조인 ---*/
select * from booking inner join schedule on booking.schedule_no=schedule.schedule_no;
select * from booking inner join `member` on booking.user_no=`member`.user_no;
select * from booking inner join ticket_price on booking.price_no=ticket_price.price_no;

select bo.*, sch.*,ti.* from booking bo
inner join schedule sch on bo.schedule_no=sch.schedule_no 
inner join `member` m on bo.user_no=m.user_no 
inner join ticket_price ti on bo.price_no=ti.price_no 
order by bo.booking_no;

select bo.*, sch.*,ti.* from booking bo
left join schedule sch on bo.schedule_no=sch.schedule_no 
left join `member` m on bo.user_no=m.user_no 
left join ticket_price ti on bo.price_no=ti.price_no 
order by bo.booking_no;


select bo.*, sch.*,m.*, ti.* from booking bo 
inner join 
(select sch.*, one.movie_no, two.audi_name, two.theater_name ,two.theater_no,two.`floor` from schedule sch left join  (select scr.*,mo.title_kor,mo.title_eng,mo.actors,mo.director, mo.age_require, mo.time_length, mo.genre   from screen scr  left join movie mo  on scr.movie_no=mo.movie_no) as one  on sch.screen_no=one.screen_no  left join  (select  audi.*,th.theater_name   from auditorium audi  left join theater th  on audi.theater_no=th.theater_no) as two   on sch.audi_no=two.audi_no) sch on bo.schedule_no=sch.schedule_no
inner join `member` m on bo.user_no=m.user_no 
inner join ticket_price ti on bo.price_no=ti.price_no order by bo.booking_no;
/*----------------------------------*/


select * from booking  join `member` on booking.user_no=`member`.user_no;

