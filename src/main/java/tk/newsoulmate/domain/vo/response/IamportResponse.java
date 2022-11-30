package tk.newsoulmate.domain.vo.response;

public class IamportResponse <T> {

	private final long code;
	private final String message;

	private final T response;
	// 9) T자리에 AccessTokenresponse가들어오거나 paymentDataresponse가 자리에 들어오거나 사용하는쪽에서 타입을 정하겠다고 변수처럼 비워놓는것

	public IamportResponse(long code, String message, T response) {
		this.code = code;
		this.message = message;
		this.response = response;
	}

	public long getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getResponse() {
		return response;
	}

	@Override
	public String toString() {
		return "IamportResponse{" +
			"code=" + code +
			", message='" + message + '\'' +
			", response=" + response +
			'}';
	}
}
