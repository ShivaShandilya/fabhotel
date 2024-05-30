package com.fabhotel.fabhotel.Service;

import com.fabhotel.fabhotel.Entity.PropertyUser;
import com.fabhotel.fabhotel.Repository.PropertyUserRepository;
import com.fabhotel.fabhotel.payload.LoginDto;
import com.fabhotel.fabhotel.payload.PropertyUserDto;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyUserService {
    private PropertyUserRepository propertyUserRepository;
    private PasswordEncoder passwordEncoder;
private JwtService jwtService;



    public PropertyUserService(PropertyUserRepository propertyUserRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.propertyUserRepository = propertyUserRepository;

        this.passwordEncoder = passwordEncoder;


        this.jwtService = jwtService;
    }
    PropertyUser user = new PropertyUser();
    PropertyUserDto dto = new PropertyUserDto();


    public PropertyUserDto userSaved(PropertyUserDto propertyUserDto) {
        user.setUserRole(propertyUserDto.getUserRole());
        user.setUserName(propertyUserDto.getUserName());
        user.setFullName(propertyUserDto.getFullName());
        user.setMobile(propertyUserDto.getMobile());
        user.setEmail(propertyUserDto.getEmail());
        user.setPassword(BCrypt.hashpw(propertyUserDto.getPassword(),BCrypt.gensalt(10)));
        PropertyUser save= propertyUserRepository.save(user);

        dto.setUserName(save.getUserName());

        dto.setFullName(save.getFullName());
        dto.setMobile(save.getMobile());
        dto.setEmail(save.getEmail());
        dto.setUserRole(save.getUserRole());

        return dto;


    }

    public List<PropertyUser> AllProperty() {
        List<PropertyUser> all = propertyUserRepository.findAll();
        return all;
    }


    public PropertyUser findbymobile(String mobile) {
        PropertyUser user = propertyUserRepository.findBymobile(mobile);
        return user;
    }

    public  String deletebyemail(String email) {
        PropertyUser user = propertyUserRepository.findByemail(email);
        user.getId();
        propertyUserRepository.deleteById(user.getId());
        return null;

    }
    public String verifylogin(LoginDto loginDto) {
        Optional<PropertyUser> user = propertyUserRepository.findByuserName(loginDto.getUserName());
        PropertyUser user1 = user.get();
        if(BCrypt.checkpw(loginDto.getPassword(), user1.getPassword())) {
            return jwtService.generateToken(user1);
        }
        return null;
    }


}

