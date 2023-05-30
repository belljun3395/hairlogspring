alter table member_entity modify column member_password varchar (255) not null;
alter table member_entity modify column member_name varchar (255) not null;
alter table member_entity modify column member_sex varchar (255) not null;


alter table designer_entity modify column designer_name varchar (255) not null;
alter table designer_entity modify column designer_fav bit not null;

alter table record_entity modify column record_date date not null;
alter table record_entity modify column record_cost bigint not null;
alter table record_entity modify column record_grade varchar (255) not null;

alter table record_perm_entity modify column perm_name varchar (255) not null;
alter table record_perm_entity modify column perm_time bigint not null;
alter table record_perm_entity modify column perm_hurt varchar (255) not null;

alter table record_dyeing_entity modify column dyeing_color varchar (255) not null;
alter table record_dyeing_entity modify column dyeing_decolorization varchar (255) not null;
alter table record_dyeing_entity modify column dyeing_time bigint not null;
alter table record_dyeing_entity modify column dyeing_hurt varchar (255) not null;

alter table record_cut_entity modify column cut_name varchar (255) not null;
alter table record_cut_entity modify column cut_length bigint not null;