package com.infosysSpringboard.flightManagementSystem.service;

import com.infosysSpringboard.flightManagementSystem.entity.Booking;
import com.infosysSpringboard.flightManagementSystem.entity.Passenger;

import java.util.List;

public interface BookingService {
    public List<Booking> addBooking(Booking booking);
    public List<Booking> modifyBooking(Booking booking);
    public Booking viewBooking(Integer id);
    public List<Booking> viewBookings();
    public List<Booking> deleteBooking(Integer id);
    public List<Passenger> getPassengerDetails(Integer id);
}
