# Fixing the Car Pooling Service Challenge

## Mission

Take over the design/implementation of a system to manage car pooling.

At Cabify we provide the service of taking people from point A to point B. So far we have done it without sharing cars with multiple groups of people. This is an opportunity to optimize the use of resources by introducing car pooling.

The job was assigned to a team and they have implemented what could be the first prototype. However, we are not very satisfied with their result. The code seems not to reach the quality standards we expect from our engineers. And as soon as the QA team has enabled their acceptance tests suite, the solution shows that it doesn't even work according to the specs.

Now it is your turn to take the baton, assuming the ownership of this prototype. We trust in your skills to edict the situation, fixing all the flaws you might find in the code to deliver something that will surely exceed the expectations of QA with a product that will make us feel really proud.

## Car Pooling Service

The Car Pooling service is aimed to track the availability of our pool of cars.

Cars have a different amount of seats available, they can accommodate groups of up to 4, 5 or 6 people.

Users request cars in groups of 1 to 6. People in the same group want to ride on the same car. You can take any group at any car that has enough empty seats for them. If it's not possible to accommodate them, they're willing to wait until there's a car available for them. Once a car is available for a group that is waiting, they should ride. 

Once they get a car assigned, they will journey until the drop off, you cannot ask them to take another car (i.e. you cannot swap them to another car to make space for another group).

In terms of fairness of trip order: groups should be served as fast as possible, but the arrival order should be kept when possible. If group B arrives later than group A, it can only be served before group A if no car can serve group A.

For example: a group of 6 is waiting for a car and there are 4 empty seats at a car for 6; if a group of 2 requests a car you may take them in the car. This may mean that the group of 6 waits a long time, possibly until they become frustrated and leave.

## Evaluation rules

### Goals

Your goal is to apply the necessary changes to this repository to redirect the situation of the project according to your own quality standards in software development. We expect you to submit a code that will serve as example for other engineers of how we should make software here in Cabify.

Anything you think that should had been done in a different way can be changed. If you are not satisfied with the performance, the design, the code style, the documentation, the build chain, the testing strategy, the Git policy, or any other thing, you can work on improving any of them. 

Please note our expectations are put on improving the quality of the product. We do not expect you to extend its functionality by including additional features. This is not the goal of the challenge, and any modification towards improving the product will not be taken in consideration.

This `INSTRUCTIONS.md` file is the only one that is not part of the deliverable. Feel free to remove it if you want. We do not expect you to make any change to it, and we will completely ignore it.

### Checks

As you will find in later, the codebase is configured to use GitLab for Continous Integration. You will find a CI job called `acceptance` in its config. It executes the tests that QA has delivered so far for the acceptance of this service. As said above, initially the tests do not pass. You must ensure the CI job is not removed and its tests are green before shipping your changes. 

Once you have completed all the changes to the repository and you submit your solution, a team of experienced developers will check the results. They will likely run some further tests to prove the solution works as expected. 

## Tips and tricks

- Use your time wisely. Do not make any change that is not moving the ball one step closer to your archetype of well-done software. 

- Assume it is for real. This code will be eventually used in Production, and different stakeholders will have to deal with it. Think that high-quality software is the one that makes our lives easier. Everybody lives.

- Avoid high latencies in feedback loops. If you find yourself waiting to tests running in GitLab CI for long periods just to figure out if your latest changes are okay... keep calm and rethink the situation. Surely you will find a way to execute those tests in your computer...

## Feedback

In Cabify, we really appreciate your interest and your time. We are highly interested on improving our Challenge and the way we evaluate our candidates. Hence, we would like to beg five more minutes of your time to fill the following survey:

- https://docs.google.com/forms/d/e/1FAIpQLSe8um9OuwqOqUYye7rmTALqmQkPkO3NRhhyUn8CdyQgHj_0HA

Your participation is really important. Thanks for your contribution!
