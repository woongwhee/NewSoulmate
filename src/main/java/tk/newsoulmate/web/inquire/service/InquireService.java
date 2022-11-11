package tk.newsoulmate.web.inquire.service;

import tk.newsoulmate.domain.dao.BoardDao;
import tk.newsoulmate.domain.dao.CategoryDao;
import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Category;
import tk.newsoulmate.domain.vo.PageInfo;

import java.sql.Connection;
import java.util.ArrayList;

import static tk.newsoulmate.web.common.JDBCTemplet.*;


public class InquireService {
    public int selectListCount(){
        Connection conn = getConnection();

        String categoryName = "문의";

        int listCount = new BoardDao().selectListCount(conn, categoryName);

        close();

        return listCount;
    }
    public ArrayList<Board> selectQnAList(PageInfo pi){
        Connection conn = getConnection();

        ArrayList<Board> list = new BoardDao().selectQnAList(conn, pi);

        close();

        return list;
    }

    public ArrayList<Category> selectCategoryList(){
        Connection conn = getConnection();

        ArrayList<Category> list = new CategoryDao().selectCategoryList(conn);

        close();

        return list;
    }

}
