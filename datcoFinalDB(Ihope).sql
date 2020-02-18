 --=======================================================
--Datco용 계정생성

--1. sys로 계정생성

--2. 권한주기

--3. 접속생성

--4. 테이블 생성입니다.

select table_name, column_name from all_tab_columns;
select * from all_tab_columns where owner='DATCO';

WITH LIST AS
(
    SELECT A.TABLE_NAME,
           A.COLUMN_NAME,
           A.DATA_TYPE,
           A.DATA_LENGTH,
           A.NULLABLE,
           B.COMMENTS
    FROM  all_tab_columns A,
           all_col_comments B
    WHERE  A.OWNER = B.OWNER
    AND    A.TABLE_NAME = B.TABLE_pNAME
    AND    A.COLUMN_NAME = B.COLUMN_NAME
    AND    A.OWNER = 'DATCO'   -- DB명
),
PKLIST AS
(
    SELECT C.TABLE_NAME,
           C.COLUMN_NAME,
           C.POSITION
    FROM USER_CONS_COLUMNS C,
         USER_CONSTRAINTS S
    WHERE C.CONSTRAINT_NAME = S.CONSTRAINT_NAME
    AND S.CONSTRAINT_TYPE = 'P'
)
SELECT L.TABLE_NAME AS "테이블명",
       L.COLUMN_NAME AS "컬럼명",
       L.DATA_TYPE AS "데이터타입",
       L.DATA_LENGTH AS "길이",
       CASE WHEN P.POSITION < 99 THEN 'Y'
            ELSE ' '
       END AS "PK",
       L.NULLABLE AS "Null 여부",
       L.COMMENTS AS "Comments"
FROM LIST L,
     PKLIST P
WHERE L.TABLE_NAME = P.TABLE_NAME(+)
  AND L.COLUMN_NAME = P.COLUMN_NAME(+)
 ORDER BY L.TABLE_NAME,
          NVL(P.POSITION, 99)
;


--=======================================================
--datco계정 생성
create user datco identified by datco
default tablespace users;

grant connect, resource to datco;

--==모든 테이블 드랍문----
DROP TABLE TB_USER CASCADE CONSTRAINTS;
DROP TABLE TB_DEL_USER CASCADE CONSTRAINTS;
DROP TABLE TB_BOARD CASCADE CONSTRAINTS;
DROP TABLE TB_BOARD_REP_LOG CASCADE CONSTRAINTS;
DROP TABLE TB_REMOVED_BOARD CASCADE CONSTRAINTS;
DROP TABLE TB_COMMENT CASCADE CONSTRAINTS;
DROP TABLE TB_REMOVED_COMMENT CASCADE CONSTRAINTS;
DROP TABLE TB_COMMENT_REP_LOG CASCADE CONSTRAINTS;
DROP TABLE TB_SELECTED_CMT CASCADE CONSTRAINTS;
DROP TABLE TB_RECRUIT CASCADE CONSTRAINTS;
DROP TABLE TB_ENTERPRISE CASCADE CONSTRAINTS;
DROP TABLE TB_BLACKLIST CASCADE CONSTRAINTS;
DROP TABLE TB_SCRAP CASCADE CONSTRAINTS;
DROP TABLE TB_MESSAGE CASCADE CONSTRAINTS;
DROP TABLE TB_POINT_LOG CASCADE CONSTRAINTS;
DROP TABLE TB_POINT CASCADE CONSTRAINTS;

commit;
--==시퀀스 드랍문
drop sequence Tb_comment_SEQ;
drop sequence Tb_board_SEQ;
drop sequence Tb_message_SEQ;

--====
--1~3 트리거 유저, 게시글, 댓글 삭제에 따른 삭제테이블로의 이동 트리거
--4~5 --답변 채택 업데이트 시 포인트로그 인서트에 따른 포인트테이블 업데이트// 포인트로그 인서트에 따른 포인트테이블 업데이트
--6&7 -- 댓글/게시글 신고로그 insert >> 댓글 게시글 신고 카운트 1자동증가.

--====================================
--테이블 생성
--====================================
--Tb_user 유저테이블.
CREATE TABLE Tb_user
(
    userId VARCHAR2(20) NOT NULL, 
    name VARCHAR2(20) NOT NULL, 
    nickname VARCHAR2(40) NOT NULL, 
    password VARCHAR2(400) NOT NULL, 
    email VARCHAR2(40) NOT NULL, 
    phone NUMBER NOT NULL, 
    enrollDate DATE default sysdate NOT NULL, 
    userRole CHAR(1) default 'U' NOT NULL, 
    address VARCHAR2(100) NOT NULL, 
    preLogDate DATE default sysdate NOT NULL, 
    CONSTRAINT TB_USER_PK PRIMARY KEY (userId),
    CONSTRAINT UQ_nickname UNIQUE (nickname),
    CONSTRAINT UQ_email UNIQUE (email),
    CONSTRAINT UQ_phone UNIQUE (phone),
    constraint ck_userrole check (userRole in ('U', 'S', 'A', 'E'))
);
--user, superadmin, admin, enterprise : user role >> 유저롤 종류입니다.
--유저테이블 삭제 시 자동으로 아래의 삭제 유저로 이동됩니다.

--Tb_del_user : 탈퇴, 삭제된 유저
CREATE TABLE Tb_del_user
(
    userId VARCHAR2(20) NOT NULL, 
    name VARCHAR2(20) NOT NULL, 
    nickname VARCHAR2(40) NOT NULL, 
    email VARCHAR2(40) NOT NULL, 
    phone NUMBER NOT NULL, 
    enrollDate DATE NOT NULL, 
    userRole CHAR(1) NOT NULL, 
    preLogDate DATE default sysdate NOT NULL, 
    delDate DATE default sysdate NOT NULL, 
    CONSTRAINT TB_DEL_USER_PK PRIMARY KEY (userId),
    CONSTRAINT UC_nickname UNIQUE (nickname),
    CONSTRAINT UC_email UNIQUE (email),
    CONSTRAINT UC_phone UNIQUE (phone)
);

--Tb_board 게시글테이블
CREATE TABLE Tb_board
(
    board_no NUMBER NOT NULL, 
    userId VARCHAR2(20) NOT NULL, 
    boardCode CHAR(3) NOT NULL, 
    boardOption VARCHAR2(10) NOT NULL, 
    boardTitle VARCHAR2(200) NOT NULL, 
    boardContent CLOB NOT NULL, 
    originalFileName VARCHAR2(20) default null, 
    renamedFileName VARCHAR2(20) default null, 
    readCount NUMBER default 0 NOT NULL, 
    selectedCount NUMBER default 0 NOT NULL, 
    reportedcount NUMBER default 0 NOT NULL, 
    boardRegDate DATE default sysdate NOT NULL, 
    cmtselect CHAR(1) default 'N' NOT NULL, 
    userGrade VARCHAR2(20) NOT NULL, 
    CONSTRAINT pk_tb_board PRIMARY KEY (board_no),
    constraint ck_cmtselect check (cmtselect in('Y', 'N'))
);


--신고로그에 insert할 경우 게시글테이블의 신고카운트가 updated되는 트리거가 있습니다.
--Tb_rmv_board_log :게시글 신고 로그
CREATE TABLE Tb_board_rep_log
(
    boardNo NUMBER NOT NULL, 
    repId VARCHAR2(20) NOT NULL, 
    regDate DATE default sysdate NOT NULL
);


--게시글 삭제시 자동으로 '삭제테이블로'insert처리됩니다. 아래 트리거 확인해주세요.
--Tb_removed_board : 삭제된 게시글 테이블
CREATE TABLE Tb_removed_board
(
    board_no NUMBER NOT NULL, 
    userId VARCHAR2(20) NOT NULL, 
    boardCode CHAR(3) NOT NULL,  --게시판코드
    boardOption VARCHAR2(10) NOT NULL,  --게시글말머리
    boardTitle VARCHAR2(200) NOT NULL, 
    boardContent CLOB NOT NULL, 
    originalFileName VARCHAR2(20) NULL, 
    renamedFileName VARCHAR2(20) NULL,
    readCount NUMBER NOT NULL, 
    selectedCount NUMBER NOT NULL, 
    reportedCount NUMBER NOT NULL, 
    boardRegDate DATE NOT NULL, 
    cmtselect CHAR(1) NOT NULL, 
    userGrade VARCHAR2(10) NOT NULL, 
    DEL_REP_date DATE default sysdate NOT NULL, 
    DELorREP CHAR(3) NOT NULL, 
    CONSTRAINT TB_REMOVED_BOARD_PK PRIMARY KEY (board_no),
    constraint ck_tb_rmv_board_cmtselect check (cmtselect in('Y', 'N')),
    constraint ck_tb_rmv_board_DELorREP_ check(DELorREP in('del','rep')) --신고, 삭제 중 하나
);



--Tb_comment: 댓글 테이블, (cmtNo는 하나의 seq로 처리)
CREATE TABLE Tb_comment
(
    cmtNo NUMBER NOT NULL, 
    userId VARCHAR2(20) NOT NULL, 
    board_no NUMBER NOT NULL, 
    cmtLevel NUMBER default 1 NOT NULL, 
    cmtRefNo NUMBER default NULL, 
    cmtContent CLOB NULL, 
    cmtRegDate DATE default sysdate NULL, 
    Recommended NUMBER default 0 NULL, 
    reported NUMBER default 0 NULL, 
    userGrade VARCHAR2(10) NULL, 
    CONSTRAINT TB_COMMENT_PK PRIMARY KEY (cmtNo)
);

--댓글도 게시글, 유저와 동일하게 delete문 수행 시 remove테이블에 자동으로 insert가 됩니다.
--*** 단 게시글 삭제에 의한 댓글삭제 트리거는 없으므로 해당 부분은 직접 처리하여야 합니다.
--*** delete board수행 후 delete cmt진행해주면 각 댓글과 게시글은 각 삭제테이블로 이동이됩니다.

--Tb_removed_comment: 삭제, 신고로 이동처리된 댓글
CREATE TABLE Tb_removed_comment
(
    cmtNo NUMBER NOT NULL, 
    userId VARCHAR2(20) NOT NULL, 
    board_no NUMBER NOT NULL, 
    cmtLevel NUMBER NOT NULL, 
    cmtRefNo NUMBER NULL, 
    cmtContent CLOB NOT NULL, 
    cmtRegDate DATE NOT NULL, 
    Recommended NUMBER NOT NULL, 
    DELorREP char(3) NOT NULL, 
    DEL_REP_date DATE default sysdate NOT NULL, 
    reported NUMBER NOT NULL, 
    userGrade VARCHAR2(10) NOT NULL,
    constraint ck_DELorREP check(DELorREP in('del','rep')) --신고, 삭제 중 하나
);


--게시글과 동일하게 신고로그를 작성하면 댓글테이블에 신고카운트가 자동으로 1씩 증가합니다.
--Tb_rmv_comment_log : 댓글 신고로그태이블, 신고된 댓글번호, 신고자, 신고일
CREATE TABLE Tb_comment_rep_log
(
    cmtNo NUMBER NOT NULL, 
    repId VARCHAR2(20) NOT NULL, 
    repDate DATE default sysdate NOT NULL
);


--Tb_selected_cmt 채택된 댓글 테이블
CREATE TABLE Tb_selected_cmt
(
    board_no NUMBER NOT NULL,
    cmtNo NUMBER,
    selectedDate DATE default sysdate,
    awardPoint NUMBER NOT NULL,
    CONSTRAINT TB_SELECTED_CMT_PK PRIMARY KEY (board_no),
    CONSTRAINT UC_cmtNo UNIQUE (cmtNo)
);

--Tb_enterprise :기업용 테이블// 회원 가입 시 기업선택.
CREATE TABLE Tb_enterprise
(
    userId VARCHAR2(20) NOT NULL, 
    empName VARCHAR2(20) NOT NULL, 
    empNo CHAR(12) NOT NULL, 
    empHomepage VARCHAR2(40) NOT NULL, 
    empAddress VARCHAR2(40) NOT NULL, 
    empInfo VARCHAR2(100) NOT NULL, 
    CONSTRAINT TB_ENTERPRISE_PK PRIMARY KEY (userId),
    constraint uq_tb_enterprise UNIQUE (userid, empName, empNo, empHomepage, empInfo)
);


--Tb_recruit 구인구직테이블
CREATE TABLE Tb_recruit
(
    empName VARCHAR2(20) NOT NULL, 
    boardNo NUMBER NOT NULL, 
    jobSitelink VARCHAR2(40) NULL
);


--Tb_blacklist 차단유저 테이블
CREATE TABLE Tb_blacklist
(
    userId VARCHAR2(20) NOT NULL, 
    blackId VARCHAR2(20) NOT NULL, 
    regDate  DATE default sysdate NOT NULL, 
    memo VARCHAR2(500)  NULL,
    CONSTRAINT FK_Tb_blacklist_userId_Tb_user FOREIGN KEY (userId) REFERENCES Tb_user (userId) on delete cascade
);


--Tb_scrap: 스크랩글 테이블
CREATE TABLE Tb_scrap
(
    userId VARCHAR2(20) not null, 
    boardNo NUMBER not null, 
    memo VARCHAR2(500) null, 
    scrapRegDate DATE default sysdate not null,  
    CONSTRAINT FK_Tb_scrap_boardNo_Tb_board_b FOREIGN KEY (boardNo) REFERENCES Tb_board (board_no) on delete cascade,
    CONSTRAINT FK_Tb_scrap_userId_Tb_user_use FOREIGN KEY (userId) REFERENCES Tb_user (userId) on delete cascade
);


--Tb_message
CREATE TABLE Tb_Message
(
    message_no number,
    getUser VARCHAR2(20),
    sendUser VARCHAR2(20),
    title varchar2(200) not null,
    msgContent varchar2(4000)  NOT NULL,
    sendDate DATE default sysdate NOT NULL,
    readornot DATE default sysdate null,
    constraint PK_Tb_message_no primary key(message_no),
    CONSTRAINT FK_Tb_Message_getUser_Tb_user FOREIGN KEY (getUser) REFERENCES Tb_user (userId) on delete set null,
    CONSTRAINT FK_Tb_Message_sendUser_Tb_user FOREIGN KEY (sendUser) REFERENCES Tb_user (userId) on delete set null
);

drop table tb_message;

--Tb_point
CREATE TABLE Tb_point
(
    userId VARCHAR2(20) NOT NULL, 
    point NUMBER NOT NULL, 
    selectedCnt NUMBER default 0 NOT NULL
);

--Tb_point_log
CREATE TABLE Tb_point_log
(
    userId VARCHAR2(20) NOT NULL, 
    pre_point NUMBER NOT NULL, 
    ch_point NUMBER NOT NULL, 
    point_regDate DATE default sysdate NOT NULL, 
    pointlog VARCHAR2(20) NULL,
    CONSTRAINT FK_Tb_point_log_userId_Tb_user FOREIGN KEY (userId) REFERENCES Tb_user(userId) on delete cascade
);
--alter table tb_point_log modify(pointlog null);


SELECT * FROM user_objects;
commit;


--====================================
--시퀀스 생성
--====================================

--댓글번호
CREATE SEQUENCE Tb_comment_SEQ
START WITH 1
INCREMENT BY 1;

--게시글번호
CREATE SEQUENCE Tb_board_SEQ
START WITH 1
INCREMENT BY 1;

--메시지번호

CREATE SEQUENCE Tb_message_SEQ
START WITH 1
INCREMENT BY 1;

--====================================
-- 트리거 생성
--====================================
--**1게시글 삭제시 삭제테이블로 이동 트리거(확인)
create or replace trigger trg_board_del
before
delete on Tb_board
for each row
declare
    delorrep char(3);
    contents clob;
begin
    contents := :old.boardContent;

    if :old.reportedcount < 10 then delorrep := 'del';
    else delorrep := 'rep';
    end if;
    
    insert into Tb_removed_board values(:old.board_no, :old.userId, :old.boardCode, :old.boardOption, :old.boardTitle, contents, :old.originalFileName, :old.renamedFileName, :old.readCount, :old.selectedCount,
                        :old.reportedcount, :old.boardRegDate, :old.cmtselect, :old.userGrade, default, delorrep);

end;
/

--**2댓글 삭제시 삭제테이블로 이동트리거  (확인)
--//게시글이동 트리거와 동일, 접근테이블 분리
create or replace trigger trg_comment_del
before
delete on Tb_comment
for each row
declare
    delorrep char(3);
begin
    if :old.reported < 10 then delorrep := 'del';
    else delorrep := 'rep';
    end if;
    
    insert into Tb_removed_comment values(:old.cmtNo, :old.userId, :old.board_no, :old.cmtLevel, :old.cmtRefNo, :old.cmtContent, 
                                                                    :old.cmtRegDate, :old.recommended, delorrep, default, :old.reported, :old.userGrade);
end;
/


--**3 유저저이동트리거 :회원탈퇴시 회원테이블에서 del >탈퇴회원테이블에insert(확인)
create or replace trigger trg_user_del
before
delete on Tb_user
for each row
begin
 
    insert into Tb_del_user values(:old.userId, :old.name, :old.nickname, :old.email, :old.phone, :old.enrollDate, :old.userRole, :old.preLogDate, default);
end;
/

-- 4포인트부여트리거 : 답글글채택시 채택댓글 테이블에 insert됨에 따라> 포인트로그테이블 insert > 포인트 테이블 update>board채택여부 변경
--(정상작동 확인 완료)
create or replace trigger trg_point_update_by_select
after
update on Tb_selected_cmt
for each row
declare
    slectedcnt number;
    newpoint number;
    awarduser varchar2(20);
    prepoints number;
begin
    select userId into awarduser from Tb_comment where cmtNo = :new.cmtNo;
    select selectedcnt into slectedcnt from Tb_point where userId = awarduser;
    select point into prepoints from Tb_point where userId = awarduser;
   
    newpoint := prepoints + :new.awardPoint;
    slectedcnt := slectedcnt + 1;
    
   insert into Tb_point_log values(awarduser, prepoints, :new.awardPoint, default, '답변채택');
   update Tb_point set point = newpoint where userId = awarduser;     
   update tb_board set cmtselect = 'Y' where board_no = :new.board_no;
end;
/



--5포인트로그테이블 insert > 포인트 테이블 insert (확인)

create or replace trigger trg_point_update
before
insert on Tb_point_log
for each row
declare
    prepoint number;    
begin
    if :old.pre_point =  null then
        update Tb_point set point = :new.ch_point where userId = :new.userId;
    else 
        update Tb_point set point = (:new.pre_point + :new.ch_point) where userId = :new.userId;        
    end if;
end;
/


--6게시글 신고 로그 인서트에 따른 게시글의 신고 카운트 추가 (확인)
create or replace trigger trg_board_report_log_count
after
insert on Tb_board_rep_log
for each row
begin
    update Tb_board set reportedCount = (reportedCount+1) where board_no = :new.boardNo ;
end;
/


--7댓글 신고 로그 인서트에 따른 게시글의 신고 카운트 추가 (확인)
create or replace trigger trg_comment_report_log_count
after
insert on Tb_comment_rep_log
for each row
begin
    update Tb_comment set reported = (reported+1) where cmtNo = :new.cmtNo ;
end;
/

-----------
--==============================
--테스트데이터
--==============================
insert into tb_user values('sadmin', 's관리자', 's그냥관리자', '1234', 'sadmin@datco.co', '0101111111', default, 'S', '닷코 우리집', default);
insert into tb_user values('admin', '관리자', '그냥관리자', '1234', 'admin@datco.co', '010111111', default, 'A', '사이버 망령시', default);
insert into tb_user values('ddochi', '또치', '또또치', '1234', 'eee@naver.com', '01011112222', default, 'U', '경기도 용인시', default);
insert into tb_user values('dulli', '둘리', '둘둘리', '1234', 'dde@naver.com', '0101111223', default, 'U', '경기도 수원시', default);
insert into tb_user values('gogildong', '고길동', '고고길동', '1234', 'eee1@naver.com', '01011112224', default, 'U', '경기도 광명시', default);
insert into tb_user values('heedonge', '희동이', '희희동이', '1234', 'eee2@naver.com', '01011112225', default, 'U', '경기도 남양주시', default);
insert into tb_user values('maichole', '마이콜', '마마이콜', '1234', 'eee3@naver.com', '01011112226', default, 'U', '경기도 파주시', default);
insert into tb_user values('dounoe', '도우너', ' 도도우너', '1234', 'eee4@naver.com', '01011112227', default, 'U', '경기도 포천시', default);
insert into tb_user values('gongsile', '공실이', '고공실이', '1234', 'eee5@naver.com', '01011112228', default, 'U', '경기도 가평군', default);
insert into tb_user values('bayoking', '바요킹', '바바요킹', '1234', 'eee6@naver.com', '01011112229', default, 'U', '경기도 양평군', default);
insert into tb_user values('gsigogi', '가시고기', '가가시고기', '1234', 'eee7@naver.com', '01011112230', default, 'U', '경기도 이천시', default);
insert into tb_user values('hani', '하니', '하하니', '1234', 'eee8@naver.com', '01011112231', default, 'U', '경기도 여주시', default);
insert into tb_user values('hongdukae', '홍두깨', '홍홍두깨', '1234', 'eee9@naver.com', '01011112232', default, 'U', '경기도 오산시', default);
insert into tb_user values('goeunae', '고은애', '고고은애', '1234', 'eea1@naver.com', '01011112233', default, 'U', '경기도 안성시', default);
insert into tb_user values('naaeri', '나애리', '나나애리', '1234', 'eea2@naver.com', '01011112234', default, 'U', '경기도 평택시', default);
insert into tb_user values('ohyeongsim', '오영심', '영심이', '1234', 'eea3@naver.com', '01011112235', default, 'U', '경기도 하남시', default);
insert into tb_user values('wanggyeongtae', '왕경태', '왕왕경태', '1234', 'eea4@naver.com', '01011112236', default, 'U', '경기도 구리시', default);
insert into tb_user values('guwolsuk', '구월숙', '구구월숙', '1234', 'eea5@naver.com', '01011112237', default, 'U', '경기도 군포시', default);
insert into tb_user values('ddachi', '따치', '따따치', '1234', 'eea6@naver.com', '01011112238', default, 'E', '경기도 광명시', default);
insert into tb_user values('dduchi', '뚜치', '뚜뚜치', '1234', 'eea7@naver.com', '01011112239', default, 'E', '경기도 부천시', default);
insert into tb_user values('ddeochi', '떠치', '떠떠치', '1234', 'eea8@naver.com', '01011112240', default, 'E', '경기도 시흥시', default);
insert into tb_user values('userfordel', '삭제용', '사사삭제용', '1234', 'eea9@naver.com', '01011112241', default, 'E', '경기도 시흥시', default);

insert into tb_point values('admin' ,51000, 5);
insert into tb_point values('ddochi' ,6430, 145);
insert into tb_point values('dulli' ,1300, 256);
insert into tb_point values('gogildong' ,100, 234);
insert into tb_point values('heedonge' ,2400, 235);
insert into tb_point values('maichole' ,9670, 112);
insert into tb_point values('dounoe' ,600, 30);
insert into tb_point values('gongsile' ,370, 3);
insert into tb_point values('bayoking' ,1140, 6);
insert into tb_point values('gsigogi' ,6200, 8);
insert into tb_point values('hani' ,81000, 24);
insert into tb_point values('hongdukae' ,85000, 207);
insert into tb_point values('goeunae' ,4000, 62);
insert into tb_point values('naaeri' ,1420, 405);
insert into tb_point values('ohyeongsim' ,1500, 10);
insert into tb_point values('wanggyeongtae' ,1900, 0);
insert into tb_point values('guwolsuk' ,5000, 0);
insert into tb_point values('ddachi' ,9780, 0);
insert into tb_point values('dduchi' ,9700, 2);
insert into tb_point values('ddeochi' ,5700, 1);
insert into tb_point values('userfordel' ,4000, 7);