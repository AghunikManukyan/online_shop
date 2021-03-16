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
) engine = InnoDB;

alter table address
    add constraint FKda8tuywtf0gb6sedwk7la1pgi foreign key (user_id) references user (id);

alter table color_product
    add constraint FK27kr4lcg6wgrpnqwt7fr2a3uk foreign key (color_id) references color (id);

alter table color_product
    add constraint FK2a9x5hjd4onstvhnl41fdhw5p foreign key (product_id) references product (id);

alter table memory_product
    add constraint FK1tbci6rm0emlu1ptutbxe1do7 foreign key (memory_id) references memory (id);

alter table memory_product
    add constraint FKgb1huukpo053po6jgy5dmh6mc foreign key (product_id) references product (id);

alter table menu_category
    add constraint FK1b2ncxm5f7gx23cmcjwkix2w6 foreign key (category_id) references category (id);

alter table menu_category
    add constraint FKcibuohcu46dgr77uxbjpd4qks foreign key (menu_id) references menu (id);

alter table order_products
    add constraint FK4lml11fgty3ga2gc8aghwj7g1 foreign key (products_id) references products (id);

alter table order_products
    add constraint FK6bn9j5gt5008ka7stewmcatf0 foreign key (product_order_id) references product_order (id);

alter table product
    add constraint FKrbd8nv60owg9ny2o3sdt7f1qm foreign key (menu_id) references menu (id);

alter table product_order
    add constraint FKa9own0mc8gwle8cckiij9ubsl foreign key (user_id) references user (id);

alter table products
    add constraint FKsn2kwcua92qiagmuldx21va39 foreign key (color_id) references color (id);

alter table products
    add constraint FKokpwa6shyu908qufts4ua1sys foreign key (memory_id) references memory (id);

alter table products
    add constraint FKclexbljvejjlq1dl4kyc7vhbe foreign key (product_id) references product (id);

alter table products
    add constraint FKhqw0i9tv44oekhnpggx9lbix2 foreign key (user_id) references user (id)
