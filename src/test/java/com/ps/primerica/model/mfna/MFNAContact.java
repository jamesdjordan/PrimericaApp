package com.ps.primerica.model.mfna;

public class MFNAContact {
	
	private String clientLocation;
	private PersonalInfo client;
	private PersonalInfo spouse;
	
	public String getClientLocation() {
		return clientLocation;
	}
	public void setClientLocation(String clientLocation) {
		this.clientLocation = clientLocation;
	}
	public PersonalInfo getClient() {
		return client;
	}
	public void setClient(PersonalInfo client) {
		this.client = client;
	}
	public PersonalInfo getSpouse() {
		return spouse;
	}
	public void setSpouse(PersonalInfo spouse) {
		this.spouse = spouse;
	}
}
