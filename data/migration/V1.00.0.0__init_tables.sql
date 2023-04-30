create table designer_entity
(
    designer_id    bigint not null auto_increment,
    designer_fav   bit,
    designer_name  varchar(255),
    designer_salon varchar(255),
    primary key (designer_id)
);

create table member_entity
(
    member_id       bigint not null auto_increment,
    member_cycle    bigint,
    member_email    varchar(255),
    member_name     varchar(255),
    member_password varchar(255),
    member_sex      varchar(255),
    primary key (member_id)
);

create table record_cut_entity
(
    cut_length varchar(255),
    cut_name   varchar(255),
    record_id  bigint not null,
    primary key (record_id)
);

create table record_dyeing
(
    dyeing_color          varchar(255),
    dyeing_decolorization varchar(255),
    dyeing_hurt           varchar(255),
    dyeing_time           bigint,
    record_id             bigint not null,
    primary key (record_id)
);

create table record_entity
(
    record_category varchar(31) not null,
    record_id       bigint      not null auto_increment,
    record_cost     bigint,
    record_date     date,
    record_etc      varchar(255),
    record_grade    varchar(255),
    designer_fk     bigint,
    member_fk       bigint,
    primary key (record_id)
);

create table record_perm_entity
(
    perm_hurt varchar(255),
    perm_name varchar(255),
    perm_time bigint,
    record_id bigint not null,
    primary key (record_id)
);

alter table record_entity
    add constraint record_designer_relation_fk
        foreign key (designer_fk)
            references designer_entity (designer_id);

alter table record_entity
    add constraint record_member_relation_fk
        foreign key (member_fk)
            references member_entity (member_id);

alter table record_cut_entity
    add constraint record_cut_relation_fk
        foreign key (record_id)
            references record_entity (record_id);

alter table record_dyeing
    add constraint record_dyeing_relation_fk
        foreign key (record_id)
            references record_entity (record_id);


alter table record_perm_entity
    add constraint record_perm_relation_fk
        foreign key (record_id)
            references record_entity (record_id);
