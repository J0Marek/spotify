CREATE TABLE IF NOT EXISTS artist(
    id SERIAL NOT NULL PRIMARY KEY ,
    spotify_id varchar(255),
    name varchar(255),
    popularity int,
    type varchar(255),
    followers int,
    UNIQUE (spotify_id)
    );

CREATE TABLE IF NOT EXISTS album(
    id SERIAL NOT NULL PRIMARY KEY ,
    name varchar(255),
    spotify_id varchar(255),
    release_date varchar(255),
    release_date_precision varchar(255),
    total_tracks int,
    type varchar(255),
    album_type varchar(255),
    UNIQUE (spotify_id)
    );

CREATE TABLE IF NOT EXISTS image(
    id SERIAL NOT NULL PRIMARY KEY,
    height int,
    width int,
    url varchar(255)
    );

CREATE TABLE IF NOT EXISTS artist_images(
    artist_id int,
    images_id int,
    FOREIGN KEY (artist_id) REFERENCES artist(id),
    FOREIGN KEY (images_id) REFERENCES image(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS artist_genres(
    artist_id int,
    genres varchar(255),
    FOREIGN KEY (artist_id) REFERENCES artist(id)
    );

CREATE TABLE IF NOT EXISTS album_artists(
    artists_id int,
    album_id int,
    FOREIGN KEY (artists_id) REFERENCES artist(id),
    FOREIGN KEY (album_id) REFERENCES album(id)
    );

CREATE TABLE IF NOT EXISTS album_images(
    album_id int,
    images_id int,
    FOREIGN KEY (album_id) REFERENCES album(id),
    FOREIGN KEY (images_id) REFERENCES image(id) ON DELETE CASCADE
    );