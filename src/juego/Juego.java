/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Juego extends JPanel  {
    Juego juego;
    Objeto objeto = new Objeto(this);
    Raqueta raqueta = new Raqueta(this);
    int velocidad = 1;
    
    AudioClip sonidoGameOver = java.applet.Applet.newAudioClip(getClass().getResource("/juego/doh.wav"));    
    AudioClip sonidoJuego = java.applet.Applet.newAudioClip(getClass().getResource("/juego/MarioBros.wav"));
    
    public int getPuntuacion() {
	return velocidad - 1;
    }

    public Juego() {
	addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                raqueta.keyReleased(e); //Se informa a Raqueta que se ha soltado la tecla
            }

            @Override
            public void keyPressed(KeyEvent e) {
                raqueta.keyPressed(e);  //Se informa a Raqueta que se ha pulsado la tecla
            }
        });
        setFocusable(true); //Se pone true para capturar eventos
	}
       
    public void mover() {
	objeto.mover();
	raqueta.mover();
    }

    @Override
    public void paint(Graphics g) {
	super.paint(g); //Limpia la pantalla
	Graphics2D g2d = (Graphics2D) g;
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);     //Sueviza los bordes de la pelota
	objeto.paint(g2d);
	raqueta.paint(g2d);

	g2d.setColor(Color.BLUE);
	g2d.setFont(new Font("Verdana", Font.PLAIN, 20));
	g2d.drawString("MINI TENIS - Puntos: " + String.valueOf(getPuntuacion()), 10, 30);
        //Se convierte en un String
    }

    public void gameOver() {
        sonidoJuego.stop();
        sonidoGameOver.play();
        JOptionPane.showMessageDialog(this, "Tu puntuación ha sido " + getPuntuacion(),
               "Game Over", JOptionPane.YES_NO_OPTION); //Inatrucción bloqueante
        System.exit(0);
    }
   

    public static void main(String[] args)  {
        Scanner teclado = new Scanner(System.in);
        JFrame frame = new JFrame("MINI TENIS");
        Juego juego = new Juego();
        frame.add(juego);      
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        //frame.setBackground(Color.yellow);
        juego.sonidoJuego.play();     
        while (true) {
            juego.mover();
            juego.repaint();
            try {
                Thread.sleep(10);   //descansa por 10 milisegundos lo que 
                                    //permite que el procesador ejecute otros threads 
                                    //y  que llama al método paint.                                  
            }
            catch (InterruptedException ie) {
                System.err.println("Error");
            }
        }
        
    }
}
