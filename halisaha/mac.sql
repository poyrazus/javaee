CREATE DATABASE HALISAHA CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE USER 'halisaha' IDENTIFIED BY 'halisaha';
GRANT ALL ON halisaha.* TO 'halisaha'@'%' IDENTIFIED BY 'halisaha';
GRANT ALL ON halisaha.* TO 'halisaha'@'localhost' IDENTIFIED BY 'halisaha';
FLUSH PRIVILEGES;

CREATE   TABLE HALISAHA.OYUNCU
        (ID        INTEGER      primary key,
         AD             VARCHAR(30)  NOT NULL,
         EMAIL          VARCHAR(50)           ,
         DAHILI_NO            VARCHAR(4)  ,
         CEP_NO         VARCHAR(11)
         );

CREATE   TABLE HALISAHA.SAHA
        (ID          INTEGER     primary key,
         AD        VARCHAR(30)     NOT NULL,
         ACIKLAMA        VARCHAR(50)     ,
         ADRES        VARCHAR(50)     ,
         TEL          VARCHAR(10)     ,
         MAX_KISI        INTEGER,
         HARITA_LINK  VARCHAR(150)
         );

CREATE   TABLE HALISAHA.MAC
        (ID         INTEGER     primary key,
         TARIH          DATE     ,
         SAAT        VARCHAR(11)     ,
         SAHA_ID INTEGER,
         ASIL_KADRO_SAYISI INTEGER
);

CREATE   TABLE HALISAHA.MAC_KADROSU(
    ID              INTEGER primary key,
    MAC_ID         INTEGER,
    OYUNCU_ID     INTEGER,
    EKLEYEN_ID INTEGER,
    EKLENME_ZAMANI TIMESTAMP);

CREATE   TABLE HALISAHA.OLAY
        (ID         INTEGER     primary key,
         CINS       INTEGER,
         MESAJ      VARCHAR(150),
         EKLENME_ZAMANI TIMESTAMP
);

alter table HALISAHA.MAC_KADROSU
add constraint MAC_KADROSU_FK
foreign key (MAC_ID) references MAC(id);

alter table HALISAHA.MAC_KADROSU
add constraint MAC_KADROSU_FK2
foreign key (OYUNCU_ID) references OYUNCU(ID);

alter table HALISAHA.MAC_KADROSU
add constraint MAC_KADROSU_FK3
foreign key (EKLEYEN_ID) references OYUNCU(ID);

alter table HALISAHA.MAC
add constraint MAC_FK
foreign key (SAHA_ID) references SAHA(ID);


CREATE TABLE ID_SEQUENCE
(
	NAME VARCHAR(255) NOT NULL,
	VALUE BIGINT NOT NULL,
	PRIMARY KEY(NAME)
);


INSERT INTO ID_SEQUENCE VALUES ('OYUNCU', 0);
INSERT INTO ID_SEQUENCE VALUES ('MAC', 0);
INSERT INTO ID_SEQUENCE VALUES ('SAHA', 0);
INSERT INTO ID_SEQUENCE VALUES ('MAC_KADROSU', 0);
INSERT INTO ID_SEQUENCE VALUES ('OLAY', 0);