package principal.maquinaestado.estados.juego;

import java.awt.Graphics;

import principal.mapas.Mapa;
import principal.maquinaestado.EstadoJuego;

public class GestorJuego implements EstadoJuego {

	private GestorMapa gm;

	// String texto = CargadorRecursos.leerArchivoTexto("/texto/prueba");
	// HojaSprites hs = new HojaSprites("/imagenes/hojasTexturas/desierto.png",
	// 32, true);

	Mapa mapa = new Mapa("/texto/prueba");

	public void actualizar() {

	}

	public void dibujar(Graphics g) {
		mapa.dibujar(g);
	}

}
