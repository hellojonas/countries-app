package dev.hellojonas.countriesapp.country;

import dev.hellojonas.countriesapp.country.errors.CountryNotFoundException;
import dev.hellojonas.countriesapp.country.errors.DuplicatedCountryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    public final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country createCountry(Country country) {
        Optional<Country> countryFound = countryRepository.findCountryByName(country.getName());

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

    public List<Country> getCountyByName(String name) {
        return countryRepository.findByNameContainingIgnoreCase(name);
    }

    public Boolean deleteCountry(Long id) {
        Country countryFound = getCountryById(id);
        countryRepository.delete(countryFound);
        return true;
    }

    public Page<Country> getCountries(Integer page, Integer size, String sortBy, String order) {
        // Sorting
        String[] allowedFields = {"name", "capital", "area", "id", "population", "region", "subregion"};
        boolean isAllowed = sortBy != null && Arrays.stream(allowedFields).anyMatch(i -> i.matches(sortBy));
        Sort sort = null;

        if (isAllowed) {
            sort = Sort.by(sortBy).ascending();
        }

        if (order != null && sort != null && order.toLowerCase().matches("desc|asc")) {
            sort = order.toLowerCase().matches("desc")
                    ? sort.descending()
                    : sort.ascending();
        }

        // Paging
        int _page = page != null && page >= 0 ? page : 0;
        int _limit = size != null && size > 0 ? size : 100;
        Pageable pageable = PageRequest.of(_page, _limit);

        if (isAllowed) {
            pageable = PageRequest.of(_page, _limit, sort);
        }

        return countryRepository.findAll(pageable);

    }
}
