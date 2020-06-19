package com.project.railway.data.repository;

import com.project.railway.data.entity.Client;
import com.project.railway.data.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByEmail(String email);

    Optional<Client> findByEmailAndUserRole(String Email, UserRole userRole);

    Optional<Client> findByEmailAndFirstNameAndLastNameAndPhoneNumber(String email, String firstName, String lastName, String phoneNumber);

    List<Client> findClientByEmailAndUserRole(String email, UserRole userRole);
}
