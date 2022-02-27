package dev.hellojonas.countriesapp.country;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    public List<Country> findByNameContainingIgnoreCase(String name);

    public Optional<Country> findCountryByName(String name);
}
