﻿DROP TABLE NewSoulmate.VOLUNTEER CASCADE CONSTRAINTS PURGE ;
DROP TABLE NewSoulmate.BREED CASCADE CONSTRAINTS PURGE ;
DROP TABLE NewSoulmate.SHELTER CASCADE CONSTRAINTS PURGE ;
DROP TABLE "GRADE_UP" CASCADE CONSTRAINTS PURGE;
DROP TABLE NewSoulmate.MEMBER CASCADE CONSTRAINTS PURGE ;
DROP TABLE NEWSOULMATE.MESSAGE CASCADE CONSTRAINTS PURGE ;
DROP TABLE NewSoulmate.SUBSCRIPTION CASCADE CONSTRAINTS PURGE ;
DROP TABLE NewSoulmate."REPLY" CASCADE CONSTRAINTS PURGE ;
DROP TABLE NewSoulmate."CONFIRM" CASCADE CONSTRAINTS PURGE ;
DROP TABLE NewSoulmate.SUPPORT CASCADE CONSTRAINTS PURGE ;
DROP TABLE NewSoulmate.TRANSFER CASCADE CONSTRAINTS PURGE ;
DROP TABLE NewSoulmate.BOARD CASCADE CONSTRAINTS PURGE ;
DROP TABLE NewSoulmate.BOARD_TYPE CASCADE CONSTRAINTS PURGE ;
DROP TABLE NewSoulmate.ATTACHMENT CASCADE CONSTRAINTS PURGE ;
DROP TABLE NewSoulmate.CARD CASCADE CONSTRAINTS PURGE ;
DROP TABLE NewSoulmate.CITY CASCADE CONSTRAINTS PURGE ;
DROP TABLE NewSoulmate.VILLAGE CASCADE CONSTRAINTS PURGE ;
DROP TABLE NewSoulmate.CATEGORY CASCADE CONSTRAINTS PURGE ;
DROP TABLE ANIMAL;
DROP SEQUENCE SQ_BOARD_PK;
DROP SEQUENCE SQ_CARD_PK;
DROP SEQUENCE SQ_COMMENT_PK;
DROP SEQUENCE SQ_MEMBER_PK;
DROP SEQUENCE SQ_VOLUNTEER_PK;
DROP SEQUENCE SQ_ATTACHMENT_PK;


create table ANIMAL
(
    "desertionNo"  number,
    "filename"     VARCHAR2(200),
    "happenDt"     DATE,
    "happenPlace"  VARCHAR2(200),
    "kindCd"       VARCHAR2(50),
    "colorCd"      VARCHAR2(40),
    "age"          VARCHAR2(40),
    "weight"       VARCHAR2(40),
    "noticeNo"     VARCHAR2(40),
    "noticeSdt"    DATE,
    "noticeEdt"    DATE,
    "popfile"      VARCHAR2(200),
    "processState" VARCHAR2(30),
    "sexCd"        VARCHAR2(1),
    "neuterYn"     VARCHAR2(1),
    "specialMark"  VARCHAR2(200),
    "careNm"       VARCHAR2(50),
    "careTel"      VARCHAR2(30),
    "careAddr"     VARCHAR2(200),
    "orgNm"        VARCHAR2(60),
    "chargeNm"     VARCHAR2(40),
    "officetel"    VARCHAR2(30)
);

comment on column ANIMAL."desertionNo" is '유기번호';
comment on column ANIMAL."filename" is '섬네일이미지';
comment on column ANIMAL."happenDt" is '접수일';
comment on column ANIMAL."happenPlace" is '발견장소';
comment on column ANIMAL."kindCd" is '품종';
comment on column ANIMAL."colorCd" is '색상';
comment on column ANIMAL."age" is '나이';
comment on column ANIMAL."weight" is '체중';
comment on column ANIMAL."noticeNo" is '공고번호';
comment on column ANIMAL."noticeSdt" is '공고시작';
comment on column ANIMAL."noticeEdt" is '공고종료';
comment on column ANIMAL."popfile" is 'Image';
comment on column ANIMAL."processState" is '상태';
comment on column ANIMAL."sexCd" is '성별';
comment on column ANIMAL."neuterYn" is '중성화여부';
comment on column ANIMAL."specialMark" is '특징';
comment on column ANIMAL."careNm" is '보호소이름';
comment on column ANIMAL."careTel" is '보호소전화번호';
comment on column ANIMAL."careAddr" is '보호장소';
comment on column ANIMAL."orgNm" is '관할기관';
comment on column ANIMAL."chargeNm" is '담당자';
comment on column ANIMAL."officetel" is '담당자연락처';
CREATE TABLE "MEMBER" (
	"MEMBER_NO"	NUMBER
	    CONSTRAINT PK_MEMBER PRIMARY KEY,
	"MEMBER_ID"	VARCHAR2(30)
        CONSTRAINT MEM_ID_NN NOT NULL
	    CONSTRAINT MEM_ID_UQ UNIQUE,
	"MEMBER_PWD"	VARCHAR2(30)
        CONSTRAINT MEM_PWD_NN NOT NULL,
	"MEMBER_NAME"	VARCHAR2(200)
        CONSTRAINT MEM_NM_NN NOT NULL,
	"PHONE"	VARCHAR2(20)
        CONSTRAINT MEM_PH_NN NOT NULL,
	"EMAIL"	VARCHAR2(30)
        CONSTRAINT MEM_EM_NN NOT NULL,
	"NICKNAME"	VARCHAR2(50)
        CONSTRAINT MEM_NIK_NN NOT NULL
	    CONSTRAINT MEM_NIK_UQ UNIQUE,
	MEMBER_GRADE	NUMBER
	    CONSTRAINT MEM_GRD_CK CHECK(MEMBER_GRADE in(0,1,2))
        CONSTRAINT MEM_GRD_NN NOT NULL,
	"MEMBER_STATUS"	VARCHAR2(1)	DEFAULT 'Y'
        CONSTRAINT MEM_STU_CK CHECK(MEMBER_STATUS in('Y','N')),
	"SHELTER_NO"	NUMBER	DEFAULT NULL,
	RESENT_CONNECTION DATE DEFAULT SYSDATE,
	ENROLL_DATE DATE DEFAULT SYSDATE

);

CREATE TABLE "SHELTER" (
	"SHELTER_NO"	NUMBER
	        CONSTRAINT PK_SHELTER PRIMARY KEY,
	"SHELTER_NAME"	VARCHAR2(50)
	        CONSTRAINT ST_NM_NN NOT NULL ,
	"SHELTER_EMAIL"	VARCHAR2(30),
	"SHELTER_ADDRESS"	VARCHAR2(200),
    "SHELTER_TEL"	VARCHAR2(20),
	"SHELTER_LANDLINE"	VARCHAR2(20),
	"COMPANY_NUM"	VARCHAR2(20),
	"TRANSFER_ACCOUNT" VARCHAR2(20),
	"CITY_NO" NUMBER,
	"VILLAGE_NO" NUMBER
);


CREATE TABLE "CITY" (
    "CITY_NO"	NUMBER
        CONSTRAINT PK_CITY PRIMARY KEY ,
    "CITY_NAME"	VARCHAR2(40)
        CONSTRAINT CT_CN_NN NOT NULL
);

CREATE TABLE "VILLAGE" (
    "VILLAGE_NO"	NUMBER
        CONSTRAINT PK_VILLAGE PRIMARY KEY ,
    "CITY_NO"	NUMBER
        CONSTRAINT VG_CN_NN NOT NULL,
    "VILLAGE_NAME"	VARCHAR2(40)
        CONSTRAINT VG_VNM_NN NOT NULL
);


CREATE TABLE "VOLUNTEER" (
	"VOLUNTEER_NO"	NUMBER
	    CONSTRAINT PK_VOLUNTEER PRIMARY KEY,
	"SHELTER_NO"	NUMBER,
	"MEMBER_NO"	NUMBER,
	"START_DATE" DATE
	    CONSTRAINT VL_SD_NN NOT NULL ,
	"APPLY_DATE" DATE
	    CONSTRAINT VL_AD_NN NOT NULL ,
	"TEL_NUMBER" VARCHAR2(20)
	    CONSTRAINT VL_TN_NN NOT NULL ,
	"NAME"	VARCHAR2(20)
	    CONSTRAINT VL_NM_NN NOT NULL
);

CREATE TABLE "BREED" (
	"BREED_NO"	NUMBER
	    CONSTRAINT PK_BREED PRIMARY KEY ,
	"BREED_NAME"	VARCHAR2(100),
	"SPECIES_NO"	NUMBER
);

CREATE TABLE "BOARD" (
	"BOARD_NO"	NUMBER
        CONSTRAINT PK_BOARD PRIMARY KEY,
	"MEMBER_NO"	NUMBER
	    CONSTRAINT BD_MN_NN NOT NULL,
	"TYPE_NO"	NUMBER
	    CONSTRAINT BD_TN_NN NOT NULL,
	"BOARD_TITLE"	VARCHAR2(100)
	    CONSTRAINT BD_BTT_NN NOT NULL,
	"BOARD_CONTENT"	VARCHAR2(3000)
	    CONSTRAINT BD_BC_NN NOT NULL,
	"BOARD_STATUS" VARCHAR2(1)
	    CONSTRAINT BD_BS_CK CHECK (BOARD_STATUS in('Y','N'))
	    CONSTRAINT BD_BS_NN NOT NULL,
	"FILE_COUNT"	NUMBER,
	"READ_COUNT"	NUMBER,
	"CREATE_DATE"	DATE DEFAULT SYSDATE,
	"ISSUE_DATE" DATE DEFAULT NULL,
	"CATEGORY_NO"	NUMBER
);

CREATE TABLE "SUPPORT" (
	"SUPPORT_NO"	NUMBER
        CONSTRAINT PK_SUPPORT PRIMARY KEY ,
	"SHELTER_NO"	NUMBER
	    CONSTRAINT SP_SN_NN	NOT NULL,
    "MEMBER_NO"	NUMBER
        CONSTRAINT SP_MN_NN NOT NULL,
	"TRANSFER_NO"	NUMBER,
	"CARD_NO"	NUMBER,
	"PAYMENT"	VARCHAR2(100),
	"PRICE"	NUMBER
	    CONSTRAINT SP_PR_NN NOT NULL,
	"PAY_TIME"	DATE DEFAULT SYSDATE,
	"DP_WD" NUMBER CHECK(DP_WD IN('DP','WD')),
	"WD_STATUS" VARCHAR2(1) CHECK ( WD_STATUS IN('Y','N') )
);


CREATE TABLE "TRANSFER" (
	"TRANSFER_NO"	NUMBER
	    CONSTRAINT PK_TRANSFER PRIMARY KEY ,
	"BANK"	VARCHAR2(100)
	    CONSTRAINT TS_BK_NN NOT NULL,
	"NAME"	VARCHAR2(100)
	    CONSTRAINT TS_NM_NN NOT NULL,
	"ACCOUNT_NUMBER"	VARCHAR2(100)
	    CONSTRAINT TS_AN_NN NOT NULL
);

CREATE TABLE "CARD" (
	"CARD_NO"	NUMBER
	    CONSTRAINT PK_CARD PRIMARY KEY ,
	"COMPANY"	VARCHAR2(100)
	    CONSTRAINT CD_CP_NN NOT NULL,
	"CARD_NUM"	VARCHAR2(100)
	    CONSTRAINT CD_CN_NN NOT NULL,
	"VALIDITY"	DATE
	    CONSTRAINT CD_VD_NN NOT NULL
);

--게시판속성--
CREATE TABLE "BOARD_TYPE" (
	"TYPE_NO" NUMBER	CONSTRAINT BT_TC_PK PRIMARY KEY ,
	"BOARD_NAME" VARCHAR2(30)
);

--인증서--
CREATE TABLE "CONFIRM" (
	"CONFIRM_NO" NUMBER
	    CONSTRAINT PK_CONFIRM PRIMARY KEY,
	"MEMBER_NO"	NUMBER
	    CONSTRAINT CF_MN_NN NOT NULL ,
	"CONFIRM_TIME"	DATE DEFAULT SYSDATE
        CONSTRAINT CF_CT_NN NOT NULL,
	"CONFIRM_CODE"	VARCHAR2(20)
        CONSTRAINT CF_CC_NN NOT NULL
);

--입양신청서---
CREATE TABLE "SUBSCRIPTION" (
	"SUB_NO" NUMBER
	    CONSTRAINT PK_SUBSCRIPTION PRIMARY KEY,
	"MEMBER_NO"	NUMBER
        CONSTRAINT SUB_MN_NN NOT NULL ,
	"SHELTER_NO" NUMBER,
	"ANIMAL_ID"	VARCHAR2(100),
	"TEL_NUM"	VARCHAR2(15)
	    CONSTRAINT SUB_TN_NN NOT NULL,
	"NAME"	VARCHAR2(15)
	    CONSTRAINT SUB_NM_NN	NOT NULL,
	"GENDER" CHAR(1)
	    CONSTRAINT SUB_GD_CK CHECK ( GENDER in ('F','M')),
	"ADOPT_REASON"	VARCHAR2(1000),
	"FAMILY_AGREEMENT"	VARCHAR2(1000),
	"WHEN_SICK"	VARCHAR2(1000),
	"BIG_DUTY"	VARCHAR2(1000),
    "WISH_DATE"	DATE  CONSTRAINT SUB_WD_NN NOT NULL,
	"SUB_READ"	VARCHAR2(1)
	    CONSTRAINT SUB_RD_CK CHECK ( SUB_READ in ('Y','N')),
	"SUB_DATE"	DATE
	    DEFAULT SYSDATE
);
--첨부파일--
CREATE TABLE "ATTACHMENT" (
	"FILE_NO"	NUMBER
        CONSTRAINT PK_ATTACHMENT PRIMARY KEY ,
	"REPLY_NO"	NUMBER	DEFAULT 0,
	"BOARD_NO"	NUMBER	DEFAULT 0,
	"ORIGIN_NAME"	VARCHAR2(250)
	    CONSTRAINT AT_ON_NN	NOT NULL,
	"CHANGE_NAME"	VARCHAR2(250)
	    CONSTRAINT AT_CN_NN		NOT NULL,
	"FILE_PATH"	VARCHAR2(1000)
	    CONSTRAINT AT_FP_NN NOT NULL ,
	"UPLOAD_DATE"	DATE	DEFAULT SYSDATE,
	"STATUS"	VARCHAR2(1) DEFAULT 'Y'
        CONSTRAINT AT_ST_CK CHECK ( STATUS in('Y','N'))
);
--댓글--
CREATE TABLE "REPLY" (
	"REPLY_NO"	NUMBER
        CONSTRAINT PK_COMMENT PRIMARY KEY ,
	"BOARD_NO"	NUMBER,
	"NOTICE_NO" NUMBER,
	"MEMBER_NO"	NUMBER
	    CONSTRAINT CM_MN_NN NOT NULL,
	"REPLY_CONTENT" VARCHAR2(400)
	    CONSTRAINT CM_CT_NN NOT NULL,
	"REPLY_STATUS" VARCHAR2(1)
	    CONSTRAINT CM_CS_CK CHECK (REPLY_STATUS IN('Y','N')),
	"REPLY_TYPE"	NUMBER	DEFAULT 0,
	"REPLY_DATE" DATE DEFAULT SYSDATE

);
--문자--
CREATE TABLE "MESSAGE" (
	"MESSAGE_NO" NUMBER
	    CONSTRAINT "PK_MESSAGE" PRIMARY KEY ,
	"MEMBER_NO"	NUMBER,
	"SHELTER_NO" NUMBER,
	"MESSAGE_DATE"	DATE	DEFAULT SYSDATE,
	"TEL_NUM"	VARCHAR2(20)
	    CONSTRAINT MS_TN_NN NOT NULL,
	"MESSAGE_CONTENT" VARCHAR2(1000)
	    CONSTRAINT MS_MC_NN NOT NULL
);
CREATE TABLE CATEGORY(
    CATEGORY_NO NUMBER
        CONSTRAINT PK_REPORT PRIMARY KEY,
    CATEGORY_NAME VARCHAR2(40),
    TYPE_NO NUMBER

);



CREATE TABLE "GRADE_UP" (
    "GRADE_NO"	NUMBER CONSTRAINT "PK_GRADE_UP" PRIMARY KEY	,
    "MEMBER_NO"	NUMBER	CONSTRAINT "GU_MN_NN" NOT NULL,
    "SHELTER_NO"	NUMBER		CONSTRAINT "GU_SN_NN" NOT NULL,
    "FILE_PATH"	VARCHAR(200)	CONSTRAINT "GU_FP_NN"	NOT NULL,
    "SHELTER_TEL"	VARCHAR2(20) CONSTRAINT "GU_ST_NN"	NOT NULL,
    "SHELTER_LANDLINE"	VARCHAR2(20) CONSTRAINT "GU_SL_NN" NOT	NULL,
    "SHELTER_COMP_NO"	VARCHAR2(30)  CONSTRAINT "GU_SCN_NN" 	NOT	NULL,
    "GRADE_STATUS"	VARCHAR2(1)		 CONSTRAINT "GU_GS_NN" NOT NULL,
    "SHELTER_ADDRESS"	VARCHAR2(100)	CONSTRAINT "GU_SA_NN"	NULL
);





--------------------------------------------------------
--------------------COMMENT-----------------------------
--------------------------------------------------------
--인증
COMMENT ON COLUMN "CONFIRM"."CONFIRM_NO" IS '인증번호';
COMMENT ON COLUMN "CONFIRM"."MEMBER_NO" IS '회원번호';
COMMENT ON COLUMN "CONFIRM"."CONFIRM_TIME" IS '인증시간';
COMMENT ON COLUMN "CONFIRM"."CONFIRM_CODE" IS '인증키';
--게시판속성
COMMENT ON COLUMN "BOARD_TYPE"."TYPE_NO" IS '게시판속성키';
COMMENT ON COLUMN "BOARD_TYPE"."BOARD_NAME" IS '속성명';

COMMENT ON COLUMN CATEGORY.CATEGORY_NO IS '신고번호';
COMMENT ON COLUMN CATEGORY.CATEGORY_NAME IS '신고분류';
COMMENT ON COLUMN CATEGORY.TYPE_NO IS '게시판속성';

--신청서
COMMENT ON COLUMN "SUBSCRIPTION"."SUB_NO" IS '신청서번호';
COMMENT ON COLUMN "SUBSCRIPTION"."MEMBER_NO" IS '회원번호';
COMMENT ON COLUMN "SUBSCRIPTION"."SHELTER_NO" IS '보호소 ID';
COMMENT ON COLUMN "SUBSCRIPTION"."ANIMAL_ID" IS '공고번호';
COMMENT ON COLUMN "SUBSCRIPTION"."TEL_NUM" IS '전화번호';
COMMENT ON COLUMN "SUBSCRIPTION"."NAME" IS '이름';
COMMENT ON COLUMN "SUBSCRIPTION"."GENDER" IS '성별';
COMMENT ON COLUMN "SUBSCRIPTION"."ADOPT_REASON" IS '입양사유';
COMMENT ON COLUMN "SUBSCRIPTION"."FAMILY_AGREEMENT" IS '가족찬반';
COMMENT ON COLUMN "SUBSCRIPTION"."WHEN_SICK" IS '아플경우';
COMMENT ON COLUMN "SUBSCRIPTION"."BIG_DUTY" IS '평생책임';
COMMENT ON COLUMN "SUBSCRIPTION"."SUB_DATE" IS '신청날짜';
COMMENT ON COLUMN "SUBSCRIPTION"."WISH_DATE" IS '방문희망일';
COMMENT ON COLUMN "SUBSCRIPTION"."SUB_READ" IS '읽음';

--댓글
COMMENT ON COLUMN REPLY."REPLY_NO" IS '댓글번호';
COMMENT ON COLUMN REPLY."BOARD_NO" IS '글번호';
COMMENT ON COLUMN REPLY."NOTICE_NO" IS '공고번호';
COMMENT ON COLUMN REPLY."REPLY_CONTENT" IS '댓글내용';
COMMENT ON COLUMN REPLY."REPLY_STATUS" IS '삭제여부';
COMMENT ON COLUMN REPLY."REPLY_TYPE" IS '댓글속성0일반/1사진';
COMMENT ON COLUMN REPLY."REPLY_DATE" IS '댓글작성일';

--파일
COMMENT ON COLUMN "ATTACHMENT"."FILE_NO" IS '파일 번호';
COMMENT ON COLUMN "ATTACHMENT"."REPLY_NO" IS '참조댓글번호';
COMMENT ON COLUMN "ATTACHMENT"."BOARD_NO" IS '참조글번호';
COMMENT ON COLUMN "ATTACHMENT"."ORIGIN_NAME" IS '원본이름';
COMMENT ON COLUMN "ATTACHMENT"."CHANGE_NAME" IS '수정명';
COMMENT ON COLUMN "ATTACHMENT"."FILE_PATH" IS '파일경로';
COMMENT ON COLUMN "ATTACHMENT"."UPLOAD_DATE" IS '파일작성시간';
COMMENT ON COLUMN "ATTACHMENT"."STATUS" IS '파일상태';

--문자
COMMENT ON COLUMN "MESSAGE"."MESSAGE_NO" IS '문자번호';
COMMENT ON COLUMN "MESSAGE"."MEMBER_NO" IS '회원번호';
COMMENT ON COLUMN "MESSAGE"."SHELTER_NO" IS '보호소 번호';
COMMENT ON COLUMN "MESSAGE"."MESSAGE_DATE" IS '보낸시간';
COMMENT ON COLUMN "MESSAGE"."TEL_NUM" IS '전화번호';
COMMENT ON COLUMN "MESSAGE"."MESSAGE_CONTENT" IS '문자내용';


--회원
COMMENT ON COLUMN "MEMBER"."MEMBER_NO" IS '회원번호';
COMMENT ON COLUMN "MEMBER"."MEMBER_ID" IS '회원 ID';
COMMENT ON COLUMN "MEMBER"."MEMBER_PWD" IS '회원비밀번호';
COMMENT ON COLUMN "MEMBER"."MEMBER_NAME" IS '이름';
COMMENT ON COLUMN "MEMBER"."PHONE" IS '전화번호';
COMMENT ON COLUMN "MEMBER"."EMAIL" IS '이메일';
COMMENT ON COLUMN "MEMBER"."NICKNAME" IS '닉네임';
COMMENT ON COLUMN "MEMBER".MEMBER_GRADE IS '유저등급';
COMMENT ON COLUMN "MEMBER"."MEMBER_STATUS" IS '상태';
COMMENT ON COLUMN "MEMBER"."SHELTER_NO" IS '보호소번호';
COMMENT ON COLUMN "MEMBER".RESENT_CONNECTION IS '최근접속시간';
COMMENT ON COLUMN "MEMBER".ENROLL_DATE IS '가입일';
--보호소
COMMENT ON COLUMN "SHELTER"."SHELTER_NAME" IS '보호소이름';
COMMENT ON COLUMN "SHELTER"."SHELTER_TEL" IS '무선전화';
COMMENT ON COLUMN "SHELTER"."SHELTER_EMAIL" IS '이메일';
COMMENT ON COLUMN "SHELTER"."SHELTER_ADDRESS" IS '주소';
COMMENT ON COLUMN "SHELTER"."SHELTER_LANDLINE" IS '유선전화';
COMMENT ON COLUMN "SHELTER"."COMPANY_NUM" IS '사업자등록번호';
COMMENT ON COLUMN "SHELTER"."TRANSFER_ACCOUNT" IS '계좌번호';
--봉사
COMMENT ON COLUMN "VOLUNTEER"."VOLUNTEER_NO" IS '봉사번호';
COMMENT ON COLUMN "VOLUNTEER"."SHELTER_NO" IS '보호소번호';
COMMENT ON COLUMN "VOLUNTEER"."MEMBER_NO" IS '회원번호';
COMMENT ON COLUMN "VOLUNTEER"."START_DATE" IS '시작 일자';
COMMENT ON COLUMN "VOLUNTEER"."APPLY_DATE" IS '신청 일자';
COMMENT ON COLUMN "VOLUNTEER"."TEL_NUMBER" IS '전화번호';
COMMENT ON COLUMN "VOLUNTEER"."NAME" IS '이름';

--게시글
COMMENT ON COLUMN "BOARD"."BOARD_NO" IS '글 번호';
COMMENT ON COLUMN "BOARD"."MEMBER_NO" IS '회원번호';
COMMENT ON COLUMN "BOARD"."TYPE_NO" IS '게시판속성';
COMMENT ON COLUMN "BOARD"."BOARD_TITLE" IS '제목';
COMMENT ON COLUMN "BOARD"."BOARD_CONTENT" IS '내용';
COMMENT ON COLUMN "BOARD"."FILE_COUNT" IS '업로드파일';
COMMENT ON COLUMN "BOARD"."READ_COUNT" IS '조회수';
COMMENT ON COLUMN "BOARD"."CREATE_DATE" IS '작성일';
COMMENT ON COLUMN "BOARD"."ISSUE_DATE" IS '입양/봉사일';
COMMENT ON COLUMN "BOARD"."CATEGORY_NO" IS '카테고리';

COMMENT ON COLUMN "TRANSFER"."TRANSFER_NO" IS '이체 후원 번호';
COMMENT ON COLUMN "TRANSFER"."BANK" IS '은행';
COMMENT ON COLUMN "TRANSFER"."NAME" IS '예금주명';
COMMENT ON COLUMN "TRANSFER"."ACCOUNT_NUMBER" IS '계좌번호';

COMMENT ON COLUMN "CARD"."CARD_NO" IS '카드 후원 번호';
COMMENT ON COLUMN "CARD"."COMPANY" IS '카드사';
COMMENT ON COLUMN "CARD"."CARD_NUM" IS '카드 번호';
COMMENT ON COLUMN "CARD"."VALIDITY" IS '카드유효기간';


COMMENT ON COLUMN "BREED"."BREED_NO" IS '품종번호';
COMMENT ON COLUMN "BREED"."BREED_NAME" IS '품종명';
COMMENT ON COLUMN "BREED"."SPECIES_NO" IS '종명';

comment on column ANIMAL."desertionNo" is '유기번호';
comment on column ANIMAL."filename" is '섬네일이미지';
comment on column ANIMAL."happenDt" is '접수일';
comment on column ANIMAL."happenPlace" is '발견장소';
comment on column ANIMAL."kindCd" is '품종';
comment on column ANIMAL."colorCd" is '색상';
comment on column ANIMAL."age" is '나이';
comment on column ANIMAL."weight" is '체중';
comment on column ANIMAL."noticeNo" is '공고번호';
comment on column ANIMAL."noticeSdt" is '공고시작';
comment on column ANIMAL."noticeEdt" is '공고종료';
comment on column ANIMAL."popfile" is 'Image';
comment on column ANIMAL."processState" is '상태';
comment on column ANIMAL."sexCd" is '성별';
comment on column ANIMAL."neuterYn" is '중성화여부';
comment on column ANIMAL."specialMark" is '특징';
comment on column ANIMAL."careNm" is '보호소이름';
comment on column ANIMAL."careTel" is '보호소전화번호';
comment on column ANIMAL."careAddr" is '보호장소';
comment on column ANIMAL."orgNm" is '관할기관';
comment on column ANIMAL."chargeNm" is '담당자';
comment on column ANIMAL."officetel" is '담당자연락처';

--보호소관계자신청
COMMENT ON COLUMN "GRADE_UP"."GRADE_NO" IS '신청번호';
COMMENT ON COLUMN "GRADE_UP"."MEMBER_NO" IS '회원번호';
COMMENT ON COLUMN "GRADE_UP"."SHELTER_NO" IS '보호소 번호';
COMMENT ON COLUMN "GRADE_UP"."FILE_PATH" IS '첨부파일경로';
COMMENT ON COLUMN "GRADE_UP"."SHELTER_TEL" IS '보호소 무선번호';
COMMENT ON COLUMN "GRADE_UP"."SHELTER_LANDLINE" IS '보호소 유선번호';
COMMENT ON COLUMN "GRADE_UP"."SHELTER_COMP_NO" IS '보호소 사업자 번호';
COMMENT ON COLUMN "GRADE_UP"."GRADE_STATUS" IS '신청서상태';



COMMENT ON COLUMN "CITY"."CITY_NO" IS '시도코드';
COMMENT ON COLUMN "CITY"."CITY_NAME" IS '시도명';
COMMENT ON COLUMN "VILLAGE"."VILLAGE_NO" IS '시군구번호';
COMMENT ON COLUMN "VILLAGE"."CITY_NO" IS '시도코드';
COMMENT ON COLUMN "VILLAGE"."VILLAGE_NAME" IS '시군구명';


COMMENT ON COLUMN "SUPPORT"."SUPPORT_NO" IS '후원번호';
COMMENT ON COLUMN "SUPPORT"."SHELTER_NO" IS '보호소번호';
COMMENT ON COLUMN "SUPPORT"."TRANSFER_NO" IS '이체 후원 번호';
COMMENT ON COLUMN "SUPPORT"."CARD_NO" IS '카드 후원 번호';
COMMENT ON COLUMN "SUPPORT"."MEMBER_NO" IS '회원번호';
COMMENT ON COLUMN "SUPPORT"."PAYMENT" IS '결제수단';
COMMENT ON COLUMN "SUPPORT"."PRICE" IS '결제 금액';
COMMENT ON COLUMN "SUPPORT"."DP_WD" IS '입출금속성(WD(출금)/DP(입금))';
COMMENT ON COLUMN "SUPPORT"."WD_STATUS" IS '출금여부';

--ForeignKey

ALTER TABLE VOLUNTEER ADD CONSTRAINT VL_SN_FK FOREIGN KEY (SHELTER_NO)
    REFERENCES SHELTER(SHELTER_NO) ON DELETE SET NULL;
ALTER TABLE VOLUNTEER ADD CONSTRAINT VL_MN_FK FOREIGN KEY (MEMBER_NO)
    REFERENCES MEMBER(MEMBER_NO) ON DELETE SET NULL;

ALTER TABLE "BOARD" ADD CONSTRAINT BD_MN_FK FOREIGN KEY (MEMBER_NO)
    REFERENCES "MEMBER" ("MEMBER_NO") on delete SET NULL;
ALTER TABLE "BOARD" ADD CONSTRAINT BD_BT_FK FOREIGN KEY ("TYPE_NO")
    REFERENCES "BOARD_TYPE" ("TYPE_NO");
ALTER TABLE "BOARD" ADD CONSTRAINT BD_RN_FK FOREIGN KEY ("CATEGORY_NO")
    REFERENCES CATEGORY (CATEGORY_NO);

ALTER TABLE "SUPPORT" ADD CONSTRAINT SP_SN_FK FOREIGN KEY ("SHELTER_NO")
    REFERENCES "SHELTER" ("SHELTER_NO");
ALTER TABLE "SUPPORT" ADD CONSTRAINT "SP_TN_FK" FOREIGN KEY ("TRANSFER_NO")
    REFERENCES "TRANSFER" ("TRANSFER_NO");
ALTER TABLE "SUPPORT" ADD CONSTRAINT "SP_CN_FK" FOREIGN KEY ("CARD_NO")
    REFERENCES "CARD" ("CARD_NO");
ALTER TABLE "SUPPORT" ADD CONSTRAINT "SP_MN_FK" FOREIGN KEY ("MEMBER_NO")
    REFERENCES "MEMBER" ("MEMBER_NO");

ALTER TABLE "CONFIRM" ADD CONSTRAINT "CF_MN_FK" FOREIGN KEY ("MEMBER_NO")
    REFERENCES "MEMBER" ("MEMBER_NO");

ALTER TABLE "SUBSCRIPTION" ADD CONSTRAINT "SUB_MN_FK" FOREIGN KEY ("MEMBER_NO")
    REFERENCES "MEMBER" ("MEMBER_NO");
ALTER TABLE "SUBSCRIPTION" ADD CONSTRAINT "SUB_SN_FK" FOREIGN KEY ("SHELTER_NO")
    REFERENCES "SHELTER" ("SHELTER_NO");

ALTER TABLE REPLY ADD CONSTRAINT "CM_BN_FK" FOREIGN KEY ("BOARD_NO")
    REFERENCES "BOARD" ("BOARD_NO");
ALTER TABLE REPLY ADD CONSTRAINT "CM_MN_FK" FOREIGN KEY("MEMBER_NO")
    REFERENCES "MEMBER" (MEMBER_NO);

ALTER TABLE "MESSAGE" ADD CONSTRAINT "MS_MN_FK" FOREIGN KEY ("MEMBER_NO")
    REFERENCES "MEMBER" ("MEMBER_NO");
ALTER TABLE "MESSAGE" ADD CONSTRAINT "MS_SN_FK" FOREIGN KEY ("SHELTER_NO")
    REFERENCES "SHELTER" ("SHELTER_NO");

ALTER TABLE "SHELTER" ADD CONSTRAINT "ST_CN_FK" FOREIGN KEY ("CITY_NO")
    REFERENCES "CITY" ("CITY_NO");
ALTER TABLE "SHELTER" ADD CONSTRAINT "ST_VN_FK" FOREIGN KEY ("VILLAGE_NO")
    REFERENCES "VILLAGE" ("VILLAGE_NO");

ALTER TABLE "VILLAGE" ADD CONSTRAINT "VG_CN_FK" FOREIGN KEY ("VILLAGE_NO")
    REFERENCES "VILLAGE" ("VILLAGE_NO");

ALTER TABLE "GRADE_UP" ADD CONSTRAINT "GU_MN_FK" FOREIGN KEY ("MEMBER_NO")
    REFERENCES "MEMBER" ("MEMBER_NO");
ALTER TABLE "GRADE_UP" ADD CONSTRAINT "FK_SHELTER_TO_GRADE_UP_1" FOREIGN KEY ( "SHELTER_NO")
    REFERENCES "SHELTER" ("SHELTER_NO");

-----TRIGGER-----------------------------
--회원탈퇴트리거

CREATE OR REPLACE TRIGGER TR_QUIT_MEMBER
AFTER
UPDATE ON MEMBER
FOR EACH ROW
WHEN (OLD.MEMBER_STATUS = 'Y' AND NEW.MEMBER_STATUS = 'N')
DECLARE
BEGIN
    UPDATE BOARD
        SET BOARD_STATUS='N'
    WHERE MEMBER_NO = :OLD.MEMBER_NO;
    UPDATE REPLY
        SET REPLY_STATUS='N'
    WHERE MEMBER_NO = :OLD.MEMBER_NO;

END;

--글 삭제트리거
CREATE OR REPLACE TRIGGER TR_DELETE_BOARD
    AFTER
    UPDATE ON BOARD
    FOR EACH ROW
    WHEN (OLD.BOARD_STATUS = 'Y' AND NEW.BOARD_STATUS = 'N')
DECLARE
BEGIN
    UPDATE REPLY
    SET REPLY_STATUS='N'
    WHERE BOARD_NO = :OLD.BOARD_NO;
END;
----------------VIEW-----------------



-------------SEQUENCE---------------
CREATE SEQUENCE SQ_VOLUNTEER_PK
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 999
    NOCYCLE
    NOCACHE
    NOORDER;
CREATE SEQUENCE SQ_MEMBER_PK
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 10000
    NOCYCLE
    NOCACHE
    NOORDER;
CREATE SEQUENCE SQ_CARD_PK
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 9999
    NOCYCLE
    NOCACHE
    NOORDER;
CREATE SEQUENCE SQ_BOARD_PK
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    MAXVALUE 9999
    NOCYCLE
    NOCACHE
    NOORDER;
CREATE SEQUENCE SQ_COMMENT_PK
    INCREMENT BY 1
    START WITH 10000
    MINVALUE 1
    MAXVALUE 99999
    NOCYCLE
    NOCACHE
    NOORDER;
CREATE SEQUENCE SQ_ATTACHMENT_PK
    INCREMENT BY 1
    START WITH 10000
    MINVALUE 1
    MAXVALUE 99999
    NOCYCLE
    NOCACHE
    NOORDER;

COMMIT;



