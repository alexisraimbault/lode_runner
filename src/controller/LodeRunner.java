package controller;

import javax.swing.JFrame;

import view.GameFrame;

public class LodeRunner extends JFrame
{
	
	public static void main(String[] args)
	{
		try
		{
			GameFrame frame = new GameFrame();
			frame.startSelection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}