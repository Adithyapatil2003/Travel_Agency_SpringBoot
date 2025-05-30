package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dtos.CustomerDetailsDto;
import com.cts.dtos.TourDetailsDto;
import com.cts.service.CustomerDetailsService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/travel_agency")
@EnableMethodSecurity
public class CustomerDetailsController {

	@Autowired
	private CustomerDetailsService customerDetailsService;

	@GetMapping
	public ResponseEntity<List<CustomerDetailsDto>> getAllCustomers() {
		List<CustomerDetailsDto> customerDetailsDtos = customerDetailsService.getAll();
		return ResponseEntity.ok(customerDetailsDtos);
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerDetailsDto> getCustomerById(@PathVariable int id) {
		CustomerDetailsDto customerDetailsDto = customerDetailsService.getById(id);
		return ResponseEntity.ok(customerDetailsDto);
	}

	@GetMapping("/customers/lastname/{name}")
	public ResponseEntity<List<CustomerDetailsDto>> getCustomerByLastName(@PathVariable String name) {
		List<CustomerDetailsDto> customerDtos = customerDetailsService.getByLastName(name);
		return ResponseEntity.ok(customerDtos);
	}

	@GetMapping("/customers/tour/{name}")
	public ResponseEntity<List<CustomerDetailsDto>> getCustomerByPackageName(@PathVariable String name) {

		List<CustomerDetailsDto> customerDtos = customerDetailsService.getByTour(name);
		return ResponseEntity.ok(customerDtos);
	}

	@GetMapping("/tours")
	public ResponseEntity<List<TourDetailsDto>> getTours() {
		List<TourDetailsDto> dtos = customerDetailsService.getAllTours();
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/tours/{tourName}")
	public ResponseEntity<TourDetailsDto> getTourDesc(@PathVariable String tourName) {
		TourDetailsDto dtos = customerDetailsService.getTourDesc(tourName);
		return ResponseEntity.ok(dtos);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/customers")
	public ResponseEntity<CustomerDetailsDto> addCustomer(@Valid @RequestBody CustomerDetailsDto customerDetailsDto) {

		CustomerDetailsDto customerDetailsDto2 = customerDetailsService.addCustomer(customerDetailsDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(customerDetailsDto2);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/tours")
	public ResponseEntity<TourDetailsDto> addTour(@Valid @RequestBody TourDetailsDto tourDto) {

		TourDetailsDto tourDto2 = customerDetailsService.addTour(tourDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(tourDto2);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/customers/{id}")
	public ResponseEntity<CustomerDetailsDto> updateCustomer(@Valid @PathVariable int id,
			@RequestBody CustomerDetailsDto customerDetailsDto) {

		CustomerDetailsDto customerDetailsDto2 = customerDetailsService.updateCustomer(id, customerDetailsDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(customerDetailsDto2);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/tours/{id}")
	public ResponseEntity<TourDetailsDto> updateTour(@Valid @PathVariable int id, @RequestBody TourDetailsDto tourDto) {

		TourDetailsDto tourDto2 = customerDetailsService.updateTour(id, tourDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(tourDto2);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/customers/delete/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {

		customerDetailsService.deleteCustomer(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

}
