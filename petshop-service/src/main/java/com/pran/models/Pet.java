package com.pran.models;

import java.io.Serializable;

public class Pet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String type;
	private int count;
	private double price;
	private String size;

	public Pet() {
		// TODO Auto-generated constructor stub
	}

	public Pet(int id, String type, int count, double price, String size) {
		super();
		this.id = id;
		this.type = type;
		this.count = count;
		this.price = price;
		this.size = size;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public int getCount() {
		return count;
	}

	public double getPrice() {
		return price;
	}

	public String getSize() {
		return size;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", type=" + type + ", count=" + count + ", price=" + price + ", size=" + size + "]";
	}

}
