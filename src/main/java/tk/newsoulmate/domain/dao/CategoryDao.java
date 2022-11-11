package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Category;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static tk.newsoulmate.web.common.JDBCTemplet.close;

public class CategoryDao {
    private Properties prop = new Properties();

    public CategoryDao(){
        try {
            prop.loadFromXML(new FileInputStream(CategoryDao.class.getResource("/sql/category/Category-Mapper.xml").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Category> selectCategoryList(Connection conn){

        ArrayList<Category> list = new ArrayList<>();

        PreparedStatement psmt = null;

        ResultSet rset = null;

        String sql = prop.getProperty("selectCategoryList");

        try {
            psmt = conn.prepareStatement(sql);

            rset = psmt.executeQuery();

            while (rset.next()){
                Category c = new Category();
                c.setCategoryName(rset.getString("CATEGORY_NAME"));
                c.setCategoryNo(rset.getInt("CATEGORY_NO"));
                list.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return list;


    }
}
