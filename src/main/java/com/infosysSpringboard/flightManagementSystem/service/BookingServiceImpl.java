package com.infosysSpringboard.flightManagementSystem.service;

import com.infosysSpringboard.flightManagementSystem.dao.BookingRepository;
import com.infosysSpringboard.flightManagementSystem.entity.Booking;
import com.infosysSpringboard.flightManagementSystem.entity.Passenger;
import com.infosysSpringboard.flightManagementSystem.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

    private BookingRepository bookingRepository;
    private UserService userService;
    private FlightService flightService;

    public BookingServiceImpl(BookingRepository bookingRepository, UserService userService, FlightService flightService) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.flightService = flightService;
    }

    @Override
    public List<Booking> addBooking(Booking booking) {
        User user = userService.getCurrentUser();
        booking.setUserId(user.getUserId());
        booking.setDate(new Date());
        bookingRepository.save(booking);
        return bookingRepository.findAllByUserId(user.getUserId());
    }

    @Override
    public List<Booking> modifyBooking(Booking booking) {
        User user = userService.getCurrentUser();
        bookingRepository.save(booking);
        return bookingRepository.findAllByUserId(user.getUserId());
    }

    @Override
    public Booking viewBooking(Integer id) {
        Booking booking = bookingRepository.findById(id).get();
        User user = userService.getCurrentUser();
        if(!booking.getUserId().equals(user.getUserId())){
           throw new RuntimeException("Booking doesn't exists");
        }
        return booking;
    }

    @Override
    public List<Booking> viewBookings() {
        User user = userService.getCurrentUser();
        return bookingRepository.findAllByUserId(user.getUserId());
    }

    @Override
    public List<Booking> deleteBooking(Integer id) {
        Booking booking = bookingRepository.findById(id).get();
        User user = userService.getCurrentUser();
        if(!booking.getUserId().equals(user.getUserId())){
            throw new RuntimeException("Booking doesn't exists");
        }
        bookingRepository.deleteById(id);
        return bookingRepository.findAllByUserId(user.getUserId());
    }

    @Override
    public List<Passenger> getPassengerDetails(Integer id) {
        Booking booking = bookingRepository.findById(id).get();
        User user = userService.getCurrentUser();
        if(!booking.getUserId().equals(user.getUserId())){
            throw new RuntimeException("Booking doesn't exists");
        }
        return booking.getPassengers();
    }
}
