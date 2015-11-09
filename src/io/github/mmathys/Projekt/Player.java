package io.github.mmathys.Projekt;

import java.util.ArrayList;

import io.github.mmathys.Projekt.stocks.Investment;
import io.github.mmathys.Projekt.stocks.Stock;

public class Player {
	private float capital = 20000;
	private ArrayList<Investment> investments = new ArrayList<Investment>();
	
	public void addInvestement(Investment investment) {
		getInvestments().add(investment);
	}

	public ArrayList<Investment> getInvestments() {
		return investments;
	}

	public void setInvestments(ArrayList<Investment> investments) {
		this.investments = investments;
	}

	public float getCapital() {
		return capital;
	}

	public void setCapital(float capital) {
		this.capital = capital;
	}
	
	public boolean canBuy(float amount) {
		return amount <= capital;
	}

	public void buy(Stock target, float value) {
		//search for investment.
		Investment buy = null;
		
		for(Investment i : investments) {
			if(i.getStock().getName().equals(target.getName())){
				buy = i;
			}
		}
		
		if(buy==null){
			buy = new Investment(target, value);
			investments.add(buy);
		}else{
			buy.setInvestmentValue(buy.getInvestmentValue()+value);
		}
		
		this.capital-=value;
	}
	
	public void sell(Investment target, float value) {
		if(target.getInvestmentValue() == value) {
			getInvestments().remove(target);
		}else{
			target.setInvestmentValue(target.getInvestmentValue()-value);
			for(int i = 0; i<getInvestments().size(); i++) {
				if(target.getStock().getName().equals(getInvestments().get(i).getStock().getName())) {
					getInvestments().set(i, target);
				}
			}
		}
		
		this.capital+=value;
	}

	public boolean hasStock(Stock target) {
		for(Investment i : getInvestments()) {
			if(i.getStock().getName().equals(target.getName())) {
				return true;
			}
		}
		return false;
	}

	public Investment getInvestmentByShortName(String stockShortName) {
		stockShortName = stockShortName.toUpperCase().trim();
		
		for(Investment i : getInvestments()){
			if(i.getStock().getShortName().equals(stockShortName)){
				return i;
			}
		}
		return null;
	}
}
