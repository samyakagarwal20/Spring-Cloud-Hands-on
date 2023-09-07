create table currency_exchange (id bigint not null, conversion_multiple numeric(38,2), environment varchar(255), currency_from varchar(255), currency_to varchar(255), primary key (id))
