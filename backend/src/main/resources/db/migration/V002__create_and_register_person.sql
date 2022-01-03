CREATE TABLE person (

        id BIGINT NOT NULL  AUTO_INCREMENT,
        username VARCHAR(40) NOT NULL,
        active BOOLEAN NOT NULL,
        -- atributos de endereco
        street VARCHAR(50) ,
        number VARCHAR(4) ,
        complement VARCHAR(100) ,
        district VARCHAR(40) ,
        zipcode VARCHAR(8) ,
        city VARCHAR(30) ,
        state VARCHAR(15) ,
        
        PRIMARY KEY (id)

);

INSERT INTO person (username, active, street, number, complement, district, zipcode, city, state) 
        VALUES ("Lucas Nascimento", true, "Av primerio de maio", "147", "prox a prefeitura", "centro", "58511000", "Monteiro","paraiba");