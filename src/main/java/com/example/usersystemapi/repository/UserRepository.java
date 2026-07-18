package com.example.usersystemapi.repository;


import com.example.usersystemapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //List<User> findByName(String name);

    Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findByDepartment(String department);

    //List<User> findByAgeLessThan(Integer age);

    @Query("SELECT u FROM User u WHERE u.age<:threshold")
    List<User> findByAgeYoungerThan(@Param("threshold") int threshold);





}
