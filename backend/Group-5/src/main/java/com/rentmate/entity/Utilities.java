package com.rentmate.entity;

public class Utilities {
	private Integer gas;
	private Integer water;
	private Integer internet;
	private Integer electric;
	
	public Utilities() {}
	
	public Utilities(Integer gas, Integer water, Integer internet, Integer electric) {
		this.gas = gas;
		this.water = water;
		this.electric = electric;
		this.internet = internet;
	}

	public Integer getGas() {
		return gas;
	}

	public void setGas(Integer gas) {
		this.gas = gas;
	}

	public Integer getWater() {
		return water;
	}

	public void setWater(Integer water) {
		this.water = water;
	}

	public Integer getInternate() {
		return internet;
	}

	public void setInternate(Integer internet) {
		this.internet = internet;
	}

	public Integer getElectric() {
		return electric;
	}

	public void setElectric(Integer electric) {
		this.electric = electric;
	}
	
	
}
