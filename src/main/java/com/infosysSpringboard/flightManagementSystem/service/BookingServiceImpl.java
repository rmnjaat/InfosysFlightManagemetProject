package com.infosysSpringboard.flightManagementSystem.service;

import com.infosysSpringboard.flightManagementSystem.dao.BookingRepository;
import com.infosysSpringboard.flightManagementSystem.entity.Booking;
import com.infosysSpringboard.flightManagementSystem.entity.Passenger;
import com.infosysSpringboard.flightManagementSystem.entity.ScheduledFlight;
import com.infosysSpringboard.flightManagementSystem.entity.User;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

    private BookingRepository bookingRepository;
    private UserService userService;
    private EmailService emailService;
    private ScheduledFlightService scheduledFlightService;

    public BookingServiceImpl(BookingRepository bookingRepository, UserService userService, ScheduledFlightService scheduledFlightService , EmailService emailService) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.scheduledFlightService = scheduledFlightService;
        this.emailService = emailService;

    }

    @Override
    public List<Booking> addBooking(Booking booking) {
        ScheduledFlight scheduledFlight = scheduledFlightService.viewScheduledFlights(booking.getPnr(),booking.getFlight().getFlightNumber());
        if(scheduledFlight==null){
            throw new RuntimeException("There are no available flights");
        }
        scheduledFlight.setAvailableSeats(scheduledFlight.getAvailableSeats()-booking.getNoOfPassengers());
        booking.setCost(scheduledFlight.getCost()*booking.getNoOfPassengers());
        User user = userService.getCurrentUser();
        booking.setUserId(user.getUserId());
        booking.setDate(new Date());
        bookingRepository.save(booking);

        String userMail = user.getEmail();
        String bodyMail = String.valueOf(booking);
        String subjectMail = "Booking Confirmed";
        emailService.sendMail(userMail,subjectMail,bodyMail);

        return bookingRepository.findAllByUserId(user.getUserId());
    }

    @Override
    public List<Booking> modifyBooking(Booking booking) {
        ScheduledFlight scheduledFlight = scheduledFlightService.viewScheduledFlights(booking.getPnr(),booking.getFlight().getFlightNumber());
        if(scheduledFlight==null){
            throw new RuntimeException("There are no available flights");
        }
        scheduledFlight.setAvailableSeats(scheduledFlight.getAvailableSeats()-booking.getNoOfPassengers());
        booking.setCost(scheduledFlight.getCost()*booking.getNoOfPassengers());
        User user = userService.getCurrentUser();
        booking.setUserId(user.getUserId());
        booking.setDate(new Date());
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
        ScheduledFlight scheduledFlight = scheduledFlightService.viewScheduledFlights(booking.getPnr(),booking.getFlight().getFlightNumber());
        scheduledFlight.setAvailableSeats(scheduledFlight.getAvailableSeats()+booking.getNoOfPassengers());
        booking.getPassengers().clear();
        bookingRepository.save(booking);
        bookingRepository.deleteById(id);

        String userMail = user.getEmail();
        String bodyMail = String.valueOf(booking);
        String subjectMail = "Your following booking has deleted .";
        emailService.sendMail(userMail,subjectMail,bodyMail);
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
