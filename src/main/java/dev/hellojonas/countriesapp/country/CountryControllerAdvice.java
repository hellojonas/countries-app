package dev.hellojonas.countriesapp.country;

import dev.hellojonas.countriesapp.country.errors.CountryNotFoundException;
import dev.hellojonas.countriesapp.country.errors.DuplicatedCountryException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CountryControllerAdvice {
    @ResponseBody
    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String countryNotFound(CountryNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> badInput(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()).get(0);

        return Map.of("message", message);
    }

    @ResponseBody
    @ExceptionHandler(DuplicatedCountryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> duplicatedCountry(DuplicatedCountryException ex) {
        return Map.of("message", ex.getMessage());
    }
}
