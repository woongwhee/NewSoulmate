package tk.newsoulmate.domain.dao;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfirmDao {
    private static Properties prop=new Properties();
    public ConfirmDao(){
        try {
            String FileName=ShelterDao.class.getResource("/sql/confirm/Confirm-Mapper.xml").getPath();
            prop.loadFromXML(new FileInputStream(FileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int selectConfirmNo() {
        return 0;

    }
//    public int insertConfirm(String email,){
//
//
//
//
//    }


}