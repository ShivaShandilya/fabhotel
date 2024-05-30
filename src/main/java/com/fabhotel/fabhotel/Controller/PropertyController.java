package com.fabhotel.fabhotel.Controller;

import com.fabhotel.fabhotel.Entity.Property;
import com.fabhotel.fabhotel.Service.PropertyService;
import com.fabhotel.fabhotel.payload.PropertyDto;
import com.fabhotel.fabhotel.payload.tokenDto;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {
    private PropertyService propertyService;


    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }
    @PostMapping("/saved")
    public ResponseEntity<Property>savedproperty(@RequestBody PropertyDto propertyDto){
        Property saveall = propertyService.savedallproperty(propertyDto);
        return new ResponseEntity<>(saveall, HttpStatus.CREATED);

    }
    @GetMapping("/getallproperty")
    public ResponseEntity<List<Property>>getallproperty(@RequestParam(name="pageno",defaultValue = "0",required = false) int pageno,
                                                        @RequestParam(name = "pagesize",defaultValue = "2",required = false) int pagesize,
                                                      @RequestParam(name = "sortby",defaultValue = "id",required = false)  String sortby,
                                                        @RequestParam(name = "sorttype",defaultValue = "asc",required = false) String sorttype){
        List<Property> getallproperty = propertyService.getallproperty(pageno,pagesize,sortby,sorttype);
return new ResponseEntity<>(getallproperty,HttpStatus.OK);
    }
   @GetMapping("/{location}")
    public ResponseEntity<List<Property>>findbylocation(@PathVariable String location){
       List<Property> findbylocation = propertyService.findbylocation(location);
       return new ResponseEntity<>(findbylocation,HttpStatus.OK);

   }

}
