package com.napier.sem.classes;
//class for capitakl city
//might not need anymore
public class capitalCity {
    private String name;
    private String country;
    private int population;
    //construct
    public capitalCity(String name, String country, int population){
        this.name = name;
        this.country = country;
        this.population = population;
    };
    //getters
    public String getName() {
        return name;
    }
    public String getCountry() {
        return country;
    }
    public int getPopulation() {
        return population;
    }
    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setPopulation(int population){
        this.population = population;
    }
}