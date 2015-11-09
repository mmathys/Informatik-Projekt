package io.github.mmathys.Projekt.stocks;

public class Investment {
	private Stock stock;
	private float investmentValue;
	
	public Investment(Stock stock){
		this(stock, 0);
	}
	
	public Investment(Stock stock, float investmentValue) {
		this.stock = stock;
		this.investmentValue = investmentValue;
	}
	
	public float getInvestmentValue() {
		return investmentValue;
	}
	public void setInvestmentValue(float investmentValue) {
		this.investmentValue = investmentValue;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void updateChange() {
		//called once a day
		investmentValue+=investmentValue*getStock().getChangeFactor()/100f;
	}
}
