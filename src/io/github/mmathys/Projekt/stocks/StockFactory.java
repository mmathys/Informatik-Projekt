package io.github.mmathys.Projekt.stocks;

public class StockFactory {
	private static StockMarket instance;

	public static StockMarket getInstance() {
		if (instance == null) {
			instance = new StockMarket();
			// Do initial cycle
			instance.nextDay();
		}
		return instance;
	}
}
