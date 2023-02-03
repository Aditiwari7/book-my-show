package com.example.bookmyshow.Controller;

import com.example.bookmyshow.Dtos.TheaterRequestDto;
import com.example.bookmyshow.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    public String addTheater(@RequestBody TheaterRequestDto theaterRequestDto){
        return theaterService.createTheater(theaterRequestDto);
    }

    @GetMapping("/get-theater-by-id")
    public String getTheaterById(@RequestBody TheaterRequestDto theaterRequestDto){
        String theaterName = theaterRequestDto.getName();
        return theaterName;
    }

//    @GetMapping("/get-all-theaters")
//    public List<String> getAllTheaters(@RequestBody TheaterRequestDto theaterRequestDto){
//        List<String> listOfTheater =
//    }
}
