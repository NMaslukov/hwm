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

    public static final int INITIAL_TEAM_MEMBER = 3;
    public static final double DELTA = 2;

    private Set<Hero> distributedHeroes = new HashSet<>();
    private List<RandomizedGroup> resultDistribution = new ArrayList<>();
    private final ImmutableSet<Hero> heroes;

    RandomizedResult randomize(){
        return processRandom(INITIAL_TEAM_MEMBER, new ArrayList<>());
    }

    private RandomizedResult processRandom(int teamMembersCount, List<Team> unDistributedTeamList) {
        List<Team> teamList = getAllCombinationsSortedList(teamMembersCount, unDistributedTeamList);

        for (Team team : teamList) {
            if(!alreadyDistributed(distributedHeroes, team)) {
                Team appropriateOpponent = findAppropriateOpponent(teamList, team);
                processResult(resultDistribution, team, appropriateOpponent);
            }
        }

        teamList.removeIf(current -> !Collections.disjoint(current.getHeroes(), distributedHeroes));

        if(teamMembersCount > 1) processRandom(teamMembersCount - 1, teamList);

        return new RandomizedResult(resultDistribution, findNotMatchedHeroes(heroes));
    }

    private List<Team> getAllCombinationsSortedList(int teamMembersCount, List<Team> unDistributedTeamList) {
        List<Team> teamList = getTeamsList(teamMembersCount);
        teamList.addAll(unDistributedTeamList);
        teamList.sort(((a, b) -> (-1) * Double.compare(a.getWeight(), b.getWeight())));
        return teamList;
    }

    private Set<Hero> findNotMatchedHeroes(ImmutableSet<Hero> heroes) {
        Set<Hero> matched = resultDistribution.stream().flatMap(e -> {
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
        return Math.abs(firstTeam.getWeight() - secondTeam.getWeight()) < DELTA;
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
