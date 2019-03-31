package services;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Test extends JFrame {
	
	public static void main(String[] args) {
		new Test(30,20);
	}

  private Board board;

  public Test(int x, int y) {
    this.setTitle("Animation");
    this.setSize(x*20, y*20);
    this.board = new Board(x,y);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setContentPane(board);
    this.setVisible(true);
    this.addKeyListener(new KeyListener(){
    	 
        @Override
        public void keyTyped(KeyEvent ke) {
            System.out.println("typed"+ke.getKeyCode());
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            System.out.println("pressed"+ke.getKeyCode());
            if(ke.getKeyCode() == 32) {
            	
            }
            if(ke.getKeyCode() == 37) {
            	board.left();
            }
            if(ke.getKeyCode() == 38) {
            	board.goUp();
            }
            if(ke.getKeyCode() == 39) {
            	board.right();
            }
            if(ke.getKeyCode() == 40) {
            	board.goDown();
            }
            if(ke.getKeyCode() == 68) {
            	board.getPlayer().digLeft();
            }
            if(ke.getKeyCode() == 70) {
            	board.getPlayer().digRight();
            }
            board.stepMobs();
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            System.out.println("released"+ke.getKeyCode());
        }
    });
    go();
  }
  

  private void go() {
    while (true) {
    	if(board.getContent(board.getPlayer().getX(), board.getPlayer().getY() +1) == "EMP") {
    		board.getPlayer().setY(board.getPlayer().getY()+1);
    	}
    	board.fallMobs();
    	
    	board.repaint();
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}