package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.EnvironmentLoader;
import controller.GameRunner;
import controller.TimeConverter;
import model.HumanPlayerEngine;
import model.gamestate.GameState;
import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableEnvironment;
import model.gamestate.environment.Environment;
import model.gamestate.operations.OperationsSpeeds;
import model.services.EntityType;
import model.services.IEditableEnvironment;
import model.services.IEnvironment;
import model.services.IEnvironmentLoader;
import model.services.IGameState;
import model.services.IGuard;
import model.services.IHumanPlayerEngine;
import model.services.IContent;
import model.services.ITreasure;
import model.services.Nature;
import model.services.PlayerCommandType;

public class EditEnvironmentPanel extends JPanel
{
	private IEditableEnvironment editable;
	private boolean selecting = false;
	private boolean isActiveSelection = false;
    private int startX, startY, endX, endY;
    private static final int block_size = 50;
    private Image ghost;
	private Image handrail;
	private Image ladder;
	private Image metal;
	private Image platform;
	private Image player;
	private Image treasure;
	private Image bg;
    GameFrame gf;
	public EditEnvironmentPanel(IEditableEnvironment editable, GameFrame gf)
	{
		this.gf = gf;
		this.editable = editable;
		editable.resize(editable.getWidth(), editable.getHeight());
		this.addMouseListener(new MouseAdapter() {
            
            
            @Override
            public void mousePressed(MouseEvent e) {
            	isActiveSelection = false;
            	repaint();
                if(!selecting){
                	selecting = true;
                	startX = e.getX()/block_size;
                	startY = e.getY()/block_size;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            	
            	if(selecting){
                	selecting = false;
                	endX = e.getX()/block_size;
                	endY = e.getY()/block_size;
                	isActiveSelection = true;
                	repaint();
                	System.out.println("new selection from : "+startX+" / "+startY+" to : "+endX+" / "+endY);
                }
            }
        });
		try {
			ghost = ImageIO.read(new File("tiles/ghost.png")).getScaledInstance(block_size, block_size, Image.SCALE_SMOOTH );
			handrail = ImageIO.read(new File("tiles/handrail.png")).getScaledInstance(block_size, block_size, Image.SCALE_SMOOTH );
			ladder = ImageIO.read(new File("tiles/ladder.png")).getScaledInstance(block_size, block_size, Image.SCALE_SMOOTH );
			treasure = ImageIO.read(new File("tiles/treasure.png")).getScaledInstance(block_size, block_size, Image.SCALE_SMOOTH );
			metal = ImageIO.read(new File("tiles/metal.png")).getScaledInstance(block_size, block_size, Image.SCALE_SMOOTH );
			platform = ImageIO.read(new File("tiles/platform.png")).getScaledInstance(block_size, block_size, Image.SCALE_SMOOTH );
			player = ImageIO.read(new File("tiles/player.png")).getScaledInstance(block_size, block_size, Image.SCALE_SMOOTH );
			bg = ImageIO.read(new File("tiles/bg.png")).getScaledInstance(block_size*editable.getWidth(), block_size * editable.getHeight(), Image.SCALE_SMOOTH );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IEditableEnvironment getEditable(){
		return editable;
	}
	
	public boolean isActiveSelection(){
		return isActiveSelection;
	}
	
	public int getStartX(){
		return startX;
	}
	public int getStartY(){
		return startY;
	}
	public int getEndX(){
		return endX;
	}
	public int getEndY(){
		return endY;
	}
	
	public void startGame() throws Exception{
		gf.launchGame(editable);
		
		/*long player_move_speed = state.getSpeeds().get(PlayerCommandType.LEFT);
		// means player move speed last 500 ms
		TimeConverter converter = new TimeConverter(player_move_speed, 200000000);
		
		Thread tick_thread = new Thread(new GameRunner(engine, panel, converter));
		
		tick_thread.start();
		
		tick_thread.join();*/
	}
	
	public void editEnv(Nature mat){
		System.out.println("editing ! ");
		isActiveSelection = false;
		int minX = Math.min(startX,  endX);
		int minY = Math.min(startY,  endY);
		int dx = Math.abs(startX - endX) + 1;
		int dy = Math.abs(startY - endY) + 1;
		for(int x = minX; x < minX + dx; x++){
			for(int y = minY; y < minY + dy; y++){
				editable.setCellNature(x, editable.getHeight() - 1 - y, mat);
			}
		}
		repaint();
	}
	
	public void editEntity(EntityType entity){
		if(startX == endX && startY == endY && !editable.getCellNature(startX, editable.getHeight() - 1 - startY).isPlenty()){
			editable.getCellContent(startX, editable.getHeight() - 1 - startY).add(entity);
			repaint();
		}
	}
	
	public void saveMap(String path) throws IOException{
		IEnvironmentLoader loader = new EnvironmentLoader(); 
		loader.uploadToFile(path, this.editable);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bg,0 , 0 , null);
		for(int x = 0; x < editable.getWidth(); ++x)
  		{
  			for(int y = 0; y < editable.getHeight(); ++y)
  			{
  				int ry = editable.getHeight() - 1 -  y;
  				switch(editable.getCellNature(x, y))
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
  				IContent contentCell = editable.getCellContent(x, y);
				for(EntityType type : EntityType.values())
				{
					if(contentCell.contains(type)){
						switch(type)
						{
						case PLAYER:
							g.drawImage(this.player, block_size*x, block_size*ry , null);
							break;
						case GUARD:
							g.drawImage(this.ghost, block_size*x, block_size*ry , null);
							break;
						case TREASURE:
							g.drawImage(this.treasure, block_size*x, block_size*ry , null);
							break;
						case TELEPORTER:
							g.setColor(Color.cyan);
							break;
						case FANTOM:
							g.setColor(Color.darkGray);
							break;
						default:
							break;
						
						}
					}
					//g.fillOval(block_size*x, block_size*ry, block_size, block_size);
				}
  			}
  		}

		if(isActiveSelection){
			g.setColor(Color.white);
			int minX = Math.min(startX,  endX);
			int minY = Math.min(startY,  endY);
			int dx = Math.abs(startX - endX) + 1;
			int dy = Math.abs(startY - endY) + 1;
			g.drawRect(block_size*minX, block_size*minY, dx*block_size, dy*block_size);
		}
		
	}
}
