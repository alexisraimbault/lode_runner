package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.EnvironmentLoader;
import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableEnvironment;
import model.services.EntityType;
import model.services.IContent;
import model.services.IEditableEnvironment;
import model.services.IEnvironmentLoader;
import model.services.Nature;

public class EnvironmentVisualizerPanel extends JPanel
{
	private IEditableEnvironment editable;
    private static final int block_size = 10;
    private SelectGamePanel selectPanel;
    private boolean isSelected = false;
    private EnvironmentVisualizerPanel thi;
    private Image ghost;
	private Image handrail;
	private Image ladder;
	private Image metal;
	private Image platform;
	private Image player;
	private Image treasure;
	private Image bg;
	public EnvironmentVisualizerPanel(IEditableEnvironment env, SelectGamePanel selectPanel)
	{
		//System.out.println("SIZE : " + env.getHeight() + " / " + env.getWidth());
		//this.setSize(new Dimension((env.getWidth() + 2)* block_size, (env.getHeight() + 2) * block_size));
		editable = env;
		this.selectPanel = selectPanel;
		thi = this;
		this.addMouseListener(new MouseAdapter() {
            
            
            @Override
            public void mousePressed(MouseEvent e) {
            	selectPanel.unSelectAll();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            	isSelected = true;
            	selectPanel.setSelected(thi);
            	selectPanel.repaint();
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
			bg = ImageIO.read(new File("tiles/bg.png")).getScaledInstance(block_size*env.getWidth(), block_size * env.getHeight(), Image.SCALE_SMOOTH );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IEditableEnvironment getEditable(){
		return editable;
	}
	
	public void setSelected(boolean b){
		this.isSelected = b;
		
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bg,0 , 0 , null);
		if(isSelected){
			g.setColor(Color.blue);
			g.fillRect(0,0, (editable.getWidth() + 2)*block_size, (editable.getHeight() + 2)*block_size);
		}
		
		for(int x = 0; x < editable.getWidth() ; ++x)
  		{
  			for(int y = 0; y < editable.getHeight() ; ++y)
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
  				//g.fillRect(block_size*(x + 1), block_size*( ry + 1), block_size, block_size);
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
					//g.fillOval(block_size*(x + 1), block_size*(ry + 1), block_size, block_size);
				}
  			}
  		}
	}
}