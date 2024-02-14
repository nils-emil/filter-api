--liquibase formatted sql

--changeset nils-emil:filters-create_time
ALTER TABLE filters ADD COLUMN create_time TIMESTAMP;

