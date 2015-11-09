package io.github.mmathys.Projekt.stocks;

import io.github.mmathys.Projekt.Application;
import io.github.mmathys.Projekt.GameParentElement;
import io.github.mmathys.Projekt.Player;
import io.github.mmathys.Projekt.interact.ActionHandler;
import io.github.mmathys.Projekt.interact.NumberQuestion;
import io.github.mmathys.Projekt.interact.OpenQuestion;
import io.github.mmathys.Projekt.interact.Question;
import io.github.mmathys.Projekt.util.ErrorHandler;
import io.github.mmathys.Projekt.util.TerminalUtil;

public class StockTransaction implements ErrorHandler, ActionHandler {

	enum Step {
		STOCK_NAME, AMOUNT, NONE;
	}
	
	private String stockShortName;

	private Step state = Step.NONE;
	private StockAction action;
	private GameParentElement parent;
	
	private Stock target;
	private Investment targetInvestment;

	public StockTransaction(StockAction action, GameParentElement parent) {
		this.action = action;
		this.parent = parent;
		startTransaction();
	}

	private void startTransaction() {
		// if sell: has stocks?
		if(action == StockAction.SELL && Application.getPlayer().getInvestments().size() == 0) {
			TerminalUtil.printError("Du hast keine Aktien, die du verkaufen kannst.");
			return;
		}
		
		// Step 1: Ask for Stock Name.
		state = Step.STOCK_NAME;
		ask();
	}

	private void ask(){
		Question q = null;
		
		switch(state) {
		case STOCK_NAME:
			q = new OpenQuestion(getQuestionString());
			break;
		case AMOUNT:
			q = new NumberQuestion(getQuestionString());
			break;
		}
		
		q.addHandler(this);
		q.addErrorHandler(this);
		parent.ask(q);
	}

	private void enterAmount() {
		state = Step.AMOUNT;
		ask();
	}

	private String getQuestionString() {
		String ret = "";
		if(state == Step.STOCK_NAME){
			if (action == StockAction.BUY) {
				ret+= "Welche Aktie willst du kaufen?";
			} else if (action == StockAction.SELL) {
				ret+= "Welche Aktie willst du verkaufen?";
			}
			ret+=" Gib den Aktienkürzel an.";
		}else if(state == Step.AMOUNT) {
			ret+="Wieviele Aktien willst du von "+target.getName()+" ";
			if(action == StockAction.BUY) {
				ret+= "kaufen?";
			}else if(action==StockAction.SELL) {
				ret+="verkaufen?";
			}
			ret+=" Gebe in USD ein.";
		}
		return ret;
	}

	@Override
	public void handle(Question q) {
		switch (state) {
		case STOCK_NAME:
			checkName(q);
			break;
		case AMOUNT:
			checkAmount(q);
			break;
		default:
			break;
		}

	}

	private void checkAmount(Question q) {
		float value = Float.parseFloat(q.getResponse());
		Player p = Application.getPlayer();
		if(action == StockAction.BUY){
			if(p.canBuy(value)){
				p.buy(target, value);
				System.out.print("\n"+TerminalUtil.info("Du hast erfolgreich "+target.getName()+"-Aktien im Wert von "+TerminalUtil.moneyFormat(value) +" gekauft."));
				TerminalUtil.continueIfEnterKeyPressed();
			}else{
				q.throwError("Du hast nicht genug Kapital, um so viel von diesen Aktien zu kaufen. Dein Kapital beträgt "+TerminalUtil.moneyFormat(p.getCapital()));
			}
		}else if(action == StockAction.SELL){
			if(value>targetInvestment.getInvestmentValue()) {
				System.out.println("So viel von diesen Aktien hast du nicht. Ich nehme an, du willst einfach alle verkaufen.");
				value = targetInvestment.getInvestmentValue();
			}
			
			p.sell(targetInvestment, value);
			
			System.out.print("\n"+TerminalUtil.info("Du hast erfolgreich deine "+target.getName()+"-Aktien im Wert von "+TerminalUtil.moneyFormat(value) +" verkauft."));
			TerminalUtil.continueIfEnterKeyPressed();
		}
	}

	private void checkName(Question q) {
		// if ok, continue to enterAmount();
		
		StockMarket market = StockFactory.getInstance();
		
		if(market.hasStock(q.getResponse()) == false) {
			q.setErrorMessage("Diese Aktie gibt es nicht.");
			handleError(q, true);
		}else{
			stockShortName = q.getResponse();
			target = market.getStockByShortName(stockShortName);
			
			// if sell: has stock?
			if(action == StockAction.SELL && !Application.getPlayer().hasStock(target)){
				TerminalUtil.printError("Du besitzt keine Aktien von "+target.getName());
				ask();
				return;
			}
			
			if(action == StockAction.BUY) {
				TerminalUtil.printStockInfo(target);
			}else if(action == StockAction.SELL){
				targetInvestment = Application.getPlayer().getInvestmentByShortName(stockShortName);
				TerminalUtil.printAccountStockInfo(targetInvestment);
			}
			enterAmount();
		}
	}

	@Override
	public void bind(GameParentElement fallback) {
	}

	@Override
	public void handleError(Question q, boolean retry) {
		// throw error, stop, and repeat question by ask();
		parent.handleError(q, retry);
		TerminalUtil.continueIfEnterKeyPressed();
		if (retry) {
			ask();
		}
	}

}
