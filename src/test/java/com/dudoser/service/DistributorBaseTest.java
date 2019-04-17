package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.dto.RandomizedGroup;
import com.dudoser.dto.RandomizedResult;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DistributorBaseTest {

    final RandomizedResult randomizedResult;
    private final ImmutableSet<Hero> testList;
    private final int undistributedHeroesAmount;
    private final int expectedGroupAmount;

    DistributorBaseTest(ImmutableSet<Hero> testList, int undistributedHeroesAmount, int expectedGroupAmount){
        this.testList = testList;
        this.undistributedHeroesAmount = undistributedHeroesAmount;
        this.expectedGroupAmount = expectedGroupAmount;
        randomizedResult = getRandomizedResult();
    }

    @Test
    public void undistributedHeroesTest(){
        assertEquals(undistributedHeroesAmount, randomizedResult.getNotMatchedHeroes().size());
    }

    @Test
    public void randomizeGroupCountPriorityFirstTest(){
        assertGroupsCount(expectedGroupAmount, randomizedResult.getRandomizedGroups());
    }

    private void assertGroupsCount(int count, List<RandomizedGroup> randomizedGroups) {
        assertEquals(count, randomizedGroups.size());
    }

    @Test
    public void randomizeResultNotContainsDuplicatesHeroFirstTest(){
        getRandomizedResult().getRandomizedGroups().forEach(e -> assertTrue(Collections.disjoint(e.getFirstTeam().getHeroes(), e.getSecondTeam().getHeroes())));
    }

    @Test
    public void randomizeBalancedFirstTest() {
        getRandomizedResult().getRandomizedGroups().forEach(e -> {
                    assertTrue(Distributor.isBalancedOpponents(e.getFirstTeam(), e.getSecondTeam()));
                }
        );
    }

    private RandomizedResult getRandomizedResult() {
        Distributor distributor = new Distributor(testList);
        return distributor.randomize();
    }
}
