CREATE TABLE users (
  id bigint NOT NULL AUTO_INCREMENT,
  username varchar(100) NOT NULL,
  password varchar(100) NOT NULL,
  guest boolean not null default false,
  PRIMARY KEY (id),
  UNIQUE KEY UK_username (username)
);

CREATE TABLE favourite (
    id bigint NOT NULL AUTO_INCREMENT,
    user_id bigint NOT NULL,
    league_id bigint NOT NULL,
    team_match_id bigint NOT NULL,
    favourite boolean not null default false,
    guest boolean not null default false,
    PRIMARY KEY (id)
);

CREATE TABLE configuration (
    id bigint NOT NULL AUTO_INCREMENT,
    user_id bigint NOT NULL,
    pengingat_pertandingan boolean NOT NULL default false,
    line_up boolean NOT NULL default false,
    kick_off boolean NOT NULL default false,
    half_time boolean NOT NULL default false,
    hasil_pertandingan boolean NOT NULL default false,
    gol boolean NOT NULL default  false,
    penalti boolean NOT NULL default  false,
    kartu_merah boolean NOT NULL default  false,
    video_dan_cuplikan boolean NOT NULL default  false,
    berita boolean NOT NULL default  false,
    PRIMARY KEY(id)
);

CREATE TABLE chat (
    user_id bigint NOT NULL,
    date Date NOT NULL,
    message TEXT,
    favourite integer
);
