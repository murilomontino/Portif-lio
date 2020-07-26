package Auxiliares;

import java.awt.Dimension;

@SuppressWarnings("serial")
public class EditorCamada extends Editor {
	
	private int TamanhoX;
	private int TamanhoY;
	
	public EditorCamada(int tamanhoX, int tamanhoY) {
		this.TamanhoX = tamanhoX;
		this.TamanhoY = tamanhoY;
	
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(TamanhoX, TamanhoY);
	}
	@Override
	public Dimension getMinimunSize() {
		return getPreferredSize();
	}
	@Override
	public void addFundo() {
		addFigura(branco);
		repaint();
	}

}
