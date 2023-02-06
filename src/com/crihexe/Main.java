package com.crihexe;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.crihexe.panels.GUIPanel;
import com.crihexe.utils.Graphic;

public class Main {

	private JFrame frame;
	private GUIPanel panel;
	
	public Main() {
		frame = new JFrame("Nand2Tetris - Chip Maker");
		panel = new GUIPanel();
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// fare roba di essere sicuro di salvare qui
				System.exit(0);
			}
		});
		
		frame.setSize(1280, 720);
		
		frame.add(panel);
		
		frame.setVisible(true);
		
		panel.init();
	}
	
	public static void main(String[] args) {
		Graphic.loadFonts();
		new Main();
	}

}
