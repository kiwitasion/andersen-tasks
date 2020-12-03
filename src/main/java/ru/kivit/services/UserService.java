package ru.kivit.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kivit.models.Role;
import ru.kivit.models.User;
import ru.kivit.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findById(Long id){
        return repository.getOne(id);
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public boolean save(User user){

        User userFromDb = repository.findByLogin(user.getLogin());

        if (userFromDb != null){
            return false;
        }

        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRole(Role.USER);
        repository.save(user);

        return true;
    }

    public User update(User newUser, Long id){
        User oldUser = repository.findById(id).orElse(null);
        oldUser.setFirst_name(newUser.getFirst_name());
        oldUser.setLast_name(newUser.getLast_name());
        oldUser.setLogin(newUser.getLogin());
        oldUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        return  repository.save(oldUser);
    }

    public boolean deleteById(Long id){

        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User userFromDb = repository.findByLogin(login);
        if (userFromDb == null){
            throw new UsernameNotFoundException("Incorrect login");
        }
        return userFromDb;
    }
}
