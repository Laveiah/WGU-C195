package model;

public class Countries {
    private int countriesID;
    private String countriesName;


    /**
     *
     * @param countriesID
     * @param countriesName
     */
    public Countries(int countriesID, String countriesName) {
        this.countriesID = countriesID;
        this.countriesName = countriesName;
    }

    /**
     *
     * @return countriesID
     */
    public int getCountriesID() {

        return countriesID;
    }

    /**
     *
     * @return countriesName
     */
    public String getCountriesName() {

        return countriesName;
    }
    /**
     *
     * @return countriesName
     */
    @Override
    public String toString(){
        return countriesName;
    }
}