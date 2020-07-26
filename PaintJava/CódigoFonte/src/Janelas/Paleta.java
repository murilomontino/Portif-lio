package Janelas;

import java.awt.Color;
import java.awt.EventQueue;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Dialog.ModalExclusionType;
import java.awt.ComponentOrientation;

public class Paleta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Color cor = Color.black;
	private Color corBorda = null;
	private String x = "255";
	private String y = "255";
	private String z = "255";
	private int ContBorda = 0;
	private boolean JanelaAberta = true;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Paleta frame = new Paleta();
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
	public Paleta() {
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
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
		setType(Type.POPUP);
		setTitle("Paleta");
		setResizable(false);
		setBounds(100, 100, 200, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panel.setBounds(0, 0, 194, 371);
		contentPane.add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JPanel CorAtual = new JPanel();
		CorAtual.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JPanel borda = new JPanel();
		borda.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JPanel branco = new JPanel();
		JPanel cinza = new JPanel();
		JPanel vermelho = new JPanel();
		JPanel cinzaclaro = new JPanel();
		sl_panel.putConstraint(SpringLayout.EAST, CorAtual, -68, SpringLayout.EAST, cinzaclaro);
		JPanel roxo = new JPanel();
		JPanel amarelo = new JPanel();
		JPanel rosa = new JPanel();
		JPanel orange = new JPanel();
		JPanel Azulzinho = new JPanel();
		JPanel Cinzão = new JPanel();
		JPanel Verdezaõ = new JPanel();
		JPanel Preto = new JPanel();
		JPanel verde = new JPanel();
		JPanel azulzão = new JPanel();

		TextField Red = new TextField(x);
		Red.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Red.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				try {
					x = Red.getText().replaceAll(" ", "");
					int r = Integer.parseInt(x);//VERIFICA SE É UM NUMERO VALIDO
					x = Integer.toString(r);
					cor = new Color(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z));
					CorAtual.setBackground(cor);

				} catch (Exception e2) {
					x = Integer.toString(cor.getRed());
					Red.setText(x);

					// CASO O USUARIO DIGITE ALGUM VALOR INVALIDO, Já MOSTRAR

				}

			}
		});

		Red.addKeyListener(new KeyAdapter() {

			@SuppressWarnings("static-access")
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (e.getKeyCode() == e.VK_ENTER) {
						x = Red.getText().replaceAll(" ", "");
						int r = Integer.parseInt(x);
						x = Integer.toString(r);
						cor = new Color(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z));
						CorAtual.setBackground(cor);
					}
				} catch (Exception e2) {
					x = Integer.toString(cor.getRed());
					Red.setText("");
				}

			}
		});
		sl_panel.putConstraint(SpringLayout.WEST, Red, 36, SpringLayout.WEST, panel);
		panel.add(Red);

		TextField Green = new TextField(y);
		Green.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Green.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				try {
					y = Green.getText().replaceAll(" ", "");
					int r = Integer.parseInt(y);
					y = Integer.toString(r);
					cor = new Color(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z));
					CorAtual.setBackground(cor);

				} catch (Exception e2) {
					y = Integer.toString(cor.getGreen());
					Green.setText(y);

					// CASO O USUARIO DIGITE ALGUM VALOR INVALIDO, Já MOSTRAR

				}
			}
		});
		sl_panel.putConstraint(SpringLayout.SOUTH, Green, -25, SpringLayout.NORTH, CorAtual);

		Green.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (e.getKeyCode() == e.VK_ENTER) {
						y = Green.getText().replaceAll(" ", "");
						int r = Integer.parseInt(y);
						y = Integer.toString(r);
						cor = new Color(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z));
						CorAtual.setBackground(cor);
					}
				} catch (Exception e2) {
					y = Integer.toString(cor.getGreen());
					Green.setText("");
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.WEST, Green, 78, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, Red, 0, SpringLayout.SOUTH, Green);
		sl_panel.putConstraint(SpringLayout.EAST, Red, -6, SpringLayout.WEST, Green);
		panel.add(Green);

		TextField Blue = new TextField(z);
		Blue.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Blue.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				try {
					z = Blue.getText().replaceAll(" ", "");
					int r = Integer.parseInt(z);
					z = Integer.toString(r);
					cor = new Color(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z));
					CorAtual.setBackground(cor);

				} catch (Exception e2) {
					z = Integer.toString(cor.getBlue());
					Blue.setText(z);
					// CASO O USUARIO DIGITE ALGUM VALOR INVALIDO, Já MOSTRAR

				}

			}
		});
		sl_panel.putConstraint(SpringLayout.SOUTH, Blue, -138, SpringLayout.SOUTH, panel);
		Blue.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (e.getKeyCode() == e.VK_ENTER) {
						z = Blue.getText().replaceAll(" ", "");
						int r = Integer.parseInt(z);
						z = Integer.toString(r);
						cor = new Color(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z));
						CorAtual.setBackground(cor);
						System.out.println(x + y + z);
					}
				} catch (Exception e2) {
					z = Integer.toString(cor.getBlue());
					Blue.setText("");
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.WEST, Blue, 120, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, Green, -6, SpringLayout.WEST, Blue);
		sl_panel.putConstraint(SpringLayout.EAST, Blue, -38, SpringLayout.EAST, panel);
		panel.add(Blue);

		///////////////////////////// COR BRANCA
		///////////////////////////// ////////////////////////////////////////
		branco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0)) {
					cor = Color.white;
					CorAtual.setBackground(cor);
					Red.setText(x = Integer.toString(cor.getRed()));
					Green.setText(y = Integer.toString(cor.getGreen()));
					Blue.setText(z = Integer.toString(cor.getBlue()));
				}

				if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) && (ContBorda == 1)) {
					corBorda = Color.white;
					borda.setBackground(corBorda);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, branco, 36, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, branco, 32, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, branco, 52, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, branco, 99, SpringLayout.WEST, panel);
		branco.setBackground(Color.WHITE);
		panel.add(branco);

		///////////////////////////// COR CINZA CLARO
		///////////////////////////// ////////////////////////////////////////

		cinzaclaro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0)) {
					cor = Color.LIGHT_GRAY;
					CorAtual.setBackground(cor);
					Red.setText(x = Integer.toString(cor.getRed()));
					Green.setText(y = Integer.toString(cor.getGreen()));
					Blue.setText(z = Integer.toString(cor.getBlue()));
				}

				if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) && (ContBorda == 1)) {
					corBorda = Color.LIGHT_GRAY;
					borda.setBackground(corBorda);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, cinzaclaro, 36, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, cinzaclaro, 1, SpringLayout.EAST, branco);
		sl_panel.putConstraint(SpringLayout.EAST, cinzaclaro, -27, SpringLayout.EAST, panel);
		cinzaclaro.setBackground(Color.LIGHT_GRAY);
		panel.add(cinzaclaro);

		///////////////////////////// COR CINZA
		///////////////////////////// ////////////////////////////////////////

		cinza.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0)) {
					cor = Color.gray;
					CorAtual.setBackground(cor);
					Red.setText(x = Integer.toString(cor.getRed()));
					Green.setText(y = Integer.toString(cor.getGreen()));
					Blue.setText(z = Integer.toString(cor.getBlue()));
				}

				if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) && (ContBorda == 1)) {
					corBorda = Color.GRAY;
					borda.setBackground(corBorda);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, cinza, 6, SpringLayout.SOUTH, branco);
		sl_panel.putConstraint(SpringLayout.WEST, cinza, 0, SpringLayout.WEST, branco);
		sl_panel.putConstraint(SpringLayout.SOUTH, cinza, -297, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, cinza, -95, SpringLayout.EAST, panel);
		cinza.setBackground(Color.GRAY);
		panel.add(cinza);

		///////////////////////////// COR ROSA
		///////////////////////////// ////////////////////////////////////////

		rosa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0)) {
					cor = Color.pink;
					CorAtual.setBackground(cor);
					Red.setText(x = Integer.toString(cor.getRed()));
					Green.setText(y = Integer.toString(cor.getGreen()));
					Blue.setText(z = Integer.toString(cor.getBlue()));
				}

				if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) && (ContBorda == 1)) {
					corBorda = Color.PINK;
					borda.setBackground(corBorda);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, rosa, 58, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, cinzaclaro, -6, SpringLayout.NORTH, rosa);
		sl_panel.putConstraint(SpringLayout.WEST, rosa, 100, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, rosa, 0, SpringLayout.EAST, cinzaclaro);
		rosa.setBackground(Color.PINK);
		panel.add(rosa);

		///////////////////////////// COR VERMELHA
		///////////////////////////// ////////////////////////////////////////

		vermelho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0)) {
					cor = Color.red;
					CorAtual.setBackground(cor);
					Red.setText(x = Integer.toString(cor.getRed()));
					Green.setText(y = Integer.toString(cor.getGreen()));
					Blue.setText(z = Integer.toString(cor.getBlue()));
				}

				if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) && (ContBorda == 1)) {
					corBorda = Color.RED;
					borda.setBackground(corBorda);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, vermelho, 6, SpringLayout.SOUTH, cinza);
		sl_panel.putConstraint(SpringLayout.WEST, vermelho, 0, SpringLayout.WEST, branco);
		sl_panel.putConstraint(SpringLayout.SOUTH, vermelho, -275, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, vermelho, -95, SpringLayout.EAST, panel);
		vermelho.setBackground(Color.RED);
		panel.add(vermelho);

		///////////////////////////// COR ROXO
		///////////////////////////// ////////////////////////////////////////

		roxo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0)) {
					cor = Color.magenta;
					CorAtual.setBackground(cor);
					Red.setText(x = Integer.toString(cor.getRed()));
					Green.setText(y = Integer.toString(cor.getGreen()));
					Blue.setText(z = Integer.toString(cor.getBlue()));
				}

				if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) && (ContBorda == 1)) {
					corBorda = Color.MAGENTA;
					borda.setBackground(corBorda);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, roxo, 80, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, roxo, -275, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, rosa, -6, SpringLayout.NORTH, roxo);
		sl_panel.putConstraint(SpringLayout.WEST, roxo, 0, SpringLayout.WEST, cinzaclaro);
		sl_panel.putConstraint(SpringLayout.EAST, roxo, -27, SpringLayout.EAST, panel);
		roxo.setBackground(Color.MAGENTA);
		panel.add(roxo);

		///////////////////////////// COR AMARELO
		///////////////////////////// ////////////////////////////////////////

		amarelo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0)) {
					cor = Color.yellow;
					CorAtual.setBackground(cor);
					Red.setText(x = Integer.toString(cor.getRed()));
					Green.setText(y = Integer.toString(cor.getGreen()));
					Blue.setText(z = Integer.toString(cor.getBlue()));
				}

				if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) && (ContBorda == 1)) {
					corBorda = Color.YELLOW;
					borda.setBackground(corBorda);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, amarelo, 6, SpringLayout.SOUTH, vermelho);
		sl_panel.putConstraint(SpringLayout.WEST, amarelo, 0, SpringLayout.WEST, branco);
		sl_panel.putConstraint(SpringLayout.SOUTH, amarelo, -253, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, amarelo, -95, SpringLayout.EAST, panel);
		amarelo.setBackground(Color.YELLOW);
		panel.add(amarelo);

		///////////////////////////// COR LARANJA
		///////////////////////////// ////////////////////////////////////////

		orange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0)) {
					cor = Color.orange;
					CorAtual.setBackground(cor);
					Red.setText(x = Integer.toString(cor.getRed()));
					Green.setText(y = Integer.toString(cor.getGreen()));
					Blue.setText(z = Integer.toString(cor.getBlue()));
				}

				if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) && (ContBorda == 1)) {
					corBorda = Color.ORANGE;
					borda.setBackground(corBorda);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, orange, 6, SpringLayout.SOUTH, roxo);
		sl_panel.putConstraint(SpringLayout.WEST, orange, 100, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, orange, -253, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, orange, 0, SpringLayout.EAST, cinzaclaro);
		orange.setBackground(Color.ORANGE);
		panel.add(orange);

		///////////////////////////// COR VERDE
		///////////////////////////// ////////////////////////////////////////

		verde.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0)) {
					cor = Color.green;
					CorAtual.setBackground(cor);
					Red.setText(x = Integer.toString(cor.getRed()));
					Green.setText(y = Integer.toString(cor.getGreen()));
					Blue.setText(z = Integer.toString(cor.getBlue()));
				}

				if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) && (ContBorda == 1)) {
					corBorda = Color.GREEN;
					borda.setBackground(corBorda);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, verde, 6, SpringLayout.SOUTH, amarelo);
		sl_panel.putConstraint(SpringLayout.WEST, verde, 0, SpringLayout.WEST, branco);
		sl_panel.putConstraint(SpringLayout.SOUTH, verde, 140, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, verde, -95, SpringLayout.EAST, panel);
		verde.setBackground(Color.GREEN);
		panel.add(verde);

		///////////////////////////// COR AZUL CLARO
		///////////////////////////// ////////////////////////////////////////

		Azulzinho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0)) {
					cor = Color.cyan;
					CorAtual.setBackground(cor);
					Red.setText(x = Integer.toString(cor.getRed()));
					Green.setText(y = Integer.toString(cor.getGreen()));
					Blue.setText(z = Integer.toString(cor.getBlue()));
				}

				if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) && (ContBorda == 1)) {
					corBorda = Color.cyan;
					borda.setBackground(corBorda);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, Azulzinho, 6, SpringLayout.SOUTH, verde);
		sl_panel.putConstraint(SpringLayout.WEST, Azulzinho, 0, SpringLayout.WEST, branco);
		sl_panel.putConstraint(SpringLayout.SOUTH, Azulzinho, -209, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, Azulzinho, -68, SpringLayout.EAST, cinzaclaro);
		Azulzinho.setBackground(Color.CYAN);
		panel.add(Azulzinho);

		///////////////////////////// COR AZUL FORTE
		///////////////////////////// ////////////////////////////////////////

		azulzão.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0)) {
					cor = Color.blue;
					CorAtual.setBackground(cor);
					Red.setText(x = Integer.toString(cor.getRed()));
					Green.setText(y = Integer.toString(cor.getGreen()));
					Blue.setText(z = Integer.toString(cor.getBlue()));
				}

				if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) && (ContBorda == 1)) {
					corBorda = Color.blue;
					borda.setBackground(corBorda);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, azulzão, 28, SpringLayout.SOUTH, amarelo);
		sl_panel.putConstraint(SpringLayout.WEST, azulzão, 68, SpringLayout.WEST, branco);
		sl_panel.putConstraint(SpringLayout.SOUTH, azulzão, 0, SpringLayout.SOUTH, Azulzinho);
		sl_panel.putConstraint(SpringLayout.EAST, azulzão, 0, SpringLayout.EAST, cinzaclaro);
		azulzão.setBackground(Color.BLUE);
		panel.add(azulzão);

		///////////////////////////// COR VERDE FORTE
		///////////////////////////// ////////////////////////////////////////

		Verdezaõ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0)) {
					cor = new Color(0, 100, 0);
					CorAtual.setBackground(cor);
					Red.setText(x = Integer.toString(cor.getRed()));
					Green.setText(y = Integer.toString(cor.getGreen()));
					Blue.setText(z = Integer.toString(cor.getBlue()));
				}

				if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) && (ContBorda == 1)) {
					corBorda = new Color(0, 100, 0);
					borda.setBackground(corBorda);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, Verdezaõ, 6, SpringLayout.SOUTH, orange);
		sl_panel.putConstraint(SpringLayout.WEST, Verdezaõ, 0, SpringLayout.WEST, cinzaclaro);
		sl_panel.putConstraint(SpringLayout.SOUTH, Verdezaõ, -231, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, Verdezaõ, 0, SpringLayout.EAST, cinzaclaro);
		Verdezaõ.setBackground(new Color(0, 100, 0));
		panel.add(Verdezaõ);

		///////////////////////////// COR CINZA FORTE
		///////////////////////////// ////////////////////////////////////////

		Cinzão.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
					cor = Color.DARK_GRAY;
					CorAtual.setBackground(cor);
					Red.setText(x = Integer.toString(cor.getRed()));
					Green.setText(y = Integer.toString(cor.getGreen()));
					Blue.setText(z = Integer.toString(cor.getBlue()));
				}

				if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) && (ContBorda == 1)) {
					corBorda = Color.DARK_GRAY;
					borda.setBackground(corBorda);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, Cinzão, 6, SpringLayout.SOUTH, Azulzinho);
		sl_panel.putConstraint(SpringLayout.WEST, Cinzão, 0, SpringLayout.WEST, branco);
		sl_panel.putConstraint(SpringLayout.SOUTH, Cinzão, 22, SpringLayout.SOUTH, Azulzinho);
		sl_panel.putConstraint(SpringLayout.EAST, Cinzão, 0, SpringLayout.EAST, branco);
		Cinzão.setBackground(Color.DARK_GRAY);
		panel.add(Cinzão);

		///////////////////////////// COR PRETA
		///////////////////////////// ////////////////////////////////////////

		Preto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0)) {
					cor = Color.BLACK;
					CorAtual.setBackground(cor);
					Red.setText(x = Integer.toString(cor.getRed()));
					Green.setText(y = Integer.toString(cor.getGreen()));
					Blue.setText(z = Integer.toString(cor.getBlue()));
				}

				if (((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) && (ContBorda == 1)) {
					corBorda = Color.BLACK;
					borda.setBackground(corBorda);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, Preto, 6, SpringLayout.SOUTH, azulzão);
		sl_panel.putConstraint(SpringLayout.WEST, Preto, 0, SpringLayout.WEST, cinzaclaro);
		sl_panel.putConstraint(SpringLayout.SOUTH, Preto, 0, SpringLayout.SOUTH, Cinzão);
		sl_panel.putConstraint(SpringLayout.EAST, Preto, 0, SpringLayout.EAST, cinzaclaro);
		Preto.setBackground(Color.BLACK);
		panel.add(Preto);

		///////////////////////////// PAINEL DE COR ATUAL
		///////////////////////////// ////////////////////////////////////////

		sl_panel.putConstraint(SpringLayout.NORTH, CorAtual, 74, SpringLayout.SOUTH, Cinzão);
		sl_panel.putConstraint(SpringLayout.WEST, CorAtual, 0, SpringLayout.WEST, branco);
		sl_panel.putConstraint(SpringLayout.SOUTH, CorAtual, -33, SpringLayout.SOUTH, panel);
		CorAtual.setBackground(cor);
		panel.add(CorAtual);

		sl_panel.putConstraint(SpringLayout.NORTH, borda, 0, SpringLayout.NORTH, CorAtual);
		sl_panel.putConstraint(SpringLayout.WEST, borda, -67, SpringLayout.EAST, cinzaclaro);
		sl_panel.putConstraint(SpringLayout.SOUTH, borda, 0, SpringLayout.SOUTH, CorAtual);
		sl_panel.putConstraint(SpringLayout.EAST, borda, 0, SpringLayout.EAST, cinzaclaro);
		borda.setBackground(Color.white);
		panel.add(borda);

		JRadioButton rdbtnBorda = new JRadioButton("Borda");
		rdbtnBorda.setToolTipText("Ao ativar esse botão, tu poderá escolher a cor da borda com o botão direito do mouse");
		rdbtnBorda.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (ContBorda == 1) {
					ContBorda = 0;
					corBorda = null;
					System.out.println(ContBorda);
				} else {
					ContBorda = 1;
					corBorda = Color.WHITE;
				}
				System.out.println(ContBorda);
			}
		});
		rdbtnBorda.setBackground(SystemColor.control);
		sl_panel.putConstraint(SpringLayout.WEST, rdbtnBorda, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, rdbtnBorda, -3, SpringLayout.NORTH, branco);
		panel.add(rdbtnBorda);

	}

	public void setColor(Color cor) {
		this.cor = cor;
	}

	public Color getColor() {
		return this.cor;
	}

	public Color getCorBorda() {
		return corBorda;
	}

	public void setCorBorda(Color corBorda) {
		this.corBorda = corBorda;
	}

	public boolean isJanelaAberta() {
		return JanelaAberta;
	}

	public void setJanelaAberta(boolean janelaAberta) {
		JanelaAberta = janelaAberta;
	}
}
