package com.cabify.carpooling.model;

import lombok.Builder;
import lombok.Data;

@Data
public class Car {
    private int id;
    private int seats;

    public Car(int id, int seats) {
        this.id = id;
        this.seats = seats;
    }


}
