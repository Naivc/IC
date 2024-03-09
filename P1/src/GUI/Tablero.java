package GUI;

import javax.swing.*;
import java.awt.*;
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

    public void inicializa(int dimensionX, int dimensionY) {
    	ini= new Point(-1,-1);
    	fin= new Point(-1,-1);
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(dimensionX,dimensionY));
        tablero = new JPanel[dimensionX][dimensionY];
     // Crear un conjunto para almacenar las coordenadas de las casillas marcadas
        casillasMarcadas = new HashSet<>();

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
                              //Graphics2D g2d = (Graphics2D) g;
                              //Stroke oldStroke = g2d.getStroke();
                              //g2d.setStroke(new BasicStroke(6));
                              g.drawLine(0, 0, getWidth(), getHeight());
                              g.drawLine(0, getHeight(), getWidth(), 0);
                              //g2d.setStroke(oldStroke);
                             
                        	
                              
                        }
                        if (punto.equals(ini)) {
                            g.setColor(Color.GREEN);
                            g.drawOval(getWidth()/2, getHeight()/2,getWidth()/4, getHeight()/4);
                            /*
                              g.drawLine(0, 0, getWidth(), getHeight());
                              g.drawLine(0, getHeight(), getWidth(), 0);
                              */
                        }
                        if (punto.equals(fin)) {
                            g.setColor(Color.BLUE);
                            g.drawOval(getWidth()/2, getHeight()/2,getWidth()/4, getHeight()/4);
                            /*
                              g.drawLine(0, 0, getWidth(), getHeight());
                              g.drawLine(0, getHeight(), getWidth(), 0);
                              */
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
                            ini=punto;
                        } else if(punto.equals(ini)) {
                        	ini = new Point(-1,-1);
                        	fin= punto;
                        }else if(punto.equals(fin)) {
                        	fin = new Point(-1,-1);
                        }else {
                        	casillasMarcadas.add(punto);
                        }
                         
                        frame.repaint();
                    }
                });

                // Configurar el borde de la casilla
                casilla.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // Agregar la casilla al contenedor principal y al arreglo tablero
                frame.getContentPane().add(casilla);
                tablero[i][j] = casilla;
            }
        }


        frame.setVisible(true);
    }
}