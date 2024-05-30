package com.fabhotel.fabhotel.Service;

import com.fabhotel.fabhotel.Entity.Booking;
import com.fabhotel.fabhotel.Entity.Property;
import com.fabhotel.fabhotel.Entity.PropertyUser;
import com.fabhotel.fabhotel.Repository.BookingRepository;
import com.fabhotel.fabhotel.Repository.PropertyRepository;
import com.fabhotel.fabhotel.payload.BookingDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private PropertyRepository propertyRepository;

    public BookingService(BookingRepository bookingRepository, PropertyRepository propertyRepository) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
    }
    BookingDto dto = new BookingDto();

    public BookingDto hotelbooking(Booking booking, Long propertyid, PropertyUser user) {
        Property property = propertyRepository.findById(propertyid).get();
        booking.setPropertyUser(user);
        booking.setProperty(property);

        String id = UUID.randomUUID().toString();
        booking.setId(id);

        Booking save = bookingRepository.save(booking);


        dto.setEmail(save.getEmail());
        dto.setId(save.getId());
        dto.setMobileNumber(save.getMobileNumber());
        dto.setGuest(String.valueOf(save.getGuest()));
        dto.setPropertyName(property.getPropertyName());
        dto.setPropertyUsername(user.getUserName());
        dto.setTotalnight(save.getTotalnight());

        Integer pernightprice = property.getNightlyPrice();
        Integer totalnight = booking.getTotalnight();
        int totalprice= pernightprice * totalnight;
        dto.setTotalprice(totalprice);
        return dto;

    }
}

