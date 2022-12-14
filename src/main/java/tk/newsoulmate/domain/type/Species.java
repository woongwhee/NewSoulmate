package tk.newsoulmate.domain.type;

import com.google.gson.annotations.SerializedName;

public enum Species {
    DOG(417000),CAT(422400),ANOTHER(429900);
    public int speciesNo;
    Species(int speciesNo) {
        this.speciesNo=speciesNo;
    }
}
