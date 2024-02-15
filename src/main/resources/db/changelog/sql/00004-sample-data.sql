--liquibase formatted sql

-- changeset nils-emil:insert-sample-data-cats
INSERT INTO filters (filter_name, create_time) VALUES ('Cats Company', NOW());

-- changeset nils-emil:insert-sample-criteria-cats-1
INSERT INTO filter_criteria (filter_id, criteria_type, comparison_operator, comparison_value)
VALUES ((SELECT id FROM filters WHERE filter_name = 'Cats Company'), 'Title', 'contains', 'Wise');

-- changeset nils-emil:insert-sample-criteria-cats-2
INSERT INTO filter_criteria (filter_id, criteria_type, comparison_operator, comparison_value)
VALUES ((SELECT id FROM filters WHERE filter_name = 'Cats Company'), 'Title', 'contains', 'Cats');