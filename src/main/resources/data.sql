drop table IF exists TLB_USERS;

CREATE TABLE TBL_USERS (
   id IDENTITY PRIMARY KEY NOT NULL,
   name VARCHAR(50) NOT NULL,
   last_name VARCHAR(50) NOT NULL
);

insert into tbl_users(id,name,last_name) values (1,'Nathalia','Porra');
insert into tbl_users(id,name,last_name) values (2,'Juan','Mosquera');
insert into tbl_users(id,name,last_name) values (3,'Maria','Vega');
insert into tbl_users(id,name,last_name) values (4,'Federico','Arango');
insert into tbl_users(id,name,last_name) values (5,'Valentina','Cardona');