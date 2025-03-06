--liquibase formatted sql
--changeset Nedobezhkin.M.I.:change_analytique_values
update analytique
set count_ = -count_
where type_ = 'accept_order';
--rollback ;
