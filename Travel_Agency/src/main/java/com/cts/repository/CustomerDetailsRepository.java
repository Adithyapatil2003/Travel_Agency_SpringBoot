package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entities.CustomerDetails;
import com.cts.entities.TourDetails;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Integer> {

	List<CustomerDetails> findBySecondName(String secondName);

	List<CustomerDetails> findByTourDetails(TourDetails tourDetails);
}
