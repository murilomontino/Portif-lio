package Janelas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Auxiliares.Clonador;
import Auxiliares.Editor;
import Auxiliares.EditorCamada;
import FormasGeometricas.Circulo2D;
import FormasGeometricas.Figura2D;
import FormasGeometricas.Geometrica;
import FormasGeometricas.PoligonoLivre;
import FormasGeometricas.Quadrado2D;
import FormasGeometricas.Triangulo2D;

@SuppressWarnings("serial")
public class Camadas extends JFrame {

	private int INDICEREAL = 0;
	private Editor principal = new Editor(); // DADOS QUE VEM DO PAINT, E
												// PERMANENCEM INTACTOS
	private Editor auxiliar = new Editor(); // DADOS MODIFICADOS PARA CABEREM NO
											// NOVO ESPAÇO DE TELA E MELHOR
											// VISUALIZAÇÃO
	private int a;
	private ArrayList<Geometrica> dados = new ArrayList<>(); // CLONO OS DADOS DO PRINCIPAL E TODAS AS ALTERAÇÔES VIRAM PRA ESSE, DEPOIS JOGO TUDO PRO PRINCIPAL
	private String[] CamadasDoDesenho;
	private JPanel contentPane;
	private JTextField CoordenadaX;
	private JTextField CoordenadaY;
	private JTextField Altura;
	private JTextField Base01;
	private JTextField Raio01;
	private JTextField Raio02;
	private JComboBox<String> CaixaDeCamadas;
	private JComboBox<String> Vertices;
	private boolean mover = false;
	private EditorCamada editorCamada;
	private int auxiliarDeEscolha;
	
	private boolean JanelaAberta = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Camadas frame = new Camadas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Camadas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				setJanelaAberta(false);
			}
			@Override
			public void windowClosing(WindowEvent e) {
				setJanelaAberta(false);
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				setJanelaAberta(false);
			}
		});
		setResizable(false);
		Vertices = new JComboBox();
		Vertices.setEnabled(false);

		CaixaDeCamadas = new JComboBox();
		editorCamada = new EditorCamada(400, 300);
		editorCamada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(mover){
					MoverDados(e.getX(), e.getY());
					mover = false;
				}
				
				
			}
		});
		setType(Type.POPUP);
		setTitle("EDITOR DE CAMADAS");
		setBounds(100, 100, 600, 425);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Vertices.setFont(new Font("Arial", Font.BOLD, 10));
		Vertices.setModel(new DefaultComboBoxModel(new String[] { "VERTÍCES" }));
		Vertices.setBackground(SystemColor.textHighlightText);
		Vertices.setToolTipText("PONTOS");
		Vertices.setBounds(478, 65, 81, 20);
		contentPane.add(Vertices);

		JButton Pula01 = new JButton(">>");
		Pula01.setEnabled(false);
		Pula01.setBackground(SystemColor.textHighlightText);
		Pula01.setBounds(329, 351, 57, 23);
		contentPane.add(Pula01);

		JButton Volta01 = new JButton("<<");
		Volta01.setEnabled(false);
		Volta01.setBackground(SystemColor.textHighlightText);
		Volta01.setBounds(99, 351, 49, 23);
		contentPane.add(Volta01);

		JComboBox Cor = new JComboBox();
		Cor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int cor = Cor.getSelectedIndex();
				AtualizarCOR(cor);
				
				
			}
		});
		Cor.setFont(new Font("Arial", Font.BOLD, 10));
		Cor.setModel(
				new DefaultComboBoxModel(new String[] { "COR", "BRANCA", "CINZA CLARO", "CINZA ", "CINZA ESCURO",
						"ROSA", "VERMELHO", "VERDE", "VERDE FORTE", "ROXO", "AZUL MARINHO", "AZUL FORTE", "PRETO" }));
		Cor.setToolTipText("PONTOS");
		Cor.setBackground(SystemColor.textHighlightText);
		Cor.setBounds(481, 146, 78, 20);
		contentPane.add(Cor);

		CoordenadaX = new JTextField();
		CoordenadaX.setEnabled(false);
		CoordenadaX.setEditable(false);
		CoordenadaX.setText("X");
		CoordenadaX.setBounds(488, 96, 30, 20);
		contentPane.add(CoordenadaX);
		CoordenadaX.setColumns(10);

		CoordenadaY = new JTextField();
		CoordenadaY.setEnabled(false);
		CoordenadaY.setEditable(false);
		CoordenadaY.setText("Y");
		CoordenadaY.setColumns(10);
		CoordenadaY.setBounds(488, 115, 30, 20);
		contentPane.add(CoordenadaY);

		JButton PulaFinal = new JButton(">>>");
		PulaFinal.setEnabled(false);
		PulaFinal.setBackground(SystemColor.textHighlightText);
		PulaFinal.setBounds(386, 351, 57, 23);
		contentPane.add(PulaFinal);

		JButton RetornaInicio = new JButton("<<<");
		RetornaInicio.setEnabled(false);
		RetornaInicio.setBackground(SystemColor.textHighlightText);
		RetornaInicio.setBounds(43, 351, 57, 23);
		contentPane.add(RetornaInicio);

		JPanel panel = new JPanel();
		panel.setBounds(43, 51, 400, 300);
		contentPane.add(panel);
		panel.setLayout(null);

		editorCamada.setBounds(0, 0, 400, 300);
		panel.add(editorCamada);
		editorCamada.setMinimumSize(new Dimension(400, 300));
		editorCamada.setMaximumSize(new Dimension(400, 300));

		Altura = new JTextField();
		Altura.setText("Altura");
		Altura.setEditable(false);
		Altura.setColumns(10);
		Altura.setBounds(460, 177, 41, 20);
		contentPane.add(Altura);

		Base01 = new JTextField();
		Base01.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					String base = Base01.getText().replaceAll(" ", "");
					String altura = Altura.getText().replaceAll(" ", "");
					int alt = Integer.parseInt(altura);
					int bas = Integer.parseInt(base);
					if (dados.get(a()) instanceof Quadrado2D) {
						Quadrado2D aux = (Quadrado2D) dados.get(a());
						aux.setAltura(alt);
						aux.setBase(bas);
						dados.set(a(), aux);
						Quadrado2D aux2 = Clonador.Clone(aux);
						auxiliar.getDados().set(a(), aux2);
						editorCamada.getFigura().set(a(), aux2);
						repaint();
						AtualizarOk();
					}
					if (dados.get(a()) instanceof Triangulo2D) {
						Triangulo2D aux = (Triangulo2D) dados.get(a());
						aux.setAltura(alt);
						aux.setBase(bas);
						dados.set(a(), aux);
						Triangulo2D aux2 = Clonador.Clone(aux);
						auxiliar.getDados().set(a(), aux2);
						editorCamada.getFigura().set(a(), aux2);
						AtualizarOk();
					}
					
					repaint();

					
				} catch (Exception e) {
				}
				
			}
		});
		Base01.setText("Base");
		Base01.setEditable(false);
		Base01.setColumns(10);
		Base01.setBounds(503, 177, 41, 20);
		contentPane.add(Base01);

		Raio01 = new JTextField();
		Raio01.setText("Raio\r\n");
		Raio01.setEditable(false);
		Raio01.setColumns(10);
		Raio01.setBounds(460, 200, 41, 20);
		contentPane.add(Raio01);

		Raio02 = new JTextField();
		Raio02.setText("Raio\r\n");
		Raio02.setEditable(false);
		Raio02.setColumns(10);
		Raio02.setBounds(503, 200, 41, 20);
		contentPane.add(Raio02);

		JButton Delete = new JButton("Delete\r\n");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete();
			}
		});
		Delete.setFont(new Font("Arial", Font.BOLD, 10));
		Delete.setBackground(SystemColor.textHighlightText);
		Delete.setBounds(200, 351, 69, 23);
		contentPane.add(Delete);

		JButton Aplicar = new JButton("Aplicar");
		Aplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NovosDados();
			}
		});
		Aplicar.setFont(new Font("Arial", Font.BOLD, 10));
		Aplicar.setBackground(SystemColor.textHighlightText);
		Aplicar.setBounds(490, 351, 69, 23);
		contentPane.add(Aplicar);

		CaixaDeCamadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					editorCamada.getFigura().clear();
					editorCamada.addFundo();
					if(((String) CaixaDeCamadas.getSelectedItem() == "GERAL") || ((String) CaixaDeCamadas.getSelectedItem() == "DESENHO") ){
						AtivandoOpcoes(-1);
						
						editorCamada.getFigura().clear();// LIMPA A TELA
						editorCamada.addFundo();// ADD FUNDO A TELA
						for (int i = 0; i < auxiliar.getDados().size(); i++) {
							AddCamada(i);
						}
						 
					 }
					auxiliarDeEscolha = CaixaDeCamadas.getSelectedIndex();
					String itemSelecionado = CaixaDeCamadas.getSelectedItem().toString();
					itemSelecionado.replaceAll("", " ");
					a = Integer.parseInt(itemSelecionado);
					INDICEREAL = auxiliarDeEscolha;
					AddCamada(a);
					AtivandoOpcoes(a);
					
				} catch (Exception e2) {
					
				}

			}
		});
		CaixaDeCamadas.setFont(new Font("Arial", Font.BOLD, 10));

		CaixaDeCamadas.setModel(new DefaultComboBoxModel(new String[] { "DESENHO" }));
		CaixaDeCamadas.setToolTipText("PONTOS");
		CaixaDeCamadas.setBackground(SystemColor.textHighlightText);
		CaixaDeCamadas.setBounds(43, 28, 93, 20);
		contentPane.add(CaixaDeCamadas);

		JButton Atualizar = new JButton("Atualizar");
		Atualizar.setFont(new Font("Arial", Font.BOLD, 10));
		Atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ATUALIZAR();
			}
		});
		Atualizar.setBackground(SystemColor.textHighlightText);
		Atualizar.setBounds(478, 11, 81, 23);
		contentPane.add(Atualizar);
		
		JButton btnOk = new JButton("OK\r\n");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String base = Base01.getText().replaceAll(" ", "");
					String altura = Altura.getText().replaceAll(" ", "");
					int alt = Integer.parseInt(altura);
					int bas = Integer.parseInt(base);
					if (dados.get(a()) instanceof Quadrado2D) {
						Quadrado2D aux = (Quadrado2D) dados.get(a());
						aux.setAltura(alt);
						aux.setBase(bas);
						dados.set(a(), aux);
						Quadrado2D aux2 = Clonador.Clone(aux);
						auxiliar.getDados().set(a(), aux2);
						repaint();
						AtualizarOk();
						//editorCamada.getFigura().set(a(), aux2);
					
						
					}
					else if (dados.get(a()) instanceof Triangulo2D) {
						Triangulo2D aux = (Triangulo2D) dados.get(a());
						aux.setAltura(alt);
						aux.setBase(bas);
						dados.set(a(), aux);
						Triangulo2D aux2 = Clonador.Clone(aux);
						auxiliar.getDados().set(a(), aux2);
						repaint();
						AtualizarOk();
						//editorCamada.getFigura().set(a(), aux2);
					}
					
					 editorCamada.repaint();

				} catch (Exception e) {
				}
				
			}
		});
		btnOk.setMargin(new Insets(2, 5, 2, 0));
		btnOk.setHorizontalAlignment(SwingConstants.LEFT);
		btnOk.setFont(new Font("Arial", Font.BOLD, 8));
		btnOk.setBackground(Color.WHITE);
		btnOk.setBounds(550, 177, 30, 20);
		contentPane.add(btnOk);
		
		JButton button = new JButton("OK\r\n");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String raio1 = Raio01.getText().replaceAll(" ", "");
				String raio2 = Raio02.getText().replaceAll(" ", "");
				int Raio1 = Integer.parseInt(raio1);
				int Raio2 = Integer.parseInt(raio2);
				Circulo2D aux = (Circulo2D) dados.get(a());
				aux.setRaio1(Raio1);
				aux.setRaio2(Raio2);
				dados.set(a(), aux);
				Circulo2D aux2 = Clonador.Clone(aux);
				auxiliar.getDados().set(a(), aux2);
				repaint();
				AtualizarOk();
				
			}
		});
		button.setMargin(new Insets(2, 5, 2, 0));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setFont(new Font("Arial", Font.BOLD, 8));
		button.setBackground(Color.WHITE);
		button.setBounds(550, 199, 30, 20);
		contentPane.add(button);
		
		JButton button_1 = new JButton("OK\r\n");
		button_1.setEnabled(false);
		button_1.setMargin(new Insets(2, 5, 2, 0));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setFont(new Font("Arial", Font.BOLD, 8));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(522, 96, 30, 20);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("OK\r\n");
		button_2.setEnabled(false);
		button_2.setMargin(new Insets(2, 5, 2, 0));
		button_2.setHorizontalAlignment(SwingConstants.LEFT);
		button_2.setFont(new Font("Arial", Font.BOLD, 8));
		button_2.setBackground(Color.WHITE);
		button_2.setBounds(522, 115, 30, 20);
		contentPane.add(button_2);
		
		JButton btnMover = new JButton("Mover");
		btnMover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mover == false){
				mover = true;
				JOptionPane.showMessageDialog(null, "Click na tela. PS: O Triângulo buga");
				}
				else mover = false;
			}
		});
		btnMover.setFont(new Font("Arial", Font.BOLD, 10));
		btnMover.setBackground(Color.WHITE);
		btnMover.setBounds(490, 231, 69, 23);
		contentPane.add(btnMover);

	}

	public Editor getPrincipal() {
		return principal;
	}

	public void setPrincipal(Editor principal) {
		this.principal = principal;
	}

	public List<Geometrica> getDados() {
		return dados;
	}

	public void setDados(ArrayList<Geometrica> dados) {
		this.dados = Clonador.ExClonadorMaster(dados);
	}

	
	// PEGA AS INFORMAÇÔES VINDAS DA INTERFACE E CLONA-AS PARA SEREM MODIFICADAS
	public void ATUALIZAR() {
		try {
			editorCamada.addFundo();
			auxiliar.clear();
			dados = Clonador.ExClonadorMaster(principal.getDados());
			CaixaDeCamadas.removeAllItems();
			CaixaDeCamadas.addItem("DESENHO");
			CaixaDeCamadas.addItem("GERAL");
			for (int i = 0; i < principal.getDados().size(); i++) {
				CamadasDoDesenho = new String[principal.getDados().size()];
				CamadasDoDesenho[i] = Integer.toString(i);

				/////// AQUI A REDUÇÂO DE TAMANHO E MODIFICAÇÂO DAS COORDENADAS
				/////// PARA CABER NA TELA
				if (principal.getDados().get(i) instanceof Circulo2D) {
					Circulo2D aux = (Circulo2D) principal.getDados().get(i);
					auxiliar.addDados(Clonador.Clone(aux));
					editorCamada.addFigura(Clonador.Clone(aux));
					repaint();
					CaixaDeCamadas.addItem(CamadasDoDesenho[i]);
				}

				if (principal.getDados().get(i) instanceof Quadrado2D) {
					Quadrado2D aux = (Quadrado2D) principal.getDados().get(i);
					auxiliar.addDados(Clonador.Clone(aux));
					editorCamada.addFigura(Clonador.Clone(aux));
					repaint();
					CaixaDeCamadas.addItem(CamadasDoDesenho[i]);
				}
				if (principal.getDados().get(i) instanceof Triangulo2D) {
					Triangulo2D aux = (Triangulo2D) principal.getDados().get(i);
					auxiliar.addDados(Clonador.Clone(aux));
					editorCamada.addFigura(Clonador.Clone(aux));
					repaint();
					CaixaDeCamadas.addItem(CamadasDoDesenho[i]);

				}

				if (principal.getDados().get(i) instanceof PoligonoLivre) {
					PoligonoLivre aux = (PoligonoLivre) principal.getDados().get(i);
					auxiliar.addDados(Clonador.Clone(aux));
					editorCamada.addFigura(Clonador.Clone(aux));
					repaint();
					CaixaDeCamadas.addItem(CamadasDoDesenho[i]);

				}
			}

		} catch (Exception e1) {
			System.out.println("Não há figuras");
		}
		
	}

	
/// METODO DE ATUALIZAÇÃO DOS DADOS, UTILIZADO POR TODAS AS OUTRAS FUNÇÕES QUE SE UTILIZA PARA MODIFICAR	
	
	public void AtualizarOk() {
		CamadasDoDesenho = new String[principal.getDados().size()];
		try {
			editorCamada.clear();
			editorCamada.addFundo();
			CaixaDeCamadas.removeAllItems();
			CaixaDeCamadas.addItem("DESENHO");
			CaixaDeCamadas.addItem("GERAL");
			for (int i = 0; i < auxiliar.getDados().size(); i++) {
				CamadasDoDesenho[i] = Integer.toString(i);

				/////// AQUI A REDUÇÂO DE TAMANHO E MODIFICAÇÂO DAS COORDENADAS
				/////// PARA CABER NA TELA
				if (auxiliar.getDados().get(i) instanceof Circulo2D) {
					Circulo2D aux = (Circulo2D) auxiliar.getDados().get(i);
					editorCamada.addFigura(aux);
					repaint();
					CaixaDeCamadas.addItem(CamadasDoDesenho[i]);
				}

				if (principal.getDados().get(i) instanceof Quadrado2D) {
					Quadrado2D aux = (Quadrado2D) auxiliar.getDados().get(i);
					editorCamada.addFigura(aux);
					repaint();
					CaixaDeCamadas.addItem(CamadasDoDesenho[i]);

				}
				if (principal.getDados().get(i) instanceof Triangulo2D) {
					Triangulo2D aux = (Triangulo2D) auxiliar.getDados().get(i);
					editorCamada.addFigura(aux);
					repaint();
					CaixaDeCamadas.addItem(CamadasDoDesenho[i]);

				}

				if (principal.getDados().get(i) instanceof PoligonoLivre) {
					PoligonoLivre aux = (PoligonoLivre) principal.getDados().get(i);
					auxiliar.addDados(Clonador.Clone(aux));
					editorCamada.addFigura(Clonador.Clone(aux));
					repaint();
					CaixaDeCamadas.addItem(CamadasDoDesenho[i]);


				}

			}

		} catch (Exception e1) {
			System.out.println("Não há figuras");
		}
		if(a() > -1){
			CaixaDeCamadas.setSelectedItem(Integer.toString(a()));
		}
	}
	
	
	
	
///Reseta a tela e adiciona a camada que é representando por aquelçe indiice
	public void AddCamada(int Indice){
		editorCamada.addFigura((Figura2D) auxiliar.getDados().get(Indice));
		repaint();
		
	}


	public boolean isJanelaAberta() {
		return JanelaAberta;
	}

	public void setJanelaAberta(boolean janelaAberta) {
		JanelaAberta = janelaAberta;
	}
	
	public void AtivandoOpcoes(int indice){
		janelasFalsas();
		try {
			if (auxiliar.getDados().get(indice) instanceof Circulo2D) {
				janelasFalsas();
				Raio01.setEditable(true);
				Raio02.setEditable(true);
				Circulo2D aux = (Circulo2D) dados.get(indice);
				Raio01.setText(Integer.toUnsignedString(aux.getRaio1()));
				Raio02.setText(Integer.toUnsignedString(aux.getRaio2()));
			
			}

			if (auxiliar.getDados().get(indice) instanceof Quadrado2D) {
				janelasFalsas();
				Base01.setEditable(true);
				Altura.setEditable(true);
				Quadrado2D aux = (Quadrado2D) dados.get(indice);
				Base01.setText(Integer.toUnsignedString(aux.getBase()));
				Altura.setText(Integer.toUnsignedString(aux.getAltura()));
				repaint();
			}

			if (auxiliar.getDados().get(indice) instanceof Triangulo2D) {
				janelasFalsas();
				Base01.setEditable(true);
				Altura.setEditable(true);
				dados.get(indice);
				Triangulo2D aux = (Triangulo2D) dados.get(indice);
				Base01.setText(Integer.toUnsignedString((int) aux.getBase()));
				Altura.setText(Integer.toUnsignedString((int) aux.getAltura()));
				repaint();
			}

			if (auxiliar.getDados().get(indice) instanceof PoligonoLivre) {
				
				janelasFalsas();
				Vertices.addItem("Ponto A");
			}
		} catch (Exception e) {
			
		}
		
		
	}
	public int a(){	
		return INDICEREAL-2;
	}
	public void janelasFalsas(){ 
		Vertices.removeAllItems();
		Vertices.addItem("VERTÍCES");
		Base01.setEditable(false);
		Altura.setEditable(false);
		Raio01.setEditable(false);
		Raio02.setEditable(false);
		Base01.setText("Base");
		Altura.setText("Altura");
		Raio01.setText("Raio");
		Raio02.setText("Raio");
	}

	public  ArrayList<Geometrica> getNovo() {
		return dados;

	}
		
	public void NovosDados(){
			
			try {
				principal.getFigura().clear();
				principal.getDados().clear();
				principal.addFundo();
				for (int i = 0; i < auxiliar.getDados().size(); i++) {

					if (auxiliar.getDados().get(i) instanceof Circulo2D) {
						Circulo2D aux = Clonador.Desclonagem( (Circulo2D) auxiliar.getDados().get(i)) ;
						principal.getDados().add( aux);
						principal.getFigura().add( aux);
						repaint();

					}

					if (auxiliar.getDados().get(i) instanceof Quadrado2D) {
						Quadrado2D aux = Clonador.Desclonagem( (Quadrado2D) auxiliar.getDados().get(i)) ;
						principal.getDados().add( aux);
						principal.getFigura().add( aux);
						repaint();

					}
					if (auxiliar.getDados().get(i) instanceof Triangulo2D) {
						Triangulo2D aux = Clonador.Desclonagem( (Triangulo2D) auxiliar.getDados().get(i)) ;
						principal.getDados().add( aux);
						principal.getFigura().add( aux);
						repaint();


					}

					if (auxiliar.getDados().get(i) instanceof PoligonoLivre) {
						PoligonoLivre aux =  Clonador.Desclonagem((PoligonoLivre) auxiliar.getDados().get(i)) ;

						principal.getDados().add( aux);
						principal.getFigura().add( aux);
						repaint();

					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	
	public void AtualizarCOR(int CORSelect) {
		try {
		switch (CORSelect) {
		case 1:
			auxiliar.getDados().get(a()).setCor(Color.white);
			dados.get(a()).setCor(Color.WHITE);
			AtualizarOk();
			break;
		case 2:
			auxiliar.getDados().get(a()).setCor(Color.LIGHT_GRAY);
			dados.get(a()).setCor(Color.LIGHT_GRAY);

			AtualizarOk();
			break;
		case 3:
			auxiliar.getDados().get(a()).setCor(Color.GRAY);
			dados.get(a()).setCor(Color.GRAY);

			AtualizarOk();
			break;
		case 4:
			auxiliar.getDados().get(a()).setCor(Color.DARK_GRAY);
			dados.get(a()).setCor(Color.DARK_GRAY);

			AtualizarOk();
			break;
		case 5:
			auxiliar.getDados().get(a()).setCor(Color.PINK);
			dados.get(a()).setCor(Color.PINK);

			AtualizarOk();
			break;
		case 6:
			auxiliar.getDados().get(a()).setCor(Color.RED);
			dados.get(a()).setCor(Color.RED);

			AtualizarOk();
			break;
		case 7:
			auxiliar.getDados().get(a()).setCor(Color.GREEN);
			dados.get(a()).setCor(Color.GREEN);

			AtualizarOk();
			break;
		case 8:
			auxiliar.getDados().get(a()).setCor(new Color(0, 100, 0));
			dados.get(a()).setCor(new Color(0, 100, 0));
			AtualizarOk();
			break;
		case 9:
			auxiliar.getDados().get(a()).setCor(Color.MAGENTA);
			dados.get(a()).setCor(Color.MAGENTA);

			AtualizarOk();
			break;
		
		case 10:
			auxiliar.getDados().get(a()).setCor(Color.cyan);
			dados.get(a()).setCor(Color.cyan);

			AtualizarOk();
			break;
		case 11:
			auxiliar.getDados().get(a()).setCor(Color.BLUE);
			dados.get(a()).setCor(Color.BLUE);

			AtualizarOk();
			break;
		case 12:
			auxiliar.getDados().get(a()).setCor(Color.BLACK);
			dados.get(a()).setCor(Color.BLACK);

			AtualizarOk();
			break;
		default:
			break;
		}
		} catch (Exception e2) {
			// TODO: handle exception
		}	
	}
	
	
	public void Delete(){
		try {
			auxiliar.getDados().remove(a());
			dados.remove(a());
			AtualizarOk();	
			CaixaDeCamadas.setSelectedItem(INDICEREAL);
		} catch (Exception e) {

			CaixaDeCamadas.setSelectedItem(INDICEREAL-1);
		}
	}
	
	public void MoverDados(int x, int y){
		try {
			auxiliar.getDados().get(a()).AtualizarMOUSE(x, y);
			dados.get(a()).AtualizarMOUSE(x,y);
			AtualizarOk();
			CaixaDeCamadas.setSelectedItem(INDICEREAL);
		} catch (Exception e) {
			CaixaDeCamadas.setSelectedItem(INDICEREAL-1);
		}
		
		
	}
	
	
}
