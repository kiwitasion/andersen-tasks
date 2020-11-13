package ru.kivit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kivit.models.Country;

import ru.kivit.repositories.CountryRepository;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository repository;

    @Autowired
    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public Country findById(Long id){
        return repository.getOne(id);
    }

    public List<Country> findAll(){

        return repository.findAll();
    }

    public Country save(Country country){
        return repository.save(country);
    }

    public Country update(Country newCountry, Long id){
        Country oldCountry = repository.findById(id).orElse(null);
        oldCountry.setName(newCountry.getName());
        return  repository.save(oldCountry);
    }


    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
