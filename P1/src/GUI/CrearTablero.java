package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CrearTablero extends JFrame {
    private JTextField campoX;
    private JTextField campoY;
    private Tablero tablero;

    public CrearTablero(Tablero tablero) {
        this.tablero = tablero;
        initComponents();
    }

    private void initComponents() {
        JLabel etiquetaX = new JLabel("Dimensión X:");
        JLabel etiquetaY = new JLabel("Dimensión Y:");
        etiquetaX.setFont(new Font("Arial", Font.BOLD, 25)); // Ejemplo de fuente Arial, negrita, tamaño 16
        etiquetaY.setFont(new Font("Arial", Font.BOLD, 25)); // Ejemplo de fuente Arial, negrita, tamaño 16
        campoX = new JTextField(10); // Aumentar el tamaño del campo de texto
        campoY = new JTextField(10); // Aumentar el tamaño del campo de texto
        JButton botonCrear = new JButton("Crear");

        botonCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int dimensionX = Integer.parseInt(campoX.getText());
                int dimensionY = Integer.parseInt(campoY.getText());
                tablero.inicializa(dimensionX, dimensionY);
                dispose(); // Cerrar la ventana emergente
            }
        });

        JPanel panel = new JPanel(new GridLayout(4, 2)); // Aumentar el número de filas del GridLayout
        panel.add(etiquetaX);
        panel.add(campoX);
        panel.add(etiquetaY);
        panel.add(campoY);
        panel.add(new JLabel()); // Añadir un componente vacío para espacio
        panel.add(new JLabel()); // Añadir un componente vacío para espacio
        panel.add(botonCrear);

        getContentPane().add(panel);
        setSize(400, 200); // Aumentar el tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana emergente en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
       /* Tableroo tablero = new Tableroo();*/
        Tablero tablero = new Tablero();
        CrearTablero ventana = new CrearTablero(tablero);
    }
}
/*
class Tableroo {
    private JFrame frame;
    private JPanel[][] tablero;

    public void inicializa(int dimensionX, int dimensionY) {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(dimensionX, dimensionY));
        tablero = new JPanel[dimensionX][dimensionY];

        for (int i = 0; i < dimensionX; i++) {
            for (int j = 0; j < dimensionY; j++) {
                JPanel casilla = new JPanel();
                casilla.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                frame.getContentPane().add(casilla);
                tablero[i][j] = casilla;
            }
        }

        frame.setVisible(true);
    }
}
*/