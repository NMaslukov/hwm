package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.dto.RandomizedGroup;
import com.dudoser.dto.RandomizedResult;
import com.dudoser.dto.Team;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class Distributor {

    static final double DELTA = 0.07;
    private static final int INITIAL_TEAM_MEMBER = 3;
    private static final int reversedDirectionSort = 1;
    private static final int linearDirectionSort = -1;

    private final ImmutableSet<Hero> heroes;
    private Set<Hero> distributedHeroes = new HashSet<>();
    private List<RandomizedGroup> distributionResult = new ArrayList<>();

    RandomizedResult randomize(){
        RandomizedResult randomizedLinearResult = processRandom(INITIAL_TEAM_MEMBER,  new ArrayList<>(), linearDirectionSort);

        resetContext();

        RandomizedResult randomizedReversedResult = processRandom(INITIAL_TEAM_MEMBER,  new ArrayList<>(), reversedDirectionSort);

        return getBetterResult(randomizedLinearResult, randomizedReversedResult);
    }

    private RandomizedResult getBetterResult(RandomizedResult firstResult, RandomizedResult secondResult) {
        return firstResult.getNotMatchedHeroes().size() < secondResult.getRandomizedGroups().size() ? firstResult : secondResult;
    }

    private void resetContext() {
        distributedHeroes = new HashSet<>();
        distributionResult = new ArrayList<>();
    }

    private RandomizedResult processRandom(int teamMembersCount, List<Team> unDistributedTeamList, int sortDirection) {
        List<Team> teamList = getAllCombinationsSortedTeams(teamMembersCount, unDistributedTeamList, sortDirection);

        for (Team team : teamList) {
            if(!alreadyDistributed(distributedHeroes, team)) {
                Team appropriateOpponent = findAppropriateOpponent(teamList, team);
                processResult(distributionResult, team, appropriateOpponent);
            }
        }

        teamList.removeIf(current -> !Collections.disjoint(current.getHeroes(), distributedHeroes));

        if(teamMembersCount > 1) processRandom(teamMembersCount - 1, teamList, sortDirection);

        return new RandomizedResult(distributionResult, findNotMatchedHeroes(heroes));
    }

    private List<Team> getAllCombinationsSortedTeams(int teamMembersCount, List<Team> unDistributedTeamList, int sortDirection) {
        List<Team> teamList = getTeamsList(teamMembersCount);
        teamList.addAll(unDistributedTeamList);
        teamList.sort(((a, b) -> sortDirection * Double.compare(a.getWeight(), b.getWeight())));
        return teamList;
    }

    private Set<Hero> findNotMatchedHeroes(ImmutableSet<Hero> heroes) {
        Set<Hero> matched = distributionResult.stream().flatMap(e -> {
            HashSet<Hero> set = new HashSet<>(e.getFirstTeam().getHeroes());
            set.addAll(e.getSecondTeam().getHeroes());
            return set.stream();
        }).collect(Collectors.toSet());

        HashSet<Hero> unMatched = new HashSet<>(heroes);
        unMatched.removeAll(matched);

        return unMatched;
    }

    private void processResult(List<RandomizedGroup> result, Team team, Team appropriateTeam) {
        if(opponentFound(appropriateTeam)) {
            result.add(new RandomizedGroup(team, appropriateTeam));
            distributedHeroes.addAll(team.getHeroes());
            distributedHeroes.addAll(appropriateTeam.getHeroes());
        }
    }

    private boolean opponentFound(Team set){
        return set != null;
    }

    private Team findAppropriateOpponent(List<Team> allHeroes, Team targetTeam) {
        for (Team iteratedTeam: allHeroes) {
            if(Collections.disjoint(iteratedTeam.getHeroes(), distributedHeroes)
                    && !iteratedTeam.equals(targetTeam)
                    && Collections.disjoint(iteratedTeam.getHeroes(), targetTeam.getHeroes())
                    && isBalancedOpponents(iteratedTeam, targetTeam)
                    && isNotDuel(targetTeam, iteratedTeam)){
                return iteratedTeam;
            }
        }
        return null;
    }

    private boolean isNotDuel(Team targetTeam, Team iteratedTeam) {
        return iteratedTeam.getHeroes().size() != 1 || targetTeam.getHeroes().size() != 1;
    }

    static boolean isBalancedOpponents(Team firstTeam, Team secondTeam) {
        return Math.abs(firstTeam.getWeight() - secondTeam.getWeight()) < DELTA * Math.min(firstTeam.getWeight(), secondTeam.getWeight());
    }

    private boolean alreadyDistributed(Set<Hero> distributedHeroes, Team team) {
        return !Collections.disjoint(distributedHeroes, team.getHeroes());
    }

    private List<Team> getTeamsList(int teamMembersCount) {
        Set<Set<Hero>> combinations = Sets.combinations(heroes, teamMembersCount);
        return combinations.stream().
                map(e -> new Team(e, e.stream().mapToDouble(Hero::getWeight).sum()))
                .collect(Collectors.toList());
    }
}
