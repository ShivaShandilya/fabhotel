package com.fabhotel.fabhotel.Repository;

import com.fabhotel.fabhotel.Entity.Country;
import com.fabhotel.fabhotel.Entity.Location;
import com.fabhotel.fabhotel.Entity.Property;
import com.fabhotel.fabhotel.Entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property,Long> {

   @Query("select p from Property p join Location l on p.location=l.id join Country c on p.country= c.id where l.locationName=:Locationname or c.countryName=:Locationname ")
   List<Property>findByLocation(@Param("Locationname") String Locationname);



}
