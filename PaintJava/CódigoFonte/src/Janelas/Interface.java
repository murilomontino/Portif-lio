package Janelas;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

import Auxiliares.Editor;
import FormasGeometricas.Circulo2D;
import FormasGeometricas.PoligonoLivre;
import FormasGeometricas.Quadrado2D;
import FormasGeometricas.Triangulo2D;
import java.awt.ComponentOrientation;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Interface extends JFrame {

	private int Controlador = 0;
	private int CoordX;
	private int CoordY;
	private int QuantLado;

	private boolean poligonoLado = false;

	private static Editor desenho = new Editor();
	private static Paleta paleta = new Paleta();
	private static Camadas Edicao = new Camadas();
	private ArrayList<Point> verticesPoligono = new ArrayList<Point>();
	private static FormasDeDesenho geometria = new FormasDeDesenho();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
					frame.pack();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Interface() {
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		paleta.setVisible(true);
		geometria.setVisible(true);
		
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				Edicao.setPrincipal(desenho);

			}
			@Override
			public void focusGained(FocusEvent arg0) {
				repaint();
					}
		});

		getContentPane().setBackground(SystemColor.control);

		setResizable(false);
		geometria.setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\muril\\Desktop\\Paint.png"));
		setBackground(Color.WHITE);
		setTitle("PAINT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////

		// AREA DO MENU
		// ELE USA DA CLASSE EDITOR, PARA SALVAR, ABRIR E ETC;
		// AO SALVAR NA PRIMEIRA VEZ, ELE PERGUNTA O DIRETÓRIO DEPOIS NÂO È MAIS
		// NECESSÁRIO

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.menu);
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setBackground(Color.GRAY);
		menuBar.add(mnMenu);

		JMenuItem mntmNovo = new JMenuItem("Novo");
		mntmNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (JOptionPane.showConfirmDialog(null, "Deseja Salvar?")) {
				case 0:
					if (desenho.getDiretorio() != null) {
						desenho.salvarFiguras(desenho.getDiretorio(), desenho);
						JOptionPane.showMessageDialog(null, "SALVO COM SUCESSO");
						limparTela();
						Edicao.setVisible(false);
						Edicao = new Camadas();
						verticesPoligono = new ArrayList<Point>();
						JOptionPane.showMessageDialog(null, "Novo arquivo criado!");
						break;
					} else {
						salvarComo();
						if (desenho.getDiretorio() != null) {
							limparTela();
							Edicao.setVisible(false);
							Edicao = new Camadas();
							verticesPoligono = new ArrayList<Point>();
							JOptionPane.showMessageDialog(null, "Novo arquivo criado!");

						}
					}

					break;
				case 1:
					limparTela();
					JOptionPane.showMessageDialog(null, "Novo arquivo criado!");
					Edicao.setVisible(false);
					Edicao = new Camadas();
					verticesPoligono = new ArrayList<Point>();
					break;
				case 2:

					break;
				default:
					break;
				}

			}
		});
		mnMenu.add(mntmNovo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					abrirArquivo();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro Inesperado");

				}

			}
		});
		mnMenu.add(mntmAbrir);

		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (desenho.getDiretorio() != null) {
						desenho.salvarFiguras(desenho.getDiretorio(), desenho);
						JOptionPane.showMessageDialog(null, "SALVO COM SUCESSO");

					} else {
						salvarComo();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "SEM SUCESSO");
				}
			}

		});
		mnMenu.add(mntmSalvar);

		JMenuItem mntmSalvarComo = new JMenuItem("Salvar Como");
		mntmSalvarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					salvarComo();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "SEM SUCESSO");
				}

			}
		});
		mnMenu.add(mntmSalvarComo);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnMenu.add(mntmSair);

		///////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////

		// MENU VIEW
		// FICA A PALETA DE CORES
		// FORMAS GEOMETRICAS
		// E O EDITOR DE CAMADAS
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenuItem mntmPaleta = new JMenuItem("Paleta");
		mntmPaleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paleta.isJanelaAberta() == false) {
					paleta.setJanelaAberta(true);
					paleta.setVisible(true);
				} else {
					paleta.setJanelaAberta(false);
					paleta.setVisible(false);
				}
			}
		});

		mnView.add(mntmPaleta);

		JMenuItem mntmFormasGeometricas = new JMenuItem("Formas Geometricas");
		mntmFormasGeometricas.setToolTipText(
				"Caso não saiba o que fazer, deixe o mouse parado na forma desejada que receberá dicas de como desenhar");
		mntmFormasGeometricas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (geometria.isJanelaAberta() == false) {
					geometria.setVisible(true);
					geometria.setJanelaAberta(true);
				} else {
					geometria.setVisible(false);
					geometria.setJanelaAberta(false);
				}
			}
		});
		mnView.add(mntmFormasGeometricas);

		JMenuItem mntmEditarCamada = new JMenuItem("Camada");
		mntmEditarCamada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Edicao.isJanelaAberta() == false) {
					Edicao.setVisible(true);
					Edicao.setJanelaAberta(true);
					Edicao.setPrincipal(desenho);
					Edicao.setDados(desenho.getDados());
					Edicao.ATUALIZAR();
				} else {
					Edicao.setVisible(false);
					Edicao.setJanelaAberta(false);
					
				}

			}
		});
		mnView.add(mntmEditarCamada);

		JButton btnNewButton = new JButton("Ajuda");
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Para desenhar vá em View e abra formas de desenho\nPara colorir vá em View e abra Paleta\nPara Editar seu desenho vá em View e clique Editar Camadas\nIMPORTANTE: Você receberá dicas do que fazer caso pare o mouse na opção desejada!\n");

			}
		});
		btnNewButton.setForeground(SystemColor.textText);
		btnNewButton.setBackground(SystemColor.menu);
		menuBar.add(btnNewButton);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 639, 0 };
		gridBagLayout.rowHeights = new int[] { 412, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////

		// PAINEL DE DESENHO

		GridBagConstraints gbc_desenho = new GridBagConstraints();
		gbc_desenho.gridx = 0;
		desenho.setBounds(new Rectangle(0, 0, 800, 600));
		getContentPane().add(desenho, gbc_desenho);
		desenho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Controlador = geometria.getContador();
				CoordX = e.getX();
				CoordY = e.getY();
				System.out.println(CoordX);
				System.out.println(CoordY);
				switch (Controlador) {

				case 1:
					try {
						Circulo2D circulo = new Circulo2D(CoordX, CoordY, geometria.getRaio01(), geometria.getRaio02(),
								paleta.getColor(), paleta.getCorBorda(), geometria.getBorda());
						desenho.addDados(circulo);
						desenho.addFigura(circulo);
						desenho.setContadorDeFiguras(1);
						repaint();
						poligonoLado = false;
						verticesPoligono.clear();

					} catch (Exception e2) {
					}

					break;

				case 2:
					try {
						Quadrado2D quadrado = new Quadrado2D(paleta.getColor(), CoordX, CoordY,
								geometria.getAlturaQuadri(), geometria.getBaseQuadri(), paleta.getCorBorda(),
								geometria.getBorda());
						desenho.addDados(quadrado);
						desenho.addFigura(quadrado);
						desenho.setContadorDeFiguras(1);
						repaint();
						poligonoLado = false;
						verticesPoligono.clear();

					} catch (Exception e2) {

					}

					break;

				case 3:
					try {
						Triangulo2D triangulo = new Triangulo2D(paleta.getColor(), CoordX, CoordY,
								geometria.getBaseTrian(), geometria.getAlturaTrian(), paleta.getCorBorda(),
								geometria.getBorda());
						desenho.addDados(triangulo);
						desenho.addFigura(triangulo);
						desenho.setContadorDeFiguras(1);
						repaint();
						poligonoLado = false;
						verticesPoligono.clear();
					} catch (Exception e2) {

					}

					break;

				case 4:
					try {
						if (geometria.isTriangulo() == true || (geometria.isPonto() == true)) {
							verticesPoligono.clear();
							geometria.setTriangulo(false);
							geometria.setPonto(false);
							;

						}
						if (poligonoLado == false) {
							poligonoLado = true;
							verticesPoligono.clear();
						}
						verticesPoligono.add(new Point(CoordX, CoordY));
						System.out.println("Chega aqui?");
						if (poligonoLado == true) {
							if (verticesPoligono.size() == 4) {
								PoligonoLivre poligono4 = new PoligonoLivre(paleta.getColor(), paleta.getCorBorda(),
										geometria.getBorda());

								for (int i = 0; i < verticesPoligono.size(); i++) {
									poligono4.setPoint(verticesPoligono.get(i));
								}

								desenho.addDados(poligono4);
								desenho.addFigura(poligono4);
								desenho.setContadorDeFiguras(1);
								repaint();
								verticesPoligono.clear();
							}

						}

					} catch (Exception e2) {

					}

					break;

				case 5:
					try {
						if ((geometria.isQuadrado() == true) || (geometria.isPonto() == true)) {
							verticesPoligono.clear();
							geometria.setQuadrado(false);
							geometria.setPonto(false);

						}
						if (poligonoLado == false) {
							poligonoLado = true;
							verticesPoligono.clear();

						}
						verticesPoligono.add(new Point(CoordX, CoordY));

						if (poligonoLado == true) {
							System.out.println("Dentro do IF");

							if (verticesPoligono.size() == 3) {
								PoligonoLivre poligono3 = new PoligonoLivre(paleta.getColor(), paleta.getCorBorda(),
										geometria.getBorda());
								for (int i = 0; i < verticesPoligono.size(); i++) {
									poligono3.setPoint(verticesPoligono.get(i));
								}
								desenho.addDados(poligono3);
								desenho.addFigura(poligono3);
								desenho.setContadorDeFiguras(1);
								repaint();
								verticesPoligono.clear();
							}

						}
					} catch (Exception e2) {

					}

					break;

				case 20:// FIGURA MÁGICÁ
					QuantLado = geometria.getQuantLado();
					try {
						if ((geometria.isQuadrado() == true) || (geometria.isTriangulo() == true)) {
							verticesPoligono.clear();
							geometria.setQuadrado(false);
							geometria.setTriangulo(false);

						}
						if (poligonoLado == false) {
							poligonoLado = true;
							verticesPoligono.clear();

						}
						verticesPoligono.add(new Point(CoordX, CoordY));

						if (poligonoLado == true) {
							System.out.println("Dentro do IF");

							if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0)) {
								verticesPoligono.clear();

							}

							PoligonoLivre poligono2 = new PoligonoLivre(paleta.getColor(), paleta.getCorBorda(),
									geometria.getBorda());
							for (int i = (verticesPoligono.size() - QuantLado); i < verticesPoligono.size(); i++) {
								poligono2.setPoint(verticesPoligono.get(i));
							}
							desenho.addDados(poligono2);
							desenho.addFigura(poligono2);
							desenho.setContadorDeFiguras(1);
							repaint();

						}
					} catch (Exception e2) {

					}

					break;

				case 1000:
					try {
						if (poligonoLado == false) {
							poligonoLado = true;
							verticesPoligono.clear();

						}
						verticesPoligono.add(new Point(CoordX, CoordY));

						if (poligonoLado == true) {
							if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0)) {
								PoligonoLivre poligono3 = new PoligonoLivre(paleta.getColor(), paleta.getCorBorda(),
										geometria.getBorda());
								for (int i = 0; i < verticesPoligono.size(); i++) {
									poligono3.setPoint(verticesPoligono.get(i));
								}
								desenho.addDados(poligono3);
								desenho.addFigura(poligono3);
								desenho.setContadorDeFiguras(1);
								repaint();
								verticesPoligono.clear();
							}

						}
					} catch (Exception e2) {
						verticesPoligono.clear();
					}

					break;

				case 100:
					try {
						Quadrado2D tela = new Quadrado2D(paleta.getColor(), 300, 400, 800, 600, null, 0);
						desenho.addDados(tela);
						desenho.addFigura(tela);
						desenho.setContadorDeFiguras(1);
						repaint();
						poligonoLado = false;
						verticesPoligono.clear();
					} catch (Exception e2) {

					}

					break;

				default:
					break;
				}

			}

		});

		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
	}

	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int getFigura2d() {
		return Controlador;
	}

	public void setFigura2d(int figura2d) {
		this.Controlador = figura2d;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// METODOS CHAMADOS AO CLICAR OS BOTÔES de SALVAR E ABRIR, JOGUEI AQUI EM
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// BAIXO
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// PRA
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// NÂO
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// POLUIR
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// LÁ
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// EM
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// CIMA////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void limparTela() {
		desenho.clear();
		desenho.repaint();
		desenho.limparContadorDeFiguras();
		desenho.addFundo();
	}

	public void salvarComo() {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filtroFIG = new FileNameExtensionFilter("Arquivos de FIGURAS", "fig");
		fc.addChoosableFileFilter(filtroFIG);
		fc.setAcceptAllFileFilterUsed(false);

		int option = fc.showSaveDialog(null);

		if (option == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			file.mkdir();
			file.getAbsolutePath();
			desenho.salvarFiguras(file.getAbsolutePath() + "\\", desenho);

		}
	}

	public void abrirArquivo() {
		try {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setAcceptAllFileFilterUsed(false);

			int res = fc.showOpenDialog(null);
			if (res == JFileChooser.APPROVE_OPTION) {
				File arq = fc.getSelectedFile();
				System.out.println(arq.getAbsolutePath());
				desenho.carregarFiguras(arq.getAbsolutePath() + "\\");

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO INESPERADO!");
		}

	}
	
	
}
