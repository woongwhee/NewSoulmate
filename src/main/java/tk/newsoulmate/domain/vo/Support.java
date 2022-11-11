package tk.newsoulmate.domain.vo;

import java.sql.Date;

public class Support {

    private int supportNo;
    private int shelterNo;
    private int memberNo;
    private int transeferNo;
    private int cardNo;//카드번호
    private String payment;
    private int price;
    private Date payTime;

    public int getSupportNo() {
        return supportNo;
    }

    public void setSupportNo(int supportNo) {
        this.supportNo = supportNo;
    }

    public int getShelterNo() {
        return shelterNo;
    }

    public void setShelterNo(int shelterNo) {
        this.shelterNo = shelterNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public int getTranseferNo() {
        return transeferNo;
    }

    public void setTranseferNo(int transeferNo) {
        this.transeferNo = transeferNo;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}
