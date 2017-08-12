package principal.maquinaestado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.entes.Jugador;
import principal.herramientas.CargadorRecursos;
import principal.interfaz_usuario.InterfazUsuario;
import principal.mapas.Mapa;
import principal.maquinaestado.EstadoJuego;

public class GestorJuego implements EstadoJuego {

	Mapa mapa = new Mapa("/texto/prueba");
	Jugador jugador = new Jugador(0.0, 200.0, mapa);

	BufferedImage logo = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_LOGO);

	public void actualizar() {
		jugador.actualizar();
		mapa.actualizar((int) jugador.obtenerPosicionX(), (int) jugador.obtenerPosicionY());
	}

	public void dibujar(Graphics g) {
		mapa.dibujar(g, (int) jugador.obtenerPosicionX(), (int) jugador.obtenerPosicionY());
		jugador.dibujar(g);
		g.drawImage(logo, Constantes.ANCHO_JUEGO - logo.getWidth() - 5, Constantes.ALTO_JUEGO - logo.getHeight() - 5,
				null);
		g.setColor(Color.red);
		g.drawString("X = " + jugador.obtenerPosicionX(), 20, 20);
		g.drawString("Y = " + jugador.obtenerPosicionY(), 20, 30);
		g.drawString("FPS = " + Constantes.FPS_CONTADOR, 20, 40);
		g.drawString("Escala x = " + Constantes.FACTOR_ESCALADO_X, 20, 60);
		g.drawString("Escala x = " + Constantes.FACTOR_ESCALADO_Y, 20, 70);

		InterfazUsuario.dibujarBarraResistencia(g, jugador.resistencia);
	}

}
