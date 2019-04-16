package com.dudoser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class Team {
    private Set<Hero> heroes;
    private double weight;
}
