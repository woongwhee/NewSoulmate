package tk.newsoulmate.domain.dto.response.response;

public class AccessTokenResponse {

	private String accessToken;
	private long now;
	private long expiredAt;

	public AccessTokenResponse(String accessToken, long now, long expiredAt) {
		this.accessToken = accessToken;
		this.now = now;
		this.expiredAt = expiredAt;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public long getNow() {
		return now;
	}

	public long getExpiredAt() {
		return expiredAt;
	}

	@Override
	public String toString() {
		return "AccessTokenResponse{" +
			"accessToken='" + accessToken + '\'' +
			", now=" + now +
			", expiredAt=" + expiredAt +
			'}';
	}
}
