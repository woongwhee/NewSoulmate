package tk.newsoulmate.domain.vo;

public class SupportWithdrawRequest {

	private final long supportNo;

	private final String bank;
	private final String accountNumber;
	private final String accountName;
	public SupportWithdrawRequest(long supportNo, String bank, String accountNumber, String accountName) {
		this.supportNo = supportNo;
		this.bank = bank;
		this.accountNumber = accountNumber;
		this.accountName = accountName;
	}

	public long getSupportNo() {
		return supportNo;
	}

	public String getBank() {
		return bank;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}
}
