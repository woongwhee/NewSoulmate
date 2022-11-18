package tk.newsoulmate.web.support.service;

import tk.newsoulmate.client.iamport.IamportClient;
import tk.newsoulmate.domain.dao.SupportDao;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.Support;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.time.LocalDateTime;

public class SupportService {
	public String createNumber(int loginMemberNo, long shelterNo, long amount) {
		String number = "NEWSOULMATE_" + loginMemberNo + "_" + LocalDateTime.now();
		Connection conn = JDBCTemplet.getConnection();
		int result = new SupportDao().initializeSupport(conn, shelterNo, loginMemberNo, number, amount);
		if (result <= 0) {
			JDBCTemplet.rollback(conn);
		}
		JDBCTemplet.close();
		return number;
	}

	public boolean verify(String impUid, String merchantUid) {
		long amount = new IamportClient().getPaymentAmount(impUid);
		Support support = this.find(merchantUid);
		return support.verify(amount);
	}

	public Support find(String merchantUid) {
		Connection conn = JDBCTemplet.getConnection();
		Support support = new SupportDao().findByMerchantUid(conn, merchantUid);
		JDBCTemplet.close();
		return support;
	}

}

