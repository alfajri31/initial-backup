CREATE TABLE team_match (
    id bigint NOT NULL AUTO_INCREMENT,
    user_id bigint NOT NULL,
    league_id bigint NOT NULL,
    home_id bigint NOT NULL,
    away_id bigint NOT NULL,
    elapsed SMALLINT NULL,
    date varchar(50) NULL,
    stadium varchar(50) NULL,
    kick_start TIMESTAMP NULL,
    referee varchar(50) NULL,
    live boolean null default false,
    PRIMARY KEY(id)
);

CREATE TABLE home (
  id bigint NOT NULL AUTO_INCREMENT,
  team_match_id bigint NOT NULL,
  name varchar(50) NULL,
  logo_url varchar(255) NOT NULL,
  goals integer NULL,
  winner boolean NULL default false,
  formation varchar(50) NULL,
  coach varchar(50) NULL,
  PRIMARY KEY(id)
);

CREATE TABLE away (
  id bigint NOT NULL AUTO_INCREMENT,
  team_match_id bigint NOT NULL,
  name varchar(50) NULL,
  logo_url varchar(255) NULL,
  goals integer NULL,
  winner boolean NULL default false,
  formation varchar(50) NULL,
  coach varchar(50) NULL,
  PRIMARY KEY(id)
);











