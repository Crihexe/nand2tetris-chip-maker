package com.crihexe;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import com.crihexe.utils.Graphic;
import com.crihexe.utils.Pin;
import com.crihexe.utils.PinList;

public class Chip implements MouseListener, MouseMotionListener {
	
	private static final int pinWidth = 7;
	private static final int pinWidthPadding = 10;
	private static final int pinBoxWidth = pinWidth + 2*pinWidthPadding;
	
	private static final int pinHeight = 18;
	
	private static final double pinLongFactor = 3.0/1.0;
	
	private static final int chipHeight = 52;
	
	private static final int orientingDotSize = 10;
	private static final int orientingDotPadding = 3;
	
	private static final int textPadding = 12;
	
	private static final Font CHIP_FONT = Graphic.CHIP_FONT.deriveFont(22f);
	
	private String name = "CHIP";
	private PinList<Pin> inputs = new PinList<Pin>(true);
	private PinList<Pin> outputs = new PinList<Pin>(false);
	
	public void processOutputs() {
		
		
		
	}
	
	public Point getPinPoint(Pin pin) {
		int index = inputs.indexOf(pin);
		if(index == -1) index = outputs.indexOf(pin);
		
		
	}
	
	public BufferedImage paint() {
		
		PinList<Pin> maxPins = getMaxPins();
		PinList<Pin> minPins = getMinPins();
		
		int chipWidth = maxPins.size() * pinBoxWidth;
		
		FontMetrics metrics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_BGR).getGraphics().getFontMetrics(CHIP_FONT);
		int textWidth = metrics.stringWidth(getName()) + 2*textPadding;
		if(textWidth + 2*orientingDotPadding + orientingDotSize > chipWidth) chipWidth = textWidth;
		
		int height = chipHeight + 2*pinHeight;
		
		BufferedImage image = new BufferedImage(chipWidth, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		int chipX = 0;
		int chipY = pinHeight;
		
		g.setColor(new Color(31, 31, 31));
		g.fillRect(chipX, chipY, chipWidth, chipHeight);
		g.setColor(new Color(21, 21, 21));
		g.fillOval(chipX + orientingDotPadding, height/2 - orientingDotSize/2, orientingDotSize, orientingDotSize);
		
		g.setColor(new Color(212, 212, 212));
		// i valori a cazzo sono per centrarla un pochino meglio siccome non ho voglia di farlo bene
		Graphic.drawCenteredString(g, getName(), new Rectangle(chipX + 2, chipY+1, chipWidth - 2, chipHeight), CHIP_FONT);
		g.setFont(CHIP_FONT);
		
		// pins
		g.setColor(new Color(200, 200, 200));
		// lato con più pin
		/*for(int i = 0; i < maxPins.size(); i++) {
			// li faccio più stretti possibile rispettando padding
			int pinX = pinWidthPadding + (pinBoxWidth * i);
			int pinY = chipY;
			if(maxPins.hasInputs()) pinY -= pinHeight - (int)(pinHeight/pinLongFactor);
			else pinY += chipHeight - (int)(pinHeight/pinLongFactor);
			
			g.fillRect(chipX + pinX, pinY, pinWidth, pinHeight);
		}*/
		// lato con più pin
		if(maxPins.size() > 0) {
			int extraWidth = chipWidth - pinBoxWidth*maxPins.size();
			int maxExtraPadding = (extraWidth) / maxPins.size();
			int maxPinBoxWidth = pinBoxWidth+maxExtraPadding;
			for(int i = 0; i < maxPins.size(); i++) {
				int pinX = pinWidthPadding+maxExtraPadding/2 + (maxPinBoxWidth * i);
				int pinY = chipY;
				if(maxPins.hasInputs()) pinY -= pinHeight - (int)(pinHeight/pinLongFactor);
				else pinY += chipHeight - (int)(pinHeight/pinLongFactor);
				
				g.fillRect(chipX + pinX, pinY, pinWidth, pinHeight);
			}
		}
		// lato con meno pin
		if(minPins.size() > 0) {
			int extraWidth = chipWidth - pinBoxWidth*minPins.size();
			int minExtraPadding = (extraWidth) / minPins.size();
			int minPinBoxWidth = pinBoxWidth+minExtraPadding;
			for(int i = 0; i < minPins.size(); i++) {
				int pinX = pinWidthPadding+minExtraPadding/2 + (minPinBoxWidth * i);
				int pinY = chipY;
				if(!maxPins.hasInputs()) pinY -= pinHeight - (int)(pinHeight/pinLongFactor);
				else pinY += chipHeight - (int)(pinHeight/pinLongFactor);
				
				g.fillRect(chipX + pinX, pinY, pinWidth, pinHeight);
			}
		}
		
		return image;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addInput(String s) {
		inputs.add(new Pin(s));
	}
	
	public void addOutput(String s) {
		outputs.add(new Pin(s));
	}
	
	public PinList<Pin> getMaxPins() {
		return inputs.size() >= outputs.size() ? inputs : outputs;
	}
	public PinList<Pin> getMinPins() {
		return inputs.size() < outputs.size() ? inputs : outputs;
	}
	
	public PinList<Pin> getInputs() {
		return inputs;
	}
	public PinList<Pin> getOutputs() {
		return outputs;
	}
	
	public String getInputName(int index) {
		return inputs.get(index).getName();
	}
	
	public String getOutputName(int index) {
		return outputs.get(index).getName();
	}
	
	public int inputSize() {
		return inputs.size();
	}
	
	public int outputSize() {
		return outputs.size();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseDragged(MouseEvent e) {}
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	
}
