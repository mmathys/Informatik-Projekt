package io.github.mmathys.Projekt.interact;

public class NumberQuestion extends Question {


	public NumberQuestion(String question) {
		super(question);
	}

	@Override
	public void onResponse() {
		//1: check if response is number
		try{
			Float.parseFloat(getResponse());
			getHandlers().get(0).handle(this);
		}catch(NumberFormatException e){
			throwError("Bitte gib eine Nummer an.");
		}

	}

}
