CREATE TABLE units (
	id NUMERIC default UNIQUEKEY('units'),
	name varchar(255) NOT NULL,
	numberplate varchar(11),
	UNIQUE (id),
	PRIMARY KEY (id)
);

CREATE TABLE vehicles (
	id NUMERIC default UNIQUEKEY('vehicles'),
	vehicle_id int(4) NOT NULL,
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

CREATE TABLE meters (
	id NUMERIC default UNIQUEKEY('meters'),
	meter_id int(4) NOT NULL,
	readingdate date,
	meterreading double,
	newmeter boolean,
	consumption double,
	notice text,
	UNIQUE (id),
	PRIMARY KEY (id)
);

ALTER TABLE vehicles ADD CONSTRAINT fk_vehicles FOREIGN KEY (vehicle_id) REFERENCES units (id) DEFERRABLE;
ALTER TABLE meters ADD CONSTRAINT fk_meters FOREIGN KEY (meter_id) REFERENCES units (id) DEFERRABLE;