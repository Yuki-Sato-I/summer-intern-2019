-- 組織定義
--------------
CREATE TABLE "organization" (
  "id"              INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  "location_id"     VARCHAR(8)   NOT NULL,
  "en_name"         VARCHAR(255) NOT NULL,
  "kanzi_name"      VARCHAR(255) NOT NULL,
  "phonetic_name"   VARCHAR(255) NOT NULL,
  "address"         VARCHAR(255) NOT NULL,
  "updated_at"      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  "created_at"      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;


-- 組織と施設の関連付け中間テーブル facicilityにorg_idつければいらないが、facilityを変えていいかわからないので
----------------------
CREATE TABLE "organization_facility" (
  "id"                 INT         NOT NULL PRIMARY KEY,
  "organization_id"    INT         NOT NULL,
  "facility_id"        INT         NOT NULL,
  "created_at"         TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;


-- sample
INSERT INTO "organization" ("location_id", "en_name", "kanzi_name", "phonetic_name", "address") VALUES ('22100', 'sosikisample1','組織サンプル1' ,'そしきさんぷる1',  '宮城県石巻市');
INSERT INTO "organization" ("location_id", "en_name", "kanzi_name", "phonetic_name", "address") VALUES ('22100', 'sosikisample2','組織サンプル2' ,'そしきさんぷる2',  '宮城県石巻市');
INSERT INTO "organization" ("location_id", "en_name", "kanzi_name", "phonetic_name", "address") VALUES ('22100', 'sosikisample3','組織サンプル3' ,'そしきさんぷる3',  '宮城県石巻市');
INSERT INTO "organization" ("location_id", "en_name", "kanzi_name", "phonetic_name", "address") VALUES ('22100', 'sosikisample4','組織サンプル4' ,'そしきさんぷる4',  '宮城県石巻市');
INSERT INTO "organization" ("location_id", "en_name", "kanzi_name", "phonetic_name", "address") VALUES ('22100', 'sosikisample5','組織サンプル5' ,'そしきさんぷる5',  '宮城県石巻市');
INSERT INTO "organization" ("location_id", "en_name", "kanzi_name", "phonetic_name", "address") VALUES ('22100', 'sosikisample6','組織サンプル6' ,'そしきさんぷる6',  '宮城県石巻市');
INSERT INTO "organization" ("location_id", "en_name", "kanzi_name", "phonetic_name", "address") VALUES ('22100', 'sosikisample7','組織サンプル7' ,'そしきさんぷる7',  '宮城県石巻市');
INSERT INTO "organization" ("location_id", "en_name", "kanzi_name", "phonetic_name", "address") VALUES ('22100', 'sosikisample8','組織サンプル8' ,'そしきさんぷる8',  '宮城県石巻市');
INSERT INTO "organization" ("location_id", "en_name", "kanzi_name", "phonetic_name", "address") VALUES ('22100', 'sosikisample9','組織サンプル9' ,'そしきさんぷる9',  '宮城県石巻市');
INSERT INTO "organization" ("location_id", "en_name", "kanzi_name", "phonetic_name", "address") VALUES ('22100', 'sosikisample10','組織サンプル10' ,'そしきさんぷる10',  '宮城県石巻市');
INSERT INTO "organization" ("location_id", "en_name", "kanzi_name", "phonetic_name", "address") VALUES ('22100', 'sosikisample11','組織サンプル11' ,'そしきさんぷる11',  '宮城県石巻市');
INSERT INTO "organization" ("location_id", "en_name", "kanzi_name", "phonetic_name", "address") VALUES ('22100', 'sosikisample12','組織サンプル12' ,'そしきさんぷる12',  '宮城県石巻市');
INSERT INTO "organization" ("location_id", "en_name", "kanzi_name", "phonetic_name", "address") VALUES ('22100', 'sosikisample13','組織サンプル13' ,'そしきさんぷる13',  '宮城県石巻市');
