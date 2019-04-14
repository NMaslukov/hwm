package com.dudoser.dto;

import com.dudoser.enums.Bild;
import com.dudoser.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Hero {
    private int id;
    private Level level;
    private Bild bild;

    public double getWeight(){
        return level.getLevelWeight();
    }
}
