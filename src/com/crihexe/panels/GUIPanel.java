package com.crihexe.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.crihexe.Chip;

public class GUIPanel extends JPanel implements KeyListener {
	private static final long serialVersionUID = -8233090345447455418L;
	
	private ChipLookPanel chipLookPanel;
	private ChipInsidePanel chipInsidePanel;
	
	private Chip chip;

	/**
	 * Create the panel.
	 */
	public GUIPanel() {
		setFocusable(true);
		addKeyListener(this);
		
		chip = new Chip();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		chipLookPanel = new ChipLookPanel(chip, this);
		chipLookPanel.setBackground(new Color(92, 92, 92));
		
		JPanel chipCodePanel = new JPanel();
		chipCodePanel.setBackground(new Color(0, 128, 255));
		
		JSplitPane chipPane = new JSplitPane();
		chipPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		chipPane.setBackground(new Color(255, 128, 0));
		chipPane.setBorder(null);
		chipPane.setDividerSize(0);
		chipPane.setTopComponent(chipLookPanel);
		chipPane.setBottomComponent(chipCodePanel);
		chipPane.setResizeWeight(0.4);
		
		chipInsidePanel = new ChipInsidePanel(chip, this);
		chipInsidePanel.setBackground(new Color(53, 53, 53));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBorder(null);
		splitPane.setDividerSize(0);
		splitPane.setLeftComponent(chipPane);
		splitPane.setRightComponent(chipInsidePanel);
		splitPane.setResizeWeight(0.3);
		add(splitPane);

	}
	
	public void init() {
		chipLookPanel.init();
		chipInsidePanel.init();
	}

	public JPanel getChipLookPanel() {
		return chipLookPanel;
	}
	public JPanel getChipInsidePanel() {
		return chipInsidePanel;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		chipInsidePanel.repaint();
		chipLookPanel.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_I)
			chipLookPanel.addInput("a");
		if(e.getKeyCode() == KeyEvent.VK_O)
			chipLookPanel.addOutput("a");
		
		repaint();
	}

	@Override public void keyReleased(KeyEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}
}
