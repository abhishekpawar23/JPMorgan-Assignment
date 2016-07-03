package com.jpmorgan.assignment.stockmarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockExchange {
	private Map<String,List<Trade>> trades ;
	
	public void recordTrade(Trade newTrade)
	{
		List<Trade> listOfTrades = trades.get(newTrade.getStock().getStockScrip());
		if(null == listOfTrades)
		{
			listOfTrades = new ArrayList<Trade>();
		}
		listOfTrades.add(newTrade);
		trades.put(newTrade.getStock().getStockScrip(), listOfTrades);
	}

	public StockExchange() {
		trades = new HashMap<String, List<Trade>>();
	}
	
	//Configurable as per input.
	public List<Trade> getAllTradesForLastTimeFrame(Stock stock,int durationInMinutes)
	{
		List<Trade> listOfTrades = trades.get(stock.getStockScrip());
		List<Trade> listOfTradesInDuration = new ArrayList<Trade>();
		long currentTime = System.currentTimeMillis();
		for (Trade trade : listOfTrades)
		{
			if (currentTime - trade.getTimeStamp() <= durationInMinutes * 60 * 1000)
			{
				listOfTradesInDuration.add(trade);
			}
}
		return listOfTradesInDuration;
		
	}
	
	public BigDecimal computeExchangeIndex(List<Stock> listOfStocks)
	{
		BigDecimal exchangeIndex = BigDecimal.ZERO;
		for(Stock stock: listOfStocks) {
			exchangeIndex=exchangeIndex.add(stock.getVolumeWeightedPrice());
		}
		
		return new BigDecimal(Math.pow(exchangeIndex.doubleValue(), 1.0 / listOfStocks.size())).setScale(2,RoundingMode.CEILING);
		
		
		
	}
	
	public BigDecimal computeVolWeightedPrice(Stock stock, int duration	)
	{
		BigDecimal volumeWeightedPrice = BigDecimal.ZERO;
		BigDecimal sumOfTradeMultipliedQuantity = BigDecimal.ZERO;
		BigDecimal sumOfQuantity = BigDecimal.ZERO;
				
		List<Trade> tradesForLastTimeFrame = getAllTradesForLastTimeFrame(stock, duration);
		for(Trade trade : tradesForLastTimeFrame)
		{
			sumOfQuantity=sumOfQuantity.add(trade.getQuantity());
			sumOfTradeMultipliedQuantity=sumOfTradeMultipliedQuantity.add(trade.getPrice().multiply(trade.getQuantity()));
		}
		try{
		volumeWeightedPrice = sumOfTradeMultipliedQuantity.divide(sumOfQuantity, 2,RoundingMode.CEILING);
		
		stock.setVolumeWeightedPrice(volumeWeightedPrice);
				
		}
		catch (ArithmeticException e) {
			
		}
		return volumeWeightedPrice;
	}

	public Map<String, List<Trade>> getTrades() {
		return trades;
	}

	public void setTrades(Map<String, List<Trade>> trades) {
		this.trades = trades;
	}
	
	
}
