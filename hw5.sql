drop table if exists datalist;
drop table if exists userinfo;
drop table if exists lastTable;


create table datalist(
	
	id integer auto_increment primary key,
	courseCode varchar(100),
	courseName varchar(255),
	prereq varchar(255)
);

insert into datalist (courseCode, courseName, prereq) values('CS120','Introduction to Web Site Development',' ');

insert into datalist (courseCode, courseName, prereq) values('CS122','Using Relational Databases and SQL',' ');

insert into datalist (courseCode, courseName, prereq) values('CS201','Introduction to Programming',' ');

insert into datalist (courseCode, courseName, prereq) values('CS202','Introduction to Object Oriented Programming','CS201');

insert into datalist (courseCode, courseName, prereq) values('CS203','Programming with Data Structures','CS202');

insert into datalist (courseCode, courseName, prereq) values('CS320','Web and Internet Programming','CS120 CS122 CS203');

create table userinfo(
 
	username varchar(100) primary key,
	password varchar(100) not null,
	firstname varchar(100),
	lastname varchar(100)
);

insert into userinfo values ('cysun', 'abcd', 'chengyu', 'sun');

insert into userinfo values ('cs320stu31', 'abcd', 'harshal', 'shah');

create table lastTable(

username varchar(100),
saved_on varchar(255),
quarter varchar(255),
courseCode varchar(100),
courseName varchar(255),
prereq varchar(255)
);


select * from lastTable;