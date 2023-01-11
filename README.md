# Car Pooling Service

## Introduction 

This service implements a Car Pooling service. 

The Car Pooling service implements a very simple API that can be used to track the assignment of cars to journeys according to the seat capacity of the cars and the number of people that will travel in each journey.

People request journeys in groups of 1 to 6. People in the same group want to ride on the same car. You can take any group at any car that has enough empty seats for them, no matter their current location. If it's not possible to accommodate them, they're willing to wait until there is a car available for them. Once a car is available for a group that is waiting, they should ride.

Once they get a car assigned, they will journey until the drop off, you cannot ask them to take another car (i.e. you cannot swap them to another car to make space for another group).

In terms of fairness of trip order: groups should be served as fast as possible, but the arrival order should be kept when possible. If group B arrives later than group A, it can only be served before group A if no car can serve group A.

For example: a group of 6 is waiting for a car and there are 4 empty seats at a car for 6; if a group of 2 requests a car you may take them in the car. This may mean that the group of 6 waits a long time, possibly until they become frustrated and leave.

## API

The interface provided by the service is a RESTfull API. The operations are as follows.

### GET /status

Indicate the service has started up correctly and is ready to accept requests.

Responses:

* **200 OK** When the service is ready to receive requests.

### PUT /cars

Load the list of available cars in the service and remove all previous data (existing journeys and cars). This method may be called more than once during the life cycle of the service.

**Body** _required_ The list of cars to load.

**Content Type** `application/json`

Sample:

```json
[
  {
    "id": 1,
    "seats": 4
  },
  {
    "id": 2,
    "seats": 6
  }
]
```

Responses:

* **200 OK** When the list is registered correctly.
* **400 Bad Request** When there is a failure in the request format, expected headers, or the payload can't be unmarshalled.

### POST /journey

A group of people requests to perform a journey.

**Body** _required_ The group of people that wants to perform the journey

**Content Type** `application/json`

Sample:

```json
{
  "id": 1,
  "people": 4
}
```

Responses:

* **200 OK** or **202 Accepted** When the group is registered correctly
* **400 Bad Request** When there is a failure in the request format or the payload can't be unmarshalled.

### POST /dropoff

A group of people requests to be dropped off. Whether they traveled or not.

**Body** _required_ A form with the group ID, such that `ID=X`

**Content Type** `application/x-www-form-urlencoded`

Responses:

* **200 OK** or **204 No Content** When the group is unregistered correctly.
* **404 Not Found** When the group is not to be found.
* **400 Bad Request** When there is a failure in the request format or the payload can't be unmarshalled.

### POST /locate

Given a group ID such that `ID=X`, return the car the group is traveling
with, or no car if they are still waiting to be served.

**Body** _required_ A url encoded form with the group ID such that `ID=X`

**Content Type** `application/x-www-form-urlencoded`

**Accept** `application/json`

Responses:

* **200 OK** With the car as the payload when the group is assigned to a car.
* **204 No Content** When the group is waiting to be assigned to a car.
* **404 Not Found** When the group is not to be found.
* **400 Bad Request** When there is a failure in the request format or the payload can't be unmarshalled.
