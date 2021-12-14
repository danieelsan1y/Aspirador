package grafico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

interface InterfaceUI {

}

class GraficoUI extends JFrame implements InterfaceUI {
	public GraficoUI(String titulo) {
		super(titulo);
	}

	// sensores
	public void sensorSujeira() {
	};

	public void sensorParede() {
	};

	// aspirador
	public void moverAspirador(int linha, int coluna) {
	};

	public void moverAspirador(int linha, int coluna, String direcao) {
	};

	public void mudarDirecaoAspirador(String direcao) {
	};

	public void sugador() {
	};

	// sujeira
	public void mostrarSujeira(int linha, int coluna) {
	};

	public void apagarSujeira(int linha, int coluna) {
	};

	public void acaoRealizar() {
	};

}

class Interface extends GraficoUI {
	JPanel sala;
	JLabel sujeira, parede, mover, direcao, sugador, acao;
	JLabel parede1;
	JLabel aspirador;
	JLabel matrizSujeira[][] = new JLabel[5][5];
	JLabel matrizRastro[][] = new JLabel[5][5];

	int linha = 0, coluna = 0;

	public Interface(String titulo) {
		super(titulo);
		setSize(700, 400);
		setLocation(250, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		montarLayout();
		setVisible(true);
	}

	protected void tempo(int ms) {
		try {
			Thread.sleep(ms);

		} catch (InterruptedException e) {

		}
	}

	protected void initSujeira(JPanel sala) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				matrizSujeira[i][j] = new JLabel(new ImageIcon("sujeira.PNG"));
				matrizSujeira[i][j].setBounds(0 + (i * 64), 0 + (j * 64), 64, 64);
				matrizSujeira[i][j].setVisible(false);
				sala.add(matrizSujeira[i][j]);

			}
		}
	}

	protected void initRastro(JPanel sala) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				matrizRastro[i][j] = new JLabel(new ImageIcon("fundo.PNG"));
				matrizRastro[i][j].setBounds(0 + (i * 64), 0 + (j * 64), 64, 64);
				matrizRastro[i][j].setVisible(false);
				sala.add(matrizRastro[i][j]);

			}
		}
	}

	protected void initAspirador(JPanel sala) {
		aspirador = new JLabel(new ImageIcon("rombaSul.png"));
		aspirador.setBounds(0, 0, 64, 64);
		sala.add(aspirador);

	}

	protected void initSensores() {
		JPanel sensores = new JPanel();
		sensores.setLayout(new FlowLayout(FlowLayout.LEFT));
		sujeira = new JLabel("sujeira", new ImageIcon("luzBranca.png"), JLabel.LEFT);
		parede = new JLabel("parede", new ImageIcon("luzBranca.png"), JLabel.LEFT);
		sensores.setBounds(350, 10, 165, 60);
		sensores.setBorder(new TitledBorder(new LineBorder(Color.black,1),"Sensores"));
		sensores.add(sujeira);
		sensores.add(parede);
		getContentPane().add(sensores);
	}

	protected void initAtuadores() {
		JPanel atuadores = new JPanel();

		atuadores.setLayout(new FlowLayout(FlowLayout.LEFT));
		mover = new JLabel("movimento", new ImageIcon("luzBranca.PNG"), JLabel.LEFT);
		direcao = new JLabel("direção", new ImageIcon("luzBranca.PNG"), JLabel.LEFT);
		sugador = new JLabel("sugador", new ImageIcon("luzBranca.PNG"), JLabel.LEFT);

		atuadores.setBounds(350, 75, 165, 110);
		atuadores.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "Sensores"));

		atuadores.add(mover);
		atuadores.add(direcao);
		atuadores.add(sugador);
		getContentPane().add(atuadores);

	}

	protected void initControle() {
		JPanel controle = new JPanel();

		controle.setLayout(new FlowLayout(FlowLayout.LEFT));
		acao = new JLabel("ação", new ImageIcon("luzBranca.PNG"), JLabel.LEFT);
		controle.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "Controle"));
		controle.setBounds(350, 190, 165, 60);
		controle.add(acao);

		getContentPane().add(controle);

	}

	protected void montarLayout() {
		setLayout(null);
		sala = new JPanel();
		sala.setLayout(null);
		sala.setBounds(10, 10, 320, 320);
		sala.setBackground(new Color(224, 248, 227));
		initAspirador(sala);
		initSujeira(sala);
		initRastro(sala);
		sala.setBorder(new BevelBorder(BevelBorder.LOWERED));
		getContentPane().add(sala);
		initSensores();
		initAtuadores();
		initControle();
	}

	public void sensorSujeira() {
		sujeira.setIcon(new ImageIcon("luzVermelha.png"));
		tempo(500);
		sujeira.setIcon(new ImageIcon("luzBranca.png"));
		tempo(500);
	}

	public void sensorParede() {
		parede.setIcon(new ImageIcon("luzVermelha.png"));
		tempo(500);
		parede.setIcon(new ImageIcon("luzBranca.png"));
		tempo(500);
	}


	public void moverAspirador(int linha, int coluna, String direcao) {
		int i, j, delta;
		mostrarRastro(this.linha, this.coluna);
		mover.setIcon(new ImageIcon("luzVermelha.png"));
		tempo(500);

		if (direcao.equalsIgnoreCase("grafico.Sul")) {
			delta = this.linha;
			for (i = 0; i < (Math.abs((linha * 64) - delta)); i++) {
				this.linha++;
				aspirador.setIcon(new ImageIcon("rombaSul.png"));
				aspirador.setLocation(this.coluna, this.linha);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {

				}
			}
		}

		if (direcao.equalsIgnoreCase("grafico.Norte")) {
			delta = this.linha;
			for (i = 0; i < (Math.abs((linha * 64) - delta)); i++) {
				this.linha--;
				aspirador.setIcon(new ImageIcon("rombaNorte.png"));
				aspirador.setLocation(this.coluna, this.linha);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {

				}
			}
		}
		if (direcao.equalsIgnoreCase("grafico.Leste")) {
			delta = this.coluna;
			for (i = 0; i < (Math.abs((coluna * 64) - delta)); i++) {
				this.coluna++;
				aspirador.setIcon(new ImageIcon("rombaLeste.png"));
				aspirador.setLocation(this.coluna, this.linha);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {

				}
			}
		}
		if (direcao.equalsIgnoreCase("grafico.Oeste")) {
			delta = this.coluna;
			for (i = 0; i < (Math.abs((coluna * 64) - delta)); i++) {
				this.coluna--;
				aspirador.setIcon(new ImageIcon("rombaOeste.png"));
				aspirador.setLocation(this.coluna, this.linha);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {

				}
			}
		}
		mover.setIcon(new ImageIcon("luzBranca.png"));
	};

	public void mudarDirecaoAspirador(String direcao) {
		this.direcao.setIcon(new ImageIcon("luzVermelha.png"));
		tempo(500);
		this.direcao.setIcon(new ImageIcon("luzbranca.png"));
		tempo(500);
	}

	public void sugador() {
		sugador.setIcon(new ImageIcon("luzVermelha.png"));
		tempo(500);
		sugador.setIcon(new ImageIcon("luzbranca.png"));
		tempo(500);
	}

	public void acaoRealizar() {
		acao.setIcon(new ImageIcon("luzVermelha.png"));
		tempo(500);
		acao.setIcon(new ImageIcon("luzbranca.png"));
		tempo(500);
	}

	protected void mostrarRastro(int linha, int coluna) {
		matrizRastro[(int) coluna / 64][(int) linha / 64].setVisible(true);
	}

	public void mostrarSujeira(int linha, int coluna) {
		tempo(500);
		matrizSujeira[coluna][linha].setVisible(true);
	}

	public void apagarSujeira(int linha, int coluna) {
		tempo(500);
		matrizSujeira[coluna][linha].setVisible(false);
	}

}

class Adapter {
	protected Interface display;
	protected static Adapter instancia = null;

	protected Adapter() {
		display = null;
	}

	public static Adapter obterInstancia() {
		if (instancia == null) {
			instancia = new Adapter();

		}
		return instancia;
	}

	public void setDisplay(Interface display) {
		this.display = display;
	}

	public void sensorSujeira() {
		display.sensorSujeira();
	}

	public void sensorParede() {
		display.sensorParede();
	}

	public void moverAspirador(int linha, int coluna, String direcao) {
		display.moverAspirador(linha, coluna, direcao);
	}

	public void mudarDirecaoAspirador(String direcao) {
		display.mudarDirecaoAspirador(direcao);
	}

	public void sugador() {
		display.sugador();
	}

	public void mostrarSujeira(int linha, int coluna) {
		display.mostrarSujeira(linha, coluna);
	}

	public void apagarSujeira(int linha, int coluna) {
		display.apagarSujeira(linha, coluna);
	}

	public void acaoRealizar() {
		display.acaoRealizar();
	}
}