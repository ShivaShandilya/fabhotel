package com.fabhotel.fabhotel.Controller;

import com.fabhotel.fabhotel.Entity.Image;
import com.fabhotel.fabhotel.Entity.Property;
import com.fabhotel.fabhotel.Service.BucketService;
import com.fabhotel.fabhotel.Service.ImageService;
import com.fabhotel.fabhotel.Service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {
    private ImageService imageService;
    private PropertyService propertyService;
    private BucketService bucketService;


    public ImageController(ImageService imageService, PropertyService propertyService, BucketController bucketController, BucketService bucketService) {
        this.imageService = imageService;
        this.propertyService = propertyService;
        this.bucketService = bucketService;

    }

    @PostMapping(path = "/upload/file/{bucketName}/{propertyid}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Image> uploadFile(@RequestParam MultipartFile file,
                                             @PathVariable String bucketName,
                                             @PathVariable long propertyid) {

        String imageurl = bucketService.uploadFile(file, bucketName);
        Image saveimage = imageService.saveimage(propertyid, imageurl);

        return new ResponseEntity<>(saveimage, HttpStatus.OK);
    }


}
