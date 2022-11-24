package tk.newsoulmate.domain.vo;

public class Transfer {
    private long transferNo;
    private long supportNo;
    private String bank;
    private String name;
    private String accountNumber;
    private boolean status;

    public Transfer(long transferNo, long supportNo, String bank, String name, String accountNumber, boolean status) {
        this.transferNo = transferNo;
        this.supportNo = supportNo;
        this.bank = bank;
        this.name = name;
        this.accountNumber = accountNumber;
        this.status = status;
    }

    public long getTransferNo() {
        return transferNo;
    }

    public long getSupportNo() {
        return supportNo;
    }

    public String getBank() {
        return bank;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean isStatus() {
        return status;
    }
}
