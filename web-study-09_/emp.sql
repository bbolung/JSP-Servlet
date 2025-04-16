-- web-study-9 연습문제
create table employees(
    id varchar2(10) not null,
    pass varchar2(10) not null,
    name varchar2(24),
    lev char(1) default 'A',        -- A: 운영자, B: 일반 회원
    enter date default sysdate,     -- 등록일
    gender char(1) default '1',     -- 1: 남자, 2: 여자
    phone varchar2(30)
    
);

insert into employees(id, pass, name, lev, gender, phone) values('pinksung', '3333', '성윤정', 'A', '2', '010-2222-2222');
insert into employees(id, pass, name, lev, gender, phone) values('subin', '1234', '전원지', 'B', '1', '010-9999-9999');
insert into employees(id, pass, name, lev, gender, phone) values('admin', '1111', '성윤정', 'A', '1', '010-1111-1111');

commit;

select *
from employees;

-- sysdate에 시간까지 표시
select id, pass, name, to_char(ENTER, 'YYYY-MM-DD HH:MM:SS') lev, gender, phone
from employees;
