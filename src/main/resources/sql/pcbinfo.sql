USE mysql;

# CREATE DATABASE PCB_DB;
USE PCB_DB;

CREATE TABLE pcb_info (
 	id int PRIMARY KEY  AUTO_INCREMENT,
 	name varchar(64),
 	address varchar(64),
 	type varchar(30)
);

-- ----------------------------
-- Records of stuffs
-- ----------------------------

insert into pcb_info values(1,'膨松段输送速度','D5400','PLC');
insert into pcb_info values(2,'除胶渣段输送速度','D5402','PLC');
insert into pcb_info values(3,'PTH段输送速度','D5404','PLC');
insert into pcb_info values(4,'化铜段输送速度',	'D5406','PLC');
insert into pcb_info values(5,'膨松1流量','D5516','PLC');
insert into pcb_info values(6,'除胶渣1-1流量','D5596','PLC');
insert into pcb_info values(7,'PTH段输送速度','D5404','PLC');
insert into pcb_info values(8,'中和1流量','D5776','PLC');
insert into pcb_info values(9,'除油1流量','D5856','PLC');
insert into pcb_info values(10,'微蚀1压力','D5976','PLC');
insert into pcb_info values(11,'中和酸碱度',	'D5836','PLC');
insert into pcb_info values(12,'还原酸碱度','D6356','PLC');
insert into pcb_info values(13,'膨松温度','1','Temperature');
insert into pcb_info values(14,'除胶渣1温度','2','Temperature');
insert into pcb_info values(15,'预中和温度','3','Temperature');
insert into pcb_info values(16,'除油温度','4','Temperature');
insert into pcb_info values(17,'微蚀温度','5','Temperature');
insert into pcb_info values(18,'预浸温度','6','Temperature');
insert into pcb_info values(19,'活化温度','7','Temperature');
insert into pcb_info values(20,'化铜1温度','8','Temperature');








