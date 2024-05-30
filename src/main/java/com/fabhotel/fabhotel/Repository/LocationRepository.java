package com.fabhotel.fabhotel.Repository;

import com.fabhotel.fabhotel.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findBylocationName(String locationName);
}