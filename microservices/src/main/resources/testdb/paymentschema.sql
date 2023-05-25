DROP TABLE PAYMENT IF EXISTS;

CREATE TABLE PAYMENT (ID bigint identity primary key, VRIJEME_OCITANJA timestamp with time zone, UREDAJ varchar(255), NAPLATNA_TOCKA varchar(255),
                      KATEGORIJA varchar(255), IDENC integer, REGISTRACIJSKA_OZNAKA varchar(255)
                    );