package ru.kivit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kivit.models.Tour;
import ru.kivit.repositories.TourRepository;

import java.util.List;

@Service
public class TourService {

    private final TourRepository repository;

    @Autowired
    public TourService(TourRepository repository) {
        this.repository = repository;
    }

    public Tour findById(Long id){
        return repository.getOne(id);
    }

    public List<Tour> findAll(){

        return repository.findAll();
    }

    public Tour save(Tour tour){
        return repository.save(tour);
    }

    public Tour update(Tour newTour, Long id){
        Tour oldTour = repository.findById(id).orElse(null);
        oldTour.setName(newTour.getName());
        oldTour.setTransfer_included(newTour.getTransfer_included());
        oldTour.setHotel(newTour.getHotel());
        return  repository.save(oldTour);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
