DROP TABLE ANIMAL;
CREATE TABLE "ANIMAL" (
                          "desertionNo"	VARCHAR2(15)		NOT NULL,
                          "filename"	VARCHAR2(200)		NULL,
                          "happenDt"	DATE		NULL,
                          "happenPlace"	VARCHAR2(200)		NULL,
                          "kindCd"	VARCHAR2(50)		NULL,
                          "colorCd"	VARCHAR2(40)		NULL,
                          "age"	VARCHAR2(40)	NULL,
                          "weight"	VARCHAR2(40)		NULL,
                          "noticeNo"	VARCHAR2(40)		NULL,
                          "noticeSdt"	DATE		NULL,
                          "noticeEdt"	DATE		NULL,
                          "popfile"	VARCHAR2(200)		NULL,
                          "processState"	VARCHAR2(30)		NULL,
                          "sexCd"	VARCHAR2(1)		NULL,
                          "neuterYn"	VARCHAR2(1)		NULL,
                          "specialMark"	VARCHAR2(200)		NULL,
                          "careNm"	VARCHAR2(50)		NULL,
                          "careTel"	VARCHAR2(30)		NULL,
                          "careAddr"	VARCHAR2(200)		NULL,
                          "orgNm"	VARCHAR2(60)		NULL,
                          "chargeNm"	VARCHAR2(40)		NULL,
                          "officetel"	VARCHAR2(30)		NULL
);
CREATE INDEX idx_sdt on ANIMAL("noticeSdt");
COMMENT ON COLUMN "ANIMAL"."desertionNo" IS '유기번호';
COMMENT ON COLUMN "ANIMAL"."filename" IS '섬네일이미지';
COMMENT ON COLUMN "ANIMAL"."happenDt" IS '접수일';
COMMENT ON COLUMN "ANIMAL"."happenPlace" IS '발견장소';
COMMENT ON COLUMN "ANIMAL"."kindCd" IS '품종';
COMMENT ON COLUMN "ANIMAL"."colorCd" IS '색상';
COMMENT ON COLUMN "ANIMAL"."age" IS '나이';
COMMENT ON COLUMN "ANIMAL"."weight" IS '체중';
COMMENT ON COLUMN "ANIMAL"."noticeNo" IS '공고번호';
COMMENT ON COLUMN "ANIMAL"."noticeSdt" IS '공고시작';
COMMENT ON COLUMN "ANIMAL"."noticeEdt" IS '공고종료';
COMMENT ON COLUMN "ANIMAL"."popfile" IS 'Image';
COMMENT ON COLUMN "ANIMAL"."processState" IS '상태';
COMMENT ON COLUMN "ANIMAL"."sexCd" IS '성별';
COMMENT ON COLUMN "ANIMAL"."neuterYn" IS '중성화여부';
COMMENT ON COLUMN "ANIMAL"."specialMark" IS '특징';
COMMENT ON COLUMN "ANIMAL"."careNm" IS '보호소이름';
COMMENT ON COLUMN "ANIMAL"."careTel" IS '보호소전화번호';
COMMENT ON COLUMN "ANIMAL"."careAddr" IS '보호장소';
COMMENT ON COLUMN "ANIMAL"."orgNm" IS '관할기관';
COMMENT ON COLUMN "ANIMAL"."chargeNm" IS '담당자';
COMMENT ON COLUMN "ANIMAL"."officetel" IS '담당자연락처';

commit;
--------------------------------------------------------
--  파일이 생성됨 - 목요일-11월-10-2022
--------------------------------------------------------
-- REM INSERTING into NEWSOULMATE.ANIMAL SET DEFINE OFF;
