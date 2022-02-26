package dev.hellojonas.countriesapp.country;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "Capital cannot be empty")
    private String capital;

    @Positive(message = "Area must be greater than 0")
    private Double area;

    @PositiveOrZero(message = "Population must be greater than 0")
    private Long population;

    @NotBlank(message = "Country must belong to a region")
    private String region;

    @NotBlank(message = "Country must belong to a sub region")
    private String subregion;

    public Country() {
    }

    public Country(String name, String capital, Double area, Long population, String region, String subregion) {
        this.name = name;
        this.capital = capital;
        this.area = area;
        this.population = population;
        this.region = region;
        this.subregion = subregion;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", area=" + area +
                ", population=" + population +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }
}
