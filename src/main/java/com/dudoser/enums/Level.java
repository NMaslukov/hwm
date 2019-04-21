package com.dudoser.enums;

public enum  Level {
    NINTH(100),
    TEN(150),
    ELEVEN(250),
    TWELVE(400),
    THIRTEEN(600),
    FOURTEEN(950),
    FIFTEEN(1250),
    SIXTEEN(1750);

    private double levelWeight;

    Level(double weight){
        this.levelWeight = weight; //magic formula
    }

    public double getLevelWeight(){
        return levelWeight;
    }
}
