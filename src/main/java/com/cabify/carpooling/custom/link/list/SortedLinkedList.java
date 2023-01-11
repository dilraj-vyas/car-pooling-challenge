package com.cabify.carpooling.custom.link.list;

import java.util.LinkedList;

import com.cabify.carpooling.model.Car;

public class SortedLinkedList extends LinkedList<Car> {

    @Override
    public boolean add(Car car) {
        // Find the correct position to insert the element
        int index = 0;
        for (Car c : this) {
            if (c.getSeats() > car.getSeats()) {
                break;
            }
            index++;
        }
        super.add(index, car);
        return true;
    }
}
