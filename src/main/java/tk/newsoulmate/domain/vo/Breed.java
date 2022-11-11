package tk.newsoulmate.domain.vo;

public class Breed {
    private transient Species Species_name;
    private long breedNo;
    private String breedName;

    public Breed(Species species_name, long breedNo, String breedName) {
        Species_name = species_name;
        this.breedNo = breedNo;
        this.breedName = breedName;
    }

    public Species getSpecies_name() {
        return Species_name;
    }

    public void setSpecies_name(Species species_name) {
        Species_name = species_name;
    }

    public long getBreedNo() {
        return breedNo;
    }

    public void setBreedNo(long breedNo) {
        this.breedNo = breedNo;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    @Override
    public String toString() {
        return "Breed{" +
                "Species_name=" + Species_name +
                ", breedNo=" + breedNo +
                ", breedName='" + breedName + '\'' +
                '}';
    }
}
