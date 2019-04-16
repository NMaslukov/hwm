package com.dudoser.enums;

public enum  Level {
    NINTH(7.2, 10),
    TEN(8.65, 12),
    ELEVEN(10.22, 14),
    TWELVE(11.8, 16),
    THIRTEEN(13.92, 18),
    FOURTEEN(16.38, 20),
    FIFTEEN(19.8, 22),
    SIXTEEN(23.59, 24);

    public final static double perkKoef = 3;
    private static final int STAT_PER_LEVEL = 1;
    private double levelWeight;

    Level(double averageStat, double armyKoef){
        this.levelWeight = ((averageStat + STAT_PER_LEVEL) / 2) * armyKoef + perkKoef; //magic formula
    }

    public double getLevelWeight(){
        return levelWeight;
    }
}
