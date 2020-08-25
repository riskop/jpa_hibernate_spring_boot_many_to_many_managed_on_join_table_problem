create sequence hibernate_sequence start with 1 increment by 1;

create table post (
        id bigint not null,
        name varchar(255),
        primary key (id)
    );
    
create table tag (
        tag_id bigint not null,
        name varchar(255),
        primary key (tag_id)
    );

create table post_tag (
       post_id bigint not null,
       tag_id bigint not null,
       primary key (post_id, tag_id)
    );
