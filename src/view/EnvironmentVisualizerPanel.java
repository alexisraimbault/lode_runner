package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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
	}
	
	public IEditableEnvironment getEditable(){
		return editable;
	}
	
	public void setSelected(boolean b){
		this.isSelected = b;
		
	}
	
	public void paintComponent(Graphics g) {
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
  				g.fillRect(block_size*(x + 1), block_size*( ry + 1), block_size, block_size);
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
					g.fillOval(block_size*(x + 1), block_size*(ry + 1), block_size, block_size);
				}
  			}
  		}
	}
}