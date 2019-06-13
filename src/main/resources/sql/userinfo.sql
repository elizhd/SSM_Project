USE mysql;

# CREATE DATABASE PCB_DB;
USE PCB_DB;

CREATE TABLE user (
 	id int PRIMARY KEY  AUTO_INCREMENT,
 	name varchar(64),
 	password varchar(64),
 	email varchar(64),
 	role boolean
);

-- ----------------------------
-- Records of users
-- ----------------------------
insert into user values(1,'admin','admin','admin@live.com',true);
insert into user values(2,'Eric','eric','eric@live.com',false);
insert into user values(3,'李四','lisi','lisi@qq.com',false);
insert into user values(4,'王五','wangwu','wangwu@live.com',false);
insert into user values(5,'张三','zhangsan','zhangsan@qq.com',false );








