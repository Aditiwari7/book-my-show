package com.example.bookmyshow.Service;

import com.example.bookmyshow.Dtos.BookTicketRequestDto;
import com.example.bookmyshow.Models.ShowEntity;
import com.example.bookmyshow.Models.ShowSeatEntity;
import com.example.bookmyshow.Models.TicketEntity;
import com.example.bookmyshow.Models.UserEntity;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Repository.TicketRepository;
import com.example.bookmyshow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    public String bookTicket(BookTicketRequestDto bookTicketRequestDto)throws Exception{
        List<String> requestedSeats = bookTicketRequestDto.getRequestSeats();
        ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowId()).get();
        UserEntity userEntity = userRepository.findById(bookTicketRequestDto.getUserId()).get();

        List<ShowSeatEntity> showSeats = showEntity.getListOfSeats();
        List<ShowSeatEntity> bookedSeats = new ArrayList<>();

        for(ShowSeatEntity showSeat : showSeats){
            String seatNo = showSeat.getSeatNo();
            if(showSeat.isBooked()==false && requestedSeats.contains(seatNo)){
                bookedSeats.add(showSeat);
            }
        }

        if(bookedSeats.size() != requestedSeats.size()){
            throw new Exception("Requested seats are not available");
        }

        TicketEntity ticketEntity = new TicketEntity();

        double totalAmount = 0;
        double multiplier = showEntity.getMultiplier();

        String allotedSeats = "";

        int rate = 0;

        for(ShowSeatEntity bookedSeat : bookedSeats){
            bookedSeat.setBooked(true);
            bookedSeat.setBookedAt(new Date());
            bookedSeat.setTicket(ticketEntity);
            bookedSeat.setShow(showEntity);

            String seatNo = bookedSeat.getSeatNo();
            allotedSeats = allotedSeats + seatNo + ",";

            if("1".equals(seatNo.charAt(0))){
                rate = 100;
            }else{
                rate = 200;
            }
            totalAmount = totalAmount + multiplier * rate;
        }

        ticketEntity.setBooked_at(new Date());
        ticketEntity.setAmount((int)totalAmount);
        ticketEntity.setShow(showEntity);
        ticketEntity.setUser(userEntity);
        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAllotted_seats(allotedSeats);

        ticketRepository.save(ticketEntity);
        return "Successfully created a ticket";
    }
}
