package com.example.nipange.models;

public class User {
    private int ID;
    private String name;
    private String email;
    private String country;
    private String gender;

    //private String phoneNumber;
    private String mobileNumber;
    private String yearOfBirth;
    //private String website;
    private String addressLine;

    public User(int ID, String name, String email, String country, String gender, String mobileNumber, String yearOfBirth, String addressLine) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.country = country;
        this.gender = gender;

        //this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.yearOfBirth = yearOfBirth;
        //this.website = website;
        this.addressLine = addressLine;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }





//    public String getPhoneNumber() {
//        return phoneNumber;
//    }

//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

//    public String getWebsite() {
//        return website;
//    }

//    public void setWebsite(String website) {
//        this.website = website;
//    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }
}
