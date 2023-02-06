package com.crihexe.utils;

import java.util.ArrayList;

public class PinList<E> extends ArrayList<E> {
	private static final long serialVersionUID = 8235118964623985156L;
	
	private boolean inputs;
	
	public PinList(boolean inputs) {
		super();
		this.inputs = inputs;
	}
	
	public boolean hasInputs() {
		return inputs;
	}
	
}
