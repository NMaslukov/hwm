package com.dudoser.enums;

public enum  Level {
    NINTH(100),
    TEN(150),
    ELEVEN(10.22),
    TWELVE(400),
    THIRTEEN(13.92),
    FOURTEEN(16.38),
    FIFTEEN(50),
    SIXTEEN(100);

    private double levelWeight;

    Level(double weight){
        this.levelWeight = weight; //magic formula
    }

    public double getLevelWeight(){
        return levelWeight;
    }
}
