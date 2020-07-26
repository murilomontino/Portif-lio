package Auxiliares;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import FormasGeometricas.Circulo2D;
import FormasGeometricas.Figura2D;
import FormasGeometricas.Geometrica;
import FormasGeometricas.PoligonoLivre;
import FormasGeometricas.Quadrado2D;
import FormasGeometricas.Triangulo2D;

public class Editor extends PainelDesenho {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1494407248611456542L;
	private String DIRETORIO = null;
	private int ContadorDeFiguras = 0;
	protected ArrayList<Geometrica> dados = new ArrayList<>();

	public void clear() {
		figura.clear();
		figura.add(branco);
		dados.clear();
		DIRETORIO = null;
	};

	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////

	public void salvarFiguras(Editor painelAtual) {
		try {
			File contador = new File(DIRETORIO + "ContadorDeFigura.txt");
			PrintStream contagem = new PrintStream(contador);
			System.setOut(contagem);
			System.out.println(ContadorDeFiguras);
			contagem.flush();
			contagem.close();
			for (int i = 0; i < painelAtual.ContadorDeFiguras; i++) {
				FileOutputStream arq = new FileOutputStream(DIRETORIO + i + ".fig");
				ObjectOutputStream escrever = new ObjectOutputStream(new BufferedOutputStream(arq));
				escrever.writeObject(painelAtual.dados.get(i));
				escrever.flush();
				escrever.close();

			}

		} catch (Exception e) {
			System.out.println("ERRRO");

		}

	}

	public void salvarFiguras(String a, Editor painelAtual) {
		try {
			DIRETORIO = a;
			System.out.println(DIRETORIO);
			salvarFiguras(painelAtual);

		} catch (Exception e) {
			System.out.println("ERRRO");

		}

	}

	public void carregarFiguras(String arquivo) {
		try {
			clear();
			DIRETORIO = arquivo;
			BufferedReader br = new BufferedReader(new FileReader(DIRETORIO + "ContadorDeFigura.txt"));
			String linha = br.readLine();
			ContadorDeFiguras = Integer.parseInt(linha);
			br.close();

			for (int i = 0; i < ContadorDeFiguras; i++) {

				ObjectInputStream ler = new ObjectInputStream(
		   new BufferedInputStream(new FileInputStream(DIRETORIO + i + ".fig")));
				Figura2D leitura = (Figura2D) ler.readObject();
				ler.close();
				if (leitura instanceof Circulo2D) {
					Circulo2D p = (Circulo2D) leitura;
					addDados(p);
					addFigura(p);
					repaint();
				}

				else if (leitura instanceof Quadrado2D) {
					Quadrado2D p = (Quadrado2D) leitura;
					addDados(p);
					addFigura(p);
					repaint();

				}

				else if (leitura instanceof Triangulo2D) {
					Triangulo2D p = (Triangulo2D) leitura;
					addDados(p);
					addFigura(p);
					repaint();
				}
				else if (leitura instanceof PoligonoLivre) {
					PoligonoLivre p = (PoligonoLivre) leitura;
					addDados(p);
					addFigura(p);
					repaint();
				}
				else
					JOptionPane.showMessageDialog(null, "Não é um objeto da Classe Figuras Geometricas");

			}

		} catch (Exception e) {
			System.out.println("ERRRO");

		}

	}

	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	public void setDiretorio(String diretorio) {
		DIRETORIO = diretorio;
	}

	public String getDiretorio() {
		return DIRETORIO;
	}

	public void imprimirFiguras() {
		System.out.println(figura);
	}

	public void addDados(Geometrica a) {
		dados.add(a);
	}
	
	public int getContadorDeFiguras() {
		return ContadorDeFiguras;
	}

	public void setContadorDeFiguras(int contadorDeFiguras) {
		ContadorDeFiguras += contadorDeFiguras;
	}
	public void limparContadorDeFiguras(){
		ContadorDeFiguras = 1;
	}
	public void removerIndex(int i) {
		if(ContadorDeFiguras > 0){
		if (i < figura.size()) {
		figura.remove(i);
		}}
		
	}

	public ArrayList<Geometrica> getDados() {
		return dados;
	}
	
	public void setDados(ArrayList<Geometrica> dados) {
		this.dados = dados;
	}
	
	public void carregarFiguras(ArrayList<Geometrica> leitura) {
		clear();
		try {
			for (int i = 0; i < ContadorDeFiguras; i++) {
				if (leitura.get(i) instanceof Circulo2D) {
					Circulo2D p = (Circulo2D) leitura.get(i);
					addDados(p);
					addFigura(p);
					repaint();
				}

				else if (leitura.get(i) instanceof Quadrado2D) {
					Quadrado2D p = (Quadrado2D) leitura.get(i);
					addDados(p);
					addFigura(p);
					repaint();

				}

				else if (leitura.get(i) instanceof Triangulo2D) {
					Triangulo2D p = (Triangulo2D) leitura.get(i);
					addDados(p);
					addFigura(p);
					repaint();
				}
				else if (leitura.get(i) instanceof PoligonoLivre) {
					PoligonoLivre p = (PoligonoLivre) leitura.get(i);
					addDados(p);
					addFigura(p);
					repaint();
				}
				else
					JOptionPane.showMessageDialog(null, "Não é um objeto da Classe Figuras Geometricas");

			}

		} catch (Exception e) {
			System.out.println("ERRRO");

		}

	}

}
