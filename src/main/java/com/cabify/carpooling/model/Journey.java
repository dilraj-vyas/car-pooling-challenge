package com.cabify.carpooling.model;

import lombok.Data;

@Data
public class Journey implements Comparable<Journey> {
    private int id;
    private int people;
    private Car assignedTo;

    public Journey(int id, int people) {
        this.id = id;
        this.people = people;
        this.assignedTo = null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Journey)) {
            return false;
        }
        Journey journey = (Journey) o;
        return journey.id == this.id;
    }

    @Override
    public int compareTo(Journey other) {
        // Compare cars based on their price
        return this.id - other.id;
    }

}
