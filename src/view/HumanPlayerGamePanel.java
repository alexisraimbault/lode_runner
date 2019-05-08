package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.gamestate.operations.ExecutedOperation;
import model.services.GuardCommandType;
import model.services.ICharacter;
import model.services.IGuardSummoner;
import model.services.IPlayerSummoner;
import model.services.ISummonerPool;
import model.services.ICharacter;
import model.services.IEnvironment;
import model.services.IExecutedCharacterOperation;
import model.services.IExecutedOperation;
import model.services.IGuard;
import model.services.IHumanPlayerEngine;
import model.services.IPlayer;
import model.services.ITreasure;
import model.services.Nature;
import model.services.PlayerCommandType;

public class HumanPlayerGamePanel extends JPanel
{
	private IHumanPlayerEngine engine;
	private IEnvironment environment;
	private ISummonerPool pool;
	private Image ghost;
	private Image handrail;
	private Image ladder;
	private Image metal;
	private Image platform;
	private Image player;
	private Image treasure;
	private Image bg;
	private static final int block_size = 50;
	
	public HumanPlayerGamePanel(IHumanPlayerEngine engine)
	{
		this.engine = engine;
		this.environment = engine.getState().getEnvironment();
		this.pool = engine.getState().getPool();
		try {
			ghost = ImageIO.read(new File("tiles/ghost.png")).getScaledInstance(block_size, block_size, Image.SCALE_SMOOTH );
			handrail = ImageIO.read(new File("tiles/handrail.png")).getScaledInstance(block_size, block_size, Image.SCALE_SMOOTH );
			ladder = ImageIO.read(new File("tiles/ladder.png")).getScaledInstance(block_size, block_size, Image.SCALE_SMOOTH );
			treasure = ImageIO.read(new File("tiles/treasure.png")).getScaledInstance(block_size, block_size, Image.SCALE_SMOOTH );
			metal = ImageIO.read(new File("tiles/metal.png")).getScaledInstance(block_size, block_size, Image.SCALE_SMOOTH );
			platform = ImageIO.read(new File("tiles/platform.png")).getScaledInstance(block_size, block_size, Image.SCALE_SMOOTH );
			player = ImageIO.read(new File("tiles/player.png")).getScaledInstance(block_size, block_size, Image.SCALE_SMOOTH );
			bg = ImageIO.read(new File("tiles/bg.png")).getScaledInstance(block_size*environment.getWidth(), block_size * environment.getHeight(), Image.SCALE_SMOOTH );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bg,0 , 0 , null);
		for(int x = 0; x < environment.getWidth(); ++x)
  		{
  			for(int y = 0; y < environment.getHeight(); ++y)
  			{
  				int ry = environment.getHeight() - 1 -  y;
  				switch(environment.getCellNature(x, y))
	  			{
		  			case EMPTY:
		  				g.setColor(Color.black);
		  				break;
		  			case HOLE:
		  				g.setColor(Color.black);
		  				break;
		  			case METAL:
		  				g.drawImage(metal, block_size*x , block_size*ry , null);
		  				g.setColor(Color.gray);
		  				break;
		  			case PLATFORM:
		  				g.drawImage(platform, block_size*x , block_size*ry , null);
		  				g.setColor(Color.blue);
		  				break;
		  			case LADDER:
		  				g.drawImage(ladder, block_size*x , block_size*ry , null);
		  				g.setColor(Color.pink);
		  				break;
		  			case HANDRAIL:
		  				g.drawImage(handrail, block_size*x , block_size*ry , null);
		  				g.setColor(Color.orange);
		  				break;
	  			}
  				//g.fillRect(block_size*x, block_size*ry, block_size, block_size);
  			}
  		}
		g.setColor(Color.yellow);
		for(ITreasure treasure : pool.getTreasures())
		{
			int px = block_size * treasure.getX();
			int py = block_size * (environment.getHeight() - 1 - treasure.getY());
			g.drawImage(this.treasure, px, py , null);
		}
		
		for(IGuard guard : pool.getGuards())
			drawGuard(g, guard, Color.red);
		
		IPlayerSummoner splayer = pool.getPlayerSummoner();
		if(splayer.hasInstance())
			drawPlayer(g, splayer.getInstance(), Color.white);
		
		
	}
	
	
	private void drawGuard(Graphics g, IGuard guard, Color color)
	{
		int px = block_size * guard.getX();
		int py = block_size * (environment.getHeight() - 1 - guard.getY());
		if(guard.hasOperation())
		{
			IExecutedCharacterOperation<GuardCommandType> operation = guard.getExecutedOperation();
			double progress = operation.getProgress();
			switch(operation.getOperationType())
			{
			case LEFT:
				px -= progress * block_size - block_size;
				break;
			case RIGHT:
				px += progress * block_size - block_size;
				break;
			case DOWN:
				py += progress * block_size - block_size;
				break;
			case UP:
				py -= progress * block_size - block_size;
				break;
			case CLIMBLEFT:
				if(progress > 0.5)
				{
					py += block_size - block_size;
					px -= (progress - 0.5) * block_size - block_size;
				}
				else
				{
					py -= progress * block_size - block_size;
				}
				break;
			case CLIMBRIGHT:
				if(progress > 0.5)
				{
					py += block_size - block_size;
					px += (progress - 0.5) * block_size - block_size;
				}
				else
				{
					py -= progress * block_size - block_size;
				}
				break;
			default:
				break;
			
			}
		}
		g.setColor(color);
		g.drawImage(this.ghost, px, py , null);
	}
	
	private void drawPlayer(Graphics g, IPlayer player, Color color)
	{
		int px = block_size * player.getX();
		int py = block_size * (environment.getHeight() - 1 - player.getY());
		if(player.hasOperation())
		{
			IExecutedCharacterOperation<PlayerCommandType> operation = player.getExecutedOperation();
			double progress = operation.getProgress();
			switch(operation.getOperationType())
			{
			case LEFT:
				px -= progress * block_size - block_size;
				break;
			case RIGHT:
				px += progress * block_size - block_size;
				break;
			case DOWN:
				py += progress * block_size - block_size;
				break;
			case UP:
				py -= progress * block_size - block_size;
				break;
			case DIGLEFT:
				break;
			case DIGRIGHT:
				break;
			default:
				break;
			
			}
		}
		g.setColor(color);
		g.drawImage(this.player, px, py , null);
	}
	
	/*
	public void init() {
		boolean unsur2 = true;
		for(int x = 0; x < mapx; x++)
  		{
  			for(int y = 0; y < mapy; y++)
  			{
  				if(y == mapy - 3){
  					screen[x][y] = Nature.MTL;
  				}else {
  					if(y%2 == 0) {
  						if(screen[x][y] == null)
  							screen[x][y] = Nature.EMP;
  					}else {
  						if( x % y == y-1) {
  							if( unsur2) {
  								screen[x][y] = Nature.EMP;
  							}
							else {
								screen[x][y] = Nature.LAD;
								if(y<mapy-1)
									screen[x][y+1] = Nature.LAD;
							}
  							unsur2 = !unsur2;
  						}
  						else {
  							screen[x][y] = new Nature("PLT", x, y);
  						}
  					}
  				}
  			}
  		}
		
		
		nodes = new ArrayList<Nature>();
		Nature tmp;
		for(int y = 0; y<mapy; y++) {
			for(int x = 0; x<mapx; x++) {
				if(p.livable.contains(getContent(x,y))&&p.walkable.contains(getContent(x,(y+1)%mapy))) {
					nodes.add(screen[x][y]);
				}
				else {
					if((getContent(x,y) == "EMP")&&(nodes.contains(screen[x][(y-1+mapy)%mapy]))) {
						nodes.add(screen[x][y]);
					}
				}
			}
			for(int x = 0; x<mapx; x++) {
				if(!nodes.contains(screen[x][y])) {
					if((getContent(x,y) == "EMP")&&(nodes.contains(screen[(x+1)%mapx][y]) || nodes.contains(screen[(x-1+mapx)%mapx][y]))) {
						nodes.add(screen[x][y]);
					}
				}
			}
		}// bug avec les echelles qui cachent
		int size = nodes.size();
		path = new int[size][size];
		pred = new Nature[size][size];
		for(int x = 0; x<size; x++) {
			for(int y = 0; y<size; y++) {
				if(x==y) {
					path[x][y]=0;
					pred[x][y]=nodes.get(x);
				}
				else
					path[x][y]=INF;
			}
		}
		for(int x = 0; x < size; x++)
  		{
			tmp = nodes.get(x);
			if(tmp.x == 17) 
					System.out.println("TEST : " + tmp.y);
  			if(nodes.contains(screen[(tmp.x+1)%mapx][tmp.y])) 
  			{
  				
  				path[x][nodes.indexOf(screen[(tmp.x+1)%mapx][tmp.y])]=1;
  				pred[x][nodes.indexOf(screen[(tmp.x+1)%mapx][tmp.y])]=tmp;
  			}
  			if(nodes.contains(screen[(tmp.x-1+mapx)%mapx][tmp.y])) 
  			{
  				path[x][nodes.indexOf(screen[(tmp.x-1+mapx)%mapx][tmp.y])]=1;
  				pred[x][nodes.indexOf(screen[(tmp.x-1+mapx)%mapx][tmp.y])]=tmp;
  			}
  			if(nodes.contains(screen[tmp.x][(tmp.y+1)%mapy])) 
  			{
  				path[x][nodes.indexOf(screen[tmp.x][(tmp.y+1)%mapy])]=1;
  				pred[x][nodes.indexOf(screen[tmp.x][(tmp.y+1)%mapy])]=tmp;
  			}
  			if(nodes.contains(screen[tmp.x][(tmp.y-1+mapy)%mapy]) && tmp == Nature.LAD) 
  			{
  				path[x][nodes.indexOf(screen[tmp.x][(tmp.y-1+mapy)%mapy])]=1;
  				pred[x][nodes.indexOf(screen[tmp.x][(tmp.y-1+mapy)%mapy])]=tmp;
  			}
  		}
		for (int k = 0; k < size; k++) 
	    { 
	        for (int x = 0; x < size ; x++) 
	        { 
	            for (int y = 0; y < size; y++) 
	            {
	                if (path[x][k] + path[k][y] < path[x][y]) 
	                {
	                	path[x][y] = path[x][k] + path[k][y];
	                	pred[x][y] = nodes.get(k);
	                }
	            } 
	        } 
	    }
	}
	public void stepMobs() {
		for(int x = 0; x<guards.size();x++) {//gerer le cas ou pas de chemin possible + le cas de defaite
			if(nodes.contains(screen[guards.get(x).x][guards.get(x).y]) && nodes.contains(screen[p.x][p.y])&&pred[nodes.indexOf(screen[guards.get(x).x][guards.get(x).y])][nodes.indexOf(screen[p.x][p.y])] != null) {
				
				if(guards.get(x).x<pred[nodes.indexOf(screen[guards.get(x).x][guards.get(x).y])][nodes.indexOf(screen[p.x][p.y])].x) {
					guards.get(x).goRight();

				}else {
					if(guards.get(x).x>pred[nodes.indexOf(screen[guards.get(x).x][guards.get(x).y])][nodes.indexOf(screen[p.x][p.y])].x) 
						guards.get(x).goLeft();
					else {
						if(guards.get(x).y<pred[nodes.indexOf(screen[guards.get(x).x][guards.get(x).y])][nodes.indexOf(screen[p.x][p.y])].y) {
							guards.get(x).goDown();
						}else {
							if(guards.get(x).y>pred[nodes.indexOf(screen[guards.get(x).x][guards.get(x).y])][nodes.indexOf(screen[p.x][p.y])].y) 
								guards.get(x).goUp();
						}
					}
				}
			}
			else {//todo, cas ou le joueur n'est pas atteignable
				guards.get(x).goLeft();
				guards.get(x).goDown();
			}
		}
	}
	public void fallMobs() {
		for(int x = 0; x<guards.size();x++) {
			if(getContent(guards.get(x).x, guards.get(x).y +1) == "EMP" || getContent(guards.get(x).x, guards.get(x).y +1) == "HOL") {
				 guards.get(x).y = guards.get(x).y +1;
	    	}
		}
	}

	public void paintComponent(Graphics g) {
		// On décide d'une couleur de fond pour notre rectangle
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
  		g.setColor(Color.red);
  		for(int x = 0; x < environment.getWidth(); ++x)
  		{
  			for(int y = 0; y < environment.getHeight(); ++y)
  			{
	  			switch(screen.getCellNature(x, y))
	  			{
		  			case Nature.EMPTY:
		  				g.setColor(Color.black);
		  				break;
		  			case Nature.HOLE:
		  				g.setColor(Color.black);
		  				break;
		  			case Nature.METAL:
		  				g.setColor(Color.gray);
		  				break;
		  			case Nature.PLATFORM:
		  				g.setColor(Color.blue);
		  				break;
		  			case Nature.LADDER:
		  				g.setColor(Color.pink);
		  				break;
	  			}
  				g.fillRect(20*x, 20* y, 20, 20);
  			}
  		}
  		g.setColor(Color.white);
  		g.fillOval(20*p.x,20*p.y, 20, 20);
  		g.setColor(Color.red);
  		for(int x=0; x<guards.size(); x++) {
  			g.fillOval(20*guards.get(x).x,20*guards.get(x).y, 20, 20);
  		}
	}
	*/
}
