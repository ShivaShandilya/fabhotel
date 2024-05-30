package com.fabhotel.fabhotel.Repository;

import com.fabhotel.fabhotel.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,String> {
}
