--liquibase formatted sql

-- changeset nils-emil:insert-sample-data
INSERT INTO filters (filter_name, create_time) VALUES ('Sample Liquibase Filter', NOW());

-- changeset nils-emil:insert-sample-criteria-1
INSERT INTO filter_criteria (filter_id, criteria_type, comparison_operator, comparison_value)
VALUES ((SELECT id FROM filters WHERE filter_name = 'Sample Liquibase Filter'), 'Title', 'contains', 'Liquibase');

-- changeset nils-emil:insert-sample-criteria-2
INSERT INTO filter_criteria (filter_id, criteria_type, comparison_operator, comparison_value)
VALUES ((SELECT id FROM filters WHERE filter_name = 'Sample Liquibase Filter'), 'Title', 'contains', 'Filter');