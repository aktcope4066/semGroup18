//class struc to rep populations

package com.napier.sem.classes;

public class Population {
    private String name;
    private int totalPopulation;
    private int cityPopulation;
    private int nonCityPopulation;
    private double cityPercentage;
    private double nonCityPercentage;

    //const
    public Population(double cityPercentage, int cityPopulation, String name, double nonCityPercentage, int nonCityPopulation, int totalPopulation) {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.cityPopulation = cityPopulation;
        this.nonCityPopulation = nonCityPopulation;
        this.cityPercentage = cityPercentage;
        this.nonCityPercentage = nonCityPercentage;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getTotalPopulation() {
        return totalPopulation;
    }

    public int getCityPopulation() {
        return cityPopulation;
    }

    public int getNonCityPopulation() {
        return nonCityPopulation;
    }

    public double getCityPercentage() {
        return cityPercentage;
    }

    public double getNonCityPercentage() {
        return nonCityPercentage;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setTotalPopulation(int totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public void setCityPopulation(int cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    public void setNonCityPopulation(int nonCityPopulation) {
        this.nonCityPopulation = nonCityPopulation;
    }

    public void setCityPercentage(double cityPercentage) {
        this.cityPercentage = cityPercentage;
    }

    public void setNonCityPercentage(double nonCityPercentage) {
        this.nonCityPercentage = nonCityPercentage;
    }
}