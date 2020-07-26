package FormasGeometricas;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class Poligonos2D extends Geometrica  {
	
	protected ArrayList<Point> vertices = new ArrayList<Point>(); 
	
	public Poligonos2D(Color cor, Color corBorda, float LargBorda) {
		super(cor, corBorda, LargBorda);
	}
	
	public void setPoint(Point a){
		vertices.add(a);
	}

	public ArrayList<Point> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Point> vertices) {
		this.vertices = vertices;
	}
	
	public void CloneSetVertices(ArrayList<Point> vertices) {
		for (int i = 0; i < vertices.size(); i++) {
			vertices.get(i).x = vertices.get(i).x/2;
			vertices.get(i).y = vertices.get(i).y/2;
		}
	}

}
