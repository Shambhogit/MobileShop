package com.example.firebaseimageproject;

import android.provider.ContactsContract;

public class ImageModel {

    String imageUri;
    String name;
    public ImageModel(){

    }

    public ImageModel(String imageUri, String name) {
        this.imageUri = imageUri;
        this.name = name;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getName() {
        return name;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setName(String name) {
        this.name = name;
    }
}
