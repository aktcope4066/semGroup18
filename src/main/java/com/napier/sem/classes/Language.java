package com.napier.sem.classes;
//Language Class
public class Language {
    private String countryCode;
    private String language;
    private boolean isOfficial;
    private double percentage;

    // Constructor that accepts language name and speakers percentage
    public Language(String language, double percentage) {
        this.language = language;
        this.percentage = percentage;
    }

    // Getters and setters (write them like this from now)
    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public boolean isOfficial() { return isOfficial; }
    public void setOfficial(boolean isOfficial) { this.isOfficial = isOfficial; }

    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }
};
