/**
 * Created by Brayan Fajardo on 19/06/2014.
 */

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Interfaz{
    public int TEM, TIM;
    //
    private static Conexion con = new Conexion("Copamundialfifa2014");
    private static Statement comando = null;
    private static ResultSet resultados = null;
    private static JTable tabla = null;
    static DefaultTableModel modelo = null;
    static final JFrame ventana = new JFrame("Copa Mundial de la FIFA Brasil 2014");
    private Player player;
    static final JPanel panelPrincipal = new JPanel();

    //

    static void mostrarTabla (){
        con.conectarBaseDatos();
        Connection conexion = con.getConn();
        String instruccion = "select * from tablaGeneral order by pts desc;";
        try {
            comando = conexion.createStatement();
            resultados = comando.executeQuery(instruccion);

            String [] columnas = {"posicion","equipo","pj","pg","pe","pp","gf","gc","pts"};
            if (tabla!=null)panelPrincipal.remove(tabla);
            tabla = new JTable();
            tabla.setLayout(null);
            tabla.setBounds(0,137,889,695);
            tabla.setVisible(false);
            modelo = new DefaultTableModel(){
                public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            };
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(SwingConstants.CENTER);


            int posicion, pj, pg, pe, pp, gf, gc, pts;
            String equipo;

            modelo.setColumnIdentifiers(columnas);

            tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tabla.setFillsViewportHeight(true);

            tabla.setModel(modelo);

            modelo.addRow(new Object[]{"POSICIÓN", "EQUIPO", "PJ", "PG", "PE", "PP", "GF", "GC", "PTS"});

            tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
            tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
            tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
            tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
            tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);
            tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
            tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);
            tabla.getColumnModel().getColumn(7).setCellRenderer(tcr);
            tabla.getColumnModel().getColumn(8).setCellRenderer(tcr);

            while (resultados.next()){
                posicion = resultados.getInt("posicion");
                equipo = resultados.getString("equipo");
                pj = resultados.getInt("pj");
                pg = resultados.getInt("pg");
                pe = resultados.getInt("pe");
                pp = resultados.getInt("pp");
                gf = resultados.getInt("gf");
                gc = resultados.getInt("gc");
                pts = resultados.getInt("pts");

                modelo.addRow(new Object[] {posicion, equipo, pj, pg,pe,pp,gf,gc,pts});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(ventana, "Error al leer datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        con.cerrarConexion();
    }

    public Interfaz() {
        //VENTANAS
        final JFrame ventanaInicio = new JFrame("Mundial Brasil 2014");
        ventanaInicio.setUndecorated(true);
        ventana.setUndecorated(true);
        ventanaInicio.setSize(891, 695);
        ventanaInicio.setLocation(200,0);
        ventanaInicio.setLayout(null);
        ventanaInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelInicio = new JPanel();
        JButton buttonIniciar = new JButton("Iniciar");
        buttonIniciar.setBounds(762, 637, 100,20);
        buttonIniciar.setVisible(true);
        panelInicio.add(buttonIniciar);
        panelInicio.setBounds(0,0,891,695);
        panelInicio.setLayout(null);
        ventanaInicio.add(panelInicio);
        ImageIcon imagenInicio = new ImageIcon("MundialInicio.jpg");
        JLabel imgInicio = new JLabel(imagenInicio);
        imgInicio.setBounds(0,0,891,695);
        panelInicio.add(imgInicio);

        //ventana.setSize(891, 695);
        ventana.setSize(891, 135);
        ventana.setLocation(200,0);
        ventana.setVisible(false);
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //PANEL
        //final JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBounds(0, 0, 890, 695);
        panelPrincipal.setLayout(null);
        ventana.add(panelPrincipal);

        //IMAGEN
        ImageIcon Imagen = new ImageIcon("FIFA2014.png");
        JLabel Img = new JLabel(Imagen);
        Img.setBounds(0, 0, 889, 115);
        panelPrincipal.add(Img);

        //TEXTOS
        final JLabel textoTEM = new JLabel("Tiempo Externo Máximo");
        textoTEM.setBounds(2, 116, 143, 20);
        panelPrincipal.add(textoTEM);
        final JLabel textoTIM = new JLabel("Tiempo Interno Máximo");
        textoTIM.setBounds(180, 116, 143, 20);
        panelPrincipal.add(textoTIM);
        final JTextField campoTIM = new JTextField();
        campoTIM.setBounds(324, 116, 25, 20);
        panelPrincipal.add(campoTIM);
        final JTextField campoTEM = new JTextField();
        campoTEM.setBounds(144, 116, 25, 20);
        panelPrincipal.add(campoTEM);
        campoTEM.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });

        campoTIM.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });

        //BOTONES
        final JButton botonSalir = new JButton("Salir");
        botonSalir.setBounds(155, 116, 100, 20);
        botonSalir.setVisible(false);
        panelPrincipal.add(botonSalir);
        final JButton botonTabla = new JButton("Tabla Posiciones");
        botonTabla.setBounds(2, 116, 150, 20);
        final JButton botonInicio = new JButton("Iniciar Simulación");
        botonInicio.setBounds(380, 116, 150, 20);
        botonInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    TEM = Integer.parseInt(campoTEM.getText());
                    TIM = Integer.parseInt(campoTIM.getText());
                    ////////////////////////////////////////////////////////////
                    Datos.actualizarBitacora("Inicio Simulación", null);
                    Hilo hilo1 = new Hilo("Grupo A", 1, TIM, TEM);
                    Hilo hilo2 = new Hilo("Grupo B", 7, TIM, TEM);
                    Hilo hilo3 = new Hilo("Grupo C", 13, TIM, TEM);
                    Hilo hilo4 = new Hilo("Grupo D", 19, TIM, TEM);
                    Hilo hilo5 = new Hilo("Grupo E", 25, TIM, TEM);
                    Hilo hilo6 = new Hilo("Grupo F", 31, TIM, TEM);
                    Hilo hilo7 = new Hilo("Grupo G", 37, TIM, TEM);
                    Hilo hilo8 = new Hilo("Grupo H", 43, TIM, TEM);
                    hilo1.start();
                    hilo2.start();
                    hilo3.start();
                    hilo4.start();
                    hilo5.start();
                    hilo6.start();
                    hilo7.start();
                    hilo8.start();
                    ////////////////////////////////////////////////////////////
                    panelPrincipal.add(botonTabla);
                    botonInicio.setVisible(false);
                    textoTEM.setVisible(false);
                    textoTIM.setVisible(false);
                    campoTEM.setVisible(false);
                    campoTIM.setVisible(false);
                    botonSalir.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ventana, "Por favor llene los espacios en blanco.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelPrincipal.add(botonInicio);

        ventanaInicio.setVisible(true);

        botonTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setSize(891, 695);
                mostrarTabla();
                panelPrincipal.add(tabla);
                tabla.setVisible(true);
            }
        });

        buttonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaInicio.setVisible(false);
                ventana.setVisible(true);
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.close();
                System.exit(0);
            }
        });

        String dir =  "We Are One.mp3";
        try {
            FileInputStream fis;
            fis = new FileInputStream(dir);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis); // Llamada a constructor de la clase Player
            player.play(); // Llamada al método play
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

    }
}


