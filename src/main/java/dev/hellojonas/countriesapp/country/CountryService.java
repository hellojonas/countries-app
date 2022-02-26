package dev.hellojonas.countriesapp.country;

import dev.hellojonas.countriesapp.country.errors.CountryNotFoundException;
import dev.hellojonas.countriesapp.country.errors.DuplicatedCountryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    public final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountries() {
        return this.countryRepository.findAll();
    }

    public Country createCountry(Country country) {
        Optional<Country> countryFound = countryRepository.findByName(country.getName());

        if (countryFound.isPresent()) {
            throw new DuplicatedCountryException(country.getName());
        }

        return countryRepository.save(country);
    }

    public Country updateCountry(Long id, Country countryData) {
        Country countryFound = getCountryById(id);
        countryData.setId(countryFound.getId());
        return countryRepository.save(countryData);
    }

    public Country getCountryById(Long id) {
        Optional<Country> countryFound = countryRepository.findById(id);

        if (countryFound.isEmpty()) {
            throw new CountryNotFoundException(id);
        }

        return countryFound.get();
    }

    public Country getCountyByName(String name) {
        Optional<Country> countryFound = countryRepository.findByName(name);

        if (countryFound.isEmpty()) {
            throw new CountryNotFoundException(name);
        }

        return countryFound.get();
    }

    public Boolean deleteCountry(Long id) {
        Country countryFound = getCountryById(id);
        countryRepository.delete(countryFound);
        return true;
    }
}
