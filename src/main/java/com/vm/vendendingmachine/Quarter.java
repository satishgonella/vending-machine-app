package com.vm.vendendingmachine;

public class Quarter implements Coin {

	private int weight = 4;
	private int size = 4;

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public int getSize() {
		return size;
	}

}
