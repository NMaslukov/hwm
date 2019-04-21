package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.dto.Team;
import com.dudoser.enums.Bild;
import com.dudoser.enums.Level;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class Distributor_12_vs_10_9_9_Test extends DistributorBaseTest {

    private static final List<Integer[]> expectedTeamCountSequence = initExpectedTeamCountSequenceList();
    private static Set<Hero> heroes = prepareResultHeroSet();

    public Distributor_12_vs_10_9_9_Test() {
        super(ImmutableSet.copyOf(heroes), 0, 1, expectedTeamCountSequence);

    }

    private static Set<Hero> prepareResultHeroSet() {

        return new HashSet<>(prepareHeroSet());
    }

    private static Set<Hero> prepareHeroSet() {
        Set<Hero> heroesSet = new HashSet<>();
        heroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        heroesSet.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        heroesSet.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        heroesSet.add(new Hero(new Random().nextInt()*1000, Level.TWELVE, Bild.ATTACK));
        return heroesSet;
    }

    private static List<Integer[]> initExpectedTeamCountSequenceList() {
        List<Integer[]> expectedTeamCountSequence = new ArrayList<>();
        expectedTeamCountSequence.add(new Integer[]{1, 3});

        return expectedTeamCountSequence;
    }
}
