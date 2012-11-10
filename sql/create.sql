CREATE TABLE car (
  id NUMERIC default UNIQUEKEY('car'),
  fueldate date NOT NULL,
  km_trip double,
  km_total double NOT NULL,
  price_liter double,
  price_total double,
  consumption double,
  station text,
  comments text
);