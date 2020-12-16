package com.vm.vendendingmachine;

public class Penny implements Coin {

	private int weight = 2;
	private int size = 2;

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public int getSize() {
		return size;
	}

}
