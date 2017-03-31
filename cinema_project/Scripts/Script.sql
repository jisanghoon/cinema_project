select * from review;

select * from review left join `member` on review.user_no=`member`.user_no where movie_no = 1 order by review_no desc limit 1, 10;