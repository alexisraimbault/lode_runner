package services;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class Board extends JPanel{
	private Cell[][] board;
	private List<Mob> mobs;
	private Player p;
	private int mapx;
	private int mapy;
	final static int INF = 99999;
	private int path[][];
	private Cell pred[][];
	private List<Cell> nodes;
	
	public Board(int dimx, int dimy) {
		mapx = dimx;
		mapy = dimy;
		board = new Cell[dimx][dimy];
		mobs = new ArrayList<Mob>();
		mobs.add(new Mob(15,10,p,this,dimx, dimy));
		p = new Player(this, 16, 10, dimx, dimy);
		init();
	}
	public void init() {
		boolean unsur2 = true;
		for(int i = 0; i < mapx; i++)
  		{
  			for(int j = 0; j < mapy; j++)
  			{
  				if(j == mapy - 3){
  					board[i][j] = new Cell("MTL", i, j);
  				}else {
  					if(j%2 == 0) {
  						if(board[i][j] == null)
  							board[i][j] = new Cell("EMP", i, j);
  					}else {
  						if( i % j == j-1) {
  							if( unsur2) {
  								board[i][j] = new Cell("EMP", i, j);
  							}
							else {
								board[i][j] = new Cell("LAD", i, j);
								if(j<mapy-1)
									board[i][j+1] = new Cell("LAD", i, j+1);
							}
  							unsur2 = !unsur2;
  						}
  						else {
  							board[i][j] = new Cell("PLT", i, j);
  						}
  					}
  				}
  			}
  		}
		/*int cpt = 0;
		int x,y;
		Random r = new Random();
		while(cpt < 3) {
			x = r.nextInt(mapx);
			y = r.nextInt(mapy);
			if(p.livable.contains(getContent(x,y))) {
				mobs.add(new Mob(x,y,p,this,mapx, mapy));
				cpt++;
			}	
		}*/
		
		
		nodes = new ArrayList<Cell>();
		Cell tmp;
		for(int j = 0; j<mapy; j++) {
			for(int i = 0; i<mapx; i++) {
				if(p.livable.contains(getContent(i,j))&&p.walkable.contains(getContent(i,(j+1)%mapy))) {
					nodes.add(board[i][j]);
				}
				else {
					if((getContent(i,j) == "EMP")&&(nodes.contains(board[i][(j-1+mapy)%mapy]))) {
						nodes.add(board[i][j]);
					}
				}
			}
			for(int i = 0; i<mapx; i++) {
				if(!nodes.contains(board[i][j])) {
					if((getContent(i,j) == "EMP")&&(nodes.contains(board[(i+1)%mapx][j]) || nodes.contains(board[(i-1+mapx)%mapx][j]))) {
						nodes.add(board[i][j]);
					}
				}
			}
		}// bug avec les echelles qui cachent
		int size = nodes.size();
		path = new int[size][size];
		pred = new Cell[size][size];
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				if(i==j) {
					path[i][j]=0;
					pred[i][j]=nodes.get(i);
				}
				else
					path[i][j]=INF;
			}
		}
		for(int i = 0; i < size; i++)
  		{
			tmp = nodes.get(i);
			if(tmp.x == 17) 
					System.out.println("TEST : " + tmp.y);
  			if(nodes.contains(board[(tmp.x+1)%mapx][tmp.y])) 
  			{
  				
  				path[i][nodes.indexOf(board[(tmp.x+1)%mapx][tmp.y])]=1;
  				pred[i][nodes.indexOf(board[(tmp.x+1)%mapx][tmp.y])]=tmp;
  			}
  			if(nodes.contains(board[(tmp.x-1+mapx)%mapx][tmp.y])) 
  			{
  				path[i][nodes.indexOf(board[(tmp.x-1+mapx)%mapx][tmp.y])]=1;
  				pred[i][nodes.indexOf(board[(tmp.x-1+mapx)%mapx][tmp.y])]=tmp;
  			}
  			if(nodes.contains(board[tmp.x][(tmp.y+1)%mapy])) 
  			{
  				path[i][nodes.indexOf(board[tmp.x][(tmp.y+1)%mapy])]=1;
  				pred[i][nodes.indexOf(board[tmp.x][(tmp.y+1)%mapy])]=tmp;
  			}
  			if(nodes.contains(board[tmp.x][(tmp.y-1+mapy)%mapy])&&tmp.getContent() == "LAD") 
  			{
  				path[i][nodes.indexOf(board[tmp.x][(tmp.y-1+mapy)%mapy])]=1;
  				pred[i][nodes.indexOf(board[tmp.x][(tmp.y-1+mapy)%mapy])]=tmp;
  			}
  		}
		for (int k = 0; k < size; k++) 
	    { 
	        for (int i = 0; i < size ; i++) 
	        { 
	            for (int j = 0; j < size; j++) 
	            {
	                if (path[i][k] + path[k][j] < path[i][j]) 
	                {
	                	path[i][j] = path[i][k] + path[k][j];
	                	pred[i][j] = nodes.get(k);
	                }
	            } 
	        } 
	    }
	}
	public void stepMobs() {
		for(int i = 0; i<mobs.size();i++) {//gerer le cas ou pas de chemin possible + le cas de defaite
			if(nodes.contains(board[mobs.get(i).x][mobs.get(i).y]) && nodes.contains(board[p.x][p.y])&&pred[nodes.indexOf(board[mobs.get(i).x][mobs.get(i).y])][nodes.indexOf(board[p.x][p.y])] != null) {
				
				if(mobs.get(i).x<pred[nodes.indexOf(board[mobs.get(i).x][mobs.get(i).y])][nodes.indexOf(board[p.x][p.y])].x) {
					mobs.get(i).goRight();

				}else {
					if(mobs.get(i).x>pred[nodes.indexOf(board[mobs.get(i).x][mobs.get(i).y])][nodes.indexOf(board[p.x][p.y])].x) 
						mobs.get(i).goLeft();
					else {
						if(mobs.get(i).y<pred[nodes.indexOf(board[mobs.get(i).x][mobs.get(i).y])][nodes.indexOf(board[p.x][p.y])].y) {
							mobs.get(i).goDown();
						}else {
							if(mobs.get(i).y>pred[nodes.indexOf(board[mobs.get(i).x][mobs.get(i).y])][nodes.indexOf(board[p.x][p.y])].y) 
								mobs.get(i).goUp();
						}
					}
				}
			}
			else {//todo, cas ou le joueur n'est pas atteignable
				mobs.get(i).goLeft();
				mobs.get(i).goDown();
			}
		}
	}
	public void fallMobs() {
		for(int i = 0; i<mobs.size();i++) {
			if(getContent(mobs.get(i).x, mobs.get(i).y +1) == "EMP" || getContent(mobs.get(i).x, mobs.get(i).y +1) == "HOL") {
				 mobs.get(i).y = mobs.get(i).y +1;
	    	}
		}
	}
	public void left() {
		p.goLeft();
	}
	public void right() {
		p.goRight();
	}
	public void goUp() {
		p.goUp();
	}
	public void goDown() {
		p.goDown();
	}
	public String getContent(int x, int y) {
		return board[x][y].getContent();
	}
	public Player getPlayer() {
		return p;
	}
	public Cell getCell(int x, int y) {
		return board[x][y];
	}
	public void paintComponent(Graphics g) {
		// On décide d'une couleur de fond pour notre rectangle
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
  		g.setColor(Color.red);
  		for(int i = 0; i < mapx; i++)
  		{
  			for(int j = 0; j < mapy; j++) {
  			switch(board[i][j].getContent()) {
	  			case "EMP":
	  				g.setColor(Color.black);
	  				break;
	  			case "HOL":
	  				g.setColor(Color.black);
	  				break;
	  			case "MTL":
	  				g.setColor(Color.gray);
	  				break;
	  			case "PLT":
	  				g.setColor(Color.blue);
	  				break;
	  			case "LAD":
	  				g.setColor(Color.pink);
	  				break;
  			}
  				/*if(nodes.contains(board[i][j]))//for testing
  					g.setColor(Color.green);*/
  				g.fillRect(20*i,20* j, 20, 20);
  			}
  		}
  		g.setColor(Color.white);
  		g.fillOval(20*p.x,20*p.y, 20, 20);
  		g.setColor(Color.red);
  		for(int i=0; i<mobs.size(); i++) {
  			g.fillOval(20*mobs.get(i).x,20*mobs.get(i).y, 20, 20);
  		}
	}
}
