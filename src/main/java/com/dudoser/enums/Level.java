package com.dudoser.enums;

public enum  Level {
    NINTH(100),
    TEN(155),
    ELEVEN(250),
    TWELVE(410),
    THIRTEEN(600),
    FOURTEEN(1000),
    FIFTEEN(1300),
    SIXTEEN(1650);

    private double levelWeight;

    Level(double weight){
        this.levelWeight = weight; //magic formula
    }

    public double getLevelWeight(){
        return levelWeight;
    }
}
