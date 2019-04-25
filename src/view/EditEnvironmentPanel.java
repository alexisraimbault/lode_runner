package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableEnvironment;
import model.services.IEditableEnvironment;
import model.services.IGuard;
import model.services.ITreasure;
import model.services.Nature;

public class EditEnvironmentPanel extends JPanel
{
	private IEditableEnvironment editable;
	private boolean selecting = false;
	private boolean isActiveSelection = false;
    private int startX, startY, endX, endY;
    private static final int block_size = 50;
	public EditEnvironmentPanel(int width, int height)
	{
		editable = new EditableEnvironment(new DynamicScreen());
		editable.resize(width, height);
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
	
	public void edit(Nature mat){
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
