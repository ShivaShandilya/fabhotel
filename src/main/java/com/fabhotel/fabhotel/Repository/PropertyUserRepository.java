package com.fabhotel.fabhotel.Repository;

import com.fabhotel.fabhotel.Entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyUserRepository extends JpaRepository<PropertyUser, Long>{
    boolean existsBymobile(String mobile);
    PropertyUser findBymobile(String mobile);
    boolean existsByemail(String email);
    PropertyUser findByemail(String email);


    Optional<PropertyUser> findByuserName(String userName);
}