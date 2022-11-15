package tk.newsoulmate.web.inquire.service;

import tk.newsoulmate.domain.dao.BoardDao;
import tk.newsoulmate.domain.vo.Attachment;

import java.sql.Connection;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class AttachmentService {
    public Attachment selectInquireAttachment(int boardNo){
        Connection conn = getConnection();

        Attachment at = new BoardDao().selectInquireAttachment(conn, boardNo);

        close();

        return at;
    }
}
