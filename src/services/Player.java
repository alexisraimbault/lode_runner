package services;

import java.util.ArrayList;
import java.util.List;

public class Player  extends Character{
	protected int x;
	protected int y;
	protected Board board;
	public List<String> walkable;
	public List<String> livable;
	protected int mapx, mapy;
	public Player(Board board, int x, int y, int mapx, int mapy) {
		this.x = x;
		this.y = y;
		this.mapx = mapx;
		this.mapy = mapy;
		this.board = board;
		walkable = new ArrayList<String>();
		livable = new ArrayList<String>();
		walkable.add("PLT");
		walkable.add("MTL");
		walkable.add("LAD");
		livable.add("EMP");
		livable.add("LAD");
	}
	public void goLeft() {
		String under = board.getContent(x, (y +1 + mapy)%mapy);
		String right = board.getContent((x-1 + mapx)%mapx, y);
		if(walkable.contains(under)) {
			if(livable.contains(right)) {
				this.x = (this.x - 1 + mapx) % mapx;
			}
		}
	}
	
	public void goRight() {
		String under = board.getContent(x, (y +1 + mapy)%mapy);
		String right = board.getContent((x+1+ mapx)%mapx, y);
		if(walkable.contains(under)) {
			if(livable.contains(right)) {
				this.x = (this.x + 1 + mapx) % mapx;
			}
		}
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void digLeft() {
		String toDig = board.getContent((x-1+mapx)%mapx, (y+1+mapy)%mapy);
		String left = board.getContent((x-1+mapx)%mapx, y);
		if(livable.contains(left)&&(toDig == "PLT")) {
			board.getCell((x-1+mapx)%mapx, (y+1+mapy)%mapy).dig();
		}
	}
	public void digRight() {
		String toDig = board.getContent((x+1+mapx)%mapx, (y+1+mapy)%mapy);
		String right = board.getContent((x+1+mapx)%mapx, y);
		if(livable.contains(right)&&(toDig == "PLT")) {
			board.getCell((x+1+mapx)%mapx, (y+1+mapy)%mapy).dig();
		}
	}
	public void goUp() {
		String current = board.getContent(x, y);
		String next = board.getContent(x, (y-1 + mapy)%mapy);
		if(current == "LAD") {
			if(livable.contains(next)) {
				y = (y-1+mapy)%mapy;
			}
		}
		
	}
	public void goDown() {
		String current = board.getContent(x, y);
		String under = board.getContent(x, (y +1 + mapy)%mapy);
		String next = board.getContent(x, (y+1)%mapy);
		if(((current == "LAD")||(under == "LAD"))&&((under != "PLT")&&(under != "MTL"))) {
			if(livable.contains(next)) {
				y = (y+1+mapy)%mapy;
			}
		}
	}
}
