package io.github.mmathys.Projekt.actions;

import io.github.mmathys.Projekt.GameParentElement;
import io.github.mmathys.Projekt.interact.Question;
import io.github.mmathys.Projekt.stocks.StockAction;
import io.github.mmathys.Projekt.stocks.StockTransaction;
import io.github.mmathys.Projekt.util.TerminalUtil;

public class SellStockAction extends Action {

	public SellStockAction(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(Question q) {
		printTitle();
		
		TerminalUtil.continueIfEnterKeyPressed();
		
		StockTransaction transaction = new StockTransaction(StockAction.SELL, getParent());
		
		fallback();
	}

}
