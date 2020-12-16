package com.vm.vendendingmachine;

public class Dime implements Coin {

	private int weight = 1;
	private int size = 1;

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public int getSize() {
		return size;
	}

}
