package tk.newsoulmate.client.iamport;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import tk.newsoulmate.client.dto.AccessTokenResponse;
import tk.newsoulmate.client.dto.IamportResponse;
import tk.newsoulmate.client.dto.PaymentDataResponse;

public class IamportClient {

	private final String baseUrl = "https://api.iamport.kr";
	private final String apiKey = "5125134043525072";
	private final String secretKey = "8XJp5Mj3RrDqiwfzxU1UOqCI2CBfy4tT4AdnXdYZrDHkUNHJaDZkrpOU80sbKcjGYeW0y6euJNqfyWxA";

	private final HttpClient client;
	private final Gson gson;

	public IamportClient() {
		this.client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
		this.gson = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.create();
	}

	// 인증방식 : Session(Server) 방식 vs Token(Client) 방식
	public String getAccessToken() {
		Map<String, String> bodyMap = new HashMap<>();
		bodyMap.put("imp_key", apiKey);
		bodyMap.put("imp_secret", secretKey);
		String parsedBody = gson.toJson(bodyMap);

		try {
			HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(parsedBody);
			String resp = client.send(
				HttpRequest.newBuilder(new URI(baseUrl + "/users/getToken"))
					.POST(body)
					.header("Content-Type", "application/json")
					.build(),
				HttpResponse.BodyHandlers.ofString()
			).body();

			Type type = new TypeToken<IamportResponse<AccessTokenResponse>>(){}.getType();
			IamportResponse<AccessTokenResponse> parsedResp = gson.fromJson(resp, type);
			return parsedResp.getResponse().getAccessToken();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		throw new RuntimeException("Iamport AccessToken 조회에 실패했습니다.");
	}

	public long getPaymentAmount(String impUid) {
		String accessToken = this.getAccessToken();
		try {
			String resp = client.send(
				HttpRequest.newBuilder(new URI(baseUrl + "/payments/" + impUid))
					.GET()
					.header("Authorization", accessToken)
					.build(),
				HttpResponse.BodyHandlers.ofString()
			).body();

			Type type = new TypeToken<IamportResponse<PaymentDataResponse>>(){}.getType();
			IamportResponse<PaymentDataResponse> parsedResp = gson.fromJson(resp, type);
			return parsedResp.getResponse().getAmount();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		throw new RuntimeException("Iamport 에 해당 결제내역이 없습니다.");
	}

}
