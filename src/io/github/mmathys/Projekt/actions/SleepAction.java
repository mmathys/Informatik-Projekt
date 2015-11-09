package io.github.mmathys.Projekt.actions;

import static io.github.mmathys.Projekt.util.Ansi.format;

import java.util.ArrayList;

import io.github.mmathys.Projekt.Application;
import io.github.mmathys.Projekt.GameParentElement;
import io.github.mmathys.Projekt.interact.Question;
import io.github.mmathys.Projekt.stocks.Investment;
import io.github.mmathys.Projekt.stocks.StockFactory;
import io.github.mmathys.Projekt.stocks.StockMarket;
import io.github.mmathys.Projekt.util.AnsiPrefixGenerator;
import io.github.mmathys.Projekt.util.TerminalUtil;
import io.github.mmathys.Projekt.util.Ansi.Color;

public class SleepAction extends Action {

	public SleepAction(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(Question q) {
		printTitle();

		// TODO: Stocks neu generieren via call.
		// Investments aktualisieren.

		StockFactory.getInstance().nextDay();

		ArrayList<Investment> investments = Application.getPlayer().getInvestments();

		for (Investment i : investments) {
			i.updateChange();
		}

		System.out.print("\n\n" + TerminalUtil.highlight(AnsiPrefixGenerator.getSleep())
				+ format(
						" Du betest vor dem Einschlafen für Lakshmi, die hinduistische Göttin des Reichtums.\n               Sie spricht dir Erfolg zu und du schläfst ruhig ein.",
						Color.GREEN));

		TerminalUtil.continueIfEnterKeyPressed();

		fallback();
	}

}
