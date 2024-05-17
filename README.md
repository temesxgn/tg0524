# Tool Rental

## Table of Contents
* [Running](#running)
* [TODO](#todo)

## Running
Docker must be installed on host machine
```bash
docker-compose up - this will stand up the mysql database, init the db & start redis cache
Once this is up you can run the spring boot application or run the juint CheckoutServiceIT integration test
```

## TODO
* Add more data & input validation
* Expose rest endpoints
* Add advanced filtering & Sorting using the query models
* Mock database connection for unit test
* Finish updating README
* Better validation
* Generate Swagger
* Implement remaining CRUD operations
* Add more unit & integration tests