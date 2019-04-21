package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.dto.RandomizedGroup;
import com.dudoser.dto.RandomizedResult;
import com.dudoser.enums.Bild;
import com.dudoser.enums.Level;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Distributo_lala_Test {
    private final ImmutableSet<Hero> testList;

    public Distributo_lala_Test() {
        Set<Hero> set = new HashSet<>();
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.DEF));
        set.add(new Hero(new Random().nextInt()*1000, Level.SIXTEEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.DEF));
        set.add(new Hero(new Random().nextInt()*1000, Level.FIFTEEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.ELEVEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.FOURTEEN, Bild.DEF));
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.SIXTEEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.FIFTEEN, Bild.DEF));
        set.add(new Hero(new Random().nextInt()*1000, Level.ELEVEN, Bild.DEF));
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.DEF));
        set.add(new Hero(new Random().nextInt()*1000, Level.THIRTEEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.SIXTEEN, Bild.ATTACK));
        set.add(new Hero(new Random().nextInt()*1000, Level.ELEVEN, Bild.DEF));
        set.add(new Hero(new Random().nextInt()*1000, Level.FOURTEEN, Bild.ATTACK));

        testList = ImmutableSet.copyOf(set);
    }

    private RandomizedResult getRandomizedResult() {
        Distributor distributor = new Distributor(testList);
        return distributor.randomize();
    }

    @Test
    public void t(){
        RandomizedResult randomizedResult = getRandomizedResult();
        List<RandomizedGroup> randomizedGroups = randomizedResult.getRandomizedGroups();
        for (RandomizedGroup randomizedGroup : randomizedGroups) {
            String collect = randomizedGroup.getFirstTeam().getHeroes().stream().map(e -> e.getLevel().toString()).collect(Collectors.joining(","));
            String collect1 = randomizedGroup.getSecondTeam().getHeroes().stream().map(e -> e.getLevel().toString()).collect(Collectors.joining(","));
            System.out.println(collect
                    + " vs " + collect1);

        }

        System.out.println("Не распределено героев: " + randomizedResult.getNotMatchedHeroes().size());
    }

}
