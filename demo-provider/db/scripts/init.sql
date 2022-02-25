\c provider1


CREATE TABLE trade (
  id              SERIAL PRIMARY KEY,
  origin CHAR(10) NOT NULL,
  destination CHAR(10) NOT NULL,
  tradeId VARCHAR(40) NOT NULL,
  executiontime timestamp NOT NULL);

INSERT INTO
	trade(origin, destination, tradeId, executiontime)
VALUES
  ('Markit', 'CME', 'Trade1', '2020-12-01 12:10:00'),
  ('Markit', 'CME', 'Trade2', '2020-12-01 13:25:00'),
  ('Markit', 'CME', 'Trade3', '2020-12-01 09:15:00');