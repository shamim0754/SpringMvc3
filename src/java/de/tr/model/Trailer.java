package de.tr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The java bean class for {@link Trailer} objects. It specifies:
 * <ul>
 * <li>DB-Table</li>
 * <li>get-/set-methods</li>
 * <li>constructors</li>
 * </ul>
 * 
 * @author 
 */
@Entity
@Table(name="tblAnhaenger")
public class Trailer {

	@Id
	@GeneratedValue
	@Column(name="anh_id")
	private int trailerId;
	
	@Column(name="anh_titel")
	private String title;
	
	@Column(name="anh_eigenschaft")
	private String carryingCapacity;
	
	@Column(name="anh_tagesgebuehr")
	private String dailyFee;

	
	@Column(name="anh_status" , columnDefinition="tinyint default '0' comment '0 indicates available trailers'")
	private int status;
	
	
	public int getTrailerId() {
		return trailerId;
	}

	public void setTrailerId(int trailerId) {
		this.trailerId = trailerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCarryingCapacity() {
		return carryingCapacity;
	}

	public void setCarryingCapacity(String carryingCapacity) {
		this.carryingCapacity = carryingCapacity;
	}

	public String getDailyFee() {
		return dailyFee;
	}

	public void setDailyFee(String dailyFee) {
		this.dailyFee = dailyFee;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
