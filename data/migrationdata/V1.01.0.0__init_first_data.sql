insert
into member_entity
(create_at, member_cycle, deleted, member_email, member_name, member_password, member_sex,
 update_at)
values (now(), 1, false, 'test@test.com', 'testmname', '12345', 'M', now());


insert
into designer_entity
(create_at, deleted, designer_fav, designer_name, designer_salon, member_fk, update_at)
values (now(), false, true, 'testdname', 'testsalon', 1, now());


insert
into record_entity
(create_at, deleted, designer_fk, member_fk, record_cost, record_date, record_etc, record_grade,
 update_at, record_category)
values (now(), false, 1, 1, 10000, now(), '', 'H', now(), 'cut');


insert
into record_cut_entity
    (cut_length, cut_name, record_id)
values (1, 'testcname', 1);

insert
into record_entity
(create_at, deleted, designer_fk, member_fk, record_cost, record_date, record_etc, record_grade,
 update_at, record_category)
values (now(), false, 1, 1, 10000, now(), '', 'H', now(), 'perm');

insert
into record_perm_entity
(perm_name, perm_time, perm_hurt, record_id)
values ('testpname', 1, 'L', 2);

insert
into record_entity
(create_at, deleted, designer_fk, member_fk, record_cost, record_date, record_etc, record_grade,
 update_at, record_category)
values (now(), false, 1, 1, 10000, now(), '', 'H', now(), 'dyeing');

insert
into record_dyeing_entity
(dyeing_color, dyeing_decolorization, dyeing_time, dyeing_hurt, record_id)
values ('color', 'decolorization', 1, 'L', 3);
