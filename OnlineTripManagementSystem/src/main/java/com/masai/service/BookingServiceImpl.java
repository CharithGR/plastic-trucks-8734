package com.masai.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BookingException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.PackageException;
import com.masai.exceptions.RouteException;
import com.masai.exceptions.UserException;
import com.masai.models.Booking;
import com.masai.models.Bus;
import com.masai.models.CurrentUserSession;
import com.masai.models.Customer;
import com.masai.models.Package;
import com.masai.models.Route;
import com.masai.models.TicketDetails;
import com.masai.models.Travels;
import com.masai.models.User;
import com.masai.repository.BookingDAO;
import com.masai.repository.CustomerDAO;
import com.masai.repository.PackageDAO;
import com.masai.repository.RouteDAO;
import com.masai.repository.SessionDAO;
import com.masai.repository.TicketDetailsDAO;
import com.masai.repository.UserDAO;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	SessionDAO sessionDAO;
	
	@Autowired
	BookingDAO bookingDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	PackageDAO packageDAO;
	
	@Autowired
	CustomerDAO customerDAO;	
	
	@Autowired
	RouteDAO routeDAO;
	
	@Autowired
	TicketDetailsDAO ticketDetailsDAO;

	@Override
	public Booking makeBooking(Integer packageId,Integer noOftickets,Integer routeId, String uuid ) throws BookingException, UserException, PackageException {
		CurrentUserSession existingUser = sessionDAO.findByUuid(uuid);		
		if(existingUser == null) {
			throw new UserException("User not logged in");
		}
		//if(existingUser.getUserType().equalsIgnoreCase("admin"))throw new LoginException("Access Denied");
				
		Package currentPackage= packageDAO.findById(packageId).orElseThrow(()-> new PackageException("Invalid Package"));
		Route route=routeDAO.findById(routeId).orElseThrow(()->new RouteException("Invalid RouteId"));
		
		boolean flag=false;
		List<Route> listOfpackageRoute=currentPackage.getListOfRouteinPackage();
		for(Route x:listOfpackageRoute) {
			if(x.equals(route)) {
				flag=true;
					break;
			}
		}
		if(flag==false)throw new RouteException("This route is not avalable in the selected package");
		
		Customer customer=customerDAO.findById(existingUser.getUserId()).orElseThrow(()-> new CustomerException("Customer not found"));
		
		
		Booking booking=new Booking();
		booking.setBookingDate(LocalDate.now());
		booking.setBookingType(currentPackage.getPackageType());
		booking.setDescription(currentPackage.getPackageDescription());
		booking.setTitle(currentPackage.getPackageName());
		booking.setNoOfTickets(noOftickets);
		booking.setBookingUser(customer);		
		
		
		TicketDetails ticketDetails=new TicketDetails();	
		ticketDetails.setTicketRoute(route);
		ticketDetails.setTicketStatus("Booked");
		ticketDetails.setBookedTickets(booking);
//		System.out.println(route);
		
//		Travels travels=route.getBusRoute().getTravelBus();
//		ticketDetails.getTicketRoute().getBusRoute().setTravelBus(travels);
		
		
		TicketDetails bookedTicketDetails= ticketDetailsDAO.save(ticketDetails);
		
		booking.setBookedTicketsofCustomer(bookedTicketDetails);
		Booking savedBooking=bookingDAO.save(booking);
		
		
		return savedBooking;
	}

	@Override
	public String cancelBooking(Integer bookingId, String uuid) throws BookingException, UserException {
		
		CurrentUserSession existingUser = sessionDAO.findByUuid(uuid);
		
		if(existingUser == null) {
			throw new UserException("Please log in first");
		}
		
		Booking booking = bookingDAO.findById(bookingId).orElseThrow(()->new BookingException("There is no booking with bookinId :"+bookingId));
		
			TicketDetails ticketDetails=booking.getBookedTicketsofCustomer();
			
			LocalDate currentDate=LocalDate.now();
			LocalDate departureDate=ticketDetails.getTicketRoute().getDepartureTime().toLocalDate();
			LocalDate arrivalDate=ticketDetails.getTicketRoute().getArrivalTime().toLocalDate();
			if(currentDate.isBefore(departureDate)){
				bookingDAO.delete(booking);
				return "Booking canceled";
			}else {
				if(currentDate.isBefore(arrivalDate)) {
					throw new BookingException("Cannot cancel ongoing trip");
				}else {
					throw new BookingException("Cannot cancel completed trip");
				}				
				
			}
			
		
		
	}

	@Override
	public Booking viewBooking(Integer bookingId, String uuid) throws BookingException, UserException {
		
		CurrentUserSession existingUser =  sessionDAO.findByUuid(uuid);
		
		if(existingUser == null) {
			throw new UserException("Please log in");
		}
		
		Optional<Booking> optional = bookingDAO.findById(bookingId);
		
		if(optional.isPresent()) {
			Booking booking = optional.get();
			return booking;
		}
		else
			throw new BookingException("There is no booking with bookingId :"+bookingId);
	}

	@Override
	public List<Booking> viewAllBooking(String uuid) throws BookingException, UserException {
		
		CurrentUserSession exUserSession = sessionDAO.findByUuid(uuid);
		
		if(exUserSession == null) {
			throw new UserException("Please log in");
		}
		
		if(exUserSession.getUserType().equalsIgnoreCase("admin")) {
			
			List<Booking> bookings = bookingDAO.findAll();
			if(bookings.isEmpty()) {
				throw new BookingException("No booking till now");
			}
			else
				return bookings;
		}
		else {			
			
			
			Customer customer = customerDAO.findById(exUserSession.getUserId()).orElseThrow(()->new CustomerException("invalid Customer id"));
			
			
			
			List<Booking> bookings = bookingDAO.findByBookingUser(customer);
			
			
			if(bookings.isEmpty()) {
				throw new BookingException("No booking till now");
			}
			else
				return bookings;
		}
		
		
	}

}
