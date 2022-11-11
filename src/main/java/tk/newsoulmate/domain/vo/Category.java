package tk.newsoulmate.domain.vo;

public class Category {
    private int categoryNo;
    private String categoryName;

    public Category(){
        super();
    }

    public Category(int categoryNo, String categoryName){
        super();
        this.categoryNo = categoryNo;
        this.categoryName = categoryName;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
