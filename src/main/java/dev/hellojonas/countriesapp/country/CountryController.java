package dev.hellojonas.countriesapp.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/countries")
public class CountryController {
    private final CountryService countryService;
    private final CountryModelAssembler assembler;

    @Autowired
    CountryController(CountryService countryService, CountryModelAssembler assembler) {
        this.countryService = countryService;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Country>> createCountry(@RequestBody Country country) {
        EntityModel<Country> entityModel = assembler.toModel(countryService.createCountry(country));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping
    public ResponseEntity<?> getCountries() {
        List<EntityModel<Country>> countries = countryService
                .getCountries()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Country>> collectionModel =
                CollectionModel.of(countries, linkTo(methodOn(CountryController.class).getCountries()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Country> getCountryById(@PathVariable Long id) {
        Country country = countryService.getCountryById(id);
        return assembler.toModel(country);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<EntityModel<Country>> getCountryByName(@PathVariable String name) {
        EntityModel<Country> entityModel = assembler.toModel(countryService.getCountyByName(name));
        return ResponseEntity.ok(entityModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<Country>> updateCountry(@RequestBody Country countryData, @PathVariable Long id) {
        EntityModel<Country> entityModel = assembler.toModel(countryService.updateCountry(id, countryData));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long id) {
        boolean countryFound = countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }
}
