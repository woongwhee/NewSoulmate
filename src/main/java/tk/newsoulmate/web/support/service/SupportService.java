package tk.newsoulmate.web.support.service;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import tk.newsoulmate.domain.dao.TransferDao;
import tk.newsoulmate.domain.vo.ShelterSupportResponse;
import tk.newsoulmate.domain.vo.SupportWithdrawRequest;
import tk.newsoulmate.domain.vo.response.SupportCompleteResponse;
import tk.newsoulmate.domain.vo.type.WithdrawStatus;
import tk.newsoulmate.web.support.controller.IamportClient;
import tk.newsoulmate.domain.dao.SupportDao;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.Support;
import tk.newsoulmate.domain.vo.SupportPage;
import tk.newsoulmate.domain.vo.type.SupportStatus;
import tk.newsoulmate.web.common.JDBCTemplet;

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

	public void complete(String merchantUid) {
		Connection conn = JDBCTemplet.getConnection();
		new SupportDao().updateStatus(conn, merchantUid, SupportStatus.DONE);
		new SupportDao().updateWithdrawStatus(conn, merchantUid, WithdrawStatus.PENDING);
		JDBCTemplet.close();
	}

	public void failed(String merchantUid) {
		Connection conn = JDBCTemplet.getConnection();
		new SupportDao().updateStatus(conn, merchantUid, SupportStatus.FAILED);
		JDBCTemplet.close();
	}

	public List<SupportCompleteResponse> findAllOnlyDone(Member member, SupportPage page) {
		Connection conn = JDBCTemplet.getConnection();
		List<SupportCompleteResponse> supportList = new SupportDao().findAllOnlyDone(conn, member.getMemberNo(), page);
		JDBCTemplet.close();
		return supportList;
	}

	public List<SupportCompleteResponse> findAllOnlyDoneByDate(Member member, LocalDate startDate, LocalDate endDate, SupportPage page) {
		Connection conn = JDBCTemplet.getConnection();
		List<SupportCompleteResponse> supportList = new SupportDao().findAllOnlyDoneByDate(conn, member.getMemberNo(), startDate, endDate, page);
		JDBCTemplet.close();
		return supportList;
	}

	public int countOnlyDone(Member member) {
		Connection conn = JDBCTemplet.getConnection();
		int count = new SupportDao().countOnlyDone(conn, member.getMemberNo());
		JDBCTemplet.close();
		return count;
	}

	public int countOnlyDoneByDate(Member member, LocalDate startDate, LocalDate endDate) {
		Connection conn = JDBCTemplet.getConnection();
		int count = new SupportDao().countOnlyDoneByDate(conn, member.getMemberNo(), startDate, endDate);
		JDBCTemplet.close();
		return count;
	}

	public List<ShelterSupportResponse> findAllOnlyDoneByShelterNo(long shelterNo) {
		Connection conn = JDBCTemplet.getConnection();
		List<ShelterSupportResponse> supports = new SupportDao().findAllOnlyDoneByShelterNo(conn, shelterNo);
		JDBCTemplet.close();
		return supports;
	}

	public int withdraw(SupportWithdrawRequest request) {
		Connection conn = JDBCTemplet.getConnection();
		int result = new SupportDao().withdraw(conn, request.getSupportNo());
		new TransferDao().withdraw(conn, request);
		JDBCTemplet.close();
		return result;
	}

}

