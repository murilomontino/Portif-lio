package Auxiliares;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import FormasGeometricas.Figura2D;
import FormasGeometricas.Quadrado2D;

@SuppressWarnings("serial")
public class PainelDesenho extends JComponent {

	protected List<Figura2D> figura = new ArrayList<>();
	protected static Quadrado2D branco;

	public PainelDesenho() {
		AtivandoLousa();
		addFundo();
	}
	
	public void AtivandoLousa(){
		branco = new Quadrado2D(Color.WHITE, 400, 300, 600,800, null, 0);
	}
	
	public void addFigura(Figura2D f) {
		figura.add(f);
	}

	public void removerIndex(int i) {
		if (i < figura.size()) {
			figura.remove(i);
		}
	}

	public void addFundo() {
		addFigura(branco);
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

	public Dimension getMinimunSize() {
		return getPreferredSize();
	}

	public void rmFigura(Figura2D f) {
		figura.remove(f);
	}

	@Override
	public void paintComponent(Graphics a) {
		super.paintComponent(a);
		for (Figura2D f : figura) {
			f.draw(a);
		}
	}

	public List<Figura2D> getFigura() {
		return figura;
	}

	public void setFigura(List<Figura2D> figura) {
		this.figura = figura;
	}

}