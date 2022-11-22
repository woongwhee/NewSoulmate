package tk.newsoulmate.web.common;

import tk.newsoulmate.domain.vo.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ApiKey
 */
public  class APIKeys {
    public static Properties prop=new Properties();
    public static String NoticeKey;

    public APIKeys(){
            String FilePath= APIKeys.class.getResource("/key/APIKey.xml").getPath();
            this.prop=new Properties();
            try {
                prop.loadFromXML(new FileInputStream(FilePath) );
                this.NoticeKey=prop.getProperty("NoticeKey");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
