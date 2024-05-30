package com.fabhotel.fabhotel.Service;

import com.fabhotel.fabhotel.Entity.Image;
import com.fabhotel.fabhotel.Entity.Property;
import com.fabhotel.fabhotel.Repository.ImageRepository;
import com.fabhotel.fabhotel.Repository.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private PropertyRepository propertyRepository;
    private ImageRepository imageRepository;

    public ImageService(PropertyRepository propertyRepository, ImageRepository imageRepository) {
        this.propertyRepository = propertyRepository;
        this.imageRepository = imageRepository;
    }

    public Image saveimage(long propertyid, String imageurl) {
        Property property = propertyRepository.findById(propertyid).get();
        Image image=new Image();
        image.setProperty(property);
        image.setUrl(imageurl);
        Image save = imageRepository.save(image);
        return save;
    }
}
