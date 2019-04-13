package com.dudoser.service;

import com.dudoser.dto.Hero;
import com.dudoser.dto.RandomizedGroup;
import com.dudoser.enums.Bild;
import com.dudoser.enums.Level;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class DistributorTest {

    @Test
    public void randomize() {
        Distributor distributor = new Distributor();
        List<RandomizedGroup> result = distributor.randomize(getFirstHeroesTestList());

        assertEquals(1, result.size());

        List<Hero> firstGroup = result.get(0).getFirstGroup();
        List<Hero> secondGroup = result.get(0).getSecondGroup();

        assertEquals(2, firstGroup.size());
        assertEquals(2, secondGroup.size());

        assert firstGroup.get(0).getLevel() != firstGroup.get(0).getLevel();
        assert secondGroup.get(0).getLevel() != secondGroup.get(0).getLevel();
    }

    private List<Hero> getFirstHeroesTestList(){
        List<Hero> list = new ArrayList<>();

        list.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        list.add(new Hero(new Random().nextInt()*1000, Level.NINTH, Bild.ATTACK));
        list.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));
        list.add(new Hero(new Random().nextInt()*1000, Level.TEN, Bild.ATTACK));

        return list;
    }

}
