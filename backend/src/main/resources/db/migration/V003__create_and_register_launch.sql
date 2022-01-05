CREATE TABLE launch (

        id BIGINT NOT NULL  AUTO_INCREMENT,
        description VARCHAR(60) NOT NULL,
        date_loser DATE NOT NULL,
        date_payment DATE,
        price DECIMAL(10,2) NOT NULL,
        note VARCHAR(100) ,
        launch_type VARCHAR(20) ,
        id_category BIGINT NOT NULL,
        id_person BIGINT NOT NULL,
        
        PRIMARY KEY (id),
        FOREIGN KEY (id_category) REFERENCES category(id),
        FOREIGN KEY (id_person) REFERENCES person(id)

);

INSERT INTO launch (description, date_loser, date_payment, price, note, launch_type, id_category, id_person) 
        VALUES ("Salário do mês", '2022-01-01', null, 3100.00, "comprovante de pagamento", "RECEITA", 2, 1);

INSERT INTO launch (description, date_loser, date_payment, price, note, launch_type, id_category, id_person) 
        VALUES ("Feira do mês", '2022-01-06', '2022-01-01', 1200.00, "comprovante de compra", "DESPESA", 1, 1);

INSERT INTO launch (description, date_loser, date_payment, price, note, launch_type, id_category, id_person) 
        VALUES ("Ingresso do FEST-VERÃO", '2022-01-08', '2022-01-02', 300.00, "comprovante  do Fest-Verão", "DESPESA", 3, 1);