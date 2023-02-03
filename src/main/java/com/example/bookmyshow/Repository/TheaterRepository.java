package com.example.bookmyshow.Repository;

import com.example.bookmyshow.Models.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<TheaterEntity, Integer> {
    TheaterEntity findByNameAndCity(String name, String city);
}
