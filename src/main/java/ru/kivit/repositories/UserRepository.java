package ru.kivit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kivit.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
