package com.aca.rest.dugoutmodel;

import java.time.LocalDate;

public class Player {
	
	private Integer id;
	private String lastName;
	private String firstName;
	
	private LocalDate birthDate;
	public Integer getId() {
		return id;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public LocalDate getBirthDate(LocalDate birthDate) {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	

}
