CREATE TABLE team_match (
    id bigint NOT NULL AUTO_INCREMENT,
    user_id bigint NOT NULL,
    league_id bigint NOT NULL,
    home_id bigint NOT NULL,
    away_id bigint NOT NULL,
    minute integer NOT NULL,
    date Date NOT NULL,
    stadium varchar(50) NOT NULL,
    kick_start integer NOT NULL,
    referee varchar(50) NOT NULL,
    live boolean not null default false,
    PRIMARY KEY(id)
);

CREATE TABLE home (
  team_match_id bigint NOT NULL,
  name varchar(50) NOT NULL,
  score integer NULL,
  win boolean null default false,
  formation varchar(50) NOT NULL,
  coach varchar(50) NULL
);

CREATE TABLE away (
  team_match_id bigint NOT NULL,
  name varchar(50) NOT NULL,
  score integer NULL,
  win boolean null default false,
  formation varchar(50) NULL,
  coach varchar(50) NOT NULL
);











