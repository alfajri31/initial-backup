CREATE TABLE league (
    id bigint NOT NULL AUTO_INCREMENT,
    user_id bigint NOT NULL,
    name varchar(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE player (
     id bigint NOT NULL AUTO_INCREMENT,
     team_match_id bigint NOT NULL,
     name varchar(50) NOT NULL,
     pos varchar(50) NOT NULL,
     age integer NOT NULL,
     apps integer NOT NULL,
     goals integer NOT NULL,
     first_name varchar(50) NOT NULL,
     last_name varchar(50) NOT NULL,
     club_name varchar(50) NOT NULL,
     citizenship varchar(50) NOT NULL,
     dob Date NOT NULL,
     gol integer NOT NULL,
     height integer NOT NULL,
     weight integer NOT NULL,
     feet VARCHAR(50) NOT NULL,
     PRIMARY KEY(id)
);