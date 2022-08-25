package model;

public class CountryReport {
    private String countryName;
    private int total;

    /**
     *
     * @param countryName
     * @param total
     *
     * */
    public CountryReport(String countryName, int total) {
        this.countryName = countryName;
        this.total = total;
    }

    /**
     *
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     *
     * @return total
     */
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
