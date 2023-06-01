package com.martinps.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.martinps.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.martinps.dto.userDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("select u from User u where u.name like ?1%")
    List<User> findByAndSort(String name, Sort sort);

    List<User> findByName(String name);

    Optional<User> findUsersByNameAndAndEmail(String name, String email);

    @Query("select u from User u where u.name = :name or u.email = :email")
    Optional<User> findByNameOrEmail(@Param("name") String name,
                                     @Param("email") String email);

}
