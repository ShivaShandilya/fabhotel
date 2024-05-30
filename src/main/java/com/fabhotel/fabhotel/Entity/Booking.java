package com.fabhotel.fabhotel.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "guest", nullable = false)
    private Integer guest;

    @Column(name = "totalnight", nullable = false)
    private Integer totalnight;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "property_user_id")
    private PropertyUser propertyUser;

    public PropertyUser getPropertyUser() {
        return propertyUser;
    }

    public void setPropertyUser(PropertyUser propertyUser) {
        this.propertyUser = propertyUser;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Integer getTotalnight() {
        return totalnight;
    }

    public void setTotalnight(Integer totalnight) {
        this.totalnight = totalnight;
    }

    public Integer getGuest() {
        return guest;
    }

    public void setGuest(Integer guest) {
        this.guest = guest;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}