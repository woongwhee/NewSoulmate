<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>후원하기</title>
    <%@include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/support/supportPayment.css" rel="stylesheet">
</head>
<body>

<script>
    function numberMaxLength(e) {
        if (e.value.length > e.maxLength) {
            e.value = e.value.slice(0, e.maxLength);
        }
    }
</script>



<div id="container">

    <div class="support_notice">
        <div id="notice_left_box">
            <h1>후원하기</h1>
        </div>
        <div id="notice_right_box">
            <p>"모든 생명은 보호받고 존중받을 권리가 있습니다"<br>
                환승주인에서는 사람과 동물이 생태적으로, 윤리적 조화를 이루며 살아가는 세상을 만들기 위해<br>
                다방면에서 활동을 펼쳐가고 있습니다.
            </p>
        </div>
    </div>

    <div id="hr_1">
        <hr>
    </div>

    <div class="">
        <h2>후원 정보</h2>
        <table id="">
            <tr>
                <td>
                    <h4>후원 항목</h4>
                </td>
                <td>일반후원</td>
            </tr>
            <tr>
                <td>
                    <h4>후원 금액</h4>
                </td>
                <td>
                    <input type="button" value="10,000원"> <input type="button" value="20,000원"><input type="button"
                                                                                                      value="30,000원"><input type="button" value="50,000원"><br><input type="button"
                                                                                                                                                                      value="100,000원">
                </td>
            </tr>
        </table>
    </div>

    <hr>

    <div class="">
        <h2>결제 정보</h2>
        <p>후원 내역 확인 등 마이페이지 이용 시 꼭 필요한 정보이니, 명확하게 기입해 주세요.</p>
        <form action="" value="" method="post">
            <table id="">
                <tr>
                    <td><label for="">구분</label></td>
                    <td><input type="button" value="개인"> <input type="button" value="사업자"></td>
                </tr>
                <tr>
                    <td><label for="">이름</label></td>
                    <td><input type="text" name="" id="" placeholder="내용을 입력해주세요" required></td>
                </tr>
                <tr>
                    <td><label for="">휴대전화</label></td>
                    <td>
                        <select name="" id="">
                            <option value="010">010</option>
                            <option value="011">011</option>
                            <option value="016">016</option>
                            <option value="017">017</option>
                            <option value="019">019</option>
                        </select>
                        <input type="number" name="" id="" placeholder="-빼고 입력" maxlength="8"
                               oninput="numberMaxLength(this);" required>
                    </td>
                </tr>
                <tr>
                    <td><label for="">이메일</label></td>
                    <td><input type="email" name="" id="" required></td>
                </tr>
                <tr>
                    <td><label for="">카드번호</label></td>
                    <td><input type="number" name="" id="" placeholder="내용을 입력해주세요" required></td>
                </tr>
                <tr>
                    <td><label for="">유효기간</label></td>
                    <td><input type="number" name="" id="" placeholder="MM/YY" maxlength="4"
                               oninput="numberMaxLength(this)" required></td>
                </tr>
                <tr>
                    <td><label for="">카드주명</label></td>
                    <td><input type="text" name="" id="" placeholder="내용을 입력해주세요" required></td>
                </tr>
                <tr>
                    <td><label for="">비밀번호</label></td>
                    <td><input type="password" name="" id="" required></td>
                </tr>
                <tr>
                    <td><label for="">생년월일</label></td>
                    <td><input type="number" name="" id="" placeholder="생년월일 6자리 입력" maxlength="6"
                               oninput="numberMaxLength(this)" required></td>
                </tr>
            </table>

            <div class="">
                <label><input type="checkbox" name="" class="">개인정보 처리방침 및 이용약간 동의</label>
            </div>
            <input type="button" value="후원하기">
        </form>
    </div>
</body>
</html>
