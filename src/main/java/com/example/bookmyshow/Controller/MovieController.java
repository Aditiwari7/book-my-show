package com.example.bookmyshow.Controller;

import com.example.bookmyshow.Dtos.MovieRequestDto;
import com.example.bookmyshow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public String addMovie(@RequestBody MovieRequestDto movieRequestDto){
        return movieService.addMovie(movieRequestDto);
    }

    @GetMapping("/get-movie-by-name")
    public String getMovieByName(@RequestBody MovieRequestDto movieRequestDto){
        String movieName = movieRequestDto.getName();

        return movieName;
    }
}
