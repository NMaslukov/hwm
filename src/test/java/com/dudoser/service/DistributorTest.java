package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.dto.RandomizedGroup;
import com.dudoser.dto.RandomizedResult;
import com.dudoser.enums.Bild;
import com.dudoser.enums.Level;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

public class DistributorTest {

    @Test
    public void randomizeFirst() {
        Distributor distributor = new Distributor(getFirstHeroesTestList());
        RandomizedResult randomizedResult = distributor.randomize();
        List<RandomizedGroup> result = randomizedResult.getRandomizedGroups();

        assertEquals(1, result.size());
        assertEquals(0, randomizedResult.getNotMatchedHeroes().size());
        RandomizedGroup randomizedGroup = result.get(0);

        List<Hero> firstGroup = new ArrayList<>(randomizedGroup.getFirstTeam().getHeroes());
        List<Hero> secondGroup = new ArrayList<>(randomizedGroup.getSecondTeam().getHeroes());

        assertEquals(3, firstGroup.size());
        assertEquals(3, secondGroup.size());

        assert (firstGroup.get(0).getLevel() != firstGroup.get(1).getLevel() || firstGroup.get(0).getLevel() != firstGroup.get(2).getLevel());
        assertTrue(Distributor.isBalancedOpponents(randomizedGroup.getFirstTeam(), randomizedGroup.getSecondTeam()));
        assertTrue(Collections.disjoint(randomizedGroup.getFirstTeam().getHeroes(), randomizedGroup.getSecondTeam().getHeroes()));
    }

    private ImmutableSet<Hero> getFirstHeroesTestList(){
        Set<Hero> set = new HashSet<>();

        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));

        return ImmutableSet.copyOf(set);
    }

    @Test
    public void randomizeSecond() {
        Distributor distributor = new Distributor(getSecondHeroesTestList());
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

    private ImmutableSet<Hero> getSecondHeroesTestList(){
        Set<Hero> set = new HashSet<>();

        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));

        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));

        return ImmutableSet.copyOf(set);
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
        set.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));

        set.add(new Hero(new Random().nextInt()*1000, Level.ELEVEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.TWELVE, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.FIFTEEN, Bild.ATTACK));

        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));

        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));


        return ImmutableSet.copyOf(set);
    }

    @Test
    public void disjoinTest(){
        Set<Hero> a = new HashSet<>();
        Set<Hero> b = new HashSet<>();
        Hero h = new Hero(1, Level.NINTH, Bild.ATTACK);
        a.add(h);
        b.add(h);

        assertFalse(Collections.disjoint(a,b));
    }
}
