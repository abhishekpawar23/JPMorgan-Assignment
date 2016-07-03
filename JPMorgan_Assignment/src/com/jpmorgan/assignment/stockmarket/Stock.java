package com.jpmorgan.assignment.stockmarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Stock {

	private String stockScrip;
	private StockType stockType;
	private BigDecimal lastDividend;
	private BigDecimal fixedDividend;
	private BigDecimal parValue;
	private BigDecimal volumeWeightedPrice;

	public String getStockScrip() {
		return stockScrip;
	}

	public void setStockScrip(String stockScrip) {
		this.stockScrip = stockScrip;
	}

	public BigDecimal getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(BigDecimal lastDividend) {
		this.lastDividend = lastDividend;
	}

	public BigDecimal getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(BigDecimal fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}

	public StockType getStockType() {
		return stockType;
	}

	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}


	public BigDecimal getVolumeWeightedPrice() {
		return volumeWeightedPrice;
	}

	public void setVolumeWeightedPrice(BigDecimal volumeWeightedPrice) {
		this.volumeWeightedPrice = volumeWeightedPrice;
	}

	public BigDecimal computePERatio(BigDecimal price){
		if(BigDecimal.ZERO.compareTo(this.getLastDividend()) == -1)
		{	
			return price.divide(this.getLastDividend(),2,RoundingMode.CEILING ) ;
		}
		else
		{
			//ideally should throw a business exception.
			return BigDecimal.ZERO;
		}
	}
	
	//Based on the Stock type this will call the appropriate Dividend Formulae.
	public BigDecimal computeDividend (BigDecimal price)
	{
		this.setLastDividend(this.getStockType().computeDividend(price, this));
		return this.getLastDividend();
	}
	
	public Stock(String stockScrip, StockType stockType,
			BigDecimal lastDividend, BigDecimal fixedDividend,
			BigDecimal parValue) {
		super();
		this.stockScrip = stockScrip;
		this.stockType = stockType;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
		this.volumeWeightedPrice = BigDecimal.ZERO;
	}
	public Stock(String stockScrip, StockType stockType,
			double lastDividend, double fixedDividend,
			double parValue) {
		super();
		this.stockScrip = stockScrip;
		this.stockType = stockType;
		this.lastDividend = new BigDecimal(lastDividend);
		this.fixedDividend = new BigDecimal(fixedDividend);
		this.parValue = new BigDecimal(parValue);
		this.volumeWeightedPrice = BigDecimal.ZERO;
	}
}
