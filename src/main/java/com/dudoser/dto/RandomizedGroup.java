package com.dudoser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class RandomizedGroup {
    private Set<Hero> firstGroup;
    private Set<Hero> secondGroup;
}
