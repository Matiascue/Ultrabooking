package com.example.bookings.services.imp;

import com.example.bookings.dtos.BookingDto;
import com.example.bookings.dtos.PassangerDto;
import com.example.bookings.dtos.client.FlightDto;
import com.example.bookings.dtos.info.BookingInfoDto;
import com.example.bookings.dtos.info.PassangerInfoDto;
import com.example.bookings.entity.BookingEntity;
import com.example.bookings.models.Status;
import com.example.bookings.repository.BookingRepository;
import com.example.bookings.services.BookingService;
import com.example.bookings.services.RestTemplateService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingServiceImp implements BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImp.class);
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestTemplateService restTemplateService;
    @Override
    public BookingDto postReservation(BookingDto reservation) {
        ResponseEntity<FlightDto>responseEntity =this.restTemplateService.getFlightById(reservation.getFlight());
        if(responseEntity.getBody()==null){
            logger.error("Flight with ID " + reservation.getFlight() + " not found.");
            throw new RuntimeException("Flight with ID " + reservation.getFlight() + " not found.");
        }
        FlightDto flightDt=responseEntity.getBody();
        this.validateSeatAvaible(flightDt);
        BookingEntity bookingEntity=modelMapper.map(reservation,BookingEntity.class);
        bookingEntity.setStatus(Status.READY_TO_CHECKIN);
        return modelMapper.map(bookingRepository.save(bookingEntity), BookingDto.class);
    }
    private void validateSeatAvaible(FlightDto flight){
        long avaibles=flight.getSeat_map().stream()
                .filter(seat -> seat.getStatus().equals(com.example.bookings.dtos.client.Status.AVAIBLE)).count();
        if(avaibles==0){
            logger.error("Not avaibles seats");
            throw new IllegalArgumentException("Not avaibles seats");
        }
    }

    @Override
    public BookingInfoDto getReservation(String id) {
        BookingEntity bookingEntity=bookingRepository.findById(id)
                .orElseThrow(()->{
                    logger.error("Reservation not found with id: {}", id);
                    return new RuntimeException("Reservation not found");
                });
        return modelMapper.map(bookingEntity, BookingInfoDto.class);
    }

    @Override
    public BookingDto checkIn(BookingInfoDto reservation) {
        BookingInfoDto bookingDto=this.getReservation(reservation.getId());
        ResponseEntity<FlightDto>responseEntity =this.restTemplateService.getFlightById(reservation.getFlight());
        if(responseEntity.getBody()==null){
            logger.error("Flight with ID " + reservation.getFlight() + " not found.");
            throw new RuntimeException("Flight with ID " + reservation.getFlight() + " not found.");
        }
        FlightDto flightDt=responseEntity.getBody();
        if(validateDateFlight(flightDt)){
            bookingDto.setStatus(Status.DUE);
            logger.warn("Flight is due for reservation: {}", reservation.getId());
            bookingRepository.save(modelMapper.map(bookingDto,BookingEntity.class));
            throw new IllegalStateException("Flight is due");
        }
        if(!Status.READY_TO_CHECKIN.equals(bookingDto.getStatus())){
            logger.error("Invalid reservation status for check-in: {}", bookingDto.getStatus());
            throw new IllegalStateException("Invalid reservation status");
        }
        this.validateChekin(flightDt,reservation);
        this.updateFlighStatus(flightDt,reservation);
        bookingDto.setStatus(Status.CHECKED_IN);
        bookingDto.setPassengers(reservation.getPassengers());
        BookingEntity bookingEntity=modelMapper.map(bookingDto, BookingEntity.class);
        return modelMapper.map(bookingRepository.save(bookingEntity), BookingDto.class);
    }

    private boolean validateDateFlight(FlightDto flight){
        LocalDateTime now=LocalDateTime.now().withSecond(0);
        return (flight.getDeparture().withSecond(0).isBefore(now));
    }
    private void validateChekin(FlightDto flight,BookingInfoDto reservation){
        for(PassangerInfoDto pass:reservation.getPassengers()){
            boolean avaible=flight.getSeat_map().stream().anyMatch(seat ->
                    seat.getSeat().equals(pass.getSeat())
                            && com.example.bookings.dtos.client.Status.AVAIBLE.equals(seat.getStatus()));
            if(!avaible){
                logger.error("Seat {} is  ocuped", pass.getSeat());
                throw new IllegalStateException("Seat " + pass.getSeat() + " is  ocuped");
            }
        }
    }
    private void updateFlighStatus(FlightDto flight,BookingInfoDto reservation){
        for (PassangerInfoDto pass : reservation.getPassengers()) {
            flight.getSeat_map().stream()
                    .filter(seat -> seat.getSeat().equals(pass.getSeat()))
                    .findFirst()
                    .ifPresent(seat -> seat.setStatus(com.example.bookings.dtos.client.Status.RESERVED));
        }
        this.restTemplateService.postFlight(flight);

    }

}
