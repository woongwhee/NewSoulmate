package tk.newsoulmate.domain.vo;

import java.time.LocalDate;

import tk.newsoulmate.domain.vo.type.WithdrawStatus;

public class ShelterSupportResponse {

	private final int supportNo;

	private final String supporterName;

	private final LocalDate date;
	private final long amount;
	private final String phoneNumber;
	private final WithdrawStatus withDrawStatus;

	public ShelterSupportResponse(int supportNo, String supporterName, LocalDate date, long amount, String phoneNumber, WithdrawStatus withDrawStatus) {
		this.supportNo = supportNo;
		this.supporterName = supporterName;
		this.date = date;
		this.amount = amount;
		this.phoneNumber = phoneNumber;
		this.withDrawStatus = withDrawStatus;
	}

	public int getSupportNo() {
		return supportNo;
	}

	public String getSupporterName() {
		return supporterName;
	}

	public LocalDate getDate() {
		return date;
	}

	public long getAmount() {
		return amount;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public WithdrawStatus getWithDrawStatus() {
		return withDrawStatus;
	}
}
