package com.example.bookmyshow.Service;

import com.example.bookmyshow.Dtos.ShowRequestDto;
import com.example.bookmyshow.Models.*;
import com.example.bookmyshow.Repository.MovieRepository;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Repository.ShowSeatRepository;
import com.example.bookmyshow.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowRequestDto showRequestDto){
        ShowEntity showEntity = ShowEntity.builder().showDate(showRequestDto.getShowDate()).showTime(showRequestDto.getShowTime()).multiplier(showRequestDto.getMultiplier()).build();
        MovieEntity movieEntity = movieRepository.findByMovieName(showRequestDto.getMovieName());
        TheaterEntity theaterEntity = theaterRepository.findById(showRequestDto.getTheaterId()).get();

        showEntity.setTheater(theaterEntity);
        showEntity.setMovie(movieEntity);

        movieEntity.getListOfShow().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);

        List<ShowSeatEntity> seatEntityList = createShowSeats(theaterEntity.getTheaterSeatEntityList());
        showEntity.setListOfSeats(seatEntityList);

        for(ShowSeatEntity showSeat : seatEntityList){
            showSeat.setShow(showEntity);
        }

        movieRepository.save(movieEntity);
        theaterRepository.save(theaterEntity);

        return "Show Added Successfully";
    }

    public List<ShowSeatEntity> createShowSeats(List<TheaterSeatEntity> theaterSeatEntityList){
        List<ShowSeatEntity> seats = new ArrayList<>();

        for(TheaterSeatEntity seat : theaterSeatEntityList){
            ShowSeatEntity showSeat = ShowSeatEntity.builder().seatNo(seat.getSeatNo()).seatType(seat.getSeatType()).build();
            seats.add(showSeat);
        }

        showSeatRepository.saveAll(seats);
        return seats;
    }
}
