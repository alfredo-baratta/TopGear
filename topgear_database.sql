CREATE SCHEMA IF NOT EXISTS topgear;

USE topgear;

CREATE TABLE utenti (
    cf VARCHAR(16) PRIMARY KEY,
    nome VARCHAR(30) NOT NULL,
    cognome VARCHAR(30) NOT NULL,
    datanascita DATE NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(32) NOT NULL,
    telefono VARCHAR(30) NOT NULL,
    citta VARCHAR(30) NOT NULL,
    via VARCHAR(50) NOT NULL,
    cap VARCHAR(5) NOT NULL,
    tipo ENUM('utente', 'amministratore')  default 'utente'
);

CREATE TABLE veicoli (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descrizione TEXT NOT NULL,
    marchio VARCHAR(30) NOT NULL,
    cilindrata INT,
    modello VARCHAR(30) NOT NULL,
    iva FLOAT NOT NULL,
    kwh INT NOT NULL,
    cavalli INT NOT NULL,
    prezzo FLOAT NOT NULL,
    visibilita BOOLEAN DEFAULT 0
);

CREATE TABLE colori_esterno (
    id INT PRIMARY KEY AUTO_INCREMENT,
    colore VARCHAR(30) NOT NULL,
    prezzo FLOAT NOT NULL,
    iva FLOAT NOT NULL,
    visibilita BOOLEAN DEFAULT 0
);

CREATE TABLE colori_interno (
    id INT PRIMARY KEY AUTO_INCREMENT,
    colore VARCHAR(30) NOT NULL,
    prezzo FLOAT NOT NULL,
    iva FLOAT NOT NULL,
    visibilita BOOLEAN DEFAULT 0
);

CREATE TABLE cerchioni (
    id INT PRIMARY KEY AUTO_INCREMENT,
    prezzo FLOAT NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    iva FLOAT NOT NULL,
    visibilita BOOLEAN DEFAULT 0
);

CREATE TABLE accessori (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    descrizione TEXT NOT NULL,
    disponibilita INT NOT NULL,
    prezzo FLOAT NOT NULL,
    iva FLOAT NOT NULL,
    visibilita BOOLEAN DEFAULT 0
);

CREATE TABLE personalizzazioni (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fk_utente VARCHAR(16) NOT NULL,
    fk_veicolo INT NOT NULL,
    fk_colori_esterno INT NOT NULL,
    fk_colori_interno INT not null,
    fk_cerchione INT NOT NULL,
    FOREIGN KEY (fk_utente) REFERENCES utenti (cf)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (fk_veicolo) REFERENCES veicoli (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (fk_colori_esterno) REFERENCES colori_esterno (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (fk_colori_interno) REFERENCES colori_interno (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (fk_cerchione) REFERENCES cerchioni (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE ordini (
    id INT PRIMARY KEY AUTO_INCREMENT,
    totale FLOAT NOT NULL,
    data_pagamento DATE NOT NULL,
    fk_utente VARCHAR(16) NOT NULL,
    tipo ENUM('accessorio', 'personalizzazione') default 'accessorio',
    FOREIGN KEY (fk_utente) REFERENCES utenti (cf)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE ordini_personalizzazioni (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fk_ordine INT NOT NULL,
    fk_personalizzazioni INT NOT NULL,
    FOREIGN KEY (fk_ordine) REFERENCES ordini (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (fk_personalizzazioni) REFERENCES personalizzazioni (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE ordini_accessorio (
    id INT PRIMARY KEY AUTO_INCREMENT,
    quantita INT not null,
    fk_ordine INT NOT NULL,
    fk_accessorio INT NOT NULL,
    FOREIGN KEY (fk_ordine) REFERENCES ordini (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (fk_accessorio) REFERENCES accessori (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE immagini_accessorio (
    id INT PRIMARY KEY AUTO_INCREMENT,
    immagine MEDIUMBLOB NOT NULL,
    fk_accessorio INT NOT NULL,
    FOREIGN KEY (fk_accessorio) REFERENCES accessori (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE immagini_veicolo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    immagine MEDIUMBLOB NOT NULL,
    fk_veicolo INT NOT NULL,
    FOREIGN KEY (fk_veicolo) REFERENCES veicoli (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE fattura_personalizzazioni (
    id INT PRIMARY KEY AUTO_INCREMENT,
    prezzo FLOAT NOT NULL,
    iva FLOAT NOT NULL,
    fk_personalizzazione INT NOT NULL,
    fk_ordine_personalizzazione INT NOT NULL,
    FOREIGN KEY (fk_personalizzazione) REFERENCES personalizzazioni (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (fk_ordine_personalizzazione) REFERENCES ordini_personalizzazioni (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE fattura_accessori (
    id INT PRIMARY KEY AUTO_INCREMENT,
    prezzo FLOAT NOT NULL,
    iva FLOAT NOT NULL,
    fk_accessorio INT NOT NULL,
    fk_ordine_accessorio INT NOT NULL,
    FOREIGN KEY (fk_accessorio) REFERENCES accessori (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (fk_ordine_accessorio) REFERENCES ordini_accessorio (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
