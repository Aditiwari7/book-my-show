package com.example.bookmyshow.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    public String index(){
        return "Welcome!";
    }

    @GetMapping("/error")
    public String index1(){
        return "Welcome!";
    }
}
