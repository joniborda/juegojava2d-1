package principal;

import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.maquinaestado.GestorEstados;

public class GestorPrincipal {

	private boolean enFuncionamiento = false;
	private String titulo;
	private int ancho;
	private int alto;
	private SuperficieDibujo sd;
	private Ventana ventana;
	private GestorEstados ge;

	private GestorPrincipal(final String titulo, final int ancho, final int alto) {
		this.titulo = titulo;
		this.ancho = ancho;
		this.alto = alto;
	}

	public static void main(String[] args) {
		Constantes.ANCHO_VENTANA = 640;
		Constantes.ALTO_VENTANA = 360;
		GestorPrincipal gp = new GestorPrincipal("After-d", Constantes.ANCHO_VENTANA, Constantes.ALTO_VENTANA);
		gp.iniciarJuego();
		gp.iniciarBuclePrincipal();
	}

	private void iniciarJuego() {
		enFuncionamiento = true;
		inicializar();
	}

	private void inicializar() {
		sd = new SuperficieDibujo(ancho, alto);
		ventana = new Ventana(titulo, sd);
		ge = new GestorEstados();
	}

	private void iniciarBuclePrincipal() {

		final int NS_POR_SEGUNDO = 1000000000;
		final int APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();
		double tiempoTranscurrido;
		double delta = 0;

		while (enFuncionamiento) {
			final long inicioBucle = System.nanoTime();

			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle;

			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

			while (delta >= 1) {
				actualizar();
				Constantes.APS++;
				delta--;
			}

			dibujar();
			Constantes.FPS++;

			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {

				Constantes.APS_CONTADOR = Constantes.APS;
				Constantes.FPS_CONTADOR = Constantes.FPS;
				Constantes.APS = 0;
				Constantes.FPS = 0;
				referenciaContador = System.nanoTime();
			}
		}
	}

	private void dibujar() {
		sd.dibujar(ge);
	}

	private void actualizar() {
		ge.actualizar();
	}
}
