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

	// 로그인 할 수 있는 apikey
	// 토큰 인증방식이어서 access token

	private final HttpClient client;
	private final Gson gson;

	public IamportClient() {
		this.client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
		this.gson = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.create();
	}

	// 인증방식 : Session(Server) 방식 vs Token(Client) 방식
	// 인증: 특정 시스템에 접근할 수 있는 사람인지 확인받는 작업 -> 로그인
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

	// 로그인 한 사람만 꺼내갈 수 있게
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

			// iamport에선 제이슨 형태로 데이터를 내려주는데 제이슨형태 문자열을 보내주는걸 객체로 변환하는 과정
			// 데이터중에 amount 반환
			// '{"code":123, "message":"success",...}' -> ObjectMapper or Gson 라이브러리 이용해서 객체를 만들거나
			// 제네릭

			Type type = new TypeToken<IamportResponse<PaymentDataResponse>>(){}.getType();
			IamportResponse<PaymentDataResponse> parsedResp = gson.fromJson(resp, type);
			return parsedResp.getResponse().getAmount();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		throw new RuntimeException("Iamport 에 해당 결제내역이 없습니다.");
	}

}
