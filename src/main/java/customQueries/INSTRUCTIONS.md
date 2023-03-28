# Getting Started

### CONSEGNA ESERCIZIO CUSTOM QUERIES 01

Write a Spring Boot application with the necessary dependencies that:
* has an entity Flight with the following columns:
  * a primary key 
  * a string description 
  * a string fromAirport 
  * a string toAirport 
  * an enum status (possible values: ONTIME, DELAYED and CANCELLED)
* has a dedicated repository 
* has a FlightController:
  * mapped on flights 
  * for the provisioning of 50 flights where: 
    * all the string values are randomly generated (using random.ints())
    * the default status is ON_TIME
  * for retrieving all the flights in the db

Test the 2 endpoints with Postman

### CONSEGNA ESERCIZIO CUSTOM QUERIES 2

Write a Spring Boot application with the necessary dependencies that:
* uses the same entity and repository as Custom Queries 1 exercise
* has a FlightController, mapped on flights:

  * for the provisioning of n flights (where n is an optional query param; if absent, n=100):
    * all the string values are randomly generated (using random.ints())
    * the status is generated randomly
    
  * for retrieving all the flights in the db using pagination 
  and returning them in ascending order by fromAirport 
  * for retrieving all the flights that are ONTIME WITHOUT USING A CUSTOM QUERY 
  * for retrieving - USING A CUSTOM QUERY - all the flights that are in p1 or in p2 status 
    * consider that the user has to pass p1 and p2 as parameters to the GET request
 
## TEST the endpoints with Postman:
* for provisioning without the n query parameter 
* for provisioning with the n query parameter, with value 49 
* for getting the flights using pagination 
* for getting the flights that are ONTIME 
* for getting the delayed or cancelled flights 
* for getting the on time or delayed flights




