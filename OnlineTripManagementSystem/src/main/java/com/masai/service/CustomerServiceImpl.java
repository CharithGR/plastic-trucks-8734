package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BookingException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.models.Booking;
import com.masai.models.CurrentUserSession;
import com.masai.models.Customer;
import com.masai.models.TicketDetails;
import com.masai.repository.BookingDAO;
import com.masai.repository.CustomerDAO;
import com.masai.repository.RouteDAO;
import com.masai.repository.SessionDAO;
import com.masai.repository.TicketDetailsDAO;





@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerD;
	
	
	@Autowired
	private SessionDAO sessionD;
	
	@Autowired
	RouteDAO routeDAO;
	
	@Autowired
	TicketDetailsDAO ticketDetailsDAO;
	
	@Autowired
	BookingDAO bookingDAO;
	
	
	
	@Override
	public Customer addCustomer(Customer cust) throws CustomerException {
		System.out.println(cust);
		Customer existCustomer = customerD.findByEmail(cust.getEmail());
		
		if(existCustomer!=null) {
			throw new CustomerException("Customer already exists");
		}
		
		Customer new_cust = customerD.save(cust);
		
		return customerD.save(new_cust);
		
	}
	

	@Override
	public Customer updateCustomer(Customer cust, String key) throws CustomerException {
		
		CurrentUserSession loggedInUser = sessionD.findByUuid(key);
		
		if(loggedInUser == null)
			throw new CustomerException("Please enter valid key");
		
		
		if(cust.getCustomerId() == loggedInUser.getUserId()) {
			return customerD.save(cust);
		}
		else {
			throw new CustomerException("Invalid customer details, please login first!");
		}
		
	}
	

	@Override
	public String removeCustomer(String key) throws CustomerException {
		
		CurrentUserSession loggedInUser = sessionD.findByUuid(key);
		
		if(loggedInUser == null)
			throw new CustomerException("Please enter valid key");
		
		Customer customer=customerD.findById(loggedInUser.getUserId()).orElseThrow(()->new LoginException("Invalid key"));
		
		List<Booking> listOfBookings=customer.getListOfBookingOfCustomer();
		Booking booking=listOfBookings.get(listOfBookings.size()-1);
		
		TicketDetails ticketDetails=booking.getBookedTickets();		
		LocalDate currentDate=LocalDate.now();
		LocalDate departureDate=ticketDetails.getTicketRoute().getDepartureTime().toLocalDate();
		LocalDate arrivalDate=ticketDetails.getTicketRoute().getArrivalTime().toLocalDate();		
		if(currentDate.isBefore(departureDate)){
			bookingDAO.delete(booking);
			return "Cannot Delete account, you have an upcoming trip, cancel the booking to delete account";
		}else {
			if(currentDate.isBefore(arrivalDate)) {
				throw new BookingException("Cannot Delete account, you have an ongoing trip");
			}else {
				
				customerD.delete(customer);				
				return "Account Deleted";
			}				
			
		}
		
		
		
		
					
	}
	

	@Override
	public Customer viewCustomer(String key) throws CustomerException {
		
		CurrentUserSession loggedInUser = sessionD.findByUuid(key);
		
		if(loggedInUser == null)
			throw new CustomerException("Please enter valid key");
		Customer customer=customerD.findById(loggedInUser.getUserId()).orElseThrow(()->new LoginException("Invalid key"));

			return customer;
		
	}

//	@Override
//	public List<Customer> viewAllCustomer(String location) throws CustomerException {
//		
//		List<Customer> customers = customerD.viewAllCustomer(location);
//		
//		if(customers.isEmpty())
//			throw new CustomerException("Customer is not found with given location: "+location);
//		
//		else {
//			List<Customer> customerList = new ArrayList<>(customers);
//			
//			return customerList;
//		}
//	}

	
	

}
