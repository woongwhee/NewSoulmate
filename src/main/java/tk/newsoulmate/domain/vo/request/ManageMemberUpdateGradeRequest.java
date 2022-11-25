package tk.newsoulmate.domain.vo.request;

import tk.newsoulmate.domain.vo.type.MemberGrade;

public class ManageMemberUpdateGradeRequest {
	private long memberNo;
	private MemberGrade memberGrade;

	public ManageMemberUpdateGradeRequest(long memberNo, MemberGrade memberGrade) {
		this.memberNo = memberNo;
		this.memberGrade = memberGrade;
	}

	public long getMemberNo() {
		return memberNo;
	}

	public MemberGrade getMemberGrade() {
		return memberGrade;
	}
}
