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
    public static String ImportApiKey;
    public static String KakaoMapKey;
    public static String ImportSecretKey;
    public static String SolapiKey;
    public static String SolapiSecretKey;
    public APIKeys(){
            String FilePath= APIKeys.class.getResource("/key/APIKey.xml").getPath();
            this.prop=new Properties();
            try {
                prop.loadFromXML(new FileInputStream(FilePath) );
                this.NoticeKey=prop.getProperty("NoticeKey");
                this.ImportApiKey=prop.getProperty("ImportApiKey");
                this.KakaoMapKey=prop.getProperty("KakaoMapKey");
                this.ImportSecretKey=prop.getProperty("ImportSecretKey");
                this.SolapiKey=prop.getProperty("SolapiKey");
                this.SolapiSecretKey=prop.getProperty("SolapiSecretKey");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
