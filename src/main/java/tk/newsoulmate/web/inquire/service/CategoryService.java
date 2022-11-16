package tk.newsoulmate.web.inquire.service;

import tk.newsoulmate.domain.dao.BoardDao;
import tk.newsoulmate.domain.dao.CategoryDao;
import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.domain.vo.Category;
import java.sql.Connection;
import java.util.ArrayList;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class CategoryService {
    public ArrayList<Category> selectCategoryList(){
        Connection conn = getConnection();

        ArrayList<Category> list = new CategoryDao().selectCategoryList(conn);

        close();
        return list;

    }

}
