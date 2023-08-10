package com.example.recyclerviewapp.normalRV;

public class Planet {

    private String planetName;
    private int distanceFromSun;
    private int gravity;
    private int diameter;

    public Planet(String planetName, int distanceFromSun, int gravity, int diameter) {
        this.planetName = planetName;
        this.distanceFromSun = distanceFromSun;
        this.gravity = gravity;
        this.diameter = diameter;
    }

    public String getPlanetName() {
        return planetName;
    }

    public int getDistanceFromSun() {
        return distanceFromSun;
    }

    public int getGravity() {
        return gravity;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public void setDistanceFromSun(int distanceFromSun) {
        this.distanceFromSun = distanceFromSun;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }



}
