create table address
(
    id        integer not null auto_increment,
    apartment varchar(255),
    city      varchar(255),
    country   varchar(255),
    phone     varchar(255),
    region    varchar(255),
    street    varchar(255),
    user_id   integer,
    primary key (id)
) engine = InnoDB;

create table category
(
    id   integer not null auto_increment,
    name varchar(255),
    primary key (id)
) engine = InnoDB;

create table color
(
    id    integer not null auto_increment,
    color varchar(255),
    primary key (id)
) engine = InnoDB;

create table color_product
(
    product_id integer not null,
    color_id   integer not null
) engine = InnoDB;

create table memory
(
    id     integer not null auto_increment,
    memory varchar(255),
    primary key (id)
) engine = InnoDB;

create table memory_product
(
    product_id integer not null,
    memory_id  integer not null
) engine = InnoDB;

create table menu
(
    id   integer not null auto_increment,
    name varchar(255),
    primary key (id)
) engine = InnoDB;

create table menu_category
(
    menu_id     integer not null,
    category_id integer not null
) engine = InnoDB;

create table order_products
(
    product_order_id integer not null,
    products_id      integer not null
) engine = InnoDB;

create table product
(
    id          integer          not null auto_increment,
    date        datetime(6),
    description varchar(255),
    name        varchar(255),
    pic_url     varchar(255),
    price       double precision not null,
    menu_id     integer,
    primary key (id)
) engine = InnoDB;

create table product_order
(
    id           integer not null auto_increment,
    date         datetime(6),
    price        double precision,
    status_order varchar(255),
    user_id      integer,
    primary key (id)
) engine = InnoDB;

create table products
(
    id         integer not null auto_increment,
    count      integer,
    date       datetime(6),
    price      double precision,
    status     bit,
    color_id   integer,
    memory_id  integer,
    product_id integer,
    user_id    integer,
    primary key (id)
) engine = InnoDB;

create table user
(
    id        integer not null auto_increment,
    age       integer,
    email     varchar(255),
    gender    varchar(255),
    is_active bit,
    name      varchar(255),
    password  varchar(255),
    pic_url   varchar(255),
    surname   varchar(255),
    token     varchar(255),
    user_type varchar(255),
    primary key (id)
) engine = InnoDB
