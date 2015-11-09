package io.github.mmathys.Projekt.actions;

import java.util.List;

import io.github.mmathys.Projekt.GameParentElement;
import io.github.mmathys.Projekt.interact.Question;
import io.github.mmathys.Projekt.stocks.Stock;
import io.github.mmathys.Projekt.stocks.StockFactory;
import io.github.mmathys.Projekt.stocks.StockMarket;
import io.github.mmathys.Projekt.util.TerminalUtil;

public class ViewStocksAction extends Action {

	public final String tableHeader = "Name                          Änderung    Wert               ";

	public ViewStocksAction(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(Question q) {
		printTitle();

		TerminalUtil.continueIfEnterKeyPressed();

		StockMarket market = StockFactory.getInstance();

		List<Stock> stocks = market.getStocks();

		printStocks(stocks);

		TerminalUtil.continueIfEnterKeyPressed();

		fallback();
	}

	private void printStocks(List<Stock> stocks) {
		System.out.println(tableHeader);
		for (int i = 0; i < tableHeader.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
		for (Stock stock : stocks) {
			System.out.println(stock);
		}
	}

}
