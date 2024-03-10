package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class Tablero {
	private JFrame frame;
	private JPanel[][] tablero;
	private Set<Point> casillasMarcadas;
	private Point ini;
	private Point fin;
	private JPanel casilla;
	private Point punto;
	private JPanel mainPanel;
	private JPanel toolPanel;
	private JToolBar toolBar;
	private JButton confirma;
	private Aestrella aestrella;
	private Boolean rutaActiva;

	public void inicializa(int dimensionX, int dimensionY) {
		rutaActiva = false;
		ini = new Point(-1, -1);
		fin = new Point(-1, -1);
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		mainPanel = new JPanel(new GridLayout(dimensionX, dimensionY));
		tablero = new JPanel[dimensionX][dimensionY];
		// Crear un conjunto para almacenar las coordenadas de las casillas marcadas
		casillasMarcadas = new HashSet<>();
		// crear toolbar con boton de confirma la mapa y calcular mejor ruta
		toolBar = new JToolBar();
		toolPanel = new JPanel();
		confirma = new JButton("Confirma");
		// con el boton confirma obtenemos las ubicaciones de prohibido, inicio y fin.
		// se aplica algorith A*
		confirma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (ini.equals(new Point(-1, -1)) || fin.equals(new Point(-1, -1))) {
					// no se puede ejecutar sin dato de ini o dato de fin
					JDialog d = new JDialog(frame, "Error");
					// create a label
					JLabel l = new JLabel("no se puede ejecutar sin dato de ini o dato de fin");
					d.add(l);
					// setsize of dialog
					d.setSize(300, 100);
					d.setLocationRelativeTo(frame);
					// set visibility of dialog
					d.setVisible(true);
				} else {
					// se ejecuta algoritmia de A*
					aestrella = new Aestrella(casillasMarcadas, ini, fin,dimensionX,dimensionY);
					// se dibuja ruta una vez obtenido la ruta atraves de A*
					rutaActiva = true;
				}
				frame.repaint();

			}
		});
		toolPanel.add(confirma);
		toolBar.add(toolPanel);

		// Crear un objeto Point para reutilizarlo dentro del bucle

		for (int i = 0; i < dimensionX; i++) {
			for (int j = 0; j < dimensionY; j++) {
				final int x = i;
				final int y = j;

				// Crear el JPanel para la casilla
				casilla = new JPanel() {
					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);
						punto = new Point(x, y); // Actualizar las coordenadas del punto
						if (casillasMarcadas.contains(punto)) {

							g.setColor(Color.RED);
							g.drawLine(0, 0, getWidth(), getHeight());
							g.drawLine(0, getHeight(), getWidth(), 0);

						}
						// dibuja inicio
						if (punto.equals(ini)) {
							g.setColor(Color.GREEN);
							g.fillOval(getWidth() / 2, getHeight() / 2, getWidth() / 4, getHeight() / 4);
						}
						// dibuja la meta
						if (punto.equals(fin)) {
							g.setColor(Color.BLUE);
							g.fillOval(getWidth() / 2, getHeight() / 2, getWidth() / 4, getHeight() / 4);
						}
						// dibuja la ruta
						if (aestrella.existeruta(punto) && rutaActiva) {
							g.setColor(Color.BLACK);
							/*explicacion de direccion que va cada tramo de ruta
							 *1 2 3
							 *4 0 5
							 *6 7 8
							 *posion 0 es la posicion actual , resto son las direccion
							 */
							int direccion = aestrella.direccion(punto);
							if (direccion == 1||direccion == 8) {
								g.drawLine(getWidth(), 0, 0, getHeight());
								
							} else if (direccion == 2||direccion == 7) {
								g.drawLine(getHeight()/2, 0, getWidth()/2, getHeight());

							} else if (direccion == 3||direccion == 6) {
								g.drawLine(0, 0, getWidth(), getHeight());

							} else if (direccion == 4||direccion == 5) {
								g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);

							} 
						}
					}
				};

				// Agregar el MouseListener para marcar o desmarcar la casilla al hacer clic
				casilla.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						punto = new Point(x, y); // Actualizar las coordenadas del punto
						if (casillasMarcadas.contains(punto)) {
							casillasMarcadas.remove(punto);
							ini = punto;
						} else if (punto.equals(ini)) {
							ini = new Point(-1, -1);
							fin = punto;
						} else if (punto.equals(fin)) {
							fin = new Point(-1, -1);
						} else {
							casillasMarcadas.add(punto);
						}
						// se oculta la ruta una vez cambiado la mapa
						rutaActiva = false;
						frame.repaint();
					}
				});

				// Configurar el borde de la casilla
				casilla.setBorder(BorderFactory.createLineBorder(Color.BLACK));

				// Agregar la casilla al contenedor principal y al arreglo tablero
				mainPanel.add(casilla);
				tablero[i][j] = casilla;
			}
		}

		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}
}