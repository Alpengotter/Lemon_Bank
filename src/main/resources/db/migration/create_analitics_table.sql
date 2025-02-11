--liquibase formatted sql
--changeset Nedobezhkin.M.I.:create_analytique_table
create table if not exists analytique
(
    id int primary key,
    type_ varchar(50),
    currency_ varchar(50),
    count_ int,
    date_ timestamp
    );
--rollback drop table analytique;

--changeset Nedobezhkin.M.I.:create_analytique_sequence
create sequence analitique_seq start with 1;
--rollback drop sequence analitique_seq;