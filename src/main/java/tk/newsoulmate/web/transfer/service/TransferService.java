package tk.newsoulmate.web.transfer.service;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

import java.sql.Connection;
import java.util.List;

import tk.newsoulmate.domain.dao.TransferDao;
import tk.newsoulmate.domain.vo.response.ManageSupportResponse;
import tk.newsoulmate.domain.vo.Transfer;

public class TransferService {

    private final TransferDao transferDao;

    public TransferService() {
        this.transferDao = new TransferDao();
    }

    public Transfer find(long transferNo) {
        Connection conn = getConnection();
        Transfer transfer =  transferDao.findByTransferNo(conn, transferNo);
        close();
        return transfer;
    }

    public List<ManageSupportResponse> findAll() {
        Connection conn = getConnection();
        List<ManageSupportResponse> transfers =  transferDao.findAll(conn);
        close();
        return transfers;
    }
}
