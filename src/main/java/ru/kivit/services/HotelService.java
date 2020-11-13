package ru.kivit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kivit.models.Hotel;
import ru.kivit.repositories.HotelRepository;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository repository;

    @Autowired
    public HotelService(HotelRepository repository) {
        this.repository = repository;
    }

    public Hotel findById(Long id){
        return repository.getOne(id);
    }

    public List<Hotel> findAll(){

        return repository.findAll();
    }

    public Hotel save(Hotel hotel){
        return repository.save(hotel);
    }

    public Hotel update(Hotel newHotel, Long id){
        Hotel oldHotel = repository.findById(id).orElse(null);
        oldHotel.setName(newHotel.getName());
        oldHotel.setService_level(newHotel.getService_level());
        return  repository.save(oldHotel);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
