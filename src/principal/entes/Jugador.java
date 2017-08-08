package principal.entes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.control.GestorControles;
import principal.sprites.HojaSprites;

public class Jugador {

	private double posicionX;
	private double posicionY;

	private int estadoAnimacion;

	private int direccion;

	private HojaSprites hs;
	private BufferedImage imagenActual;

	public Jugador(final double posicionX, final double posicionY) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.estadoAnimacion = 0;

		this.direccion = 0;

		this.hs = new HojaSprites("/imagenes/hojasTexturas/1.png", Constantes.LADO_SPRITE, false);
		this.imagenActual = hs.getSprite(4, 3).getImagen();
	}

	public void actualizar() {
		if (GestorControles.teclado.isArriba()) {
			direccion = 1;
			cambiarImagenActual(direccion);
			this.posicionY -= 0.5;
		}

		if (GestorControles.teclado.isAbajo()) {
			direccion = 0;
			cambiarImagenActual(direccion);
			this.posicionY += 0.5;
		}

		if (GestorControles.teclado.isIzquierda()) {
			direccion = 2;
			cambiarImagenActual(direccion);
			this.posicionX -= 0.5;
		}

		if (GestorControles.teclado.isDerecha()) {
			direccion = 3;
			cambiarImagenActual(direccion);
			this.posicionX += 0.5;
		}
	}

	private void cambiarImagenActual(int direccion) {
		int frecuenciaAnimacion = 15; // 60/15 = 4
		int limiteEstado = 60 / frecuenciaAnimacion;

		if (Constantes.APS % frecuenciaAnimacion == 0) {
			estadoAnimacion++;
			if (estadoAnimacion >= limiteEstado) {
				estadoAnimacion = 0;
			}

			switch (estadoAnimacion) {
			case 0:
				this.imagenActual = hs.getSprite(direccion, estadoAnimacion).getImagen();
				break;
			case 1:
				this.imagenActual = hs.getSprite(direccion, 1).getImagen();
				break;
			case 2:
				this.imagenActual = hs.getSprite(direccion, estadoAnimacion).getImagen();
				break;
			case 3:
				this.imagenActual = hs.getSprite(direccion, 2).getImagen();
				break;
			}
		}
	}

	public void dibujar(Graphics g) {

		final int centroX = Constantes.ANCHO_PANTALLA / 2 - Constantes.LADO_SPRITE / 2;
		final int centroY = Constantes.ALTO_PANTALLA / 2 - Constantes.LADO_SPRITE / 2;

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
