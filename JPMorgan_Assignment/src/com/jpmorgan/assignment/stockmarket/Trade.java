package com.jpmorgan.assignment.stockmarket;

import java.math.BigDecimal;


public class Trade {
	private String type;
	private BigDecimal quantity;
	private BigDecimal price;
	private Stock stock;
	private long timeStamp;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Trade(String type, BigDecimal quantity, BigDecimal price,
			Stock stock, long timeStamp) {
		super();
		this.type = type;
		this.quantity = quantity;
		this.price = price;
		this.stock = stock;
		this.timeStamp = timeStamp;
	}

	
}

