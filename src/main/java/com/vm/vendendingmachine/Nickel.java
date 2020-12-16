package com.vm.vendendingmachine;

public class Nickel implements Coin {

	private int weight = 3;
	private int size = 3;

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public int getSize() {
		return size;
	}

}
