package tk.newsoulmate.web.common;

import com.oreilly.servlet.multipart.FileRenamePolicy;
import oracle.sql.BFILE;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFileRenamePolicy implements FileRenamePolicy {

    @Override
    public File rename(File originFile) {

        // 원본파일명 ("aaa.jpg")
        String originName = originFile.getName();

        // 수정 파일명 : 파일업로드된시간(년월일시분초)+5자리 랜덤값 => 최대한 이름이 겹치지않게
        // 확장자 : 원본파일의 확장자 그대로

        // 원본명                =>            수정명
        // aaa.jpg             =>            2022121719265511111.jpg

        // 1. 파일업로드된시간(년월일시분초) => String currentTime;
        String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        int ranNum = (int)(Math.random() * 90000 + 10000);

        // 3. 원본파일 확장자 추출.
        // 파일명 중간에 .이 들어가는 경우를 생략하고 마지막 .의 위치에서 추출하기
        String ext =  originName.substring(originName.lastIndexOf(".")) ;

        // 2. 5자리 랜덤값 => int ranNum
        String changeName = currentTime+ranNum+ext;

        // 원본파일(originFile)을 수정된 파일명으로 적용시켜서 파일객체로 반환.
        return new File(originFile.getParent(),changeName);


    }
}
