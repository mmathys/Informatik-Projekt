package io.github.mmathys.Projekt.util;

import static io.github.mmathys.Projekt.ApplicationState.ERROR;
import static io.github.mmathys.Projekt.util.Ansi.*;

import java.util.Scanner;

import io.github.mmathys.Projekt.stocks.Investment;
import io.github.mmathys.Projekt.stocks.Stock;
import io.github.mmathys.Projekt.util.Ansi.Color;

public class TerminalUtil {
	public static void continueIfEnterKeyPressed() {
		Scanner scanner = new Scanner(System.in);
	    scanner.nextLine();
	}
	
	public static void enterBlock() {
		System.out.print("(Enter)");
		continueIfEnterKeyPressed();
	}

	public static String moneyFormat(float money) {
		return moneyFormat(Float.toString(money));
	}
	
	public static String moneyFormat(String money) {
		return format(money+" USD", Attribute.REVERSE, Color.BLUE, null);
	}
	
	public static String highlight(String whatever) {
		return format(whatever, Color.BLACK, Color.WHITE);
	}

	public static String formatCyclePercent(float original, String cyclePercentString) {
		if(original==0){
			return cyclePercentString;
		}else if(original > 0) {
			return format(cyclePercentString, Color.GREEN);
		}else {
			return format(cyclePercentString, Color.RED);
		}
	}
	
	public static String info(String msg) {
		return format("ℹ "+msg, Color.GREEN);
	}
	
	public static String altInfo(String msg) {
		return "ℹ "+msg;
	}
	
	public static void printStockInfo(Stock target) {
		System.out.println(TerminalUtil.altInfo(target.getName()
				+" ("+target.getShortName()+"), Kurs: "
				+TerminalUtil.formatCyclePercent(target.getCyclePercent(), target.getCyclePercentString())
				+" Gesamtwert: "+TerminalUtil.moneyFormat(target.getValuation())));
	}
	
	public static void printAccountStockInfo(Investment i) {
		System.out.println(TerminalUtil.altInfo(i.getStock().getName()
				+" ("+i.getStock().getShortName()+"), Kurs: "
				+TerminalUtil.formatCyclePercent(i.getStock().getCyclePercent(), i.getStock().getCyclePercentString())
				+" Wert: "+TerminalUtil.moneyFormat(i.getInvestmentValue())));
	}
	
	public static void printError(String msg) {
		System.out.print(format("     " + AnsiPrefixGenerator.getError() + "     ", Color.BLACK, Color.WHITE) + " ");
		System.out.print(format("⤫ " + msg, Color.RED));
		continueIfEnterKeyPressed();
	}
	
	// reverse, black, white = night.
	// bright, red = minus?
	// bright, green = plus?
}
