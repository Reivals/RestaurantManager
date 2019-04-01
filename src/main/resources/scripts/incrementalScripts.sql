INSERT INTO dishes(DIS_ID,DIS_NAME, DIS_TYPE) VALUES(1,'Potrawka z kurczka',1);
INSERT INTO dishes(DIS_ID,DIS_NAME, DIS_TYPE) VALUES(2,'Miesny jez',1);
INSERT INTO dishes(DIS_ID,DIS_NAME, DIS_TYPE) VALUES(3,'Mieso z kaczki',2);
INSERT INTO dishes(DIS_ID,DIS_NAME, DIS_TYPE) VALUES(4,'Mieso z jelenia',2);
INSERT INTO dishes(DIS_ID,DIS_NAME, DIS_TYPE) VALUES(5,'Miesa z kurczaka',2);
INSERT INTO dishes(DIS_ID,DIS_NAME, DIS_TYPE) VALUES(6,'Bykowe mieso',2);
INSERT INTO dishes(DIS_ID,DIS_NAME, DIS_TYPE) VALUES(7,'Mieso po tradycyjnemu',1);

INSERT INTO ingredient(ING_IG, ING_CALORIES, ING_NAME) VALUES (1,723,'Mieso');
INSERT INTO ingredient(ING_IG, ING_CALORIES, ING_NAME) VALUES (2,32,'Ciastka');
INSERT INTO ingredient(ING_IG, ING_CALORIES, ING_NAME) VALUES (3,643,'Pietruszka');
INSERT INTO ingredient(ING_IG, ING_CALORIES, ING_NAME) VALUES (4,4321,'Seler');
INSERT INTO ingredient(ING_IG, ING_CALORIES, ING_NAME) VALUES (5,9123,'Mleko');
INSERT INTO ingredient(ING_IG, ING_CALORIES, ING_NAME) VALUES (6,9233,'Wino');
INSERT INTO ingredient(ING_IG, ING_CALORIES, ING_NAME) VALUES (7,7234,'Lycha');
INSERT INTO ingredient(ING_IG, ING_CALORIES, ING_NAME) VALUES (8,723,'Wodka');
INSERT INTO ingredient(ING_IG, ING_CALORIES, ING_NAME) VALUES (9,723,'Woda');
INSERT INTO ingredient(ING_IG, ING_CALORIES, ING_NAME) VALUES (10,23423,'Kabanosy');
INSERT INTO ingredient(ING_IG, ING_CALORIES, ING_NAME) VALUES (11,765,'Marchewka');
INSERT INTO ingredient(ING_IG, ING_CALORIES, ING_NAME) VALUES (12,753,'Jarmuz');

INSERT INTO dish_ingredients(ING_IG, DIS_ID) VALUES(1,1);
INSERT INTO dish_ingredients(ING_IG, DIS_ID) VALUES(1,2);
INSERT INTO dish_ingredients(ING_IG, DIS_ID) VALUES(1,3);
INSERT INTO dish_ingredients(ING_IG, DIS_ID) VALUES(1,4);
INSERT INTO dish_ingredients(ING_IG, DIS_ID) VALUES(1,5);
INSERT INTO dish_ingredients(ING_IG, DIS_ID) VALUES(1,6);