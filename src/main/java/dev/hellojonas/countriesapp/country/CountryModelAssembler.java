package dev.hellojonas.countriesapp.country;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CountryModelAssembler implements RepresentationModelAssembler<Country, EntityModel<Country>> {

    @Override
    public EntityModel<Country> toModel(Country country) {
        return EntityModel.of(country,
                linkTo(methodOn(CountryController.class).getCountryById(country.getId())).withSelfRel(),
                linkTo(methodOn(CountryController.class).getCountryByName(country.getName())).withRel("countryByName")
        );
    }
}
