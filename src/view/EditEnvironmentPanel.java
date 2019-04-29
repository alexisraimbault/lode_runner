package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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
		  				g.setColor(Color.gray);
		  				break;
		  			case PLATFORM:
		  				g.setColor(Color.blue);
		  				break;
		  			case LADDER:
		  				g.setColor(Color.pink);
		  				break;
		  			case HANDRAIL:
		  				g.setColor(Color.orange);
		  				break;
	  			}
  				g.fillRect(block_size*x, block_size*ry, block_size, block_size);
  				IContent contentCell = editable.getCellContent(x, y);
				for(EntityType type : EntityType.values())
				{
					if(contentCell.contains(type)){
						switch(type)
						{
						case PLAYER:
							g.setColor(Color.white);
							break;
						case GUARD:
							g.setColor(Color.red);
							break;
						case TREASURE:
							g.setColor(Color.yellow);
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
					g.fillOval(block_size*x, block_size*ry, block_size, block_size);
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
