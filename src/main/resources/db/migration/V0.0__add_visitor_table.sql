CREATE SEQUENCE visitor_sequence;

CREATE TABLE visitor
(
  id          bigint       NOT NULL,
  employee_id VARCHAR(40)  UNIQUE,
  mobile      VARCHAR(40)  NOT NULL UNIQUE,
  name        varchar(255) NOT NULL,
  CONSTRAINT visitor_pkey PRIMARY KEY (id)
);