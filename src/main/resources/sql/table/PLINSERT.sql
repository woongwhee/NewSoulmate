
BEGIN
    FOR i in 1..200 LOOP
            insert into board values(SQ_BOARD_PK.nextval,3,2,'테스트용 페이지','<p>ㄹㅇㄴㅇㄴㄹㄹㅇㄴ<img src="/file/resources/board_upfiles/2022/11/25/20221190147.jpg" title="agumon.jpg"> </p>','Y',1,1,TO_DATE('2022-11-25 06:58:03','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2022-11-17','yyyy-MM-dd') ,null);
            insert into ATTACHMENT values (SQ_ATTACHMENT_PK.nextval,0,SQ_BOARD_PK.CURRVAL,'mung.jpg','20221190147.jpg','/file/resources/board_upfiles/2022/11/25',TO_DATE('2022-11-25 06:58:01','YYYY-MM-DD HH24:MI:SS'),'Y');
            insert into board values(SQ_BOARD_PK.nextval,3,1,'테스트용 페이지','<p>ㄹㅇㄴㅇㄴㄹㄹㅇㄴ<img src="/file/resources/board_upfiles/2022/11/25/20221190147.jpg" title="agumon.jpg"> </p>','Y',1,1,TO_DATE('2022-11-25 06:58:03','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2022-11-17','yyyy-MM-dd') ,null);
            insert into ATTACHMENT values (SQ_ATTACHMENT_PK.nextval,0,SQ_BOARD_PK.CURRVAL,'mung.jpg','20221190147.jpg','/file/resources/board_upfiles/2022/11/25',TO_DATE('2022-11-25 06:58:01','YYYY-MM-DD HH24:MI:SS'),'Y');
        END LOOP;
    commit;
END;




