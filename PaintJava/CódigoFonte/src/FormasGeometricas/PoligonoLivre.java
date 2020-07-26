package FormasGeometricas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

@SuppressWarnings("serial")
public class PoligonoLivre extends Poligonos2D implements Figura2D {

	public PoligonoLivre(Color cor, Color corBorda, float LargBorda) {
		super(cor, corBorda, LargBorda);
	}

	@Override
	public void draw(Graphics a) {
		Polygon livre = new Polygon();
		for (int i = 0; i < vertices.size(); i++) {
			livre.addPoint(vertices.get(i).x, vertices.get(i).y);
		}
		a.setColor(cor);
		a.fillPolygon(livre);
		if(borda!= null){
			Graphics2D c = (Graphics2D) a;	
			c.setColor(borda);
			c.setStroke(new BasicStroke(LargBorda));
			for (int i = 0; i < vertices.size(); i++) {
				livre.addPoint(vertices.get(i).x, vertices.get(i).y);
			}
			a.drawPolygon(livre);
			}
	}

	@Override
	public void AtualizarMOUSE(int CoordX, int CoordY) {
		// TODO Auto-generated method stub
		
	}



}
