package de.tr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The java bean class for {@link Rental} objects. It specifies:
 * <ul>
 * <li>DB-Table</li>
 * <li>get-/set-methods</li>
 * <li>constructors</li>
 * </ul>
 * 
 * @author 
 */
@Entity
@Table(name="TblVermietung")
public class Rental {

	@Id
	@GeneratedValue
	@Column(name="a_id")
	private int rentalId;
	
	@ManyToOne
	@JoinColumn(name = "k_id_f", nullable = false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "anh_id_f", nullable = false)
	private Trailer trailer;
	
	@GeneratedValue
	@Column(name="a_von")
	private Date dateOfRent;
	
	@Column(name="a_bis")
	private Date dateOfReturn;

	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

	public Date getDateOfRent() {
		return dateOfRent;
	}

	public void setDateOfRent(Date dateOfRent) {
		this.dateOfRent = dateOfRent;
	}

	public Date getDateOfReturn() {
		return dateOfReturn;
	}

	public void setDateOfReturn(Date dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}
	
	
	
}
