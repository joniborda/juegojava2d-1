package principal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

	public Tecla arriba = new Tecla();
	public Tecla abajo = new Tecla();
	public Tecla izquierda = new Tecla();
	public Tecla derecha = new Tecla();

	public boolean corriendo = false;

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W :
			case KeyEvent.VK_UP :
				arriba.teclaPulsada();
				break;
			case KeyEvent.VK_S :
			case KeyEvent.VK_DOWN :
				abajo.teclaPulsada();
				break;
			case KeyEvent.VK_A :
			case KeyEvent.VK_LEFT :
				izquierda.teclaPulsada();
				break;
			case KeyEvent.VK_D :
			case KeyEvent.VK_RIGHT :
				derecha.teclaPulsada();
				break;
			case KeyEvent.VK_SHIFT :
				corriendo = true;
				break;
			case KeyEvent.VK_ESCAPE :
				System.exit(0);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W :
			case KeyEvent.VK_UP :
				arriba.teclaLiberada();
				break;
			case KeyEvent.VK_S :
			case KeyEvent.VK_DOWN :
				abajo.teclaLiberada();
				break;
			case KeyEvent.VK_A :
			case KeyEvent.VK_LEFT :
				izquierda.teclaLiberada();
				break;
			case KeyEvent.VK_D :
			case KeyEvent.VK_RIGHT :
				derecha.teclaLiberada();
				break;
			case KeyEvent.VK_SHIFT :
				corriendo = false;
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
