package dev.hellojonas.countriesapp.country.errors;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String name) {
        super(String.format("Country with name '%s' was not found", name));
    }

    public CountryNotFoundException(Long id) {
        super(String.format("Country with id '%d' was not found", id));
    }
}
