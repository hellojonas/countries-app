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

Follow the [documentation](https://countries-app-2.herokuapp.com) to access all supported resource endpoints. The API is
hosted on Heroku with the host location ***https://countries-app-2.herokuapp.com***.

All country related action are mapped to **/api/v1/countries**. For example to get all countries send a GET request to
the following endpoint:

```
https://countries-app-2.herokuapp.com/api/v1/countries
```

### Paging snd sorting support

Use these keys (page, size, sortBy and order) to control paging and sorting on the */countries* resource:

```
GET https://countries-app-2.herokuapp.com/api/v1/countries?page=4&size=10&sortBy=area&order=desc
```

For details about available action check the [documentation](https://countries-app-2.herokuapp.com);

## Running locally

This project depends on java version 17. To run locally clone this repository

```bash
git clone git@github.com:hellojonas/countries-app.git
```

Install postgres and add following lines to application.properties:

- spring.datasource.url=jdbc:postgresql://localhost:5432/{database-name}
- spring.datasource.username={postgres-username}
- spring.datasource.password={postgres-user-password}

Then navigate to countries-app and start the server, by default it listens on port 8080

```bash
cd countries-app
./mvnw spring-boot:run
```
