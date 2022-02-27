package dev.hellojonas.countriesapp.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/countries")
public class CountryController {
    private final CountryService countryService;
    private final CountryModelAssembler assembler;

    private final PagedResourcesAssembler<Country> pagedResourcesAssembler;

    @Autowired
    CountryController(CountryService countryService, CountryModelAssembler assembler,
                      PagedResourcesAssembler<Country> pagedResourcesAssembler) {
        this.countryService = countryService;
        this.assembler = assembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Country>> createCountry(@RequestBody Country country) {
        EntityModel<Country> entityModel = assembler.toModel(countryService.createCountry(country));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping
    public ResponseEntity<?> getCountries(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "100") Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order) {

        Page<Country> countries = countryService.getCountries(page, size, sortBy, order);
        PagedModel<EntityModel<Country>> pagedCountries = pagedResourcesAssembler.toModel(countries);
        return ResponseEntity.ok(pagedCountries);
    }

    @GetMapping("/{id}")
    public EntityModel<Country> getCountryById(@PathVariable Long id) {
        Country country = countryService.getCountryById(id);
        return assembler.toModel(country);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getCountryByName(@PathVariable String name) {
//        EntityModel<Country> entityModel = assembler.toModel(countryService.getCountyByName(name));
        CollectionModel<EntityModel<Country>> collectionModel = CollectionModel.of(
                countryService
                        .getCountyByName(name)
                        .stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList()));
        return ResponseEntity.ok(collectionModel);
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
