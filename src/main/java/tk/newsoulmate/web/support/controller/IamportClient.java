package tk.newsoulmate.web.support.controller;

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

import tk.newsoulmate.domain.dto.response.response.AccessTokenResponse;
import tk.newsoulmate.domain.dto.response.response.IamportResponse;
import tk.newsoulmate.domain.dto.response.response.PaymentDataResponse;
import tk.newsoulmate.web.common.APIKeys;

public class IamportClient {

	// 1) 결제요청은 requestpay
	// 2) accesstoken을 가져옴 - accesstoken 하는 역할: 이 시스템에 접근할수 있는 사용자 라는 증표 클라이언트 쪽에서 저장
	// 클라이언트: 데이터를 받는입장 , 서버: 데이터를 주는입장 아임포트로 받은 정보를 클라이언트가 저장해서 다른 요청을 보낼때 같이 쥐어줘서 보냄

	private final String baseUrl = "https://api.iamport.kr";
	private final String apiKey;
	private final String secretKey;

	// 로그인 할 수 있는 apikey
	// 토큰 인증방식이어서 access token

	private final HttpClient client;
	private final Gson gson;

	public IamportClient() {
		this.client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
		this.gson = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.create();
		this.apiKey= APIKeys.ImportApiKey;
		this.secretKey=APIKeys.ImportSecretKey;
	}

	// 인증방식 : Session(Server) 방식 vs Token(Client) 방식
	// 인증: 특정 시스템에 접근할 수 있는 사람인지 확인받는 작업 -> 로그인
	public String getAccessToken() {
		Map<String, String> bodyMap = new HashMap<>();
		bodyMap.put("imp_key", apiKey);
		// 6) idpw가 아니라 apikey와 secretkey를 어디민콘솔을 통해 뽑아와서 내가 허락된 사람이란걸 아임포트로부터 검증받음
		// 아임포트는 검증 완료 한 다음 accesstoken에 둔다

		bodyMap.put("imp_secret", secretKey); 	// 2) 서버는 내가 준 징표를 잘 가지고 있네? 로 판단 함
		String parsedBody = gson.toJson(bodyMap);

		try { // import에 http 요청
			HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(parsedBody);
			String resp = client.send(
				HttpRequest.newBuilder(new URI(baseUrl + "/users/getToken"))
					.POST(body)
					.header("Content-Type", "application/json")
					.build(),
				HttpResponse.BodyHandlers.ofString()
			).body(); // 8) jsp가 컨트롤러에게 요청 보내면 body로 응답 내려주듯이 아임포트에 요청 응답 내려줌 응답의 바디를 꺼냄
			// JSON 포맷의 String 자료형 문자열 형태로 생김 "{'id':123, 'name':'name'...} 이렇게 생긴 데이터를를 importResponse라는 객체로 바꿔줌
			//"{'code':123, 'message':'success','response':{~~~}}"
			// 9) IamportResponse -> 리스폰스에 어떤게 들어도는 대응할 수 있도록 제네릭 타입으로 열어둠

			Type type = new TypeToken<IamportResponse<AccessTokenResponse>>(){}.getType();
			IamportResponse<AccessTokenResponse> parsedResp = gson.fromJson(resp, type); // 12) 받은 데이터중 accesstoken만 꺼내서 응답해줌
			return parsedResp.getResponse().getAccessToken();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		throw new RuntimeException("Iamport AccessToken 조회에 실패했습니다.");
	}

	// 로그인 한 사람만 꺼내갈 수 있게
	public long getPaymentAmount(String impUid) { // 4)paymentamount를 가져오기 위해서 로그인같은 과정을 거쳐줘야함 가져올수 있는 사람인걸 알림
		String accessToken = this.getAccessToken();
		try {
			String resp = client.send(
				HttpRequest.newBuilder(new URI(baseUrl + "/payments/" + impUid))
					.GET()
					.header("Authorization", accessToken) // 14) 잘못된 토큰이나 만료되었다면 401에러
						// Authorization 헤더에 넣어서 보내면 허락받은애인걸 인증해서 넘어줌
					.build(),
				HttpResponse.BodyHandlers.ofString()
			).body(); // 요청 결과에서 body만 꺼내서 사용할 수 있는 모양으로(iamportREsponse로)

			// iamport에선 제이슨 형태로 데이터를 내려주는데 제이슨형태 문자열을 보내주는걸 객체로 변환하는 과정
			// 데이터중에 amount 반환
			// '{"code":123, "message":"success",...}' -> ObjectMapper or Gson 라이브러리 이용해서 객체를 만들거나 제네릭
			Type type = new TypeToken<IamportResponse<PaymentDataResponse>>(){}.getType(); //객체생성 후 gettype호출 // -> 상속받은애만 객체를 만들 수 있게
			//overide할 메소드 목록이 들어가야 하는데 익명클래스로 만든것 프로텍티드라서 객체를 생성할 수 없는데 다시 클래스는 만들 수 있음
			// typetoken의 기본생성자가 접근제어자가 아닌 protected 그래서 객체생성이 불가능함. 익명클래스를 이용해서 객체 생성을 하려고 함
			// 익명클래스는 typetoken을 상속받는 1회성 클래스를 만드는것 익명클래스를 객체 생성하기 위해 만든것

			// 11) 받은 데이터를 IamportResponse AccesstokenREsponse 데이터타입으로 바꾸겠다
			IamportResponse<PaymentDataResponse> parsedResp = gson.fromJson(resp, type);
			return parsedResp.getResponse().getAmount();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		throw new RuntimeException("Iamport 에 해당 결제내역이 없습니다.");
	}

}
