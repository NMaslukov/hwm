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

public class DistributorTest_3 extends DistributorBaseTest {

    private static final Level l9 = prepareLevelMock(1.0, Level.NINTH.name());
    private static final Level l10 = prepareLevelMock(10.0, Level.TEN.name());
    private static final List<Integer[]> expectedTeamCountSequence = initExpectedTeamCountSequenceList();
    private static Set<Hero> heroes = prepareResultHeroSet();

    public DistributorTest_3() {
        super(ImmutableSet.copyOf(heroes), 0, 1, expectedTeamCountSequence);

    }

    private static Set<Hero> prepareResultHeroSet() {

        Set<Hero> resultSet = new HashSet<>();
        resultSet.addAll(prepareHeroSet());
        resultSet.addAll(prepareHeroSet());
        return resultSet;
    }

    private static Set<Hero> prepareHeroSet() {
        Set<Hero> heroesSet = new HashSet<>();
        heroesSet.add(new Hero(new Random().nextInt()*1000, l9, Bild.ATTACK));
        heroesSet.add(new Hero(new Random().nextInt()*1000, l10, Bild.ATTACK));
        heroesSet.add(new Hero(new Random().nextInt()*1000, l10, Bild.ATTACK));
        return heroesSet;
    }

    private static Level prepareLevelMock(double mockWeight, String mockName) {
        Level l9 = Mockito.mock(Level.class);
        Mockito.when(l9.getLevelWeight()).thenReturn(mockWeight);
        Mockito.when(l9.name()).thenReturn(mockName);
        return l9;
    }

    @Test
    public void isBalancedOpponents() {
        double weight = Distributor.DELTA;
        assertTrue(Distributor.isBalancedOpponents(new Team(prepareHeroSet(), weight),
                new Team(prepareHeroSet(), weight)));
    }

    private static List<Integer[]> initExpectedTeamCountSequenceList() {
        List<Integer[]> expectedTeamCountSequence = new ArrayList<>();
        expectedTeamCountSequence.add(new Integer[]{3, 3});
        expectedTeamCountSequence.add(new Integer[]{3, 3});

        return expectedTeamCountSequence;
    }
}
