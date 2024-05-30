package com.fabhotel.fabhotel.Service;

import com.fabhotel.fabhotel.Entity.Country;
import com.fabhotel.fabhotel.Entity.Location;
import com.fabhotel.fabhotel.Entity.Property;
import com.fabhotel.fabhotel.Repository.CountryRepository;
import com.fabhotel.fabhotel.Repository.LocationRepository;
import com.fabhotel.fabhotel.Repository.PropertyRepository;
import com.fabhotel.fabhotel.payload.PropertyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
   private PropertyRepository propertyRepository;
   private CountryRepository countryRepository;
   private LocationRepository locationRepository;

    public PropertyService(PropertyRepository propertyRepository, CountryRepository countryRepository, LocationRepository locationRepository) {
        this.propertyRepository = propertyRepository;
        this.countryRepository = countryRepository;
        this.locationRepository = locationRepository;
    }
Property property = new Property();
    public Property savedallproperty(PropertyDto propertyDto) {
        property.setAddress(propertyDto.getAddress());
        property.setPropertyName(propertyDto.getPropertyname());
        property.setBedroom(propertyDto.getBedroom());
        property.setNightlyPrice(propertyDto.getNightlyprice());
        property.setGuest(propertyDto.getGuest());


        Country country = countryRepository.findBycountryName(propertyDto.getCountryname());
property.setCountry(country);
        Location location = locationRepository.findBylocationName(propertyDto.getLocation());
        property.setLocation(location);
        Property save = propertyRepository.save(property);

        return save;
    }
    public List<Property> getallproperty(int pageno, int pagesize, String sortby, String sorttype) {
        Sort sort = sorttype.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortby).ascending() : Sort.by(sortby).descending();


        PageRequest pageable = PageRequest.of(pageno, pagesize, sort);
        Page<Property> all = propertyRepository.findAll(pageable);
        List<Property> content = all.getContent();
        System.out.println("total page=" + all.getTotalPages());
        System.out.println("total elements=" + all.getTotalElements());

        return content;


    }

    public List<Property> findbylocation(String location) {
        List<Property> byLocation = propertyRepository.findByLocation(location);
        return byLocation;
    }

}

