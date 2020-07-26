package Auxiliares;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import FormasGeometricas.Circulo2D;
import FormasGeometricas.Geometrica;
import FormasGeometricas.PoligonoLivre;
import FormasGeometricas.Quadrado2D;
import FormasGeometricas.Triangulo2D;

public abstract class Clonador {
	
	////// CLONA RENDIMESSIONANDO PARA A JANELA CAMADAS
	public static Quadrado2D Clone(Quadrado2D original) {
		Color cor = original.getCor();
		Color corBorda = original.getBorda();
		float LargBorda = original.getLargBorda();
		int X = original.getP().x / 2;
		int Y = original.getP().y / 2;
		
		int Base = (int) (original.getBase() / 2);
		int Altura = (int) (original.getAltura() / 2);
		return new Quadrado2D(cor, X, Y,Altura , Base, corBorda, LargBorda);
	}

	public static Triangulo2D Clone(Triangulo2D original) {
		Color cor = original.getCor();
		Color corBorda = original.getBorda();
		float LargBorda = original.getLargBorda();
		int X = (int) original.getP().x / 2;
		int Y = (int) original.getP().y / 2;
		double Altura = (original.getAltura() / 2);
		double Base = (original.getBase() / 2);
		return new Triangulo2D(cor, X, Y, Base, Altura, corBorda, LargBorda);

	}

	public static Circulo2D Clone(Circulo2D original) {
		Color cor = original.getCor();
		Color corBorda = original.getBorda();
		float LargBorda = original.getLargBorda();
		int X = (int) original.getP().x / 2;
		int Y = (int) original.getP().y / 2;
		int Raio1 = (original.getRaio1() / 2);
		int Raio2 = (original.getRaio2() / 2);
		return new Circulo2D(X, Y, Raio1, Raio2, cor, corBorda, LargBorda);
	}

	public static PoligonoLivre Clone(PoligonoLivre original) {
		Color cor = original.getCor();
		Color corBorda = original.getBorda();
		float LargBorda = original.getLargBorda();
		ArrayList<Point> vertices = Clone(original.getVertices());
		PoligonoLivre aux = new PoligonoLivre(cor, corBorda, LargBorda);
		aux.setVertices(vertices);
		return aux;
	}

	public static ArrayList<Point> Clone(ArrayList<Point> original) {
		ArrayList<Point> vertices = new ArrayList<Point>();
		for (int i = 0; i < original.size(); i++) {
			int x = original.get(i).x/2;
			int y = original.get(i).y/2;
			vertices.add(new Point(x, y));
		}
		return vertices;
	}

	
	///////////////////// DESCLONA
	public static Quadrado2D Desclonagem(Quadrado2D original) {
		Color cor = original.getCor();
		Color corBorda = original.getBorda();
		float LargBorda = original.getLargBorda();
		int X = original.getP().x * 2;
		int Y = original.getP().y * 2;
		;
		int Altura = (int) (original.getAltura() * 2);
		int Base = (int) (original.getBase() * 2);
		return new Quadrado2D(cor, X, Y, Altura, Base, corBorda, LargBorda);
	}
	
	public static Triangulo2D Desclonagem(Triangulo2D original) {
		Color cor = original.getCor();
		Color corBorda = original.getBorda();
		float LargBorda = original.getLargBorda();
		int X = (int) original.getP().x * 2;
		int Y = (int) original.getP().y * 2;
		double Altura = (original.getAltura() * 2);
		double Base = (original.getBase() * 2);
		return new Triangulo2D(cor, X, Y, Base, Altura, corBorda, LargBorda);

	}

	public static Circulo2D Desclonagem(Circulo2D original) {
		Color cor = original.getCor();
		Color corBorda = original.getBorda();
		float LargBorda = original.getLargBorda();
		int X = (int) original.getP().x * 2;
		int Y = (int) original.getP().y * 2;
		int Raio1 = (original.getRaio1() * 2);
		int Raio2 = (original.getRaio2() * 2);
		return new Circulo2D(X, Y, Raio1, Raio2, cor, corBorda, LargBorda);
	}

	public static PoligonoLivre Desclonagem(PoligonoLivre original) {
		Color cor = original.getCor();
		Color corBorda = original.getBorda();
		float LargBorda = original.getLargBorda();
		ArrayList<Point> vertices = Clone(original.getVertices());
		PoligonoLivre aux = new PoligonoLivre(cor, corBorda, LargBorda);
		aux.setVertices(vertices);
		return aux;
	}

	public static ArrayList<Point> Desclonagem(ArrayList<Point> original) {
		ArrayList<Point> vertices = new ArrayList<Point>();
		for (int i = 0; i < original.size(); i++) {
			int x = original.get(i).x*2;
			int y = original.get(i).y*2;
			vertices.add(new Point(x, y));
		}
		return vertices;
	}
	
	
	//////////////CLONADOR SEM REDIMENSIONAR
	public static Quadrado2D ExClonador(Quadrado2D original) {
		Color cor = original.getCor();
		Color corBorda = original.getBorda();
		float LargBorda = original.getLargBorda();
		int X = original.getP().x ;
		int Y = original.getP().y ;
		int Altura = (int) (original.getAltura());
		int Base = (int) (original.getBase());
		return new Quadrado2D(cor, X, Y, Altura, Base, corBorda, LargBorda);
	}
	
	public static Triangulo2D ExClonador(Triangulo2D original) {
		Color cor = original.getCor();
		Color corBorda = original.getBorda();
		float LargBorda = original.getLargBorda();
		int X = (int) original.getP().x;
		int Y = (int) original.getP().y;
		double Altura = (original.getAltura());
		double Base = (original.getBase());
		return new Triangulo2D(cor, X, Y, Base, Altura, corBorda, LargBorda);

	}

	public static Circulo2D ExClonador(Circulo2D original) {
		Color cor = original.getCor();
		Color corBorda = original.getBorda();
		float LargBorda = original.getLargBorda();
		int X = (int) original.getP().x;
		int Y = (int) original.getP().y ;
		int Raio1 = (original.getRaio1());
		int Raio2 = (original.getRaio2());
		return new Circulo2D(X, Y, Raio1, Raio2, cor, corBorda, LargBorda);
	}

	public static PoligonoLivre ExClonador(PoligonoLivre original) {
		Color cor = original.getCor();
		Color corBorda = original.getBorda();
		float LargBorda = original.getLargBorda();
		ArrayList<Point> vertices = Clone(original.getVertices());
		PoligonoLivre aux = new PoligonoLivre(cor, corBorda, LargBorda);
		aux.setVertices(vertices);
		return aux;
	}

	public static ArrayList<Point> ExClonador(ArrayList<Point> original) {
		ArrayList<Point> vertices = new ArrayList<Point>();
		for (int i = 0; i < original.size(); i++) {
			int x = original.get(i).x;
			int y = original.get(i).y;
			vertices.add(new Point(x, y));
		}
		return vertices;
	}
	
	
	public static ArrayList<Geometrica> ExClonadorMaster(ArrayList<Geometrica> original){
		 ArrayList<Geometrica> clone = new ArrayList<Geometrica>(); 
		try {
			 for (int i = 0; i < original.size(); i++) {
				if (original.get(i) instanceof Circulo2D) {
					Circulo2D aux = ExClonador((Circulo2D) original.get(i));
					clone.add(aux);
				}

				else if (original.get(i) instanceof Quadrado2D) {
					Quadrado2D aux = ExClonador((Quadrado2D) original.get(i));
					clone.add(aux);
				}

				else if (original.get(i) instanceof Triangulo2D) {
					Triangulo2D aux = ExClonador((Triangulo2D) original.get(i));
					clone.add(aux);

				}
				else if (original.get(i) instanceof PoligonoLivre) {
					PoligonoLivre aux = ExClonador((PoligonoLivre) original.get(i));
					clone.add(aux);

				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao copiar");
		}
		
		return clone;
	}
	
	
}
