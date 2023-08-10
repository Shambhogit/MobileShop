package com.example.mobileshop.models;

public class BuyModel {
    public String name, price, quantity, paymentOption, customer_name, mobile_no, total_price,address;

    public String image;
    public BuyModel(){

    }
    public BuyModel(String image, String name, String price, String quantity, String paymentOption, String customer_name, String mobile_no,String address, String total_price) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.paymentOption = paymentOption;
        this.customer_name = customer_name;
        this.mobile_no = mobile_no;
        this.total_price = total_price;
        this.address = address;
        this.image = image;
    }

    public String getImage(){return image;}
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
    public String getAddress() {
        return address;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public String getTotal_price() {
        return total_price;
    }
}
