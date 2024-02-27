package juego;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racket {
    public static final int POSICIONY_RAQUETA = 330;
    public static final int ANCHURA_RAQUETA = 60;
    public static final int ALTURA_RAQUETA = 10;
    int x = 0;
    int xa = 0;
    public Game miniTenis;

    public Racket(Game miniTenis) {
	this.miniTenis = miniTenis;
    }

    public void mover() {
	if ((x + xa) > 0 && (x + xa) < (miniTenis.getWidth() - ANCHURA_RAQUETA)){
            x = x + xa; //Se mueve en el eje x, y lo delimitamos
        }
            
    }

    public void paint(Graphics2D g) {
	g.fillRect(x, POSICIONY_RAQUETA, ANCHURA_RAQUETA, ALTURA_RAQUETA);
    }

    public void keyReleased(KeyEvent e) {
	xa = 0;
    }

    public void keyPressed(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_LEFT) //El código de la letra pulsada coincide con el código de la tecla dcha.
            xa = -miniTenis.velocidad;
	if (e.getKeyCode() == KeyEvent.VK_RIGHT) //El código de la letra pulsada coincide con el código de la tecla izda.
            xa = miniTenis.velocidad;
    }

    public Rectangle getBounds() {
	return new Rectangle(x, POSICIONY_RAQUETA, ANCHURA_RAQUETA, ALTURA_RAQUETA);
    }

    public int getAltura() {
	return POSICIONY_RAQUETA - ALTURA_RAQUETA;
    }
}