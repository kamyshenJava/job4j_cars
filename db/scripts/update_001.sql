create table if not exists users(
    id serial primary key,
    name varchar(255) UNIQUE NOT NULL,
    password varchar(255) not null
);

create table if not exists ads(
    id serial primary key,
    description varchar(255) not null,
    model varchar(255),
    car_body varchar(255),
    photo bytea,
    is_sold bool,
    user_id int references users(id)
)




