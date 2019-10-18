create table if not exists book
(
    `id` identity auto_increment primary key,
    `category` varchar(100),
    main_title varchar(100),
    second_title varchar(100),
    author varchar(100)
);