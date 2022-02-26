package dev.hellojonas.countriesapp.country.errors;

public class DuplicatedCountryException extends RuntimeException {
    public DuplicatedCountryException(String name) {
        super(String.format("Duplicated country with name '%s'", name));
    }
}
