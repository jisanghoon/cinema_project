-- 고객
ALTER TABLE `cinema`.`member`
	DROP PRIMARY KEY; -- 고객 기본키

-- 고객
DROP TABLE IF EXISTS `cinema`.`member` RESTRICT;

-- 고객
CREATE TABLE `cinema`.`member` (
	`user_no`       INT         NOT NULL, -- 고객번호
	`user_id`       VARCHAR(30) NOT NULL, -- 아이디
	`user_pw`       VARCHAR(30) NOT NULL, -- 패스워드
	`user_name`     VARCHAR(30) NOT NULL, -- 이름
	`user_phone`    VARCHAR(30) NOT NULL, -- 전화번호
	`user_email`    VARCHAR(50) NULL,     -- 메일
	`date_of_birth` DATETIME    NOT NULL, -- 생년월일
	`state_of_join` BOOLEAN     NOT NULL, -- 가입상태
	`user_gender`   VARCHAR(2)  NULL,     -- 성별
	`reg_date`      DATETIME    NOT NULL  -- 등록날짜
);

-- 고객
ALTER TABLE `cinema`.`member`
	ADD CONSTRAINT `PK_member` -- 고객 기본키
		PRIMARY KEY (
			`user_no` -- 고객번호
		);

ALTER TABLE `cinema`.`member`
	MODIFY COLUMN `user_no` INT NOT NULL AUTO_INCREMENT;
/*---------------------------------------------------------*/

select * from `member`;
delete from `member`;
ALTER TABLE `member` AUTO_INCREMENT=1;

 SET foreign_key_checks = 0;
 DROP TABLE IF EXISTS `cinema`.`member` RESTRICT;
SET foreign_key_checks = 1;



/*----------------------------------------------------------*/

insert into `member`(`user_id`,`user_pw`,`user_name`,`user_phone`,`user_email`,`date_of_birth`,`state_of_join`,`user_gender`,`reg_date` ) values
('show01','0000' , '홍길동', '010-512-2547','show01@gmail.com','1988-03-21',true,'M','2017-03-01' ),
('show02','0000','이말년','016-254-8547','show02@gmail.com','1985-10-04',true,'M','2017-03-01' ),
('show03','0000','장마담','017-854-6584','show03@gmail.com','1970-07-30',true,'M','2017-03-01' ), 
('show04','0000','길동이','010-7284-5874','show04@gmail.com','1940-02-21',true,'M','2017-03-01' ); 

insert into member(user_id, user_pw, user_name, user_phone, user_email, date_of_birth, state_of_join, user_gender,reg_date) values(?, ?, ?, ?, ?,?, ?, ?, ?)
 

select * from review left join `member` on review.user_no=`member`.user_no where movie_no = 1;







