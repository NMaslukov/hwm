package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.enums.Bild;
import com.dudoser.enums.Level;
import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DistributorTest_2 extends DistributorBaseTest {

    private static final List<Integer[]> expectedTeamCountSequence = initExpectedTeamCountSequenceList();
    private static ImmutableSet<Hero> testList = initResultSet();

    public DistributorTest_2() {
        super(testList, 0, 2, expectedTeamCountSequence);
    }

    private static ImmutableSet<Hero> initResultSet() {
        Set<Hero> resultSet = new HashSet<>();
        resultSet.addAll(getFirstHeroesSet());
        resultSet.addAll(getSecondHeroesSet());
        resultSet.addAll(getThirdHeroesSet());
        resultSet.addAll(getFourthHeroesSet());

        return ImmutableSet.copyOf(resultSet);
    }

    private static Set<Hero> getFirstHeroesSet() {
        Set<Hero> firstHeroesSet = new HashSet<>();
        firstHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        firstHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        firstHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        return firstHeroesSet;
    }

    private static Set<Hero> getSecondHeroesSet() {
        Set<Hero> secondHeroesSet = new HashSet<>();
        secondHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        secondHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        secondHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        return secondHeroesSet;
    }

    private static Set<Hero> getThirdHeroesSet() {
        Set<Hero> thirdHeroesSet = new HashSet<>();
        thirdHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        thirdHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        thirdHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        return thirdHeroesSet;
    }

    private static Set<Hero> getFourthHeroesSet() {
        Set<Hero> fourthHeroesSet = new HashSet<>();
        fourthHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        fourthHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        fourthHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        return fourthHeroesSet;
    }

    private static List<Integer[]> initExpectedTeamCountSequenceList() {
        List<Integer[]> expectedTeamCountSequence = new ArrayList<>();
        expectedTeamCountSequence.add(new Integer[]{3, 3});
        expectedTeamCountSequence.add(new Integer[]{3, 3});

        return expectedTeamCountSequence;
    }
}
