# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table menu (
  id                        bigint auto_increment not null,
  mac                       varchar(255),
  hostip                    varchar(255),
  menuid                    integer,
  clickcount                bigint,
  constraint pk_menu primary key (id))
;

create table menu_time (
  id                        bigint auto_increment not null,
  mac                       varchar(255),
  hostip                    varchar(255),
  menuid                    integer,
  timestamp                 bigint,
  due_date                  datetime,
  constraint pk_menu_time primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table menu;

drop table menu_time;

SET FOREIGN_KEY_CHECKS=1;

