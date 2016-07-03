package com.jpmorgan.assignment.stockmarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface StockType {
	BigDecimal computeDividend(BigDecimal price , Stock stock);
}


class CommonStock  implements StockType
{

	public BigDecimal computeDividend(BigDecimal price,  Stock stock) {
		BigDecimal dividend = BigDecimal.ZERO;
		if (null != price)
		{
			dividend=stock.getLastDividend().divide(price,2,RoundingMode.CEILING);
		}
		return dividend;
	}
	
}
class PreferredStock implements StockType
{

	public BigDecimal computeDividend(BigDecimal price, Stock stock) {
		BigDecimal dividend = BigDecimal.ZERO;
		if (null != price)
		{
			dividend = stock.getFixedDividend().multiply(stock.getParValue()).divide(price,2,RoundingMode.CEILING);
		}
		return dividend;
	}
	
}