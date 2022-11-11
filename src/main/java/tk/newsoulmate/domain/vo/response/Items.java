
package tk.newsoulmate.domain.vo.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import tk.newsoulmate.domain.vo.Notice;

public class Items {

    @Expose
    @SerializedName("item")
    private List<Notice> item = null;

    public List<Notice> getItem() {
        return item;
    }

    public void setItem(List<Notice> item) {
        this.item = item;
    }

}
