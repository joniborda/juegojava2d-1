package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.control.GestorControles;
import principal.mapas.Mapa;
import principal.sprites.HojaSprites;

public class Jugador {

	private double posicionX;
	private double posicionY;

	private int direccion;

	private double velocidad = 1;
	private boolean enMovimiento = false;
	private int animacion;
	private int estado;

	private static final int ANCHO_JUGADOR = 16;
	private static final int ALTO_JUGADOR = 16;

	private final Rectangle LIMITE_ARRIBA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y, ANCHO_JUGADOR, 1);

	private final Rectangle LIMITE_ABAJO = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y + ALTO_JUGADOR, ANCHO_JUGADOR, 1);
	private final Rectangle LIMITE_IZQUIERDA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y, 1, ALTO_JUGADOR);

	private final Rectangle LIMITE_DERECHA = new Rectangle(Constantes.CENTRO_VENTANA_X + ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y, 1, ALTO_JUGADOR);

	private HojaSprites hs;
	private BufferedImage imagenActual;

	private Mapa mapa;

	public Jugador(final double posicionX, final double posicionY, Mapa mapa) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;

		this.direccion = 0;

		this.hs = new HojaSprites(Constantes.RUTA_JUGADOR, Constantes.LADO_SPRITE, false);
		this.imagenActual = hs.getSprite(0, 0).getImagen();

		this.mapa = mapa;
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
		if (animacion < 40) {
			animacion++;
		} else {
			animacion = 0;
		}

		if (animacion < 10) {
			estado = 1;
		} else if (animacion < 30 && animacion >= 20) {
			estado = 2;
		} else {
			estado = 0;
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

		if (!fueraMapa(velocidadX, velocidadY)) {
			if (velocidadX == -1 && !enColisionIzquierda(velocidadX)) {
				posicionX += velocidadX * this.velocidad;
				return;
			}

			if (velocidadX == 1 && !enColisionDerecha(velocidadX)) {
				posicionX += velocidadX * this.velocidad;
				return;
			}

			if (velocidadY == -1 && !enColisionArriba(velocidadY)) {
				posicionY += velocidadY * this.velocidad;
				return;
			}

			if (velocidadY == 1 && !enColisionAbajo(velocidadY)) {
				posicionY += velocidadY * this.velocidad;
				return;
			}
		}

	}

	private boolean enColisionArriba(final int velocidadY) {
		for (int r = 0; r < mapa.areasColision.size(); r++) {
			final Rectangle area = mapa.areasColision.get(r);
			int origenX = area.x;
			// 3 pixeles mas porque es un rectangle y ocupa 2px y 1px es para el
			// siguiente
			int origenY = area.y + velocidadY * (int) velocidad + 3 * (int) velocidad;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

			if (LIMITE_ARRIBA.intersects(areaFutura)) {
				return true;
			}
		}
		return false;
	}

	private boolean enColisionAbajo(final int velocidadY) {
		for (int r = 0; r < mapa.areasColision.size(); r++) {
			final Rectangle area = mapa.areasColision.get(r);
			int origenX = area.x;
			// 3 pixeles mas porque es un rectangle y ocupa 2px y 1px es para el
			// siguiente
			int origenY = area.y + velocidadY * (int) velocidad - 3 * (int) velocidad;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

			if (LIMITE_ABAJO.intersects(areaFutura)) {
				return true;
			}
		}
		return false;
	}

	private boolean enColisionIzquierda(final int velocidadX) {
		for (int r = 0; r < mapa.areasColision.size(); r++) {
			final Rectangle area = mapa.areasColision.get(r);

			int origenX = area.x + velocidadX * (int) velocidad + 3 * (int) velocidad;
			int origenY = area.y;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

			if (LIMITE_IZQUIERDA.intersects(areaFutura)) {
				return true;
			}
		}
		return false;
	}

	private boolean enColisionDerecha(final int velocidadX) {
		for (int r = 0; r < mapa.areasColision.size(); r++) {
			final Rectangle area = mapa.areasColision.get(r);

			int origenX = area.x + velocidadX * (int) velocidad - 3 * (int) velocidad;
			int origenY = area.y;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

			if (LIMITE_DERECHA.intersects(areaFutura)) {
				return true;
			}
		}
		return false;
	}

	private boolean fueraMapa(final int velocidadX, final int velocidadY) {

		int posicionFuturaX = (int) this.posicionX + velocidadX * (int) this.velocidad;
		int posicionFuturaY = (int) this.posicionY + velocidadY * (int) this.velocidad;

		final Rectangle bordesMapa = mapa.obtenerBordes(posicionFuturaX, posicionFuturaY, ANCHO_JUGADOR, ALTO_JUGADOR);

		if (LIMITE_ARRIBA.intersects(bordesMapa) || LIMITE_ABAJO.intersects(bordesMapa)
				|| LIMITE_DERECHA.intersects(bordesMapa) || LIMITE_IZQUIERDA.intersects(bordesMapa)) {
			return false;
		}
		return true;
	}
	private void cambiarVelocidadX(final int velocidadX, final int velocidadY) {
		if (velocidadX == -1) {
			direccion = 3;
		} else if (velocidadX == 1) {
			direccion = 1;
		}

		if (velocidadY == -1) {
			direccion = 2;
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

		g.drawImage(imagenActual, centroX, centroY, null);
		g.setColor(Color.green);
		g.drawRect(LIMITE_ARRIBA.x, LIMITE_ARRIBA.y, LIMITE_ARRIBA.width, LIMITE_ARRIBA.height);
		g.drawRect(LIMITE_ABAJO.x, LIMITE_ABAJO.y, LIMITE_ABAJO.width, LIMITE_ABAJO.height);
		g.drawRect(LIMITE_IZQUIERDA.x, LIMITE_IZQUIERDA.y, LIMITE_IZQUIERDA.width, LIMITE_IZQUIERDA.height);
		g.drawRect(LIMITE_DERECHA.x, LIMITE_DERECHA.y, LIMITE_DERECHA.width, LIMITE_DERECHA.height);
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
