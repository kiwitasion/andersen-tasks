package ru.kivit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kivit.models.User;
import ru.kivit.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findById(Long id){
        return repository.getOne(id);
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public User save(User user){
        return repository.save(user);
    }

    public User update(User newUser, Long id){
        User oldUser = repository.findById(id).orElse(null);
        oldUser.setFirst_name(newUser.getFirst_name());
        oldUser.setLast_name(newUser.getLast_name());
        oldUser.setLogin(newUser.getLogin());
        oldUser.setPassword(newUser.getPassword());
        return  repository.save(oldUser);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
