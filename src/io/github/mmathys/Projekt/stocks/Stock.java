package io.github.mmathys.Projekt.stocks;

import java.util.Random;

import io.github.mmathys.Projekt.util.Ansi;
import io.github.mmathys.Projekt.util.Ansi.Color;
import io.github.mmathys.Projekt.util.TerminalUtil;

public class Stock {
	private float valuation;
	private float volatility = 0.1f; // 0...1 = 0% to 100% * -1 to 1
	private String name;
	private String shortName;
	private int tabCount;

	// cycle values
	private float cyclePercent;
	private float changeFactor;

	public Stock(String shortName, String name, float valuation, float volatility) {
		this.name = name;
		this.shortName = shortName;
		this.valuation = valuation;
		this.volatility = volatility;
		this.tabCount = tabCount;
	}

	public float getValuation() {
		return valuation;
	}
	
	public String getFormattedValuation() {
		return String.format("%.2f", getValuation());
	}

	public void setValuation(float valuation) {
		this.valuation = valuation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getVolatility() {
		return volatility;
	}

	public void setVolatility(float volatility) {
		this.volatility = volatility;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void cycle() {
		Random r = new Random();
		float factor = r.nextFloat() * 2 - 1; // from -1 to 1
		// max volatility is +-80%
		changeFactor = factor * 0.8f * volatility;
		valuation += changeFactor * valuation;
		setCyclePercent(changeFactor * 100f);
	}

	public float getChangeFactor() {
		return cyclePercent;
	}
	
	public float getCyclePercent() {
		// auf zwei gerundet
		int a = (int) (cyclePercent * 100f);
		return a / 100f;
	}
	
	public String getCyclePercentString() {
		float p = getCyclePercent();
		if(p>0){
			return "+"+p;
		}else if(p == 0){
			return " "+p;
		}else{
			return Float.toString(p);
		}
	}

	public void setCyclePercent(float cyclePercent) {
		this.cyclePercent = cyclePercent;
	}
	
	public String getTabsAfter(String s, int max) {
		int remaining = max - s.length();
		String tabs = "";
		for(int i = 0; i<remaining; i++){
			tabs+=" ";
		}
		return tabs;
	}
	
	@Override
	public String toString() {
		String out = "";
		out += Ansi.format(getShortName(), Ansi.Attribute.UNDERLINE, null, null) + " " 
				+ getName() + getTabsAfter(getName(), 26) 
				+ TerminalUtil.formatCyclePercent(getCyclePercent(), getCyclePercentString()) + getTabsAfter(getCyclePercentString(), 9) 
				+ getTabsAfter(getFormattedValuation(), 19) + TerminalUtil.moneyFormat(getFormattedValuation());
		return out;
	}
}
