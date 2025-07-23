insert into users
(username, password, email, nickname, tel)
values
('hong', '$2a$10$cGQ8YORL9m9JTvB6GciWAuni9ffWsMBBQCxChza2vuZyCa0ejI9zi', 'hong@gmail.com', '홍길동', '010-1234-1111');

insert into users
(username, password, email, nickname, tel, deleted)
values
('kim', '$2a$10$cGQ8YORL9m9JTvB6GciWAuni9ffWsMBBQCxChza2vuZyCa0ejI9zi', 'kim@gmail.com', '김유신', '010-1234-2222', true);

insert into posts
(user_no, title, content)
values
(1, '연습1', '연습1 입니다.');

insert into posts
(user_no, title, content)
values
(2, '연습2', '연습2 입니다.');

insert into posts
(user_no, title, content)
values
(1, '연습3', '연습3 입니다.');

insert into posts
(user_no, title, content)
values
(2, '연습4', '연습4 입니다.');

insert into posts
(user_no, title, content, comment_cnt)
values
(1, '연습5', '연습5 입니다.', 3);

insert into post_comments
(user_no, post_no, content)
values
(1, 5, '첫번째 댓글입니다.');

insert into post_comments
(user_no, post_no, content)
values
(2, 5, '두번째 댓글입니다.');

insert into post_comments
(user_no, post_no, content)
values
(1, 5, '세번째 댓글입니다.');

commit;