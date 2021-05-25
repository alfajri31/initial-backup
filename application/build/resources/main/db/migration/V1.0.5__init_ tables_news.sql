CREATE TABLE news_thumbnail (
     id bigint NOT NULL AUTO_INCREMENT,
     user_id bigint NOT NULL,
     title varchar(50) NOT NULL,
     PRIMARY KEY(id)
);

CREATE TABLE news_detail (
     news_thumbnail_id bigint NOT NULL,
     message TEXT,
     date Date NOT NULL
);