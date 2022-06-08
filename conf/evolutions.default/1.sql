-- !Ups
CREATE TABLE users (
                       id   SERIAL,
                       email varchar(255) NOT NULL,
                       password varchar(255) NOT NULL,
                       first_name varchar(255) NOT NULL,
                       last_name varchar(255) NOT NULL,
                       PRIMARY KEY (id)
);

CREATE TABLE rooms (
                      id   SERIAL,
                      price float NOT NULL,
                      capacity int NOT NULL,
                      PRIMARY KEY (id)
);

CREATE TABLE reservations (
                      id   SERIAL,
                      start_date timestamp  NOT NULL,
                      end_date timestamp  NOT NULL,
                      user_id SERIAL NOT NULL,
                      room_id SERIAL NOT NULL,
                      total_price float DEFAULT 0.0,
                      PRIMARY KEY (id)
);

ALTER TABLE reservations ADD CONSTRAINT fk_reservation_user FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE reservations ADD CONSTRAINT fk_reservation_room FOREIGN KEY (room_id) REFERENCES rooms(id);


-- !Downs
DROP TABLE Stocks;
DROP TABLE User;