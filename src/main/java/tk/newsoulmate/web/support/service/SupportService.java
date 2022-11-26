package tk.newsoulmate.web.support.service;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import tk.newsoulmate.domain.dao.SupportDao;
import tk.newsoulmate.domain.dao.TransferDao;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.domain.vo.Support;
import tk.newsoulmate.domain.vo.SupportPage;
import tk.newsoulmate.domain.vo.request.SupportWithdrawRequest;
import tk.newsoulmate.domain.vo.type.SupportStatus;
import tk.newsoulmate.domain.vo.type.WithdrawStatus;
import tk.newsoulmate.web.common.JDBCTemplet;
import tk.newsoulmate.web.support.controller.IamportClient;

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
		new SupportDao().updateWithdrawStatus(conn, merchantUid, SupportStatus.DONE);
		new SupportDao().updateWithdrawStatus(conn, merchantUid, WithdrawStatus.PENDING);
		JDBCTemplet.close();
	}

	public void failed(String merchantUid) {
		Connection conn = JDBCTemplet.getConnection();
		new SupportDao().updateWithdrawStatus(conn, merchantUid, SupportStatus.FAILED);
		JDBCTemplet.close();
	}

	public List<Support> findAllOnlyDone(Member member, SupportPage page) {
		Connection conn = JDBCTemplet.getConnection();
		List<Support> supportList = new SupportDao().findAllOnlyDone(conn, member.getMemberNo(), page);
		JDBCTemplet.close();
		return supportList;
	}

	public List<Support> findAllOnlyDoneByDate(Member member, LocalDate startDate, LocalDate endDate, SupportPage page) {
		Connection conn = JDBCTemplet.getConnection();
		List<Support> supportList = new SupportDao().findAllOnlyDoneByDate(conn, member.getMemberNo(), startDate, endDate, page);
		JDBCTemplet.close();
		return supportList;
	}

	public List<Support> findAllByFilter(PageInfo page, String filter) {
		Connection conn = JDBCTemplet.getConnection();
		List<Support> supportList = new SupportDao().findAllByFilter(conn, page, filter);
		JDBCTemplet.close();
		return supportList;
	}

	public int countOnlyDone(Member member) {
		Connection conn = JDBCTemplet.getConnection();
		int count = new SupportDao().countOnlyDone(conn, member.getMemberNo());
		JDBCTemplet.close();
		return count;
	}

	public int countByFilter(String filter) {
		Connection conn = JDBCTemplet.getConnection();
		int count = new SupportDao().countByFilter(conn, filter);
		JDBCTemplet.close();
		return count;
	}

	public int countOnlyDoneByDate(Member member, LocalDate startDate, LocalDate endDate) {
		Connection conn = JDBCTemplet.getConnection();
		int count = new SupportDao().countOnlyDoneByDate(conn, member.getMemberNo(), startDate, endDate);
		JDBCTemplet.close();
		return count;
	}

	public List<Support> findAllOnlyDoneByShelterNo(long shelterNo) {
		Connection conn = JDBCTemplet.getConnection();
		List<Support> supports = new SupportDao().findAllOnlyDoneByShelterNo(conn, shelterNo);
		JDBCTemplet.close();
		return supports;
	}

	public int withdraw(SupportWithdrawRequest request) {
		Connection conn = JDBCTemplet.getConnection();
		int result = new SupportDao().updateWithdrawStatus(conn, request.getSupportNo(), WithdrawStatus.REQUESTED);
		new TransferDao().withdraw(conn, request);
		JDBCTemplet.close();
		return result;
	}

	public int withdrawApprove(long supportNo) {
		Connection conn = JDBCTemplet.getConnection();
		int result = new SupportDao().updateWithdrawStatus(conn, supportNo, WithdrawStatus.DONE);
		JDBCTemplet.close();
		return result;
	}

}

