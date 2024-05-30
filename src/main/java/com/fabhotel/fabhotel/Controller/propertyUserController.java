package com.fabhotel.fabhotel.Controller;

import com.fabhotel.fabhotel.Entity.PropertyUser;
import com.fabhotel.fabhotel.Repository.PropertyUserRepository;

import com.fabhotel.fabhotel.Service.PropertyUserService;
import com.fabhotel.fabhotel.payload.LoginDto;
import com.fabhotel.fabhotel.payload.PropertyUserDto;
import com.fabhotel.fabhotel.payload.tokenDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/propertyUser")
public class propertyUserController {
    private PropertyUserService propertyUserService;
    private PropertyUserRepository propertyUserRepository;


    public propertyUserController(PropertyUserService propertyUserService, PropertyUserRepository propertyUserRepository) {
        this.propertyUserService = propertyUserService;
        this.propertyUserRepository = propertyUserRepository;


    }

    @PostMapping("/save")
    public ResponseEntity<?> savedPropertyUser(@RequestBody @Valid PropertyUserDto propertyUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


        PropertyUserDto dto = propertyUserService.userSaved(propertyUserDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @GetMapping("/AllProperty")
    public ResponseEntity<List<PropertyUser>> AllPropertyUser() {
        List<PropertyUser> AllPropertyUser = propertyUserService.AllProperty();
        return new ResponseEntity<>(AllPropertyUser, HttpStatus.OK);

    }

    @GetMapping("/mobile")
    public ResponseEntity<?> findbymobile(@RequestBody String mobile) {
        boolean b = propertyUserRepository.existsBymobile(mobile);
        if (b == true) {
            PropertyUser mainuser = propertyUserService.findbymobile(mobile);
            return new ResponseEntity<>(mainuser, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("mobile does not exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deletebyemail(@PathVariable String email) {
        boolean b = propertyUserRepository.existsByemail(email);
        if (b == true) {
            propertyUserService.deletebyemail(email);
            return new ResponseEntity<>("email is delete", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("email is not available", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/verifylogin")
    public ResponseEntity<?> VerifyLogin(@RequestBody LoginDto loginDto){

        String token = propertyUserService.verifylogin(loginDto);

        if(token!=null){
            tokenDto tokenHeader=new tokenDto();
            tokenHeader.setToken(token);
            return new ResponseEntity<>(tokenHeader,HttpStatus.OK);

        }
        return new ResponseEntity<>("Something went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}






