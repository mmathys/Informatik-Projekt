package io.github.mmathys.Projekt.interact;

import java.util.ArrayList;

public class OptionQuestion extends Question {

	private ArrayList<String> options = new ArrayList<String>();

	public OptionQuestion(String question) {
		super(question);
	}

	@Override
	public void onResponse() {
		if (getResponse() != null && getResponse().length() > 0) {
			int response = -1;
			try {
				response = Integer.parseInt(getResponse());
			} catch (NumberFormatException e) {
				throwError("Bitte gebe eine Zahl ein.");
				return;
			}

			if (response < 0) {
				throwError("Es gibt keine negativen Optionen.");
				return;
			}

			if (response == 0 || response > getOptions().size()) {
				throwError("Diese Option gibt es nicht. Wähle eine der aufgelisteten Optionen aus.");
				return;
			}

			// Pass Question to Handler
			getHandlers().get(response - 1).handle(this);

		} else {
			throwError("Bitte gebe eine Zahl zwischen 1 und " + (getOptions().size()) + " an.");
		}
	}

	public void addOption(String option, ActionHandler handler) {
		getOptions().add(option);
		getHandlers().add(handler);
	}

	// only for internal use
	private ArrayList<String> getOptions() {
		return options;
	}

	public ArrayList<String> getOptionStrings() {
		return options;
	}

	private void setOptions(ArrayList<String> options) {
		this.options = options;
	}

}
