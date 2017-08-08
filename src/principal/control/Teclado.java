package principal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

	private final int numero_teclas = 256;
	private final boolean[] teclas = new boolean[numero_teclas];

	private boolean arriba;
	private boolean abajo;
	private boolean derecha;
	private boolean izquierda;

	private boolean correr;

	private boolean salir;

	public void actualizar() {
		arriba = teclas[KeyEvent.VK_W];
		abajo = teclas[KeyEvent.VK_S];
		derecha = teclas[KeyEvent.VK_D];
		izquierda = teclas[KeyEvent.VK_A];

		salir = teclas[KeyEvent.VK_ESCAPE];

		correr = teclas[KeyEvent.VK_SHIFT];
	}

	public boolean[] getTeclas() {
		return teclas;
	}

	public boolean isArriba() {
		return arriba;
	}

	public boolean isAbajo() {
		return abajo;
	}

	public boolean isDerecha() {
		return derecha;
	}

	public boolean isIzquierda() {
		return izquierda;
	}

	public boolean isCorrer() {
		return correr;
	}

	public boolean isSalir() {
		return salir;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		teclas[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
