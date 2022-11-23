package tk.newsoulmate.domain.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import tk.newsoulmate.domain.vo.SupportWithdrawRequest;
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

}
