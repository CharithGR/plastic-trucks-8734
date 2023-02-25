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
import com.masai.models.Admin;
import com.masai.models.Booking;
import com.masai.models.CurrentUserSession;
import com.masai.models.Customer;
import com.masai.models.TicketDetails;
import com.masai.repository.AdminDAO;
import com.masai.repository.BookingDAO;
import com.masai.repository.CustomerDAO;
import com.masai.repository.RouteDAO;
import com.masai.repository.SessionDAO;
import com.masai.repository.TicketDetailsDAO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private CustomerDAO customerD;
	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private SessionDAO sessionD;
	
	@Autowired
	RouteDAO routeDAO;
	
	@Autowired
	TicketDetailsDAO ticketDetailsDAO;
	
	@Autowired
	BookingDAO bookingDAO;
	
	
//	@Override
//	public Customer addCustomer(Customer cust) throws CustomerException {
//		
//		Customer existCustomer = customerD.findByEmail(cust.getEmail());
//		
//		if(existCustomer!=null) {
//			throw new CustomerException("Customer already exists");
//		}
//		
//		Customer new_cust = customerD.save(cust);
//		
//		return customerD.save(new_cust);
//		
//	}
//	
//
//	@Override
//	public Customer updateCustomer(Customer cust) throws CustomerException {
//		
//		Customer existCustomer = customerD.findByEmail(cust.getEmail());
//		
//		if(existCustomer!=null) {
//			Customer new_cust = customerD.save(cust);
//			
//			return customerD.save(new_cust);
//		}
//		else
//		{
//			return null;
//		}
//		
//		
//		
//	}
	

	@Override
	public String removeCustomer(Integer custId,String key) throws CustomerException {
		
		Optional<Customer> custOpt = customerD.findById(custId);
		
		if(custOpt!=null) {
			Customer customer=custOpt.get();
			List<Booking> listOfBookings=customer.getListOfBookingOfCustomer();
			Booking booking=listOfBookings.get(listOfBookings.size()-1);
			
			TicketDetails ticketDetails=booking.getBookedTicketsofCustomer();		
			LocalDate currentDate=LocalDate.now();
			LocalDate departureDate=ticketDetails.getTicketRoute().getDepartureTime().toLocalDate();
			LocalDate arrivalDate=ticketDetails.getTicketRoute().getArrivalTime().toLocalDate();	
			
			if(currentDate.isBefore(departureDate)){
				bookingDAO.delete(booking);
				return "Cannot Delete account,this customer has an upcoming trip";
			}else {
				if(currentDate.isBefore(arrivalDate)) {
					throw new BookingException("Cannot Delete account, this customer has an ongoing trip");
				}else {
					
					customerD.delete(customer);				
					return "Account Deleted";
				}								
			}		
			
		}
		else {
			throw new CustomerException("Invalid customer ID");
		}
					
	}
	

	@Override
	public Customer viewCustomer(Integer custId, String key){
		CurrentUserSession currentUserSession=sessionD.findByUuid(key);
		if(currentUserSession==null)throw new LoginException("Login to view customers/Invalid key");
		if(currentUserSession.getUserType().equalsIgnoreCase("customer"))throw new LoginException("Access Denied");
		
		Optional<Customer> custOpt = customerD.findById(custId);
		
		if(custOpt.isPresent())
			return custOpt.get();
		
		else
			throw new CustomerException("Customer is not found with given customerId "+custId);
	}


	@Override
	public void addAdmin() {
		
		Optional<Admin> admin=adminDAO.findById(Integer.MAX_VALUE);
		if(!admin.isPresent()) {
			Admin newAdmin=new Admin();
			newAdmin.setAdminId(Integer.MAX_VALUE);
			newAdmin.setAdminName("admin");
			newAdmin.setEmail("admin@admin.com");
			newAdmin.setPassword("admin");
			newAdmin.setMobile("9999999999");
			adminDAO.save(newAdmin);
		}
		
	}

	@Override
	public List<Customer> viewAllCustomer(String key ){
		CurrentUserSession currentUserSession=sessionD.findByUuid(key);
		if(currentUserSession==null)throw new LoginException("Login to view customers/Invalid key");
		if(currentUserSession.getUserType().equalsIgnoreCase("customer"))throw new LoginException("Access Denied");
		
		List<Customer> listOfCustomer=customerD.findAll();
		
		return listOfCustomer;
		
	}

	
	


}
