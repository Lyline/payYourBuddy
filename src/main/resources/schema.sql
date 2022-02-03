--Reinitialize database
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;


--Create database
create table commission
(
    id    bigserial,
    value numeric(10, 2) not null,
    constraint commission_pkey
        primary key (id)
);

alter table commission
    owner to "admin_payMyBuddy";

create table users
(
    user_id      bigint       not null,
    balance      numeric(10, 2) default 0,
    bank_account varchar(255) not null,
    email        varchar(255) not null,
    first_name   varchar(100) not null,
    last_name    varchar(100) not null,
    password     varchar(255) not null,
    constraint users_pkey
        primary key (user_id),
    constraint email_unique
        unique (email)
);

alter table users
    owner to "admin_payMyBuddy";

create table bank_transaction
(
    transaction_id bigserial,
    description    varchar(150) not null,
    value          numeric(10, 2) default 0,
    commission_id  bigint,
    debtor_user_id bigint,
    constraint bank_transaction_pkey
        primary key (transaction_id),
    constraint commission_id_fk
        foreign key (commission_id) references commission,
    constraint debtor_id_fk
        foreign key (debtor_user_id) references users
);

alter table bank_transaction
    owner to "admin_payMyBuddy";

create table user_transaction
(
    transaction_id   bigserial,
    description      varchar(150) not null,
    value            numeric(10, 2) default 0,
    commission_id    bigint,
    debtor_user_id   bigint,
    creditor_user_id bigint,
    constraint user_transaction_pkey
        primary key (transaction_id),
    constraint commission_id_fk
        foreign key (commission_id) references commission,
    constraint debtor_id_fk
        foreign key (debtor_user_id) references users,
    constraint creditor_id_fk
        foreign key (creditor_user_id) references users
);

alter table user_transaction
    owner to "admin_payMyBuddy";

create table users_friends
(
    user_user_id    bigint not null,
    friends_user_id bigint not null,
    constraint user_id_fk
        foreign key (friends_user_id) references users,
    constraint friend_id_fk
        foreign key (user_user_id) references users
);

alter table users_friends
    owner to "admin_payMyBuddy";


--Implement ID sequence generator
create sequence user_sequence
    start 100 increment by 2;
alter sequence user_sequence owner to "admin_payMyBuddy";

alter sequence bank_transaction_transaction_id_seq
    start 20 increment by 5;
alter sequence bank_transaction_transaction_id_seq owner to "admin_payMyBuddy";

alter sequence user_transaction_transaction_id_seq
    start 20 increment by 5;
alter sequence user_transaction_transaction_id_seq owner to "admin_payMyBuddy";

alter sequence commission_id_seq
    start 1 increment by 1;
alter sequence commission_id_seq owner to "admin_payMyBuddy";