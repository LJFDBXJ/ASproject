package com.manger.base;

public class PhoneClass {
	private String name;
	private int image;
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public int getImage() {
		return image;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImage(int image) {
		this.image = image;
	}

	/**
	 * @param name
	 * @param image
	 */
	public PhoneClass(String name, int image) {
		super();
		this.name = name;
		this.image = image;
	}

	/**
	 * @param name
	 * @param number
	 */
	public PhoneClass(String name, String number) {
		super();
		this.name = name;
		this.number = number;
	}

}
