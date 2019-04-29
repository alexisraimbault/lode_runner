package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableEnvironment;
import model.services.EntityType;
import model.services.IContent;
import model.services.IEditableEnvironment;

public class NewMapSelectVisualizer extends JPanel
{
	private IEditableEnvironment editable;
    private static final int block_size = 10;
    private SelectGamePanel selectPanel;
    private boolean isSelected = false;
    
	public NewMapSelectVisualizer(SelectGamePanel selectPanel)
	{
		//System.out.println("SIZE : " + env.getHeight() + " / " + env.getWidth());
		//this.setSize(new Dimension((env.getWidth() + 2)* block_size, (env.getHeight() + 2) * block_size));
		this.selectPanel = selectPanel;
		editable = new EditableEnvironment(new DynamicScreen());
		editable.resize(27, 11);
		this.addMouseListener(new MouseAdapter() {
            
            
            @Override
            public void mousePressed(MouseEvent e) {
            	selectPanel.unSelectAll();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            	isSelected = true;
            	selectPanel.setSelectedNew();
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
			g.fillRect(0,0, (20)*block_size, (20)*block_size);
		}
		g.setColor(Color.white);
		g.drawString("NEW MAP", 10*block_size, 10*block_size);

	}
}
