UPDATE ANIMAL
    SET "happenDt"=SYSDATE-15,
        "noticeSdt"=SYSDATE-14,
        "noticeEdt"=SYSDATE;
commit;
