package dev.hellojonas.countriesapp.country;

import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class CountryConfig {

    @Bean
    public CommandLineRunner commandLineRunner(CountryRepository repository) throws FileNotFoundException {

        BufferedReader br = new BufferedReader(
                new FileReader("src/main/java/dev/hellojonas/countriesapp/country/countries.json"));
        String jsonString = br.lines().collect(Collectors.joining());

        Country[] countries = new Gson().fromJson(jsonString, Country[].class);
        List<Country> countriesList = Arrays
                .stream(countries).map(country -> (Country) country)
                .collect(Collectors.toList());

        return args -> {
            // Preload database with data from countries.json
            repository.saveAll(countriesList);
        };
    }
}
