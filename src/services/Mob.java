package services;

public class Mob extends Character{
	public int x;
	public int y;
	protected int mapx;
	protected int mapy;
	protected Player p;
	protected Board board;
	
	public Mob(int x, int y, Player p, Board b, int mapx, int mapy) {
		this.x = x;
		this.y = y;
		this.mapx = mapx;
		this.mapy = mapy;
		this.p = p;
		this.board = b;
	}
	public void goLeft() {
		String under = board.getContent(x, (y +1 + mapy)%mapy);
		String right = board.getContent((x-1 + mapx)%mapx, y);
		if(board.getPlayer().walkable.contains(under)) {
			if(board.getPlayer().livable.contains(right)) {
				this.x = (this.x - 1 + mapx) % mapx;
			}
		}
	}
	public void goRight() {
		String under = board.getContent(x, (y +1 + mapy)%mapy);
		String right = board.getContent((x+1+ mapx)%mapx, y);
		if(board.getPlayer().walkable.contains(under)) {
			if(board.getPlayer().livable.contains(right)) {
				this.x = (this.x + 1 + mapx) % mapx;
			}
		}
	}
	public void goUp() {
		String current = board.getContent(x, y);
		String next = board.getContent(x, (y-1 + mapy)%mapy);
		if(current == "LAD") {
			if(board.getPlayer().livable.contains(next)) {
				y = (y-1+mapy)%mapy;
			}
		}
		
	}
	public void goDown() {
		String current = board.getContent(x, y);
		String under = board.getContent(x, (y +1 + mapy)%mapy);
		String next = board.getContent(x, (y+1)%mapy);
		if(((current == "LAD")||(under == "LAD"))&&((under != "PLT")&&(under != "MTL"))) {
			if(board.getPlayer().livable.contains(next)) {
				y = (y+1+mapy)%mapy;
			}
		}
	}
}
