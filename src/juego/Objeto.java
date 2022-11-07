package juego;


import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Objeto {
    public static final int DIAMETRO = 30;
	
    int x = 0;
    int y = 0;
    int xa = 1; //Se mueve un pixel 
    int ya = 1; //Se mueve un pixel 
    public Juego miniTenis;
    AudioClip sonidoChoqueRaqueta = java.applet.Applet.newAudioClip(getClass().getResource("/juego/ping.wav"));
    AudioClip sonidoChoquePared = java.applet.Applet.newAudioClip(getClass().getResource("/juego/Chispa.wav"));

    public Objeto(Juego miniTenis) {
	this.miniTenis = miniTenis;
    }

    public void mover() {                
        if (x + xa < 0) {
            sonidoChoquePared.play();
            xa = miniTenis.velocidad;
        }
        else if (x + xa > miniTenis.getWidth() - DIAMETRO) {
            sonidoChoquePared.play();
            xa = -miniTenis.velocidad;
        }
        else if (y + ya < 0) {
            sonidoChoquePared.play();
            ya = miniTenis.velocidad;	
        }
        else if ((y + ya > miniTenis.getHeight() - DIAMETRO)){
            miniTenis.gameOver();
        }	
        else if (colision() == true){  
            sonidoChoqueRaqueta.play();
            ya = -miniTenis.velocidad;
            y = miniTenis.raqueta.getAltura() - DIAMETRO;
            miniTenis.velocidad++;
        } 
	x = x + xa;
        y = y + ya;       
    }

    public boolean colision() {
	return miniTenis.raqueta.getBounds().intersects(getBounds());
        //Devuelve true si la pelota intersecta con la raqueta
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.GREEN);
	g.fillOval(x, y, DIAMETRO, DIAMETRO);
    }

    public Rectangle getBounds() {
	return new Rectangle(x, y, DIAMETRO, DIAMETRO);
    }
}