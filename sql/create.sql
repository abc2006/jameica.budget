CREATE TABLE test (
	id NUMERIC default UNIQUEKEY('test'),
	buchstabensalat text,
	UNIQUE (id),
	PRIMARY KEY (id)
);

