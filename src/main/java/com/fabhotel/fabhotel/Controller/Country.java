package com.fabhotel.fabhotel.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/country")
public class Country {
    @PostMapping("/fine")
    public String find(){
        return "heelo";
    }
}
