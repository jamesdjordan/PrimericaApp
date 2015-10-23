package com.ps.primerica.model;

public class PersonalInfo {

	private PersonalData client;
	private PersonalData spouse;
	
	public PersonalData getClient() {
		return client;
	}
	public void setClient(PersonalData client) {
		this.client = client;
	}
	public PersonalData getSpouse() {
		return spouse;
	}
	public void setSpouse(PersonalData spouse) {
		this.spouse = spouse;
	}
}
