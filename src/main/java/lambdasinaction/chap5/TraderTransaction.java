package lambdasinaction.chap5;

/**
 * 5.5.1 交易和交易员
 * 结合 TraderPractice
 * */
public class TraderTransaction {

	private Trader trader;
	private int year;
	private int value;

	// 交易
	public TraderTransaction(Trader trader, int year, int value)
	{
		this.trader = trader; // 交易员
		this.year = year;     // 年份
		this.value = value;   // 交易额
	}

	public Trader getTrader(){ 
		return this.trader;
	}

	public int getYear(){
		return this.year;
	}

	public int getValue(){
		return this.value;
	}
	
	public String toString(){
	    return "{" + this.trader + ", " +
	           "year: "+this.year+", " +
	           "value:" + this.value +"}";
	}
}