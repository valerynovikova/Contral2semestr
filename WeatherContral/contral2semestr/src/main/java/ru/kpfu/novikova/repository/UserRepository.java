package ru.kpfu.novikova.repository;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.novikova.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getUserByEmail(String email);

    User findByVerificationCode(String code);
    @Query("select u from User u where u.email = :email")


    Optional<User> findByEmail(String mail);

}
