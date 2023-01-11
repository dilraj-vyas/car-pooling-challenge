package com.cabify.carpooling.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

import org.springframework.stereotype.Service;

import com.cabify.carpooling.custom.link.list.SortedLinkedList;
import com.cabify.carpooling.model.Car;
import com.cabify.carpooling.model.Journey;
import com.cabify.carpooling.service.CarPoolingService;

@Service
public class CarPoolingServiceImpl implements CarPoolingService {


    private List<Car> availableCars;
    private Queue<Journey> waitingGroups;
    private Map<Integer, Journey> trackGroup;

    private List<Journey> waitingGroupsRemove;

    @Override
    public void resetCars(List<Car> cars) {
        waitingGroupsRemove = new ArrayList<>();
        this.availableCars = new SortedLinkedList();
        this.waitingGroups = new PriorityQueue<Journey>();
        this.trackGroup = new HashMap<>();
        for (Car car : cars) {
            if (car.getSeats() >= 4 && car.getSeats() <= 6) {
                this.availableCars.add(car);
            } else {
                throw new IllegalArgumentException("Invalid seats");
            }
        }
    }

    @Override
    public void newJourney(Journey journey) {

        if (this.trackGroup.get(journey.getId()) == null) {
            Optional<Car> car = availableCars.stream().filter(c -> c.getSeats() >= journey.getPeople()).findFirst();
            if (car.isPresent()) {
                Car car1 = car.get();
                car1.setSeats(car1.getSeats() - journey.getPeople());
                journey.setAssignedTo(new Car(car1.getId(), journey.getPeople()));
                trackGroup.put(journey.getId(), journey);
                waitingGroups.remove(journey);
            } else {
                // If no car is available, add the group to the waiting queue
                waitingGroups.add(journey);
                trackGroup.put(journey.getId(), journey);
            }
        }
    }

    @Override
    public void reAssignCar(Car car) {
        for (Car car1 : availableCars) {
            if (car1.getId() == car.getId()) {
                car1.setSeats(car1.getSeats() + car.getSeats());
            }
        }
        // Check if any waiting groups can be served
        checkWaitingGroups();
    }

    @Override
    public Car locate(int journeyId) {
        Journey journey = this.trackGroup.get(journeyId);
        if (journey != null) {
            if (journey.getAssignedTo() != null) {
                return journey.getAssignedTo();
            } else {
                return null;
            }
        } else {
            throw new NoSuchElementException("journey not found");
        }
    }

    @Override
    public Optional<Car> findCar(int seats) {
        return Optional.empty();
    }

    @Override
    public Car dropOff(int journeyId) {
        Car car = null;
        Journey journey = this.trackGroup.get(journeyId);
        if (journey != null) {
            car = journey.getAssignedTo();
            this.trackGroup.remove(journeyId);
            reAssignCar(journey.getAssignedTo());
        }
        return car;
    }


    private void checkWaitingGroups() {
        // Iterate through the waiting groups and assign a car if possible
        waitingGroups.removeIf(journey -> {
            if (availableCars.stream().anyMatch(car -> car.getSeats() >= journey.getPeople())) {
                Car car = availableCars.stream().filter(c -> c.getSeats() >= journey.getPeople()).findFirst().orElseThrow(IllegalStateException::new);
                car.setSeats(car.getSeats() - journey.getPeople());
                journey.setAssignedTo(new Car(car.getId(), journey.getPeople()));
                trackGroup.put(journey.getId(), journey);
                return true;
            }
            return false;
        });
    }
}
