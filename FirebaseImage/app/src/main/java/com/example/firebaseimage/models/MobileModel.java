package com.example.firebaseimage.models;

import java.io.Serializable;

public class MobileModel implements Serializable {
    String mobileImageURI, name, price, ramRom, processor, camera, display, battery, warranty, firebaseId;

    public MobileModel(String mobileImageURI, String name, String price, String ramRom, String processor, String camera, String display, String battery, String warranty) {
        this.mobileImageURI = mobileImageURI;
        this.name = name;
        this.price = price;
        this.ramRom = ramRom;
        this.processor = processor;
        this.camera = camera;
        this.display = display;
        this.battery = battery;
        this.warranty = warranty;
    }
    public MobileModel(String firebaseId , String mobileImageURI, String name, String price, String ramRom, String processor, String camera, String display, String battery, String warranty) {
        this.mobileImageURI = mobileImageURI;
        this.name = name;
        this.price = price;
        this.ramRom = ramRom;
        this.processor = processor;
        this.camera = camera;
        this.display = display;
        this.battery = battery;
        this.warranty = warranty;
        this.firebaseId = firebaseId;
    }

    public MobileModel(){}

    public String getMobileImageURI() {
        return mobileImageURI;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public void setMobileImageURI(String mobileImageURI) {
        this.mobileImageURI = mobileImageURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRamRom() {
        return ramRom;
    }

    public void setRamRom(String ramRom) {
        this.ramRom = ramRom;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }
}
