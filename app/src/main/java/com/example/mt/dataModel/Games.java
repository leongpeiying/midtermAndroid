package com.example.mt.dataModel;

public class Games {

    private String gameName;
    private String gameRate;
    private String gamePrice;
    private String gameDescription;

    public Games(){

    }

    public Games(String Name, String Rate, String Price, String Description) {
        this.gameName = Name;
        this.gameRate = Rate;
        this.gamePrice = Price;
        this.gameDescription = Description;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameRate() {
        return gameRate;
    }

    public String getGamePrice() {
        return gamePrice;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameName(String Name) {
        this.gameName = Name;
    }

    public void setGameRate(String Rate) {
        this.gameRate = Rate;
    }

    public void setGamePrice(String Price) {
        this.gamePrice = Price;
    }

    public void setGameDescription(String Description) {
        this.gameDescription = Description;
    }
}
