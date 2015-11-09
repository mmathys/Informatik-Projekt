package io.github.mmathys.Projekt;

import static io.github.mmathys.Projekt.ApplicationState.*;
import static io.github.mmathys.Projekt.util.Ansi.*;

import java.util.Scanner;

import io.github.mmathys.Projekt.actions.Action;
import io.github.mmathys.Projekt.actions.BuyStockAction;
import io.github.mmathys.Projekt.actions.SellStockAction;
import io.github.mmathys.Projekt.actions.SleepAction;
import io.github.mmathys.Projekt.actions.ViewAccountAction;
import io.github.mmathys.Projekt.actions.ViewStocksAction;
import io.github.mmathys.Projekt.interact.ActionHandler;
import io.github.mmathys.Projekt.interact.Dialogue;
import io.github.mmathys.Projekt.interact.OptionQuestion;
import io.github.mmathys.Projekt.interact.Question;
import io.github.mmathys.Projekt.util.AnsiPrefixGenerator;
import io.github.mmathys.Projekt.util.ErrorHandler;
import io.github.mmathys.Projekt.util.TerminalUtil;

public class Application implements ErrorHandler, GameParentElement {
	private static final String ansi = format(
			"        WICHTIG: Die ANSI-Ausgabe scheint nicht zu funktionieren in diesem Terminal!      ", Color.RED,
			Color.RED);
	private static final String title = "Willkommen zu " + format("STOCKS SIMULATOR", Color.RED);

	private ApplicationState state;

	// Es gibt genau einen Spieler pro Applikation, also ist er statisch
	private static Player player;
	private OptionQuestion menu;

	public Application() {
		printTitle();
		state = INIT;
	}

	private void checkAnsi() {
		System.out.println(ansi);
	}

	private void printTitle() {
		checkAnsi();
		System.out.println("\n" + title + "\n");
		checkAnsi();
	}

	// Questions

	public void ask(Question question) {
		if (state == AVAITING_INPUT) {
			throw new IllegalStateException(
					"Es können nicht Fragen gestellt werden, bevor die letzte beantwortet wurde.");
		}
		state = AVAITING_INPUT;
		question.addErrorHandler(this);
		System.out.println("\n" + format("→ " + question.getQuestion(), Color.GREEN));

		if (question instanceof OptionQuestion) {
			OptionQuestion optionQuestion = (OptionQuestion) question;
			for (int index = 0; index < optionQuestion.getOptionStrings().size(); index++) {
				System.out.println(format("  • " + (index + 1) + ": " + optionQuestion.getOptionStrings().get(index),
						Color.GREEN));
			}
		}

		System.out.print("Antwort: ");

		Scanner s = new Scanner(System.in);
		question.setResponse(s.next());
		state = INIT;
		question.onResponse();
	}

	@Override
	public void handleError(Question q, boolean retry) {
		state = ERROR;
		System.out.print(format("     " + AnsiPrefixGenerator.getError() + "     ", Color.BLACK, Color.WHITE) + " ");
		System.out.print(format("⤫ " + q.getErrorMessage(), Color.RED));
		if (retry) {
			System.out.print(" (Enter um fortzufahren.)");
			TerminalUtil.continueIfEnterKeyPressed();
			this.ask(q);
		} else {
			System.out.print("\n");
		}
	}

	public void dialogue(Dialogue dialogue) {
		dialogue.startDialogue();
		int c = 0;
		while (dialogue.hasNextLine()) {
			System.out.print("\n" + dialogue.nextLine() + (c++ == 0 ? " (Enter um fortzufahren)" : ""));
			TerminalUtil.continueIfEnterKeyPressed();
		}
	}

	public void onFallback(Action handler) {
		// maybe do something with the handler... or maybe not.
		ask(menu);
	}

	public static Player getPlayer() {
		return player;
	}

	public static void setPlayer(Player player) {
		Application.player = player;
	}

	public void displayMenu() {
		// init actions
		ViewAccountAction account = new ViewAccountAction("Konto");
		account.bind(this);
		ViewStocksAction view = new ViewStocksAction("Aktienmarkt-Tafel");
		view.bind(this);
		BuyStockAction buy = new BuyStockAction("Akiten-Kauftresen, um Aktien zu kaufen");
		buy.bind(this);
		SellStockAction sell = new SellStockAction("Akiten-Kauftrese, um Aktien zu verkaufen");
		sell.bind(this);
		SleepAction sleep = new SleepAction("Zuhause");
		sleep.bind(this);

		// create menu and assign actions
		OptionQuestion menu = new OptionQuestion("Was willst du tun?");
		menu.addOption("Dein Konto ansehen", account);
		menu.addOption("Aktienmarkt betrachten", view);
		menu.addOption("Aktie kaufen", buy);
		menu.addOption("Aktie verkaufen", sell);
		menu.addOption("Schlafen gehen", sleep);
		this.menu = menu;
		ask(menu);
	}

}
