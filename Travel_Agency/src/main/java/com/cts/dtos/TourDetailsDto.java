package com.cts.dtos;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class TourDetailsDto {

	private int id;
	@NotNull(message = "Starting Location cannot be empty")
	private String startLocation;
	@NotNull(message = "Package cost cannot be empty")
	@Positive(message="Package cost cannot be negative")
	private double cost;
	@NotNull(message = "Destination Location cannot be empty")
	private String destLocation;
	private List<String> locationsCovered;
	@NotNull(message = "Package Name cannot be empty")
	private String packageName;
}
