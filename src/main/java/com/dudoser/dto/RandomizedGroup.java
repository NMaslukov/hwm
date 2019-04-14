package com.dudoser.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class RandomizedGroup {
    private Set<Hero> firstGroup;
    private Set<Hero> secondGroup;
}
