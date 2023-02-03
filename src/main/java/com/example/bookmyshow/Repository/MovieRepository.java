package com.example.bookmyshow.Repository;

import com.example.bookmyshow.Models.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    abstract MovieEntity findByMovieName(String movieName);
}
