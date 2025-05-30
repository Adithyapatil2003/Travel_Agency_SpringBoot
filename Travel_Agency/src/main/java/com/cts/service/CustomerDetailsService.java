package com.cts.service;

import java.util.List;

import com.cts.dtos.AddressDto;
import com.cts.dtos.CustomerDetailsDto;
import com.cts.dtos.TourDetailsDto;

public interface CustomerDetailsService {

	// Retrieval
	// --------------------------------------------------------------------------//
	List<CustomerDetailsDto> getAll();

	CustomerDetailsDto getById(int id);

	List<CustomerDetailsDto> getByLastName(String lastName);

	List<CustomerDetailsDto> getByTour(String packageName);
	
	List<TourDetailsDto> getAllTours();
	
	TourDetailsDto getTourDesc(String packageName);
	// --------------------------------------------------------------------------//

	// Create
	// --------------------------------------------------------------------------//
	CustomerDetailsDto addCustomer(CustomerDetailsDto customerDetailsDto);

	TourDetailsDto addTour(TourDetailsDto tourDetailsDto);
	// --------------------------------------------------------------------------//

	// Update
	// ---------------------------------------------------------------------------//
	CustomerDetailsDto updateCustomer(int id, CustomerDetailsDto customerDetailsDto);

	TourDetailsDto updateTour(int id, TourDetailsDto tourDetailsDto);
	// ---------------------------------------------------------------------------//

	// Delete
	// ----------------------------------------------------------------------------//
	public void deleteCustomer(int id);
	// ---------------------------------------------------------------------------//
}
