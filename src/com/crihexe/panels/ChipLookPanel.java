package com.crihexe.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.crihexe.Chip;
import com.crihexe.graphics.Button;
import com.crihexe.graphics.MouseInteractive;
import com.crihexe.utils.Graphic;
import com.crihexe.utils.PinList;

public class ChipLookPanel extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = -1127931269373419717L;
	
	private final Chip chip;
	
	private ArrayList<Button> buttons = new ArrayList<Button>();
	
	private Button addInputButton;
	private Button addOutputButton;
	
	private GUIPanel panel;
	
	public ChipLookPanel(Chip chip, GUIPanel panel) {
		super(true);
		
		this.chip = chip;
		this.panel = panel;
		
		setFocusable(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		
	}
	
	public void init() {
		addInputButton = addNewButton(new Button(getWidth()/2 - 50/2, 20, 50, 20, new Font("Calibri", Font.PLAIN, 16)), new MouseInteractive() {
			@Override
			public void onRelease() {
				addInput("");
			}
			
			@Override public void onPress() {}
			@Override public void mouseEntered() {}
			@Override public void mouseExited() {}
		});
		addInputButton.setText("input");
		
		
		addOutputButton = addNewButton(new Button(getWidth()/2 - 60/2, getHeight() - 40, 60, 20, new Font("Calibri", Font.PLAIN, 16)), new MouseInteractive() {
			@Override
			public void onRelease() {
				addOutput("");
			}
			
			@Override public void onPress() {}
			@Override public void mouseEntered() {}
			@Override public void mouseExited() {}
		});
		addOutputButton.setText("output");
	}
	
	private Button addNewButton(Button button, MouseInteractive callback) {
		addMouseListener(button);
		addMouseMotionListener(button);
		
		button.addCallback(callback);
		
		buttons.add(button);
		
		return button;
	}
	
	public void addInput(String s) {
		chip.addInput(s);
		refresh();
	}
	
	public void addOutput(String s) {
		chip.addOutput(s);
		refresh();
	}
	
	public void refresh() {
		panel.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		
		Graphics2D g = (Graphics2D) g2;
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
	    Graphic.drawCenteredImage(g, chip.paint(), new Rectangle(0, 0, getWidth(), getHeight()));
		
		//buttons
		for(Button button : buttons) button.paintComponent(g);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
