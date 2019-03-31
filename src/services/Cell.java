package services;

import java.util.Timer;

public class Cell {
	public int x;
	public int y;
	protected String content;
	
	public Cell(String content, int x, int y) {
		this.content = content;
		this.x = x;
		this.y = y;
	}
	public void setContent(String c) {
		content = c;
	}
	public String getContent(){
		return content;
	}
	public void dig() {
		content = "HOL";
		Timer timer = new Timer();
		TimerTaskFill task = new TimerTaskFill();
		task.setC(this);
		timer.schedule(task, 2000);
	}
	public void fill() {
		content = "PLT";
	}
}