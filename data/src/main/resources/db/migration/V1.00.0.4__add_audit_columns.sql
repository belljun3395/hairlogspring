alter table member_entity add  create_at datetime(6) not null;
alter table member_entity add update_at datetime(6) not null;
alter table member_entity add deleted bit not null;

alter table designer_entity add  create_at datetime(6) not null;
alter table designer_entity add update_at datetime(6) not null;
alter table designer_entity add deleted bit not null;

alter table record_entity add  create_at datetime(6) not null;
alter table record_entity add update_at datetime(6) not null;
alter table record_entity add deleted bit not null;