package com.cts.entities;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tour_details")
public class TourDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "Starting Location cannot be empty")
	private String startLocation;
	@NotNull(message = "Package cost cannot be empty")
	private double cost;
	@NotNull(message = "Destination Location cannot be empty")
	private String destLocation;
	@ElementCollection  
	@CollectionTable(name="tour_locations",joinColumns = @JoinColumn(name="location_id"))
	@Column(name="locations")
	private List<String> locationsCovered;
	@NotNull(message = "Package Name cannot be empty")
	private String packageName;
}
