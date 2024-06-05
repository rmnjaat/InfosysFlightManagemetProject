package com.infosysSpringboard.flightManagementSystem.controller;

import com.infosysSpringboard.flightManagementSystem.entity.Booking;
import com.infosysSpringboard.flightManagementSystem.entity.Passenger;
import com.infosysSpringboard.flightManagementSystem.entity.ScheduledFlight;
import com.infosysSpringboard.flightManagementSystem.service.BookingService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/add")
    public List<Booking> addBooking(@RequestBody Booking booking){
        return bookingService.addBooking(booking);
    }
    @PutMapping("/modify")
    public List<Booking> modifyBooking(@RequestBody Booking booking){
        return bookingService.modifyBooking(booking);
    }
    @GetMapping("/getAll")
    public List<Booking> getAllBookings(){
        return bookingService.viewBookings();
    }
    @GetMapping("/getOne/{id}")
    public Booking getOneBooking(@PathVariable Integer id){
        return bookingService.viewBooking(id);
    }
    @DeleteMapping("/delete/{id}")
    public List<Booking> deleteBooking(@PathVariable Integer id){
        return bookingService.deleteBooking(id);
    }
    @GetMapping("/{id}/passengers")
    public List<Passenger> getPassengers(@PathVariable Integer id){
        return bookingService.getPassengerDetails(id);
    }
}
