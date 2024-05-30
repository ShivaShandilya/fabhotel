package com.fabhotel.fabhotel.payload;

public class BookingDto {
    private String id;
    private String email;
    private String mobileNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }


    public int getTotalnight() {
        return totalnight;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalnight(int totalnight) {
        this.totalnight = totalnight;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }



    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyUsername() {
        return propertyUsername;
    }

    public void setPropertyUsername(String propertyUsername) {
        this.propertyUsername = propertyUsername;
    }

    private String guest;
    private int totalnight;
    private  int totalprice;
    private String propertyName;
    private String propertyUsername;


}
