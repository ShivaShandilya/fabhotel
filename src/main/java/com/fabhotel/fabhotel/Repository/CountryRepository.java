package com.fabhotel.fabhotel.Repository;

import com.fabhotel.fabhotel.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

     Country findBycountryName(String countryname);

}