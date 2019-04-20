package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.dto.RandomizedGroup;
import com.dudoser.dto.RandomizedResult;
import com.google.common.collect.ImmutableSet;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

@Ignore
public class DistributorBaseTest {

    private final RandomizedResult randomizedResult;
    private final ImmutableSet<Hero> testList;
    private final int undistributedHeroesAmount;
    private final int expectedGroupAmount;
    private final List<Integer[]> expectedTeamCountSequence;

    DistributorBaseTest(ImmutableSet<Hero> testHeroes, int undistributedHeroesAmount, int expectedGroupAmount, List<Integer[]> expectedTeamCountSequence){
        this.testList = testHeroes;
        this.undistributedHeroesAmount = undistributedHeroesAmount;
        this.expectedGroupAmount = expectedGroupAmount;
        this.expectedTeamCountSequence = expectedTeamCountSequence;
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

    @Test
    public void randomizeTeamHeroesCountTest(){
        List<RandomizedGroup> randomizedGroups = randomizedResult.getRandomizedGroups();
        for (int i = 0; i < randomizedGroups.size(); i++) {

            List<Integer> actual = new ArrayList<>();
            actual.add(randomizedGroups.get(i).getFirstTeam().getHeroes().size());
            actual.add(randomizedGroups.get(i).getSecondTeam().getHeroes().size());

            assertThat("List equality without order",
                    actual, containsInAnyOrder(expectedTeamCountSequence.get(i)));
        }
    }

    private RandomizedResult getRandomizedResult() {
        Distributor distributor = new Distributor(testList);
        return distributor.randomize();
    }
}
