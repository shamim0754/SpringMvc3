package de.tr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The java bean class for {@link Customer} objects. It specifies:
 * <ul>
 * <li>DB-Table</li>
 * <li>get-/set-methods</li>
 * <li>constructors</li>
 * </ul>
 * 
 * @author 
 */
@Entity
@Table(name="tblKunde")
public class Customer {

	@Id
	@GeneratedValue
	@Column(name="k_id")
	private int customerId;
	
	@Column(name="k_vorname")
	private String firstName;
	
	@Column(name="k_nachname")
	private String lastName;
	
	@Column(name="k_strasse")
	private String street;
	
	@Column(name="k_plz")
	private String zipCode;
	
	@Column(name="k_ort")
	private String place;
	
	@Column(name="k_fscheinklasse")
	private String licenseCategory;
	

	@Column(unique=true , name="k_benutzername")
	private String userName;
	
	@Column(name="k_passwort")
	private String password;
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getLicenseCategory() {
		return licenseCategory;
	}

	public void setLicenseCategory(String licenseCategory) {
		this.licenseCategory = licenseCategory;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
