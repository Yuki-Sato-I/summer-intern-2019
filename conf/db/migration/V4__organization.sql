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