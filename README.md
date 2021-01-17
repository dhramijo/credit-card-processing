# Credit Card Processing

RESTful API for a credit card provider. This backend service allows to add new credit card accounts and view them as a list.

## Requirements

Two REST Endpoints must be implemented

* "Add" will create a new credit card for a given name, card number, and limit
  * Card numbers should be validated using Luhn 10
  * New cards start with a £0 balance
  * for cards not compatible with Luhn 10, return an error
* "Get all" returns all cards in the system

## Validation

* All input and output will be JSON
* Credit card numbers may vary in length, up to 19 characters
* Credit card numbers will always be numeric

## Technical requirements

* Create the RESTful API using Spring Boot and Use Maven/Gradle for dependency management
* Create the endpoints use appropriate HTTP Methods and define the payloads, return codes and response structures
* Write unit test cases, though please note that we’re looking for quality over quantity – coverage does not need to be high
* Use an in-memory DB to store the information while the API is running, so that it can store the credit card information
* Hand code the Luhn 10 check, don’t use a library

## How to run
````
$ git clone https://github.com/dhramijo/credit-card-processing.git
$ mvn spring-boot:run
````
## Endpoints

There are 2 endpoints:

### 1. Create a new credit card
````
HTTP POST /api/v1/card
````
Request
````
{
  "balance": 20,
  "cardNumber": "1111222233334444",
  "limit": 1000,
  "name": "Joni"
}
````
### 2. Get all the available credit cards

````
HTTP GET /api/v1/cards
````

## API Documentation

Swagger documentation can be accessible via
````
http://localhost:8080/swagger-ui.html
````
