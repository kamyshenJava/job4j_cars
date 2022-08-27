create table if not exists users(
    id serial primary key,
    name text unique not null,
    password text not null
);

create table if not exists car_brands(
    id serial primary key,
    brand text
);

create table if not exists car_models(
    id serial primary key,
    model text,
    car_brand_id int references car_brands(id)
);

create table if not exists cars(
    id serial primary key,
    car_model_id int references car_models(id),
    car_body text
);

create table if not exists ads(
    id serial primary key,
    description text not null,
    is_sold bool,
    created timestamp,
    photo bytea,
    user_id int references users(id),
    car_id int references cars(id)
);




