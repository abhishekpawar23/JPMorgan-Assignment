package com.jpmorgan.assignment.stockmarket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StockExchangeTest {

	 StockExchange gbce;
	 List<Stock> listOfStocks;
	@Before
	public  void initializeStockExchange()
	{
		gbce = new StockExchange();
		listOfStocks= new ArrayList<Stock>();
		listOfStocks.add(new Stock("TEA", new CommonStock(), 0.0, 0.0, 100.0));
		listOfStocks.add(new Stock("POP", new CommonStock(), 8.0, 0.0, 100.0));
		listOfStocks.add(new Stock("ALE", new CommonStock(), 23.0, 0.0, 60.0));
		listOfStocks.add(new Stock("GIN", new PreferredStock(), 8.0, 0.2, 100.0));
		listOfStocks.add(new Stock("JOE", new CommonStock(), 13.0, 0.0, 250.0));


	}
	@Test
	public void testRecordTrade()
	{
		for (Stock stock: listOfStocks) {
			

			// Time of trading can be configured to any duration 
			//Generating some random trades
			for (int i=1; i <= 100; i++) {

				try {
					Random r = new Random();
					if(r.nextBoolean())
					{
						Trade newTrade = new Trade("BUY",new BigDecimal(r.nextLong()) , new BigDecimal(r.nextDouble()), stock, System.currentTimeMillis());
						gbce.recordTrade(newTrade);
					}
					else
					{
						Trade newTrade = new Trade("SELL",new BigDecimal(r.nextLong()) , new BigDecimal(r.nextDouble()), stock, System.currentTimeMillis());
						gbce.recordTrade(newTrade);
					}


					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		assertNotNull(gbce.getTrades());
		
	}
	
	@Test
	public void testComputeVolWeightedPrice()
	{
		for(Stock stock : listOfStocks)
		{
			/*
			 * this loop will run 100 times for each stock creating 200 trades .
			therefore 
			sum of quantity = (250+125)*100 = 37500
			sum of trade price * quantity = (212.25 * 250) + (311.17 * 125) * 100 = 9195875
			therefore volume weighted stock price for each stock =  245.23
			*/
			
			for(int i=1; i <= 100; i++)
			{
				try {
				Trade buy = new Trade("BUY",new BigDecimal(250) , new BigDecimal(212.25), stock, System.currentTimeMillis());
				gbce.recordTrade(buy);
				Trade sell = new Trade("SELL",new BigDecimal(125) , new BigDecimal(311.17), stock, System.currentTimeMillis());
				gbce.recordTrade(sell);
					Thread.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			assertEquals(245.23, gbce.computeVolWeightedPrice(stock, 5).doubleValue(), 0.0);
		}
	}
	@Test
	public void testComputeExchangeIndex()
	{
		testComputeVolWeightedPrice();
		/*
		 * as volume weighted stock price for each stock =  245.23
		 * there are 5 stocks therefore sum = 245.23 * 5 = 1226.15
		 * therefore geometric mean = 1226.15 to the pow of (1/5) = 4.15
		 */
		assertEquals(4.15, gbce.computeExchangeIndex(listOfStocks).doubleValue(), 0.0);
	}

}
