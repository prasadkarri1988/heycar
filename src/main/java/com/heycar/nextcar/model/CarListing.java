package com.heycar.nextcar.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CarListing implements Serializable {

	private static final long serialVersionUID = 1L;

	public CarListing() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private String make;

	@Column
	private String model;

	@Column
	private Integer kW;

	@Column
	private String color;

	@Column
	private BigDecimal price;

	@Column
	private Integer year;

	public CarListing(String make, String model, Integer kW, String color, BigDecimal price, Integer year) {
		this.make = make;
		this.model = model;
		this.kW = kW;
		this.color = color;
		this.price = price;
		this.year = year;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getkW() {
		return kW;
	}

	public void setkW(Integer kW) {
		this.kW = kW;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
