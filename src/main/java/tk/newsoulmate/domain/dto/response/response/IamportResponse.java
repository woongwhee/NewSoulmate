package tk.newsoulmate.domain.dto.response.response;

public class IamportResponse <T> {

	private final long code;
	private final String message;

	private final T response;

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
