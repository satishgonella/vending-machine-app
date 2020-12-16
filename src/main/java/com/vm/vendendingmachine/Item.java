package com.vm.vendendingmachine;

import java.math.BigDecimal;

public interface Item {

	public void setPrice(BigDecimal price);

	public BigDecimal getPrice();

	public void setType(String type);

	public String getType();

}
