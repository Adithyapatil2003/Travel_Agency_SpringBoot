package com.cts.service.impl;

import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cts.dtos.AddressDto;
import com.cts.dtos.CustomerDetailsDto;
import com.cts.dtos.TourDetailsDto;
import com.cts.entities.Address;
import com.cts.entities.CustomerDetails;
import com.cts.entities.TourDetails;
import com.cts.exception.CustomerNotFoundException;
import com.cts.exception.TourNotFoundException;
import com.cts.repository.AddressRepository;
import com.cts.repository.CustomerDetailsRepository;
import com.cts.repository.TourDetailsRepository;

@ExtendWith(MockitoExtension.class)
class CustomerDetailsServiceImplTest {

	@Mock
	private CustomerDetailsRepository customerDetailsRepository;
	@Mock
	private TourDetailsRepository tourDetailsRepository;
	@Mock
	private AddressRepository addressRepository;
	@InjectMocks
	private CustomerDetailsServiceImpl customerDetailsServiceImpl;

	CustomerDetails customer1;
	CustomerDetails customer2;
	CustomerDetails customer3;
	CustomerDetailsDto custdto;
	CustomerDetailsDto custdto1;
	TourDetails tour1;
	TourDetails tour2;
	TourDetails tour3;
	TourDetailsDto tourdto;
	List<CustomerDetails> customerDetails = new ArrayList<CustomerDetails>();
	List<TourDetails> tourDetails = new ArrayList<TourDetails>();

	@BeforeEach
	void init() {
		// Customer 1
		Address permanentAddress1 = new Address(1, "123", "Main St", "Near Park", "Springfield", "IL", 62701);
		Address communicationAddress1 = new Address(1, "123", "Main St", "Near Park", "Springfield", "IL", 62701);
		tour1 = new TourDetails(1, "Springfield", 1500.00, "Chicago", Arrays.asList("Lincoln Home", "Millennium Park"),
				"Weekend Getaway");
		customer1 = new CustomerDetails(1, "John", "Doe", "1234567890", "Prefers window seat", permanentAddress1,
				communicationAddress1, tour1);

		// DTO
		AddressDto permanentAddressdto = new AddressDto("123", "Main St", "Near Park", "Springfield", "IL", 62701);
		AddressDto communicationAddressdto = new AddressDto("123", "Main St", "Near Park", "Springfield", "IL", 62701);
		tourdto = new TourDetailsDto(1, "Springfield", 1500.00, "Chicago",
				Arrays.asList("Lincoln Home", "Millennium Park"), "Weekend Getaway");
		custdto = new CustomerDetailsDto(1, "John", "Doe", "1234567890", "Prefers window seat", permanentAddressdto,
				communicationAddressdto, tourdto);
		custdto1 = new CustomerDetailsDto(2, "Mark", "John", "1334567890", null, null, null, tourdto);

		// Customer 2
		Address permanentAddress2 = new Address(2, "456", "Oak Ave", "Beside Lake", "Shelbyville", "KY", 40065);
		Address communicationAddress2 = new Address(2, "789", "Maple Dr", "Downtown", "Shelbyville", "KY", 40065);
		tour2 = new TourDetails(2, "Shelbyville", 2500.00, "Miami", Arrays.asList("South Beach", "Art Deco District"),
				"Beach Fun");
		customer2 = new CustomerDetails(2, "Jane", "Smith", "0987654321", "Allergic to nuts", permanentAddress2,
				communicationAddress2, tour2);

		// Customer 3
		Address permanentAddress3 = new Address(3, "789", "Pine Ln", "Opposite Mall", "Capital City", "NY", 12201);
		Address communicationAddress3 = new Address(3, "101", "Elm Rd", "Near Library", "Capital City", "NY", 12201);
		tour3 = new TourDetails(3, "Capital City", 3500.00, "San Francisco",
				Arrays.asList("Golden Gate Bridge", "Alcatraz"), "City Explorer");
		customer3 = new CustomerDetails(3, "Peter", "Jones", "1122334455", "Needs wheelchair access", permanentAddress3,
				communicationAddress3, tour3);

		customerDetails.add(customer1);
		customerDetails.add(customer2);
		customerDetails.add(customer3);
		tourDetails.add(tour1);
		tourDetails.add(tour2);
		tourDetails.add(tour3);
	}

	@Test
	void testGetAll() {

		when(customerDetailsRepository.findAll()).thenReturn(customerDetails);
		var res = customerDetailsServiceImpl.getAll();
		assertEquals(3, res.size());
		assertEquals("Peter", res.get(2).getFirstName());
		assertEquals("Springfield", res.get(0).getCommunuicationAdd().getCity());
		assertEquals("Beach Fun", res.get(1).getTour().getPackageName());
		verify(customerDetailsRepository, times(1)).findAll();
	}

	@Test
	void testGetById() {

		when(customerDetailsRepository.findById(anyInt())).thenReturn(Optional.of(customer1));
		var res = customerDetailsServiceImpl.getById(anyInt());
		assertNotNull(res);
		assertEquals("John", res.getFirstName());
		assertEquals("Weekend Getaway", res.getTour().getPackageName());
		assertEquals("Chicago", res.getTour().getDestLocation());
		verify(customerDetailsRepository, times(1)).findById(anyInt());

	}

	@Test
	void testNegativeGetById() {

		when(customerDetailsRepository.findById(anyInt())).thenThrow(CustomerNotFoundException.class);
		assertThrows(CustomerNotFoundException.class, () -> customerDetailsServiceImpl.getById(anyInt()));
		verify(customerDetailsRepository, times(1)).findById(anyInt());

	}

	@Test
	void testGetByLastName() {

		when(customerDetailsRepository.findBySecondName(anyString())).thenReturn(customerDetails);
		var res = customerDetailsServiceImpl.getByLastName(anyString());
		assertNotNull(res);
		assertEquals("Doe", res.get(0).getSecondName());
		verify(customerDetailsRepository, times(1)).findBySecondName(anyString());
	}

	@Test
	void testGetAllTours() {

		when(tourDetailsRepository.findAll()).thenReturn(tourDetails);
		var res = customerDetailsServiceImpl.getAllTours();
		assertEquals(3, res.size());
		assertEquals("Springfield", res.get(0).getStartLocation());
		assertEquals("Chicago", res.get(0).getDestLocation());
		assertEquals(1500, res.get(0).getCost());
		verify(tourDetailsRepository, times(1)).findAll();
	}

	@Test
	void testGetByTour() {

		when(tourDetailsRepository.findByPackageName(anyString())).thenReturn(tourDetails);
		when(customerDetailsRepository.findByTourDetails(any(TourDetails.class))).thenReturn(customerDetails);
		var res = customerDetailsServiceImpl.getByTour(anyString());
		assertEquals(3, res.size());
		assertEquals("City Explorer", res.get(2).getTour().getPackageName());
		verify(tourDetailsRepository, times(1)).findByPackageName(anyString());
		verify(customerDetailsRepository, times(1)).findByTourDetails(any(TourDetails.class));
	}

	@Test
	void testGetTourDesc() {

		List<String> loc = Arrays.asList("Lincoln Home", "Millennium Park");

		when(tourDetailsRepository.findByPackageName(anyString())).thenReturn(tourDetails);
		var res = customerDetailsServiceImpl.getTourDesc(anyString());
		assertEquals(loc, res.getLocationsCovered());
		assertEquals("Chicago", res.getDestLocation());
		verify(tourDetailsRepository, times(1)).findByPackageName(anyString());
	}

	@Test
	void testAddCustomer() {
		when(tourDetailsRepository.existsByPackageName(anyString())).thenReturn(true);
		when(tourDetailsRepository.findByPackageName(anyString())).thenReturn(tourDetails);
		when(tourDetailsRepository.save(any(TourDetails.class))).thenReturn(tour1);
		when(customerDetailsRepository.save(any(CustomerDetails.class))).thenReturn(customer1);
		var res = customerDetailsServiceImpl.addCustomer(custdto);
		assertEquals("Springfield", res.getCommunuicationAdd().getCity());
		assertEquals("Weekend Getaway", res.getTour().getPackageName());
		assertEquals("Millennium Park", res.getTour().getLocationsCovered().get(1));
		verify(tourDetailsRepository, times(1)).existsByPackageName(anyString());
		verify(customerDetailsRepository, times(1)).save(any(CustomerDetails.class));
		verify(tourDetailsRepository, times(1)).findByPackageName(anyString());
		verify(tourDetailsRepository, times(1)).save(any(TourDetails.class));

	}

	@Test
	void testNegativeAddCustomer() {
		when(tourDetailsRepository.existsByPackageName(anyString())).thenReturn(false);
		when(tourDetailsRepository.save(any(TourDetails.class))).thenReturn(tour1);
		when(customerDetailsRepository.save(any(CustomerDetails.class))).thenReturn(customer1);
		var res = customerDetailsServiceImpl.addCustomer(custdto);
		assertEquals("Springfield", res.getCommunuicationAdd().getCity());
		assertEquals("Weekend Getaway", res.getTour().getPackageName());
		assertEquals("Millennium Park", res.getTour().getLocationsCovered().get(1));
		verify(tourDetailsRepository, times(1)).existsByPackageName(anyString());
		verify(customerDetailsRepository, times(1)).save(any(CustomerDetails.class));
		verify(tourDetailsRepository, times(0)).findByPackageName(anyString());
		verify(tourDetailsRepository, times(1)).save(any(TourDetails.class));
	}

	@Test
	void testAddTour() {
		when(tourDetailsRepository.existsByPackageName(anyString())).thenReturn(true);
		when(tourDetailsRepository.findByPackageName(anyString())).thenReturn(tourDetails);
		when(tourDetailsRepository.save(any(TourDetails.class))).thenReturn(tour1);
		var res = customerDetailsServiceImpl.addTour(tourdto);
		verify(tourDetailsRepository, times(1)).existsByPackageName(anyString());
		verify(tourDetailsRepository, times(1)).findByPackageName(anyString());
		verify(tourDetailsRepository, times(1)).save(any(TourDetails.class));

	}

	@Test
	void testNegativeAddTour() {
		List<String> loc = Arrays.asList("Lincoln Home", "Millennium Park");

		when(tourDetailsRepository.existsByPackageName(anyString())).thenReturn(false);
		when(tourDetailsRepository.save(any(TourDetails.class))).thenReturn(tour1);
		var res = customerDetailsServiceImpl.addTour(tourdto);
		assertEquals(loc, res.getLocationsCovered());
		assertEquals("Chicago", res.getDestLocation());
		verify(tourDetailsRepository, times(1)).existsByPackageName(anyString());
		verify(tourDetailsRepository, times(0)).findByPackageName(anyString());
		verify(tourDetailsRepository, times(1)).save(any(TourDetails.class));
	}

	@Test
	void testUpdateCustomer() {
		when(customerDetailsRepository.findById(anyInt())).thenReturn(Optional.of(customer2));
		when(customerDetailsRepository.save(any(CustomerDetails.class))).thenReturn(customer2);
		var res = customerDetailsServiceImpl.updateCustomer(anyInt(), custdto);
		assertEquals("Springfield", res.getCommunuicationAdd().getCity());
		assertEquals("Weekend Getaway", res.getTour().getPackageName());
		assertEquals("Millennium Park", res.getTour().getLocationsCovered().get(1));
		verify(customerDetailsRepository, times(1)).save(any(CustomerDetails.class));
		verify(customerDetailsRepository, times(1)).findById(anyInt());
	}

	@Test
	void testUpdateTour() {

		when(tourDetailsRepository.findById(anyInt())).thenReturn(Optional.of(tour2));
		when(tourDetailsRepository.save(any(TourDetails.class))).thenReturn(tour1);
		var res = customerDetailsServiceImpl.updateTour(anyInt(), tourdto);
		assertEquals("Springfield", res.getStartLocation());
		assertEquals("Weekend Getaway", res.getPackageName());
		assertEquals(1500.00, res.getCost());
		assertEquals("Millennium Park", res.getLocationsCovered().get(1));
		verify(tourDetailsRepository, times(1)).findById(anyInt());
		verify(tourDetailsRepository, times(1)).save(any(TourDetails.class));
	}

	@Test
	void testNegativeUpdateTour() {

		when(tourDetailsRepository.findById(anyInt())).thenThrow(TourNotFoundException.class);
		assertThrows(TourNotFoundException.class, () -> customerDetailsServiceImpl.updateTour(anyInt(), tourdto));
		verify(tourDetailsRepository, times(1)).findById(anyInt());

	}

	@Test
	void testDeleteCustomer() {

		when(customerDetailsRepository.findById(anyInt())).thenReturn(Optional.of(customer1));
		customerDetailsServiceImpl.deleteCustomer(anyInt());
		verify(customerDetailsRepository, times(1)).findById(anyInt());
		verify(addressRepository, times(2)).deleteById(anyInt());
		verify(customerDetailsRepository, times(1)).deleteById(anyInt());
	}

	@Test
	void testNegativeDeleteCustomer() {

		when(customerDetailsRepository.findById(anyInt())).thenThrow(CustomerNotFoundException.class);
		assertThrows(CustomerNotFoundException.class, () -> customerDetailsServiceImpl.deleteCustomer(anyInt()));
		verify(customerDetailsRepository, times(1)).findById(anyInt());

	}

}
