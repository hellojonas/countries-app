# Countries App

API to manage countries related information built in java using Spring Boot.

## Features

- **HAL support**, easy documentation discovery during development
- Paging and sorting

## Technologies

- Spring Boot
- Spring Data
- PostgreSQL

## How to access the API

Follow the [documentation](https://countries-app-prod.herokuapp.com) to access all supported resource endpoints.
The API is hosted on Heroku with the following host locations:
  - **Development**: ***https://countries-app-dev.herokuapp.com***, tracking git branch "review".
  - **Production**: ***https://countries-app-prod.herokuapp.com***, tracking git branch "main".


All country related action are mapped to **/api/v1/countries**. For example to get all countries send a GET request to
the following endpoint:

```
https://countries-app-dev.herokuapp.com/api/v1/countries
```

### Paging and sorting support

Use these keys (page, size, sortBy and order) to control paging and sorting on the */countries* resource:

```
GET https://countries-app-dev.herokuapp.com/api/v1/countries?page=4&size=10&sortBy=area&order=desc
```

For details about available action check the [documentation](https://countries-app-dev.herokuapp.com);

## Running locally

This project depends on java version 17. To run locally clone this repository

```bash
git clone git@github.com:hellojonas/countries-app.git
```

Install postgres and add set the environment variables:

- DB_URL: the url to the database, example: jdbc:postgres://[host]:[port]/[database_name]
- DB_USER: the database user
- DB_PASSWORD: password for the user

Then navigate to countries-app and start the server, by default it listens on port 8080.

```bash
cd countries-app
./mvnw spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=<dev | prod>
```

The application can run on two different profiles (Development and Production), to run on
production set the active profile to **prod**, if set every time the application
is started the database will not be recreated only updated when necessary.
To run on development profile set the active profile to **dev**, this way every time the
application is started the database will be recreated and the logs will be more verbose.
