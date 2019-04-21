package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.dto.RandomizedResult;
import com.google.common.collect.ImmutableSet;

public class Distributo_lala_Test {
    private final ImmutableSet<Hero> testList;

    public Distributo_lala_Test(ImmutableSet<Hero> testList) {
        this.testList = testList;
    }

    private RandomizedResult getRandomizedResult() {
        Distributor distributor = new Distributor(testList);
        return distributor.randomize();
    }

}
