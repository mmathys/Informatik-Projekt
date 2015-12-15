package io.github.mmathys.Projekt;

import static io.github.mmathys.Projekt.util.Ansi.*;

import java.util.Scanner;

import io.github.mmathys.Projekt.interact.Dialogue;
import io.github.mmathys.Projekt.interact.OptionQuestion;
import io.github.mmathys.Projekt.util.TerminalUtil;

public class Main {

	public static void main(String[] args) {
		// init basic objects
		Application app = new Application();

		Player player = new Player();

		Application.setPlayer(player);

		// set up intro dialogue
		Dialogue introDialogue = new Dialogue();
		introDialogue.addLine("Herzlich Willkommen zu Stocks Simulator.");
		introDialogue.addLine("Du fängst frisch als Investor bei NYSE an. Es ist dein erster Tag.");
		introDialogue.addLine("Dein Chef war so nett und spendierte dir ein Startkapital von " + TerminalUtil.moneyFormat(player.getCapital()) + ".");
		introDialogue.addLine("Dein Chef verlangt von dir, dass du in die besten Aktien investierst und einen Gewinn für das Geschäft erzielst.");
		introDialogue.addLine("Er wünscht dir viel Glück an der Börse.");
		app.dialogue(introDialogue);

		app.displayMenu();
	}
}
