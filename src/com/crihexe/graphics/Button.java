package com.crihexe.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import com.crihexe.utils.Graphic;

public class Button implements MouseListener, MouseMotionListener {
	
	private Rectangle rect;
	
	private Color color;
	private String text;
	private Font font;
	
	private ArrayList<MouseInteractive> callbacks = new ArrayList<MouseInteractive>();
	
	private boolean hover = false;
	
	public Button(Rectangle rect, Font font) {
		this.rect = rect;
		this.color = Color.white;
		this.text = "";
		this.font = font;
	}
	
	public Button(int x, int y, int width, int height, Font font) {
		this(new Rectangle(x, y, width, height), font);
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getX() {
		return rect.x;
	}
	
	public int getY() {
		return rect.y;
	}
	
	public int getWidth() {
		return rect.width;
	}
	
	public int getHeight() {
		return rect.height;
	}
	
	public void addCallback(MouseInteractive callback) {
		callbacks.add(callback);
	}
	
	public void paintComponent(Graphics2D g) {
		g.setColor(color);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(color.darker());
		Graphic.drawCenteredString(g, text, new Rectangle(getX(), getY(), getWidth(), getHeight()), font);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(e.getX() > rect.x && e.getX() < rect.x+rect.width && e.getY() > rect.y && e.getY() < rect.y+rect.height) {
			if(!hover)
				for(MouseInteractive callback : callbacks) callback.mouseEntered();
			hover = true;
		} else {
			if(hover)
				for(MouseInteractive callback : callbacks) callback.mouseExited();
			hover = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getX() > rect.x && e.getX() < rect.x+rect.width && e.getY() > rect.y && e.getY() < rect.y+rect.height)
			for(MouseInteractive callback : callbacks) callback.onPress();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getX() > rect.x && e.getX() < rect.x+rect.width && e.getY() > rect.y && e.getY() < rect.y+rect.height) 
			for(MouseInteractive callback : callbacks) callback.onRelease();
	}
	
	@Override public void mouseDragged(MouseEvent e) {}
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	
}
