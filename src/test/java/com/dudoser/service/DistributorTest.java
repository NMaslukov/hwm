package com.dudoser.service;

import com.dudoser.enums.Level;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class DistributorTest {

    @Test
    public void a(){
        Level l1 = Mockito.mock(Level.class);
        Level l2 = Mockito.mock(Level.class);
        Level l3 = Mockito.mock(Level.class);

        Level l4 = Mockito.mock(Level.class);
        Level l5 = Mockito.mock(Level.class);
        Level l6 = Mockito.mock(Level.class);

        Mockito.when(l1.getLevelWeight()).thenReturn(1.0);

    }
}
