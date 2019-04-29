package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import controller.EnvironmentLoader;
import model.services.IEditableEnvironment;
import model.services.IEnvironmentLoader;

public class SelectGamePanel extends JPanel
{
	private List<EnvironmentVisualizerPanel> maps;
	private EnvironmentVisualizerPanel selected;
	private boolean selectedNew = false;
	private NewMapSelectVisualizer newMapPanel;
	private GameFrame gf;
	
	public SelectGamePanel(GameFrame gf){
		this.maps = new ArrayList<EnvironmentVisualizerPanel>();
		this.gf = gf;
		IEnvironmentLoader loader = new EnvironmentLoader();
		File repertoire = new File("maps");
	    String liste[] = repertoire.list();      
	    this.setLayout(new GridLayout(0, 3));
	    if (liste != null) {         
	        for (int i = 0; i < liste.length; i++) {
	        	try {
	        		EnvironmentVisualizerPanel tmp = new EnvironmentVisualizerPanel(loader.loadFromFile(liste[i]), this);
	        		maps.add(tmp);
	        		this.add(tmp);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	        newMapPanel = new NewMapSelectVisualizer(this);
    		this.add(newMapPanel);
	    } else {
	        System.err.println("Nom de repertoire invalide");
	    }
	    
	}
	
	public void unSelectAll(){
		for(EnvironmentVisualizerPanel viz : maps)
			viz.setSelected(false);
		newMapPanel.setSelected(false);
		this.selectedNew = false;
	}
	
	public void setSelected( EnvironmentVisualizerPanel s){
		this.selected = s;
	}
	public void setSelectedNew(){
		this.selectedNew = true;
	}
	public boolean isActiveSelection(){
		return (this.selectedNew || this.selected != null);
	}
	
	public void validateSelection(){
		try {
			this.gf.startEdit(this.getLaunchMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public IEditableEnvironment getLaunchMap(){
		if(selectedNew)
			return newMapPanel.getEditable();
		if(selected != null){
			return selected.getEditable();
		}
		return null;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
	}
}
	
