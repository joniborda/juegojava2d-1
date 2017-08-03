package principal.maquinaestado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;

import principal.herramientas.CargadorRecursos;
import principal.maquinaestado.EstadoJuego;

public class GestorJuego implements EstadoJuego {

	private GestorMapa gm;

	String texto = CargadorRecursos.leerArchivoTexto("/texto/prueba");
	// HojaSprites hs = new HojaSprites("/imagenes/hojasTexturas/desierto.png",
	// 32, true);

	public void actualizar() {

	}

	public void dibujar(Graphics g) {
		// BufferedImage imagen = hs.getSprite(3, 3).getImagen();
		// g.drawImage(imagen, 100, 100, null);

		g.setColor(Color.white);
		g.drawString(texto, 10, 10);
	}

}
