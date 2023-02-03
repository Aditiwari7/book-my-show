package com.example.bookmyshow.Service;

import com.example.bookmyshow.Dtos.MovieRequestDto;
import com.example.bookmyshow.Models.MovieEntity;
import com.example.bookmyshow.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieRequestDto movieRequestDto){
        MovieEntity movie = MovieEntity.builder().movieName(movieRequestDto.getName()).duration(movieRequestDto.getDuration()).releaseDate(movieRequestDto.getReleaseDate()).build();
        movieRepository.save(movie);
        return "Movie Added Successfully";
    }
}
