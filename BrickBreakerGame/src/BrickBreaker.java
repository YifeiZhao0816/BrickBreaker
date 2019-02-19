

import javax.swing.JFrame;
import java.awt.Canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
public class BrickBreaker{


	public static void main(String[] args) throws InterruptedException {
		JFrame myFrame = new JFrame("Brick Breaker");
		//myFrame.getContentPane().add(new Game());
		myFrame.setSize(1280, 720);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		JButton startGame = new JButton("Start Game");
		myFrame.add(startGame);
		myFrame.setVisible(true);
		

		
	}
	

}
