package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.dto.RandomizedGroup;
import com.dudoser.dto.RandomizedResult;
import com.dudoser.dto.Team;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DistributorFirstTest {

    private static int TEST_HEROES_LIST_SIZE = 6;
    private final static ImmutableSet<Hero> testList;
    private final static Set<Hero> firstHeroesSet;
    private final static Set<Hero> secondHeroesSet;

    static {
        firstHeroesSet = new HashSet<>();
        firstHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        firstHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        firstHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));

        secondHeroesSet = new HashSet<>();
        secondHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        secondHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        secondHeroesSet.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));

        Set<Hero> resultSet = new HashSet<>();
        resultSet.addAll(firstHeroesSet);
        resultSet.addAll(secondHeroesSet);

        assertEquals(TEST_HEROES_LIST_SIZE, resultSet.size());
        testList = ImmutableSet.copyOf(resultSet);
    }

    @Test
    public void randomizeFullDistributionTest(){
        RandomizedResult randomizedResult = getRandomizedResult();
        assertAllHeroesDistributed(randomizedResult);
    }

    private void assertAllHeroesDistributed(RandomizedResult randomizedResult) {
        assertEquals(0, randomizedResult.getNotMatchedHeroes().size());
    }

    @Test
    public void randomizeGroupCountPriorityFirstTest(){
        RandomizedResult randomizedResult = getRandomizedResult();

        assertGroupsCount(TEST_HEROES_LIST_SIZE / (Distributor.INITIAL_TEAM_MEMBER * 2), randomizedResult.getRandomizedGroups());
    }

    private void assertGroupsCount(int count, List<RandomizedGroup> randomizedGroups) {
        assertEquals(count, randomizedGroups.size());
    }

    @Test
    public void randomizeTeamHeroesCountTest(){
        RandomizedGroup randomizedGroup = getRandomizedResult().getRandomizedGroups().get(0);

        List<Hero> firstGroup = new ArrayList<>(randomizedGroup.getFirstTeam().getHeroes());
        List<Hero> secondGroup = new ArrayList<>(randomizedGroup.getSecondTeam().getHeroes());

        assertEquals(Distributor.INITIAL_TEAM_MEMBER, firstGroup.size());
        assertEquals(Distributor.INITIAL_TEAM_MEMBER, secondGroup.size());
    }

    @Test
    public void randomizeResultNotContainsDuplicatesHeroFirstTest(){
        RandomizedGroup randomizedGroup = getRandomizedResult().getRandomizedGroups().get(0);
        assertTrue(Collections.disjoint(randomizedGroup.getFirstTeam().getHeroes(), randomizedGroup.getSecondTeam().getHeroes()));
    }

    @Test
    public void isBalancedOpponents() {
        double weight = 10;
        assertTrue(Distributor.isBalancedOpponents(new Team(firstHeroesSet, weight), new Team(secondHeroesSet, weight)));
    }

    @Test
    public void randomizeBalancedFirstTest() {
        RandomizedGroup randomizedGroup = getRandomizedResult().getRandomizedGroups().get(0);

        assertTrue(Distributor.isBalancedOpponents(randomizedGroup.getFirstTeam(), randomizedGroup.getSecondTeam()));
    }


    private RandomizedResult getRandomizedResult() {
        Distributor distributor = new Distributor(testList);
        return distributor.randomize();
    }
}
