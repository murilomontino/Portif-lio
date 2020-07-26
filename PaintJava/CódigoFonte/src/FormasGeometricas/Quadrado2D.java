package FormasGeometricas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

@SuppressWarnings("serial")
public class Quadrado2D extends Geometrica implements Figura2D
{

	public Quadrado2D(Color cor, int x, int y, int Altura, int Base, Color corBorda, float LargBorda) {
		super(cor, corBorda, LargBorda);
	        this.Altura = Altura;
	        this.Base = Base;
			PontoP(x, y);
			AtualizarMOUSE(x,y);
			}

	private int Altura;
    private int Base;
    
    
	@Override
	public void draw(Graphics a) {
		a.setColor(cor);
		a.fillRect(p.x, p.y, getBase(), getAltura());
		if(borda!= null){
			Graphics2D c = (Graphics2D) a;	
			c.setColor(borda);
			c.setStroke(new BasicStroke(LargBorda));
			c.drawRect(p.x , p.y , Base, Altura);
			}
	}

	public int getBase() {
		return Base;
	}

	public void setAltura(int ladoB) {
		this.Altura = ladoB;
	}

	public int getAltura() {
		return Altura;
	}
	
	public void AtualizarMOUSE(int CoordX, int CoordY){
		int x = (CoordX - (Base/2));
		int y = (CoordY - (Altura/2));
		
		setP(new Point(x,y));	
		
	}
	public void setBase(int ladoA) {
		this.Base = ladoA;
	}

    
	public void CloneSetBase(int ladoB) {
		this.Base = ladoB/2;
	}
	public void CloneSetAltura(int ladoA) {
		this.Altura = ladoA/2;
	}
}
