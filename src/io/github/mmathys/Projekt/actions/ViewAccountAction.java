package io.github.mmathys.Projekt.actions;

import io.github.mmathys.Projekt.GameParentElement;
import io.github.mmathys.Projekt.Player;
import io.github.mmathys.Projekt.interact.Question;
import io.github.mmathys.Projekt.stocks.Investment;
import io.github.mmathys.Projekt.util.Ansi;
import io.github.mmathys.Projekt.util.AnsiPrefixGenerator;
import io.github.mmathys.Projekt.util.TerminalUtil;

import static io.github.mmathys.Projekt.util.Ansi.*;

import io.github.mmathys.Projekt.Application;

public class ViewAccountAction extends Action {
	
	public ViewAccountAction(String title) {
		super(title);
	}

	@Override
	public void handle(Question q) {
		printTitle();
		printAccountInfo();
	}

	private void printAccountInfo() {
		TerminalUtil.continueIfEnterKeyPressed();

		Player p = Application.getPlayer();
		String money = TerminalUtil.moneyFormat(p.getCapital());
		
		System.out.println("Kapital: "+money);
				
		printStocks();
		
		TerminalUtil.continueIfEnterKeyPressed();
				
		//TODO print vermögen, vergleiche das vermögen, nicht das kapital.
		if(p.getCapital()>=20000) {
			System.out.println(TerminalUtil.highlight(AnsiPrefixGenerator.getError()) + format(" ! Dein Chef wundert sich, wieso du so viel Geld in der Tasche hast und es nicht investierst.", Color.RED));
		}
		
		if(p.getCapital() > 20000) {
			System.out.println(TerminalUtil.highlight(AnsiPrefixGenerator.getHappy())+format(" ! Dein Chef ist zufrieden mit dir. Du machst Gewinn.", Color.GREEN));
		}else if(p.getCapital()< 100/*keep capital*/) {
			System.out.println(TerminalUtil.highlight(AnsiPrefixGenerator.getSad())+format(" ! Dein Rücken kratzt, deine letzte Nacht hast du auf der Strasse verbracht, du hast kein Geld.", Color.RED));
		}else if(p.getCapital() < 20000) {
			System.out.println(TerminalUtil.highlight(AnsiPrefixGenerator.getSad())+format(" ! Dein Chef überlegt sich, dich zu feuern mit deinen Finanzen sieht es im Moment nicht sehr gut aus.", Color.RED));
		}
		
		System.out.println();
				
		//TODO list stocks
		
		TerminalUtil.continueIfEnterKeyPressed();
		
		fallback();
		
	}

	private void printStocks() {
		System.out.println(Ansi.format("Aktien:", Attribute.UNDERLINE, null, null));
		for(Investment i : Application.getPlayer().getInvestments()) {
			TerminalUtil.printAccountStockInfo(i);
		}
	}

}
