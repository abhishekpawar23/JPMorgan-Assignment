# JPMorgan-Assignment
Super Simple Stock Market

#Solution
The basic objective was to keep the code clean and simple. 
The approach was to stick to basic Java concepts of OOP.
The first major hurdle was to think of the options I had to implement the stock class.
Strategy Pattern was used to implement the Stock class and its types.
This gives us an ability to separate what is different from what otherwise is common.
Also its enables the stocks to change itself from a “Common” to a “Preferred” type at runtime without breaking any other code.
To keep the code as closely connected to the real world StockExchange was given major role.
It holds the list of trades as in real world.
It holds the ability to compute the Index.
Also as it hoolds the list of trades therefore it was an obvious choice to let the exchange compute the volume weighted index.

Stock class will compute the P/e ratio and the dividend.
Now based on the type of the stock the appropriate computeDividend() method will be called (Polymorphism).

 

#Classes
* Stock Exchange -> Holds the list of trades , used to compute the share Index and the volumeWeightedPrice

*Stock -> computes P/E ratio and computes dividends

*StockType (Interface) – To implement strategy pattern and separate  what is different
  CommonStockType – computes dividend for common stock
  PreferredStockType – computes dividend for preferred stock
  
* Trade -> contains trade related information

#Pre requisite 
• Please include Junit library to execute the Junits

