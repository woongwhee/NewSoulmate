package tk.newsoulmate.web.manger.site.service;

import tk.newsoulmate.domain.dao.GradeUpDao;

import tk.newsoulmate.domain.dao.MemberDao;
import tk.newsoulmate.domain.vo.GradeUp;
import tk.newsoulmate.domain.vo.Member;

import java.sql.Connection;
import java.util.ArrayList;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class ManageService {


    public ArrayList<Member> selectManageMember() {
        Connection conn = getConnection();
        ArrayList<Member> mList = new MemberDao().selectManageMember(conn);
        close();
        return mList;
    }

    public ArrayList<GradeUp> selectGradeUp() {
        Connection conn = getConnection();
        ArrayList<GradeUp> gList = new GradeUpDao().selectAllGrade(conn);
        close();
        return gList;
    }
    }
