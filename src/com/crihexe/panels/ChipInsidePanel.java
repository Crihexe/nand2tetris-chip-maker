package com.crihexe.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.crihexe.Chip;
import com.crihexe.ChipComponent;
import com.crihexe.utils.Pin;

public class ChipInsidePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 6906509401668718727L;

	private Chip chip;
	private GUIPanel panel;
	
	private ArrayList<ChipComponent> chips = new ArrayList<ChipComponent>();
	
	private boolean showNodes = false;
	
	public ChipInsidePanel(Chip chip, GUIPanel panel) {
		super(true);
		
		this.chip = chip;
		this.panel = panel;
		
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
	}
	
	public void init() {
		
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
	    
	    for(Pin pin : chip.getInputs()) {
	    	
	    }
	    for(ChipComponent c : chips) {
	    	for(Pin pin : c.getInputs()) {
		    	
		    }
	    }
	    
	    // inputs a sinistra
	    if(chip.inputSize() > 0) {
	    	int vPadding = getHeight() / chip.inputSize();
		    for(int i = 0; i < chip.inputSize(); i++) {
		    	g.setColor(new Color(200, 200, 200));
		    	g.fillRect(0, vPadding*i + vPadding/2 - 4, 32, 8);
		    	g.drawString(chip.getInputName(i), 0, vPadding*i + vPadding/2 - 4 - 4);
		    	if(showNodes) {
		    		g.setColor(new Color(31, 31, 31));
		    		g.fillOval(32 - 8, vPadding*i + vPadding/2 - 8, 16, 16);
		    	}
		    }
	    }
	    if(chip.outputSize() > 0) {
	    	int vPadding = getHeight() / chip.outputSize();
		    for(int i = 0; i < chip.outputSize(); i++) {
		    	g.setColor(new Color(200, 200, 200));
		    	g.fillRect(getWidth()-32, vPadding*i + vPadding/2 - 4, 32, 8);
		    	g.drawString(chip.getOutputName(i), getWidth()-32, vPadding*i + vPadding/2 - 4 - 4);
		    	if(showNodes) {
		    		g.setColor(new Color(31, 31, 31));
		    		g.fillOval(getWidth()-32 - 8, vPadding*i + vPadding/2 - 8, 16, 16);
		    	}
		    }
	    }
	    
	    for(ChipComponent c : chips) {
	    	BufferedImage img = c.paint();
	    	g.drawImage(new AffineTransformOp(
	    			AffineTransform.getRotateInstance(-Math.PI/2, img.getWidth()/2, img.getHeight()/2), 
	    			AffineTransformOp.TYPE_BILINEAR).filter(img, null),
	    			c.getX() - img.getWidth()/2, c.getY() - img.getHeight()/2, null);
	    }
	    
	    
	    
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ChipComponent c = new ChipComponent(e.getX(), e.getY());
		c.addInput("a");
		c.addInput("a");
		c.addOutput("a");
		chips.add(c);
		
		refresh();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		showNodes = true;
		refresh();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		showNodes = false;
		refresh();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}

}
