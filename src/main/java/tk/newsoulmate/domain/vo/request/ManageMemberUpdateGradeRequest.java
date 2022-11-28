package tk.newsoulmate.domain.vo.request;

import tk.newsoulmate.domain.vo.type.MemberGrade;

public class ManageMemberUpdateGradeRequest {
	private final long memberNo;
	private final MemberGrade memberGrade;

	public ManageMemberUpdateGradeRequest(long memberNo, MemberGrade memberGrade) {
		this.memberNo = memberNo;
		this.memberGrade = memberGrade;
	}

	public boolean isToUser() {
		return memberGrade.equals(MemberGrade.USER);
	}

	public long getMemberNo() {
		return memberNo;
	}

	public MemberGrade getMemberGrade() {
		return memberGrade;
	}
}


/*
   POST 요청이라 Client가 Server한테 전달하는 데이터를 Request Body 에 담아서 보내었음.
   서버에서 받아서 사용하려면 결국 객체로 파싱(전환)해야하는데, 데이터를 담을 수 있는 객체가 필요함.
 */
