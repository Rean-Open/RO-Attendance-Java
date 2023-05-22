package edu.aeu.msit.assigment.attendance.qr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.aeu.msit.assigment.attendance.qr.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
