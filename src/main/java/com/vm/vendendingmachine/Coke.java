package com.vm.vendendingmachine;

import java.math.BigDecimal;

public class Coke implements Item {

	private BigDecimal price = new BigDecimal("0.25");
	private String type = "coke";

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return type;
	}

}
