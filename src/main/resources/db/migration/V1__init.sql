create table comment
(
    id               bigint not null auto_increment,
    date_of_creation datetime,
    description      varchar(255),
    event_id         bigint,
    user_id          bigint,
    primary key (id)
) engine = InnoDB;
create table event
(
    id          bigint not null auto_increment,
    description varchar(255),
    end         date,
    start       date,
    title       varchar(255),
    user_id     bigint,
    primary key (id)
) engine = InnoDB;
create table role
(
    id        bigint not null auto_increment,
    role_name varchar(255),
    primary key (id)
) engine = InnoDB;
create table user
(
    id            bigint not null auto_increment,
    email         varchar(255),
    password_hash varchar(255),
    username      varchar(255),
    primary key (id)
) engine = InnoDB;
create table user_roles
(
    user_id  bigint not null,
    roles_id bigint not null,
    primary key (user_id, roles_id)
) engine = InnoDB;
create table event_registered_users
(
    event_id bigint not null,
    registered_users_id  bigint not null,
    primary key (event_id, registered_users_id)
);
alter table comment
    add constraint com_ev_fk foreign key (event_id) references event (id);
alter table comment
    add constraint com_user_fk foreign key (user_id) references user (id);
alter table event
    add constraint event_user_fk foreign key (user_id) references user (id);
alter table user_roles
    add constraint user_roles_role_fk foreign key (roles_id) references role (id);
alter table user_roles
    add constraint user_roles_user_fk foreign key (user_id) references user (id);
alter table event_registered_users
    add constraint event_users_event_fk foreign key (event_id) references event (id);
alter table event_registered_users
    add constraint event_users_user_fk foreign key (registered_users_id) references user (id);
