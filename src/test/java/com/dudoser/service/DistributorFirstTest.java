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

public class DistributorFirstTest extends DistributorBaseTest {

    private static Set<Hero> heroes = prepareResultHeroSet();

    public DistributorFirstTest() {
        super(ImmutableSet.copyOf(heroes), 0, heroes.size() / (Distributor.INITIAL_TEAM_MEMBER * 2));

    }

    private static Set<Hero> prepareResultHeroSet() {
        Level l9 = prepareLevelMock(1.0, Level.NINTH.name());
        Level l10 = prepareLevelMock(10.0, Level.TEN.name());

        Set<Hero> resultSet = new HashSet<>();
        resultSet.addAll(prepareHeroSet(l9, l10));
        resultSet.addAll(prepareHeroSet(l9, l10));
        return resultSet;
    }

    private static Set<Hero> prepareHeroSet(Level l9, Level l10) {
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
        assertTrue(Distributor.isBalancedOpponents(new Team(prepareHeroSet(Level.NINTH, Level.NINTH), weight),
                new Team(prepareHeroSet(Level.NINTH, Level.NINTH), weight)));
    }

    @Test
    public void randomizeTeamHeroesCountTest(){
        randomizedResult.getRandomizedGroups().forEach(e -> {

            List<Hero> firstGroup = new ArrayList<>(e.getFirstTeam().getHeroes());
            List<Hero> secondGroup = new ArrayList<>(e.getSecondTeam().getHeroes());

            int teamSize = heroes.size() / 2;
            assertTrue(firstGroup.size() == teamSize && secondGroup.size() == teamSize);
        });
    }
}
