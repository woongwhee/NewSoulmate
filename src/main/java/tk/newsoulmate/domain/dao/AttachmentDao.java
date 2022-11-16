package tk.newsoulmate.domain.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AttachmentDao {

    private Properties prop = new Properties();

    public AttachmentDao(){
        try {
            prop.loadFromXML(new FileInputStream(AttachmentDao.class.getResource("/sql/Attachment/Attachment-Mapper.xml").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


















}
