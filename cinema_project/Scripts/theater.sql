-- 영화관
DROP TABLE IF EXISTS `theater` RESTRICT;

-- 영화관
CREATE TABLE `theater` (
	`theater_no`          INT          NOT NULL, -- 영화관번호
	`theater_name`        VARCHAR(50)  NOT NULL, -- 지점
	`theater_addr_num`    VARCHAR(200) NOT NULL, -- 지번
	`theater_addr_street` VARCHAR(200) NULL,     -- 도로명
	`theater_manager`     VARCHAR(50)  NULL,     -- 담당자
	`theater_tel`         VARCHAR(30)  NULL      -- 전화번호
)
DEFAULT CHARACTER SET = 'utf8'
ENGINE = InnoDB;

-- 영화관
ALTER TABLE `theater`
	ADD CONSTRAINT `PK_theater` -- 영화관 기본키
		PRIMARY KEY (
			`theater_no` -- 영화관번호
		);

ALTER TABLE `theater`
	MODIFY COLUMN `theater_no` INT NOT NULL AUTO_INCREMENT;
	
	
/*---------------------------------------------------------*/

select * from theater;
delete from theater;
ALTER TABLE theater AUTO_INCREMENT=1;

/*----------------------------------------------------------*/

/*영화 데이터 입력*/
load data infile 'C:/ProgramData/MySQL/MySQL Server 5.6/Uploads/theater.csv' into table theater
fields terminated by ';' 
lines terminated by '\r\n' 
ignore 1 lines
(theater_name, theater_addr_num, theater_addr_street, theater_manager, theater_tel);


insert into theater(theater_name,theater_addr) values('강남','서울시 강남구');

select * from theater where theater_name='강남' and theater_tel='1544-0001';


