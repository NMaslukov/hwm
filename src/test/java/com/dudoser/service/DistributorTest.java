package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.dto.RandomizedGroup;
import com.dudoser.enums.Bild;
import com.dudoser.enums.Level;
import com.google.common.collect.ImmutableList;
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
        Distributor distributor = new Distributor();
        List<RandomizedGroup> result = distributor.randomize(getFirstHeroesTestList());

        assertEquals(1, result.size());

        List<Hero> firstGroup = new ArrayList<>(result.get(0).getFirstGroup());
        List<Hero> secondGroup = new ArrayList<>(result.get(0).getSecondGroup());

        assertEquals(3, firstGroup.size());
        assertEquals(3, secondGroup.size());

        assert (firstGroup.get(0).getLevel() != firstGroup.get(1).getLevel() || firstGroup.get(0).getLevel() != firstGroup.get(2).getLevel());
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
        Distributor distributor = new Distributor();
        List<RandomizedGroup> result = distributor.randomize(getSecondHeroesTestList());

        assertEquals(2, result.size());

        RandomizedGroup firstRandomizedGroup = result.get(0);
        Set<Hero> firstGroupSet = new HashSet<>(firstRandomizedGroup.getFirstGroup());
        firstGroupSet.addAll(firstRandomizedGroup.getSecondGroup());

        RandomizedGroup secondRandomizedGroup = result.get(1);
        Set<Hero> secondGroupSet = new HashSet<>(secondRandomizedGroup.getFirstGroup());
        secondGroupSet.addAll(secondRandomizedGroup.getSecondGroup());

        assertFalse(Collections.disjoint(firstGroupSet, secondGroupSet));
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
    public void disjoinTest(){
        Set<Hero> a = new HashSet<>();
        Set<Hero> b = new HashSet<>();
        Hero h = new Hero(1, Level.NINTH, Bild.ATTACK);
        a.add(h);
        b.add(h);

        assertFalse(Collections.disjoint(a,b));
    }
}
