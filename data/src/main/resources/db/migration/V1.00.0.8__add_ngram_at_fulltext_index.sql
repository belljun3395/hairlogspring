drop index idx_ft_name on designer_entity;

alter table designer_entity add fulltext index idx_ft_name (designer_name) with PARSER NGRAM;
