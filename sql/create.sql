CREATE TABLE test (
	id NUMERIC default UNIQUEKEY('test'),
	fueldate date,
	km_total double,
	km_trip double,
	price_liter double,
	price_total double,
	consumption double,
	station text,
	notice text,
	UNIQUE (id),
	PRIMARY KEY (id)
);

