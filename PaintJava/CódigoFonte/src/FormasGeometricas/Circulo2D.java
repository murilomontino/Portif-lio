package FormasGeometricas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

@SuppressWarnings("serial")
public class Circulo2D extends Geometrica implements Figura2D {

	private int raio1;
	private int raio2;
	
	public Circulo2D(int x, int y,int raio01, int raio02, Color cor, Color corBorda, float LargBorda) {
		super(cor, corBorda, LargBorda);
		this.raio1 = VerificarRaio(raio01);
		this.raio2 = VerificarRaio(raio02);
		PontoP(x,y);
		PontoAtualizado();
	}

	
	
	
	
	@Override
	public void draw(Graphics a) {
	
		a.setColor(cor);
		a.fillOval(p.x, p.y, raio1 , raio2 );
		if(borda!= null){
		Graphics2D c = (Graphics2D) a;	
		c.setColor(borda);
		c.setStroke(new BasicStroke(LargBorda));
		c.drawOval(p.x, p.y, raio1, raio2);
		}
	}


	
	public int getRaio1() {
		return raio1;
	}
	
	public void PontoAtualizado(){
		int x = (p.x - (raio1/2));
		int y = (p.y - (raio2/2));
		
		setP(new Point(x,y));	
	}
	
	
	public void AtualizarMOUSE(int CoordX, int CoordY){
		int x = (CoordX - (raio1/2));
		int y = (CoordY - (raio2/2));
		
		setP(new Point(x,y));	
		
	}
	
	public int getRaio2() {
		return raio2;
	}


	public int VerificarRaio(int r){
		if(r >= 0){
		return r;
		}
		else 
		return 0;
	}
	
	public void setRaio1(int raio) {
		this.raio1 = raio;
	}	
	
	public void setRaio2(int raio) {
		this.raio2 = raio;
	}

	public void CloneRaio1(int raio) {
		this.raio1 = raio/2;
	}	
	
	public void CloneRaio2(int raio) {
		this.raio2 = raio/2;
	}

}
