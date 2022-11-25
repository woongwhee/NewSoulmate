package tk.newsoulmate.domain.vo.request;

public class ManageMemberDeleteRequest {

	private final long memberNo;

	public ManageMemberDeleteRequest(long memberNo) {
		this.memberNo = memberNo;
	}

	public long getMemberNo() {
		return memberNo;
	}
}
