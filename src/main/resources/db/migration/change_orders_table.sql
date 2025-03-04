--liquibase formatted sql
--changeset Nedobezhkin.M.I.:change_order_table
alter table orders
add column date_order_new timestamp;

update orders
set date_order_new = TO_TIMESTAMP(date_order, 'DD.MM.YYYY');

alter table orders
drop column date_order;

alter table orders
rename column date_order_new to date_order;
--rollback ;
