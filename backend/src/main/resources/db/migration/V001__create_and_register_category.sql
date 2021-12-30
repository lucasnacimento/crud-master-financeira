CREATE TABLE category (

        id BIGINT NOT NULL  AUTO_INCREMENT,
        name VARCHAR(50) NOT NULL,

        PRIMARY KEY (id)

);

INSERT INTO category (name) VALUES ("Supermercado");
INSERT INTO category (name) VALUES ("Tecnologia");
INSERT INTO category (name) VALUES ("Lazer");