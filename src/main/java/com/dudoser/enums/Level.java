package com.dudoser.enums;

public enum  Level {
    NINTH(4.325),
    TEN(8.65),
    ELEVEN(10.22),
    TWELVE(11.8),
    THIRTEEN(13.92),
    FOURTEEN(16.38),
    FIFTEEN(50),
    SIXTEEN(100);

    public final static double perkKoef = 3;
    private static final int STAT_PER_LEVEL = 1;
    private double levelWeight;

    Level(double weight){
        this.levelWeight = weight; //magic formula
    }

    public double getLevelWeight(){
        return levelWeight;
    }
}
