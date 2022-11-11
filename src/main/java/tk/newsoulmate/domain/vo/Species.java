package tk.newsoulmate.domain.vo;

public enum Species {
    DOG(417000),CAT(422400),ANOTHER(429900);
    int speciesNo;
    Species(int speciesNo) {
        this.speciesNo=speciesNo;
    }
    public int getSpeciesNo(){
        return speciesNo;
    }
}
