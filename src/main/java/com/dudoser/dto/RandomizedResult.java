package com.dudoser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public class RandomizedResult {
    private List<RandomizedGroup> randomizedGroups;
    private Set<Hero> notMatchedHeroes;
}
