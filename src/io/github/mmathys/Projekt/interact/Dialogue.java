package io.github.mmathys.Projekt.interact;

import java.util.ArrayList;

public class Dialogue {
	private ArrayList<String> lines = new ArrayList<String>();
	
	private int lineCounter = 0;

	public void addLine(String s) {
		lines.add(s);
	}
	
	public void startDialogue() {
		lineCounter = 0;
	}
	
	public String nextLine() {
		if(lineCounter < lines.size()) {
			return lines.get(lineCounter++);
		} else {
			return null;
		}
	}
	
	public boolean hasNextLine() {
		return lineCounter+1 < lines.size();
	}
}
