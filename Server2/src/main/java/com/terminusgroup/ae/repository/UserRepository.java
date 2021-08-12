package com.terminusgroup.ae.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.terminusgroup.ae.model.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByEmail(String email);

    @Query(value = "select u.name ,u.email from User u where u.enable= false")
    List<User> findAllByIsEnabledfalse();

    @Query(value = "select u.name ,u.email from User u where u.enable= true")
    List<User> findAllByIsenabletrue();



}
