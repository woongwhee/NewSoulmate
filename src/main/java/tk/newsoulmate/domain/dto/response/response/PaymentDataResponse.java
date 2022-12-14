package tk.newsoulmate.domain.dto.response.response;

public class PaymentDataResponse {
	private long amount;

	public PaymentDataResponse(long amount) {
		this.amount = amount;
	}

	public long getAmount() {
		return amount;
	}
}
