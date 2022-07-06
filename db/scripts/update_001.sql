create table if not exists users(
    id serial primary key,
    username varchar(255) UNIQUE NOT NULL,
    name varchar(255),
    password varchar(255)
);

create table if not exists ads(
    id serial primary key,
    description varchar(255),
    model varchar(255),
    car_body varchar(255),
    photo bytea,
    is_sold bool,
    user_id int references users(id)
)




