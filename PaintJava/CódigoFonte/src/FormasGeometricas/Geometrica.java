package FormasGeometricas;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

public abstract class Geometrica implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Point p;
	protected Color cor;
	protected Color borda = null;
	protected float LargBorda = 1;
	
	public Geometrica(Color cor, Color corBorda, float LargBorda){
		
		this.cor = cor;
		this.borda = corBorda;
		this.LargBorda = LargBorda;
		
	}
	
	public void PontoP(int x, int y){
		p = new Point(x, y);
	}
	
	public int x(){ 
		return p.x;
		}
	
	public int y(){ 
		return p.y;
		}

	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	public Color getBorda() {
		return borda;
	}

	public void setBorda(Color borda) {
		this.borda = borda;
	}

	public float getLargBorda() {
		return LargBorda;
	}

	public void setLargBorda(int largBorda) {
		LargBorda = largBorda;
	}
	
	public void CloneSetP(Point p) {
		this.p.x = (p.x/2);
		this.p.y = (p.y/2);
	}
	
	public abstract void AtualizarMOUSE(int CoordX, int CoordY);
}
