package com.crihexe.panels;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.crihexe.Chip2;

public class ChipLookPanel2 extends JPanel implements KeyListener {
	
	private final Chip2 chip;
	
	/**
	 * Create the panel.
	 */
	public ChipLookPanel2() {
		setLayout(null);
		
		setFocusable(true);
		addKeyListener(this);
		
		chip = new Chip2()/* {
			float a = (float) (Math.PI/2);
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.rotate(a, getX() + getWidth()/2, getY() + getHeight()/2);
				//a += 0.1;
				
				super.paintComponent(g2);
				setBounds(getX(), getY(), getHeight(), getWidth());
				g2.fillRect(0, 0, 100000, 100000);
				
			}
		}*/;
		//chip.setRotated(true);
		chip.setBounds(100, 10, 100, 100);
		add(chip);
		
	}
	
	public void addInput(String s) {
		chip.addInput(s);
	}
	
	public void addOutput(String s) {
		chip.addOutput(s);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void rotateChip() {
		chip.setRotated(!chip.isRotated());
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
