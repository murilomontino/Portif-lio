package FormasGeometricas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

@SuppressWarnings("serial")
public class Triangulo2D extends Poligonos2D implements Figura2D

{
	  private double base;
	  private double altura;
	  
	    
	    public Triangulo2D(Color cor, int x, int y, double base, double altura, Color corBorda, float LargBorda){
	        super(cor, corBorda, LargBorda);
	        this.base = base;
	        this.altura = altura;
	        PontoP(x,y);
	        AtualizarMOUSE(p.x , p.y);
	    
	    }
	    
	    public double area(){
	        return base*altura;
	    }

		@Override
		public void draw(Graphics a) {
			Polygon triangulo = new Polygon();
			for (int i = 0; i < vertices.size(); i++) {
				triangulo.addPoint(vertices.get(i).x, vertices.get(i).y);
			}
			a.setColor(cor);
			a.fillPolygon(triangulo);
			if(borda!= null){
				Graphics2D c = (Graphics2D) a;	
				c.setColor(borda);
				c.setStroke(new BasicStroke(LargBorda));
				for (int i = 0; i < vertices.size(); i++) {
					triangulo.addPoint(vertices.get(i).x, vertices.get(i).y);
				}
				a.drawPolygon(triangulo);
				}
		}

		public void AtualizarMOUSE(int CoordX, int CoordY){
			vertices.clear();
			vertices.add( new Point(CoordX,   (int) ( CoordY - (altura/2) )));
	        vertices.add( new Point((int) (CoordX - (base/2) ),   (int) ( CoordY + (altura/2) )));
	        vertices.add( new Point((int) (CoordX + (base/2) ),   (int) ( CoordY + (altura/2) )));	
			
		}
		public double getBase() {
			return base;
		}

		public void setBase(double base) {
			this.base = base;
		}

		public double getAltura() {
			return altura;
		}

		public void setAltura(double altura) {
			this.altura = altura;
		}
		
		public void CloneSetBase(double base) {
			this.base = base;
		}
		
		public void CloneSetAltura(double altura) {
			this.altura = altura;
		}
}
