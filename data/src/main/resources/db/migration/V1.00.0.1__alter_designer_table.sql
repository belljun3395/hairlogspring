alter table designer_entity add member_fk bigint;
alter table designer_entity add constraint designer_member_relation_fk foreign key(member_fk) references member_entity (member_id);