package com.example.mobileshop.models;

import java.io.Serializable;

public class UserInfo implements Serializable {
    String name, phone, address, email, profileImageUrl;

    public UserInfo(String name, String phone, String address, String email, String profileImageUrl) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

    public UserInfo(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
