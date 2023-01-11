package com.cabify.carpooling.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cabify.carpooling.model.Car;
import com.cabify.carpooling.model.Journey;
import com.cabify.carpooling.service.CarPoolingService;

@RestController
@RequestMapping("/*")
public class CarPoolingController {

    @Autowired
    private CarPoolingService carService;

    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public void getStatus() {
    }

    @PutMapping(value = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void putCars(@RequestBody List<Car> cars) {
        carService.resetCars(cars);
    }

    @PostMapping("/journey")
    public ResponseEntity<Void> postJourney(@RequestBody Journey journey) {
        if (journey.getId() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        carService.newJourney(journey);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/dropoff",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = "application/json")
    public ResponseEntity<Void> postDropoff(@RequestParam("ID") int journeyID) {
        if (journeyID <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Car car = carService.dropOff(journeyID);
            if (car != null) {
                carService.reAssignCar(car);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(
        value = "/locate",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<Car> postLocate(@RequestParam("ID") final int journeyID) {
        if (journeyID <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Car car = carService.locate(journeyID);
            if (car == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
