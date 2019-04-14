package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.dto.RandomizedGroup;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Distributor {

    private static final int AVERAGE_TEAM_MEMBER = 2;
    private static final double DELTA = 1;

    List<RandomizedGroup> randomize(ImmutableSet<Hero> heroes){


        double averageGroupWeight = calculateAverageGroupWeight(heroes);
        log("averageGroupWeight: " + averageGroupWeight);

        Set<Set<Hero>> combinations = Sets.combinations(heroes, AVERAGE_TEAM_MEMBER);
        List<Pair<Double, Set<Hero>>> weightTeamPair = combinations.stream().
                map(a -> new Pair<>(a.stream().mapToDouble(Hero::getWeight).sum(), a))
                .collect(Collectors.toList());

        List<Set<Hero>> tempo = getTempo(averageGroupWeight, weightTeamPair);

        List<Set<Hero>> forRemoving = new LinkedList<>();
        for (Set<Hero> heroSet : tempo) {
            if(new HashSet<>(forRemoving).contains(heroSet)) continue;
            List<Set<Hero>> dublicates = tempo.stream().filter(e -> e.stream().anyMatch(h -> heroSet.contains(h))).collect(Collectors.toList());
            dublicates.remove(heroSet);
            forRemoving.addAll(dublicates);
        }
        forRemoving = forRemoving.stream().distinct().collect(Collectors.toList());
        tempo.removeAll(forRemoving);
        List<RandomizedGroup> result = calculateResult(tempo);

        return result;
    }

    private List<RandomizedGroup> calculateResult(List<Set<Hero>> tempo) {
        List<RandomizedGroup> result = new ArrayList<>();
        for (int i = 0; i < tempo.size(); i+=2) {
            RandomizedGroup group = new RandomizedGroup();
            group.setFirstGroup(tempo.get(i));
            group.setSecondGroup(tempo.get(i + 1));
            result.add(group);
        }
        return result;
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
