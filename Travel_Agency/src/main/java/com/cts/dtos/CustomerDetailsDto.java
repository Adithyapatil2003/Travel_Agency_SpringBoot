package com.cts.dtos;

import com.cts.entities.Address;
import com.cts.entities.TourDetails;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerDetailsDto {

	private int id;
	@NotNull(message="First name must not be empty")
	@Pattern(regexp = "[A-Za-z\s]{3,}",message="First name should contain alphabets and minimum 3 letters")
	private String firstName;
	@NotNull(message="Last name must not be empty")
	@Pattern(regexp = "[A-Za-z\s]{1,}",message="Last name should contain alphabets and minimum 1 letter")
	private String secondName;
	@NotNull(message = "Phone number should not be empty")
	@Pattern(regexp = "\\d{10}",message = "Phone number should contain 10 digits")
	private String phone;
	@Pattern(regexp = "[A-Za-z0-9\s]{0,260}", message = "Notes should not exceed 260 letters")
	private String notes;
	@Valid
	@NotNull(message = "Permanent Address should not be null")
	private AddressDto permanentAdd;
	@Valid
	@NotNull(message = "Communication Address should not be null")
	private AddressDto communuicationAdd;
	@Valid
	@NotNull(message = "Tour Details should not be null")
	private TourDetailsDto tour;
}
