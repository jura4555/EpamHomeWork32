USE spring_db_agency;

INSERT INTO hotel_type VALUES (DEFAULT,'TOURIST_CLASS');
INSERT INTO hotel_type VALUES (DEFAULT,'STANDART_CLASS');
INSERT INTO hotel_type VALUES (DEFAULT,'COMFORTABLE_CLASS');
INSERT INTO hotel_type VALUES (DEFAULT,'FIRST_CLASS');
INSERT INTO hotel_type VALUES (DEFAULT,'LUXURY');

INSERT INTO tour_type VALUES (DEFAULT,'REST');
INSERT INTO tour_type VALUES (DEFAULT,'EXCURSION');
INSERT INTO tour_type values (DEFAULT,'SHOPPING');

INSERT INTO user_role VALUES (DEFAULT,'USER');
INSERT INTO user_role VALUES (DEFAULT,'MANAGER');
INSERT INTO user_role VALUES (DEFAULT,'ADMIN');

INSERT INTO tour_status VALUES(DEFAULT, 'REGISTERED');
INSERT INTO tour_status VALUES(DEFAULT, 'PAID');
INSERT INTO tour_status VALUES(DEFAULT, 'CANCELED');

INSERT INTO user_status VALUES (0,'ACTIVE');
INSERT INTO user_status VALUES (1,'BLOCKED');





