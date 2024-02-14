--liquibase formatted sql

--changeset nils-emil:init-filters
CREATE TABLE filters (
    id SERIAL PRIMARY KEY,
    filter_name VARCHAR(255)
);

--changeset nils-emil:filter_criteria
CREATE TABLE filter_criteria (
    id SERIAL PRIMARY KEY,
    filter_id SERIAL NOT NULL,
    criteria_type VARCHAR(255),
    comparison_operator VARCHAR(255),
    comparison_value VARCHAR(255),
    CONSTRAINT criteria_filter_fk FOREIGN KEY (filter_id) REFERENCES filters(id) ON DELETE CASCADE
);