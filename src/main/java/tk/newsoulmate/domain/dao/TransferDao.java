package tk.newsoulmate.domain.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import tk.newsoulmate.domain.vo.ManageSupportResponse;
import tk.newsoulmate.domain.vo.SupportWithdrawRequest;
import tk.newsoulmate.domain.vo.Transfer;
import tk.newsoulmate.domain.vo.type.WithdrawStatus;
import tk.newsoulmate.web.common.JDBCTemplet;

public class TransferDao {
	private Properties prop = new Properties();

	public TransferDao(){

		String FilePath=NoticeDao.class.getResource("/sql/support/Transfer-Mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(FilePath));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int withdraw(Connection conn, SupportWithdrawRequest request) {
		int result = 0;
		PreparedStatement psmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("create");

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setLong(1, request.getSupportNo());
			psmt.setString(2, request.getBank());
			psmt.setString(3, request.getAccountName());
			psmt.setString(4, request.getAccountNumber());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplet.close(rset);
			JDBCTemplet.close(psmt);
		}
		return result;
	}

	public Transfer findBySupportNo(Connection conn, long supportNo) {
		PreparedStatement psmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findBySupportNo");

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setLong(1, supportNo);
			rset = psmt.executeQuery();
			if (rset.next()) {
				return this.mapToTransfer(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplet.close(rset);
			JDBCTemplet.close(psmt);
		}
		return null;
	}

	public Transfer findByTransferNo(Connection conn, long transferNo) {
		PreparedStatement psmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findByTransferNo");

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setLong(1, transferNo);
			rset = psmt.executeQuery();
			if (rset.next()) {
				return this.mapToTransfer(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplet.close(rset);
			JDBCTemplet.close(psmt);
		}
		return null;
	}

	public List<ManageSupportResponse> findAll(Connection conn) {
		List<ManageSupportResponse> transfers = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findAll");

		try {
			psmt = conn.prepareStatement(sql);
			rset = psmt.executeQuery();
			while (rset.next()) {
				transfers.add(new ManageSupportResponse(

						rset.getString("MERCHANT_UID"),
						rset.getString("SHELTER_NAME"),
						rset.getDate("PAY_TIME").toLocalDate(),
						rset.getLong("AMOUNT"),
						rset.getString("MEMBER_NAME"),
						WithdrawStatus.valueOf(rset.getString("WD_STATUS")),
						rset.getString("BANK"),
						rset.getString("ACCOUNT_NUMBER"),
						rset.getString("NAME")
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplet.close(rset);
			JDBCTemplet.close(psmt);
		}
		return transfers;
	}

	private Transfer mapToTransfer(ResultSet rset) throws SQLException {
		long transferNo = rset.getLong("TRANSFER_NO");
		long supportNo = rset.getLong("SUPPORT_NO");
		String bank = rset.getString("BANK");
		String name = rset.getString("NAME");
		String accountNumber = rset.getString("ACCOUNT_NUMBER");
		String status = rset.getString("STATUS");

		return new Transfer(transferNo, supportNo, bank, name, accountNumber, status.equals("Y"));
	}
}
