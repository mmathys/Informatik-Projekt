package io.github.mmathys.Projekt.actions;

import io.github.mmathys.Projekt.GameParentElement;
import static io.github.mmathys.Projekt.util.Ansi.*;
import io.github.mmathys.Projekt.interact.ActionHandler;
import io.github.mmathys.Projekt.interact.Question;
import io.github.mmathys.Projekt.util.TerminalUtil;
import io.github.mmathys.Projekt.util.Ansi.Color;

public abstract class Action implements ActionHandler {
	private GameParentElement fallback;
	private String title;

	// must be called in every child
	public Action(String title) {
		setTitle(title);
	}

	protected void printTitle() {
		System.out.print(format("\nℹ︎ Du bist nun hier: " + getTitle(), Color.GREEN));
	}

	@Override
	public void bind(GameParentElement fallback) {
		this.fallback = fallback;
	}

	public void fallback() {
		this.fallback.onFallback(this);
	}

	public GameParentElement getParent() {
		return fallback;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
