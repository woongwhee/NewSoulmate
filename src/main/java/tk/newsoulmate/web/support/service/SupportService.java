package tk.newsoulmate.web.support.service;

import tk.newsoulmate.client.iamport.IamportClient;
import tk.newsoulmate.domain.dao.ShelterDao;
import tk.newsoulmate.domain.dao.SupportDao;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.Support;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.time.LocalTime;
import java.util.ArrayList;

import static tk.newsoulmate.web.common.JDBCTemplet.close;
import static tk.newsoulmate.web.common.JDBCTemplet.getConnection;

public class SupportService {
	public String createNumber(int loginMemberNo, long shelterNo, long amount) {
		String number = "NS_" + loginMemberNo + "_" + LocalTime.now();
		Connection conn = JDBCTemplet.getConnection();
		int result = new SupportDao().initializeSupport(conn, shelterNo, loginMemberNo, number, amount);
		if (result <= 0) {
			JDBCTemplet.rollback(conn);
		}
		JDBCTemplet.close();
		return number;
	}

	public boolean verify(String impUid, String merchantUid) {
		// 실제 결제된 amount
		long amount = new IamportClient().getPaymentAmount(impUid);
		Support support = this.find(merchantUid);
		return support.verify(amount); // 사용자가 클릭해서 넘어온 amount
	}

	public Support find(String merchantUid) {
		Connection conn = JDBCTemplet.getConnection();
		Support support = new SupportDao().findByMerchantUid(conn, merchantUid);
		JDBCTemplet.close();
		return support;
	}

	public ArrayList<Support> selectSupportList() {
		Connection conn = JDBCTemplet.getConnection();
		ArrayList<Support> supportList = new SupportDao().selectSupportList(conn);
		JDBCTemplet.close();
		return supportList;
	}


}

