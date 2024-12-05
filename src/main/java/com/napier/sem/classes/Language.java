package com.napier.sem.classes;
//Language Class
public class Language {
    private String name;
    private double population;
    //constructor
    public Language(String name, double population){
        this.name = name;
        this.population = population;
    }
    //getters
    public String getName() {
        return name;
    }

    public double getPopulation() {
        return population;
    }
    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(double population) {
        this.population = population;
    }
};
