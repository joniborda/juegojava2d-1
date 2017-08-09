package principal.entes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.control.GestorControles;
import principal.sprites.HojaSprites;

public class Jugador {

	private double posicionX;
	private double posicionY;

	private int direccion;

	private double velocidad = 1;
	private boolean enMovimiento = false;
	private int animacion;
	private int estado;

	private HojaSprites hs;
	private BufferedImage imagenActual;

	public Jugador(final double posicionX, final double posicionY) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;

		this.direccion = 0;

		this.hs = new HojaSprites(Constantes.RUTA_JUGADOR, Constantes.LADO_SPRITE, false);
		this.imagenActual = hs.getSprite(4, 3).getImagen();

		this.animacion = 0;
		this.estado = 0;
	}

	public void actualizar() {
		cambiarAnimacionEstado();
		enMovimiento = false;
		determinarDireccion();
		animar();
	}

	private void cambiarAnimacionEstado() {
		if (animacion < 30) {
			animacion++;
		} else {
			animacion = 0;
		}

		if (animacion < 15) {
			estado = 1;
		} else {
			estado = 2;
		}
	}

	private void determinarDireccion() {
		final int velocidadX = evaluarVelocidadX();
		final int velocidadY = evaluarVelocidadY();

		if (velocidadX == 0 && velocidadY == 0) {
			return;
		}
		if ((velocidadX != 0 && velocidadY == 0) || (velocidadX == 0 && velocidadY != 0)) {
			mover(velocidadX, velocidadY);
		} else {
			// izqueirda y arriba
			if (velocidadX == -1 && velocidadY == -1) {
				if (GestorControles.teclado.izquierda.getUltimaPulsacion() > GestorControles.teclado.arriba
						.getUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
			// izquierda y abajo
			if (velocidadX == -1 && velocidadY == 1) {
				if (GestorControles.teclado.izquierda.getUltimaPulsacion() > GestorControles.teclado.abajo
						.getUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
			// derecha y arriba
			if (velocidadX == 1 && velocidadY == -1) {
				if (GestorControles.teclado.derecha.getUltimaPulsacion() > GestorControles.teclado.arriba
						.getUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
			// derecha y abajo
			if (velocidadX == 1 && velocidadY == 1) {
				if (GestorControles.teclado.derecha.getUltimaPulsacion() > GestorControles.teclado.abajo
						.getUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
		}
	}

	private void mover(final int velocidadX, final int velocidadY) {
		enMovimiento = true;

		cambiarVelocidadX(velocidadX, velocidadY);

		posicionX += velocidadX * this.velocidad;
		posicionY += velocidadY * this.velocidad;
	}

	private void cambiarVelocidadX(final int velocidadX, final int velocidadY) {
		if (velocidadX == -1) {
			direccion = 3;
		} else if (velocidadX == 1) {
			direccion = 2;
		}

		if (velocidadY == -1) {
			direccion = 1;
		} else if (velocidadY == 1) {
			direccion = 0;
		}
	}

	private int evaluarVelocidadX() {
		int velocidadX = 0;
		if (GestorControles.teclado.izquierda.estaPulsada() && !GestorControles.teclado.derecha.estaPulsada()) {
			velocidadX = -1;
		} else if (GestorControles.teclado.derecha.estaPulsada() && !GestorControles.teclado.izquierda.estaPulsada()) {
			velocidadX = 1;
		}

		return velocidadX;
	}

	private int evaluarVelocidadY() {
		int velocidadY = 0;
		if (GestorControles.teclado.arriba.estaPulsada() && !GestorControles.teclado.abajo.estaPulsada()) {
			velocidadY = -1;
		} else if (GestorControles.teclado.abajo.estaPulsada() && !GestorControles.teclado.arriba.estaPulsada()) {
			velocidadY = 1;
		}

		return velocidadY;
	}

	private void animar() {
		if (!enMovimiento) {
			estado = 0;
			animacion = 0;

		}
		imagenActual = hs.getSprite(direccion, estado).getImagen();
	}

	public void dibujar(Graphics g) {

		final int centroX = Constantes.ANCHO_VENTANA / 2 - Constantes.LADO_SPRITE / 2;
		final int centroY = Constantes.ALTO_VENTANA / 2 - Constantes.LADO_SPRITE / 2;

		g.drawImage(imagenActual, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE, null);
	}

	public void establecerPosicionX(final double posicionX) {
		this.posicionX = posicionX;
	}

	public void establecerPosicionY(final double posicionY) {
		this.posicionY = posicionY;
	}

	public double obtenerPosicionX() {
		return posicionX;
	}

	public double obtenerPosicionY() {
		return posicionY;
	}
}
