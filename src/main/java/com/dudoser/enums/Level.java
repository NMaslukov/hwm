package com.dudoser.enums;

public enum  Level {
    NINTH(7.2, 10), TEN(8.65, 12);

    public final static double perkKoef = 3;
    private double levelWeight;

    Level(double averageStat, double armyKoef){
        this.levelWeight = (averageStat / 2) * armyKoef + perkKoef; //super formula
    }

    public double getLevelWeight(){
        return levelWeight;
    }
}
