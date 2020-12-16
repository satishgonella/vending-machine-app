package com.vm.vendendingmachine;

import java.math.BigDecimal;

public class Soda implements Item {

	private BigDecimal price = new BigDecimal("0.45");
	private String type = "soda";

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
