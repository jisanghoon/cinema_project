-- 가격
DROP TABLE IF EXISTS `ticket_price` RESTRICT;

-- 가격
CREATE TABLE `ticket_price` (
	`price_no`    INT         NOT NULL, -- 가격번호
	`cate_day`    VARCHAR(30) NOT NULL, -- 주중주말
	`cate_time`   VARCHAR(50) NOT NULL, -- 시간구분
	`cate_audi`   VARCHAR(50) NULL,     -- 상영관구분
	`cate_screen` VARCHAR(50) NULL,     -- 스크린타입구분
	`cate_seat`   VARCHAR(50) NULL,     -- 좌석구분
	`cate_age`    VARCHAR(30) NOT NULL, -- 나이구분
	`price`       INT         NOT NULL  -- 가격
)
DEFAULT CHARACTER SET = 'utf8'
ENGINE = InnoDB;

-- 가격
ALTER TABLE `ticket_price`
	ADD CONSTRAINT `PK_ticket_price` -- 가격 기본키
		PRIMARY KEY (
			`price_no` -- 가격번호
		);

ALTER TABLE `ticket_price`
	MODIFY COLUMN `price_no` INT NOT NULL AUTO_INCREMENT;
	
	

	
/*---------------------------------------------------------*/

select * from ticket_price;
delete from ticket_price;
ALTER TABLE ticket_price AUTO_INCREMENT=1;

/*----------------------------------------------------------*/


load data infile 'C:/ProgramData/MySQL/MySQL Server 5.6/Uploads/price.csv' into table  ticket_price
fields terminated by ';' 
lines terminated by '\r\n' 
(cate_peak, cate_time, cate_age, @couple, @cate_3d, price) SET couple = (@couple = 'TRUE'), cate_3d = (@cate_3d = 'TRUE');

update ticket_price set price=500000 where price_no=4;



