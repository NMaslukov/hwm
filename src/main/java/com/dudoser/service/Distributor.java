package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.dto.RandomizedGroup;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Distributor {

    private static final int AVERAGE_TEAM_MEMBER = 3;
    private static final double DELTA = 1;

    List<RandomizedGroup> randomize(ImmutableSet<Hero> heroes){
        double averageGroupWeight = calculateAverageGroupWeight(heroes);
        log("averageGroupWeight: " + averageGroupWeight);

        List<Pair<Double, Set<Hero>>> weightTeamPairList = getWeightTeamPairList(heroes);
        Set<Hero> distributedHeroes = new HashSet<>();
        List<RandomizedGroup> result = new ArrayList<>();
        Set<Hero> notMatched = new HashSet<>();

        for (Pair<Double, Set<Hero>> pair : weightTeamPairList) {
            if(!alreadyDistributed(distributedHeroes, pair)) {
                Set<Hero> appropriateOpponent = findAppropriateOpponent(weightTeamPairList, distributedHeroes, pair);
                processResult(result, notMatched, pair, appropriateOpponent);
            }
        }

        return result;
    }

    private void processResult(List<RandomizedGroup> result, Set<Hero> notMatched, Pair<Double, Set<Hero>> pair, Set<Hero> appropriateOpponent) {
        if(opponentNotFound(appropriateOpponent)){
            notMatched.addAll(pair.getValue());
        } else {
            result.add(new RandomizedGroup(pair.getValue(), appropriateOpponent));
        }
    }

    private boolean opponentNotFound(Set<Hero> set){
        return set == null;
    }

    private Set<Hero> findAppropriateOpponent(List<Pair<Double, Set<Hero>>> allHeroes, Set<Hero> distributedHeroes, Pair<Double, Set<Hero>> targetHeroes) {
        for (Pair<Double, Set<Hero>> group: allHeroes) {
            if(!group.equals(targetHeroes) && Collections.disjoint(group.getValue(), targetHeroes.getValue())  && Math.abs(group.getKey() - targetHeroes.getKey()) < DELTA){
                distributedHeroes.addAll(group.getValue()); //добавляем в Distributed тут
                distributedHeroes.addAll(targetHeroes.getValue());
                return group.getValue();
            }
        }
        return null;
    }
    //TODO extract distributedHeroes to class level
    private boolean alreadyDistributed(Set<Hero> distributedHeroes, Pair<Double, Set<Hero>> pair) {
        return !Collections.disjoint(distributedHeroes, pair.getValue());
    }

    private List<Pair<Double, Set<Hero>>> getWeightTeamPairList(ImmutableSet<Hero> heroes) {
        Set<Set<Hero>> combinations = Sets.combinations(heroes, AVERAGE_TEAM_MEMBER);
        List<Pair<Double, Set<Hero>>> weightTeamPair = combinations.stream().
                map(a -> new Pair<>(a.stream().mapToDouble(Hero::getWeight).sum(), a))
                .sorted((a,b) -> (-1) * Double.compare(a.getKey(), b.getKey()))
                .collect(Collectors.toList());
        return weightTeamPair;
    }


    private List<Set<Hero>> getTempo(double averageGroupWeight, List<Pair<Double, Set<Hero>>> weightTeamPair) {
        List<Set<Hero>> tempo = new ArrayList<>();
        for (Pair<Double, Set<Hero>> team : weightTeamPair) {
            if (Math.abs(team.getKey() - averageGroupWeight) < DELTA) tempo.add(team.getValue());
        }
        return tempo;
    }

    private double calculateAverageGroupWeight(ImmutableSet<Hero> heroes) {
        return heroes.stream().mapToDouble(Hero::getWeight).sum() / AVERAGE_TEAM_MEMBER;
    }

    private Map<Double, Hero> toWeightHeroMap(List<Hero> heroes) {
        return heroes.stream().collect(Collectors.toMap(Hero::getWeight, a -> a));
    }










    private void log(String s) {
        System.out.println(s);
    }
}
