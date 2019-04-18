package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.enums.Bild;
import com.dudoser.enums.Level;
import com.google.common.collect.ImmutableSet;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DistributorFirstTest extends DistributorBaseTest{

    private static final Level L9 = prepareLevelMock(5.0, Level.NINTH.name());
    private static final Level L10 = prepareLevelMock(12.5, Level.TEN.name());
    private static final Level L13 = prepareLevelMock(20.0, Level.THIRTEEN.name());
    private static final List<Integer[]> expectedTeamCountSequence = initExpectedTeamCountSequenceList();
    private static ImmutableSet<Hero> testHeroes = prepareResultHeroSet();

    public DistributorFirstTest() {
        super(testHeroes, 0, 2, expectedTeamCountSequence);
    }

    private static ImmutableSet<Hero> prepareResultHeroSet() {
        Set<Hero> resultSet = new HashSet<>();
        resultSet.addAll(prepareFirstHeroSet());
        resultSet.addAll(prepareSecondHeroSet());
        return ImmutableSet.copyOf(resultSet);
    }

    private static Set<Hero> prepareFirstHeroSet() {
        Set<Hero> heroesSet = new HashSet<>();
        heroesSet.add(new Hero(new Random().nextInt()*1000, L9, Bild.ATTACK));
        heroesSet.add(new Hero(new Random().nextInt()*1000, L10, Bild.ATTACK));
        heroesSet.add(new Hero(new Random().nextInt()*1000, L10, Bild.ATTACK));

        heroesSet.add(new Hero(new Random().nextInt()*1000, L9, Bild.ATTACK));
        heroesSet.add(new Hero(new Random().nextInt()*1000, L10, Bild.ATTACK));
        heroesSet.add(new Hero(new Random().nextInt()*1000, L10, Bild.ATTACK));
        return heroesSet;
    }

    private static Set<Hero> prepareSecondHeroSet() {
        Set<Hero> heroesSet = new HashSet<>();
        heroesSet.add(new Hero(new Random().nextInt()*1000, L9, Bild.ATTACK));
        heroesSet.add(new Hero(new Random().nextInt()*1000, L13, Bild.ATTACK));

        heroesSet.add(new Hero(new Random().nextInt()*1000, L10, Bild.ATTACK));
        heroesSet.add(new Hero(new Random().nextInt()*1000, L10, Bild.ATTACK));
        return heroesSet;
    }

    private static Level prepareLevelMock(double mockWeight, String mockName) {
        Level mockLevel = Mockito.mock(Level.class);
        Mockito.when(mockLevel.getLevelWeight()).thenReturn(mockWeight);
        Mockito.when(mockLevel.name()).thenReturn(mockName);
        return mockLevel;
    }

    private static List<Integer[]> initExpectedTeamCountSequenceList() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{3, 3});
        list.add(new Integer[]{2, 2});
        return list;
    }

}
