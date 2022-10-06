package com.dataaccess.models;

public class CustomerCountry {
    private String country;
    private String postalCode;

    //Constructor Created
    public CustomerCountry(String country, String postalCode) {
        this.country = country;
        this.postalCode = postalCode;
    }

    //Getter and setter
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
