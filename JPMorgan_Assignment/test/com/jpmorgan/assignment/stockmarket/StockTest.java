package com.jpmorgan.assignment.stockmarket;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class StockTest {
	Stock stockJOE,stockGIN;
	@Before
	public void initializeStocks()
	{
		 stockJOE = new Stock("JOE", new CommonStock(), 13.0, 0.0, 250.0);
         stockGIN = new Stock("GIN", new PreferredStock(), 8.0, 0.2, 100.0);
		
	}
	
	@Test
	public void testComputeDividend()
	{
		
        // Compute dividend for common stock
        // As strategy  design pattern is used. the common stock type will be automatically called to compute dividend
		BigDecimal dividendTEA= stockJOE.computeDividend(new BigDecimal(121.0));
		BigDecimal expectedDividendALE = new BigDecimal(0.11);
		assertEquals (dividendTEA.doubleValue(),expectedDividendALE.doubleValue(), 0.0);
		  // Compute dividend for preferred stock
        // As strategy  design pattern is used. the preferred stock type will be automatically called to compute dividend
		BigDecimal dividendGIN = stockGIN.computeDividend(new BigDecimal(44.00));
		BigDecimal expectedDividendGIN = new BigDecimal(0.46);
		assertEquals( dividendGIN.doubleValue(),expectedDividendGIN.doubleValue(), 0.0);
	}
	
	@Test
	public void testComputePERatio()
	{
		BigDecimal computePERatioJOE = stockJOE.computePERatio(new BigDecimal(121.00));
		assertEquals(9.31, computePERatioJOE.doubleValue(), 0.0);
		BigDecimal computePERatioGIN = stockGIN.computePERatio(new BigDecimal(44.00));
		assertEquals(5.5, computePERatioGIN.doubleValue(), 0.0);
		
	}
	
	
}	
