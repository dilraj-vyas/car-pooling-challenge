package com.cabify.carpooling.service;

import java.util.List;
import java.util.Optional;

import com.cabify.carpooling.model.Car;
import com.cabify.carpooling.model.Journey;

public interface CarPoolingService {

    public void resetCars(List<Car> cars);

    public void newJourney(Journey journey);

    public void reAssignCar(Car car);


    public Car locate(int journeyId);

    public Optional<Car> findCar(int seats);

    public Car dropOff(int journeyId);
}
