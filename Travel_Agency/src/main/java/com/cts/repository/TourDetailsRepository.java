package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entities.TourDetails;

public interface TourDetailsRepository extends JpaRepository<TourDetails, Integer> {

	List<TourDetails> findByPackageName(String packageName);
	boolean existsByPackageName(String packageName);
	
}
