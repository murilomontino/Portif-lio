package Janelas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.ComponentOrientation;

@SuppressWarnings("serial")
public class FormasDeDesenho extends JFrame {

	private JPanel contentPane;
	private boolean triangulo = false;
	private boolean quadrado = false;
	private boolean poligono = false;
	private boolean ponto = false;
	private int QuantLado = 1;
	private int raio01 = 0;
	private int raio02 = 0;
	private int alturaQuadrilatero = 90;
	private int baseQuadrilatero = 200;
	private int baseTrian = 0;
	private int alturaTrian = 0;
	private float LargBorda = 0;
	private int Contador = 0;
	private boolean JanelaAberta = true;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormasDeDesenho frame = new FormasDeDesenho();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public FormasDeDesenho() {
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
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
		setTitle("Formas");
		setType(Type.POPUP);
		setResizable(false);
		setBounds(100, 100, 200, 480);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		///////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////
		// Já instaciarei antes pra quando usar dentro dos botões não dar erro

		JButton btnQuadrilatero = new JButton("QUADRILATERO");
		btnQuadrilatero.setToolTipText("Esta opção libera duas novas escolhas");
		btnQuadrilatero.setHorizontalAlignment(SwingConstants.LEFT);
		btnQuadrilatero.setFont(new Font("Segoe UI Historic", Font.BOLD, 9));
		JButton btnCircuferncia = new JButton("CIRCULOS");
		btnCircuferncia.setToolTipText("Essa opção libera a edição das caixas de valores a seguir, basta escolher o tamanho dos dois raios.  Lembre-se de mudar cor na paleta");
		btnCircuferncia.setFont(new Font("Segoe UI Historic", Font.BOLD, 9));
		JButton btnTringulo = new JButton("TRIÂNGULO");
		btnTringulo.setToolTipText("Esta opção libera duas novas escolhas");
		btnTringulo.setFont(new Font("Segoe UI Historic", Font.BOLD, 9));
		JButton LivreQuadrilatero = new JButton("LIVRE");
		LivreQuadrilatero.setToolTipText("Ao clicar nessa opção tu poderá desenhar quadrilateros livremente na tela, bastando clicar em 4 posições desejadas\r\n Lembre-se de mudar cor na paleta");
		LivreQuadrilatero.setFont(new Font("Tahoma", Font.PLAIN, 9));
		JButton PadrãoQuadrilatero = new JButton("PADRÃO");
		PadrãoQuadrilatero.setToolTipText("Essa opção libera a edição das caixas de valores abaixo, basta escolher a base e altura. Lembre-se de mudar cor na paleta");
		PadrãoQuadrilatero.setFont(new Font("Arial", Font.BOLD, 9));
		JButton LivreTriângulo = new JButton("LIVRE");
		LivreTriângulo.setToolTipText("Ao clicar nessa opção tu poderá desenhar triângulos livremente na tela, bastando clicar em 3 posições desejadas\r\n Lembre-se de mudar cor na paleta");
		LivreTriângulo.setFont(new Font("Tahoma", Font.PLAIN, 9));
		JButton PadrãoTriângulo = new JButton("PADRÃO");
		PadrãoTriângulo.setToolTipText("Essa opção libera a edição das caixas de valores abaixo, basta escolher a base e altura. Lembre-se de mudar cor na paleta");
		PadrãoTriângulo.setFont(new Font("Arial", Font.BOLD, 9));
		JButton btnPintarTela = new JButton("TELA");
		btnPintarTela.setToolTipText("Pinta toda a tela");
		btnPintarTela.setBackground(SystemColor.textHighlightText);
		TextField Borda = new TextField();
		Borda.setText("1.0");
		TextField AlturaQuadri = new TextField("h px");
		TextField BaseQuadr = new TextField("b px");
		TextField AlturaTriang = new TextField("h px");

		TextField BaseTriang = new TextField("b px");

		TextField Raio1 = new TextField("Raio\r\n");
		TextField Raio2 = new TextField("Raio");

		/////////////////////////////// OS EVENTOS AO SEREM CLICADOS///////////
		/// ELES VIERAM PARAR AQUI PQ FIZ A PARADA ESCRITA ACIMA PRIMEIRO E
		/////////////////////////////// DEPOIS CRIEI OS EVENTOS, não esperava
		/////////////////////////////// isso
		/////////////////////////////////////////////////////////////////////

		btnCircuferncia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LivreQuadrilatero.setEnabled(false);
				PadrãoQuadrilatero.setEnabled(false);
				LivreTriângulo.setEnabled(false);
				PadrãoTriângulo.setEnabled(false);
				AlturaQuadri.setEditable(false);
				AlturaTriang.setEditable(false);
				BaseQuadr.setEditable(false);
				BaseTriang.setEditable(false);
				Raio1.setEditable(true);
				Raio2.setEditable(true);

				Contador = 1;

			}
		});

		btnTringulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LivreQuadrilatero.setEnabled(false);
				PadrãoQuadrilatero.setEnabled(false);
				LivreTriângulo.setEnabled(true);
				PadrãoTriângulo.setEnabled(true);
				AlturaQuadri.setEditable(false);
				AlturaTriang.setEditable(false);
				BaseQuadr.setEditable(false);
				BaseTriang.setEditable(false);
				Raio1.setEditable(false);
				Raio2.setEditable(false);
				Contador = 0;
				poligono = false;

			}
		});

		LivreQuadrilatero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlturaQuadri.setEditable(false);
				BaseQuadr.setEditable(false);
				Contador = 4;
				poligono = true;
				quadrado = true;
				
			}
		});
		PadrãoQuadrilatero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlturaQuadri.setEditable(true);
				BaseQuadr.setEditable(true);
				Contador = 2;
				poligono = false;

			}
		});
		LivreTriângulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlturaTriang.setEditable(false);
				BaseTriang.setEditable(false);
				Contador = 5;
				poligono = true;
				triangulo = true;
			}
		});
		PadrãoTriângulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlturaTriang.setEditable(true);
				BaseTriang.setEditable(true);
				Contador = 3;
				poligono = false;

			}
		});

		btnQuadrilatero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LivreQuadrilatero.setEnabled(true);
				PadrãoQuadrilatero.setEnabled(true);
				LivreTriângulo.setEnabled(false);
				PadrãoTriângulo.setEnabled(false);

				AlturaTriang.setEditable(false);
				AlturaQuadri.setEditable(false);
				BaseQuadr.setEditable(false);
				BaseTriang.setEditable(false);
				Raio1.setEditable(false);
				Raio2.setEditable(false);
				Contador = 0; // CASO A PESSOA SEJA DESCUIDADA E CLICK NA TELA E
								// DEPOIS ACHE QUE ESCOLHEU A FIGURA, tipo só clicar no quadrilatero não vai fazer ainda, então zera o contador, e não pinta a tela

			}
		});
		btnQuadrilatero.setBackground(SystemColor.textHighlightText);
		btnQuadrilatero.setBounds(50, 11, 110, 25);
		contentPane.add(btnQuadrilatero);

		btnCircuferncia.setBackground(SystemColor.textHighlightText);
		btnCircuferncia.setBounds(10, 100, 87, 23);
		contentPane.add(btnCircuferncia);

		btnTringulo.setBackground(SystemColor.textHighlightText);
		btnTringulo.setBounds(53, 145, 100, 25);
		contentPane.add(btnTringulo);

		LivreQuadrilatero.setBackground(SystemColor.textHighlightText);
		LivreQuadrilatero.setEnabled(false);
		LivreQuadrilatero.setBounds(120, 37, 65, 20);
		contentPane.add(LivreQuadrilatero);

		PadrãoQuadrilatero.setBackground(SystemColor.textHighlightText);
		PadrãoQuadrilatero.setEnabled(false);
		PadrãoQuadrilatero.setBounds(21, 38, 75, 20);
		contentPane.add(PadrãoQuadrilatero);

		LivreTriângulo.setBackground(SystemColor.textHighlightText);
		LivreTriângulo.setEnabled(false);
		LivreTriângulo.setBounds(105, 180, 65, 20);
		contentPane.add(LivreTriângulo);

		PadrãoTriângulo.setBackground(SystemColor.textHighlightText);
		PadrãoTriângulo.setEnabled(false);
		PadrãoTriângulo.setBounds(20, 180, 75, 20);
		contentPane.add(PadrãoTriângulo);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////// EVENTOS de CLICAR COM O MOUSE OU TECLA
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ENTER////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		AlturaQuadri.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// RELEASE PQ ACHO QUE A CHANCE DE BUGAR É MENOR,uma hora o
				// usuário vai soltar o botão
				if (Contador == 2) {// PARA SÓ ATIVAR QUANDO REALMENTE FOR
									// ESCOLHIDO O QUADRADO

					AlturaQuadri.setText("");
				}
			}
		});
		AlturaQuadri.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (Contador == 2) {// PARA SÓ ATIVAR QUANDO REALMENTE FOR
									// ESCOLHIDO O QUADRADO
					try {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							String x = AlturaQuadri.getText().replaceAll(" ", "");
							alturaQuadrilatero = Integer.parseInt(x);
						}

					} catch (Exception e2) {
						AlturaQuadri.setText("");
					}

				}

			}
		});
		AlturaQuadri.setEditable(false);
		AlturaQuadri.setBounds(60, 60, 35, 25);
		contentPane.add(AlturaQuadri);

		BaseQuadr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (Contador == 2) {

					BaseQuadr.setText("");
				}
			}
		});
		BaseQuadr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (Contador == 2) {
					try {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							String x = BaseQuadr.getText().replaceAll(" ", "");
							baseQuadrilatero = Integer.parseInt(x);

						}

					} catch (Exception e2) {
						BaseQuadr.setText("");
					}
				}
			}
		});
		BaseQuadr.setEditable(false);
		BaseQuadr.setBounds(20, 60, 35, 25);
		contentPane.add(BaseQuadr);
		AlturaTriang.setEditable(false);
		AlturaTriang.setBounds(61, 205, 35, 25);
		contentPane.add(AlturaTriang);
		BaseTriang.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						String x = BaseTriang.getText().replaceAll(" ", "");
						baseTrian = Integer.parseInt(x);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "VALOR INVALIDO,TENTE APENAS NÚMEROS!");
					BaseTriang.setText("");
				}
			}
		});
		BaseTriang.setEditable(false);
		BaseTriang.setBounds(21, 205, 35, 25);
		contentPane.add(BaseTriang);

		Raio1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (Contador == 1) {
					Raio1.setText("");
				}
			}
		});
		Raio1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (Contador == 1) {// PARA SÓ ATIVAR QUANDO REALMENTE FOR
									// ESCOLHIDO O CIRCULO
					try {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							String x = Raio1.getText().replaceAll(" ", "");
							raio01 = Integer.parseInt(x);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "VALOR INVALIDO,TENTE APENAS NÚMEROS!");
						Raio1.setText("");
					}
				}
			}

		});
		Raio1.setEditable(false);
		Raio1.setBounds(147, 101, 36, 22);
		contentPane.add(Raio1);

		Raio2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (Contador == 1) {
					Raio2.setText("");
				}
			}
		});
		Raio2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (Contador == 1) {// PARA SÓ ATIVAR QUANDO REALMENTE FOR
									// ESCOLHIDO O CIRCULO

					try {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							String x = Raio2.getText().replaceAll(" ", "");
							raio02 = Integer.parseInt(x);
						}
						if (e.getKeyCode() == KeyEvent.VK_TAB) {
							String x = AlturaQuadri.getText().replaceAll(" ", "");
							raio02 = Integer.parseInt(x);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "VALOR INVALIDO,TENTE APENAS NÚMEROS!");
					}
				}
			}
		});
		Raio2.setEditable(false);
		Raio2.setBounds(103, 101, 36, 22);
		contentPane.add(Raio2);

		btnPintarTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LivreQuadrilatero.setEnabled(false);
				PadrãoQuadrilatero.setEnabled(false);
				LivreTriângulo.setEnabled(false);
				PadrãoTriângulo.setEnabled(false);
				AlturaQuadri.setEditable(false);
				AlturaTriang.setEditable(false);
				BaseQuadr.setEditable(false);
				BaseTriang.setEditable(false);
				Raio1.setEditable(false);
				Raio2.setEditable(false);
				Contador = 100;
			}
		});
		btnPintarTela.setFont(new Font("Arial", Font.BOLD, 10));
		btnPintarTela.setBounds(10, 368, 65, 20);
		contentPane.add(btnPintarTela);

		JTextPane txtpnBorda = new JTextPane();
		txtpnBorda.setToolTipText("Grossura da borda");
		txtpnBorda.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnBorda.setBackground(SystemColor.control);
		txtpnBorda.setText("Borda");
		txtpnBorda.setEditable(false);
		txtpnBorda.setBounds(105, 250, 40, 25);
		contentPane.add(txtpnBorda);

		//////////////////// TEXTFIELD
		///////////////////// BORDA/////////////////////////////
		Borda.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				try {
					Borda.setText("");

				} catch (Exception e2) {
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				try {
					String x = Borda.getText().replaceAll(" ", "");
					LargBorda = Float.parseFloat(x);

				} catch (Exception e2) {
					Borda.setText("1.0");

				}
			}
		});
		Borda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						String x = Borda.getText().replaceAll(" ", "");
						LargBorda = Float.parseFloat(x);
					}

				} catch (Exception e2) {
					Borda.setText("1.0");
				}
			}
		});
		Borda.setBounds(147, 250, 35, 25);
		contentPane.add(Borda);
		
		JButton btnPoligono = new JButton("POLIGONO");
		btnPoligono.setToolTipText("Ao clicar nessa opção tu poderá desenhar poligonos livremente na tela, bastando clicar nas posições desejadas, mas para desenhar deverá clicar com o botão direito do mouse.\r\n ");
		btnPoligono.setBackground(SystemColor.textHighlightText);
		btnPoligono.setFont(new Font("Arial", Font.BOLD, 9));
		btnPoligono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LivreQuadrilatero.setEnabled(false);
				PadrãoQuadrilatero.setEnabled(false);
				LivreTriângulo.setEnabled(false);
				PadrãoTriângulo.setEnabled(false);
				AlturaQuadri.setEditable(false);
				AlturaTriang.setEditable(false);
				BaseQuadr.setEditable(false);
				BaseTriang.setEditable(false);
				Raio1.setEditable(false);
				Raio2.setEditable(false);
				Contador = 1000;
				
			}
		});
		btnPoligono.setBounds(10, 338, 90, 20);
		contentPane.add(btnPoligono);
		
		JButton TrianguloMágic = new JButton("MÁGICO");
		TrianguloMágic.setBackground(SystemColor.textHighlightText);
		TrianguloMágic.setFont(new Font("Arial", Font.BOLD, 10));
		TrianguloMágic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LivreQuadrilatero.setEnabled(false);
				PadrãoQuadrilatero.setEnabled(false);
				LivreTriângulo.setEnabled(false);
				PadrãoTriângulo.setEnabled(false);
				AlturaQuadri.setEditable(false);
				AlturaTriang.setEditable(false);
				BaseQuadr.setEditable(false);
				BaseTriang.setEditable(false);
				Raio1.setEditable(false);
				Raio2.setEditable(false);
				Contador = 20;
				poligono = true;
				ponto=true;
				try {
				QuantLado = Integer.parseInt(JOptionPane.showInputDialog(null, "Se Quiser:\n 01 - Reta Digite 2(A BORDA DEVE SER ATIVADA);\n 02 - Triangulos Consecutivos Digite 3;\n 03 - Quadrilateros Consecutivos Digite 4\nApós isso, vc deverá clicar na tela pela quantidade de vezes escolhida na primeira vez!\n para parar a consecutividade de impressões, click com o botão direito do mouse, sobre a tela")); 
						
				} catch (Exception e2) {
				QuantLado = 1;	// TODO: handle exception
				}
				
			}
		});
		TrianguloMágic.setBounds(10, 307, 80, 20);
		contentPane.add(TrianguloMágic);

		////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////// QUANDO A CAIXA PERDE O FOCO ou////
		/////////////////////////////////////// GANHA//////////////////////
		////////////////////////////////////////////////////////////////////////////////////////

		///////////////////////////////////////////////////////////////////////////////////
		// PERCA E GANHO DE FOCO DO CAIXA DE TEXTO DO QUADRADO
		// ALTURA QUADRADO
		///////////////////////////////////////////////////////////////////////////////////

		AlturaQuadri.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				try {
					if (Contador == 2) {
						AlturaQuadri.setText("");
					}
				} catch (Exception e2) {

				}
			}
		});

		AlturaQuadri.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (Contador == 2) {

					try {
						String x = AlturaQuadri.getText().replaceAll(" ", "");
						alturaQuadrilatero = Integer.parseInt(x);
					} catch (Exception e2) {
						alturaQuadrilatero = 0;
						AlturaQuadri.setText("0"); // CASO O USUARIO DIGITE
													// ALGUM VALOR INVALIDO, Já
													// MOSTRAR

					}
				}
			}
		});
		///////////////////////////////////////////////////////////////////////////////////
		// BASE QUADRADO
		///////////////////////////////////////////////////////////////////////////////////

		BaseQuadr.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				try {
					if (Contador == 2) {
						BaseQuadr.setText("");
					}
				} catch (Exception e2) {

				}
			}
		});

		AlturaQuadri.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (Contador == 2) {

					try {
						String x = BaseQuadr.getText().replaceAll(" ", "");
						baseQuadrilatero = Integer.parseInt(x);
					} catch (Exception e2) {
						baseQuadrilatero = 0;
						BaseQuadr.setText("0"); // CASO O USUARIO DIGITE ALGUM
												// VALOR INVALIDO, Já MOSTRAR

					}
				}
			}
		});

		///////////////////////////////////////////////////////////////////////////////////
		/////////////////// PERCA E GANHO DE FOCO DAS CAIXAS DE TEXTOS DO
		/////////////////////////////////////////////////////////////////////////////////// CIRCULO///////
		////////////////////////////////////////////////////////////////////////////////

		///////////////////////////////////////////////////////////////////////////////////
		/////////// RAIO 01
		/////////////////////////////////////////////////////////////////////////////////// ///////////////////////////////////////////////////////////

		Raio1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				try {
					if (Contador == 1) {
						Raio1.setText("");
					}
				} catch (Exception e2) {

				}
			}
		});

		Raio1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					if (Contador == 1) {
						String x = Raio1.getText().replaceAll(" ", "");
						raio01 = Integer.parseInt(x);
					}
				} catch (Exception e2) {
					raio01 = 0;
					Raio1.setText("0");
					// CASO O USUARIO DIGITE ALGUM VALOR INVALIDO, Já MOSTRAR

				}

			}
		});

		///////////////////////////////////////////////////////////////////////////////////
		///////// RAIO 2
		/////////////////////////////////////////////////////////////////////////////////// 02/////////////////////////////////////////////////////////////////////////

		Raio2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					if (Contador == 1) {
						String x = Raio2.getText().replaceAll(" ", "");
						raio02 = Integer.parseInt(x);
					}
				} catch (Exception e2) {
					raio02 = 0;
					Raio2.setText("0");
					// CASO O USUARIO DIGITE ALGUM VALOR INVALIDO, Já MOSTRAR
				}
			}
		});

		Raio2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				try {
					if (Contador == 1) {
						Raio2.setText("");
					}
				} catch (Exception e2) {

				}
			}
		});

		///////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////
		/////////////////////////// FOCO E GANHO DO TRIANGULO
		/////////////////////////////////////////////////////////////////////////////////// TRIANGULO///////////////////////////////

		///////////////////////////////////////////////////////////////////////////////////
		// BASE DO TRIANGULO

		BaseTriang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				try {
					if (Contador == 3) {
						BaseTriang.setText("");
					}
				} catch (Exception e2) {

				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				try {
					if (Contador == 3) {
						String x = BaseTriang.getText().replaceAll(" ", "");
						baseTrian = Integer.parseInt(x);
					}
				} catch (Exception e2) {
					baseTrian = 0;
					BaseTriang.setText("0");
					// CASO O USUARIO DIGITE ALGUM VALOR INVALIDO, Já MOSTRAR
				}

			}
		});
		///////////////////////////////////////////////////////////////////////////////////
		// ALTURA DO TRIANGULO

		AlturaTriang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				try {
					if (Contador == 3) {
						AlturaTriang.setText("");
					}
				} catch (Exception e2) {
				}

			}

			@Override
			public void focusLost(FocusEvent e) {
				try {
					if (Contador == 3) {
						String x = AlturaTriang.getText().replaceAll(" ", "");
						alturaTrian = Integer.parseInt(x);
					}
				} catch (Exception e2) {
					alturaTrian = 0;
					AlturaTriang.setText("0");
					// CASO O USUARIO DIGITE ALGUM VALOR INVALIDO, Já MOSTRAR
				}
			}
		});

		AlturaTriang.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// PARA SÓ ATIVAR QUANDO REALMENTE FOR
				// ESCOLHIDO O TRIANGULO
				if (Contador == 3) {
					try {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							String x = AlturaTriang.getText().replaceAll(" ", "");
							alturaTrian = Integer.parseInt(x);
						}
					} catch (Exception e2) {
						if (Contador == 3) {
							AlturaTriang.setText("");
						}
					}
				}
			}
		});

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////// METODOS ACESSORES E MODIFICADORES
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// MODIFICADORES//////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int getBaseQuadri() {
		return baseQuadrilatero;
	}

	public void setBaseQuadri(int baseQuadri) {
		this.baseQuadrilatero = baseQuadri;
	}

	public int getAlturaQuadri() {
		return alturaQuadrilatero;
	}

	public void setAlturaQuadri(int alturaQuadri) {
		this.alturaQuadrilatero = alturaQuadri;
	}

	public int getBaseTrian() {
		return baseTrian;
	}

	public void setBaseTrian(int baseTrian) {
		this.baseTrian = baseTrian;
	}

	public int getAlturaTrian() {
		return alturaTrian;
	}

	public void setAlturaTrian(int alturaTrian) {
		this.alturaTrian = alturaTrian;
	}

	public int getContador() {
		return Contador;
	}

	public void setContador(int contador) {
		Contador = contador;
	}

	int getRaio01() {
		return raio01;
	}

	public void setRaio01(int raio01) {
		this.raio01 = raio01;
	}

	public int getRaio02() {
		return raio02;
	}

	public void setRaio02(int raio02) {
		this.raio02 = raio02;
	}

	public float getBorda() {
		return LargBorda;
	}

	public boolean getPoligono() {
		return poligono;
	}

	public void setPoligono(boolean poligono) {
		this.poligono = poligono;
	}

	public boolean isTriangulo() {
		return triangulo;
	}

	public void setTriangulo(boolean triangulo) {
		this.triangulo = triangulo;
	}

	public boolean isQuadrado() {
		return quadrado;
	}

	public void setQuadrado(boolean quadrado) {
		this.quadrado = quadrado;
	}

	public boolean isPonto() {
		return ponto;
	}

	public void setPonto(boolean ponto) {
		this.ponto = ponto;
	}
	public int getQuantLado(){
		return QuantLado;
	}

	public boolean isJanelaAberta() {
		return JanelaAberta;
	}

	public void setJanelaAberta(boolean janelaAberta) {
		JanelaAberta = janelaAberta;
	}
}
