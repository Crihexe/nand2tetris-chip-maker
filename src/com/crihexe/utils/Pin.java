package com.crihexe.utils;

import java.util.ArrayList;

public class Pin {
	
	private String name;
	private ArrayList<Pin> nexts = new ArrayList<Pin>();
	
	private boolean state;
	
	public Pin(String name) {
		this.name = name;
		state = false;
	}
	
	public void addPin(Pin pin) {
		nexts.add(pin);
	}
	
	public void removePin(Pin pin) {
		nexts.remove(pin);
	}
	
	public String getName() {
		return name;
	}
	
	public boolean state() {
		return state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
}
