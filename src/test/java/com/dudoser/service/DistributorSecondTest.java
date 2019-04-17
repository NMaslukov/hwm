package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.dto.RandomizedGroup;
import com.dudoser.dto.RandomizedResult;
import com.dudoser.enums.Bild;
import com.dudoser.enums.Level;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

public class DistributorSecondTest {

    private static int TEST_HEROES_LIST_SIZE = 12;
    private static ImmutableSet<Hero> testList;
    private static Set<Hero> firstHeroesSet;
    private static Set<Hero> secondHeroesSet;
    
    private static Set<Hero> thirdHeroesSet;
    private static Set<Hero> fourthHeroesSet;

    static {
        initFirstSet();
        initSecondSet();
        initThirdSet();
        initFourthSet();
        initResultSet();

        assertEquals(TEST_HEROES_LIST_SIZE, testList.size());
    }

    @Test
    public void randomizeBalancedSecond() {
        Distributor distributor = new Distributor(testList);
        RandomizedResult randomizedResult = distributor.randomize();
        List<RandomizedGroup> result = randomizedResult.getRandomizedGroups();

        assertEquals(2, result.size());
        assertEquals(0, randomizedResult.getNotMatchedHeroes().size());

        RandomizedGroup firstRandomizedGroup = result.get(0);
        Set<Hero> firstGroupSet = new HashSet<>(firstRandomizedGroup.getFirstTeam().getHeroes());
        firstGroupSet.addAll(firstRandomizedGroup.getSecondTeam().getHeroes());

        RandomizedGroup secondRandomizedGroup = result.get(1);
        Set<Hero> secondGroupSet = new HashSet<>(secondRandomizedGroup.getFirstTeam().getHeroes());
        secondGroupSet.addAll(secondRandomizedGroup.getSecondTeam().getHeroes());

        assertTrue("Dublicates heroes in teams!", Collections.disjoint(firstGroupSet, secondGroupSet));
        for (RandomizedGroup randomizedGroup : result) {
            assertTrue(Distributor.isBalancedOpponents(randomizedGroup.getFirstTeam(), randomizedGroup.getSecondTeam()));
        }
    }

    @Test
    public void randomizeThird() {
        Distributor distributor = new Distributor(getThirdHeroesTestList());
        RandomizedResult randomizedResult = distributor.randomize();
        List<RandomizedGroup> result = randomizedResult.getRandomizedGroups();
        System.out.println(result);
    }

    private ImmutableSet<Hero> getThirdHeroesTestList() {
        Set<Hero> set = new HashSet<>();

        set.add(new Hero(new Random().nextInt()*1000, Level.SIXTEEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));

        set.add(new Hero(new Random().nextInt()*1000, Level.ELEVEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.TWELVE, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.FIFTEEN, Bild.ATTACK));

        set.add(new Hero(new Random().nextInt()*1000, Level.SIXTEEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.FIFTEEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.FIFTEEN, Bild.ATTACK));


        return ImmutableSet.copyOf(set);
    }


    private static void initFirstSet() {
        firstHeroesSet = new HashSet<>();
        firstHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        firstHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        firstHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
    }

    private static void initSecondSet() {
        secondHeroesSet = new HashSet<>();
        secondHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        secondHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        secondHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
    }

    private static void initThirdSet() {
        thirdHeroesSet = new HashSet<>();
        thirdHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        thirdHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        thirdHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
    }

    private static void initFourthSet() {
        fourthHeroesSet = new HashSet<>();
        fourthHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        fourthHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        fourthHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
    }

    private static void initResultSet() {
        Set<Hero> resultSet = new HashSet<>();
        resultSet.addAll(firstHeroesSet);
        resultSet.addAll(secondHeroesSet);
        resultSet.addAll(thirdHeroesSet);
        resultSet.addAll(fourthHeroesSet);
        testList = ImmutableSet.copyOf(resultSet);
    }
}
